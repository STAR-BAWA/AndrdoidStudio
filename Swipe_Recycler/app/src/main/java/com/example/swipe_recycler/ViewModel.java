package com.example.swipe_recycler;

import android.widget.ImageView;

public class ViewModel {

    String name;
    int img_code;
    public ViewModel(String name, String age, String contact, int ic_launcher_background) {
        this.name=name;
        this.Age=age;
        this.contact=contact;
    }

    public int getImg_code() {
        return img_code;
    }

    public void setImg_code(int img_code) {
        this.img_code = img_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

     String Age;
    String contact;

    ImageView img;


}
