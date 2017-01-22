package com.baldev.answerme.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.baldev.answerme.R;
import com.baldev.answerme.model.DTOs.QuestionDTO;
import com.baldev.answerme.model.helpers.FirebaseManager;
import com.baldev.answerme.model.helpers.FirebaseManagerImplementation;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateQuestionActivity extends AppCompatActivity { // TODO: 22/01/2017 create MVP for class

	@BindView(R.id.edit_text_question)
	EditText questionField;

	@BindView(R.id.button_send)
	Button sendButton;

	FirebaseManager firebaseManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_create_question);
		ButterKnife.bind(this);
		this.firebaseManager = new FirebaseManagerImplementation(this);
	}

	@OnClick(R.id.button_send)
	public void onSendPressed(){
		this.firebaseManager.addNewQuestion(new QuestionDTO(this.questionField.getText().toString()));
		this.finish();
	}
}
