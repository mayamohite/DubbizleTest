package com.example.dubizzletest.presentation.productdetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.example.dubizzletest.R;
import com.example.dubizzletest.base.BaseFragment;
import com.example.dubizzletest.domain.entities.Product;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

import dagger.hilt.android.AndroidEntryPoint;

import static com.example.dubizzletest.presentation.util.UtilKt.PRODUCT;

@AndroidEntryPoint
public class ProductDetailsFragment extends BaseFragment {

    private View fragmentView;
    private Product product;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_product_details, container, false);
        return fragmentView;
    }

    @Override
    public void onViewCreated(@NotNull View view, @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        product = getArguments().getParcelable(PRODUCT);
        initViews();
    }

    private void initViews() {
        TextView productName = fragmentView.findViewById(R.id.tv_product_name);
        TextView productPrice = fragmentView.findViewById(R.id.tv_product_price);
        productName.setText(product.getName());
        productPrice.setText(product.getPrice());
        //Initialise view pager
        ViewPager productViewPager = fragmentView.findViewById(R.id.vp_product_images);
        ProductViewPagerAdapter productViewPagerAdapter = new ProductViewPagerAdapter(getActivity(), product.getImages());
        productViewPager.setOffscreenPageLimit(1);
        productViewPager.setAdapter(productViewPagerAdapter);
        TabLayout tabLayout = fragmentView.findViewById(R.id.tl_image);
        tabLayout.setupWithViewPager(productViewPager);
    }
}
