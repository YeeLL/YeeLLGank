package yeell.yeellgank.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * 图片加载工具类
 * Created by YeeLL on 11/24/16.
 */

public class ImageLoader {

    private static ImageLoader mInstance;

    public static ImageLoader getInstance() {
        if (mInstance == null) {
            mInstance = new ImageLoader();
        }
        return mInstance;
    }


    public void loadImage(ImageView imageView, String url, Context context) {
        if (TextUtils.isEmpty(url)) {
            return;
        }

        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);

    }

    public void loadImage(ImageView imageView, String url, Activity context) {
        if (TextUtils.isEmpty(url)) {
            return;
        }

        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);

    }

    public void loadImage(ImageView imageView, String url, Fragment context) {
        if (TextUtils.isEmpty(url)) {
            return;
        }

        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }

    public void loadWithAndHeightImage(int with, int height, String url, ImageView imageView, Context context) {
        if (TextUtils.isEmpty(url)) {
            return;
        }

        Glide.with(context)
                .load(url)
                .override(with, height)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }


    /**
     * 加载类型数据中的图片
     * @param imageView
     * @param url
     * @param context
     */
    public void loadTypeDataImage(ImageView imageView,String url,Context context){
        if (TextUtils.isEmpty(url)){
            return;
        }

        Glide.with(context)
                .load(url)
                .into(imageView);
    }

}
