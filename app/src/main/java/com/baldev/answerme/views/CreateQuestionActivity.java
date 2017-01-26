package com.baldev.answerme.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.baldev.answerme.R;
import com.baldev.answerme.model.helpers.FirebaseManager;
import com.baldev.answerme.model.helpers.FirebaseManagerImplementation;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateQuestionActivity extends AnswerMeActivity { // TODO: 22/01/2017 create MVP for class

	@BindView(R.id.edit_text_answer)
	EditText questionField;

	@BindView(R.id.button_send)
	Button sendButton;

	FirebaseManager firebaseManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.firebaseManager = new FirebaseManagerImplementation(this);
	}

	@Override
	protected int getLayoutResourceId() {
		return R.layout.activity_create_question;
	}

	@OnClick(R.id.button_send)
	public void onSendPressed(){
		String questionText = this.questionField.getText().toString();
		this.firebaseManager.addNewQuestion(questionText);
		this.finish();
	}
}
