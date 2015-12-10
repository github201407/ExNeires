package com.jen.change.exneires.splash;

/**
 * Created by Administrator on 2015/12/10.
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

import com.jen.change.exneires.AppKeys;
import com.jen.change.exneires.AppModel;
import com.jen.change.exneires.R;

public class SplashActivity extends Activity {
    // 生命周期方法---开始
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //全屏
        setContentView(R.layout.splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        appModel = (AppModel)getApplication();
        appController = appModel.getAppController();
        appController.postDelayed(new Runnable() {
            /**
             * 隔10秒钟启动主页面
             */
            @Override
            public void run() {
                appController.processEvent(new AppEvent(SplashActivity.this, AppEvent.EVE_SPLASH_END, null));
            }
        }, AppKeys.SPLASH_DURATION);
        // 启动异步任务准备应用数据
    }
    // 生命周期方法---结束

    private AppController appController = null;
    private AppModel appModel = null;
}
