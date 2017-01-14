package com.example.tanmayjha.gdgvitvellore.Entity.Members;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.tanmayjha.gdgvitvellore.Entity.model.MemberModel;
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
public class DesignMemberFragment extends Fragment {

    RecyclerView mRecyclerView;
    Firebase mRef;
    ProgressDialog mProgressDialog;

    public DesignMemberFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void onStart()
    {
        super.onStart();
        View view=getView();
        mRef=new Firebase("https://gdg-vit-vellore-af543.firebaseio.com/designmembers");
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycler_view_design_member);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        showProgressDialog();
        FirebaseRecyclerAdapter<MemberModel,DesignMemberFragment.MembersViewHolder> adapter=new FirebaseRecyclerAdapter<MemberModel,DesignMemberFragment.MembersViewHolder>(
                MemberModel.class,
                R.layout.card_member,
                DesignMemberFragment.MembersViewHolder.class,
                mRef.getRef()
        ) {
            @Override
            protected void populateViewHolder(DesignMemberFragment.MembersViewHolder membersViewHolder, MemberModel memberModel, int i) {
                membersViewHolder.name.setText(memberModel.getName());
                membersViewHolder.work.setText(memberModel.getWork());
                membersViewHolder.githubid.setText(memberModel.getGithubid());
                Glide.with(getActivity()).load(memberModel.getProfile_pic()).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.ALL).into(DesignMemberFragment.MembersViewHolder.profile_pic);
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

    public static class MembersViewHolder extends RecyclerView.ViewHolder{
        TextView name,work,githubid;
        static CircleImageView profile_pic;

        public MembersViewHolder(View v) {
            super(v);
            name=(TextView)v.findViewById(R.id.member_name);
            work=(TextView)v.findViewById(R.id.member_work);
            githubid=(TextView)v.findViewById(R.id.member_github_id);
            profile_pic=(CircleImageView)v.findViewById(R.id.member_image);
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_design_member, container, false);
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
