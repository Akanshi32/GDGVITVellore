package com.example.tanmayjha.gdgvitvellore.Entity.FAQs;


import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tanmayjha.gdgvitvellore.Entity.model.FaqsModel;
import com.example.tanmayjha.gdgvitvellore.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.FirebaseRecyclerAdapter;


public class FAQsFragment extends Fragment {

    Firebase mRef;
    RecyclerView mRecyclerView;
    ProgressDialog mProgressDialog;
    Typeface custom_font;

    public FAQsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_faqs, container, false);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        View view=getView();
        mRef=new Firebase("https://gdg-vit-vellore-af543.firebaseio.com/faqs");
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycler_view_faq);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        custom_font = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Montserrat-Regular.ttf");
        showProgressDialog();
        FirebaseRecyclerAdapter<FaqsModel,FaqsViewHolder> adapter=new FirebaseRecyclerAdapter<FaqsModel, FaqsViewHolder>(
                FaqsModel.class,
                R.layout.card_faqs,
                FaqsViewHolder.class,
                mRef.getRef()
        ) {
            @Override
            protected void populateViewHolder(FaqsViewHolder faqsViewHolder, FaqsModel faqsModel, int i) {
                faqsViewHolder.question.setTypeface(custom_font);
                faqsViewHolder.answer.setTypeface(custom_font);
                faqsViewHolder.question.setText(faqsModel.getQuestion());
                faqsViewHolder.answer.setText(faqsModel.getAnswer());
            }
        };

        mRecyclerView.setAdapter(adapter);
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                hideProgressDialog();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    public static class FaqsViewHolder extends RecyclerView.ViewHolder{

        TextView question,answer;

        public FaqsViewHolder(View v) {
            super(v);
            question=(TextView)v.findViewById(R.id.question);
            answer=(TextView)v.findViewById(R.id.answer);
        }
    }

    void showProgressDialog(){
        if(mProgressDialog==null){
            mProgressDialog=new ProgressDialog(getActivity());
            mProgressDialog.setMessage("Loading");
            mProgressDialog.setIndeterminate(true);
        }
        mProgressDialog.show();
    }

    private void hideProgressDialog(){
        if(mProgressDialog!=null && mProgressDialog.isShowing()){
            mProgressDialog.hide();
        }
    }
}

