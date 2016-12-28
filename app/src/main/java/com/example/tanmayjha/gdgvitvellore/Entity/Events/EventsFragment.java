package com.example.tanmayjha.gdgvitvellore.Entity.Events;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.tanmayjha.gdgvitvellore.Entity.model.EventModel;
import com.example.tanmayjha.gdgvitvellore.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.FirebaseRecyclerAdapter;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends Fragment {

    Firebase mRef;
    RecyclerView recyclerView;
    ProgressDialog mProgressDialog;

    public EventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_events, container, false);
    }

    @Override
    public void onStart()
    {
        View view=getView();
        super.onStart();
        mRef=new Firebase("https://gdg-vit-vellore-af543.firebaseio.com/events");
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view_event);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        showProgressDialog();
        FirebaseRecyclerAdapter<EventModel,EventViewHolder> adapter=new FirebaseRecyclerAdapter<EventModel, EventViewHolder>(
                EventModel.class,
                R.layout.card_event,
                EventViewHolder.class,
                mRef.getRef()
        ) {
            @Override
            protected void populateViewHolder(EventViewHolder eventViewHolder, EventModel eventModel, int i) {
                eventViewHolder.eventName.setText(eventModel.getEventName());
                eventViewHolder.eventDescription.setText(eventModel.getEventDescription());
                eventViewHolder.eventVenue.setText(eventModel.getEventVenue());
                eventViewHolder.eventDate.setText(eventModel.getEventDate());
                eventViewHolder.eventTime.setText(eventModel.getEventTime());
                Picasso.with(getActivity()).load(EventModel.getEventpic()).into(EventViewHolder.eventpic);
                Glide.with(getActivity()).load(eventModel.getEventpic()).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.ALL).into(EventViewHolder.eventpic);
            }
        };

        recyclerView.setAdapter(adapter);
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

    public static class EventViewHolder extends RecyclerView.ViewHolder{
        TextView eventName,eventDescription,eventVenue,eventDate,eventTime;
        static ImageView eventpic;

        public EventViewHolder(View v){
            super(v);
            eventName=(TextView)v.findViewById(R.id.event_name);
            eventDescription=(TextView)v.findViewById(R.id.event_description);
            eventVenue=(TextView)v.findViewById(R.id.event_venue);
            eventDate=(TextView)v.findViewById(R.id.event_date);
            eventTime=(TextView)v.findViewById(R.id.event_time);
            eventpic=(ImageView)v.findViewById(R.id.event_image);
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
