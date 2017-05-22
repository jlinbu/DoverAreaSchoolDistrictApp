package com.example.emailey.doverareaschooldistrictapp;

import android.app.Fragment;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CalendarView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;

/**
 * Created by tsengia on 5/5/2017.
 */

public class CalendarFragment extends Fragment {

    private CalendarView calendarView;
    private ListView eventsListView;
    private EventAdapter adapter;
    private ProgressBar mProgressBar;

    private List<Event> eventsList = new ArrayList<Event>(); // This list holds all of the Calendar events
    private List<Event> currentEventsList = new ArrayList<Event>(); // This list holds all of the Calendar events for the currently selected date
    private Date selectedDate = Calendar.getInstance().getTime(); // This will be the Date that is selected on the CalendarView
    private Calendar calendar = Calendar.getInstance(); // 
    public final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");

    private class CalendarUpdater extends AsyncTask<String, Integer, String> { // Must extend AsyncTask in order to make HttpURLConnection and download the iCalendar files. If it isn't done asyncronously, Android will crash, complaining that you are holding up the UI thread.

        @Override
        protected String doInBackground(String... params) { // This is the method that is executed asyncronously
            eventsList.clear(); // Clear the list of all events as we are downloading new ones
            for(CalendarInfo c : CalendarManifest.manifest) { // iterate through all of the CalendarInfo objects that we have stored in the manifest/list
              try {
                    URL url = new URL(c.getUrl()); 
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // This creates a virtual connection, but no data is transmitted to the URL. Data won't be sent until HttpURLConnection.connect() is called
                    conn.connect(); // Now we are transmitting a GET request to the URL
                    int response = conn.getResponseCode(); // Grab the HTTP response code (ie 404, 500, 200) 200 means OK
                    InputStream in;

                    if(response == HttpURLConnection.HTTP_OK) { // If the URL accepted our GET request and has given us a response
                        in = conn.getInputStream(); // Open up the InputStream to read in the response
                        ICalendar iCal = Biweekly.parse(in).first(); // The BiWeekly can parse ICalendar objects from an InputStream object
                        for(VEvent e : iCal.getEvents()) { //Loop over each event (named VEvent objects) in the iCalendar and add it to our master list of events
                            Date start = new Date(e.getDateStart().getValue().getTime()); // Grab the start Date of the event
                            Date end = new Date(e.getDateEnd().getValue().getTime()); //Grab the ending Date of the event
                            Event event = new Event(c.getName() + ": " + e.getSummary().getValue(), start); // Create a new Event. This Event class is defined in this package. the constructor accepts Event(String title, Date startDate)
                            event.setEndDate(end); // Set the ending Date for the Event
                            event.setEventColor(c.getCalendarColor()); // Set the color of the Event according to the calendar it belongs to
                            eventsList.add(event); // Finally, add the Event to the master list
                        }
                    }
                    else { // If the HTTP Response Code is something other than OK (200), then that means our request probably failed, so log a complaint
                        Log.e("CalendarFragment", "Failed to update ics file, HTTP Response code: " + Integer.toString(response));
                    }
                } catch (MalformedURLException e) { // If the URL is malformed
                    e.printStackTrace();
                } catch (IOException e) { // If URL.openConnection fails
                    e.printStackTrace();
                }
            }
            return null; // AsyncTask never returns anything
        }

        @Override
        public void onPostExecute(String result) { // This method is called when the backgroudn (asyncronous thread) finishes
            mProgressBar.setVisibility(View.GONE); //Hide the progressbar/loading circle
            eventsListView.setVisibility(View.VISIBLE); // Show the events list
            refreshViewEvents(); // Tell the CalendarFragment to refresh its list of events in the EventsListView
        }

    }

