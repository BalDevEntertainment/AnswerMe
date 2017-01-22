package com.baldev.answerme.model.helpers;

import com.baldev.answerme.BuildConfig;
import com.baldev.answerme.model.DTOs.QuestionDTO;
import com.baldev.answerme.model.helpers.FirebaseManagerImplementation.Callback;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;

public class FirebaseDatabaseHelperImplementation implements FirebaseDatabaseHelper {
	private static final String KEY_DATABASE = BuildConfig.DATABASE;
	private static final String KEY_USERS = "users";
	private static final String KEY_QUESTIONS = "questions";
	private static final String KEY_TOKEN = "firebaseToken";
	private static final String KEY_QUESTION = "message";
	private static FirebaseDatabaseHelperImplementation ourInstance = new FirebaseDatabaseHelperImplementation();
	private FirebaseDatabase firebaseDatabase;
	private DatabaseReference database;
	private DatabaseReference usersReference;
	private DatabaseReference questionsReference;
	private DatabaseReference myMessagesReference; //TODO see how to avoid null
	private String myToken;
	private FirebaseTokenCallback tokenCallback;

	public static FirebaseDatabaseHelper getInstance() {
		return ourInstance;
	}

	private FirebaseDatabaseHelperImplementation() {
		this.myToken = FirebaseInstanceId.getInstance().getToken();
	}

	@Override
	public void registerFCMToken() {
		this.myToken = FirebaseInstanceId.getInstance().getToken();
		this.setupReferences();
	}

	@Override
	public void notifyTokenRegistration() {
		if (tokenCallback != null) {
			tokenCallback.onTokenRetrieved(this.myToken);
		}
	}

	@Override
	public void askForToken(FirebaseTokenCallback callback) {
		this.tokenCallback = callback;
		FirebaseInstanceId.getInstance().getToken();
	}

	@Override
	public String getMyToken() {
		return myToken;
	}

	@Override
	public void saveQuestion(QuestionDTO questionDTO) {
		DatabaseReference newQuestion = questionsReference.push();
		newQuestion.setValue(questionDTO);
	}

	@Override
	public void registerListenerForReplies(FirebaseRepliesListener listener) {
		this.myMessagesReference.addChildEventListener(new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot dataSnapshot, String s) {
				listener.onNewReply(null, null, 0);
			}

			@Override
			public void onChildChanged(DataSnapshot dataSnapshot, String s) {

			}

			@Override
			public void onChildRemoved(DataSnapshot dataSnapshot) {

			}

			@Override
			public void onChildMoved(DataSnapshot dataSnapshot, String s) {

			}

			@Override
			public void onCancelled(DatabaseError databaseError) {

			}
		});
	}

	@Override
	public void invalidateToken(String token) {
		DatabaseReference userReference = this.usersReference.child(token);
		if (userReference != null) {
			userReference.removeValue();
		}
	}

	@Override
	public void getQuestions(Callback callback) {
		ValueEventListener postListener = new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				ArrayList<QuestionDTO> result = new ArrayList<>();
				for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
					QuestionDTO post = postSnapshot.getValue(QuestionDTO.class);
					result.add(post);
				}
				callback.onQuestionsRetrieved(result);
			}

			@Override
			public void onCancelled(DatabaseError databaseError) {
			}
		};
		questionsReference.addValueEventListener(postListener);
	}

	private void setupReferences() {
		if (this.firebaseDatabase == null) {
			this.firebaseDatabase = FirebaseDatabase.getInstance();
			this.firebaseDatabase.setPersistenceEnabled(true);
		}
		this.database = this.firebaseDatabase.getReference(KEY_DATABASE);
		this.usersReference = this.database.child(KEY_USERS);
		this.questionsReference = this.database.child(KEY_QUESTIONS);
		if (this.myToken != null && !this.myToken.equals("")) {
			this.myMessagesReference = this.questionsReference.child(myToken);
			DatabaseReference userToken = this.usersReference.child(myToken);
			userToken.child(KEY_TOKEN).setValue(myToken);
		}
	}

}
