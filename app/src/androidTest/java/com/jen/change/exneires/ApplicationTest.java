package com.jen.change.exneires;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.jen.change.exneires.bean.User;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.GetListener;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

}