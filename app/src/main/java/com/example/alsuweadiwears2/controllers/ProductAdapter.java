package com.example.alsuweadiwears2.controllers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
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
   private SharedPreferences sharedPreferences;

    public ProductAdapter(@NonNull Context context, int resource) {

        super(context, resource);
        productLikeListener = (ProductLikeListener) context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
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
        if(sharedPreferences.contains(String.valueOf(getItem(position)))){
            likeBtn.setImageResource(R.drawable.ic_baseline_thumb_up_24);
            clicked = false;
        }
        likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clicked){
                    likeBtn.setImageResource(R.drawable.ic_baseline_thumb_up_24);
                    clicked = false;
                    product = new Product(getItem(position).getId(),true);
                }else{
                    likeBtn.setImageResource(R.drawable.ic_like_impty);
                    clicked = true;
                    product = new Product(getItem(position).getId(),false);
                }
                productLikeListener.productIsLiked(product);
            }
        });

        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(parent.getContext(), "share is pressed", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                String shareableContent  = productName.getText().toString();
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT,"product name");
                intent.putExtra(Intent.EXTRA_TEXT,shareableContent);
                v.getContext().startActivity(Intent.createChooser(intent,"share via"));


            }
        });


        return contentView;
    }
}
