package com.baldev.answerme.model.DTOs;


public class QuestionDTO {

	String question;

	public QuestionDTO() {
		// Default constructor required for calls to DataSnapshot.getValue()
	}

	public QuestionDTO(String question) {
		this.question = question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getQuestion() {
		return question;
	}
}
