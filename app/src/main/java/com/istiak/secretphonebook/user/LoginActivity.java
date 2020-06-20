package com.istiak.secretphonebook.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.istiak.secretphonebook.Constant;
import com.istiak.secretphonebook.R;
import com.istiak.secretphonebook.home.HomeActivity;
import com.istiak.secretphonebook.sqlite.UserSqlite;

public class LoginActivity extends AppCompatActivity {

    Button btnCreateAnAccount,btnLogin;
    EditText etxtContact,etxtPassword;
    UserSqlite mydb;
    CheckBox cb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();
        btnCreateAnAccount = findViewById(R.id.btn_sign_up);
        etxtContact = findViewById(R.id.etxt_contact_number);
        etxtPassword = findViewById(R.id.etxt_password);
        btnLogin = findViewById(R.id.btn_login);
        mydb=new UserSqlite(LoginActivity.this);
        cb = findViewById(R.id.remember);

        btnCreateAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contact = etxtContact.getText().toString();
                String pass = etxtPassword.getText().toString();

                if (contact.isEmpty()){
                    etxtContact.setError("Please, enter contact number");
                    etxtContact.requestFocus();
                }else if (pass.isEmpty()){
                    etxtPassword.setError("Please, enter password");
                    etxtPassword.requestFocus();
                }else{
                    int login = mydb.login(contact,pass);
                    if (login==1){
                        //Creating a shared preference

                        SharedPreferences sp = LoginActivity.this.getSharedPreferences(Constant.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                        //Creating editor to store values to shared preferences
                        SharedPreferences.Editor editor = sp.edit();
                        //Adding values to editor
                        editor.putString(Constant.LOGIN_SHARED_PREF, contact);
                        if (cb.isChecked()) {
                            editor.putString(Constant.REMEMBER, "1");
                        }
                        //Saving values to editor
                        editor.apply();

                        //Starting Home activity
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "Contact number or password is wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
