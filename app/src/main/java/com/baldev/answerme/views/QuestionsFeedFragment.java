package com.baldev.answerme.views;

import android.content.Context;
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

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuestionsFeedFragment extends Fragment implements QuestionsFeedMVP.View {

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
		View view = inflater.inflate(R.layout.fragment_questions_feed, container, false);
		ButterKnife.bind(this, view);
		this.setupAdapter();
		this.setupSwipeRefreshLayout();
		return view;
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
		this.resultsList.setLayoutManager(layoutManager);
		this.resultsList.setAdapter(this.adapter);
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

}
