package be.sacy;

import android.os.Bundle;

public class TheologieFamiliereActivity extends MainActivity {

    protected String domain = "theologie-familiere.sacy.be";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.domain = this.domain;
        super.onCreate(savedInstanceState);
    }
}
