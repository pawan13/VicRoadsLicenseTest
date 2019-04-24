package com.example.pawansiwakoti.vicroadslicensetest;

import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.pawansiwakoti.vicroadslicensetest.databinding.ActivityQuizBinding;
import com.example.pawansiwakoti.vicroadslicensetest.model.Answers;
import com.example.pawansiwakoti.vicroadslicensetest.model.Quiz;
import com.example.pawansiwakoti.vicroadslicensetest.repository.AppRepository;
import com.example.pawansiwakoti.vicroadslicensetest.ResultFragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener, QuizFragment.OnItemClickListener{

    private TextView tvQuizCount;
    private AppRepository appRepository;

    public static final String ARG_IS_PRACTICE = "arg_is_practice";

    private QuizViewModel viewModel;
    private ActivityQuizBinding mBinding;
    private QuizesSlidePagerAdapter mPagerAdapter;
    private List<QuizFragment> fragments;
    private HashMap<String, Integer> answerMap;
    private List<Quiz> mQuizList;
    private boolean isPractice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_quiz);

        isPractice = getIntent() != null && getIntent().getExtras() != null
                && getIntent().getExtras().getBoolean(ARG_IS_PRACTICE, false);
        setupActionBar();
        initViewPager();
        initViewModel();
        mBinding.buttonNext.setOnClickListener(this);
        mBinding.buttonPrev.setOnClickListener(this);
    }

    private void initViewPager() {
        mPagerAdapter = new QuizesSlidePagerAdapter(getSupportFragmentManager(), null);
        mBinding.viewpagerQuiz.setAdapter(mPagerAdapter);
        mBinding.viewpagerQuiz.addOnPageChangeListener(mListener);
    }

    private ViewPager.OnPageChangeListener mListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            setCurrentStatus(i + 1, fragments.size());
            changeNextButton();
            viewModel.setCurrentPagerPosition(i);
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    private boolean isLastPage() {
        return mBinding.viewpagerQuiz.getCurrentItem() == fragments.size() - 1;
    }

    private boolean isFirstPage() {
        return mBinding.viewpagerQuiz.getCurrentItem() <= 0;
    }

    private void changeNextButton() {
        boolean isComplete = isLastPage();
        mBinding.buttonNext.setText(isComplete ? getString(R.string.text_complete) : getString(R.string.text_next));
        if (isComplete) {
            mBinding.buttonNext.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_check_white_24dp, 0);
        } else {
            mBinding.buttonNext.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_chevron_right_white_24dp, 0);
        }
    }

    private void setCurrentStatus(int x, int y) {
        if (fragments == null || fragments.size() == 0) {
            mBinding.textCurrentStatus.setVisibility(View.GONE);
        } else {
            mBinding.textCurrentStatus.setVisibility(View.VISIBLE);
            mBinding.textCurrentStatus.setText(String.format(getString(R.string.format_x_of_y), x, y));
        }
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        viewModel.getAllQuizes().observe(this, quizzes -> {
            if (quizzes == null) return;
            // Todo
            //List<Quiz> randomQuizzes = CommonMethods.INSTANCE.getRandomXQuizzes(quizzes, Constants.MAX_QUIZ_PER_ATTEMPT);
            List<Quiz> randomQuizzes = quizzes;
            mQuizList = randomQuizzes;
            createAndSetFragments(randomQuizzes);
        });

        viewModel.setPractice(isPractice);
        viewModel.getOptionMap().observe(this, stringIntegerHashMap -> answerMap = stringIntegerHashMap);
    }

    private void createAndSetFragments(@NonNull List<Quiz> quizzes) {
        if (fragments == null) {
            fragments = new ArrayList<>();
        }

        fragments.clear();
        for (Quiz quiz : quizzes) {
            QuizFragment fragment = new QuizFragment();
            Bundle bundle = new Bundle();
            bundle.putString(QuizFragment.KEY_QUIZ_DATA, quiz.getId());
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        mPagerAdapter.setFragmentList(fragments);
        // If screen rotation, restore previous quiz position
        if (viewModel.getCurrentPagerLocation() > -1) {
            mBinding.viewpagerQuiz.setCurrentItem(viewModel.getCurrentPagerLocation());
        }
        setCurrentStatus(viewModel.getCurrentPagerLocation() + 1, fragments.size());
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(isPractice ? R.string.title_practice : R.string.title_quiz);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_prev:
                changeQuiz(false);
                break;
            case R.id.button_next:
                if (isLastPage()) {
                    if (isPractice) displayAdAndGoToHome();
                    else displayResult();
                } else {
                    changeQuiz(true);
                }
                break;
        }
    }

    private void displayAdAndGoToHome() {
        startActivity(new Intent(this, MainActivity.class));
    }

    private void displayResult() {
        if (answerMap == null || mQuizList == null) return;
        int skip = 0;
        for (Quiz quiz : mQuizList) {
            if (!answerMap.containsKey(quiz.getId())) {
                skip++;
            }
        }

        if (skip > 0) { //Alert user
            final int finalSkip = skip;
            DialogInterface.OnClickListener positiveClickListener = (dialog, which) -> gotoResultPage();
            DialogInterface.OnClickListener negativeClickListener = (dialog, which) -> dialog.dismiss();
            AlertUtils.displayYesNoAlert(this, getString(R.string.skip_dialog_title), String.format(getString(R.string.skip_dialog_message), finalSkip),
                    getString(R.string.skip_dialog_positive_text), getString(R.string.skip_dialog_negative_text),
                    positiveClickListener, negativeClickListener);
        } else {
            gotoResultPage();
        }

    }

    private void gotoResultPage() {
        int totalCorrectAnswer = 0;
        String randomId = UUID.randomUUID().toString();
        //Todo
        List<Answers> answers = new ArrayList<>();
        for (Quiz quiz : mQuizList) {
            boolean skipped = !answerMap.containsKey(quiz.getId());
            String selectedAnswer = !skipped ? quiz.getOptions()[answerMap.get(quiz.getId())] : null;
            String rightAnswer = quiz.getOptions()[quiz.getAnswer()-1];
            boolean isCorrectAns = !skipped && (answerMap.get(quiz.getId()) == quiz.getAnswer());
            if (isCorrectAns) totalCorrectAnswer++;
            Answers answer = new Answers(randomId, quiz.getId(),
                    skipped, selectedAnswer, rightAnswer, isCorrectAns, new Date());
            answers.add(answer);
        }
        viewModel.saveAnswers(answers);
        Intent intent = GeneralActivity.getIntent(this, ResultFragment.class, ResultFragment.class.getSimpleName(), "Result");
        intent.putExtra(ResultFragment.ARG_SESSION_ID, randomId);
        startActivity(intent);
        finish();
    }

    private void changeQuiz(boolean next) {
        int currentItem = mBinding.viewpagerQuiz.getCurrentItem();
        if (currentItem <= 0 && !next) return;
        if (currentItem >= (fragments.size()-1) && next) return;
        mBinding.viewpagerQuiz.setCurrentItem(next ? currentItem + 1 : currentItem - 1, true);
    }

    @Override
    public void onClick(int position) {
        changeQuiz(true);
    }



}