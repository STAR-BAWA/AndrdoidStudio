package com.example.examrecord;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    private static final int Database_ver = 1;
    private static final String Dbname="CourseDb";
    private static final String Table_name="Course_table";
    private static final String courseName="Course_Name ";
    private static final String courseDur="Course_Duration";
    private static final String courseTracks="CourseTracks";
    private static final String courseDes="Description";
    private static final String ID="ID";
    public Database(Context context)
    {
        super(context,Dbname,null,Database_ver);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    String query="CREATE TABLE " +Table_name +"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ courseName+"Text,"+courseDur+"Text,"+courseTracks+"Text,"+courseDes+"TEXT "+")";
    sqLiteDatabase.execSQL(query);
    }

    public void InsertData(String CourseName,String CourseDuration,String CourseTracks,String CourseDes){
        ContentValues cv=new ContentValues();
        cv.put(courseName,CourseName);
        cv.put(courseDur,CourseDuration);
        cv.put(courseTracks,CourseTracks);
        cv.put(courseDes,CourseDes);

        SQLiteDatabase db=this.getWritableDatabase();
        db.insert(Table_name,null,cv);

    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Table_name);
        onCreate(sqLiteDatabase);
    }

    public ArrayList<CourseModal> readCourses(){

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursorCourses=db.rawQuery("SELECT * FROM "+Table_name,null);
        ArrayList<CourseModal> courseModalArrayList=new ArrayList<>();

        if(cursorCourses.moveToFirst()){
            do {
           courseModalArrayList.add(new CourseModal(cursorCourses.getString(1),
                   cursorCourses.getString(1),
                   cursorCourses.getString(2),
                   cursorCourses.getString(3)));
            }
            while(cursorCourses.moveToNext());
        }
        cursorCourses.close();
        return courseModalArrayList;

    }


}
