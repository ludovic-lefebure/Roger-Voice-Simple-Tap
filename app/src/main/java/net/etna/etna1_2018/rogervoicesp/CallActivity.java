package net.etna.etna1_2018.rogervoicesp;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by lefebure on 11/12/2015.
 */
public class CallActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.getBoolean("Close", false)) {
            this.finish();
        } else {
            setContentView(R.layout.pop_up);
        }
    }

}
