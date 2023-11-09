package com.example.project_db;

public class VendorDetailsDB {

    String name,email,password,dob,address,dor,gender,city,state,pincode,contact;
String img;
    int contact_num;

    VendorDetailsDB(String name,String email,String password,String address,String dob,String dor,String gender,String city,String pincode,String contact,String img,String State){
//    VendorDetailsDB(String name,String email,String password,String address){
        this.name=name;
        this.email=email;
        this.password=password;
        this.address=address;
        this.dor=dor;
        this.dob=dob;
        this.gender=gender;
        this.city=city;
        this.pincode=pincode;
        this.contact=contact;
        this.img=img;
        this.state=State;
    }
    String getContact(){
        return contact;
    }

    void setContact(String con){
        this.contact=con;

    }
    String getName(){
        return name;
    }

    void setName(String name){
        this.name=name;
    }

    String getemail(){
        return email;
    }

    void setemail(String email){
        this.email=email;
    }

    String getstate(){
        return state;
    }

    void setstate(String state){
        this.state=state;
    }

    String getState(){
        return state;
    }

    void setGender(String gender){
        this.gender=gender;
    }

    String getGender(){
        return  gender;
    }

    String getPassword(){return password;}

    void Setpassword(String pwd){
        this.password=pwd;
    }

    String getDob()
    {   return dob;
    }

    void setdob(String dob){
        this.dob=dob;
    }

    void setDor(String dor){
        this.dor=dor;
    }

    String getDor()
    {
        return getDor();
    }


    void setAddress(String address){
        this.address=address;
    }

    String getAddress(){
        return address;
    }

    String getImage(){
        return img;
    }

    void setImage(String img)
    {
     this.img=img;
    }

    String getpincode(){
        return pincode;
    }

    void setpincode(String pc){
        this.pincode=pc;

    }
    String    getEmail(){
        return email;
    }
    void setEmail(String email){
        this.email=email;

    }

    String getCity()
    {
     return city;
    }
    void setCity(String setCity){
        this.city=setCity;
    }

}
