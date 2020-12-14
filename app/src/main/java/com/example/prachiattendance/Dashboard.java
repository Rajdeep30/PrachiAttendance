package com.example.prachiattendance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Dashboard extends AppCompatActivity {

    TextView datex, dayx, classes;
    String dateString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        datex = findViewById(R.id.date_db);
        dayx = findViewById(R.id.day_db);

        dateString = findDate();

        datex.setText(""+dateString);


    }

    public static String findDate()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        System.out.println(formatter.format(date));

        return formatter.format(date);
    }
}