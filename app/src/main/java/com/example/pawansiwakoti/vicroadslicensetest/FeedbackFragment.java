package com.example.pawansiwakoti.vicroadslicensetest;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//import androidx.databinding.DataBindingUtil;

import com.example.pawansiwakoti.vicroadslicensetest.R;
import com.example.pawansiwakoti.vicroadslicensetest.databinding.FragmentFeedbackBinding;
import com.example.pawansiwakoti.vicroadslicensetest.WebServiceListener;
import com.example.pawansiwakoti.vicroadslicensetest.network.ApiResponse;
import com.example.pawansiwakoti.vicroadslicensetest.network.FeedbackSchema;
import com.example.pawansiwakoti.vicroadslicensetest.GeneralActivityViewModel;
import com.example.pawansiwakoti.vicroadslicensetest.AlertUtils;
import com.example.pawansiwakoti.vicroadslicensetest.StringUtils;


import java.util.ArrayList;
import java.util.List;


public class FeedbackFragment extends BaseFragment implements View.OnClickListener, WebServiceListener {
    private FragmentFeedbackBinding mBinding;
    private GeneralActivityViewModel mViewModel;

    private List<TextInputLayout> textInputLayouts;

    public FeedbackFragment() {
        textInputLayouts = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_feedback, container,false);
        setupUI();
        return mBinding.getRoot();
    }

    private void setupUI() {
        mBinding.buttonClear.setOnClickListener(this);
        mBinding.buttonSubmit.setOnClickListener(this);
        textInputLayouts.add(mBinding.tilName);
        textInputLayouts.add(mBinding.tilEmail);
        textInputLayouts.add(mBinding.tilPhone);
        textInputLayouts.add(mBinding.tilMessage);

        for (final TextInputLayout layout: textInputLayouts) {
            if (layout.getEditText() != null) {
                layout.getEditText().addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (count > 0 && layout.isErrorEnabled()) layout.setErrorEnabled(false);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(getActivity()).get(GeneralActivityViewModel.class);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_submit:
                FeedbackSchema feedback = new FeedbackSchema();
                feedback.setEmail(mBinding.tilEmail.getEditText().getText().toString());
                feedback.setMessage(mBinding.tilMessage.getEditText().getText().toString());
                feedback.setName(mBinding.tilName.getEditText().getText().toString());
                feedback.setPhone(mBinding.tilPhone.getEditText().getText().toString());
                boolean validFeedback = validateFeedback(feedback);
                if (validFeedback) sendFeedback(feedback);
                break;
            case R.id.button_clear:
                resetAllTextFields();
                break;
        }
    }

    private void sendFeedback(FeedbackSchema feedback) {
        mViewModel.sendFeedback(feedback, this);
        displayProgressBar(true);
    }

    private boolean validateFeedback(FeedbackSchema feedback) {

        if (StringUtils.isNullOrEmpty(feedback.getName().trim())) {
            mBinding.tilName.setError("Please enter full name");
            return false;
        }
        if (StringUtils.isNullOrEmpty(feedback.getEmail().trim())) {
            mBinding.tilEmail.setError("Please enter email");
            return false;
        }
        if (StringUtils.isNullOrEmpty(feedback.getMessage().trim())) {
            mBinding.tilMessage.setError("Please enter message");
            return false;
        }
        return true;
    }

    private void displayProgressBar(boolean display) {
        mBinding.progressBar.setVisibility(display ? View.VISIBLE : View.GONE);
    }


    @Override
    public void onFeedbackSubmit(ApiResponse response) {
        displayProgressBar(false);
        if (response != null) {
            AlertUtils.displayAlertOnly(getActivity(), "Feedback Submitted", response.getMessage());
            resetAllTextFields();
        } else {
            AlertUtils.displayAlertOnly(getActivity(), "Something wrong!", "Please check your internet connection");
        }
    }

    private void resetAllTextFields() {
        for (TextInputLayout layout : textInputLayouts) {
            if (layout.getEditText() != null) {
                layout.getEditText().setText(null);
            }
        }
        mBinding.tilName.requestFocus();
    }
}