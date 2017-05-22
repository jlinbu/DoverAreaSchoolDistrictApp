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

public class PerformingArtsFragment extends Fragment {
    public WebView fPerformingArtsWebView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.performing_arts_fragment, container, false);
        // Initialize and load the WebView to the desired page
        fPerformingArtsWebView = (WebView) v.findViewById(R.id.fperformingartswebview);
        fPerformingArtsWebView.loadUrl("http://www.doversd.org/performing-arts/");

        // Enable Javascript
        WebSettings webSettings = fPerformingArtsWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        fPerformingArtsWebView.setWebViewClient(new WebViewClient());

        return v;

    }
}
