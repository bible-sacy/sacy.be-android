package be.sacy.activity;

import android.os.Bundle;

import be.sacy.AncestorActivity;

public class RoyaumontActivity extends AncestorActivity {

    protected String domain = "royaumont.sacy.be";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.domain = this.domain;
        super.onCreate(savedInstanceState);
    }
}
