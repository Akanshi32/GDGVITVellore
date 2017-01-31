package com.example.tanmayjha.gdgvitvellore.Entity.Timeline;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.tanmayjha.gdgvitvellore.Entity.model.TimelineModel;
import com.example.tanmayjha.gdgvitvellore.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.FirebaseRecyclerAdapter;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimelineFragment extends Fragment {
    Firebase mRef;
    RecyclerView mRecyclerView;
    ProgressDialog mProgressDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timeline, container, false);
    }
    public TimelineFragment() {
        // Required empty public constructor
    }
    @Override
    public void onStart()
    {
        super.onStart();
        View view=getView();

        mRef=new Firebase("https://gdg-vit-vellore-af543.firebaseio.com/timeline");
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycler_view_timeline);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        showProgressDialog();
        FirebaseRecyclerAdapter<TimelineModel,TimelineViewHolder> adapter=new FirebaseRecyclerAdapter<TimelineModel,TimelineViewHolder>(
                TimelineModel.class,
                R.layout.card_timeline,
                TimelineViewHolder.class,
                mRef.getRef()
        ) {
            @Override
            protected void populateViewHolder(TimelineViewHolder timelineViewHolder, TimelineModel timelineModel, int i) {
                timelineViewHolder.timelineEventName.setText(timelineModel.getTimelineEventName());
                timelineViewHolder.timelineEventVenue.setText(timelineModel.getTimelineEventVenue());
                timelineViewHolder.timelineEventDate.setText(timelineModel.getTimelineEventDate());
                timelineViewHolder.timelineEventTime.setText(timelineModel.getTimelineEventTime());
                Glide.with(getActivity()).load(timelineModel.getTimelineEventPic()).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.ALL).into(TimelineViewHolder.timelineEventPic);
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

    public static class TimelineViewHolder extends RecyclerView.ViewHolder{
        TextView timelineEventName,timelineEventDate,timelineEventVenue,timelineEventTime;
        static CircleImageView timelineEventPic;

        public TimelineViewHolder(View v) {
            super(v);
            timelineEventName=(TextView)v.findViewById(R.id.timeline_event_name);
            timelineEventDate=(TextView)v.findViewById(R.id.timeline_event_date);
            timelineEventTime=(TextView)v.findViewById(R.id.timeline_event_time);
            timelineEventVenue=(TextView)v.findViewById(R.id.timeline_event_venue);
            timelineEventPic=(CircleImageView)v.findViewById(R.id.timeline_event_image);
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

//TODO: Try nested scroll view instead of recycler view in fragments having scrolling problem.
//TODO: Nested Fragment not working Still not working :/
//TODO: make real use of splash screen
//TODO: Put share option when and where as required
//TODO: Try different color for color primary primary and stuff. 500 for color primary 700 for dark and 300 for secondary