package com.example.emailey.doverareaschooldistrictapp;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by Bmotter on 5/8/2017.
 */

public class LunchMenuFragment extends Fragment {
//Puts Lunch Menu View into app window
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.lunch_menu_fragment, container, false);


        return v;
}
}

