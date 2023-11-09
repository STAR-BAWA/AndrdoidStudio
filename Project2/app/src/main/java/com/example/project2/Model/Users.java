package com.example.project2.Model;

public class Users {
    private String Email,Name,Password,Phone,ques1,ques2,ques3;
    public Users()
    {

    }
    public Users(String email, String name, String password, String phone, String ques1, String ques2, String ques3) {
        this.Email = email;
        this.Name = name;
        this.Password = password;
        this.Phone = phone;
        this.ques1 = ques1;
        this.ques2 = ques2;
        this.ques3 = ques3;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        this.Phone = phone;
    }

    public String getQues1() {
        return ques1;
    }

    public void setQues1(String ques1) {
        this.ques1 = ques1;
    }

    public String getQues2() {
        return ques2;
    }

    public void setQues2(String ques2) {
        this.ques2 = ques2;
    }

    public String getQues3() {
        return ques3;
    }

    public void setQues3(String ques3) {
        this.ques3 = ques3;
    }


}
