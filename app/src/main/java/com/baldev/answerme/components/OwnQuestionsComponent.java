package com.baldev.answerme.components;

import com.baldev.answerme.modules.AppModule;
import com.baldev.answerme.modules.OwnQuestionsModule;
import com.baldev.answerme.modules.QuestionsFeedModule;
import com.baldev.answerme.views.OtherPeopleQuestionsFeedFragment;
import com.baldev.answerme.views.OwnQuestionsFeedFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
		modules={AppModule.class, OwnQuestionsModule.class}
)
@SuppressWarnings("package")
public interface OwnQuestionsComponent {
	void inject(OwnQuestionsFeedFragment fragment);
}

