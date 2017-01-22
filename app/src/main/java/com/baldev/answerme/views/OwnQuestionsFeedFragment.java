package com.baldev.answerme.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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
import butterknife.OnClick;

public class OwnQuestionsFeedFragment extends OtherPeopleQuestionsFeedFragment implements QuestionsFeedMVP.View {

	@Override
	protected int getLayoutResourceId() {
		return R.layout.fragment_questions_mine;
	}

	@OnClick(R.id.fab_add_new_question)
	public void onFabClick(){
		Intent intent = new Intent(this.getActivity(), CreateQuestionActivity.class);
		startActivity(intent);
	}
}
