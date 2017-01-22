package com.baldev.answerme.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baldev.answerme.R;

import butterknife.BindView;
import butterknife.ButterKnife;

class QuestionViewHolder extends RecyclerView.ViewHolder {

	@BindView(R.id.item_root_view) ViewGroup rootView;
	@BindView(R.id.text_question) TextView questionText;

	QuestionViewHolder(View itemView) {
		super(itemView);
		ButterKnife.bind(this, itemView);
	}

	ViewGroup getRootView() {
		return this.rootView;
	}
}
