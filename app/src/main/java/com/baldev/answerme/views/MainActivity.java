package com.baldev.answerme.views;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.baldev.answerme.R;
import com.baldev.answerme.components.DaggerMainComponent;
import com.baldev.answerme.modules.MainModule;
import com.baldev.answerme.mvp.MainActivityMVP;
import com.baldev.answerme.mvp.MainActivityMVP.Presenter;
import com.baldev.answerme.views.adapters.MainFragmentPagerAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityMVP.View {

	@BindView(R.id.view_pager_main)
	ViewPager viewPager;

	@BindView(R.id.tab_layout)
	TabLayout tabLayout;

	@Inject
	Presenter presenter;

	@Inject
	MainFragmentPagerAdapter pagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);
		this.setupComponent();
		ButterKnife.bind(this);
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
	}
}
