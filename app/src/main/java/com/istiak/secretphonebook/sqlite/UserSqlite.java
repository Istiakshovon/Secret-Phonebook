package com.istiak.secretphonebook.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class UserSqlite  extends SQLiteOpenHelper {

    private static final int DB_VERSION=1;
    private static final String DB_NAME="user.db";
    private static final String TABLE="user";
    private static final String COLUMN2="contact";
    private static final String COLUMN3="email";
    private static final String COLUMN4="password";

    public UserSqlite(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query;
        query="CREATE TABLE "+TABLE+" (contact TEXT PRIMARY KEY, email TEXT, password TEXT)";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);

    }




    //for insert data
    public int insertData(String contact,String email,String password)
    {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN2,contact);
        values.put(COLUMN3,email);
        values.put(COLUMN4,password);

            long check=db.insert(TABLE,null,values);
            if (check==-1)
            {
                return 0;

            }
            else
            {
                return 1;
            }



    }



    //for view data
    public Cursor display()
    {
        SQLiteDatabase db=getReadableDatabase();
        Cursor result;
        result=db.rawQuery("SELECT * FROM "+TABLE,null);
        return result;

    }


    //for update
    public boolean updateData(String id,String name,String phone)
    {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN2,name);
        values.put(COLUMN3,phone);

        long check=db.update(TABLE,values,"id = ?",new String[] {id});
        if (check==-1)
        {
            return false;

        }

        else
        {
            return true;
        }

    }




        // Get View
        public int login(String contact,String password) {

            SQLiteDatabase db = this.getReadableDatabase();
            if ((db.rawQuery("SELECT * FROM "+TABLE+" WHERE "+COLUMN2+" = ? AND "+COLUMN4+" = ?", new String[]{contact,password}).getCount()) > 0) {
                return 1;
            }else {
                return 0;
            }

          }


}