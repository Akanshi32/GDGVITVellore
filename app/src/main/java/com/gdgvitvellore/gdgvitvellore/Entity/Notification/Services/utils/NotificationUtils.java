package com.gdgvitvellore.gdgvitvellore.Entity.Notification.Services.utils;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;

import com.gdgvitvellore.gdgvitvellore.Entity.Notification.Services.app.Config;
import com.gdgvitvellore.gdgvitvellore.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by tanmay jha on 20-12-2016.
 */

public class NotificationUtils {

    private static String TAG=NotificationUtils.class.getSimpleName();

    private Context mContext;

    public NotificationUtils(Context mContext){
        this.mContext=mContext;
    }

    public void showNotificationMessage(String title, String message, String timeStamp, Intent intent) {
        //I guess this is member function overloading
        //Yes, this is function overloading. Its basically, if there is no imageurl passed then the first function is called which
        //calls the sencond function with empty image url otherwise 2nd function is called.
        showNotificationMessage(title, message, timeStamp, intent, null);
    }

    public void showNotificationMessage(final String title,final String message,final String timeStamp,Intent intent,String imageurl){
        //checks for empty push message
        if(TextUtils.isEmpty(message))
            return;

        //notification icon
        final int icon = R.mipmap.ic_launcher;

        //  FLAG_ACTIVITY_CLEAR_TOP: If set, and the activity being launched is
        // already running in the current task,
        // then instead of launching a new instance of that activity,
        // all of the other activities on top of it will be closed and
        // this Intent will be delivered to the (now on top) old activity as a new Intent
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
        final PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        mContext,
                        0,
                        intent,
                        PendingIntent.FLAG_CANCEL_CURRENT
                );
        /*
        this (context) : This is the context in which the PendingIntent starts the activity
        requestCode : “1” is the private request code for the sender.
        Then we can do various things like cancelling the pending intent with cancel(), etc.
        Using it later with the same method again will get back the same pending intent.
        intent : Explicit intent object of the activity to be launched
        flag : There can be many flags used here for eg. FLAG_UPDATE_CURRENT.
        This one states that if a previous PendingIntent already exists,
        then the current one will update it with the latest intent.
        There are many other flags like FLAG_CANCEL_CURRENT that we used here. etc.
         */

