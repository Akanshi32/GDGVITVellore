package com.gdgvitvellore.gdgvitvellore.Entity.Firebase;

import com.firebase.client.Firebase;

/**
 * Created by tanmay jha on 15-10-2016.
 */
public class GDGVITVellore2 extends android.app.Application {

    @Override
    public void onCreate(){
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
