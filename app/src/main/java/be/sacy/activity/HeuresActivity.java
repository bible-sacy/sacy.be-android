package be.sacy.activity;

import android.os.Bundle;

import be.sacy.AncestorActivity;

public class HeuresActivity extends AncestorActivity {

    protected String domain = "heures.sacy.be";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.domain = this.domain;
        super.onCreate(savedInstanceState);
    }
}
