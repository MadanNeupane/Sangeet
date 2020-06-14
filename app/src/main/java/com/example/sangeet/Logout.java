package com.example.sangeet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Logout extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private TextView text;
    private String name;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        mAuth = FirebaseAuth.getInstance();
    }

    public void logout(View view) {
        mAuth.signOut();
        Toast.makeText(Logout.this, "Logged Out", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(Logout.this, LoginActivity.class));
    }
}