package com.baldev.answerme.modules;

import android.app.Application;

import com.baldev.answerme.model.DTOs.QuestionDTO;
import com.baldev.answerme.mvp.DataModel;
import com.baldev.answerme.mvp.QuestionsFeedMVP.Presenter;
import com.baldev.answerme.mvp.QuestionsFeedMVP.View;
import com.baldev.answerme.presenters.OtherPeopleQuestionsFeedPresenter;
import com.baldev.answerme.presenters.OwnQuestionsFeedPresenter;
import com.baldev.answerme.views.adapters.QuestionsListAdapter;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class OwnQuestionsModule{
	private View view;

	public OwnQuestionsModule(View view) {
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
			public void storeDataToRetain(List<QuestionDTO> retainedQuestions, String lastSearch) {

			}
		};
	}

	@Provides
	public Presenter providePresenter(View view, DataModel dataModel) {
		return new OwnQuestionsFeedPresenter(view, dataModel);
	}

	@Provides
	public QuestionsListAdapter provideTwitterListAdapter() {
		return new QuestionsListAdapter();
	}

}