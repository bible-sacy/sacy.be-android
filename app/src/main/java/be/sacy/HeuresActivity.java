package be.sacy;

import android.os.Bundle;

public class HeuresActivity extends MainActivity {

    protected String domain = "heures.sacy.be";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.domain = this.domain;
        super.onCreate(savedInstanceState);
    }
}
