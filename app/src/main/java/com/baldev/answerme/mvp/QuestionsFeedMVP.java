package com.baldev.answerme.mvp;

import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;

import com.baldev.answerme.model.DTOs.QuestionDTO;
import com.baldev.answerme.views.adapters.QuestionsListAdapter.QuestionAdapterListener;

import java.util.List;

public interface QuestionsFeedMVP {

	interface Presenter extends OnRefreshListener, QuestionAdapterListener {
		@Override
		void onRefresh();

		void unsubscribe();

		void getTweetsBySearchTerm(String searchTerm);

		void storeDataToRetain(List<QuestionDTO> questions, String query);

		@Override
		void onQuestionClicked(QuestionDTO dto);
	}

	interface View {
		void startLoading();

		void onNewData(List<QuestionDTO> tweets);

		void storeDataToRetain();

		void openAnswerQuestionActivity(QuestionDTO questionDTO);

		void openQuestionDetailsActivity(QuestionDTO dto);
	}
}
