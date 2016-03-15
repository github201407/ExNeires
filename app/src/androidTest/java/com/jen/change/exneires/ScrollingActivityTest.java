package com.jen.change.exneires;

import android.os.Environment;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.TextView;

import com.bmob.BTPFileResponse;
import com.bmob.BmobProFile;
import com.bmob.btp.callback.DownloadListener;
import com.bmob.btp.callback.GetAccessUrlListener;
import com.bmob.btp.callback.UploadBatchListener;
import com.bmob.btp.callback.UploadListener;
import com.jen.change.exneires.bean.Res;
import com.jen.change.exneires.bmob.BmobUtils;

import java.io.File;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
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

    public void testGetImage(){

    }

    public void testUploadImage(){
//        File file = new File(Environment.getExternalStorageDirectory(), "img.jpg");
        String base = Environment.getExternalStorageDirectory().getAbsolutePath();
        String[] files = new String[]{base + "/img0.jpg",base + "/test.png"};
        BmobProFile.getInstance(activity).uploadBatch(files, new UploadBatchListener() {

            @Override
            public void onSuccess(boolean isFinish,String[] fileNames,String[] urls,BmobFile[] files) {
                // isFinish ：批量上传是否完成
                // fileNames：文件名数组
                // urls        : url：文件地址数组
                // files     : BmobFile文件数组，`V3.4.1版本`开始提供，用于兼容新旧文件服务。
//                注：若上传的是图片，url(s)并不能直接在浏览器查看（会出现404错误），需要经过`URL签名`得到真正的可访问的URL地址,当然，`V3.4.1`版本可直接从BmobFile中获得可访问的文件地址。
                Log.i("bmob","onProgress :"+ isFinish +"---"+ fileNames +"---"+ urls +"----"+ files);
            }

            @Override
            public void onProgress(int curIndex, int curPercent, int total,int totalPercent) {
                // curIndex    :表示当前第几个文件正在上传
                // curPercent  :表示当前上传文件的进度值（百分比）
                // total       :表示总的上传文件数
                // totalPercent:表示总的上传进度（百分比）
                Log.i("bmob","onProgress :"+curIndex+"---"+curPercent+"---"+total+"----"+totalPercent);
            }

            @Override
            public void onError(int statuscode, String errormsg) {
                // TODO Auto-generated method stub
                Log.i("bmob","批量上传出错："+statuscode+"--"+errormsg);
            }
        });
    }

    public void testDownloadImage(){
        String fileName = "img.jpg";
        BmobProFile.getInstance(activity).download(fileName, new DownloadListener() {

            @Override
            public void onSuccess(String fullPath) {
                // TODO Auto-generated method stub
                Log.i("bmob", "下载成功：" + fullPath);
            }

            @Override
            public void onProgress(String localPath, int percent) {
                // TODO Auto-generated method stub
                Log.i("bmob", "download-->onProgress :" + percent);
//                dialog.setProgress(percent);
            }

            @Override
            public void onError(int statuscode, String errormsg) {
                // TODO Auto-generated method stub
//                dialog.dismiss();
                Log.i("bmob", "下载出错：" + statuscode + "--" + errormsg);
            }
        });
    }

    public void testDownload(){
        String fileName = "189a158a35a54ce0a9eaf3281275375e.jpg";
        BmobProFile.getInstance(activity).getAccessURL(fileName, new GetAccessUrlListener() {

            @Override
            public void onError(int errorcode, String errormsg) {
                // TODO Auto-generated method stub
                Log.i("bmob", "获取文件的服务器地址失败：" + errormsg + "(" + errorcode + ")");
            }

            @Override
            public void onSuccess(BmobFile file) {
                // TODO Auto-generated method stub
                Log.i("bmob", "源文件名：" + file.getFilename() + "，可访问的地址：" + file.getUrl());
            }
        });
    }

    public void testuploadFile() {
        File file = new File(Environment.getExternalStorageDirectory(), "Test0.jpg");
        String filePath  = file.getAbsolutePath();
        BTPFileResponse response = BmobProFile.getInstance(activity).upload(filePath, new UploadListener() {

            @Override
            public void onSuccess(String fileName, String url, BmobFile file) {
                Log.i("bmob", "文件上传成功：" + fileName + ",可访问的文件地址：" + file.getUrl());
                // TODO Auto-generated method stub
                // fileName ：文件名（带后缀），这个文件名是唯一的，开发者需要记录下该文件名，方便后续下载或者进行缩略图的处理
                // url        ：文件地址
                // file        :BmobFile文件类型，`V3.4.1版本`开始提供，用于兼容新旧文件服务。
//                注：若上传的是图片，url地址并不能直接在浏览器查看（会出现404错误），需要经过`URL签名`得到真正的可访问的URL地址,当然，`V3.4.1`的版本可直接从'file.getUrl()'中获得可访问的文件地址。
            }

            @Override
            public void onProgress(int progress) {
                // TODO Auto-generated method stub
                Log.i("bmob", "onProgress :" + progress);
            }

            @Override
            public void onError(int statuscode, String errormsg) {
                // TODO Auto-generated method stub
                Log.i("bmob", "文件上传失败：" + errormsg);
            }
        });
    }

}