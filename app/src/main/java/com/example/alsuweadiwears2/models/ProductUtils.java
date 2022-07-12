package com.example.alsuweadiwears2.models;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class ProductUtils {
   public static Context context;
   public static ArrayList<Product> products;

    public ProductUtils(Context context) {
        this.context = context;

    }

    public static String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = context.getAssets().open("alsuwediData.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static ArrayList<Product> getProducts() {
        ArrayList<Product>  products = new ArrayList<>();
        try{
            JSONArray jsonArray = new JSONArray(loadJSONFromAsset());
            for(int i = 0; i< jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.getInt("id");
                String name = jsonObject.getString("name");
                String category = jsonObject.getString("category");
                String brand = jsonObject.getString("brand");
                double price = jsonObject.getDouble("price");
                String details = jsonObject.getString("details");
                JSONObject photo = jsonObject.getJSONObject("photo");
                int photoId = photo.getInt("id");
                String url = photo.getString("url");
                String publicId = photo.getString("publicId");
                int productId = photo.getInt("productId");
                Photo p = new Photo(photoId,url,publicId,productId);
                ArrayList<Size> _Sizes = new ArrayList<>();
                JSONArray sizes = jsonObject.getJSONArray("sizes");
                for(int s = 0; s< sizes.length(); s++){
                    JSONObject size = sizes.getJSONObject(s);
                    int sizeId = size.getInt("id");
                    String value = size.getString("value");
                    int sizePId = size.getInt("productId");
                    int quantity = size.getInt("quantity");
                    Size _size = new Size(sizeId,value,sizePId,quantity);
                    _Sizes.add(_size);
                }
                Product product = new Product(id,name,category,brand,price,details,p,_Sizes,false);
                products.add(product);

            }

        }catch ( JSONException e){
            e.printStackTrace();
        }

        return products;
    }


}
