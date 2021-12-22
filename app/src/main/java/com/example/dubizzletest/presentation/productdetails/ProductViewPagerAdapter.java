package com.example.dubizzletest.presentation.productdetails;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.dubizzletest.R;

import java.util.List;

public class ProductViewPagerAdapter extends PagerAdapter {

    LayoutInflater layoutInflater;
    Context context;
    List<String> productImages;

    public ProductViewPagerAdapter(Context context, List<String> productImages) {
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
        Glide.with(context).load(productImages.get(position)).placeholder(new ColorDrawable(Color.LTGRAY))
                .into(ivProductImage);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
