package com.jen.change.exneires;

import android.content.Context;
import android.content.SharedPreferences;
import android.test.ApplicationTestCase;

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

}