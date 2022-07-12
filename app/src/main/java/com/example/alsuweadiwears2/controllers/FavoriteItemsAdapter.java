package com.example.alsuweadiwears2.controllers;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.alsuweadiwears2.R;
import com.example.alsuweadiwears2.models.Product;
import com.example.alsuweadiwears2.models.ProductManager;
import com.example.alsuweadiwears2.views.FavoriteItemsViewHolder;

import java.util.ArrayList;
import java.util.List;

public class FavoriteItemsAdapter extends RecyclerView.Adapter<FavoriteItemsViewHolder> {
    Context context;
    List<Product> products;

    public FavoriteItemsAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public FavoriteItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_item,parent,false);
        FavoriteItemsViewHolder holder = new FavoriteItemsViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteItemsViewHolder holder, int position) {

        holder.getFavoriteItemId().setText(products.get(position).getId() + "");
        Glide.with(context).asBitmap().load(products.get(position).getPhoto().getUrl())
                .into(holder.getFavoriteItemImage());
        holder.getFavoriteItemImage().setImageURI(Uri.parse(products.get(position).getPhoto().getUrl()));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
