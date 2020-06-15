package com.example.sangeet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    private EditText signup_email, signup_pass, signup_cpass;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        updateUI(user);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);

        signup_email = findViewById(R.id.signup_email_id);
        signup_pass = findViewById(R.id.signup_pass_id);
        signup_cpass = findViewById(R.id.signup_cpass_id);

        mAuth = FirebaseAuth.getInstance();
    }

    public void signUp(View view) {
        String email = signup_email.getText().toString().trim();
        String pass = signup_pass.getText().toString().trim();
        String c_pass = signup_cpass.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Email Field is Empty", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(pass)){
            Toast.makeText(this, "Password Field is Empty", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(c_pass)){
            Toast.makeText(this, "Confirm Password Field is Empty", Toast.LENGTH_SHORT).show();
        }
        else if(pass.length() < 8){
            Toast.makeText(this, "Password Must Be Of More Than 8 Character", Toast.LENGTH_SHORT).show();
        }
        else if(pass.equals(c_pass)){
            createAccount(email, pass);
        }else{
            Toast.makeText(this, "Password Does not Match", Toast.LENGTH_SHORT).show();
        }
    }

    private void createAccount(String email, String pass) {
        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success
                            Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });

    }

    public void Login(View view) {
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
    }

    private void updateUI(FirebaseUser user) {
        user = mAuth.getCurrentUser();
        /*-------- Check if user is already logged in or not--------*/
        if (user != null) {
            startActivity(new Intent(RegisterActivity.this,MainActivity.class));
        }
    }
}


