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

public class LoginActivity extends AppCompatActivity {

    private EditText login_email, login_pass;
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
        setContentView(R.layout.activity_login);

        login_email = findViewById(R.id.login_email_id);
        login_pass = findViewById(R.id.login_pass_id);

        mAuth = FirebaseAuth.getInstance();
    }


    public void login(View view) {
        String l_email = login_email.getText().toString().trim();
        String l_pass = login_pass.getText().toString().trim();

        if(TextUtils.isEmpty(l_email)){
            Toast.makeText(this, "Email Field is Empty", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(l_pass)){
            Toast.makeText(this, "Password Field is Empty", Toast.LENGTH_SHORT).show();
        }else {
            loginUser(l_email,l_pass);
        }
    }

    private void loginUser(String l_email, String l_pass) {
        mAuth.signInWithEmailAndPassword(l_email, l_pass)
                .addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success
                            Toast.makeText(LoginActivity.this, "Logged in Successfully.",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    public void register(View view) {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    private void updateUI(FirebaseUser user) {
        user = mAuth.getCurrentUser();
        /*-------- Check if user is already logged in or not--------*/
        if (user != null) {
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }
    }

}