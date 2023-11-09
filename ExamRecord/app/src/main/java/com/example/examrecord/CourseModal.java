package com.example.examrecord;

public class CourseModal {

    private  String courseName,courseDuration,courseTracks,courseDescription;
    private int id;

    public String getCourseName(){
        return courseName;
    }
    public void setcourseName(String courseName){
        this.courseName=courseName;
    }

    public String getcourse(){
        return courseDuration;
    }

    public String getCourseDuration(){
        return  courseDuration;
    }
    public void setCourseDuration(String setCourseDuration){
        this.courseDuration=courseDuration;
    }


    public String getCourseTracks(){
        return  courseTracks;
    }
    public void setcourseTrack(String courseTracks)
    {
        this.courseTracks=courseTracks;
    }
     public String getCourseDescription(){
        return  courseDescription;
    }
    public void setCourseDescription(String courseDescription)
    {
        this.courseDescription=courseDescription;
    }
    public int GetId(){return id;
    }

    public void setID(int id)
    {
        this.id=id;
    }

 public CourseModal(String courseName, String courseDuration, String courseTracks, String courseDescription) {
        this.courseName = courseName;
        this.courseDuration = courseDuration;
        this.courseTracks = courseTracks;
        this.courseDescription = courseDescription;
    }

}
