package com.example.tanmayjha.gdgvitvellore.Entity.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tanmayjha.gdgvitvellore.R;

import com.example.tanmayjha.gdgvitvellore.Entity.AboutUs.AboutUsFragment;
import com.example.tanmayjha.gdgvitvellore.Entity.Feedback.FeedbackFragment;
import com.example.tanmayjha.gdgvitvellore.Entity.Welcome.WelcomeFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String personName="User";
    //TODO: Get a default person url
    String personPhotoUrl="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Intent fromLogin=getIntent();
        //To check null pointer exception
        if(fromLogin.getStringExtra("personName")!= null||!fromLogin.getStringExtra("personName").isEmpty())
            personName=fromLogin.getStringExtra("personName");
        if(fromLogin.getStringExtra("personPhotoUrl")!=null||!fromLogin.getStringExtra("personPhotoUrl").isEmpty())
            personPhotoUrl=fromLogin.getStringExtra("personPhotoUrl");
        TextView Name=(TextView)findViewById(R.id.person_name);
        ImageView personImage=(ImageView)findViewById(R.id.person_image);
        Name.setText(personName);
        //Glide.with(getApplicationContext()).load(personPhotoUrl).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(personImage);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.welcome) {
            WelcomeFragment welcomeFragment=new WelcomeFragment();
            ft.replace(R.id.container,welcomeFragment);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        } else if (id == R.id.events) {

        } else if (id == R.id.projects) {

        } else if (id == R.id.sponsors) {

        } else if (id == R.id.about_us) {
            AboutUsFragment aboutUsFragment=new AboutUsFragment();
            ft.replace(R.id.container,aboutUsFragment);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();

        } else if (id == R.id.contact_us) {

        }
        else if (id == R.id.faqs) {

        }
        else if (id == R.id.feedback) {
            FeedbackFragment feedbackFragment=new FeedbackFragment();
            ft.replace(R.id.container,feedbackFragment);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
