package be.sacy;

import android.os.Bundle;

public class ConsiderationsActivity extends MainActivity {

    protected String domain = "considerations.sacy.be";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.domain = this.domain;
        super.onCreate(savedInstanceState);
    }
}
