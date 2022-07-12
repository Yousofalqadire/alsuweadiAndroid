package com.example.alsuweadiwears2.controllers;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.alsuweadiwears2.R;
import com.example.alsuweadiwears2.models.Product;


public class ProductAdapter extends ArrayAdapter<Product> {

   private boolean clicked = true;
   private ProductLikeListener productLikeListener;
   private Product product;

    public ProductAdapter(@NonNull Context context, int resource) {

        super(context, resource);
        productLikeListener = (ProductLikeListener) context;
    }
    @Override
    public View getView(int position,  View contentView, ViewGroup parent){
        //supply the layout for your card
        TextView productName = contentView.findViewById(R.id.product_name);
        TextView productPrice = contentView.findViewById(R.id.product_price);
        TextView productDetails = contentView.findViewById(R.id.product_details);
        ImageView productImage = contentView.findViewById(R.id.product_image);
        ImageButton likeBtn = contentView.findViewById(R.id.like_btn);
        ImageButton shareBtn = contentView.findViewById(R.id.share_btn);
        productName.setText(getItem(position).getName());
        productPrice.setText(getItem(position).getPrice()+ "");
        productDetails.setText(getItem(position).getDetails());
        Glide.with(parent.getContext()).asBitmap()
                .load(getItem(position).getPhoto().getUrl())
                .into(productImage);
        productImage.setImageURI(Uri.parse(getItem(position).getPhoto().getUrl()));
        likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clicked){
                    likeBtn.setImageResource(R.drawable.ic_baseline_thumb_up_24);
                    clicked = false;
                    product = new Product(getItem(position).getId(),true);
                    productLikeListener.productIsLiked(product);
                }else{
                    likeBtn.setImageResource(R.drawable.ic_like_impty);
                    clicked = true;
                    product = new Product(getItem(position).getId(),false);
                    productLikeListener.productIsLiked(product);
                }
            }
        });

        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(parent.getContext(), "share is pressed", Toast.LENGTH_SHORT).show();

            }
        });


        return contentView;
    }
}
