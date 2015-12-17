package com.jen.change.exneires.bmob;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.bmob.BTPFileResponse;
import com.bmob.BmobProFile;
import com.bmob.btp.callback.UploadListener;
import com.jen.change.exneires.AppModel;
import com.jen.change.exneires.bean.Res;
import com.jen.change.exneires.bean.User;

import java.io.File;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by chenmingqun on 2015/12/15.
 */

public class BmobUtils {

    private AppModel appModel;

    public BmobUtils(AppModel appModel){
        this.appModel = appModel;
    }
    public Context provideApplicationContext() {
        return this.appModel;
    }

    /**
     * query imgUrl by 
     * @param context
     */
    public static void queryImgUrl(Context context) {
        String URL = BmobProFile.getInstance(context).signURL("8eb0aab174b54dc9bdc307ebd8555274.jpg",
                "http://newfile.codenow.cn:8080/8eb0aab174b54dc9bdc307ebd8555274.jpg",
                "981db4370ae4fbe98811e6a28bc764a6",
                0, null);
        System.out.println(URL);
    }

    /**
     * upload file
     * @param context
     */
    public static void uploadFile(Context context) {
        File file = new File(Environment.getExternalStorageDirectory(), "img.jpg");
        String filePath  = file.getAbsolutePath();
        BTPFileResponse response = BmobProFile.getInstance(context).upload(filePath, new UploadListener() {

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

    /**
     * query data by objectId
     * @param context
     */
    public static void queryResByObjectId(Context context) {
        BmobQuery<Res> bmobQuery = new BmobQuery<>();
        bmobQuery.getObject(context, "87c49ccb13", new GetListener<Res>() {
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

    /**
     * upload Res
     * @param context
     */
    public static void insertRes(Context context) {
        Res res = new Res();
        res.setName("Goods1");
        res.setLocation("北京天安门1");
        res.setType("电子产品1");
        res.setChangeType("免费1");
        res.setWantRes("微笑1");
        res.save(context, new SaveListener() {
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

    public static void insertUser(Context context){
        User user = new User();
        user.setEmail("hah@qq.com");
        user.setUsername("QWERT");
        user.setUsername("qee");
        user.setMobilePhoneNumber("15260");
        user.save(context, new SaveListener() {
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
     * query data by objectId
     * @param context
     */
    public static void queryUseryObjectId(Context context) {
        BmobQuery<User> bmobQuery = new BmobQuery<>();
        bmobQuery.getObject(context, "0f5468eadd", new GetListener<User>() {
            @Override
            public void onSuccess(User res) {
                Log.e("info", res.toString());
            }

            @Override
            public void onFailure(int i, String s) {
                Log.e("inof", "failure");
            }
        });
    }
    
    public static void queryResAll(Context context){
        BmobQuery<Res> query = new BmobQuery<Res>();
        //查询playerName叫“比目”的数据
        //query.addWhereEqualTo("playerName", "比目");
        //返回50条数据，如果不加上这条语句，默认返回10条数据
       // query.setLimit(50);
        //执行查询方法
        query.findObjects(context, new FindListener<Res>() {
            @Override
            public void onSuccess(List<Res> object) {
                // TODO Auto-generated method stub
                Log.e("info", "查询成功：共" + object.size() + "条数据。");
                for (Res res : object) {
                    //获得数据的objectId信息
                    res.getObjectId();
                    //获得createdAt数据创建时间（注意是：createdAt，不是createAt）
                    res.getCreatedAt();
                    Log.e("info", "查询:" + res.toString());
                }
            }

            @Override
            public void onError(int code, String msg) {
                // TODO Auto-generated method stub
                Log.e("info", "查询失败：" + msg);
            }
        });
    }
    
    
}
