package com.example.alsuweadiwears2.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arasthel.asyncjob.AsyncJob;
import com.example.alsuweadiwears2.R;
import com.example.alsuweadiwears2.controllers.FavoriteItemsAdapter;
import com.example.alsuweadiwears2.models.Product;
import com.example.alsuweadiwears2.models.ProductManager;
import com.example.alsuweadiwears2.models.ProductUtils;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;


public class FavoriteFragment extends Fragment {

 private List<Product> products = new ArrayList<>();
  FavoriteItemsAdapter adapter;
  ProductManager manager;
  RecyclerView recyclerView;
  ProductUtils utils;
  private Product deletedProduct;

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
            AsyncJob.doInBackground(new AsyncJob.OnBackgroundJob() {
                @Override
                public void doOnBackground() {
                    try {
                        for(Product product: manager.retrieveProducts()){
                            for(Product p : baseProducts){
                                if(product.getId() == p.getId()){
                                    product.setPhoto(p.getPhoto());
                                    product.setBrand(p.getBrand());
                                    product.setCategory(p.getCategory());
                                    product.setDetails(p.getDetails());
                                    product.setLiked(true);
                                    product.setSizes(p.getSizes());
                                }

                            }
                            products.add(product);

                        }

                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // Create a fake result (MUST be final)
                    final boolean result = true;


                    AsyncJob.doOnMainThread(new AsyncJob.OnMainThreadJob() {
                        @Override
                        public void doInUIThread() {

                        }
                    });
                }
            });

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
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
            itemTouchHelper.attachToRecyclerView(recyclerView);
        }
        return view;
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT| ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            final int position = viewHolder.getAdapterPosition();
            switch (direction){
                case ItemTouchHelper.LEFT:
                case ItemTouchHelper.RIGHT:
                    deletedProduct = products.get(position);
                    manager.deleteProduct(deletedProduct);
                    products.remove(position);
                    adapter.notifyItemRemoved(position);
                    adapter.notifyDataSetChanged();
                    Snackbar.make(recyclerView,"product has been deleted",Snackbar.LENGTH_LONG)
                            .setAction("Undo", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                   products.add(position,deletedProduct);
                                   manager.SaveProduct(deletedProduct);
                                   adapter.notifyItemInserted(position);
                                }
                            }).show();
                    break;

            }

        }
    };
}