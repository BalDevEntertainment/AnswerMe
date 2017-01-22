package com.baldev.answerme.mvp;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;

import com.baldev.answerme.model.DTOs.QuestionDTO;

import java.util.List;

public interface QuestionsFeedMVP {

	interface Presenter extends OnRefreshListener {
		@Override
		void onRefresh();

		void unsubscribe();

		void getTweetsBySearchTerm(String searchTerm);

		void storeDataToRetain(List<QuestionDTO> questions, String query);
	}

	interface View {
		void startLoading();

		void onNewData(List<QuestionDTO> tweets);

		void storeDataToRetain();
	}
}
