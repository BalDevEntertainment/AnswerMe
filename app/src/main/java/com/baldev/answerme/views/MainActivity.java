package com.baldev.answerme.views;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.baldev.answerme.R;
import com.baldev.answerme.components.DaggerMainComponent;
import com.baldev.answerme.modules.MainModule;
import com.baldev.answerme.mvp.MainActivityMVP;
import com.baldev.answerme.mvp.MainActivityMVP.Presenter;
import com.baldev.answerme.views.adapters.MainFragmentPagerAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.analytics.FirebaseAnalytics;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AnswerMeActivity implements MainActivityMVP.View {

	@BindView(R.id.view_pager_main)
	ViewPager viewPager;

	@BindView(R.id.tab_layout)
	TabLayout tabLayout;

	@Inject
	Presenter presenter;

	@Inject
	MainFragmentPagerAdapter pagerAdapter;

	private FirebaseAnalytics firebaseAnalytics;
	private AdView adView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setupComponent();
		this.viewPager.setAdapter(pagerAdapter);
		tabLayout.addTab(tabLayout.newTab().setText(pagerAdapter.getPageTitle(0)));
		tabLayout.addTab(tabLayout.newTab().setText(pagerAdapter.getPageTitle(1)));
		tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
		viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
		tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				viewPager.setCurrentItem(tab.getPosition());
			}

			@Override
			public void onTabUnselected(TabLayout.Tab tab) {

			}

			@Override
			public void onTabReselected(TabLayout.Tab tab) {

			}
		});
		setupAds();

		firebaseAnalytics = FirebaseAnalytics.getInstance(this);
	}

	@Override
	protected int getLayoutResourceId() {
		return R.layout.activity_main;
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
		adView.destroy();
	}

	private void setupAds() {
		adView = (AdView) this.findViewById(R.id.ad_view);
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);
	}

	public void logInAnalytics(String key, String id) {
		Bundle bundle = new Bundle();
		bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id);
		firebaseAnalytics.logEvent(key, bundle);
	}
}
