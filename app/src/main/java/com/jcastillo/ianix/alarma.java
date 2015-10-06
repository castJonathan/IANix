package com.jcastillo.ianix;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

public class alarma extends AppCompatActivity {
    private AlarmManager mAlarmManager;
    private Intent mNotificationReceiverIntent, mLoggerReceiverIntent;
    private PendingIntent mNotificationReceiverPendingIntent,
            mLoggerReceiverPendingIntent;
    private static final long INITIAL_ALARM_DELAY =3000;//1 * 60 * 1000L;
    protected static final long JITTER = 5000L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarma);

        // Get the AlarmManager Service
        mAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        // Create an Intent to broadcast to the AlarmNotificationReceiver
        mNotificationReceiverIntent = new Intent(alarma.this,
                AlarmNotificationReceiver.class);

        // Create an PendingIntent that holds the NotificationReceiverIntent
        mNotificationReceiverPendingIntent = PendingIntent.getBroadcast(
                alarma.this, 0, mNotificationReceiverIntent, 0);

        // Create an Intent to broadcast to the AlarmLoggerReceiver
        mLoggerReceiverIntent = new Intent(alarma.this,
                AlarmLoggerReceiver.class);

        // Create PendingIntent that holds the mLoggerReceiverPendingIntent
        mLoggerReceiverPendingIntent = PendingIntent.getBroadcast(
                alarma.this, 0, mLoggerReceiverIntent, 0);

        // Set up single alarm Button
        final Button singleAlarmButton = (Button) findViewById(R.id.single_alarm_button);
        singleAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Set single alarm
                mAlarmManager.set(AlarmManager.RTC_WAKEUP,
                        System.currentTimeMillis() + INITIAL_ALARM_DELAY,
                        mNotificationReceiverPendingIntent);

                // Set single alarm to fire shortly after previous alarm
                mAlarmManager.set(AlarmManager.RTC_WAKEUP,
                        System.currentTimeMillis() + INITIAL_ALARM_DELAY
                                + JITTER, mLoggerReceiverPendingIntent);

                // Show Toast message
                Toast.makeText(getApplicationContext(), "Single Alarm Set",
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_alarma, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
