package net.etna.etna1_2018.rogervoicesp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by lefebure on 10/12/2015.
 */
public class CallActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Call", "In!");
        boolean close = getIntent().getExtras().getBoolean("Close");
        if (close) {
            Log.d("Call", "Closed!");
            this.finish();
        }
    }

}
