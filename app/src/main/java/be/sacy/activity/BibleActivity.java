package be.sacy.activity;

import android.os.Bundle;

import be.sacy.AncestorActivity;

public class BibleActivity extends AncestorActivity {

    public static String domain = "bible.sacy.be";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.domain = domain;
        super.onCreate(savedInstanceState);
    }
}
