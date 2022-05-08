package be.sacy;

import android.os.Bundle;

public class BreviairePrintempsActivity extends MainActivity {

    protected String domain = "breviaire.sacy.be/printemps";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.domain = this.domain;
        super.onCreate(savedInstanceState);
    }
}
