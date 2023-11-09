package com.example.project2;

public class passwordShow {

    public String ques1,ques2,ques3;

    passwordShow(String ques1,String ques2,String ques3)
    {
        this.ques1=ques1;
        this.ques2=ques2;
        this.ques3=ques3;
    }

    public void setQues1(String ques1)
    {
     this.ques1=ques1;
    }

    public void setQues2(String ques2)
    {
     this.ques2=ques2;
    }
    public void setQues3(String ques3)
    {
     this.ques1=ques1;
    }

    public String getQues1(){
        return ques1;
    }
}
