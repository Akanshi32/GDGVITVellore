package com.example.tanmayjha.gdgvitvellore.Entity.Feedback;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.tanmayjha.gdgvitvellore.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedbackFragment extends Fragment {

    Button button;
    Spinner spinnerOption;
    EditText feedback;

    public FeedbackFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feedback, container, false);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        View view=getView();
        button=(Button)view.findViewById(R.id.submit);
        spinnerOption=(Spinner)view.findViewById(R.id.spinner_option);
        feedback=(EditText)view.findViewById(R.id.feedback);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                String subject=String.valueOf(spinnerOption.getSelectedItem());
                Intent email = new Intent(Intent.ACTION_SEND);
                String message=feedback.getText().toString();
                //TODO: ADD GDG OFFICIAL ID

                email.putExtra(Intent.EXTRA_EMAIL,new String[]{"tanmay.jha1@gmail.com"});
                email.putExtra(Intent.EXTRA_SUBJECT, subject);
                email.putExtra(Intent.EXTRA_TEXT, message);
                //need this to prompts email client only
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Choose an Email client :"));
            }
        });
    }
}
