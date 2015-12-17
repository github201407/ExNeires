package com.jen.change.exneires;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.TextView;

import com.jen.change.exneires.bean.Res;
import com.jen.change.exneires.bmob.BmobUtils;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2015/12/10.
 */
public class ScrollingActivityTest extends ActivityInstrumentationTestCase2<ScrollingActivity> {

    private ScrollingActivity activity;
    private TextView titleTxtv;

    public ScrollingActivityTest(){
        super(ScrollingActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(false);
        activity = getActivity();
    }

    public void tearDown() throws Exception {

    }

    public void testInsertRes(){
        BmobUtils.insertRes(activity);
    }

    public void testInsertUser(){
        BmobUtils.insertUser(activity);
    }

    public void testqueryImgUrl(){
        BmobUtils.queryImgUrl(activity);
    }

    public void testqueryResObjectId(){
        BmobUtils.queryResByObjectId(activity);
    }

    public void testqueryUseryObjectId(){
        BmobUtils.queryUseryObjectId(activity);
    }

    public void testInsertRes2(){
        Res res = new Res();
        res.setName("移动电源1");
        res.setLocation("上海泗泾1");
        res.setType("电子1");
        res.setChangeType("快递方式1");
        res.setWantRes("到付1");
        res.save(activity, new SaveListener() {
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


    /**
     * 测试界面上标题文字为：维康街
     */
    public void testTemp001_AppTitle(){
        assertEquals(activity.getString(R.string.app_name_test), activity.getTitle());
    }

    public void testQueryRes(){
        BmobQuery<Res> bmobQuery = new BmobQuery<>();
        bmobQuery.getObject(activity, "6b6c11c537", new GetListener<Res>() {
            @Override
            public void onSuccess(Res res) {
                Log.e("info", res.toString());
            }

            @Override
            public void onFailure(int i, String s) {
                Log.e("inof", "failure");
            }
        });
    }

    public void testinitDate() {
        Res res = new Res();
        res.setName("Goods");
        res.setLocation("北京天安门");
        res.setType("电子产品");
        res.setChangeType("免费");
        res.setWantRes("微笑");
        res.save(activity, new SaveListener() {
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

    public void testqueryResAll(){
        BmobUtils.queryResAll(activity);
    }
}