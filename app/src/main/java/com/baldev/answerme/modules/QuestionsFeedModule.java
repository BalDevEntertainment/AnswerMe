package com.baldev.answerme.modules;

import android.app.Application;

import com.baldev.answerme.model.DTOs.Tweet;
import com.baldev.answerme.mvp.DataModel;
import com.baldev.answerme.mvp.QuestionsFeedMVP.Presenter;
import com.baldev.answerme.mvp.QuestionsFeedMVP.View;
import com.baldev.answerme.presenters.QuestionsFeedPresenter;
import com.baldev.answerme.views.adapters.QuestionsListAdapter;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class QuestionsFeedModule {
	private View view;

	public QuestionsFeedModule(View view) {
		this.view = view;
	}

	@Provides
	public View provideView() {
		return this.view;
	}

	@Singleton
	@Provides
	public DataModel provideModel(Application context) {
		return new DataModel() {
			@Override
			public void storeDataToRetain(List<Tweet> retainedTweets, String lastSearch) {

			}
		};
	}

	@Provides
	public Presenter providePresenter(View view, DataModel dataModel) {
		return new QuestionsFeedPresenter(view, dataModel);
	}

	@Provides
	public QuestionsListAdapter provideTwitterListAdapter() {
		return new QuestionsListAdapter();
	}

}