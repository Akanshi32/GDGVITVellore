package com.example.tanmayjha.gdgvitvellore.Entity.FAQs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.tanmayjha.gdgvitvellore.Entity.Project.ProjectFragment;
import com.example.tanmayjha.gdgvitvellore.Entity.model.FaqsModel;
import com.example.tanmayjha.gdgvitvellore.R;
import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseRecyclerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FAQsFragment extends Fragment {

    Firebase mRef;
    RecyclerView mRecyclerView;

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
        mRef=new Firebase("https://gdg-vit-vellore-af543.firebaseio.com/Faqs");
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycler_view_faq);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerAdapter<FaqsModel,FaqsViewHolder> adapter=new FirebaseRecyclerAdapter<FaqsModel, FaqsViewHolder>(
                FaqsModel.class,
                R.layout.card_faqs,
                FaqsViewHolder.class,
                mRef.getRef()
        ) {
            @Override
            protected void populateViewHolder(FaqsViewHolder faqsViewHolder, FaqsModel faqsModel, int i) {
                faqsViewHolder.question.setText(faqsModel.getQuestion());
                faqsViewHolder.answer.setText(faqsModel.getAnswer());
            }
        };

        mRecyclerView.setAdapter(adapter);

    }

    public static class FaqsViewHolder extends RecyclerView.ViewHolder{

        TextView question,answer;

        public FaqsViewHolder(View v) {
            super(v);
            question=(TextView)v.findViewById(R.id.question);
            answer=(TextView)v.findViewById(R.id.answer);
        }
    }
}
