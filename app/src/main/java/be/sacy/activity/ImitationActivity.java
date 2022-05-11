package be.sacy.activity;

import android.os.Bundle;

import be.sacy.AncestorActivity;

public class ImitationActivity extends AncestorActivity {

    public static String domain = "imitation.sacy.be";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.domain = domain;
        super.onCreate(savedInstanceState);
    }
}
