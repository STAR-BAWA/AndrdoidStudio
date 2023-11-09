package com.example.db_improved;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Dbhandler extends SQLiteOpenHelper {

    public static final int db_version=1;
    public static final String DB_NAME="courseDb";
    public static final String Table_name="mycourses";
    public static final String ID_col="id";
    private  static final String Name_col="Name";
    private  static final String Duration="duration";
    private static final String Description="desc";
    private static final String tracks_col="Tracks";
    public Dbhandler(Context context)
    {
     super(context,DB_NAME,null,db_version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + Table_name + " ("
                + ID_col + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Name_col + " TEXT,"
                + Duration + " TEXT,"
                + Description + "TEXT,"
                + tracks_col + " TEXT)";

        sqLiteDatabase.execSQL(query);
    }

    public void AddNewCourse(String name,String duration,String Desc,String tracks_column){
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(Name_col,name);
        cv.put(Duration,duration);
        cv.put(Description,Desc);
        cv.put(tracks_col,tracks_column);

        db.insert(Table_name,null,cv);
        db.close();
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Table_name);
    }

    public void  readDB(String contact)
    {
        String query="SELECT * FROM coursedb where contact"+contact;
        SQLiteDatabase db=this.getReadableDatabase();
        // db.read(TABLE_NAME,null,query);
        db.close();
    }
    // we have created a new method for reading all the courses.
    public ArrayList<CourseModal> readCourses() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + Table_name, null);

        // on below line we are creating a new array list.
        ArrayList<CourseModal> courseModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                courseModalArrayList.add(new CourseModal(cursorCourses.getString(1),
                        cursorCourses.getString(4),
                        cursorCourses.getString(2),
                        cursorCourses.getString(3)));
            } while (cursorCourses.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close();
        return courseModalArrayList;
    }
}
