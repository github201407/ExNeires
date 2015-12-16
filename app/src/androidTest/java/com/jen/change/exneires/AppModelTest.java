package com.jen.change.exneires;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.jen.change.exneires.bean.Res;
import com.jen.change.exneires.bean.User;

import java.io.File;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2015/12/10.
 */
public class AppModelTest extends ApplicationTestCase<AppModel> {

    private AppModel prefs;

    public AppModelTest() {
        super(AppModel.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        createApplication();
        prefs = getApplication();
    }

    public void tearDown() throws Exception {

    }

    public void testFirstRunTrue() {
        SharedPreferences.Editor editor = prefs.getSharedPreferences("prefs", Context.MODE_PRIVATE).edit();
        editor.clear().apply();
        assertTrue(prefs.isFirstRun());
    }

    public void testSecondAndMoreRun() {
        prefs.isFirstRun();
        assertFalse(prefs.isFirstRun());
    }

    public void testQueryUser(){
        BmobQuery<User> bmobQuery = new BmobQuery<>();
        bmobQuery.getObject(getContext(), "kpaA666B", new GetListener<User>() {
            @Override
            public void onSuccess(User object) {
                // TODO Auto-generated method stub
                Log.e("info", "查询成功");
            }

            @Override
            public void onFailure(int code, String msg) {
                // TODO Auto-generated method stub
                Log.e("info", "查询失败：" + msg);
            }
        });
    }

    public void testInsertRes(){
        Res res = new Res();
        res.setName("移动电源");
        res.setLocation("上海泗泾");
        res.setType("电子");
        res.setChangeType("快递方式");
        res.setWantRes("到付");
        res.save(getContext(), new SaveListener() {
            @Override
            public void onSuccess() {
                Log.e("info", "Success");
            }

            @Override
            public void onFailure(int i, String s) {
                Log.e("info", "Failure");
            }
        });
    }

    public void testPath(){
        System.out.println(Environment.getRootDirectory().getAbsolutePath());
        System.out.println(Environment.getExternalStorageDirectory().getAbsolutePath());
        System.out.println(Environment.getDownloadCacheDirectory().getAbsolutePath());
        System.out.println(Environment.getDataDirectory().getAbsolutePath());
        File file = new File(Environment.getExternalStorageDirectory(), "img.jpg");
        assertTrue(file.exists());
    }

}