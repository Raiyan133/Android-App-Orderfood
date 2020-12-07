package com.chayen.myproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class OrderCustomerActivity extends AppCompatActivity {

    Button mButtonNextCus;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_customer);


        mButtonNextCus = findViewById(R.id.btNextCus);
        db = new DatabaseHelper(this);

        mButtonNextCus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent moveToOrder = new Intent(OrderCustomerActivity.this, OrderMenuActivity.class);
                startActivity(moveToOrder);



            }
        });
    }


}
