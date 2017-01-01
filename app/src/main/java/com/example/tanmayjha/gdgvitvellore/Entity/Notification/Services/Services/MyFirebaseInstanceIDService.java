package com.example.tanmayjha.gdgvitvellore.Entity.Notification.Services.Services;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.tanmayjha.gdgvitvellore.Entity.Notification.Services.app.Config;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by tanmay jha on 31-12-2016.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG= MyFirebaseInstanceIDService.class.getSimpleName();

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken= FirebaseInstanceId.getInstance().getToken();
        
        //Saving reg id to shared preference
        storeRegIdInPref(refreshedToken);

        //seding reg id to the server
        sendRegistrationToServer(refreshedToken);

        //Notify UI that registration has completed,so the progress indicator can be hidden
        Intent registrationComplete=new Intent(Config.REGISTRATION_COMPLETE);
        registrationComplete.putExtra("token",refreshedToken);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }

    private void sendRegistrationToServer(final String token){
        Log.e(TAG,"sendRegistrationToServer:"+token);
    }

    private void storeRegIdInPref(String token) {
        SharedPreferences pref=getApplicationContext().getSharedPreferences(Config.SHARED_PREF,0);
        SharedPreferences.Editor editor=pref.edit();
        editor.putString("regId",token);
        editor.commit();
    }
}
