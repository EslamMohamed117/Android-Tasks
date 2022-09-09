package com.example.online_store.database;

import android.provider.BaseColumns;

public final class DatabaseContract {
    private DatabaseContract(){}
    public static final class ProductInfo implements BaseColumns{
        public static final String TABLE_NAME = "product_info";
        public static final String PRODUCT_ID = "product_id";
        public static final String PRODUCT_NAME = "product_name";
        public static final String PRODUCT_PRICE = "product_price";
        public static final String PRODUCT_DESCRIPTION = "product_description";
        public static final String PRODUCT_COUNT = "product_count";
        public static final String PRODUCT_PICTURE = "product_picture";
        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE "+TABLE_NAME+ "(" +
                _ID+" INTEGER PRIMARY KEY, " +
                PRODUCT_ID + " INTEGER UNIQUE NOT NULL, " +
                PRODUCT_NAME + " TEXT NOT NULL, " +
                PRODUCT_PRICE + " REAL NOT NULL, " +
                PRODUCT_DESCRIPTION + " TEXT, " +
                PRODUCT_COUNT + " INTEGER NOT NULL," +
                PRODUCT_PICTURE + " INTEGER NOT NULL" +
                        ")";
    }
}
