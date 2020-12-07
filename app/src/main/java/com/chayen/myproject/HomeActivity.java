package com.chayen.myproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    Button mButtonToOrder;
    Button mButtonToEdit;
    Button mButtonLogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        mButtonToOrder = findViewById(R.id.btorder);
        mButtonToEdit = findViewById(R.id.btadd);
        mButtonLogout = findViewById(R.id.btLogout);


        mButtonToOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToOrder = new Intent(HomeActivity.this,OrderCustomerActivity.class);
                startActivity(moveToOrder);
            }
        });

        mButtonToEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToEdit = new Intent(HomeActivity.this,EditMenuActivity.class);
                startActivity(moveToEdit);
            }
        });

        mButtonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToLogin = new Intent(HomeActivity.this,LoginActivity.class);
                startActivity(moveToLogin);
            }
        });



    }
}
