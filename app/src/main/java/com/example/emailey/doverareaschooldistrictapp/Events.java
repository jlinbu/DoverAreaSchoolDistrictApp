package com.example.emailey.doverareaschooldistrictapp;

import java.util.ArrayList;

/**
 * Created by shunt on 12/6/2017.
 */



public class Events {
    private static ArrayList<Event>mEvents;
    private static ArrayList<String>fEvents;
    private Events(){
        mEvents = new ArrayList<>();
    }
    private void FilteredEvents(){
        fEvents = new ArrayList<>();
    }

    public static void addEvent(Event e){
        if (mEvents == null){
            mEvents = new ArrayList<>();
        }
        mEvents.add(e);
    }

    public ArrayList<Event> getmEvents(){
        if (mEvents == null){
            mEvents = new ArrayList<>();
        }
        return  mEvents;
    }

    public ArrayList<Event> getFilteredArray(String filter, Object data){
        ArrayList<Event> filteredArray = new ArrayList<>();
        if (mEvents == null){
            mEvents = new ArrayList<>();
        }
        for (Event e :mEvents){
            if (e.dictionaryEvent.get(filter).equals(data));
            fEvents.add(data.toString());
        }
        return  filteredArray;
    }
}

