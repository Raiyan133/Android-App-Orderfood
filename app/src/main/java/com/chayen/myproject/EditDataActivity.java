package com.chayen.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class EditDataActivity extends AppCompatActivity {

    Button mButtonSave;
    Button mButtonDelete;
    EditText mEditTextName;
    EditText mEditTextPrice;
    TextView mTextViewID;

    DatabaseHelper db;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        mButtonSave = findViewById(R.id.btnSave);
        mButtonDelete = findViewById(R.id.btnDelete);
        mTextViewID = findViewById(R.id.etID);
        mEditTextName = findViewById(R.id.etName);
        mEditTextPrice = findViewById(R.id.etPrice);
        db = new DatabaseHelper(this);

        // Read var from Intent
        Intent intent= getIntent();
        final String MenuID = intent.getStringExtra("MenuID");

        // Show Data
        ShowData(MenuID);

        // btnSave (Save)
        mButtonSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // If Save Complete
                if(UpdateData(MenuID))
                {
                    // Open Form ListUpdate
                    Intent newActivity = new Intent(EditDataActivity.this,EditMenuActivity.class);
                    startActivity(newActivity);
                }
            }
        });

        // btnDelete
        mButtonDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                long flg = db.DeleteData(MenuID);
                if(flg > 0)
                {
                    // Open Form ListUpdate
                    Intent newActivity = new Intent(EditDataActivity.this,EditMenuActivity.class);
                    startActivity(newActivity);

                    Toast.makeText(EditDataActivity.this,"Delete Data Successfully. ",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    public void ShowData(String MenuID)
    {
        mTextViewID = findViewById(R.id.etID);
        mEditTextName = findViewById(R.id.etName);
        mEditTextPrice = findViewById(R.id.etPrice);
        db = new DatabaseHelper(this);

        // Show Data
        String arrData[] = db.SelectData(MenuID);
        if(arrData != null)
        {
            mTextViewID.setText(arrData[0]);
            mEditTextName.setText(arrData[1]);
            mEditTextPrice.setText(arrData[2]);
        }

    }

    public boolean UpdateData(String MenuID)
    {

        // txtName, txtTel
        mEditTextName = findViewById(R.id.etName);
        mEditTextPrice = findViewById(R.id.etPrice);

        // Dialog
        final AlertDialog.Builder adb = new AlertDialog.Builder(this);
        AlertDialog ad = adb.create();

        // Check Name
        if(mEditTextName.getText().length() == 0)
        {
            ad.setMessage("Please enter Name");
            ad.show();
            mEditTextName.requestFocus();
            return false;
        }

        // Check Tel
        if(mEditTextPrice.getText().length() == 0)
        {
            ad.setMessage("Please enter Price");
            ad.show();
            mEditTextPrice.requestFocus();
            return false;
        }

        // new Class DB
        db = new DatabaseHelper(this);

        // Save Data
        long saveStatus = db.UpdateData(MenuID,
                mEditTextName.getText().toString(),
                mEditTextPrice.getText().toString());
        if(saveStatus <=  0)
        {
            ad.setMessage("Error!! ");
            ad.show();
            return false;
        }

        Toast.makeText(EditDataActivity.this,"Update Data Successfully. ",
                Toast.LENGTH_SHORT).show();

        return true;

    }
}
