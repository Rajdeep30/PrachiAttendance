package com.example.prachiattendance;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Setup_Acc extends AppCompatActivity {

    EditText std_id, name,email, pass;
    Button submit;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup_account);

        std_id = findViewById(R.id.Id1);
        name = findViewById(R.id.name1);
        email = findViewById(R.id.email1);
        pass = findViewById(R.id.pass1);
        submit = findViewById(R.id.btn_submit);

        FirebaseApp.initializeApp(this);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(std_id.getText().toString()) || TextUtils.isEmpty(name.getText().toString()) || TextUtils.isEmpty(email.getText().toString())
                || TextUtils.isEmpty(pass.getText().toString())){
                    Toast.makeText(Setup_Acc.this, "Please enter all details", Toast.LENGTH_SHORT).show();
                }else if(pass.length()<8){
                    Toast.makeText(Setup_Acc.this, "Password too short", Toast.LENGTH_SHORT).show();
                }

                else
                {

                    String s3 = std_id.getText().toString();
                    String s = name.getText().toString();
                    String s1 = email.getText().toString();
                    String s2 = pass.getText().toString();

                    databaseReference.child("Student_Details").child(s3).child("Name").setValue(s);
                    databaseReference.child("Student_Details").child(s3).child("Email").setValue(s1);
                    databaseReference.child("Student_Details").child(s3).child("Password").setValue(s2);
                    Toast.makeText(Setup_Acc.this, "Student account Setup successfully", Toast.LENGTH_SHORT).show();
                    name.getText().clear();
                    email.getText().clear();
                    pass.getText().clear();
                }
            }
        });

    }
}
