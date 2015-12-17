package com.jen.change.exneires.splash;

/**
 * Created by Administrator on 2015/12/10.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.jen.change.exneires.AppModel;
import com.jen.change.exneires.ScrollingActivity;

public class AppController extends Handler {
    public AppController(AppModel appModel) {
        super();
        this.appModel = appModel;
    }

    /**
     * Activity中会根据用户的操作或系统状态，产生对应的事件，发送给AppController进行统一处理。
     * @param event
     */
    public void processEvent(AppEvent event) {
        switch (event.getEventId()) {
            case AppEvent.EVE_SPLASH_END: // 从Splash界面显示主界面
                showMainActivity((Activity)event.getContext(), event.getParams());
                break;
            default:
                break;
        }
    }

    /**
     * 异步任务、线程、后台服务等需要更新界面时，向AppController发送消息即可
     */
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
    }

    /**
     * 关闭Splash页面并打开应用主界面
     * @param activity
     * @param params
     */
    private void showMainActivity(Activity activity, Bundle params) {
        Log.d("wkj", "activity=" + activity + "; c=" + ScrollingActivity.class + "!");
        Intent intent = new Intent(activity, ScrollingActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    public void registerAppEnter(){
        Log.e("dragger", "registerAppEnter");
    }

    private AppModel appModel = null;
}