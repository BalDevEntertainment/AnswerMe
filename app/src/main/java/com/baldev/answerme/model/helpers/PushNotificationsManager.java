package com.baldev.answerme.model.helpers;

import android.content.Context;

public interface PushNotificationsManager {
	void sendPushNotification(Context context, String to, String body, PushNotificationCallback callback);

	interface PushNotificationCallback{
		void onInvalidRecipient();
	}
}
