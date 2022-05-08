package be.sacy;

import android.os.Bundle;

public class ImitationActivity extends MainActivity {

    protected String domain = "imitation.sacy.be";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.domain = this.domain;
        super.onCreate(savedInstanceState);
    }
}
