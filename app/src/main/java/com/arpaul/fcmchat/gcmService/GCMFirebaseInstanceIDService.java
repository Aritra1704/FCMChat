package com.arpaul.fcmchat.gcmService;

import android.util.Log;

import com.arpaul.fcmchat.common.AppPreference;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import static com.google.android.gms.plus.PlusOneDummyView.TAG;

/**
 * Created by Aritra on 20-10-2016.
 */

public class GCMFirebaseInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        Log.i(TAG, "onTokenRefresh called ");
        try {
            // Get updated InstanceID token.
            String refreshedToken = FirebaseInstanceId.getInstance().getToken();
            Log.d(TAG, "Refreshed token: " + refreshedToken);

            new AppPreference(this).saveStringInPreference(AppPreference.GCM_TOKEN, refreshedToken);
            // If you want to send messages to this application instance or
            // manage this apps subscriptions on the server side, send the
            // Instance ID token to your app server.
            sendRegistrationToServer(refreshedToken);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void sendRegistrationToServer(String token){
        Log.i(TAG, "FCM Registration Token: " + token);
    }
}
