package com.example.tanmayjha.gdgvitvellore.Entity.Events;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tanmayjha.gdgvitvellore.Entity.model.EventModel;
import com.example.tanmayjha.gdgvitvellore.R;
import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseRecyclerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends Fragment {

    Firebase mRef;
    RecyclerView recyclerView;

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
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

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
            }
        };

        recyclerView.setAdapter(adapter);
    }

    public static class EventsViewHolder extends RecyclerView.ViewHolder{
        TextView mText;

        public EventsViewHolder(View v){
            super(v);
            mText=(TextView)v.findViewById(android.R.id.text1);
        }
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder{
        TextView eventName,eventDescription;

        public EventViewHolder(View v){
            super(v);
            eventName=(TextView)v.findViewById(R.id.event_name);
            eventDescription=(TextView)v.findViewById(R.id.event_description);
        }
    }

}
