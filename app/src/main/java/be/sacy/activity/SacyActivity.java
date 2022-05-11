package be.sacy.activity;

import android.os.Bundle;

import be.sacy.AncestorActivity;

public class SacyActivity extends AncestorActivity {

    public static String domain = "www.sacy.be";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.domain = domain;
        super.onCreate(savedInstanceState);
    }
}
