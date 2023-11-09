package com.example.project2;

import android.media.Image;
import android.widget.ImageView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class VendorModal {

    String BookName, BookDescription,book_auth,book_price, product_Image, Seller_phone,Rating;

FirebaseStorage fs=FirebaseStorage.getInstance();

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    StorageReference ref=fs.getReference();

    public String getSeller_phone() {
        return Seller_phone;
    }

    public void setSeller_phone(String seller_phone) {
        Seller_phone = seller_phone;
    }

    VendorModal()
    {

    }
    public VendorModal(String bookName, String bookDescription, String book_auth, String book_price,String product_Image) {
        BookName = bookName;
        BookDescription = bookDescription;
        this.book_auth = book_auth;
        this.book_price = book_price;
        this.product_Image=product_Image;

    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public String getBookDescription() {
        return BookDescription;
    }

    public void setBookDescription(String bookDescription) {
        BookDescription = bookDescription;
    }

    public String getBook_auth() {
        return book_auth;
    }

    public String getProduct_Image() {
        return product_Image;
    }

    public void setProduct_Image(String product_Image) {
        this.product_Image = product_Image;
    }

    public void setBook_auth(String book_auth) {
        this.book_auth = book_auth;
    }

    public String getBook_price() {
        return book_price;
    }

    public void setBook_price(String book_price) {
        this.book_price = book_price;
    }

}
