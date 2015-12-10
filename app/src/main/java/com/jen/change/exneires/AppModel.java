package com.jen.change.exneires;

/**
 * Created by Administrator on 2015/12/10.
 */

import android.app.Application;
import android.content.SharedPreferences;

public class AppModel extends Application {
    private static final String IS_FIRST_RUN = "isFirstRun";
    private SharedPreferences prefs;

    // 生命周期方法开始
    public void onCreate() {
        super.onCreate();
        welcomeMsg = getString(R.string.app_name_test);
    }

    public void onTerminate() {
        super.onTerminate();
    }
    // 生命周期方法结束


    private String welcomeMsg = null;


    public String getWelcomeMsg() {
        return welcomeMsg;
    }

    public void setWelcomeMsg(String welcomeMsg) {
        this.welcomeMsg = welcomeMsg;
    }


    private boolean isFirstRun = true;

    public boolean isFirstRun() {
        prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean orgVal = isFirstRun;
        isFirstRun = false;
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(IS_FIRST_RUN, false);
        editor.apply();
        return orgVal;
    }

    public void setFirstRun(boolean isFirstRun) {
        this.isFirstRun = isFirstRun;
    }

}

