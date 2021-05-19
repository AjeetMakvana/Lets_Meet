package com.example.letsmeet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    private EditText username , email , password ;
    private AppCompatButton signUp;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = findViewById(R.id.username_sign_up);
        email = findViewById(R.id.email_sign_up);
        password = findViewById(R.id.password_sign_up);
        signUp = findViewById(R.id.sign_up_btn);

        firebaseAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Username = username.getText().toString();
                String Email = email.getText().toString();
                String Password = password.getText().toString();

                firebaseAuth.createUserWithEmailAndPassword(Email , Password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(SignUpActivity.this, "Registered Successfull", Toast.LENGTH_SHORT).show();
                                    UserModel userModel = new UserModel(Username , Email , Password);
                                    reference.child("users").child(firebaseAuth.getCurrentUser().getUid()).setValue(userModel);
                                    startActivity(new Intent(SignUpActivity.this , DashboardActivity.class ));
                                } else{
                                    Toast.makeText(SignUpActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    public void SendToLoginPage(View view) {
        startActivity(new Intent(this , LoginActivity.class));
    }
}