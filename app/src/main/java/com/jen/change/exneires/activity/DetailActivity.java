package com.jen.change.exneires.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.widget.TextView;

import com.jen.change.exneires.R;
import com.jen.change.exneires.adapter.MyPagerAdapter;
import com.jen.change.exneires.bean.Res;

/**
 * Created by chenmingqun on 2015/12/10.
 */
public class DetailActivity extends BaseActivity {

    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private TextView resName, resDesc, resLocation, resType, resChargeType, resWant;

    public static void Instance(Context context, Bundle bundle){
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("bundle", bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        layoutResID = R.layout.activity_detail;
        super.onCreate(savedInstanceState);

        // Add the Up button
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        initView();
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        Bundle bundle = getIntent().getBundleExtra("bundle");
        assert bundle != null;
        Res res = (Res)bundle.get("res");
        if(res == null)
            finish();
        assert res != null;
        pagerAdapter = new MyPagerAdapter(res.getImgUrl());
        viewPager.setAdapter(pagerAdapter);

        resName = (TextView) findViewById(R.id.detail_resName);
        resDesc = (TextView) findViewById(R.id.detail_resDesc);
        resLocation = (TextView) findViewById(R.id.detail_resLocation);
        resType = (TextView) findViewById(R.id.detail_resType);
        resChargeType = (TextView) findViewById(R.id.detail_resChargeType);
        resWant = (TextView) findViewById(R.id.detail_resWant);

        resName.setText(res.getName());
//        resDesc.setText(res.get);
        resLocation.setText(res.getLocation());
        resType.setText(res.getType());
        resChargeType.setText(res.getChangeType());
        resWant.setText(res.getWantRes());
    }
}
