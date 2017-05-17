package com.example.emailey.doverareaschooldistrictapp;

import android.app.Activity;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebViewFragment;
import android.widget.Toast;

/**
 * Created by Bmotter on 5/8/2017.
 */

public class LunchMenuFragment extends Fragment {
    public WebView fLunchWebView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.lunch_menu_fragment, container, false);
        fLunchWebView = (WebView) v.findViewById(R.id.flunchmenuwebview);
        fLunchWebView.loadUrl("http://www.doversd.org/downloads/food_service_and_transportation/may_912_lunch_menu.pdf");

        // Enable Javascript
        WebSettings webSettings = fLunchWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        fLunchWebView.setWebViewClient(new WebViewClient());

        return v;

    }
}

