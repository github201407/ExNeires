package com.jen.change.exneires;

/**
 * Created by Administrator on 2015/12/10.
 */

import android.app.Application;
import android.content.SharedPreferences;

import com.jen.change.exneires.bean.Res;
import com.jen.change.exneires.bmob.BmobUtils;
import com.jen.change.exneires.splash.AppController;

import java.util.Arrays;
import java.util.List;

import cn.bmob.v3.Bmob;

public class AppModel extends Application {
    private static final String IS_FIRST_RUN = "isFirstRun";
    private SharedPreferences prefs;
    private static AppModel sAppModel = null;
    private List<Res> list;

    // 生命周期方法开始
    public void onCreate() {
        super.onCreate();
        welcomeMsg = getString(R.string.app_name_test);
        Bmob.initialize(this, "7428acdbcbbf507bf463331590b849d8");
        sAppModel = this;
    }

    private List<Object> getModules() {
        return Arrays.<Object>asList(new BmobUtils(this));
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

    public AppController getAppController() {
        return new AppController(this);
    }

    public static AppModel getApplication(){
        return sAppModel;
    }

    public List<Res> getList() {
        return list;
    }

    public void setList(List<Res> list) {
        this.list = list;
    }


}

