package com.baldev.answerme.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.baldev.answerme.R;
import com.baldev.answerme.model.DTOs.QuestionDTO;
import com.baldev.answerme.views.adapters.AnswersListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuestionDetailsActivity extends AppCompatActivity {

	public static final String QUESTION_DTO = "QUESTION_DTO";

	@BindView(R.id.text_question)
	TextView questionText;

	@BindView(R.id.rv_answers)
	RecyclerView answersRecyclerView;

	private QuestionDTO questionDTO;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_question_details);
		ButterKnife.bind(this);
		questionDTO = (QuestionDTO) getIntent().getExtras().getSerializable(QUESTION_DTO);
		if(questionDTO != null){
			this.questionText.setText(questionDTO.getQuestion());
		}
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
		answersRecyclerView.setLayoutManager(linearLayoutManager);
		AnswersListAdapter adapter = new AnswersListAdapter();
		adapter.setAnswers(questionDTO.getAnswers());
		answersRecyclerView.setAdapter(adapter);

	}
}
