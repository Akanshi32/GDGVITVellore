package com.gdgvitvellore.gdgvitvellore.Entity.Members;


import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gdgvitvellore.gdgvitvellore.Entity.model.MemberModel;
import com.gdgvitvellore.gdgvitvellore.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.FirebaseRecyclerAdapter;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ManagementMemberFragment extends Fragment {

    RecyclerView mRecyclerView;
    Firebase mRef;
    ProgressDialog mProgressDialog;
    Typeface custom_font;

    public ManagementMemberFragment() {
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
        mRef=new Firebase("https://gdg-vit-vellore-af543.firebaseio.com/managementmembers");
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycler_view_management_member);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        custom_font = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Montserrat-Regular.ttf");
        showProgressDialog();
        FirebaseRecyclerAdapter<MemberModel,ManagementMemberFragment.MembersViewHolder> adapter=new FirebaseRecyclerAdapter<MemberModel,ManagementMemberFragment.MembersViewHolder>(
                MemberModel.class,
                R.layout.card_member,
                ManagementMemberFragment.MembersViewHolder.class,
                mRef.getRef()
        ) {
            @Override
            protected void populateViewHolder(ManagementMemberFragment.MembersViewHolder membersViewHolder, MemberModel memberModel, int i) {
                membersViewHolder.name.setTypeface(custom_font);
                membersViewHolder.work.setTypeface(custom_font);
                membersViewHolder.githubid.setTypeface(custom_font);
                membersViewHolder.profile_pic.setImageDrawable(null);
                membersViewHolder.name.setText(memberModel.getName());
                membersViewHolder.work.setText(memberModel.getWork());
                membersViewHolder.githubid.setText(memberModel.getGithubid());
                Log.v("From"+"management fragment",memberModel.getProfile_pic());
                ManagementMemberFragment.MembersViewHolder.profile_pic.setImageDrawable(getResources().getDrawable(R.drawable.user_default));
                //Glide.with(getActivity()).load(memberModel.getProfile_pic()).thumbnail(0.4f).error(R.drawable.image_not_found).into(ManagementMemberFragment.MembersViewHolder.profile_pic);
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
        return inflater.inflate(R.layout.fragment_management_member, container, false);
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