    public void refreshViewEvents() { // This method will search through the master events list and find the events that occur on the date selected on the CalendarView. It will then add those events to the ListView
        currentEventsList.clear(); //Clear the currentEventsList as we are refreshing it
        String date = DATE_FORMAT.format(selectedDate); // Grab a string representation of the selected Date on the CalendarView. (yyyyMMdd)

        for(int i = 0; i < eventsList.size(); i++) { // Iterate through the master list of events. We must do it in a for loop and not a for-each lopp or else we will trigger a ConcurrentModification exception. Just...don't change this loop, please
            Event e = eventsList.get(i);
            if(date.equals(DATE_FORMAT.format(e.getDate()))) { // If the two string based representations of the Dates match, then they are on the same date, so add in that events
                currentEventsList.add(e);
            }
            else if(selectedDate.getTime() > e.getDate().getTime() && (selectedDate.getTime() < e.getEndDate().getTime())) { // Grab the start and ending timestamps and check to see if the selected date's timestamp falls within the start and end timestamp of the Event we're checking with
                currentEventsList.add(e); // If the selected date does fall within the start and ending timestamp, add it to the list
            }
        }

        eventsListView.invalidateViews(); // Force the ListView to refresh
    }

    public void refreshDataEvents() { // This method will use the iCal4J library to refresh the list of events we have.
        CalendarUpdater updater = new CalendarUpdater();
        updater.execute(); //Start the asyncronous thread
    }

    private class onCalendarDateChanged implements CalendarView.OnDateChangeListener { //This is called when a new date is selected on the CalendarView
        @Override
        public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) { // This is called when the user selects a new date
            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            selectedDate = c.getTime(); //Set selectedDate to the Date selected on the CalendarView
            refreshViewEvents(); // Now that the selectedDate has changed, update the EventListView
        }
    }

    private class EventAdapter extends BaseAdapter implements ListAdapter { // Setting the list adapter for the Events List
        private LayoutInflater inflater
            
            /* There are many empty methods below, they are just there to satisfy the ListAdapter interface
             * 
             */
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
            public int getCount() { // This method is needed or else a NullPointer Exception will be thrown
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
                root.setBackgroundColor(currentEventsList.get(position).getEventColor()); // Set the event bubble's background to the color specified by the Event object
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
            public boolean isEmpty() { // This method is needer or else a NullPointerException will be thrown
                return currentEventsList.size() == 0;
            }

            public EventAdapter(LayoutInflater inf) {
                this.inflater = inf;
            }
    }

    @Override
    public void onStart() {
        super.onStart();
        refreshDataEvents(); // Start to load all of the Events from the iCalendars
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.calendar_fragment, container, false);

        //Below we are creating the manifest for all of the CalendarInfo objects and adding in all of the Calendars
        CalendarManifest.manifest.clear();
        CalendarManifest.manifest.add(new CalendarInfo(getString(R.string.district_calendar), Color.RED, "District Calendar"));
        CalendarManifest.manifest.add(new CalendarInfo(getString(R.string.high_school_calendar), Color.BLACK, "High School"));
        CalendarManifest.manifest.add(new CalendarInfo(getString(R.string.intermediate_school_calendar), Color.DKGRAY, "Intermediate School"));
        CalendarManifest.manifest.add(new CalendarInfo(getString(R.string.directors_calendar), Color.GRAY, "Director's Calendar"));
        CalendarManifest.manifest.add(new CalendarInfo(getString(R.string.lieb_elementary_school_calendar), Color.BLUE, "Lieb Elementary"));
        CalendarManifest.manifest.add(new CalendarInfo(getString(R.string.weigelstown_elementary_school_calendar), Color.rgb(0, 150, 0), "Weigelstown Elementary"));
        CalendarManifest.manifest.add(new CalendarInfo(getString(R.string.northsalem_elementary_school_calendar), Color.rgb(117, 75, 13), "North Salem Elementary"));
        CalendarManifest.manifest.add(new CalendarInfo(getString(R.string.dover_elementary_school_calendar), Color.MAGENTA, "Dover Elementary"));

        calendarView = (CalendarView) root.findViewById(R.id.calendarView); // Get the calendar view
        calendarView.setDate(Calendar.getInstance().getTime().getTime()); // Set the calendar view to today's date

        calendarView.setOnDateChangeListener(new CalendarFragment.onCalendarDateChanged()); // Set the listener for when the user picks a new date
        eventsListView = (ListView) root.findViewById(R.id.events_list); // Get the list view for the Events
        adapter = new EventAdapter(inflater);
        eventsListView.setAdapter(adapter);

        mProgressBar = (ProgressBar) root.findViewById(R.id.marker_progress); // Grab the ProgressBar so that we can turn it off later
        refreshViewEvents();
        return root;
    }
}
