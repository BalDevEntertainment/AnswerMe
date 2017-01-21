package com.baldev.answerme.modules;

import android.app.Application;

import com.baldev.answerme.model.DataManager;
import com.baldev.answerme.mvp.DataModel;
import com.baldev.answerme.mvp.TwitterFeedMVP.Presenter;
import com.baldev.answerme.mvp.TwitterFeedMVP.View;
import com.baldev.answerme.presenters.TwitterFeedPresenter;
import com.baldev.answerme.views.adapters.TwitterListAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TwitterFeedModule {
	private View view;

	public TwitterFeedModule(View view) {
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
		return new TwitterFeedPresenter(view, dataModel);
	}

	@Provides
	public TwitterListAdapter provideTwitterListAdapter() {
		return new TwitterListAdapter();
	}

}