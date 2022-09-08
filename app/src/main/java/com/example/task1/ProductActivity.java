package com.example.task1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_gallery);
        Intent intent = getIntent();
        int itemPosition = intent.getIntExtra(RecyclerAdapter.ITEMPOSITIONTAG,0);
        Product product= ProductsList.getInstance().getList().get(itemPosition);
        ((TextView) findViewById(R.id.product_profile_name)).setText(product.getProductName());
        ((TextView) findViewById(R.id.product_profile_desc)).setText(product.getProductDescription());
        ((TextView) findViewById(R.id.product_profile_count)).setText("Available: "+Integer.toString(product.getProductCount()));
        ((TextView) findViewById(R.id.product_profile_price)).setText("Price: "+ Float.toString(product.getProductPrice())+"$");
        ((ImageView) findViewById(R.id.product_profile_picture_nav)).setImageResource(product.getProductPicture());


        ActionBar actionBar;
        actionBar = getSupportActionBar();


        actionBar.setTitle("Product Info");

        Utils.initializeActivity(this);
    }
}