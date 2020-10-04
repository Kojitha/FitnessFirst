package com.example.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class SearchCalories extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_calories);

        WebView webView = new WebView(this);
        setContentView(webView);
        webView.loadUrl("https://www.nhs.uk/live-well/healthy-weight/calorie-checker/");
    }
}