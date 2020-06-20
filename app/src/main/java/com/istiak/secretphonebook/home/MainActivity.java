package com.istiak.secretphonebook.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;

import com.istiak.secretphonebook.Constant;
import com.istiak.secretphonebook.CustomAdapterOfReadContacts;
import com.istiak.secretphonebook.R;
import com.istiak.secretphonebook.contacts.AddPhoneContactsActivity;
import com.istiak.secretphonebook.user.LoginActivity;
import com.istiak.secretphonebook.user.SignUpActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);


        //readContacts
        readContacts();
    }


    private void readContacts(){
        Cursor c;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        c=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,ContactsContract.Contacts.DISPLAY_NAME+" ASC ");

        while (c.moveToNext()){
            //get contact list
            String name=c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String number=c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            //put value into Hashmap
            HashMap<String, String> user_data = new HashMap<>();
            user_data.put(Constant.NAME, name);
            user_data.put(Constant.PHONE_NUMBER, number);

            list.add(user_data);
        }



        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        CustomAdapterOfReadContacts customAdapter = new CustomAdapterOfReadContacts(MainActivity.this,list);
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView

        c.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //menu item click listener
            case R.id.sign_up:
                Intent intent1 = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent1);
                return true;
            case R.id.login:
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                return true;
            case R.id.add_contact:
                Intent intent2 = new Intent(getApplicationContext(), AddPhoneContactsActivity.class);
                startActivity(intent2);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}