package com.baldev.answerme.mvp;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;

import com.baldev.answerme.model.DTOs.Tweet;

import java.util.List;

public interface TwitterFeedMVP {

	interface Presenter extends OnRefreshListener {
		@Override
		void onRefresh();

		void unsubscribe();

		void getTweetsBySearchTerm(String searchTerm);

		void storeDataToRetain(List<Tweet> tweets, String query);
	}

	interface View {
		@NonNull
		String getSearchQuery();

		void startLoading();

		void onNewData(List<Tweet> tweets);

		void storeDataToRetain();
	}
}
