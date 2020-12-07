package com.chayen.myproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class OrderMenuActivity extends AppCompatActivity {



    Button mButtonToTotal;
    TextView mealTotalText;
    ArrayList<Food> orders;
    ListView storedOrders;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_menu);

        db = new DatabaseHelper(this);
        mButtonToTotal = findViewById(R.id.btNextToTotal);
        storedOrders = findViewById(R.id.ListViewOrder);
        orders = getListItemData();
        mealTotalText = findViewById(R.id.meal_total);

        OrderAdapter adapter = new OrderAdapter(this, orders);

        storedOrders.setAdapter(adapter);
        adapter.registerDataSetObserver(observer);

        mButtonToTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MoveToTotal = new Intent(OrderMenuActivity.this, HomeActivity.class);
               startActivity(MoveToTotal);
            }
        });
    }

    public int calculateMealTotal(){
        int mealTotal = 0;
        for(Food order : orders){
            mealTotal += order.getmAmount() * order.getmQuantity();
        }
        return mealTotal;
    }

    DataSetObserver observer = new DataSetObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            setMealTotal();
        }
    };

    private ArrayList<Food> getListItemData(){
        ArrayList<Food> listViewItems = new ArrayList<Food>();


        Cursor cursor = db.getListContents();

        // Looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Food student = new Food(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2));

                listViewItems.add(student);
            } while (cursor.moveToNext());
        }

        // return list
        return listViewItems;

}



    public void setMealTotal(){
        mealTotalText.setText(""+ calculateMealTotal()+ " Baht");
    }

}