package com.istiak.secretphonebook.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class SecretPhonebook extends SQLiteOpenHelper {

    private static final int DB_VERSION=1;
    private static final String DB_NAME="phone_book.db";
    private static final String TABLE="secrect_contact";
    private static final String COLUMN_ID="id";
    private static final String COLUMN_CONTACT="contact";
    private static final String COLUMN_NAME="name";
    private static final String COLUMN_COMPANY="company";
    private static final String COLUMN_WORK_CONTACT="work_contact";
    private static final String COLUMN_EMAIL="email";
    private static final String COLUMN_JOB="job";
    private static final String COLUMN_HOME_CONTACT="home_contact";

    public SecretPhonebook(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query;
        query="CREATE TABLE "+TABLE+" (id TEXT, contact TEXT PRIMARY KEY, name TEXT,company TEXT, work_contact TEXT,email TEXT, job TEXT,home_contact TEXT)";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);

    }




    //for insert data
    public int insertData(String id,String DisplayName, String MobileNumber,String HomeNumber, String WorkNumber, String emailID, String company, String jobTitle) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, id);
        values.put(COLUMN_CONTACT, MobileNumber);
        values.put(COLUMN_NAME, DisplayName);
        values.put(COLUMN_COMPANY, company);
        values.put(COLUMN_WORK_CONTACT, WorkNumber);
        values.put(COLUMN_EMAIL, emailID);
        values.put(COLUMN_JOB, jobTitle);
        values.put(COLUMN_HOME_CONTACT, HomeNumber);

        SQLiteDatabase db1 = this.getReadableDatabase();
//        if ((db1.rawQuery("SELECT * FROM " + TABLE + " WHERE " + COLUMN_NAME + " = " + DisplayName, null).getCount()) > 0) {
//            return -1;
//        } else {
            long check = db.insert(TABLE, null, values);
            if (check == -1) {
                return 0;

            } else {
                return 1;
            }
//        }


    }

    //for update
    public boolean updateData(String userId,String DisplayName, String MobileNumber,String HomeNumber, String WorkNumber, String emailID, String company, String jobTitle)
    {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_CONTACT, MobileNumber);
        values.put(COLUMN_NAME, DisplayName);
        values.put(COLUMN_COMPANY, company);
        values.put(COLUMN_WORK_CONTACT, WorkNumber);
        values.put(COLUMN_EMAIL, emailID);
        values.put(COLUMN_JOB, jobTitle);
        values.put(COLUMN_HOME_CONTACT, HomeNumber);

        long check=db.update(TABLE,values,"id = ?",new String[] {userId});
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
    public ArrayList<HashMap<String, String>> GetUsers(String id) {

        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE + " WHERE "+COLUMN_ID+" = "+id;
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            HashMap<String, String> user = new HashMap<>();
            user.put("contact", cursor.getString(cursor.getColumnIndex(COLUMN_CONTACT)));
            user.put("name", cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
            user.put("company", cursor.getString(cursor.getColumnIndex(COLUMN_COMPANY)));
            user.put("work_contact", cursor.getString(cursor.getColumnIndex(COLUMN_WORK_CONTACT)));
            user.put("email", cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));
            user.put("job", cursor.getString(cursor.getColumnIndex(COLUMN_JOB)));
            user.put("home_contact", cursor.getString(cursor.getColumnIndex(COLUMN_HOME_CONTACT)));
            userList.add(user);
        }
        return userList;
    }



    //for delete
    public int deleteData(String id)
    {
        SQLiteDatabase db=getWritableDatabase();

        return db.delete(TABLE,"id=?",new String[] {id});
    }
}