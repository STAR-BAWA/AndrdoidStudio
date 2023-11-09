package com.example.project2;

public class AdminOrderModal {

    String BookDescription;
    String BookName;
    String book_auth;
    String OIN;

    public String getOIN() {
        return OIN;
    }

    public void setOIN(String OIN) {
        this.OIN = OIN;
    }

    String book_price;

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    String Seller_phone;
String ISBN;

    public String getSeller_phone() {
        return Seller_phone;
    }

    public void setSeller_phone(String seller_phone) {
        Seller_phone = seller_phone;
    }

    public String getProduct_Image() {
        return product_Image;
    }

    public void setProduct_Image(String product_Image) {
        this.product_Image = product_Image;
    }

    String product_Image;


    AdminOrderModal()
    {

    }
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
}
