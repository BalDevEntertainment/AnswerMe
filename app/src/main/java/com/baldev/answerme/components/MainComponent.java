package com.baldev.answerme.components;

import com.baldev.answerme.modules.MainModule;
import com.baldev.answerme.views.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
		modules={MainModule.class}
)
@SuppressWarnings("package")
public interface MainComponent {
	void inject(MainActivity activity);
}

