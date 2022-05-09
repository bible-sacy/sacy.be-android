package be.sacy.activity;

import android.os.Bundle;

import be.sacy.AncestorActivity;

public class BreviaireAutomneActivity extends AncestorActivity {

    protected String domain = "breviaire.sacy.be/automne";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.domain = this.domain;
        super.onCreate(savedInstanceState);
    }
}
