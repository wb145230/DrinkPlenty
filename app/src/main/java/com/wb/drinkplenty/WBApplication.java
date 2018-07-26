package com.wb.drinkplenty;

import android.app.Application;

import com.wb.drinkplenty.util.SharedPreferenceSetting;
import com.xdandroid.hellodaemon.DaemonEnv;

public class WBApplication extends Application {

    private static WBApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        DaemonEnv.initialize(this, CoreService.class, DaemonEnv.DEFAULT_WAKE_UP_INTERVAL);
        CoreService.sShouldStopService = !SharedPreferenceSetting.getCupService(this);
        DaemonEnv.startServiceMayBind(CoreService.class);
    }

    public static synchronized WBApplication getInstance() {
        return sInstance;
    }

}
