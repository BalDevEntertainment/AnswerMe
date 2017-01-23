package com.baldev.answerme.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.baldev.answerme.R;
import com.baldev.answerme.model.helpers.FirebaseManager;
import com.baldev.answerme.model.helpers.FirebaseManagerImplementation;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnswerQuestionActivity extends AppCompatActivity {

	public static final String QUESTION_TEXT = "questiontext";

	@BindView(R.id.text_question)
	TextView questionText;

	@BindView(R.id.edit_text_answer)
	EditText answerField;

	@BindView(R.id.button_send)
	Button sendButton;

	FirebaseManager firebaseManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_answer_question);
		ButterKnife.bind(this);
		this.firebaseManager = new FirebaseManagerImplementation(this);
		this.questionText.setText(getIntent().getExtras().getString(QUESTION_TEXT));
	}

	@OnClick(R.id.button_send)
	public void onSendPressed() {
		String questionText = this.answerField.getText().toString();
		this.firebaseManager.addNewQuestion(questionText);
		this.finish();
	}
}
