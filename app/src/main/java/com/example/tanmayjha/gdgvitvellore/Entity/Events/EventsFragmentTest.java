package com.example.tanmayjha.gdgvitvellore.Entity.Events;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.tanmayjha.gdgvitvellore.Entity.model.EventModel;
import com.example.tanmayjha.gdgvitvellore.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.FirebaseRecyclerAdapter;
import com.example.tanmayjha.gdgvitvellore.Boundary.Interface.RecyclerItemClickListener;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragmentTest extends Fragment {

    Firebase mRef;
    RecyclerView recyclerView;
    ProgressDialog mProgressDialog;

    public EventsFragmentTest() {
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
                R.layout.card_event_test,
                EventViewHolder.class,
                mRef.getRef()
        ) {
            @Override
            protected void populateViewHolder(EventViewHolder eventViewHolder, EventModel eventModel, int i) {
                eventViewHolder.eventName.setText(eventModel.getEventName());
                /*eventViewHolder.eventDescription.setText(eventModel.getEventDescription());
                eventViewHolder.eventVenue.setText(eventModel.getEventVenue());
                eventViewHolder.eventDate.setText(eventModel.getEventDate());
                eventViewHolder.eventTime.setText(eventModel.getEventTime());*/
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

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(getActivity(),EventActivity.class);
                intent.putExtra("position",position);
                startActivity(intent);

            }

            @Override
            public void onLongItemClick(View view, int position) {
            }
        }));
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder{
        TextView eventName,eventDescription,eventVenue,eventDate,eventTime;
        static ImageView eventpic;

        public EventViewHolder(View v){
            super(v);
            eventName=(TextView)v.findViewById(R.id.event_name);
            /*eventDescription=(TextView)v.findViewById(R.id.event_description);
            eventVenue=(TextView)v.findViewById(R.id.event_venue);
            eventDate=(TextView)v.findViewById(R.id.event_date);
            eventTime=(TextView)v.findViewById(R.id.event_time);*/
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
