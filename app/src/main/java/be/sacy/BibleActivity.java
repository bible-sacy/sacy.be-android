package be.sacy;

import android.os.Bundle;

public class BibleActivity extends MainActivity {

    protected String domain = "bible.sacy.be";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.domain = this.domain;
        super.onCreate(savedInstanceState);
    }
}
