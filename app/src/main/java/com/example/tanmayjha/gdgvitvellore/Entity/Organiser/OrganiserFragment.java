package com.example.tanmayjha.gdgvitvellore.Entity.Organiser;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tanmayjha.gdgvitvellore.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrganiserFragment extends Fragment {


    private List<TeamMember> teamMemberList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TeamAdapter teamAdapter;
    private View v;

    public OrganiserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_organiser, container, false);

        recyclerView = (RecyclerView)v.findViewById(R.id.recycler_a);
        teamAdapter = new TeamAdapter(teamMemberList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(teamAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        getData();
        recyclerView.setNestedScrollingEnabled(false);
        return v;
    }

    private void getData(){

        String[] names = {"Nikhil Kasukurthi",
                "Ashwini Purohit",
                "Rishi Raj",
                "Shiv Bidani",
                "Abhinav Das",
                "Saumya Bakshi",
                "Shanya Verma",
                "Shubhanjan Chakrabarty",
                "T Thiyagaraj",
                "Shimona Bordia"};
        String[] desgn = {"President",
                "Vice President(Technical)",
                "Vice President(Technical)",
                "Vice President(Management)",
                "Project Lead(Technical)",
                "Editorial Lead",
                "Operations Lead",
                "Marketing Lead",
                "Design Lead",
                "WTM Lead"};

        String[] drawableId = new String[27];
        for (int i=1;i<=10;i++) {
            drawableId[i] = "y" + i;
        }

        TeamMember teamMember;

        for (int i=0;i<10;i++) {
            int resId = getResources().getIdentifier(drawableId[i+1],"drawable",getActivity().getPackageName());
            teamMember = new TeamMember(names[i],desgn[i],resId);
            teamMemberList.add(teamMember);
        }

    }

}
