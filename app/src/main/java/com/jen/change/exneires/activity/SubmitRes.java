package com.jen.change.exneires.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jen.change.exneires.R;
import com.jen.change.exneires.adapter.RecyclerAdapter;
import com.jen.change.exneires.bean.Res;

import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2015/12/16.
 */
public class SubmitRes extends BaseActivity implements View.OnClickListener{
    private RecyclerView recyclerView            ;
    private EditText editName, editDesc, editChargeType, editType, editWant;
    private Button btnSubmit;

    public static void Instance(Context context){
        Intent intent = new Intent(context, SubmitRes.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        layoutResID = R.layout.submit_res;
        super.onCreate(savedInstanceState);

        // Add the Up button
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        initView();
        initRecyclerView();
    }

    private void initView() {
        editName = (EditText) findViewById(R.id.editName);
        editDesc = (EditText) findViewById(R.id.editDesc);
        editChargeType = (EditText) findViewById(R.id.editChargeType);
        editType = (EditText) findViewById(R.id.editType);
        editWant = (EditText) findViewById(R.id.editWant);
        btnSubmit = (Button)  findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);
    }

    /**
     * init RecyclerView
     */
    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        // make recyclerView horizontal scroll
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

//        String[] dataset = new String[2];
//        for (int i = 0; i < dataset.length; i++) {
//            dataset[i] = "item" + i;
//        }

        Res res = new Res();
        res.setName("Goods");
        res.setLocation("北京天安门");
        res.setType("电子产品");
        res.setChangeType("免费");
        res.setWantRes("微笑");
        res.setImgUrl("http://www.baidu.com|http://www.qq.com");
        RecyclerAdapter mAdapter = new RecyclerAdapter(this, res);
        recyclerView.setAdapter(mAdapter);

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSubmit:
                doSubmit();
                break;
            default:
                break;
        }
    }

    private void doSubmit() {
        showProgress();
        String name = this.getString(editName);
        String desc = this.getString(editDesc);
        String type = this.getString(editType);
        String want = this.getString(editWant);
        String chargeType = this.getString(editChargeType);
        Res res = new Res();
        res.setName(name);
        res.setLocation(desc);
        res.setType(chargeType);
        res.setChangeType(type);
        res.setWantRes(want);
        res.setImgUrl("http://www.baidu.com");
        res.save(this, new SaveListener() {
            @Override
            public void onSuccess() {
                hideProgress();
                showToast("提交成功");
                Log.e("info", "Success");
                finish();
            }

            @Override
            public void onFailure(int i, String s) {
                hideProgress();
                Log.e("info", "Failure");
                showToast("提交失败，请重试！");
            }
        });
    }

    private String getString(EditText editText){
        if(editText != null)
            return editText.getText().toString().trim();
        return "";
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private ProgressDialog progressDialog;

    private void showProgress(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(false);
        progressDialog.show();
    }

    private void hideProgress(){
        if(progressDialog != null && progressDialog.isShowing())
            progressDialog.hide();
        progressDialog = null;
    }

    private void showToast(String msg){
        Snackbar.make(btnSubmit, msg, Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }


}
