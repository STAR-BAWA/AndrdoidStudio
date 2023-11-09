package com.example.splashscreendatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBhandler extends SQLiteOpenHelper {
public static final String DbName="Medical";
public static final int Dbversion=1;
public static final String tableName="Disease";
public static final String patientName="pName";
public static final String AGE="age";
public static final String ID="ID";
public static final String disease="disease";


    public DBhandler(Context context)
    {
        super(context,DbName,null,Dbversion);
    }
//works on this particular screen
    // works on the class
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + tableName + " ("

                + patientName + " TEXT,"
                + AGE + " TEXT,"
                + ID + " TEXT,"
                + disease + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        sqLiteDatabase.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public void addNewCourse(String patientName, String AGE, String disease) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(patientName, patientName);
        values.put(AGE, AGE);
        values.put(ID, ID);
        values.put(disease, disease);

        // after adding all values we are passing
        // content values to our table.
        db.insert(tableName, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
        onCreate(db);
    }
}

