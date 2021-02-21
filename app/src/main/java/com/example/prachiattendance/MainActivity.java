package com.example.prachiattendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    Button btn, btn1;
    EditText email, pass;
    ProgressDialog progressDialog;
private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn_login);
        btn1 = findViewById(R.id.btn_login1);
        email = findViewById(R.id.email_main);
        pass = findViewById(R.id.pass_main);

        firebaseAuth = FirebaseAuth.getInstance();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(MainActivity.this, "Logged in With Google", Toast.LENGTH_SHORT).show();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View v) {
                if(TextUtils.isEmpty(email.getText().toString()) ||TextUtils.isEmpty(pass.getText().toString())){
                    Toast.makeText(MainActivity.this, "Please enter credentials", Toast.LENGTH_SHORT).show();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches()) {
                    email.setError("Please enter Valid email address");
                }else{

                String mail = email.getText().toString().trim();
                String password =pass.getText().toString().trim();
                firebaseAuth.signInWithEmailAndPassword(mail, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    progressDialog = new ProgressDialog(MainActivity.this);
                                    progressDialog.show();
                                    progressDialog.setContentView(R.layout.progress_bar);
                                    progressDialog.getWindow().setBackgroundDrawableResource(
                                            android.R.color.transparent
                                    );
                                    Thread timer= new Thread(){
                                        @Override
                                        public void run() {
                                            try {
                                                sleep(3000);
                                                startActivity(new Intent(MainActivity.this, AdminActivity.class));
                                                super.run();
                                                progressDialog.dismiss();

                                            }catch (InterruptedException e){
                                                e.printStackTrace();

                                            }

                                        }
                                    };
                                    timer.start();


                                } else {
                                    Toast.makeText(MainActivity.this, "Login Failed as Incorrect Details are given.", Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });}



            }
        });




    }

    /*void configureGoogleSignIn()
    {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        }
    }



*/
}