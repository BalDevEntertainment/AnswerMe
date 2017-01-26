package com.baldev.answerme.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.baldev.answerme.R;
import com.baldev.answerme.model.DTOs.AnswerDTO;
import com.baldev.answerme.model.DTOs.QuestionDTO;
import com.baldev.answerme.model.helpers.FirebaseManager;
import com.baldev.answerme.model.helpers.FirebaseManagerImplementation;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnswerQuestionActivity extends AnswerMeActivity {

	public static final String QUESTION_DTO = "QUESTION_DTO";

	@BindView(R.id.text_question)
	TextView questionText;

	@BindView(R.id.edit_text_answer)
	EditText answerField;

	@BindView(R.id.button_send)
	Button sendButton;

	FirebaseManager firebaseManager;
	private QuestionDTO questionDTO;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.firebaseManager = new FirebaseManagerImplementation(this);
		questionDTO = (QuestionDTO) getIntent().getExtras().getSerializable(QUESTION_DTO);
		if(questionDTO != null){
			this.questionText.setText(questionDTO.getQuestion());
		}
	}

	@Override
	protected int getLayoutResourceId() {
		return R.layout.activity_answer_question;
	}

	@OnClick(R.id.button_send)
	public void onSendPressed() {
		String answerText = this.answerField.getText().toString();
		questionDTO.getAnswers().add(new AnswerDTO(answerText));
		this.firebaseManager.saveQuestionReply(questionDTO);
		this.finish();
	}

}
