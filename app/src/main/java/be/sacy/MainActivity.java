package be.sacy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    WebView mWebView;
    String urlToLoad;
    protected String domain = "www.sacy.be";

    protected String getDomain() {
        return this.domain;
    }

    String getUrlToLoad() {
        return urlToLoad;
    }

    void setUrlToLoad(String urlToLoad) {
        this.urlToLoad = urlToLoad;
    }

    WebView getWebView() {
        return mWebView;
    }

    void setWebView(WebView v) {
        mWebView = v;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("url", getWebView().getUrl());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getUrlToLoad() != null) {
            getWebView().loadUrl(getUrlToLoad());
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException ignored){}

        setWebView(findViewById(R.id.webview));
        WebSettings settings = getWebView().getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(false);
        settings.setAllowContentAccess(false);
        settings.setAllowFileAccessFromFileURLs(false);
        settings.setAllowUniversalAccessFromFileURLs(false);

        getWebView().setWebViewClient(new SacyWebViewClient(getDomain()));

        String savedUrl;
        if (
                savedInstanceState != null
                && (savedUrl = savedInstanceState.getString("url")) != null
        ) {
            setUrlToLoad(savedUrl);
        } else {
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
                setUrlToLoad(url.toString());
            } else {
                setUrlToLoad(
                        this.getPreferences(Context.MODE_PRIVATE)
                                .getString(getDomain(), "https://" + getDomain())
                );
            }
        }
    }

    @Override
    protected void onPause() {
        this.getPreferences(Context.MODE_PRIVATE)
                .edit()
                .putString(getDomain(), getWebView().getUrl())
                .apply();
        super.onPause();
    }

    // https://developer.android.com/guide/webapps/webview#NavigatingHistory
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && getWebView().canGoBack()) {
            getWebView().goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }
}