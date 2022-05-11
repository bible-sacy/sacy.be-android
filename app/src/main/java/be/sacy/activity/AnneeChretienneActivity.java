package be.sacy.activity;

import android.os.Bundle;

import be.sacy.AncestorActivity;

public class AnneeChretienneActivity extends AncestorActivity {

    public static String domain = "missel.sacy.be";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.domain = domain;
        super.onCreate(savedInstanceState);
    }
}
