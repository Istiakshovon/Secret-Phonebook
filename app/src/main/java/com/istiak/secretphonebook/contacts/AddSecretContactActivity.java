package com.istiak.secretphonebook.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.istiak.secretphonebook.Constant;
import com.istiak.secretphonebook.R;
import com.istiak.secretphonebook.home.HomeActivity;
import com.istiak.secretphonebook.home.SecretPhonebookActivity;
import com.istiak.secretphonebook.sqlite.SecretPhonebook;
import com.istiak.secretphonebook.sqlite.UserSqlite;
import com.istiak.secretphonebook.user.LoginActivity;

public class AddSecretContactActivity extends AppCompatActivity {
    Button btnDone;
    TextView txtName,txtContact,txtHomeContact,txtCompany,txtWorkNumber,txtEmail,txtJobTitle;
    SecretPhonebook mydb;
    String getUserLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_secret_contact);

        txtName = findViewById(R.id.etxt_name);
        txtContact = findViewById(R.id.etxt_phone_number);
        txtHomeContact = findViewById(R.id.etxt_home_phone_number);
        txtWorkNumber = findViewById(R.id.etxt_work_phone_number);
        txtEmail = findViewById(R.id.etxt_email);
        txtCompany = findViewById(R.id.etxt_company);
        txtJobTitle = findViewById(R.id.etxt_job_title);
        btnDone = findViewById(R.id.btn_done);
        mydb = new SecretPhonebook(AddSecretContactActivity.this);


        //Fetching id from shared preferences
        SharedPreferences sharedPreferences;
        sharedPreferences =getSharedPreferences(Constant.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        getUserLogin = sharedPreferences.getString(Constant.LOGIN_SHARED_PREF, "");

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txtName.getText().toString();
                String contact = txtContact.getText().toString();
                String homeContact = txtHomeContact.getText().toString();
                String workNumber = txtWorkNumber.getText().toString();
                String email = txtEmail.getText().toString();
                String company = txtCompany.getText().toString();
                String jobTitle = txtJobTitle.getText().toString();

                if (name.isEmpty()){
                    txtName.setError("Enter name");
                    txtName.requestFocus();
                }else if (contact.isEmpty()){
                    txtContact.setError("Enter contact number");
                    txtContact.requestFocus();
                }else {
                    int insert = mydb.insertData(getUserLogin,name, contact, homeContact, workNumber, email, company, jobTitle);
                    if (insert==1){
                        Toast.makeText(AddSecretContactActivity.this, "Successfully added secret contact", Toast.LENGTH_SHORT).show();
                    }else if (insert==-1){
                        Toast.makeText(AddSecretContactActivity.this, "The user name is already available", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(AddSecretContactActivity.this, "Contact number is already available", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}
