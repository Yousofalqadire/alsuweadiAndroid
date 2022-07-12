package com.example.alsuweadiwears2.models;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductManager {
    private Context context;
    SharedPreferences sharedPreferences;

    public ProductManager(Context context) {
        this.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }
    public void SaveProduct(Product product){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(String.valueOf(product.getId()), product.isLiked());
        editor.apply();
    }
    public void deleteProduct(Product product){
        if(sharedPreferences.contains(String.valueOf(product.getId()))){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove(String.valueOf(product.getId())).commit();
        }
    }
    public List<Product> retrieveProducts(){
        Map<String,?> data = sharedPreferences.getAll();
        List<Product> products = new ArrayList<>();
        for(Map.Entry<String,?> entry: data.entrySet()){

            Product product = new Product(Integer.parseInt(entry.getKey()),(boolean)entry.getValue());
            if(entry.getKey().matches("variations_seed_native_stored")){
                continue;
            }
            products.add(product);
        }
        return products;
    }
}
