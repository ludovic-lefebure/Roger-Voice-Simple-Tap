package net.etna.etna1_2018.rogervoicesp;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

public class MainActivity extends Activity {

    private final int PERMISSIONS_PROCESS_OUTGOING_CALLS = 1;
    private final int PERMISSIONS_SYSTEM_ALERT_WINDOW = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermissions();
    }

    /**
     * Check if the user has granted the permission to detect outgoing calls
     */
    public void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.PROCESS_OUTGOING_CALLS)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.PROCESS_OUTGOING_CALLS},
                    PERMISSIONS_PROCESS_OUTGOING_CALLS);

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        if (requestCode == PERMISSIONS_PROCESS_OUTGOING_CALLS) {
            // If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("Permissions", "granted");
            } else {
                Log.d("Permissions", "denied");
            }
        }
    }
}
