package be.sacy;

import android.os.Bundle;

public class BreviaireAutomneActivity extends MainActivity {

    protected String domain = "breviaire.sacy.be/automne";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.domain = this.domain;
        super.onCreate(savedInstanceState);
    }
}
