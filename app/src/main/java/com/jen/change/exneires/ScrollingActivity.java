package com.jen.change.exneires;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.jen.change.exneires.activity.BaseActivity;
import com.jen.change.exneires.activity.SubmitRes;
import com.jen.change.exneires.adapter.ResAdapter;
import com.jen.change.exneires.bean.Res;
import com.jen.change.exneires.bmob.BmobUtils;
import com.jen.change.exneires.utils.ProgressUtils;

import java.util.List;

import cn.bmob.v3.listener.FindListener;

public class ScrollingActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener{

    private AppModel appModel;
    private ResAdapter resAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        layoutResID = R.layout.activity_scrolling;
        super.onCreate(savedInstanceState);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                SubmitRes.Instance(ScrollingActivity.this);
            }
        });

        appModel = (AppModel)getApplication();

//        ListFragment fragment1 = new ListFragment();
//        getSupportFragmentManager().beginTransaction().replace(R.id.listFragment, fragment1).commit();

        initView();

        downLoadData();
    }

    private void initView(){
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.YELLOW, Color.RED);
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.LTGRAY);
        swipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
        swipeRefreshLayout.setOnRefreshListener(this);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.news_list);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

        resAdapter = new ResAdapter();
        mRecyclerView.setAdapter(resAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTitle(appModel.getWelcomeMsg());

    }

    private void downLoadData() {
        ProgressUtils.showProgress(this);
        BmobUtils.queryRes(this, new FindListener<Res>() {
            @Override
            public void onSuccess(List<Res> list) {
                ProgressUtils.hideProgress();
                Log.e("bmob", "success:");
                resAdapter.addRes(list);
            }

            @Override
            public void onError(int i, String s) {
                ProgressUtils.hideProgress();
                Log.e("bmob", "error:" + i + "," + s);
            }
        });
    }


    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 停止刷新
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 5000); // 5秒后发送消息，停止刷新
    }
}
