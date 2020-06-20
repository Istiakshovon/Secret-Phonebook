package com.istiak.secretphonebook;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.istiak.secretphonebook.home.DetailsSecretPhoneBookActivity;
import com.istiak.secretphonebook.home.HomeActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomAdapterOfSecretContacts extends RecyclerView.Adapter<CustomAdapterOfSecretContacts.MyViewHolder> {

    Context context;

    ArrayList<HashMap<String, String>> contacts;

    //constructor
    public CustomAdapterOfSecretContacts(Context context, ArrayList<HashMap<String, String>> contacts) {
        this.context = context;
        this.contacts = contacts;
    }

    @Override
    public CustomAdapterOfSecretContacts.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.secret_contacts, parent, false);
        return new CustomAdapterOfSecretContacts.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomAdapterOfSecretContacts.MyViewHolder holder, final int position) {
        //set data
        holder.textView.setText(contacts.get(position).get("name"));
        holder.textViewPhone.setText(contacts.get(position).get("contact"));
        //setOnClickLister
        holder.imgCall.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                //direct PhoneCall
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+contacts.get(position).get("contact")));
                context.startActivity(intent);
            }
        });

        holder.imgMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set intent
                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                smsIntent.setType("vnd.android-dir/mms-sms");
                //message to
                smsIntent.putExtra("address"  , new String(contacts.get(position).get("contact")));
                //message body
                smsIntent.putExtra("sms_body"  , "Sending a message via Secret Phonebook");
                context.startActivity(smsIntent);
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsSecretPhoneBookActivity.class);
                intent.putExtra("name",contacts.get(position).get("name"));
                intent.putExtra("contact",contacts.get(position).get("contact"));
                intent.putExtra("company",contacts.get(position).get("company"));
                intent.putExtra("work_contact",contacts.get(position).get("work_contact"));
                intent.putExtra("email",contacts.get(position).get("email"));
                intent.putExtra("job",contacts.get(position).get("job"));
                intent.putExtra("home_contact",contacts.get(position).get("home_contact"));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView,textViewPhone;
        ImageView imgCall,imgMessage,imgDelete;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text1);
            textViewPhone = itemView.findViewById(R.id.text2);
            imgCall = itemView.findViewById(R.id.imgCall);
            imgMessage = itemView.findViewById(R.id.imgMsg);
            imgDelete = itemView.findViewById(R.id.imgDelete);
        }
    }

}