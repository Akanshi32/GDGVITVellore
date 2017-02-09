package com.gdgvitvellore.gdgvitvellore.Entity.Project;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gdgvitvellore.gdgvitvellore.Boundary.Interface.RecyclerItemClickListener;
import com.gdgvitvellore.gdgvitvellore.Entity.model.ProjectModel;
import com.gdgvitvellore.gdgvitvellore.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.FirebaseRecyclerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectFragment extends Fragment {

    RecyclerView mRecyclerView;
    Firebase mRef;
    ProgressDialog mProgressDialog;

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
    public void onStart() {
        super.onStart();
        View view = getView();
        mRef = new Firebase("https://gdg-vit-vellore-af543.firebaseio.com/project");
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_project);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        showProgressDialog();
        FirebaseRecyclerAdapter<ProjectModel, ProjectsViewHolder> adapter=new FirebaseRecyclerAdapter<ProjectModel, ProjectsViewHolder>(
                ProjectModel.class,
                R.layout.card_project,
                ProjectsViewHolder.class,
                mRef.getRef()
        ) {
            @Override
            protected void populateViewHolder(ProjectsViewHolder projectsViewHolder, ProjectModel projectModel, int i) {
                Glide.with(getActivity()).load(projectModel.getProjectIcon()).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.ALL).into(ProjectsViewHolder.projectIcon);
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

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(getActivity(),ProjectActivity.class);
                intent.putExtra("position",position);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }

    public static class ProjectsViewHolder extends RecyclerView.ViewHolder{

        static ImageView projectIcon;

        public ProjectsViewHolder(View v) {
            super(v);
            projectIcon=(ImageView)v.findViewById(R.id.project_image);
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
