package com.baldev.answerme.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baldev.answerme.R;
import com.baldev.answerme.components.DaggerQuestionsFeedComponent;
import com.baldev.answerme.model.DTOs.QuestionDTO;
import com.baldev.answerme.modules.AppModule;
import com.baldev.answerme.modules.QuestionsFeedModule;
import com.baldev.answerme.mvp.QuestionsFeedMVP;
import com.baldev.answerme.mvp.QuestionsFeedMVP.Presenter;
import com.baldev.answerme.views.adapters.QuestionsListAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OtherPeopleQuestionsFeedFragment extends Fragment implements QuestionsFeedMVP.View {

	@BindView(R.id.list_results) RecyclerView resultsList;
	@BindView(R.id.swipe_refresh_layout) SwipeRefreshLayout swipeRefreshLayout;

	@Inject
	Presenter presenter;

	@Inject
	QuestionsListAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRetainInstance(true);
		this.setupComponent();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(getLayoutResourceId(), container, false);
		ButterKnife.bind(this, view);
		this.setupAdapter();
		this.setupSwipeRefreshLayout();
		return view;
	}

	protected int getLayoutResourceId() {
		return R.layout.fragment_questions_feed;
	}

	protected void setupComponent() {
		DaggerQuestionsFeedComponent.builder()
				.questionsFeedModule(new QuestionsFeedModule(this))
				.appModule(new AppModule(this.getActivity().getApplication()))
				.build()
				.inject(this);
	}

	@Override
	public void onDestroy() {
		this.presenter.unsubscribe();
		super.onDestroy();
	}

	private void setupAdapter() {
		LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
		layoutManager.setReverseLayout(true);
		this.resultsList.setLayoutManager(layoutManager);
		this.resultsList.setAdapter(this.adapter);
		this.adapter.setListener(this.presenter);
	}

	private void setupSwipeRefreshLayout() {
		this.swipeRefreshLayout.setOnRefreshListener(this.presenter);
		this.swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(this.getActivity(), R.color.colorPrimary));
	}

	@Override
	public void onNewData(List<QuestionDTO> questions) {
		this.adapter.setQuestions(questions);
		this.adapter.notifyDataSetChanged();
		this.swipeRefreshLayout.setRefreshing(false);
	}

	@Override
	public void startLoading() {
		if (!this.swipeRefreshLayout.isRefreshing()) {
			this.swipeRefreshLayout.setRefreshing(true);
		}
	}

	public void storeDataToRetain() {
	}

	@Override
	public void openAnswerQuestionActivity(QuestionDTO questionDTO) {
		((MainActivity) this.getActivity()).logInAnalytics("OtherPeopleQuestionClicked", questionDTO.getId());
		Intent intent = new Intent(this.getActivity(), AnswerQuestionActivity.class);
		intent.putExtra(AnswerQuestionActivity.QUESTION_DTO, questionDTO);
		startActivity(intent);
	}

	@Override
	public void openQuestionDetailsActivity(QuestionDTO dto) {
		// TODO: 23/01/2017 refactor
	}

}
