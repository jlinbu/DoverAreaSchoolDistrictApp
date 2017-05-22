package com.example.emailey.doverareaschooldistrictapp;

import android.graphics.Color;

/**
 * Created by tsengia on 5/16/2017
 * The CalendarInfo class is designed to create CalendarInfo objects.
 * CalendarInfo objects are made to hold information about different iCalendars.
 * Information such as url, name, and the color its events will be highlighted with.
 */

public class CalendarInfo {
    private String name = "Default";
    private String url = "http://github.com";
    private int calendarColor = Color.BLACK;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCalendarColor() {
        return calendarColor;
    }

    public void setCalendarColor(int calendarColor) {
        this.calendarColor = calendarColor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*
     * This is the normal constructor, but if you make new constructors, make note that the fields are already initialized o lines
     * 13 to 15.
     */
    public CalendarInfo(String url, int color, String name) {
        this.name = name;
        this.url = url;
        this.calendarColor = color;
    }
}
