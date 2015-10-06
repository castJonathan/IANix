package com.jcastillo.ianix;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Jonathan on 02/10/2015.
 */
public class AlarmLoggerReceiver extends BroadcastReceiver {
    private static final String TAG = "AlarmLoggerReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {

        // Log receipt of the Intent with timestamp
        Log.e(TAG, "Logging alarm at:" + DateFormat.getDateTimeInstance().format(new Date()));

    }
}
