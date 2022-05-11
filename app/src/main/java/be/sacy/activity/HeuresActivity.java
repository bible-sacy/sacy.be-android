package be.sacy.activity;

import android.os.Bundle;

import be.sacy.AncestorActivity;

public class HeuresActivity extends AncestorActivity {

    public static String domain = "heures.sacy.be";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.domain = domain;
        super.onCreate(savedInstanceState);
    }
}
