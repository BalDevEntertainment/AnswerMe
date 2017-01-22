package com.baldev.answerme.model.helpers;


import android.content.Context;
import android.util.Log;

import com.baldev.answerme.model.DTOs.QuestionDTO;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


@Deprecated
public class FirebaseHelper {

	private static final String TAG = "FirebaseHelper";

	public static void writeQuestion(QuestionDTO questionDTO){
		DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();

		DatabaseReference newQuestion = myRef.child("questions").push();
		newQuestion.setValue(questionDTO);

	}

	public static void getQuestions(Context context, Callback callback){
		FirebaseApp.initializeApp(context);
		DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
		DatabaseReference questions = myRef.child("questions");

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
				Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
			}
		};
		questions.addValueEventListener(postListener);
	}

	public interface Callback {
		void onQuestionsRetrieved(List<QuestionDTO> questions);
	}
}
