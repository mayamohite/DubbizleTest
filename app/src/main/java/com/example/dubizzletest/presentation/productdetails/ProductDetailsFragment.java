package com.example.dubizzletest.presentation.productdetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager.widget.ViewPager;

import com.example.dubizzletest.R;
import com.example.dubizzletest.base.BaseFragment;
import com.example.dubizzletest.domain.entities.Product;
import com.example.dubizzletest.presentation.productlist.ProductViewModel;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProductDetailsFragment extends BaseFragment {

    private View fragmentView;
    private ProductViewModel productViewModel;
    TextView productName;
    TextView productPrice;
    ViewPager productViewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_product_details, container, false);
        return fragmentView;
    }

    @Override
    public void onViewCreated(@NotNull View view, @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.product_details);
        productViewModel = new ViewModelProvider(requireActivity()).get(ProductViewModel.class);
        productViewModel.getSelectedProduct().observe(getViewLifecycleOwner(), new Observer<Product>() {
            @Override
            public void onChanged(Product product) {
                setProductDetails(product);
            }
        });
        initViews();
    }

    private void initViews() {
        productName = fragmentView.findViewById(R.id.tv_product_name);
        productPrice = fragmentView.findViewById(R.id.tv_product_price);
        //Initialise view pager
        productViewPager = fragmentView.findViewById(R.id.vp_product_images);
        productViewPager.setOffscreenPageLimit(1);
        TabLayout tabLayout = fragmentView.findViewById(R.id.tl_image);
        tabLayout.setupWithViewPager(productViewPager);
        productName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ProductDetailsFragment.this).navigate(R.id.action_productDetailsFragment_to_dummyActiivity);
            }
        });
    }

    private void setProductDetails(Product product) {
        productName.setText(product.getName());
        productPrice.setText(product.getPrice());
        ProductViewPagerAdapter productViewPagerAdapter = new ProductViewPagerAdapter(getActivity(), product.getImages());
        productViewPager.setAdapter(productViewPagerAdapter);
    }
}
