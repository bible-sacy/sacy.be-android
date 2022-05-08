package be.sacy;

import android.content.Intent;
import android.net.Uri;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.RequiresApi;

import java.util.regex.Pattern;

public class SacyWebViewClient extends WebViewClient {

    Pattern urlPattern;

    public SacyWebViewClient(String domain) {
        urlPattern = Pattern.compile(
                Pattern.quote("https://")
                        + Pattern.quote(domain)
                        + "($|/.*)"
        );
    }

    private boolean matchExtUrl(String url) {
        return !urlPattern.matcher(url).matches() || url.endsWith(".pdf");
    }

    @Override
    @SuppressWarnings("deprecation") // for API < 24
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (matchExtUrl(url)) {
            view.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            return true;
        }
        return super.shouldOverrideUrlLoading(view, url);
    }

    @Override
    @RequiresApi(24)
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        if (matchExtUrl(request.getUrl().toString())) {
            view.getContext().startActivity(new Intent(Intent.ACTION_VIEW, request.getUrl()));
            return true;
        }
        return super.shouldOverrideUrlLoading(view, request);
    }

}
