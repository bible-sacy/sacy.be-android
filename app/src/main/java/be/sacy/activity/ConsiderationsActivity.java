package be.sacy.activity;

import android.os.Bundle;

import be.sacy.AncestorActivity;

public class ConsiderationsActivity extends AncestorActivity {

    protected String domain = "considerations.sacy.be";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.domain = this.domain;
        super.onCreate(savedInstanceState);
    }
}
