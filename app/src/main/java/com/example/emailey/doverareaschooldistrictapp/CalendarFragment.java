package com.example.emailey.doverareaschooldistrictapp;

import android.app.Fragment;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CalendarView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by tsengia on 5/5/2017.
 */

public class CalendarFragment extends Fragment {

    private CalendarView calendarView;
    private ListView eventsListView;
    private EventAdapter adapter;

    private List<Event> eventsList = new ArrayList<Event>(); // This list holds all of the Calendar events
    private List<Event> currentEventsList = new ArrayList<Event>(); // This list holds all of the Calendar events for the currently selected date
    private Date selectedDate = Calendar.getInstance().getTime();
    private Calendar calendar = Calendar.getInstance();
    public final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");

    public void refreshViewEvents() {
        currentEventsList.clear();
        String date = DATE_FORMAT.format(selectedDate);

        for(Event e : eventsList) {
            if(date.equals(DATE_FORMAT.format(e.getDate()))) {
                currentEventsList.add(e);
            }
        }
        eventsListView.invalidateViews();
    }

    public void refreshDataEvents() { // This method will use the iCal4J library to refresh the list of events we have.

    }

    private class onCalendarDateChanged implements CalendarView.OnDateChangeListener {
        @Override
        public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) { // This is called when the user selects a new date
            Calendar c = Calendar.getInstance(); // Here we are
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            selectedDate = c.getTime();
            refreshViewEvents();
        }
    }

    private class EventAdapter extends BaseAdapter implements ListAdapter { // Setting the list adapter for the Events List
        private LayoutInflater inflater;
            @Override
            public boolean areAllItemsEnabled() {
                return false;
            }

            @Override
            public boolean isEnabled(int position) {
                return true;
            }

            @Override
            public void registerDataSetObserver(DataSetObserver observer) {

            }

            @Override
            public void unregisterDataSetObserver(DataSetObserver observer) {

            }

            @Override
            public int getCount() {
                return currentEventsList.size();
            }

            @Override
            public Object getItem(int position) {
                return currentEventsList.get(position);
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View root = inflater.inflate(R.layout.event_bubble, parent, false);
                ((TextView) root.findViewById(R.id.event_bubble_title)).setText(currentEventsList.get(position).getTitle());
                return root;
            }

            @Override
            public int getItemViewType(int position) {
                return 0;
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public boolean isEmpty() {
                return currentEventsList.size() == 0;
            }

            public EventAdapter(LayoutInflater inf) {
                this.inflater = inf;
            }
    }

    @Override
    public void onStart() {
        super.onStart();
        refreshDataEvents();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.calendar_fragment, container, false);

        eventsList.add(new Event("Testing", Calendar.getInstance().getTime())); // This is a sample event that is set to the current date.

        calendarView = (CalendarView) root.findViewById(R.id.calendarView); // Get the calendar view
        calendarView.setDate(Calendar.getInstance().getTime().getTime()); // Set the calendar view to today's date

        calendarView.setOnDateChangeListener(new CalendarFragment.onCalendarDateChanged()); // Set the listener for when the user picks a new date
        eventsListView = (ListView) root.findViewById(R.id.events_list); // Get the list view for the Events
        adapter = new EventAdapter(inflater);
        eventsListView.setAdapter(adapter);
        refreshViewEvents();
        return root;
    }
}
