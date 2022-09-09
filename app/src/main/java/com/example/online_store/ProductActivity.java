package com.example.online_store;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.online_store.database.DatabaseContract;
import com.example.online_store.database.DatabaseContract.ProductInfo;
import com.example.online_store.database.DatabaseOpenHelper;

public class ProductActivity extends AppCompatActivity {

    private DatabaseOpenHelper mOpenHelper;
    private int mItemPosition;
    private String productName;
    private String productDescription;
    private float productPrice;
    private int productID;
    private int productCount;
    private int productPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_gallery);
        mOpenHelper = new DatabaseOpenHelper(this);
        Intent intent = getIntent();
        mItemPosition = intent.getIntExtra(RecyclerAdapter.ITEMPOSITIONTAG,0);
        loadProductInfoFromDB();


        ActionBar actionBar;
        actionBar = getSupportActionBar();


        actionBar.setTitle("Product Info");

        Utils.initializeActivity(this);
    }
    private void loadProductInfoFromDB()
    {
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        String selection = ProductInfo.PRODUCT_ID+"= ?";
        String[] selectionArgs = {Integer.toString(mItemPosition)};

        final String[] columns = {
                ProductInfo.PRODUCT_ID,
                ProductInfo.PRODUCT_NAME,
                ProductInfo.PRODUCT_PRICE,
                ProductInfo.PRODUCT_DESCRIPTION,
                ProductInfo.PRODUCT_COUNT,
                ProductInfo.PRODUCT_PICTURE};

        Cursor cursor = db.query(ProductInfo.TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        int productIDPos = cursor.getColumnIndex(ProductInfo.PRODUCT_ID);
        int productNamePos = cursor.getColumnIndex(ProductInfo.PRODUCT_NAME);
        int productPricePos = cursor.getColumnIndex(ProductInfo.PRODUCT_PRICE);
        int productDescriptionPos = cursor.getColumnIndex(ProductInfo.PRODUCT_DESCRIPTION);
        int productCountPos = cursor.getColumnIndex(ProductInfo.PRODUCT_COUNT);
        int productPicturePos = cursor.getColumnIndex(ProductInfo.PRODUCT_PICTURE);

        while(cursor.moveToNext())
        {
            productID=cursor.getInt(productIDPos);
            productName=cursor.getString(productNamePos);
            productDescription= cursor.getString(productDescriptionPos);
            productPrice=cursor.getFloat(productPricePos);
            productCount=cursor.getInt(productCountPos);
            productPicture=cursor.getInt(productPicturePos);
        }
        cursor.close();
        displayProduct();
    }
    private void displayProduct()
    {
        ((TextView) findViewById(R.id.product_profile_name)).setText(productName);
        ((TextView) findViewById(R.id.product_profile_desc)).setText(productDescription);
        ((TextView) findViewById(R.id.product_profile_count)).setText("Available: "+productCount);
        ((TextView) findViewById(R.id.product_profile_price)).setText("Price: "+ productPrice+"$");
        ((ImageView) findViewById(R.id.product_profile_picture_nav)).setImageResource(productPicture);
    }

    @Override
    protected void onDestroy() {
        mOpenHelper.close();
        super.onDestroy();
    }
}