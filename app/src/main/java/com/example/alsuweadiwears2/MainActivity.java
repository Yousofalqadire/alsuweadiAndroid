package com.example.alsuweadiwears2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.arasthel.asyncjob.AsyncJob;
import com.example.alsuweadiwears2.controllers.ProductAdapter;
import com.example.alsuweadiwears2.controllers.ProductLikeListener;
import com.example.alsuweadiwears2.fragments.FavoriteFragment;
import com.example.alsuweadiwears2.models.Product;
import com.example.alsuweadiwears2.models.ProductManager;
import com.example.alsuweadiwears2.models.ProductUtils;
import com.wenchao.cardstack.CardStack;
import com.wenchao.cardstack.DefaultStackEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ProductLikeListener {
  private ProductUtils utils;
  private CardStack cardStack;
  private ProductAdapter adapter;
  private ProductManager productManager;
  private SensorManager sensorManager;
  private Sensor accelerometer;
  private ShakeDetector shakeDetector;
    ArrayList<Product> products;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        productManager = new ProductManager(this);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        shakeDetector = new ShakeDetector();
        cardStack = findViewById(R.id.container);
        new AsyncJob.AsyncJobBuilder<Boolean>().doInBackground(new AsyncJob.AsyncAction<Boolean>() {
            @Override
            public Boolean doAsync() {
                utils = new ProductUtils(MainActivity.this);
                products = utils.getProducts();
                return true;
            }
        }).doWhenFinished(new AsyncJob.AsyncResultAction() {
            @Override
            public void onResult(Object o) {
                cardStack.setContentResource(R.layout.product_item);
                cardStack.setStackMargin(30);
                adapter = new ProductAdapter(MainActivity.this,products.size());
                for(Product product:products){
                    adapter.add(product);
                }
                cardStack.setAdapter(adapter);


            }
        }).create().start();

        shakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {
            @Override
            public void onShake(int count) {
                handleShakeEvent();
            }
        });

   }
   private void handleShakeEvent(){

       new AsyncJob.AsyncJobBuilder<Boolean>().doInBackground(new AsyncJob.AsyncAction<Boolean>() {
        @Override
        public Boolean doAsync() {

            Collections.shuffle(products);
            return true;
        }
    }).doWhenFinished(new AsyncJob.AsyncResultAction() {
        @Override
        public void onResult(Object o) {
            adapter.clear();
            cardStack.setContentResource(R.layout.product_item);
            cardStack.setStackMargin(30);
            adapter = new ProductAdapter(MainActivity.this,products.size());
            for(Product product:products){
                adapter.add(product);
            }
            cardStack.setAdapter(adapter);


        }
    }).create().start();

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
        if(product != null && product.isLiked()){
            productManager.SaveProduct(product);
        }else{
            productManager.deleteProduct(product);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(shakeDetector,accelerometer,sensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(shakeDetector);
    }
}