package com.example.tanmayjha.gdgvitvellore.Entity.Notification.Services.Services;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;

import com.example.tanmayjha.gdgvitvellore.Entity.Notification.Services.app.Config;
import com.example.tanmayjha.gdgvitvellore.Entity.Notification.Services.NotificationActivity;
import com.example.tanmayjha.gdgvitvellore.Entity.Notification.Services.utils.NotificationUtils;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by tanmay jha on 31-12-2016.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG=MyFirebaseMessagingService.class.getSimpleName();
    private NotificationUtils notificationUtils;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.e(TAG,"FROM:"+remoteMessage.getFrom());
        if(remoteMessage==null)
            return;

        //check if message contains a notification payload
        if(remoteMessage.getNotification()!=null){
            Log.e(TAG,"Notification body:"+remoteMessage.getNotification().getBody());
            handleNotification(remoteMessage.getNotification().getBody());
        }

        //check if message contains a data payload
        if(remoteMessage.getData().size()>0){
            Log.e(TAG,"data Payload:"+remoteMessage.getData().toString());
            try{
                JSONObject json= new JSONObject(remoteMessage.getData().toString());
                handleDataMessage(json);
            }catch (Exception e)
            {
                Log.e(TAG,"Exception: "+e.getMessage());
            }
        }
    }

    private void handleNotification(String message) {
        if(!NotificationUtils.isAppIsInBackground(getApplicationContext())){
            // app is in foreground,broadcast the push message
            Intent pushNotifaction= new Intent(Config.PUSH_NOTIFICATION);
            pushNotifaction.putExtra("message",message);
            LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotifaction);

            //play notification sound
            NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
            notificationUtils.playNotificationSound();
        }else{
            //if app is in background, then firebase itself handles the notification
        }
    }

    private void handleDataMessage(JSONObject json){
        Log.e(TAG,"push json: "+json.toString());

        try{
            JSONObject data=json.getJSONObject("data");
            String title=data.getString("title");
            String message=data.getString("messaxge");
            boolean isBackground=data.getBoolean("is_background");
            String imageUrl=data.getString("image");
            String timestamp=data.getString("timestamp");
            JSONObject payload=data.getJSONObject("payload");
            Log.e(TAG, "title: " + title);
            Log.e(TAG, "message: " + message);
            Log.e(TAG, "isBackground: " + isBackground);
            Log.e(TAG, "payload: " + payload.toString());
            Log.e(TAG, "imageUrl: " + imageUrl);
            Log.e(TAG, "timestamp: " + timestamp);

            if(!NotificationUtils.isAppIsInBackground(getApplicationContext())){
                //app is in foreground, broadcast the push message
                Intent pushNotification = new Intent(Config.PUSH_NOTIFICATION);
                pushNotification.putExtra("message",message);
                LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);

                //play notification sound
                NotificationUtils notificationUtils =new NotificationUtils(getApplicationContext());
                notificationUtils.playNotificationSound();
            }else{
                //app is in background, show the notification in notification tray
                Intent resultIntent=new Intent(getApplicationContext(),NotificationActivity.class);
                resultIntent.putExtra("message",message);

                //check for image attachment
                if(TextUtils.isEmpty(imageUrl))
                {
                    showNotificationMessage(getApplicationContext(),title,message,timestamp,resultIntent);
                }
                else {
                    // image is present, show notification with image
                    showNotificationMessageWithBigImage(getApplicationContext(), title, message, timestamp, resultIntent, imageUrl);
                }
            }
        }catch(JSONException e){
            Log.e(TAG,"Json Exception: "+e.getMessage());
        }catch (Exception e){
            Log.e(TAG,"Exception:"+e.getMessage());
        }

        /**
         * Showing notification with text only
         */
    }

    private void showNotificationMessage(Context applicationContext, String title, String message, String timestamp, Intent resultIntent) {
        notificationUtils=new NotificationUtils(applicationContext);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title,message,timestamp,resultIntent);
    }

    /**
     * Showing notification with text and image
     */

    private void showNotificationMessageWithBigImage(Context context,String title,String message,String timeStamp, Intent intent, String imageUrl ){
        notificationUtils= new NotificationUtils(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title,message,timeStamp,intent,imageUrl);

    }


}
