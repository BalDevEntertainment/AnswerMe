package com.baldev.answerme.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baldev.answerme.R;
import com.baldev.answerme.model.DTOs.AnswerDTO;

import java.util.ArrayList;
import java.util.List;

public class AnswersListAdapter extends RecyclerView.Adapter<AnswerViewHolder> {

	private List<AnswerDTO> answers = new ArrayList<>();
	private AnswerAdapterListener listener;

	public void setAnswers(List<AnswerDTO> answers) {
		this.answers = answers;
	}

	public List<AnswerDTO> getAnswers() {
		return answers;
	}

	@Override
	public AnswerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_results_item, parent, false);
		return new AnswerViewHolder(view);
	}

	@Override
	public void onBindViewHolder(AnswerViewHolder holder, int position) {
		final AnswerDTO answerDTO = answers.get(position);
		holder.answerText.setText(answerDTO.getAnswer());
		holder.getRootView().setOnClickListener(view -> {
			listener.onAnswerClicked(answerDTO);
		});
	}

	@Override
	public int getItemCount() {
		return answers.size();
	}

	public void setListener(AnswerAdapterListener listener) {
		this.listener = listener;
	}

	public interface AnswerAdapterListener {
		void onAnswerClicked(AnswerDTO dto);
	}
}
