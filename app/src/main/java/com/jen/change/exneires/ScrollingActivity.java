package com.jen.change.exneires;

import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
<<<<<<< HEAD
=======
import android.util.Log;
import android.view.View;
>>>>>>> 77873facd99f888e558c77378eca53c396b71a10
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.bmob.BTPFileResponse;
import com.bmob.BmobProFile;
import com.bmob.btp.callback.UploadListener;
import com.jen.change.exneires.activity.PostShellActivity;
import com.jen.change.exneires.bean.Res;

import java.io.File;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.SaveListener;

public class ScrollingActivity extends AppCompatActivity {

    private AppModel appModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
<<<<<<< HEAD
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                SubmitRes.Instance(ScrollingActivity.this);
=======
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                PostShellActivity.Instance(ScrollingActivity.this);
>>>>>>> 77873facd99f888e558c77378eca53c396b71a10
            }
        });

        appModel = (AppModel)getApplication();
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
        //initDate();
//        queryData();
//        uploadFile();
        queryImgUrl();
    }

    private void queryImgUrl() {
        String URL = BmobProFile.getInstance(this).signURL("8eb0aab174b54dc9bdc307ebd8555274.jpg",
                "http://newfile.codenow.cn:8080/8eb0aab174b54dc9bdc307ebd8555274.jpg",
                "981db4370ae4fbe98811e6a28bc764a6",
                0, null);
        System.out.println(URL);
    }

    private void uploadFile() {
        File file = new File(Environment.getExternalStorageDirectory(), "img.jpg");
        String filePath  = file.getAbsolutePath();
        BTPFileResponse response = BmobProFile.getInstance(this).upload(filePath, new UploadListener() {

            @Override
            public void onSuccess(String fileName,String url,BmobFile file) {
                Log.i("bmob","文件上传成功："+fileName+",可访问的文件地址："+file.getUrl());
                // TODO Auto-generated method stub
                // fileName ：文件名（带后缀），这个文件名是唯一的，开发者需要记录下该文件名，方便后续下载或者进行缩略图的处理
                // url        ：文件地址
                // file        :BmobFile文件类型，`V3.4.1版本`开始提供，用于兼容新旧文件服务。
//                注：若上传的是图片，url地址并不能直接在浏览器查看（会出现404错误），需要经过`URL签名`得到真正的可访问的URL地址,当然，`V3.4.1`的版本可直接从'file.getUrl()'中获得可访问的文件地址。
            }

            @Override
            public void onProgress(int progress) {
                // TODO Auto-generated method stub
                Log.i("bmob","onProgress :"+progress);
            }

            @Override
            public void onError(int statuscode, String errormsg) {
                // TODO Auto-generated method stub
                Log.i("bmob","文件上传失败："+errormsg);
            }
        });
    }

    public void queryData() {
        BmobQuery<Res> bmobQuery = new BmobQuery<>();
        bmobQuery.getObject(this, "87c49ccb13", new GetListener<Res>() {
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

    private void initDate() {
        Res res = new Res();
        res.setName("Goods");
        res.setLocation("北京天安门");
        res.setType("电子产品");
        res.setChangeType("免费");
        res.setWantRes("微笑");
        res.save(this, new SaveListener() {
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
}
