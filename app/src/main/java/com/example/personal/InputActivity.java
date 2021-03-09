package com.example.personal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

public class InputActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {
    Button saveButton;
    Button DateButton;
    Button Save;
    EditText subject;
    EditText description;
    Spinner sp;
    int H,M;
    String Date,category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        this.getWindow().setStatusBarColor(this.getResources().getColor(R.color.black));

        saveButton = (Button) findViewById(R.id.timeButton);
        DateButton = (Button) findViewById(R.id.dateButton);
        Save = (Button) findViewById(R.id.save);
        subject = (EditText) findViewById(R.id.subjectText);
        description = (EditText) findViewById(R.id.descriptionText);
        sp = (Spinner) findViewById(R.id.aSpinner);

        sp.setOnItemSelectedListener(this);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(),"Time Picker");
            }
        });
        DateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date Picker");
            }
        });

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dashboard = new Intent(InputActivity.this, DashboardActivity.class);

                String sub = subject.getText().toString();
                String des = description.getText().toString();
                //String category = sp.toString();
                /*general.putExtra("Subject",sub);
                general.putExtra("Date",Date);
                general.putExtra("Hour",H);
                general.putExtra("Minute",M);
                general.putExtra("Description",des);
                general.putExtra("cat",category);*/

                FirebaseDatabase.getInstance().getReference("Category").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(category).child("Date")
                        .setValue(Date);
                FirebaseDatabase.getInstance().getReference("Category").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(category).child("Description")
                        .setValue(des);
                FirebaseDatabase.getInstance().getReference("Category").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(category).child("Subject")
                        .setValue(sub);
                FirebaseDatabase.getInstance().getReference("Category").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(category).child("Time")
                        .setValue("Hour: "+H+" Minute: "+M);





                startActivity(dashboard);






            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        TextView textview = (TextView)findViewById(R.id.textDate);
        textview.setText("Date: "+currentDateString);
        Date = currentDateString;

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView textview = (TextView)findViewById(R.id.view1);
        H = hourOfDay;
        M = minute;
        textview.setText("Hour: "+hourOfDay+" Minute: "+minute);
    }

    private void onClick(View view) {
        Intent dashboardIntent = new Intent(InputActivity.this, DashboardActivity.class);
        startActivity(dashboardIntent);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        category = adapterView.getSelectedItem().toString();
        Toast.makeText(this, adapterView.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}