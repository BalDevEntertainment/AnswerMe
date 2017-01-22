package com.baldev.answerme.model.helpers;


import com.baldev.answerme.model.DTOs.QuestionDTO;
import com.baldev.answerme.model.helpers.FirebaseManagerImplementation.Callback;

public interface FirebaseDatabaseHelper {
	void registerFCMToken();

	void notifyTokenRegistration();

	String getMyToken();

	void saveQuestion(QuestionDTO questionDTO);

	void registerListenerForReplies(FirebaseRepliesListener listener);

	void askForToken(FirebaseTokenCallback callback);

	void invalidateToken(String token);

	void getQuestions(Callback callback);

	interface FirebaseTokenCallback {
		void onTokenRetrieved(String string);

		void onError(String errorMsg);
	}

	interface FirebaseRepliesListener {
		void onNewReply(String to, String messageBody, long messageTimestamp);
	}

}
