package com.example.tanmayjha.gdgvitvellore.Entity.SplashScreen;

import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

import com.example.tanmayjha.gdgvitvellore.Entity.IntroSlider.IntroSliderActivity;
import com.example.tanmayjha.gdgvitvellore.Entity.LogIn.LoginActivity;
import com.example.tanmayjha.gdgvitvellore.R;

public class SplashScreenActivity extends Activity {
    ImageView gdgLeftIcon,gdgRightIcon;
    private final int SPLASH_DISPLAY_DURATION = 3000;
    DisplayMetrics displaymetrics = new DisplayMetrics();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = displaymetrics.widthPixels;
        gdgLeftIcon=(ImageView)findViewById(R.id.gdg_icon_left);
        gdgRightIcon=(ImageView)findViewById(R.id.gdg_icon_right);
        gdgLeftIcon.animate().translationX(width/2).setInterpolator(new AccelerateInterpolator()).setDuration(5000l).start();
        gdgRightIcon.animate().translationX(-width/2).setInterpolator(new AccelerateInterpolator()).setDuration(5000l).start();
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashScreenActivity.this,IntroSliderActivity.class);
                SplashScreenActivity.this.startActivity(mainIntent);
                SplashScreenActivity.this.finish();
            }
        }, SPLASH_DISPLAY_DURATION);
    }
}
