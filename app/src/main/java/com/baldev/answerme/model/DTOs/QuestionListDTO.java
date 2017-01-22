package com.baldev.answerme.model.DTOs;


import java.util.List;

public class QuestionListDTO {

	List<QuestionDTO> questions;

	public QuestionListDTO() {
		// Default constructor required for calls to DataSnapshot.getValue()
	}

	public List<QuestionDTO> getQuestions() {
		return questions;
	}
}
