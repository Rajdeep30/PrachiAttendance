package com.example.prachiattendance;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

public class AdminActivity extends AppCompatActivity {

    int day, month, YYYY, hour,min;
    EditText date, time, msg, subject;
    Button post;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        date = findViewById(R.id.eT_date_aa);
        time = findViewById(R.id.eT_time_aa);
        post = findViewById(R.id.btn_post_aa);
        msg = findViewById(R.id.eT_message_aa);
        subject = findViewById(R.id.eT_subject_aa);

        FirebaseApp.initializeApp(this);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = String.valueOf(date.getText());
                String s1 = String.valueOf(time.getText());
                String s2 = String.valueOf(msg.getText());
                String s3 = String.valueOf(subject.getText());

                databaseReference.child("admin").child("1").child("time").setValue(s1);
                databaseReference.child("admin").child("1").child("date").setValue(s);
                databaseReference.child("admin").child("1").child("Message").setValue(s2);
                databaseReference.child("admin").child("1").child("Subject").setValue(s3);
            }
        });


        date.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                showDatePicker();
               // Toast.makeText(AdminActivity.this, "clicked", Toast.LENGTH_SHORT).show();
            }
        });
time.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        showTimePicker();
    }
});
    }

    private void showTimePicker(){
        AlertDialog.Builder builder = new AlertDialog.Builder(AdminActivity.this);
        View view = LayoutInflater.from(this).inflate(R.layout.timepickerlayout, null);
        builder.setView(view);
        TimePicker tp = view.findViewById(R.id.tp_tpl);
        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                hour=hourOfDay;
                min=minute;
            }
        });
        builder.setPositiveButton("Set Time", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                time.setText(hour+":" + min);
            }
        });
        builder.create().show();
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void showDatePicker() {

        AlertDialog.Builder builder = new AlertDialog.Builder(AdminActivity.this);
        View view = LayoutInflater.from(this).inflate(R.layout.datepicker, null);
        builder.setView(view);
        DatePicker datePicker = view.findViewById(R.id.dp_dp);
        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                day = dayOfMonth;
                month = monthOfYear+1;
                YYYY= year;

            }
        });
        builder.setPositiveButton("Set Date", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                date.setText(day + "/" + month+ "/"+ YYYY);
            }
        });

        builder.create().show();
    }




}