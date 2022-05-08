package be.sacy;

import android.os.Bundle;

public class BreviaireHiverActivity extends MainActivity {

    protected String domain = "breviaire.sacy.be/hiver";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.domain = this.domain;
        super.onCreate(savedInstanceState);
    }
}
