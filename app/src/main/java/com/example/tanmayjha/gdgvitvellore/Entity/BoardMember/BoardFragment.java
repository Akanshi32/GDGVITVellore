package com.example.tanmayjha.gdgvitvellore.Entity.BoardMember;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.tanmayjha.gdgvitvellore.Entity.Timeline.TimelineFragment;
import com.example.tanmayjha.gdgvitvellore.Entity.model.BoardModel;
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
public class BoardFragment extends Fragment {

    Firebase mRef;
    RecyclerView mRecyclerView;
    ProgressDialog mProgressDialog;

    public BoardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_board, container, false);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        View view=getView();
        mRef=new Firebase("https://gdg-vit-vellore-af543.firebaseio.com/board");
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycler_board);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        showProgressDialog();
        FirebaseRecyclerAdapter<BoardModel,BoardViewHolder> adapter=new FirebaseRecyclerAdapter<BoardModel,BoardViewHolder>(
                BoardModel.class,
                R.layout.card_board_member,
                BoardFragment.BoardViewHolder.class,
                mRef.getRef()
        ) {
            @Override
            protected void populateViewHolder(BoardFragment.BoardViewHolder boardViewHolder,BoardModel boardModel, int i) {
                boardViewHolder.name.setText(boardModel.getName());
                boardViewHolder.position.setText(boardModel.getPosition());
                Glide.with(getActivity()).load(boardModel.getDisplay_pic()).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.ALL).into(boardViewHolder.display_pic);
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

    public static class BoardViewHolder extends RecyclerView.ViewHolder{
        TextView name,position;
        static CircleImageView display_pic;

        public BoardViewHolder(View v) {
            super(v);
            name=(TextView)v.findViewById(R.id.board_member_name);
            position=(TextView)v.findViewById(R.id.board_member_work);
            display_pic=(CircleImageView)v.findViewById(R.id.board_member_image);
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