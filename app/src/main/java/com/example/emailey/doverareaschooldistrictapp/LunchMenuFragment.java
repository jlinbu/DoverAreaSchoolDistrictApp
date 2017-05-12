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

    public WebView LunchMenuWebView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View Lunch = inflater.inflate(R.layout.lunch_menu_fragment, container, false);
        LunchMenuWebView = (WebView) Lunch.findViewById(R.id.lunchmenuwebview);
        LunchMenuWebView.loadUrl("http://www.doversd.org/downloads/food_service_and_transportation/may_912_lunch_menu.pdf");

        // Enable Javascript
        WebSettings webSettings = LunchMenuWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        LunchMenuWebView.setWebViewClient(new WebViewClient());

        return Lunch;

    }
}

