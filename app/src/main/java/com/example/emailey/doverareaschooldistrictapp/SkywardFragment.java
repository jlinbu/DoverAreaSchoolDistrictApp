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

public class SkywardFragment extends Fragment {
    public WebView fSkywardWebView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.skyward_fragment, container, false);
        // Initialize and load the WebView to the desired page
        fSkywardWebView = (WebView) v.findViewById(R.id.fskywardwebview);
        fSkywardWebView.loadUrl("https://skyward.iscorp.com/scripts/wsisa.dll/WService=wsedudoverpa/seplog01.w");

        // Enable Javascript
        WebSettings webSettings = fSkywardWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        fSkywardWebView.setWebViewClient(new WebViewClient());

        return v;

    }
}