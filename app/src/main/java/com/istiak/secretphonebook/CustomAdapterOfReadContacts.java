package com.istiak.secretphonebook;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static androidx.core.content.ContextCompat.checkSelfPermission;

public class CustomAdapterOfReadContacts extends RecyclerView.Adapter<CustomAdapterOfReadContacts.MyViewHolder> {

    Context context;

    ArrayList<HashMap<String, String>> list;

    //constructor
    public CustomAdapterOfReadContacts(Context context, ArrayList<HashMap<String, String>> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public CustomAdapterOfReadContacts.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.read_contacts, parent, false);
        return new CustomAdapterOfReadContacts.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomAdapterOfReadContacts.MyViewHolder holder, final int position) {
        //set data
        holder.textView.setText(list.get(position).get(Constant.NAME));
        holder.textViewPhone.setText(list.get(position).get(Constant.PHONE_NUMBER));
        //setOnClickLister
        holder.imgCall.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                //direct PhoneCall
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+list.get(position).get(Constant.PHONE_NUMBER)));
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
                smsIntent.putExtra("address"  , new String(list.get(position).get(Constant.PHONE_NUMBER)));
                //message body
                smsIntent.putExtra("sms_body"  , "Sending a message via Secret Phonebook");
                context.startActivity(smsIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView,textViewPhone;
        ImageView imgCall,imgMessage;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text1);
            textViewPhone = itemView.findViewById(R.id.text2);
            imgCall = itemView.findViewById(R.id.imgCall);
            imgMessage = itemView.findViewById(R.id.imgMsg);
        }
    }

}