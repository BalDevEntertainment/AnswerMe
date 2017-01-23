package com.baldev.answerme.model.helpers;

//Facade for messages


import com.baldev.answerme.model.DTOs.AnswerDTO;
import com.baldev.answerme.model.DTOs.QuestionDTO;
import com.baldev.answerme.model.helpers.FirebaseManagerImplementation.Callback;

public interface FirebaseManager {

	void askForToken(TokenCallback callback);

	void addNewQuestion(String questionText);

	void getQuestions(Callback callback);

	void getOwnQuestions(Callback callback);

	boolean hasTokenBeenRetrieved();

	void invalidateToken(String token);

	void saveQuestionReply(QuestionDTO questionId);

	interface NewReplyListener {
		void onNewReply(QuestionDTO reply);
	}

	interface TokenCallback {
		void onTokenRetrieved(String string);

		void onError();
	}

	interface MessageDeliveryCallback {
		void onInvalidRecipient();
	}
}
