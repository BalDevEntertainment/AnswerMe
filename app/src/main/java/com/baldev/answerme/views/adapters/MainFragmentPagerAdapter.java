package com.baldev.answerme.views.adapters;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.baldev.answerme.R;
import com.baldev.answerme.views.OtherPeopleQuestionsFeedFragment;
import com.baldev.answerme.views.OwnQuestionsFeedFragment;

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

	private final String myQuestionsText;
	private final String otherPeopleQuestions;

	public MainFragmentPagerAdapter(FragmentManager fm, Context context) {
		super(fm);
		this.myQuestionsText = context.getString(R.string.my_questions);
		this.otherPeopleQuestions = context.getString(R.string.other_people_questions);
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
				return myQuestionsText;
			default:
				return otherPeopleQuestions;
		}
	}
}
