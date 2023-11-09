
package com.example.project2;

public class user_home_modal {

    String OIN;
    String ISBN;

    public String getOIN() {
        return OIN;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setOIN(String OIN) {
        this.OIN = OIN;
    }

    String Desc;
            String BookName;
    String Seller_contact;


    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getSeller_contact() {
        return Seller_contact;
    }

    public void setSeller_contact(String seller_contact) {
        Seller_contact = seller_contact;
    }

    String book_auth;

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getBook_price() {
        return book_price;
    }

    public void setBook_price(String book_price) {
        this.book_price = book_price;
    }

    String Image;
    String book_price;
    String Rating;


    user_home_modal(){

    }

//    public String getBookDescription() {
//        return BookDescription;
//    }
//
//    public void setBookDescription(String bookDescription) {
//        BookDescription = bookDescription;
//    }

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




    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }
}
