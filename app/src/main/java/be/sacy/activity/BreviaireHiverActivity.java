package be.sacy.activity;

import android.os.Bundle;

import be.sacy.AncestorActivity;

public class BreviaireHiverActivity extends AncestorActivity {

    public static String domain = "breviaire.sacy.be/hiver";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.domain = domain;
        super.onCreate(savedInstanceState);
    }
}
