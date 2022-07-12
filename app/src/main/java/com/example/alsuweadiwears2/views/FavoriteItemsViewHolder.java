package com.example.alsuweadiwears2.views;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alsuweadiwears2.R;

public class FavoriteItemsViewHolder extends RecyclerView.ViewHolder {
    private TextView favoriteItemId;
    private ImageView favoriteItemImage;
    public FavoriteItemsViewHolder(@NonNull View itemView) {
        super(itemView);
        favoriteItemId = itemView.findViewById(R.id.fav_item_id);
        favoriteItemImage = itemView.findViewById(R.id.fav_item_image);
    }

    public TextView getFavoriteItemId() {
        return favoriteItemId;
    }

    public void setFavoriteItemId(TextView favoriteItemId) {
        this.favoriteItemId = favoriteItemId;
    }

    public ImageView getFavoriteItemImage() {
        return favoriteItemImage;
    }

    public void setFavoriteItemImage(ImageView favoriteItemImage) {
        this.favoriteItemImage = favoriteItemImage;
    }
}
