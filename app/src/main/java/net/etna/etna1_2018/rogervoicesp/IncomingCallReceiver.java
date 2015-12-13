package net.etna.etna1_2018.rogervoicesp;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * Created by lefebure on 08/12/2015.
 */
public class IncomingCallReceiver extends BroadcastReceiver {

    private NotificationManager mNM;

    // Unique Identification Number for the Notification
    private int NOTIFICATION = R.string.local_service_started;

    private Context context;

    @Override
    public void onReceive(Context context, Intent intent) {

        this.context = context;
        this.mNM = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (intent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")) {
            showNotification();
        } else {
            String stateStr = intent.getExtras().getString(TelephonyManager.EXTRA_STATE);
            if(stateStr.equals(TelephonyManager.EXTRA_STATE_IDLE)){
                mNM.cancel(NOTIFICATION);
                createCallActivity(true);
            } else if(stateStr.equals(TelephonyManager.EXTRA_STATE_OFFHOOK) || stateStr.equals(TelephonyManager.EXTRA_STATE_RINGING)){
                showNotification();
            }
        }

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void showNotification() {

        // The PendingIntent to launch our activity if the user selects this notification
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, CallActivity.class), 0);

        // Set the info for the views that show in the notification panel.
        Notification notification = new Notification.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)  // the status icon
                .setTicker(NOTIFICATION + "")  // the status text
                .setWhen(System.currentTimeMillis())  // the time stamp
                .setContentTitle(context.getText(R.string.local_service_label))  // the label of the entry
                .setContentIntent(contentIntent)  // The intent to send when the entry is clicked
                .build();

        // Send the notification.
        mNM.notify(NOTIFICATION, notification);
    }

    public void createCallActivity(final boolean toClose) {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent(context, CallActivity.class);
                i.putExtra("Close", toClose);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                context.startActivity(i);
            }

        }, 2000);

    }

}
