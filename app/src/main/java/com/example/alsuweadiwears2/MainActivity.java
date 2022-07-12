package com.example.alsuweadiwears2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.alsuweadiwears2.controllers.ProductAdapter;
import com.example.alsuweadiwears2.controllers.ProductLikeListener;
import com.example.alsuweadiwears2.fragments.FavoriteFragment;
import com.example.alsuweadiwears2.models.Product;
import com.example.alsuweadiwears2.models.ProductManager;
import com.example.alsuweadiwears2.models.ProductUtils;
import com.wenchao.cardstack.CardStack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ProductLikeListener {
  private ProductUtils utils;
  private CardStack cardStack;
  private ProductAdapter adapter;
  private ProductManager productManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cardStack = findViewById(R.id.container);
        utils = new ProductUtils(this);
        ArrayList<Product> products = utils.getProducts();
        cardStack.setContentResource(R.layout.product_item);
        cardStack.setStackMargin(30);
        adapter = new ProductAdapter(this,products.size());
        for(Product product:products){
            adapter.add(product);
        }
        cardStack.setAdapter(adapter);
        productManager = new ProductManager(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
      if(item.getItemId() == R.id.favorite){
          Intent intent = new Intent(MainActivity.this,FavoriteActivity.class);
          startActivity(intent);
      }

        return super.onOptionsItemSelected(item);



    }

    @Override
    public void productIsLiked(Product product) {
        if(product.isLiked()){
            productManager.SaveProduct(product);
        }else{
            productManager.deleteProduct(product);

        }
    }
}