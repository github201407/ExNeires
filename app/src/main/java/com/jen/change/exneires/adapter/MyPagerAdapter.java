package com.jen.change.exneires.adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jen.change.exneires.utils.CameraUtil;

import java.io.File;

/**
 * Created by Administrator on 2015/12/24.
 */
public class MyPagerAdapter extends PagerAdapter {
    private String[] imgs;
    public MyPagerAdapter(String imgUrl) {
        imgs = imgUrl.contains("|") ? imgUrl.split("\\|") : new String[]{imgUrl};
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return imgs.length;
    }

    /**
     * Determines whether a page View is associated with a specific key object
     * as returned by {@link #instantiateItem(ViewGroup, int)}. This method is
     * required for a PagerAdapter to function properly.
     *
     * @param view   Page View to check for association with <code>object</code>
     * @param object Object to check for association with <code>view</code>
     * @return true if <code>view</code> is associated with the key object <code>object</code>
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    private String getFileName(String url){
        return url.substring(url.lastIndexOf("/") + 1, url.length());
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final String imgUrl = imgs[position];
        ImageView imageView = new ImageView(container.getContext());
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = Glide.getPhotoCacheDir(v.getContext(), getFileName(imgUrl));
                if(!file.exists()) return;
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(file), "image/*");
                v.getContext().startActivity(intent);
            }
        });
        CameraUtil.setPic(imageView, imgUrl);
        container.addView(imageView, position);
        return imageView;
    }

    /**
     * Remove a page for the given position.  The adapter is responsible
     * for removing the view from its container, although it only must ensure
     * this is done by the time it returns from {@link #finishUpdate(ViewGroup)}.
     *
     * @param container The containing View from which the page will be removed.
     * @param position  The page position to be removed.
     * @param object    The same object that was returned by
     *                  {@link #instantiateItem(View, int)}.
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeViewAt(position);
    }
}
