package be.sacy;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.RequiresApi;

import java.util.HashMap;
import java.util.regex.Pattern;

import be.sacy.activity.AnneeChretienneActivity;
import be.sacy.activity.BibleActivity;
import be.sacy.activity.BreviaireAutomneActivity;
import be.sacy.activity.BreviaireEteActivity;
import be.sacy.activity.BreviaireHiverActivity;
import be.sacy.activity.BreviairePrintempsActivity;
import be.sacy.activity.ConsiderationsActivity;
import be.sacy.activity.HeuresActivity;
import be.sacy.activity.ImitationActivity;
import be.sacy.activity.RoyaumontActivity;
import be.sacy.activity.SacyActivity;
import be.sacy.activity.TheologieFamiliereActivity;

public class SacyWebViewClient extends WebViewClient {

    private static final String PUB_URL_PREFIX = "https://bible.sacy.be/pub/";

    Pattern urlPattern;

    static HashMap<String, Class<? extends AncestorActivity>> prefixes = new HashMap<>();
    static {
        prefixes.put(AnneeChretienneActivity.domain, AnneeChretienneActivity.class);
        prefixes.put(BibleActivity.domain, BibleActivity.class);
        prefixes.put(BreviaireAutomneActivity.domain, BreviaireAutomneActivity.class);
        prefixes.put(BreviaireEteActivity.domain, BreviaireEteActivity.class);
        prefixes.put(BreviaireHiverActivity.domain, BreviaireHiverActivity.class);
        prefixes.put(BreviairePrintempsActivity.domain, BreviairePrintempsActivity.class);
        prefixes.put(ConsiderationsActivity.domain, ConsiderationsActivity.class);
        prefixes.put(HeuresActivity.domain, HeuresActivity.class);
        prefixes.put(ImitationActivity.domain, ImitationActivity.class);
        prefixes.put(RoyaumontActivity.domain, RoyaumontActivity.class);
        prefixes.put(SacyActivity.domain, SacyActivity.class);
        prefixes.put(TheologieFamiliereActivity.domain, TheologieFamiliereActivity.class);
    }

    public SacyWebViewClient(String domain) {
        urlPattern = Pattern.compile(
                Pattern.quote("https://")
                        + Pattern.quote(domain)
                        + "($|/.*)"
        );
    }

    private boolean matchExtUrl(String url) {
        return url.startsWith(PUB_URL_PREFIX)
                || url.endsWith(".pdf")
                || !urlPattern.matcher(url).matches();
    }

    void startActivityForUri(Context context, Uri uri) {
        if (uri.toString().endsWith(".pdf")
            || uri.toString().startsWith(PUB_URL_PREFIX)) {
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(intent);
        } else {
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            String hostPath = uri.getHost();
            if (hostPath != null) {
                String path = uri.getPath();
                if (path != null) {
                    hostPath += path;
                }
                for (String prefix : prefixes.keySet()) {
                    if (hostPath.startsWith(prefix)) {
                        intent.setClass(context, prefixes.get(prefix));
                        break;
                    }
                }
            }
            context.startActivity(intent);
        }
    }

    @Override
    // for API < 24
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (matchExtUrl(url)) {
            startActivityForUri(view.getContext(), Uri.parse(url));
            return true;
        }
        return super.shouldOverrideUrlLoading(view, url);
    }

    @Override
    @RequiresApi(24)
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        if (matchExtUrl(request.getUrl().toString())) {
            startActivityForUri(view.getContext(), request.getUrl());
            return true;
        }
        return super.shouldOverrideUrlLoading(view, request);
    }

}
