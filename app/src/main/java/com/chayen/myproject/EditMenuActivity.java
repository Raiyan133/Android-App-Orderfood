package com.chayen.myproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;

public class EditMenuActivity extends AppCompatActivity {

    EditText mTextNewMenuName;
    EditText mTextNewMenuPrice;
    Button mButtonAdd;
    Button mButtonHome;
    ListView mListView;
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_menu);


        mTextNewMenuName = findViewById(R.id.etNewMenuName);
        mTextNewMenuPrice = findViewById(R.id.etNewMenuPrice);
        mButtonAdd = findViewById(R.id.btAdd);
        mButtonHome = findViewById(R.id.btHome);
        mListView = findViewById(R.id.ListViewEditMenu);
        db = new DatabaseHelper(this);



        final ArrayList<HashMap<String, String>> List = db.SelectAllData();


        SimpleAdapter sAdap;
        sAdap = new SimpleAdapter(EditMenuActivity.this, List, R.layout.column_edit_menu,
                new String[] {"ID_menu", "MenuName", "MenuPrice"},
                new int[] {R.id.tvID, R.id.tvNameMenu, R.id.tvPriceMenu});
        mListView.setAdapter(sAdap);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> myAdapter, View myView, int position, long mylng) {

                // Show on new activity
                Intent newActivity = new Intent(EditMenuActivity.this,EditDataActivity.class);
                newActivity.putExtra("MenuID", List.get(position).get("ID_menu"));
                startActivity(newActivity);

            }
        });

        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NewMenuName = mTextNewMenuName.getText().toString().trim();
                String NewMenuPrice = mTextNewMenuPrice.getText().toString().trim();
                if (mTextNewMenuName.length() != 0 && mTextNewMenuPrice.length() != 0) {
                    long val = db.addMenu(NewMenuName, NewMenuPrice);
                    if (val > 0) {
                        Toast.makeText(EditMenuActivity.this, "You have added new menu", Toast.LENGTH_LONG).show();
                        Intent moveToEdit = new Intent(EditMenuActivity.this,EditMenuActivity.class);
                        startActivity(moveToEdit);
                    } else {
                        Toast.makeText(EditMenuActivity.this, "add new menu Error!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(EditMenuActivity.this, "Please put all!", Toast.LENGTH_LONG).show();
                }

            }
        });


        mButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MoveToHome = new Intent(EditMenuActivity.this, HomeActivity.class);
                startActivity(MoveToHome);
            }
        });


    }

}
