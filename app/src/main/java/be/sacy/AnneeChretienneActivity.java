package be.sacy;

import android.os.Bundle;
import android.webkit.WebView;

public class AnneeChretienneActivity extends MainActivity {

    protected String domain = "missel.sacy.be";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.domain = this.domain;
        super.onCreate(savedInstanceState);
    }
}
