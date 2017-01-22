package com.baldev.answerme.modules;

import android.app.Application;

import com.baldev.answerme.model.DataManager;
import com.baldev.answerme.mvp.DataModel;
import com.baldev.answerme.mvp.QuestionsFeedMVP.Presenter;
import com.baldev.answerme.mvp.QuestionsFeedMVP.View;
import com.baldev.answerme.presenters.QuestionsFeedPresenter;
import com.baldev.answerme.views.adapters.TwitterListAdapter;

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
		return new DataManager(context);
	}

	@Provides
	public Presenter providePresenter(View view, DataModel dataModel) {
		return new QuestionsFeedPresenter(view, dataModel);
	}

	@Provides
	public TwitterListAdapter provideTwitterListAdapter() {
		return new TwitterListAdapter();
	}

}