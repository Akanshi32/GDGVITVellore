package com.example.tanmayjha.gdgvitvellore.Entity.Project;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tanmayjha.gdgvitvellore.Entity.model.ProjectModel;
import com.example.tanmayjha.gdgvitvellore.R;
import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseRecyclerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectFragment extends Fragment {

    RecyclerView mRecyclerView;
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
    public void onStart() {
        super.onStart();
        View view = getView();
        mRef = new Firebase("https://gdg-vit-vellore-af543.firebaseio.com/project");
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_project);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerAdapter<ProjectModel, ProjectsViewHolder> adapter=new FirebaseRecyclerAdapter<ProjectModel, ProjectsViewHolder>(
                ProjectModel.class,
                R.layout.card_project,
                ProjectsViewHolder.class,
                mRef.getRef()
        ) {
            @Override
            protected void populateViewHolder(ProjectsViewHolder projectsViewHolder, ProjectModel projectModel, int i) {
                projectsViewHolder.projectName.setText(projectModel.getProjectName());
                projectsViewHolder.projectDescription.setText(projectModel.getProjectDescription());
            }
        };

        mRecyclerView.setAdapter(adapter);
    }

    public static class ProjectsViewHolder extends RecyclerView.ViewHolder{

        TextView projectName,projectDescription;

        public ProjectsViewHolder(View v) {
            super(v);
            projectName=(TextView)v.findViewById(R.id.project_name);
            projectDescription=(TextView)v.findViewById(R.id.project_description);
        }
    }
        
}
