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
 * Created by Bmotter on 5/8/2017.
 */

public class SchoolsFragment extends Fragment {
    public WebView fSchoolsWebView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.schools_fragment, container, false);
        // Initialize and load the WebView to the desired page
        fSchoolsWebView = (WebView) v.findViewById(R.id.fschoolswebview);
        fSchoolsWebView.loadUrl("http://www.doversd.org/schools/");

        // Enable Javascript
        WebSettings webSettings = fSchoolsWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        fSchoolsWebView.setWebViewClient(new WebViewClient());

        return v;

    }
}
