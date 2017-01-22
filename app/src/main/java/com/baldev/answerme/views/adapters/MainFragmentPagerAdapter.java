package com.baldev.answerme.views.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.baldev.answerme.views.QuestionsFeedFragment;

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

	public MainFragmentPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int i) {
		QuestionsFeedFragment questionsFeedFragment = new QuestionsFeedFragment();
		return questionsFeedFragment;
	}

	@Override
	public int getCount() {
		return 2;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		switch (position) {
			case 1:
				return "My questions";
			default:
				return "People questions";
		}
	}
}
