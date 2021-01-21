package com.example.prachiattendance;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class AdminActivity extends AppCompatActivity {

    int day, month, year;
    EditText date, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        date = findViewById(R.id.eT_date_aa);
        time = findViewById(R.id.eT_time_aa);

        date.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                showDatePicker();
               // Toast.makeText(AdminActivity.this, "clicked", Toast.LENGTH_SHORT).show();
            }
        });

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

            }
        });
        builder.setPositiveButton("Set Date", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                date.setText(day + "/" + month);

            }
        });

        builder.create().show();
    }




}