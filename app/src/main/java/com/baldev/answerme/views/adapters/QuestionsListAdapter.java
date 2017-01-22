package com.baldev.answerme.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baldev.answerme.R;
import com.baldev.answerme.model.DTOs.QuestionDTO;

import java.util.ArrayList;
import java.util.List;

public class QuestionsListAdapter extends RecyclerView.Adapter<QuestionViewHolder> {

	private List<QuestionDTO> questions = new ArrayList<>();

	public void setQuestions(List<QuestionDTO> tweets) {
		this.questions = tweets;
	}

	public List<QuestionDTO> getQuestions() {
		return questions;
	}

	@Override
	public QuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_results_item, parent, false);
		return new QuestionViewHolder(view);
	}

	@Override
	public void onBindViewHolder(QuestionViewHolder holder, int position) {
		final QuestionDTO question = questions.get(position);
		holder.questionText.setText(question.getQuestion());
	}

	@Override
	public int getItemCount() {
		return questions.size();
	}
}
