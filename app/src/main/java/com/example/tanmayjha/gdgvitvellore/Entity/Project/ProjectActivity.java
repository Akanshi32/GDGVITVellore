package com.example.tanmayjha.gdgvitvellore.Entity.Project;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tanmayjha.gdgvitvellore.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class ProjectActivity extends AppCompatActivity {
    Firebase mRef;
    Intent intent;
    CollapsingToolbarLayout collapsingToolbar;
    String projectName,projectContributor,projectPic,projectDescription;
    TextView projectDescriptionView,projectContributorView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        // Typeface custom_font = Typeface.createFromAsset(getAssets(),"fonts/Montserrat-Regular.ttf");

        //projectDescriptionView.setTypeface(custom_font);

        //projectContributorView.setTypeface(custom_font);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        collapsingToolbar=(CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);
        projectDescriptionView=(TextView)findViewById(R.id.project_content_description);
        projectContributorView=(TextView)findViewById(R.id.project_content_contributor);
        intent=getIntent();
        mRef=new Firebase("https://gdg-vit-vellore-af543.firebaseio.com/project/"+intent.getIntExtra("position",0));
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataSnapshot projectNameDataSnapshot,projectDescriptionDataSnapshot,projectContributorDataSnapshot,projectPicDataSnapshot;
                projectNameDataSnapshot=dataSnapshot.child("/projectName");
                projectDescriptionDataSnapshot=dataSnapshot.child("/projectDescription");
                projectContributorDataSnapshot=dataSnapshot.child("/projectContributer");
                projectPicDataSnapshot=dataSnapshot.child("/projectIcon");
                projectName=projectNameDataSnapshot.getValue(String.class);
                projectDescription=projectDescriptionDataSnapshot.getValue(String.class);
                projectContributor=projectContributorDataSnapshot.getValue(String.class);
                projectPic=projectPicDataSnapshot.getValue(String.class);
                setValues();
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void setValues() {
        collapsingToolbar.setTitle(projectName);
        collapsingToolbar.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        loadEventBackdrop();
        projectDescriptionView.setText(projectDescription);
        projectContributorView.setText(projectContributor);
    }

    private void loadEventBackdrop() {
        final ImageView imageView = (ImageView) findViewById(R.id.project_backdrop);
        Glide.with(this).load(projectPic).fitCenter().into(imageView);
    }
}
