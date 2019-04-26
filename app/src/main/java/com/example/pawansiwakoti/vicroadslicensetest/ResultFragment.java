package com.example.pawansiwakoti.vicroadslicensetest;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pawansiwakoti.vicroadslicensetest.databinding.FragmentQuizResultBinding;
import com.example.pawansiwakoti.vicroadslicensetest.model.Answers;

import java.util.List;

public class ResultFragment extends Fragment implements View.OnClickListener {

    public static final String ARG_SESSION_ID = "arg_session_id";
    public static final int PASS_PERCENT = 50;
    private static final String TAG = "ResultFragment";

    private FragmentQuizResultBinding mBinding;
    private GeneralActivityViewModel viewModel;
    private String sessionId;
    private List<Answers> answersList;
    private int correctAnswer = 0;

    public ResultFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz_result, container, false);

        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey(ARG_SESSION_ID)) {
            sessionId = bundle.getString(ARG_SESSION_ID, "");
        }
        mBinding.buttonGoHome.setOnClickListener(this);
        mBinding.buttonTryAgain.setOnClickListener(this);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(GeneralActivityViewModel.class);
        viewModel.getAnswersBySession(sessionId).observe(getViewLifecycleOwner(), new Observer<List<Answers>>() {
            @Override
            public void onChanged(@Nullable List<Answers> answers) {
                if (answers != null) {
                    answersList = answers;
                    calculatePercentAndUpdateUI();
                }
            }
        });
    }

    private void calculatePercentAndUpdateUI() {
        int count = answersList.size();
        for (Answers answer: answersList) {
            if (answer.isCorrectlyAnswered()) correctAnswer++;
        }
        double percent = count == 0 ? 0 : (correctAnswer / count) * 100;
        changeUIFor(percent > PASS_PERCENT, correctAnswer, count);

    }

    private void changeUIFor(boolean pass, int correct, int total) {
        int color = getResources().getColor(pass ? R.color.seaGreen : R.color.rosada);
        Drawable drawable = ContextCompat.getDrawable(getContext(), pass ? R.drawable.ic_success : R.drawable.ic_exclamation);
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, color);
        mBinding.imageResult.setImageDrawable(drawable);
        mBinding.textFeedback.setText(pass ? getString(R.string.feedback_success) : getString(R.string.feedback_fail));
        mBinding.textPoints.setText(String.format(getString(R.string.display_result_format), correct, total));
        mBinding.textPoints.setTextColor(color);
        mBinding.textFeedback.setTextColor(color);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.button_go_home:
                intent = new Intent(getActivity(), MainActivity.class);
                break;
            case R.id.button_try_again:
                intent = new Intent(getActivity(), QuizActivity.class);
                break;
        }
        startActivity(intent);
    }

}