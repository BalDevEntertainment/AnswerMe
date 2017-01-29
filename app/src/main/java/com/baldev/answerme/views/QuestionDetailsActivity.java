package com.baldev.answerme.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.widget.TextView;

import com.baldev.answerme.R;
import com.baldev.answerme.model.DTOs.QuestionDTO;
import com.baldev.answerme.views.adapters.AnswersListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuestionDetailsActivity extends AnswerMeActivity {

	public static final String QUESTION_DTO = "QUESTION_DTO";

	@BindView(R.id.text_question)
	TextView questionText;

	@BindView(R.id.rv_answers)
	RecyclerView answersRecyclerView;

	private QuestionDTO questionDTO;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		questionDTO = (QuestionDTO) getIntent().getExtras().getSerializable(QUESTION_DTO);
		if (questionDTO != null) {
			this.questionText.setText(questionDTO.getQuestion());
		}
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
		answersRecyclerView.setLayoutManager(linearLayoutManager);
		AnswersListAdapter adapter = new AnswersListAdapter();
		adapter.setAnswers(questionDTO.getAnswers());
		answersRecyclerView.setAdapter(adapter);

	}

	@Override
	protected int getLayoutResourceId() {
		return R.layout.activity_question_details;
	}
}
