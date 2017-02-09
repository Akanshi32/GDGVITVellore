package com.gdgvitvellore.gdgvitvellore.Entity.model;

/**
 * Created by tanmay jha on 13-12-2016.
 */

public class MemberModel {
    public String name,profile_pic,work,githubid;

    public MemberModel()
    {
    }

    public MemberModel(String githubid, String name, String profile_pic, String work) {
        this.githubid = githubid;
        this.name = name;
        this.profile_pic = profile_pic;
        this.work = work;
    }

    public String getGithubid() {
        return githubid;
    }

    public void setGithubid(String githubid) {
        this.githubid = githubid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getName() {
        return name;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public String getWork() {
        return work;
    }
}
