package com.gdgvitvellore.gdgvitvellore.Entity.model;

/**
 * Created by tanmay jha on 13-12-2016.
 */

public class TimelineModel {
    public String timelineEventPic,timelineEventName,timelineEventDate,timelineEventVenue,timelineEventTime;

    public TimelineModel(){
    }

    public TimelineModel(String timelineEventDate, String timelineEventName, String timelineEventPic, String timelineEventTime, String timelineEventVenue) {
        this.timelineEventDate = timelineEventDate;
        this.timelineEventName = timelineEventName;
        this.timelineEventPic = timelineEventPic;
        this.timelineEventTime = timelineEventTime;
        this.timelineEventVenue = timelineEventVenue;
    }

    public void setTimelineEventDate(String timelineEventDate) {
        this.timelineEventDate = timelineEventDate;
    }

    public void setTimelineEventName(String timelineEventName) {
        this.timelineEventName = timelineEventName;
    }

    public void setTimelineEventPic(String timelineEventPic) {
        this.timelineEventPic = timelineEventPic;
    }

    public void setTimelineEventTime(String timelineEventTime) {
        this.timelineEventTime = timelineEventTime;
    }

    public void setTimelineEventVenue(String timelineEventVenue) {
        this.timelineEventVenue = timelineEventVenue;
    }

    public String getTimelineEventDate() {
        return timelineEventDate;
    }

    public String getTimelineEventName() {
        return timelineEventName;
    }

    public String getTimelineEventPic() {
        return timelineEventPic;
    }

    public String getTimelineEventTime() {
        return timelineEventTime;
    }

    public String getTimelineEventVenue() {
        return timelineEventVenue;
    }
}
