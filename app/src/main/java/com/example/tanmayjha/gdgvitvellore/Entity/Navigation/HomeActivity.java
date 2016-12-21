package com.example.tanmayjha.gdgvitvellore.Entity.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.tanmayjha.gdgvitvellore.Entity.Events.EventsFragment;
import com.example.tanmayjha.gdgvitvellore.Entity.FAQs.FAQsFragment;
import com.example.tanmayjha.gdgvitvellore.Entity.LogIn.LoginActivity;
import com.example.tanmayjha.gdgvitvellore.Entity.Members.TabbedMemberFragment;
import com.example.tanmayjha.gdgvitvellore.Entity.Project.ProjectFragment;
import com.example.tanmayjha.gdgvitvellore.Entity.Organiser.OrganiserFragment;
import com.example.tanmayjha.gdgvitvellore.Entity.Timeline.TimelineFragment;
import com.example.tanmayjha.gdgvitvellore.R;

import com.example.tanmayjha.gdgvitvellore.Entity.AboutUs.AboutUsFragment;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,GoogleApiClient.OnConnectionFailedListener{
    String personName="User";
    GoogleApiClient mGoogleApiClient;
    String TAG=getClass().getSimpleName();
    String personPhotoUrl;
    FragmentTransaction ft=getSupportFragmentManager().beginTransaction();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TimelineFragment timelineFragment=new TimelineFragment();
        ft.replace(R.id.container,timelineFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();

        Intent fromLogin=getIntent();
        personName=fromLogin.getStringExtra("personName");
        personPhotoUrl=fromLogin.getStringExtra("personPhotoUrl");
        Log.v("Person's name on MA",personName);
        View hView =  navigationView.getHeaderView(0);
        TextView name=(TextView)hView.findViewById(R.id.person_name);
        CircleImageView personImage=(CircleImageView)hView.findViewById(R.id.person_image);
        name.setText(personName);
        Glide.with(getApplicationContext()).load(personPhotoUrl).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.ALL).into(personImage);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        String title="Timeline";
        getSupportActionBar().setTitle(title);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        mGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
        OptionalPendingResult<GoogleSignInResult> opr =
                Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);

        if (opr.isDone()) {
            // Users cached credentials are valid, GoogleSignInResult containing ID token
            // is available immediately. This likely means the current ID token is already
            // fresh and can be sent to your server.
            GoogleSignInResult result = opr.get();
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently and get a valid
            // ID token. Cross-device single sign on will occur in this branch.
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult result) {
                }
            });
        }

        // Timeline code starts here onwards.!
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();

            Fragment f =getSupportFragmentManager().findFragmentById(R.id.container);
            if(f instanceof TimelineFragment)
            {
                finishAffinity();
            }
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
        // Handle navigation view item clicks here.
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        String title="";
        int id = item.getItemId();
        if (id == R.id.timeline) {
            TimelineFragment timelineFragment=new TimelineFragment();
            ft.replace(R.id.container,timelineFragment);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
            title="Timeline";
        } else if (id == R.id.events) {
            EventsFragment eventsFragment=new EventsFragment();
            ft.replace(R.id.container,eventsFragment);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
            title="Events";
        } else if (id == R.id.projects) {
            ProjectFragment projectFragment=new ProjectFragment();
            ft.replace(R.id.container,projectFragment);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
            title="Projects";
        } else if (id == R.id.members) {
            TabbedMemberFragment tabbedMemberFragment=new TabbedMemberFragment();
            ft.replace(R.id.container, tabbedMemberFragment);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
            title="Members";
        } else if (id == R.id.organiser) {
            OrganiserFragment organiserFragment =new OrganiserFragment();
            ft.replace(R.id.container, organiserFragment);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
            title="The Board";
        } else if (id == R.id.about_us) {
            AboutUsFragment aboutUsFragment=new AboutUsFragment();
            ft.replace(R.id.container,aboutUsFragment);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
            title="About Us";
        } else if (id == R.id.contact_us) {
            title="Contact Us";
        }
        else if (id == R.id.faqs) {
            FAQsFragment faqsFragment=new FAQsFragment();
            ft.replace(R.id.container,faqsFragment);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
            title="FAQs";
        }
        /*else if (id == R.id.feedback) {
            FeedbackFragment feedbackFragment=new FeedbackFragment();
            ft.replace(R.id.container,feedbackFragment);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
            title="Feedback";
        }
        */
        else if (id == R.id.sign_out) {
            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                    new ResultCallback<Status>() {
                        @Override
                        public void onResult(@NonNull Status status) {
                        }
                    }
            );
            Intent intent=new Intent(this,LoginActivity.class);
            startActivity(intent);
            }
        getSupportActionBar().setTitle(title);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed: " + connectionResult);
    }
}
