package com.istiak.secretphonebook.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.istiak.secretphonebook.R;
import com.istiak.secretphonebook.sqlite.SecretPhonebook;

public class DetailsSecretPhoneBookActivity extends AppCompatActivity {

    EditText etxtName,etxtContact,etxtCompany,etxtWorkContact,etxtEmail,etxtJob,etxtHomeContact;
    Button btnEdit,btnUpdate;
    SecretPhonebook mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_secret_phone_book);

        etxtName = findViewById(R.id.etxtName);
        etxtContact = findViewById(R.id.etxtContact);
        etxtCompany = findViewById(R.id.etxtCompany);
        etxtWorkContact = findViewById(R.id.etxtWorkContact);
        etxtEmail = findViewById(R.id.etxtEmail);
        etxtJob = findViewById(R.id.etxtJob);
        etxtHomeContact = findViewById(R.id.etxtHomeContact);
        btnEdit = findViewById(R.id.btnEdit);
        btnUpdate = findViewById(R.id.btnUpdate);
        mydb=new SecretPhonebook(DetailsSecretPhoneBookActivity.this);

        String name = getIntent().getExtras().getString("name");
        String contact  = getIntent().getExtras().getString("contact");
        String company = getIntent().getExtras().getString("company");
        String work_contact  = getIntent().getExtras().getString("work_contact");
        String email = getIntent().getExtras().getString("email");
        String job  = getIntent().getExtras().getString("job");
        String home_contact  = getIntent().getExtras().getString("home_contact");


        etxtName.setText(name);
        etxtCompany.setText(company);
        etxtContact.setText(contact);
        etxtWorkContact.setText(work_contact);
        etxtEmail.setText(email);
        etxtJob.setText(job);
        etxtHomeContact.setText(home_contact);

        etxtName.setEnabled(false);
        etxtCompany.setEnabled(false);
        etxtContact.setEnabled(false);
        etxtWorkContact.setEnabled(false);
        etxtEmail.setEnabled(false);
        etxtJob.setEnabled(false);
        etxtHomeContact.setEnabled(false);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                etxtName.setEnabled(true);
                etxtCompany.setEnabled(true);
                etxtContact.setEnabled(true);
                etxtWorkContact.setEnabled(true);
                etxtEmail.setEnabled(true);
                etxtJob.setEnabled(true);
                etxtHomeContact.setEnabled(true);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etxtName.isEnabled()){
                    String txtName = etxtName.getText().toString();
                    String txtCompany = etxtCompany.getText().toString();
                    String txtContact = etxtContact.getText().toString();
                    String txtWorkContact = etxtWorkContact.getText().toString();
                    String txtEmail = etxtEmail.getText().toString();
                    String txtJob = etxtJob.getText().toString();
                    String txtHomeContact = etxtHomeContact.getText().toString();

                    if (txtName.isEmpty()){
                        etxtName.setError("Enter name");
                        etxtName.requestFocus();
                    }else if (txtContact.isEmpty()){
                        etxtContact.setError("Enter contact number");
                        etxtContact.requestFocus();
                    }else{
                        update(txtName,txtCompany,txtContact,txtWorkContact,txtEmail,txtJob,txtHomeContact);
                    }
                }else{
                    Toast.makeText(DetailsSecretPhoneBookActivity.this, "Edit data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void update(String name,String company,String contact, String workContact, String email, String job, String homeContact){
        boolean check=mydb.updateData("user_id",name,contact,homeContact,workContact,email,company,job);

        if (check==true)
        {
            Toast.makeText(DetailsSecretPhoneBookActivity.this, "Data update successfully", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(DetailsSecretPhoneBookActivity.this, "Data not update.Try again!", Toast.LENGTH_SHORT).show();
        }
    }
}
