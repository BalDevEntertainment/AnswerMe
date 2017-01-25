package com.baldev.answerme.model.DTOs;


import java.io.Serializable;

public class AnswerDTO implements Serializable{

	String answer;

	public AnswerDTO() {
	}

	public AnswerDTO(String answer) {
		this.answer = answer;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
