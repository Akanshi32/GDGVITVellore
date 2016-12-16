package com.example.tanmayjha.gdgvitvellore.Entity.Sponsor;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tanmayjha.gdgvitvellore.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;



public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.MyViewHolder> {

    private List<TeamMember> teamMemberList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name, desg;
        public CircleImageView image;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.member_name);
            desg = (TextView) view.findViewById(R.id.desg);
            image = (CircleImageView) view.findViewById(R.id.member_image);
        }
    }
    public TeamAdapter(List<TeamMember> teamMemberList){
        this.teamMemberList = teamMemberList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_sponsor,parent,false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        TeamMember teamMember = teamMemberList.get(position);
        holder.name.setText(teamMember.getName());
        holder.desg.setText(teamMember.getDesg());
        holder.image.setImageResource(teamMember.getImageResId());
    }

    @Override
    public int getItemCount() {
        return teamMemberList.size();
    }
}
