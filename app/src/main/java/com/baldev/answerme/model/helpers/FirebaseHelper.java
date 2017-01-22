package com.baldev.answerme.model.helpers;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseHelper {

	FirebaseDatabase database = FirebaseDatabase.getInstance();
	DatabaseReference myRef = database.getReference("message");
}
