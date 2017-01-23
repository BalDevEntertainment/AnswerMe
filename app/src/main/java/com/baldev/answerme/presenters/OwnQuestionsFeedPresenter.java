package com.baldev.answerme.presenters;

import com.baldev.answerme.model.DTOs.QuestionDTO;
import com.baldev.answerme.mvp.DataModel;
import com.baldev.answerme.mvp.QuestionsFeedMVP.View;

public class OwnQuestionsFeedPresenter extends OtherPeopleQuestionsFeedPresenter {

	public OwnQuestionsFeedPresenter(View view, DataModel dataModel) {
		super(view, dataModel);
	}

	@Override
	public void onRefresh() {
		firebaseManager.getOwnQuestions(view::onNewData);
	}

	@Override
	public void onQuestionClicked(QuestionDTO dto) {

	}
}
