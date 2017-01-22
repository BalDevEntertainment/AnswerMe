package com.baldev.answerme.presenters;

import com.baldev.answerme.model.DTOs.Tweet;
import com.baldev.answerme.model.helpers.FirebaseHelper;
import com.baldev.answerme.mvp.DataModel;
import com.baldev.answerme.mvp.QuestionsFeedMVP;
import com.baldev.answerme.mvp.QuestionsFeedMVP.View;
import com.baldev.answerme.views.QuestionsFeedFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.subjects.PublishSubject;

public class QuestionsFeedPresenter implements QuestionsFeedMVP.Presenter {

	private static final int SEARCH_DELAY = 200;

	private final View view;
	private final DataModel dataModel;
	private PublishSubject<String> searchResultsSubject = PublishSubject.create();
	private List<Subscription> subscriptions = new ArrayList<>();

	@Inject
	public QuestionsFeedPresenter(View view, DataModel dataModel) {
		this.view = view;
		this.dataModel = dataModel;
	}

	@Override
	public void getTweetsBySearchTerm(final String searchTerm) {
		this.searchResultsSubject.onNext(searchTerm);
	}

	@Override
	public void unsubscribe() {
		for (Subscription subscription : subscriptions) {
			if (!subscription.isUnsubscribed()) {
				subscription.unsubscribe();
			}
		}
	}

	@Override
	public void storeDataToRetain(List<Tweet> tweets, String lastSearch) {
		this.dataModel.storeDataToRetain(tweets, lastSearch);
	}

	@Override
	public void onRefresh() {
		FirebaseHelper.getQuestions(((QuestionsFeedFragment)view).getActivity(), view::onNewData); // TODO: 22/01/2017 improve this getactivity shit
	}
}
