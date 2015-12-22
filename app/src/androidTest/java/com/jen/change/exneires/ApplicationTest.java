package com.jen.change.exneires;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.test.ApplicationTestCase;
import android.util.Log;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<AppModel> {
    public ApplicationTest() {
        super(AppModel.class);
    }

    public void testUriToString(){
        Uri uri = Uri.parse("content://media/external/images/media/5400");
        Log.e("uri", uri.toString());
        assertEquals("content://media/external/images/media/5400", uri.toString());
    }

    public void testPhotoPath(){
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Context context = AppModel.getApplication();
        assert context != null;
        ContentResolver contentResolver = context.getContentResolver();
        assert contentResolver != null;
        Cursor cursor = contentResolver.query(uri,
                null, null, null, null);
        assert cursor != null;
        cursor.moveToFirst();
        //int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();
        assertEquals("/storage/sdcard0/wostore/download/cache/b47426d0dee0348c56c2513a83cc866d.png", picturePath);
    }

    public void testSubString(){
        String imgUrls = "wqrwoot|isjijfig|";
        imgUrls = imgUrls.substring(0, imgUrls.length()-1);
        assertEquals("wqrwoot|isjijfig|", imgUrls);
    }

}