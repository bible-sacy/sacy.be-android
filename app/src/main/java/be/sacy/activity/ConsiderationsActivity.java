package be.sacy.activity;

import android.os.Bundle;

import be.sacy.AncestorActivity;

public class ConsiderationsActivity extends AncestorActivity {

    public static String domain = "considerations.sacy.be";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.domain = domain;
        super.onCreate(savedInstanceState);
    }
}
