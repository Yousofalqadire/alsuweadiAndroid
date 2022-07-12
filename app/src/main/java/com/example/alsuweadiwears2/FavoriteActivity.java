package com.example.alsuweadiwears2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.alsuweadiwears2.fragments.FavoriteFragment;

public class FavoriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        FavoriteFragment favoriteFragment = FavoriteFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container,favoriteFragment)
                .commit();
    }
}