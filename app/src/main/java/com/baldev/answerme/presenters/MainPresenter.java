package com.baldev.answerme.presenters;

import com.baldev.answerme.mvp.MainActivityMVP;
import com.baldev.answerme.mvp.MainActivityMVP.View;

import javax.inject.Inject;

public class MainPresenter implements MainActivityMVP.Presenter {

	private final View view;

	@Inject
	public MainPresenter(View view) {
		this.view = view;
	}

	public void storeDataToRetain() {
	}
}
