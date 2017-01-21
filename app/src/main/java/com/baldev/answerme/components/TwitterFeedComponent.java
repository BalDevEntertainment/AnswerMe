package com.baldev.answerme.components;

import com.baldev.answerme.modules.AppModule;
import com.baldev.answerme.modules.TwitterFeedModule;
import com.baldev.answerme.views.TwitterFeedFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
		modules={AppModule.class, TwitterFeedModule.class}
)
@SuppressWarnings("package")
public interface TwitterFeedComponent {
	void inject(TwitterFeedFragment fragment);
}

