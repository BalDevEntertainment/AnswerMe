package com.baldev.answerme.model.helpers;

import android.content.Context;

import com.baldev.answerme.model.DTOs.QuestionDTO;
import com.baldev.answerme.model.helpers.FirebaseDatabaseHelper.FirebaseRepliesListener;
import com.baldev.answerme.model.helpers.FirebaseDatabaseHelper.FirebaseTokenCallback;

import java.util.List;

public class FirebaseManagerImplementation implements FirebaseManager {

	private final FirebaseDatabaseHelper firebaseDatabaseHelper = FirebaseDatabaseHelperImplementation.getInstance();
	private final PushNotificationsManager pushNotificationsManager = VolleyHelperImplementation.getInstance();
	private final Context context;
	private NewReplyListener listener;

	public FirebaseManagerImplementation(final Context context) {
		this.context = context;
	}

	public FirebaseManagerImplementation(final Context context, NewReplyListener newReplyListener) {
		this.context = context;
		this.listener = newReplyListener;
		this.firebaseDatabaseHelper.registerFCMToken();
		this.firebaseDatabaseHelper.registerListenerForReplies(new FirebaseRepliesListener() {
			@Override
			public void onNewReply(String to, String messageBody, long messageTimestamp) {
				listener.onNewReply(null);
			}
		});
	}

	public void askForToken(final TokenCallback callback) {
		this.firebaseDatabaseHelper.askForToken(new FirebaseTokenCallback() {
			@Override
			public void onTokenRetrieved(String string) {
				callback.onTokenRetrieved(string);
			}

			@Override
			public void onError(String errorMsg) {
				callback.onError();
			}
		});
	}

	@Override
	public void addNewQuestion(final String questionText) {
		this.firebaseDatabaseHelper.saveQuestion(new QuestionDTO(questionText, this.firebaseDatabaseHelper.getMyToken()));
	}

	@Override
	public void getQuestions(final Callback callback) {
		this.firebaseDatabaseHelper.getQuestions(callback);
	}

	@Override
	public void getOwnQuestions(Callback callback) {
		this.firebaseDatabaseHelper.getOwnQuestions(callback);
	}

	@Override
	public boolean hasTokenBeenRetrieved() {
		String myToken = this.firebaseDatabaseHelper.getMyToken();
		return myToken != null && !myToken.equals("");
	}

	@Override
	public void invalidateToken(String token) {
		this.firebaseDatabaseHelper.invalidateToken(token);
	}

	@Override
	public void saveQuestionReply(QuestionDTO questionDTO) {
		this.firebaseDatabaseHelper.saveQuestionReply(questionDTO);
	}

	public static void registerToken() {
		FirebaseDatabaseHelper firebaseDatabaseHelper = FirebaseDatabaseHelperImplementation.getInstance();
		firebaseDatabaseHelper.registerFCMToken();
		firebaseDatabaseHelper.notifyTokenRegistration();
	}

	public interface Callback {
		void onQuestionsRetrieved(List<QuestionDTO> questions);
	}

}
