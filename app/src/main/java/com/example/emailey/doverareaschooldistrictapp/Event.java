package com.example.emailey.doverareaschooldistrictapp;

import android.graphics.Color;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by tsengia on 5/4/2017.
 * This class holds all of the information that is needed by a calendar event.
 * Each event has a start date, end date, color, description, and a title.
 */

class Event {
    private String title = "Default Name";
    private Date date = Calendar.getInstance().getTime(); // The "date" field is actually the start date of the event
    private Date endDate; //ending date of the event
    private String description = "This is an example description for a calendar event.";
    private int eventColor = Color.RED; // This will be the background color of the event bubble when viewed on the calendar

    public String getTitle() {
        return title;
    }

    public int getEventColor() {
        return eventColor;
    }

    public void setEventColor(int color) {
        this.eventColor = color;
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

    public Event(String title, Date date) { // Title and starting date is needed in this constructor
        this.title = title;
        this.date = date;
    }

}
