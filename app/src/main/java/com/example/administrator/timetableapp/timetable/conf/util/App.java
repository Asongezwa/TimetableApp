package com.example.administrator.timetableapp.timetable.conf.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2017/07/31.
 */

public class App extends Application {
    private static Context context;
    private static App singleton;

    public void onCreate(){
        super.onCreate();
        App.context = getApplicationContext();
        singleton = this;
    }

    public static Context getAppContext(){
        return App.context;
    }
}
