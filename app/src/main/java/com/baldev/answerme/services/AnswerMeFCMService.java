package com.baldev.answerme.services;

import android.app.NotificationManager;
import android.support.v7.app.NotificationCompat;

import com.baldev.answerme.R;
import com.baldev.answerme.model.DTOs.QuestionDTO;
import com.baldev.answerme.model.helpers.FirebaseManager.NewReplyListener;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class AnswerMeFCMService extends FirebaseMessagingService implements NewReplyListener {

	public static final int NEW_MESSAGE_ID = 001;
	public static final String KEY_BODY = "messageBody";

	@Override
	public void onMessageReceived(RemoteMessage remoteMessage) {
		Map<String, String> notificationData = remoteMessage.getData();
		if (notificationData != null) {
			NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
			builder.setContentTitle(this.getString(R.string.notification_new_reply_title));
			builder.setContentText(notificationData.get(KEY_BODY));
			//builder.setSmallIcon(R.drawable.logo_putify);
			builder.setAutoCancel(true);
			builder.setDefaults(NotificationCompat.DEFAULT_SOUND | NotificationCompat.DEFAULT_VIBRATE | NotificationCompat.DEFAULT_LIGHTS);
			//Intent resultIntent = new Intent(this, MessagesActivity.class);
			//resultIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
			//PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
			//builder.setContentIntent(resultPendingIntent);
			NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
			mNotifyMgr.notify(NEW_MESSAGE_ID, builder.build());
		}
	}

	@Override
	public void onNewReply(QuestionDTO message) {

	}
}
