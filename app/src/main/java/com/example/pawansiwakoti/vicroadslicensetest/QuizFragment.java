package com.example.pawansiwakoti.vicroadslicensetest;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pawansiwakoti.vicroadslicensetest.databinding.CellQuizQuestionBinding;
import com.example.pawansiwakoti.vicroadslicensetest.model.Quiz;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class QuizFragment extends Fragment {
    public static final String KEY_QUIZ_DATA = "key_quiz_data";
    private QuizViewModel viewModel;
    private Quiz currentQuiz;
    private String quizId;
    private CellQuizQuestionBinding mBinding;
    private List<TextView> textViewOptions;
    private int selectedOptionIndex = -1;
    private List<OnItemClickListener> listeners;

    interface OnItemClickListener{
        void onClick(int position);
    }

    public QuizFragment() {
        listeners = new ArrayList<>();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemClickListener) {
            listeners.add((OnItemClickListener) context);
        }
        if (getArguments() != null && getArguments().containsKey(KEY_QUIZ_DATA)) {
            quizId = getArguments().getString(KEY_QUIZ_DATA, "");
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (textViewOptions == null) {
            textViewOptions = new ArrayList<>();
        }
        textViewOptions.clear();
        textViewOptions.add(mBinding.textOptionOne);
        textViewOptions.add(mBinding.textOptionTwo);
        textViewOptions.add(mBinding.textOptionThree);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(QuizViewModel.class);
        viewModel.getAllQuizes().observe(getViewLifecycleOwner(), quizzes -> {
            currentQuiz = CommonMethods.filterQuizById(quizzes, quizId);
            modelToUI();
        });
        viewModel.getOptionMap().observe(getViewLifecycleOwner(), stringIntegerHashMap -> {
            if (stringIntegerHashMap != null && stringIntegerHashMap.containsKey(quizId)) {
                selectedOptionIndex = stringIntegerHashMap.get(quizId);
                modelToUI();
            }
        });
    }

    private void modelToUI() {
        if (currentQuiz == null) {
            return;
        }
        mBinding.textQuestion.setText(currentQuiz.getQuestion());
        if (StringUtils.isNullOrEmpty(currentQuiz.getQuestionImageUrl())) {
            mBinding.imageQuestion.setVisibility(View.GONE);
        } else {
            mBinding.imageQuestion.setVisibility(View.VISIBLE);
            Picasso.get()
                    .load(currentQuiz.getQuestionImageUrl())
                    .placeholder(R.drawable.placeholder)
                    .into(mBinding.imageQuestion);
        }
        if (currentQuiz.getOptions() != null && currentQuiz.getOptions().length > 0) {
            for (int i = 0; i < textViewOptions.size(); i++) {
                TextView textView = textViewOptions.get(i);
                textView.setText(currentQuiz.getOptions()[i]);
                if (viewModel.isPractice()) { // Practice quiz
                    if (selectedOptionIndex < 0) {
                        textView.setTextColor(getResources().getColor(R.color.light_black));
                        textView.setBackground(getResources().getDrawable(R.drawable.option_normal));
                    } else {
                        if (i == selectedOptionIndex) {
                            if (i != currentQuiz.getAnswer()) {
                                textView.setTextColor(getResources().getColor(R.color.white));
                                textView.setBackground(getResources().getDrawable(R.drawable.option_in_correct));
                            } else {
                                textView.setTextColor(getResources().getColor(R.color.white));
                                textView.setBackground(getResources().getDrawable(R.drawable.option_correct));
                            }
                        } else {
                            if (i == currentQuiz.getAnswer()) {
                                textView.setTextColor(getResources().getColor(R.color.white));
                                textView.setBackground(getResources().getDrawable(R.drawable.option_correct));
                            } else {
                                textView.setTextColor(getResources().getColor(R.color.light_black));
                                textView.setBackground(getResources().getDrawable(R.drawable.option_normal));
                            }
                        }
                    }
                } else { // Real quiz
                    if (i == selectedOptionIndex) {
                        textView.setTextColor(getResources().getColor(R.color.white));
                        textView.setBackground(getResources().getDrawable(R.drawable.option_selected));
                    } else {
                        textView.setTextColor(getResources().getColor(R.color.light_black));
                        textView.setBackground(getResources().getDrawable(R.drawable.option_normal));
                    }
                }
                final int finalI = i;
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewModel.setOption(currentQuiz.getId(), finalI);
                        if (!viewModel.isPractice()) {
                            for (OnItemClickListener listener : listeners) {
                                listener.onClick(finalI);
                            }
                        }

                    }
                });
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.cell_quiz_question, container, false);
        return mBinding.getRoot();
    }
}