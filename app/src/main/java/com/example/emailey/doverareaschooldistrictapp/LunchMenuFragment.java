package com.example.emailey.doverareaschooldistrictapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by EMailey on 5/11/2017.
 */

public class LunchMenuFragment extends Fragment {

    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.lunch_menu_fragment, container, false);

        return root;
    }

}

