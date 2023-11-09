package com.example.bookworm_user;

import android.widget.ImageView;

public class User_Main_Product_Modal {

    String BookDescription,BookName,ISBN,Seller_phone,book_auth,book_price;
    ImageView img;

    User_Main_Product_Modal(){}


    public String getBookDescription() {
        return BookDescription;
    }

    public void setBookDescription(String bookDescription) {
        BookDescription = bookDescription;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getSeller_phone() {
        return Seller_phone;
    }

    public void setSeller_phone(String seller_phone) {
        Seller_phone = seller_phone;
    }

    public String getBook_auth() {
        return book_auth;
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

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }
}
