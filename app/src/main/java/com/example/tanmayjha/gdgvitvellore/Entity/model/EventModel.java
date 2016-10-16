package com.example.tanmayjha.gdgvitvellore.Entity.model;

/**
 * Created by tanmay jha on 16-10-2016.
 */
public class EventModel {
    public String eventName,eventDescription;

    public EventModel(String eventName, String eventDescription) {
        this.eventName = eventName;
        this.eventDescription = eventDescription;
    }

    public EventModel(){

    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventDescription() {

        return eventDescription;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {

        this.eventName = eventName;
    }
}
