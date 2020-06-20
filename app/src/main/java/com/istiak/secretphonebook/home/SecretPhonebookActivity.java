package com.istiak.secretphonebook.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.istiak.secretphonebook.Constant;
import com.istiak.secretphonebook.CustomAdapterOfSecretContacts;
import com.istiak.secretphonebook.R;
import com.istiak.secretphonebook.contacts.AddSecretContactActivity;
import com.istiak.secretphonebook.sqlite.SecretPhonebook;

import java.util.ArrayList;
import java.util.HashMap;

public class SecretPhonebookActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SecretPhonebook secrectPhonebook;
    String getUserLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secret_phonebook);

        recyclerView = findViewById(R.id.recyclerView);


        //Fetching id from shared preferences
        SharedPreferences sharedPreferences;
        sharedPreferences =getSharedPreferences(Constant.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        getUserLogin = sharedPreferences.getString(Constant.LOGIN_SHARED_PREF, "");

        getSupportActionBar().setTitle("Secret Phonebook");

        secrectPhonebook = new SecretPhonebook(SecretPhonebookActivity.this);


        ArrayList<HashMap<String, String>> userList = secrectPhonebook.GetUsers(getUserLogin);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        CustomAdapterOfSecretContacts customAdapter = new CustomAdapterOfSecretContacts(SecretPhonebookActivity.this,userList);
        recyclerView.setAdapter(customAdapter);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_contact:
                Intent intent3 = new Intent(getApplicationContext(), AddSecretContactActivity.class);
                startActivity(intent3);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
