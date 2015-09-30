package com.jcastillo.ianix;

/**
 * Created by Jonathan on 30/09/2015.
 */
import android.content.Context;
import android.app.Application;
import android.util.Log;

public class IANix extends Application {
    private static Context mContext;
    private static boolean activityVisible;
    public static boolean isActivityVisible() {
        return activityVisible;
    }
    public static void activityResumed() {
        activityVisible = true;
    }

    public static void activityPaused() {
        activityVisible = false;
    }

    public static Context getContext() {
        return mContext;
    }

    public static void onDestroy() {
        Log.e("Application", "Estoy en OnDestroy");
    }

    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
}
