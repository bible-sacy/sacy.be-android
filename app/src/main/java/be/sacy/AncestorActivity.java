package be.sacy;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class AncestorActivity extends AppCompatActivity {

    private static final String TAG = "AncestorActivity";

    WebView mWebView;

    protected String domain;

    protected String getDomain() {
        return this.domain;
    }

    @Override
    @SuppressLint("SetJavaScriptEnabled")
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate " + this.getLocalClassName());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException ignored){}

        mWebView = findViewById(R.id.webview);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(false);
        settings.setAllowContentAccess(false);
        settings.setAllowFileAccessFromFileURLs(false);
        settings.setAllowUniversalAccessFromFileURLs(false);

        mWebView.setWebViewClient(new SacyWebViewClient(getDomain()));
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart " + this.getLocalClassName());
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume "  + this.getLocalClassName());
        super.onResume();
        String urlToLoad;
        {
            final Intent intent = getIntent();
            final String action = intent.getAction();
            final Uri url = intent.getData();
            if (
                    Intent.ACTION_VIEW.equals(action)
                            && null != url
                            && null != url.getHost()
                            && url.toString().contains(getDomain())
                            && url.toString().contains("/#/")
            ) {
                urlToLoad = url.toString();
            } else {
                urlToLoad =
                        this.getPreferences(Context.MODE_PRIVATE)
                                .getString(getDomain(), "https://" + getDomain());
            }
        }
        saveUrl(urlToLoad);
        mWebView.loadUrl(urlToLoad);
    }

    void saveUrl(String url) {
        this.getPreferences(Context.MODE_PRIVATE)
                .edit()
                .putString(getDomain(), url)
                .apply();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause "  + this.getLocalClassName());
        saveUrl(mWebView.getUrl());
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop "  + this.getLocalClassName());
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy " + this.getLocalClassName());
        super.onDestroy();
    }

    // https://developer.android.com/guide/webapps/webview#NavigatingHistory
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }
}