package com.arpaul.fcmchat.gcmService;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Aritra on 24-10-2016.
 */

public class GCMSendAsyncLoader extends AsyncTaskLoader {

    private Context context;
    public GCMSendAsyncLoader(Context context){
        super(context);
        this.context = context;
    }

    @Override
    public Object loadInBackground() {

//        FirebaseMessaging fm = FirebaseMessaging.getInstance();
//        fm.send(new RemoteMessage.Builder(SENDER_ID + "@gcm.googleapis.com")
//                .setMessageId(Integer.toString(msgId.incrementAndGet()))
//                .addData("my_message", "Hello World")
//                .addData("my_action","SAY_HELLO")
//                .build());


        return null;
    }
}
