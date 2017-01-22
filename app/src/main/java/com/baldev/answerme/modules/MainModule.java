package com.baldev.answerme.modules;

import android.support.v4.app.FragmentManager;

import com.baldev.answerme.mvp.MainActivityMVP.Presenter;
import com.baldev.answerme.mvp.MainActivityMVP.View;
import com.baldev.answerme.presenters.MainPresenter;
import com.baldev.answerme.views.adapters.MainFragmentPagerAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
	private final FragmentManager fragmentManager;
	private final View view;

	public MainModule(View view, FragmentManager fragmentManager) {
		this.view = view;
		this.fragmentManager = fragmentManager;
	}

	@Provides
	public View provideView() {
		return this.view;
	}

	@Provides
	public Presenter providePresenter(View view) {
		return new MainPresenter(view);
	}

	@Provides
	public MainFragmentPagerAdapter providePagerAdapter() {
		return new MainFragmentPagerAdapter(fragmentManager);
	}
}