package com.example.task1;

public class Product {
    private String productName;
    private String productDescription;
    private float productPrice;
    private int productID;
    private int productCount;
    private int productPicture;

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public int getProductID() {
        return productID;
    }

    public int getProductCount() {
        return productCount;
    }

    public int getProductPicture() {
        return productPicture;
    }

    private Product(Builder builder){
        this.productName = builder.productName;
        this.productDescription = builder.productDescription;
        this.productPrice = builder.productPrice;
        this.productID = builder.productID;
        this.productCount = builder.productCount;
        this.productPicture = builder.productPicture;
    }
    public static class Builder{
        private String productName;
        private String productDescription;
        private float productPrice;
        private int productID;
        private int productCount;
        private int productPicture;

        public Builder productName(String productName) {
            this.productName = productName;
            return this;
        }
        public Builder productPrice(float productPrice) {
            this.productPrice = productPrice;
            return this;
        }
        public Builder productID(int productID) {
            this.productID = productID;
            return this;
        }
        public Builder productCount(int productCount) {
            this.productCount = productCount;
            return this;
        }
        public Builder productPicture(int productPicture) {
            this.productPicture = productPicture;
            return this;
        }
        public Builder productDescription(String productDescription) {
            this.productDescription = productDescription;
            return this;
        }
        public Product build()
        {
            Product product = new Product(this);
            return product;
        }
    }
}
