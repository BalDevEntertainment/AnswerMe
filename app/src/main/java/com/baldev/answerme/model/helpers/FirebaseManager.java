package com.baldev.answerme.model.helpers;

//Facade for messages


import com.baldev.answerme.model.DTOs.QuestionDTO;
import com.baldev.answerme.model.helpers.FirebaseManagerImplementation.Callback;

public interface FirebaseManager {

	void askForToken(TokenCallback callback);

	void addNewQuestion(QuestionDTO questionDTO);

	void getQuestions(Callback callback);

	boolean hasTokenBeenRetrieved();

	void invalidateToken(String token);

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
