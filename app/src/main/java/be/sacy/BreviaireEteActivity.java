package be.sacy;

import android.os.Bundle;

public class BreviaireEteActivity extends MainActivity {

    protected String domain = "breviaire.sacy.be/ete";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.domain = this.domain;
        super.onCreate(savedInstanceState);
    }
}
