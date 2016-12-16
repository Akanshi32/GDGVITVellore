package com.example.tanmayjha.gdgvitvellore.Entity.Sponsor;


public class TeamMember {

    private String name,desg;
    private int imageResId;

    public TeamMember(){

    }

    public TeamMember(String name,String desg, int imageResId) {
        this.name = name;
        this.desg = desg;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesg() {
        return desg;
    }

    public void setDesg(String desg) {this.desg = desg;}

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }
}
