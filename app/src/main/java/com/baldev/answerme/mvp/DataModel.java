package com.baldev.answerme.mvp;

import com.baldev.answerme.model.DTOs.QuestionDTO;

import java.util.List;

public interface DataModel {

	void storeDataToRetain(List<QuestionDTO> retainedQuestions, String lastSearch);

}
