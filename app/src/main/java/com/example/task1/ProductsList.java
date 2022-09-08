package com.example.task1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.task1.database.DatabaseContract;
import com.example.task1.database.DatabaseContract.ProductInfo;
import com.example.task1.database.DatabaseOpenHelper;

import java.util.ArrayList;

public class ProductsList {
    private static ProductsList single_instance = null;
    private static ArrayList<Product> productsList = new ArrayList<Product>();
    private ProductsList()
    {
    }
    public static ProductsList getInstance()
    {
        // To ensure only one instance is created
        if (single_instance == null) {
            synchronized (ProductsList.class){
                if (single_instance == null) {
                    single_instance = new ProductsList();
                }
            }

        }
        return single_instance;
    }
    public static void loadFromDB(DatabaseOpenHelper openHelper)
    {
        SQLiteDatabase db = openHelper.getReadableDatabase();
        final String[] columns = {ProductInfo.PRODUCT_ID, ProductInfo.PRODUCT_NAME, ProductInfo.PRODUCT_PRICE, ProductInfo.PRODUCT_DESCRIPTION, ProductInfo.PRODUCT_COUNT, ProductInfo.PRODUCT_PICTURE};
        Cursor cursor = db.query(ProductInfo.TABLE_NAME, columns, null, null, null, null, ProductInfo.PRODUCT_ID);
        loadProductsFromDB(cursor);
    }

    private static void loadProductsFromDB(Cursor cursor) {
        int productIDPos = cursor.getColumnIndex(ProductInfo.PRODUCT_ID);
        int productNamePos = cursor.getColumnIndex(ProductInfo.PRODUCT_NAME);
        int productPricePos = cursor.getColumnIndex(ProductInfo.PRODUCT_PRICE);
        int productDescriptionPos = cursor.getColumnIndex(ProductInfo.PRODUCT_DESCRIPTION);
        int productCountPos = cursor.getColumnIndex(ProductInfo.PRODUCT_COUNT);
        int productPicturePos = cursor.getColumnIndex(ProductInfo.PRODUCT_PICTURE);

        productsList.clear();

        while(cursor.moveToNext())
        {
            productsList.add(new Product.Builder()
                    .productID(cursor.getInt(productIDPos))
                    .productName(cursor.getString(productNamePos))
                    .productDescription(cursor.getString(productDescriptionPos))
                    .productPrice(cursor.getFloat(productPricePos))
                    .productCount(cursor.getInt(productCountPos))
                    .productPicture(cursor.getInt(productPicturePos))
                    .build()
            );
        }
        cursor.close();

    }

    private void initializeProductsList()
    {
        if(productsList.isEmpty()) {
            productsList.add(
                    new Product.Builder()
                            .productID(0)
                            .productName("Sunglasses")
                            .productDescription("Change your look with nice sunglasses")
                            .productPrice(150)
                            .productCount(30)
                            .productPicture(R.drawable.sunglass)
                            .build()
            );
            productsList.add(
                    new Product.Builder()
                            .productID(1)
                            .productName("Headset")
                            .productDescription("High quality headset, supports stereo 7.1")
                            .productPrice(50)
                            .productCount(450)
                            .productPicture(R.drawable.headset)
                            .build()
            );
            productsList.add(
                    new Product.Builder()
                            .productID(2)
                            .productName("Watch")
                            .productDescription("Monitor you health status with a new smartwatch")
                            .productPrice(1000)
                            .productCount(5)
                            .productPicture(R.drawable.watch)
                            .build()
            );
            productsList.add(
                    new Product.Builder()
                            .productID(3)
                            .productName("Smartphone")
                            .productDescription("Feel the future with the new flagship")
                            .productPrice(5000)
                            .productCount(100)
                            .productPicture(R.drawable.smartphone)
                            .build()
            );
            productsList.add(
                    new Product.Builder()
                            .productID(4)
                            .productName("Laptop")
                            .productDescription("Get your tasks on time with a new high-end laptop")
                            .productPrice(20000)
                            .productCount(12)
                            .productPicture(R.drawable.laptop)
                            .build()
            );
            productsList.add(
                    new Product.Builder()
                            .productID(5)
                            .productName("T-Shirt")
                            .productDescription("100% cotton, S M L XL sizes available")
                            .productPrice(150)
                            .productCount(432)
                            .productPicture(R.drawable.tshirt)
                            .build()
            );
            productsList.add(
                    new Product.Builder()
                            .productID(6)
                            .productName("Perfume")
                            .productDescription("Enchanter perfume")
                            .productPrice(300)
                            .productCount(899)
                            .productPicture(R.drawable.perfume)
                            .build()
            );
            productsList.add(
                    new Product.Builder()
                            .productID(7)
                            .productName("Cover")
                            .productDescription("Protect mobile phone")
                            .productPrice(20)
                            .productCount(3000)
                            .productPicture(R.drawable.cover)
                            .build()
            );
            productsList.add(
                    new Product.Builder()
                            .productID(8)
                            .productName("Toolbox")
                            .productDescription("Home basic tools")
                            .productPrice(1500)
                            .productCount(20)
                            .productPicture(R.drawable.toolbox)
                            .build()
            );
            productsList.add(
                    new Product.Builder()
                            .productID(9)
                            .productName("Keyboard")
                            .productDescription("Mechanical keyboard for gamers")
                            .productPrice(300)
                            .productCount(250)
                            .productPicture(R.drawable.keyboard)
                            .build()
            );
            productsList.add(
                    new Product.Builder()
                            .productID(10)
                            .productName("Mouse")
                            .productDescription("Multi-functional 8 buttons, a mouse for gamers")
                            .productPrice(400)
                            .productCount(90)
                            .productPicture(R.drawable.mouse)
                            .build()
            );
        }
    }
    public ArrayList<Product> getList()
    {
        return productsList;
    }

}
