package com.example.emailey.doverareaschooldistrictapp;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by tsengia on 5/4/2017.
 */

class Event {
    private String title = "Default Name";
    private Date date = Calendar.getInstance().getTime();
    private Date endDate;
    private String description = "This is an example description for a calendar event.";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date ending) {
        this.endDate = ending;
    }

    public Event(String title, Date date) {
        this.title = title;
        this.date = date;
    }

}
