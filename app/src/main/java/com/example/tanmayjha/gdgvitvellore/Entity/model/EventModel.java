package com.example.tanmayjha.gdgvitvellore.Entity.model;

/**
 * Created by tanmay jha on 16-10-2016.
 */
public class EventModel {
    public String eventName,eventDescription,eventVenue,eventDate,eventTime,eventpic;

    public EventModel(String eventDate, String eventDescription, String eventName, String eventpic, String eventTime, String eventVenue) {
        this.eventDate = eventDate;
        this.eventDescription = eventDescription;
        this.eventName = eventName;
        this.eventpic = eventpic;
        this.eventTime = eventTime;
        this.eventVenue = eventVenue;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public void setEventpic(String eventpic) {
        this.eventpic = eventpic;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public void setEventVenue(String eventVenue) {
        this.eventVenue = eventVenue;
    }

    public String getEventDate() {
        return eventDate;
    }

    public String getEventpic() {
        return eventpic;
    }

    public String getEventTime() {
        return eventTime;
    }

    public String getEventVenue() {
        return eventVenue;
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
