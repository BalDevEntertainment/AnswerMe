package com.baldev.answerme.model.DTOs;


public class QuestionDTO {

	String question;

	String userId;

	public QuestionDTO() {
		// Default constructor required for calls to DataSnapshot.getValue()
	}

	public QuestionDTO(String question, String userId) {
		this.question = question;
		this.userId = userId;
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
}
