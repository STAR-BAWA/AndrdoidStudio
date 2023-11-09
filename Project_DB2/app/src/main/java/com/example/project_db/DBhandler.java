package com.example.project_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBhandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
   private static final String DATABASE_NAME = "ProjectDB";
   private static final String TABLE_NAME= "USER";
   private static final String COLUMN_NAME = "user_Name";
   private static final String User_contact_no="user_contact";
   private static final String User_email="Email";
   private static final String UserID="uid";
   private static final String password="pwd";
   private static final String DOb="Dob";
   private static final String address="address";
   private static final String dor="dor";
   private static final String image="image";
   private static final String gender="city";
   private static final String city="City";
   private static final String State="state";
   private static final String pincode="pincode";
   public static int key=0;


   private static final String COLUMN_NO = "phoneNumber";

   public DBhandler(Context context)
   {
    super(context,DATABASE_NAME,null,DATABASE_VERSION);
   }
    @Override
    public void onCreate(SQLiteDatabase db) {
        if(key==0) {
            String query = "CREATE TABLE " + TABLE_NAME + "("
                    +COLUMN_NAME+" TEXT ,"
//                    + User_contact_no + " TEXT,"
                    + User_email + " TEXT ,"
                    + password + " TEXT ,"
//                    + DOb + " TEXT "
                    + address + " TEXT "
//                    + dor + " TEXT "
//                    + image + " TEXT,"
//                    + gender + "TEXT,"
//                    + city + "TEXT,"
//                    + State + "TEXT,"
//                    + pincode+ " TEXT"
                    + ")";
            key++;
         db.execSQL(query);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Create tables again
        onCreate(db);
    }
    void  addDetails(VendorDetailsDB vd)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
//        cv.put(User_contact_no,vd.getContact());
        cv.put(User_email,vd.getEmail());
        cv.put(password,vd.getPassword());
//        cv.put(DOb,vd.getDob());
        cv.put(address,vd.getAddress());
//        cv.put(dor,vd.getDor());
//        cv.put(image,vd.getImage());
//        cv.put(gender,vd.getGender());
//        cv.put(city,vd.getCity());
//        cv.put(State,vd.getstate());
//        cv.put(pincode,vd.getpincode());
        cv.put(COLUMN_NAME,vd.getName());

        db.insert(TABLE_NAME,null,cv);
        db.close();

    }
}
