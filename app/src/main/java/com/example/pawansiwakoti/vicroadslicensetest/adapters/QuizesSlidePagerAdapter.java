package com.example.pawansiwakoti.vicroadslicensetest.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.pawansiwakoti.vicroadslicensetest.fragments.QuizFragment;

import java.util.List;

public class QuizesSlidePagerAdapter extends FragmentStatePagerAdapter {
    private List<QuizFragment> mFragmentList;

    public QuizesSlidePagerAdapter(FragmentManager fm, List<QuizFragment> fragments) {
        super(fm);
        this.mFragmentList = fragments;
    }

    public void setFragmentList(List<QuizFragment> mFragmentList) {
        this.mFragmentList = mFragmentList;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int i) {

        if (this.mFragmentList == null) return null;
        return this.mFragmentList.get(i);
    }

    @Override
    public int getCount() {
        if (this.mFragmentList == null) return 0;
        return mFragmentList.size();
    }
}