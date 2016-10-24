package com.arpaul.fcmchat.gcmService;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;

import com.arpaul.fcmchat.R;
import com.arpaul.fcmchat.activity.ChatActivity;
import com.arpaul.fcmchat.common.AppConstants;
import com.arpaul.fcmchat.dataObjects.MessageDO;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import static com.google.android.gms.plus.PlusOneDummyView.TAG;

/**
 * Created by Aritra on 20-10-2016.
 */

public class GCMFirebaseMessagingService extends FirebaseMessagingService {

    private static final String EXTRA_LOCATION = "location";
    public static final int NOTIFICATION_ID = 1;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if(remoteMessage != null){
//            Map<String, String> hashRemoteMessage = remoteMessage.getData();
//            if(hashRemoteMessage != null && hashRemoteMessage.size() > 0){
//                String location = hashRemoteMessage.get(EXTRA_LOCATION);
//
//                Toast.makeText(this, "location: " + location, Toast.LENGTH_LONG).show();
//
//                sendNotification(location);
//            }

            Log.d(TAG, "From: " + remoteMessage.getFrom());

            if (remoteMessage.getData().size() > 0) {
                Log.d(TAG, "Message data payload: " + remoteMessage.getData());

                Map<String, String> hashRemoteMessage = remoteMessage.getData();
                if(hashRemoteMessage != null && hashRemoteMessage.size() > 0){
                    MessageDO objMessage = new MessageDO();
                    objMessage.messageSender = hashRemoteMessage.get(MessageDO.SENDER);
                    objMessage.messageBody = hashRemoteMessage.get(MessageDO.BODY);

                    Intent intent = new Intent(AppConstants.ACTION_REFRESH);
                    intent.putExtra(MessageDO.SENDER, hashRemoteMessage.get(MessageDO.SENDER));
                    intent.putExtra(MessageDO.BODY, hashRemoteMessage.get(MessageDO.BODY));

                    sendBroadcast(intent);
                }
            }

            if (remoteMessage.getNotification() != null) {
                Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());

                String body = remoteMessage.getNotification().getBody();
                String title = remoteMessage.getNotification().getTitle();
                String icon = remoteMessage.getNotification().getIcon();

                if(!TextUtils.isEmpty(body) && !TextUtils.isEmpty(title)){
                    sendNotification(title, body, icon);
                }
            }
        }
    }

    /**
     *  Put the message into a notification and post it.
     *  This is just one simple example of what you might choose to do with a GCM message.
     *
     * @param body The alert message to be posted.
     */
    private void sendNotification(String title, String body, String icon) {
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, ChatActivity.class), 0);

        // Notifications using both a large and a small icon (which yours should!) need the large
        // icon as a bitmap. So we need to create that here from the resource ID, and pass the
        // object along in our notification builder. Generally, you want to use the app icon as the
        // small icon, so that users understand what app is triggering this notification.
        Bitmap largeIcon = BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(largeIcon)
                        .setContentTitle(title)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(body))
                        .setContentText(body)
                        .setPriority(NotificationCompat.PRIORITY_HIGH);
        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }
}
