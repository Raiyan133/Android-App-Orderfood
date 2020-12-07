package com.chayen.myproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText mTextUserName;
    EditText mTextPassword;
    Button mButtonLogin;
    TextView mTextViewResister;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mTextUserName = findViewById(R.id.etAdminUserName);
        mTextPassword = findViewById(R.id.etAdminPass);
        mButtonLogin = findViewById(R.id.btLogin);
        mTextViewResister = findViewById(R.id.tvReg);
        db = new DatabaseHelper(this);
        mTextViewResister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent RegisterIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(RegisterIntent);
            }
        });

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mTextUserName.getText().toString().trim();
                String password = mTextPassword.getText().toString().trim();
                Boolean res = db.checkUser(username, password);
                if(res == true){
                    Toast.makeText(LoginActivity.this,"Success",Toast.LENGTH_SHORT).show();
                    Intent moveToHome = new Intent(LoginActivity.this,HomeActivity.class);
                    startActivity(moveToHome);
                }
                else {
                    Toast.makeText(LoginActivity.this,"Error",Toast.LENGTH_SHORT).show();

                }
            }
        });


    }
}
