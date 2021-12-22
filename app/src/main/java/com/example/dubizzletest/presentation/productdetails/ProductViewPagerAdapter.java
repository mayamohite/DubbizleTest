package com.example.dubizzletest.presentation.productdetails;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.dubizzletest.R;
import com.example.dubizzletest.presentation.util.ImageCache;

import java.util.List;

import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.android.EntryPointAccessors;
import dagger.hilt.components.SingletonComponent;

public class ProductViewPagerAdapter extends PagerAdapter {

    LayoutInflater layoutInflater;
    Context context;
    List<String> productImages;
    ImageCache imageCache;

    /**
     * EntryPoint is used as inject dependencies in PagerAdapter not supported by Hilt.
     */
    @EntryPoint
    @InstallIn(SingletonComponent.class)
    interface ViewPagerEntryPoint {
        ImageCache getImageCache();
    }

    public ProductViewPagerAdapter(Context context, List<String> productImages) {
        ViewPagerEntryPoint viewPagerEntryPoint = EntryPointAccessors.fromApplication(context, ViewPagerEntryPoint.class);
        imageCache = viewPagerEntryPoint.getImageCache();
        this.context = context;
        this.productImages = productImages;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return productImages.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View view = layoutInflater.inflate(R.layout.row_slider_image, container, false);
        ImageView ivProductImage = view.findViewById(R.id.iv_product);
        Bitmap bitmap = imageCache.getImageFromCache(productImages.get(position));
        if (bitmap != null) {
            ivProductImage.setImageBitmap(bitmap);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
