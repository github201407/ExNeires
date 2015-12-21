package com.jen.change.exneires.bean;

import android.util.Log;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenmingqun on 2015/12/10.
 */
public class UserTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();

    }

    public void tearDown() throws Exception {

    }

    public void testQuery(){
        List<String> stockList = new ArrayList<String>();
        stockList.add("stock1");
        stockList.add("stock2");

        String[] stockArr = new String[stockList.size()];
        stockArr = stockList.toArray(stockArr);

        for(String s : stockArr)
            Log.e("string", s);
    }
}