package com.baldev.answerme.views;

import android.content.Intent;

import com.baldev.answerme.R;
import com.baldev.answerme.components.DaggerOwnQuestionsComponent;
import com.baldev.answerme.model.DTOs.QuestionDTO;
import com.baldev.answerme.modules.AppModule;
import com.baldev.answerme.modules.OwnQuestionsModule;
import com.baldev.answerme.mvp.QuestionsFeedMVP;

import butterknife.OnClick;

public class OwnQuestionsFeedFragment extends OtherPeopleQuestionsFeedFragment implements QuestionsFeedMVP.View {

	@Override
	protected int getLayoutResourceId() {
		return R.layout.fragment_questions_mine;
	}

	@Override
	protected void setupComponent() {
		DaggerOwnQuestionsComponent.builder()
				.ownQuestionsModule(new OwnQuestionsModule(this))
				.appModule(new AppModule(this.getActivity().getApplication()))
				.build()
				.inject(this);
	}

	@OnClick(R.id.fab_add_new_question)
	public void onFabClick(){
		Intent intent = new Intent(this.getActivity(), CreateQuestionActivity.class);
		startActivity(intent);
	}

	@Override
	public void openAnswerQuestionActivity(QuestionDTO questionDTO) {
		((MainActivity)this.getActivity()).logInAnalytics("OwnQuestionsClicked", questionDTO.getId());
	}

	@Override
	public void openQuestionDetailsActivity(QuestionDTO questionDTO) {
		Intent intent = new Intent(this.getActivity(), QuestionDetailsActivity.class);
		intent.putExtra(QuestionDetailsActivity.QUESTION_DTO, questionDTO);
		startActivity(intent);
	}
}
