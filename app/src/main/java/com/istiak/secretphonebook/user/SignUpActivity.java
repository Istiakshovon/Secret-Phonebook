package com.istiak.secretphonebook.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.istiak.secretphonebook.R;
import com.istiak.secretphonebook.home.MainActivity;
import com.istiak.secretphonebook.sqlite.UserSqlite;

public class SignUpActivity extends AppCompatActivity {

    Button btnLogin,btnSignUp;
    EditText etxtContact,etxtEmail,etxtPass;
    CheckBox cb;
    UserSqlite mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().hide();
        btnLogin = findViewById(R.id.btn_login);
        btnSignUp = findViewById(R.id.btn_sign_up);
        etxtContact = findViewById(R.id.etxt_contact);
        etxtEmail = findViewById(R.id.etxt_email);
        etxtPass = findViewById(R.id.etxt_pass);
        cb = findViewById(R.id.checkBox);
        mydb=new UserSqlite(SignUpActivity.this);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contact = etxtContact.getText().toString();
                String email = etxtEmail.getText().toString();
                String pass = etxtPass.getText().toString();

                if (contact.isEmpty()){
                    etxtContact.setError("Please, enter your contact number");
                    etxtContact.requestFocus();
                }else if (email.isEmpty()){
                    etxtEmail.setError("Please, enter your email address");
                    etxtEmail.requestFocus();
                }else if (pass.isEmpty()){
                    etxtPass.setError("Please, enter password");
                    etxtPass.requestFocus();
                }else if (contact.length()<10){
                    etxtContact.setError("Please, enter correct number");
                    etxtContact.requestFocus();
                }else if (pass.length()<8){
                    etxtPass.setError("Enter minimum 8 characters");
                    etxtPass.requestFocus();
                }else if (!cb.isChecked()){
                    Toast.makeText(SignUpActivity.this, "Agree with terms.", Toast.LENGTH_SHORT).show();
                    cb.requestFocus();
                }
                else{
                    //insert data in SqliteDatabase
                    int sign_up = mydb.insertData(contact,email,pass);
                    if (sign_up==0){
                        Toast.makeText(SignUpActivity.this, "Contact number is already available", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(SignUpActivity.this, "Successfully sign up", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
