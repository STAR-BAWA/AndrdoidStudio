package com.example.bookworm_user;

import android.graphics.drawable.Drawable;

public class Product {

    public String title;
    public Drawable productImage;
    public String description;
    public double price;
    public int qty;
    public boolean selected;

    public Product(String title, Drawable productImage, String description,
                   double price, int qty) {
        this.title = title;
        this.productImage = productImage;
        this.description = description;
        this.price = price;
        this.qty=qty;
    }

}
