package com.jen.change.exneires;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

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

    /**
     * 测试界面上标题文字为：维康街
     */
    public void testTemp001_AppTitle(){
        assertEquals(activity.getString(R.string.app_name_test), activity.getTitle());
    }
}