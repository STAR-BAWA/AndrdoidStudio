package com.example.gridview;

public class courseModal {
    String course_name;
    int image_id;

    courseModal(String cname,int id){
        course_name=cname;
        image_id=id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }


}
