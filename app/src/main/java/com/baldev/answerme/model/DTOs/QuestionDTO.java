package com.baldev.answerme.model.DTOs;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QuestionDTO implements Serializable{

	String id;

	String question;

	String userId;

	List<AnswerDTO> answers;

	public QuestionDTO() {
		// Default constructor required for calls to DataSnapshot.getValue()
	}

	public QuestionDTO(String question, String userId) {
		this.question = question;
		this.userId = userId;
	}


	public QuestionDTO(String question, String userId, List<AnswerDTO> answers) {
		this(question, userId);
		this.answers = answers;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getQuestion() {
		return question;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<AnswerDTO> getAnswers() {
		if(answers == null){
			this.answers = new ArrayList<>();
		}
		return answers;
	}

	public void setAnswers(List<AnswerDTO> answers) {
		this.answers = answers;
	}

	public String getId() {
		return id;
	}
}
