package com.example.tanmayjha.gdgvitvellore.Entity.Project;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tanmayjha.gdgvitvellore.R;
import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseRecyclerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectFragment extends Fragment {

    RecyclerView recyclerView;
    Firebase mRef;
    public ProjectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_project, container, false);
    }

    @Override
    public void onStart()
    {
        View view=getView();
        super.onStart();
        mRef=new Firebase("https://gdg-vit-vellore-af543.firebaseio.com/Projects/0");
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view_project);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        FirebaseRecyclerAdapter<String,FaqsViewHolder> adapter=
                new FirebaseRecyclerAdapter<String, FaqsViewHolder>(String.class,
                        android.R.layout.simple_list_item_2,
                        FaqsViewHolder.class,
                        mRef) {
                    @Override
                    protected void populateViewHolder(FaqsViewHolder faqsViewHolder, String s, int i) {
                        faqsViewHolder.mText.setText(s);
                    }
                };
        recyclerView.setAdapter(adapter);
    }

    public static class FaqsViewHolder extends RecyclerView.ViewHolder{
        TextView mText;

        public FaqsViewHolder(View v){
            super(v);
            mText=(TextView)v.findViewById(android.R.id.text1);
            /* TODO: Set Text size and color
            mText.setTextColor(0000000);
            mText.setTextSize(20);
            */
        }
    }

}
