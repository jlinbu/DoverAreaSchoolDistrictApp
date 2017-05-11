package com.example.emailey.doverareaschooldistrictapp;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

public class DoverMain extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private final String TAG = this.getClass().getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dover_main);
        Log.i(TAG, "onCreate");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dover_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        ((ViewGroup) findViewById(R.id.content_dover_main)).removeAllViews(); // This line removes all child views from the main view; "refreshes" the UI.
        if (id == R.id.nav_home_page) {
            // Navigate to the District Page WebView
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.add(R.id.content_dover_main, new DistrictPageFragment());
            transaction.commit();
        } else if (id == R.id.nav_calendar) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.add(R.id.content_dover_main, new CalendarFragment());
            transaction.commit();
        } else if (id == R.id.nav_menu) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.add(R.id.content_dover_main, new MenuFragment());
            transaction.commit();
        } else if (id == R.id.nav_lunch_menu) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.add(R.id.content_dover_main, new LunchMenuFragment());
            transaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
