package com.baldev.answerme.views.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.baldev.answerme.views.OtherPeopleQuestionsFeedFragment;
import com.baldev.answerme.views.OwnQuestionsFeedFragment;

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

	public MainFragmentPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int i) {
		switch (i){
			case 1:
				return new OwnQuestionsFeedFragment();
			default:
				return new OtherPeopleQuestionsFeedFragment();
		}
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
