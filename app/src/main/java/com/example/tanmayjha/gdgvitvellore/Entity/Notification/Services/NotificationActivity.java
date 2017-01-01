package com.example.tanmayjha.gdgvitvellore.Entity.Notification.Services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tanmayjha.gdgvitvellore.Entity.Notification.Services.app.Config;
import com.example.tanmayjha.gdgvitvellore.Entity.Notification.Services.utils.NotificationUtils;
import com.example.tanmayjha.gdgvitvellore.R;
import com.google.firebase.messaging.FirebaseMessaging;

public class NotificationActivity extends AppCompatActivity {
    private static final String TAG=NotificationActivity.class.getSimpleName();
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private TextView registrationID,textMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        registrationID=(TextView)findViewById(R.id.txt_reg_id);
        textMessage=(TextView)findViewById(R.id.txt_push_message);

        mRegistrationBroadcastReceiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //checking for type intent filter
                if(intent.getAction().equals(Config.REGISTRATION_COMPLETE)){
                    //gcm successfully registered
                    //now subscribe to global topic to recieve app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);
                    displayFirebaseRegId();
                    } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)){
                    //new push notification is recieved

                    String message=intent.getStringExtra("message");
                    Toast.makeText(getApplicationContext(),"Push notification: "+message,Toast.LENGTH_LONG).show();
                    textMessage.setText(message);
                }
            }
        };
        displayFirebaseRegId();
    }

    //fetches reg id from shared preferences
    //and displays on the screen
    private void displayFirebaseRegId(){
        SharedPreferences pref=getApplicationContext().getSharedPreferences(Config.SHARED_PREF,0);
        String regId=pref.getString("regId",null);

        Log.e(TAG,"Firebase reg id: "+regId);

        if(!TextUtils.isEmpty(regId))
            registrationID.setText("Firebase Reg Id: "+regId);
        else
            registrationID.setText("Firebase Reg Id is not received yet!");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        //register GCM registration complete receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));

        //register new push message receiver
        //by doing this, the activity will be notified each time a new message arrives

        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
             new IntentFilter(Config.PUSH_NOTIFICATION));

        //clear the notification area when the app is opened
        NotificationUtils.clearNotifications(getApplicationContext());
    }

    @Override
    protected void onPause(){
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }
}
