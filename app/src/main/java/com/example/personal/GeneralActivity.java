package com.example.personal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GeneralActivity extends AppCompatActivity {

    FirebaseUser user;
    DatabaseReference databaseReference;
    String uid;
    String date,description,subject,time;

    TextView textV1,textV2,textV3,textV4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);

        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        textV1 = (TextView) findViewById(R.id.tv1);
        textV2 = (TextView) findViewById(R.id.tv2);
        textV3 = (TextView) findViewById(R.id.tv3);
        textV4 = (TextView) findViewById(R.id.tv4);


        getData(databaseReference,uid);

        /*Bundle bundle = getIntent().getExtras();

        if(bundle!=null)
        {
            String subject = bundle.getString("Subject");
            String date = bundle.getString("Date");
            int hour = bundle.getInt("Hour");
            int minute = bundle.getInt("Minute");
            String description = bundle.getString("Description");
            String Category = bundle.getString("cat");

            subText.setText("\nCategory: "+Category+"\n\nSubject: "+subject+ "\n\nDescription: "+ description + " \n\nDate: "+date+" \n\nTime: Hour:"+hour+ " Minute: "+minute+"\n");

        }*/

    }

    private void getData(DatabaseReference dbf, String id){


        dbf.addValueEventListener(new ValueEventListener() {        /////
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                date = snapshot.child("Category").child(id).child("Education").child("Date").getValue(String.class);
                description = snapshot.child("Category").child(id).child("Education").child("Description").getValue(String.class);
                subject = snapshot.child("Category").child(id).child("Education").child("Subject").getValue(String.class);
                time = snapshot.child("Category").child(id).child("Education").child("Time").getValue(String.class);


                if(!date.isEmpty())
                    textV1.setBackgroundColor(Color.parseColor("#147a8c"));

                textV1.setText("Date: "+date+" \n\nDescription: "+description+" \n\nSubject: "+subject+" \n\ntime: "+time);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }






}