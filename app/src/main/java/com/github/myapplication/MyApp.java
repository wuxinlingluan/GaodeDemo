package com.github.myapplication;

import android.app.Application;

import com.amap.api.navi.AMapNavi;

/**
 * Created by ${sheldon} on 2017/8/14.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AMapNavi.setApiKey(this, "71669e6052f52a506b64961792a1991e");
    }
}
