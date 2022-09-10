package com.example.online_store.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.online_store.Product;
import com.example.online_store.ProductsList;
import com.example.online_store.database.DatabaseContract.ProductInfo;

import java.util.ArrayList;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "Online_Store.db";
    public static final int DB_VERSION = 1;
    public DatabaseOpenHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ProductInfo.SQL_CREATE_TABLE);
        insertProducts(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    private void insertProducts(SQLiteDatabase db)
    {
        ProductsList.getInstance();
        ProductsList.initializeProductsList();
        ArrayList<Product> list = ProductsList.getInstance().getList();
        for(Product product : list)
        {
            ContentValues contentValues = new ContentValues();
            contentValues.put(ProductInfo.PRODUCT_ID,product.getProductID());
            contentValues.put(ProductInfo.PRODUCT_NAME,product.getProductName());
            contentValues.put(ProductInfo.PRODUCT_PRICE,product.getProductPrice());
            contentValues.put(ProductInfo.PRODUCT_DESCRIPTION,product.getProductDescription());
            contentValues.put(ProductInfo.PRODUCT_COUNT,product.getProductCount());
            contentValues.put(ProductInfo.PRODUCT_PICTURE,product.getProductPicture());
            db.insert(ProductInfo.TABLE_NAME,null, contentValues);
        }
        ProductsList.getInstance().getList().clear();


    }
}
