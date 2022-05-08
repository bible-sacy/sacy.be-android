package be.sacy;

import android.os.Bundle;

public class RoyaumontActivity extends MainActivity {

    protected String domain = "royaumont.sacy.be";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.domain = this.domain;
        super.onCreate(savedInstanceState);
    }
}
