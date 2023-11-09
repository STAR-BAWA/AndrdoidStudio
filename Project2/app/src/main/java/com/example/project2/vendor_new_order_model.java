package com.example.project2;

public class vendor_new_order_model {

    String BookName,Image,Seller_contact
,User_address,
User_contact,
    book_price;

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getSeller_contact() {
        return Seller_contact;
    }

    public void setSeller_contact(String seller_contact) {
        Seller_contact = seller_contact;
    }

    public String getUser_address() {
        return User_address;
    }

    public void setUser_address(String user_address) {
        User_address = user_address;
    }

    public String getUser_contact() {
        return User_contact;
    }

    public void setUser_contact(String user_contact) {
        User_contact = user_contact;
    }

    public String getBook_price() {
        return book_price;
    }

    public void setBook_price(String book_price) {
        this.book_price = book_price;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }
}
