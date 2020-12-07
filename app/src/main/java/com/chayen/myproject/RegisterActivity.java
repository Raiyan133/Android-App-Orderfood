package com.chayen.myproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText mTextFullName;
    EditText mTextUserName;
    EditText mTextEmail;
    EditText mTextPassword;
    EditText mTextConfirmPassword;
    Button mButtonRegister;
    TextView mTextViewLogin;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        mTextFullName = findViewById(R.id.etFullName);
        mTextUserName = findViewById(R.id.etUserName);
        mTextEmail = findViewById(R.id.etEmail);
        mTextPassword = findViewById(R.id.etPassword);
        mTextConfirmPassword = findViewById(R.id.etConfirmPassword);
        mButtonRegister = findViewById(R.id.btReg);
        mTextViewLogin = findViewById(R.id.tvLogin);
        db = new DatabaseHelper(this);

        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(LoginIntent);
            }
        });
        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String FullName = mTextFullName.getText().toString().trim();
                String UserName = mTextUserName.getText().toString().trim();
                String Email = mTextEmail.getText().toString().trim();
                String Password = mTextPassword.getText().toString().trim();
                String ConPass = mTextConfirmPassword.getText().toString().trim();
                if (mTextFullName.length() != 0 && mTextUserName.length() != 0 && mTextEmail.length() != 0 && mTextPassword.length() != 0 && mTextConfirmPassword.length() != 0) {
                    if (Password.equals(ConPass)) {
                        long val = db.addUser(FullName, UserName, Email, Password);
                        if (val > 0) {
                            Toast.makeText(RegisterActivity.this, "You have registered!", Toast.LENGTH_LONG).show();
                            Intent moveToLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(moveToLogin);
                        } else {
                            Toast.makeText(RegisterActivity.this, "Registeraton Error!", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Password not match!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Please Put All!", Toast.LENGTH_LONG).show();
                }
            }
        });


    }




}




