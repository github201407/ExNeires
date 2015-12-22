package com.jen.change.exneires.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bmob.btp.callback.UploadBatchListener;
import com.jen.change.exneires.R;
import com.jen.change.exneires.adapter.RecyclerAdapter;
import com.jen.change.exneires.bean.Res;
import com.jen.change.exneires.bmob.BmobUtils;
import com.jen.change.exneires.utils.CameraUtil;
import com.jen.change.exneires.utils.ProgressUtils;

import java.util.ArrayList;
import java.util.HashMap;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2015/12/16.
 */
public class SubmitRes extends BaseActivity implements View.OnClickListener{
    private RecyclerView recyclerView            ;
    private EditText editName, editDesc, editChargeType, editType, editWant;
    private Button btnSubmit;
    private RecyclerAdapter mAdapter;
    private HashMap<String,String> mChoosed;/* <本地url，网络url> */

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

//        Res res = new Res();
//        res.setName("Goods");
//        res.setLocation("北京天安门");
//        res.setType("电子产品");
//        res.setChangeType("免费");
//        res.setWantRes("微笑");
//        res.setImgUrl("http://www.baidu.com|http://www.qq.com");
        mAdapter = new RecyclerAdapter(this);
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
                doUploadImgs();
                break;
            default:
                break;
        }
    }

    private String imgUrls = "";
    private void doUploadImgs(){
        ProgressUtils.showProgress(this);
        ArrayList<String> imgs = mAdapter.getArrayList();
        if(imgs.isEmpty()){
            doSubmit();
        }else {
            uploadImgs(imgs);
        }
    }

    private void uploadImgs(ArrayList<String> imgs) {
        // TODO 代码优化，是否限定必须有图片，显示具体的进度
        BmobUtils.uploadImgs(this, imgs, new UploadBatchListener() {

            @Override
            public void onSuccess(boolean isFinish, String[] fileNames, String[] urls, BmobFile[] files) {
                // isFinish ：批量上传是否完成
                // fileNames：文件名数组
                // urls       : url：文件地址数组
                // files     : BmobFile文件数组，`V3.4.1版本`开始提供，用于兼容新旧文件服务。
//                注：若上传的是图片，url(s)并不能直接在浏览器查看（会出现404错误），需要经过`URL签名`得到真正的可访问的URL地址,当然，`V3.4.1`版本可直接从BmobFile中获得可访问的文件地址。
                Log.i("bmob", "onProgress :" + isFinish + "---" + fileNames + "---" + urls + "----" + files);
                if(isFinish) {
                    for (BmobFile file : files) {
                        if(file != null)
                            imgUrls += file.getUrl() + "|";
                    }
                    imgUrls = imgUrls.substring(0, imgUrls.length() - 1);
                    doSubmit();
                }
            }

            @Override
            public void onProgress(int curIndex, int curPercent, int total, int totalPercent) {
                // curIndex    :表示当前第几个文件正在上传
                // curPercent  :表示当前上传文件的进度值（百分比）
                // total       :表示总的上传文件数
                // totalPercent:表示总的上传进度（百分比）
                Log.i("bmob", "onProgress :" + curIndex + "---" + curPercent + "---" + total + "----" + totalPercent);
            }

            @Override
            public void onError(int statuscode, String errormsg) {
                ProgressUtils.hideProgress();
                // TODO Auto-generated method stub
                Log.i("bmob", "批量上传出错：" + statuscode + "--" + errormsg);
            }
        });
    }

    private void doSubmit() {
        // ToDo 相关字段空值限制和判断
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
        res.setImgUrl(imgUrls);
        res.save(this, new SaveListener() {
            @Override
            public void onSuccess() {
                ProgressUtils.hideProgress();
                showToast("提交成功");
                Log.e("info", "Success");
                finish();
            }

            @Override
            public void onFailure(int i, String s) {
                ProgressUtils.hideProgress();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case RecyclerAdapter.ACTION_TAKE_PHOTO: {
                if (resultCode == RESULT_OK) {
                    handleBigCameraPhoto();
                }
            }
            break;
            case RecyclerAdapter.ACTION_PICK_PHOTO: {
                if (resultCode == RESULT_OK) {
                    handlePickPhoto(data);
                }
            }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void handlePickPhoto(Intent data) {
        if(data != null){
            Uri selectedImage = data.getData();
            String picturePath = null;
            try {
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                picturePath = cursor.getString(columnIndex);
                cursor.close();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            mAdapter.addImage(TextUtils.isEmpty(picturePath) ? selectedImage.getPath() : picturePath);
        }
    }

    private void handleBigCameraPhoto() {
        CameraUtil cameraUtil = CameraUtil.getInstance();
        String mCurrentPhotoPath = cameraUtil.getmCurrentPhotoPath();
        if (mCurrentPhotoPath != null) {
            mAdapter.addImage(mCurrentPhotoPath);
            cameraUtil.galleryAddPic(mCurrentPhotoPath);
        }
    }

    private void showToast(String msg){
        Snackbar.make(btnSubmit, msg, Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }

    @Override
    protected void onPause() {
        ProgressUtils.hideProgress();
        super.onPause();
    }
}
