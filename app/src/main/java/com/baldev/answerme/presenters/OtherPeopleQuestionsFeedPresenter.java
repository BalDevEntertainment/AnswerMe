package com.baldev.answerme.presenters;

import com.baldev.answerme.model.DTOs.QuestionDTO;
import com.baldev.answerme.model.helpers.FirebaseManager;
import com.baldev.answerme.model.helpers.FirebaseManager.NewReplyListener;
import com.baldev.answerme.model.helpers.FirebaseManagerImplementation;
import com.baldev.answerme.mvp.DataModel;
import com.baldev.answerme.mvp.QuestionsFeedMVP.Presenter;
import com.baldev.answerme.mvp.QuestionsFeedMVP.View;
import com.baldev.answerme.views.OtherPeopleQuestionsFeedFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.subjects.PublishSubject;

public class OtherPeopleQuestionsFeedPresenter implements Presenter, NewReplyListener {

	protected final View view;
	private final DataModel dataModel;
	private PublishSubject<String> searchResultsSubject = PublishSubject.create();
	private List<Subscription> subscriptions = new ArrayList<>();

	protected final FirebaseManager firebaseManager; // TODO: 22/01/2017 move to model

	@Inject
	public OtherPeopleQuestionsFeedPresenter(View view, DataModel dataModel) {
		this.view = view;
		this.dataModel = dataModel;
		this.firebaseManager = new FirebaseManagerImplementation(((OtherPeopleQuestionsFeedFragment)view).getContext(), this); // TODO: 22/01/2017 move to model
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
	public void storeDataToRetain(List<QuestionDTO> questionsDTO, String lastSearch) {
		this.dataModel.storeDataToRetain(questionsDTO, lastSearch);
	}

	@Override
	public void onRefresh() {
		firebaseManager.getQuestions(view::onNewData);
	}

	@Override
	public void onNewReply(QuestionDTO reply) {

	}
}
