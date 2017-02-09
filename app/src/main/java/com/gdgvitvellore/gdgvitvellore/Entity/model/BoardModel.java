package com.gdgvitvellore.gdgvitvellore.Entity.model;

/**
 * Created by tanmay jha on 29-12-2016.
 */

public class BoardModel {
    public String name,display_pic,position;

    public BoardModel(String display_pic) {

        this.display_pic = display_pic;
    }

    public BoardModel(String display_pic, String name, String position) {
        this.display_pic = display_pic;
        this.name = name;
        this.position = position;
    }

    public BoardModel(){}


    public void setDisplay_pic(String display_pic) {
        this.display_pic = display_pic;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDisplay_pic() {

        return display_pic;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }
}
