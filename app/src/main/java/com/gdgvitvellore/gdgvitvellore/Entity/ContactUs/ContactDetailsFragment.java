package com.gdgvitvellore.gdgvitvellore.Entity.ContactUs;


import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gdgvitvellore.gdgvitvellore.R;
import com.melnykov.fab.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactDetailsFragment extends Fragment implements View.OnClickListener {

    private TextView content,visitUsText,callUsText,mailUsText;
    private LinearLayout visitUsView,mailUsView,callUsView;
    private FloatingActionButton webFab;
    private LinearLayout contentView;

    public ContactDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_contact_details_test, container, false);

    }

    @Override
    public void onStart()
    {
        View view=getView();
        super.onStart();
        visitUsView=(LinearLayout)view.findViewById(R.id.visit_us);
        mailUsView=(LinearLayout)view.findViewById(R.id.mail_us);
        callUsView=(LinearLayout)view.findViewById(R.id.call_us);
        content=(TextView)view.findViewById(R.id.content);
        visitUsText=(TextView)view.findViewById(R.id.visit_us_text);
        callUsText=(TextView)view.findViewById(R.id.call_us_text);
        mailUsText=(TextView)view.findViewById(R.id.mail_us_text);
        contentView=(LinearLayout)view.findViewById(R.id.content_view);
        contentView.setOnClickListener(this);
        webFab=(FloatingActionButton)view.findViewById(R.id.web_fab);
        webFab.setOnClickListener(this);
        visitUsText.setTextColor(ContextCompat.getColor(getActivity(),R.color.colorPrimary));
        content.setText("VIT Vellore,\nVellore, TN 632014");
        visitUsView.setOnClickListener(this);
        callUsView.setOnClickListener(this);
        mailUsView.setOnClickListener(this);


        Typeface custom_font = Typeface.createFromAsset(getContext().getAssets(),"fonts/Montserrat-Regular.ttf");

        content.setTypeface(custom_font);
        visitUsText.setTypeface(custom_font);
        callUsText.setTypeface(custom_font);
        mailUsText.setTypeface(custom_font);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()== R.id.visit_us){
            visitUsText.setTextColor(ContextCompat.getColor(getActivity(),R.color.colorPrimary));
            callUsText.setTextColor(ContextCompat.getColor(getActivity(),R.color.dgray));
            mailUsText.setTextColor(ContextCompat.getColor(getActivity(),R.color.dgray));
            content.setText("VIT Vellore,\nVellore, TN 632014");
            Animation animation = AnimationUtils.loadAnimation(getActivity(),android.R.anim.fade_in);
            contentView.startAnimation(animation);
        }
        if(v.getId()== R.id.mail_us){
            visitUsText.setTextColor(ContextCompat.getColor(getActivity(),R.color.dgray));
            callUsText.setTextColor(ContextCompat.getColor(getActivity(),R.color.dgray));
            mailUsText.setTextColor(ContextCompat.getColor(getActivity(),R.color.colorPrimary));
            content.setText("gdgvitvellore@gmail.com");
            Animation animation = AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in);
            contentView.startAnimation(animation);
        }
        if(v.getId()== R.id.call_us){
            visitUsText.setTextColor(ContextCompat.getColor(getActivity(),R.color.dgray));
            callUsText.setTextColor(ContextCompat.getColor(getActivity(),R.color.colorPrimary));
            mailUsText.setTextColor(ContextCompat.getColor(getActivity(),R.color.dgray));
            content.setText("0416-2320145");
            Animation animation = AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in);
            contentView.startAnimation(animation);
        }
        if(v.getId()== R.id.web_fab){
            Intent i=new Intent(getActivity(),GdgWebViewActivity.class);
            i.putExtra("title","GDGVIT Website");
            startActivity(i);
        }
        if(v.getId() == R.id.content_view)
        {
            String redirect = content.getText().toString();
            if(redirect == "0416-2320145")
            {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0416-2320145"));
                startActivity(Intent.createChooser(intent, "Call using"));
            }
            if(redirect == "gdgvitvellore@gmail.com")
            {
                String[] addresses={"gdgvitvellore@gmail.com"};
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, addresses);
                emailIntent.setType("text/plain");
                startActivity(Intent.createChooser(emailIntent, "Send your email in:"));
            }

            if(redirect == "VIT Vellore,\n" +
                    "Vellore, TN 632014")
            {
                String mLat="12.969129";
                String mLong="79.155787";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=" + mLat + "," + mLong));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(Intent.createChooser(intent, "Open using:"));
            }
        }
    }

}
