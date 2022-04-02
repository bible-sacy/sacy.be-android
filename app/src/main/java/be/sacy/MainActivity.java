package be.sacy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;


public class MainActivity extends AppCompatActivity {

    WebView mWebView;

    private final static String DOMAIN = "sacy.be";
    private final static String DEFAULT_SUBDOMAIN = "www";

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("url", mWebView.getUrl());
        super.onSaveInstanceState(outState);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        mWebView.setWebViewClient(new SacyWebViewClient(DOMAIN));

        String savedUrl;
        if (
                savedInstanceState != null
                && (savedUrl = savedInstanceState.getString("url")) != null
        ) {
            mWebView.loadUrl(savedUrl);
        } else {
            final Intent intent = getIntent();
            final String action = intent.getAction();
            final Uri url = intent.getData();
            if (
                    Intent.ACTION_VIEW.equals(action)
                            && null != url
                            && null != url.getHost()
                            && url.getHost().endsWith(DOMAIN)
            ) {
                mWebView.loadUrl(url.toString());
            } else {
                mWebView.loadUrl("https://" + DEFAULT_SUBDOMAIN + "." + DOMAIN + "/");
            }
        }
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