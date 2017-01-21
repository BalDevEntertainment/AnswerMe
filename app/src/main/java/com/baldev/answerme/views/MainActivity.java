package com.baldev.answerme.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.baldev.answerme.R;
import com.baldev.answerme.components.DaggerMainComponent;
import com.baldev.answerme.modules.MainModule;
import com.baldev.answerme.mvp.MainActivityMVP;
import com.baldev.answerme.mvp.MainActivityMVP.Presenter;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainActivityMVP.View {

	@Inject
	Presenter presenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_twitter_feed);
		this.setupComponent();
	}

	private void setupComponent() {
		DaggerMainComponent.builder()
				.mainModule(new MainModule(this, this.getSupportFragmentManager()))
				.build()
				.inject(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		this.presenter.storeDataToRetain();
	}
}
