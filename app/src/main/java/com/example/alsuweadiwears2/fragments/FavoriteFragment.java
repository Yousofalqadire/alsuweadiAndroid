package com.example.alsuweadiwears2.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alsuweadiwears2.R;
import com.example.alsuweadiwears2.controllers.FavoriteItemsAdapter;
import com.example.alsuweadiwears2.models.Product;
import com.example.alsuweadiwears2.models.ProductManager;
import com.example.alsuweadiwears2.models.ProductUtils;

import java.util.ArrayList;
import java.util.List;


public class FavoriteFragment extends Fragment {

 private List<Product> products = new ArrayList<>();
  FavoriteItemsAdapter adapter;
  ProductManager manager;
  RecyclerView recyclerView;
  ProductUtils utils;

    public FavoriteFragment() {
        // Required empty public constructor
    }


    public static FavoriteFragment newInstance() {
        FavoriteFragment fragment = new FavoriteFragment();

        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        utils = new ProductUtils(context);
        List<Product> baseProducts = utils.getProducts();
        manager = new ProductManager(context);
        products.clear();
        if(manager.retrieveProducts().size() > 0){
            for(Product product: manager.retrieveProducts()){
                 Integer.parseInt(String.valueOf(product.getId())) ;
             for(Product p : baseProducts){
                 if(product.getId() == p.getId()){
                     product.setPhoto(p.getPhoto());
                     product.setBrand(p.getBrand());
                     product.setCategory(p.getCategory());
                     product.setDetails(p.getDetails());
                     product.setLiked(true);
                     product.setSizes(p.getSizes());
                 }
                 products.add(product);



             }

            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        if(view != null){
            recyclerView =  view.findViewById(R.id.favorite_list);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            adapter = new FavoriteItemsAdapter(getContext(),products);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        return view;
    }
}