        final NotificationCompat.Builder mBuilder =new NotificationCompat.Builder(mContext);
        final Uri alarmSound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE+"://"+mContext.getPackageName()+"/raw/notification");
        //CHECK THIS. It may not work because here our location of package is different from normal case.
        //This will work fine because mContext.getPackageName() returns com.example.tanmayjha.gdgvitvellore from anywhere

        if(!TextUtils.isEmpty(imageurl)) {
            if (imageurl != null && imageurl.length() > 4 && Patterns.WEB_URL.matcher(imageurl).matches()) {
                //Patterns.WEB_URL.matcher(url).matches(), Checks only if url has valid syntax, i.e url like: 000:/some.url will be not valid.
                Bitmap bitmap = getBitmapFromURL(imageurl);
                Log.v("Notification Utils","Check 5");
                if (bitmap != null) {
                    Log.v("Notification Utils","Check 6");
                    showBigNotification(bitmap, mBuilder, icon, title, message, timeStamp, resultPendingIntent, alarmSound);
                } else {
                    Log.v("Notification Utils","Check 7");
                    showSmallNotification(mBuilder, icon, title, message, timeStamp, resultPendingIntent, alarmSound);
                }
            }
            }
        else {
                showSmallNotification(mBuilder, icon, title, message, timeStamp, resultPendingIntent, alarmSound);
                playNotificationSound();
            }
    }

    private void showBigNotification(Bitmap bitmap, NotificationCompat.Builder mBuilder, int icon, String title, String message, String timeStamp, PendingIntent resultPendingIntent, Uri alarmSound) {
        NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();
        bigPictureStyle.setBigContentTitle(title);
        bigPictureStyle.setSummaryText(Html.fromHtml(message).toString());
        bigPictureStyle.bigPicture(bitmap);
        Notification notification;
        notification=mBuilder.setSmallIcon(icon).setTicker(title).setWhen(0)
                .setAutoCancel(true)
                .setContentTitle(title)
                .setContentIntent(resultPendingIntent)
                .setSound(alarmSound)
                //Notification text is not completely showing when viewed as a whole.
                .setStyle(bigPictureStyle)
                .setWhen(getTimeMilliSec(timeStamp))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(),icon))
                .setContentText(message)
                .build();
        NotificationManager notificationManager=(NotificationManager)mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(Config.NOTIFICATION_ID_BIG_IMAGE,notification);
    }

    private void showSmallNotification(NotificationCompat.Builder mBuilder, int icon, String title, String message, String timeStamp, PendingIntent resultPendingIntent, Uri alarmSound) {
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.addLine(message);
        Notification notification;
                //In the notification builder, methods have been called agained and again. Don't know why
        notification=mBuilder.setSmallIcon(icon).setTicker(title).setWhen(0)
                //setTicker: It is the top notification which shows on status bar when is notification initially arrives.
                //eg. when a text message comes then its message is shown in by part on status bar.
                .setAutoCancel(true)
                //setAutoCancel: Setting this flag will make it so the notification is automatically canceled when the user clicks it in the panel.
                .setContentTitle(title)
                //setContentTitle: Set the text (first row) of the notification, in a standard notification.
                .setContentIntent(resultPendingIntent)/*When the user click on notification
                then this is called and a corresponding activity to the intent opens*/
                .setSound(alarmSound)
                .setStyle(inboxStyle)
                .setWhen(getTimeMilliSec(timeStamp))
                //setWhen: Set the time that the event occurred. Notifications in the panel are sorted by this time.
                //idk why they have chosen getTimeMilliSec type name, its just returning the time i guess from the code.
                //maybe all are converted to millisecs
                .setSmallIcon(R.mipmap.ic_launcher)
                //Set the small icon to use in the notification layouts.
                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(),icon))
                //BitmapFactory.decodeResource(mContext.getResources(),icon)) Here icon is the id of launcher icon. Basically i guess it converts into Bitmap image
                .setContentText(message)
                //setContentText: Set the text (second row) of the notification, in a standard notification.
                .build();
                //build(): Combine all of the options that have been set and return a new Notification object.

                //doubt: setAutoCancel will remove the notification when it is clicked where as
                //setContentIntent will go to activity mentioned in the intent when clicked. How can both be true at same time
        //you pass the Notification object to the system by calling NotificationManager.notify() to send your notification.
        NotificationManager notificationManger=(NotificationManager)mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        // notificationID allows you to update the notification later on.
        notificationManger.notify(Config.NOTIFICATION_ID,notification);
    }

    //public void playNotification sound
    public void playNotificationSound() {
        try {
            Uri alarmSound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + mContext.getPackageName() + "/raw/notification");
            Ringtone r = RingtoneManager.getRingtone(mContext, alarmSound);
            r.play();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Downloading, push notification image before displaying it in the notification tray
     */

    public Bitmap getBitmapFromURL(String strURL) {
        try{
            URL url=new URL(strURL);
            HttpURLConnection connection=(HttpURLConnection)url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input=connection.getInputStream();
            Bitmap mybitmap= BitmapFactory.decodeStream(input);
            return mybitmap;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static long getTimeMilliSec(String timeStamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = format.parse(timeStamp);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Method checks if the app is in background or not
     */

    public static boolean isAppIsInBackground(Context context){
        boolean isInBackground=true;
        ActivityManager am=(ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.KITKAT_WATCH){
            List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
            for(ActivityManager.RunningAppProcessInfo processInfo:runningProcesses){
                if(processInfo.importance==ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND){
                    for(String activeProcess : processInfo.pkgList){
                        if(activeProcess.equals(context.getPackageName())){
                            isInBackground=false;
                        }
                    }
                }
            }
        }else{
            List<ActivityManager.RunningTaskInfo> taskInfo=am.getRunningTasks(1);
            ComponentName componentInfo=taskInfo.get(0).topActivity;
            if(componentInfo.getPackageName().equals(context.getPackageName())){
                isInBackground=false;
            }
        }
        return isInBackground;
    }

    public static void clearNotifications(Context context){
        NotificationManager notificationManager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
    }

}
