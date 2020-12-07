package com.chayen.myproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.view.menu.MenuAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "EditMenuActivity";

    public static final String DATABASE_NAME ="project.db";

    public static final String TABLE_USER ="User";
    public static final String COL_USER_1 ="ID_user";
    public static final String COL_USER_2 ="fullname";
    public static final String COL_USER_3 ="username";
    public static final String COL_USER_4 ="email";
    public static final String COL_USER_5 ="password";

    public static final String TABLE_MENU ="Menu";
    public static final String COL_MENU_1 ="ID_menu";
    public static final String COL_MENU_2 ="MenuName";
    public static final String COL_MENU_3 ="MenuPrice";





    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_USER + "(ID_user INTEGER PRIMARY KEY AUTOINCREMENT, fullname TEXT, username TEXT, email TEXT,password TEXT)");
        db.execSQL("CREATE TABLE " + TABLE_MENU + "(ID_menu INTEGER PRIMARY KEY AUTOINCREMENT, MenuName TEXT, MenuPrice TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_MENU);
        onCreate(db);
    }

    //-----------//

    public long addUser(String fname, String uname, String email, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_USER_2, fname);
        contentValues.put(COL_USER_3, uname);
        contentValues.put(COL_USER_4, email);
        contentValues.put(COL_USER_5, pass);
        long res = db.insert(TABLE_USER, null, contentValues);
        db.close();

        return res;
    }

    public boolean checkUser(String uname, String pass){
        String[] columns = { COL_USER_1 };
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_USER_3 + "=?" + " and " + COL_USER_5 + "=?";
        String[] selectionArgs = { uname,pass };
        Cursor cursor = db.query(TABLE_USER,columns,selection,selectionArgs, null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count>0)
            return true;
        else
            return false;
    }

    //---------//

    public long addMenu(String menuN, String menuP) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_MENU_2, menuN);
        contentValues.put(COL_MENU_3, menuP);
        long res = db.insert(TABLE_MENU, null, contentValues);
        db.close();

        return res;
    }

    public Cursor getListContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_MENU, null);
        return data;
    }


    // Select Data
    public String[] SelectData(String strID_menu) {
        // TODO Auto-generated method stub

        try {
            String arrData[] = null;

            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data

            Cursor cursor = db.query(TABLE_MENU, new String[] { "*" },
                    "ID_menu=?",
                    new String[] { String.valueOf(strID_menu) }, null, null, null, null);

            if(cursor != null)
            {
                if (cursor.moveToFirst()) {
                    arrData = new String[cursor.getColumnCount()];
                    /***
                     *  0 = ID_menu
                     *  1 = MenuName
                     *  2 = MenuPrice
                     */
                    arrData[0] = cursor.getString(0);
                    arrData[1] = cursor.getString(1);
                    arrData[2] = cursor.getString(2);
                }
            }
            cursor.close();
            db.close();
            return arrData;

        } catch (Exception e) {
            return null;
        }

    }


    // Show All Data
    public ArrayList<HashMap<String, String>> SelectAllData() {
        // TODO Auto-generated method stub

        try {

            ArrayList<HashMap<String, String>> MyArrList = new ArrayList<>();
            HashMap<String, String> map;

            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data

            String strSQL = "SELECT  * FROM " + TABLE_MENU;
            Cursor cursor = db.rawQuery(strSQL, null);

            if(cursor != null)
            {
                if (cursor.moveToFirst()) {
                    do {
                        map = new HashMap<>();
                        map.put("ID_menu", cursor.getString(0));
                        map.put("MenuName", cursor.getString(1));
                        map.put("MenuPrice", cursor.getString(2));
                        MyArrList.add(map);
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            db.close();
            return MyArrList;

        } catch (Exception e) {
            return null;
        }

    }

    // Update Data
    public long UpdateData(String strID_menu,String strMenuName,String strMenuPrice) {
        // TODO Auto-generated method stub

        try {

            SQLiteDatabase db;
            db = this.getWritableDatabase(); // Write Data

            /**
             *  for API 11 and above
             SQLiteStatement insertCmd;
             String strSQL = "UPDATE " + TABLE_MENU
             + " SET MenuName = ? "
             + " , MenuPrice = ? "
             + " WHERE ID_menu = ? ";

             insertCmd = db.compileStatement(strSQL);
             insertCmd.bindString(1, strMenuName);
             insertCmd.bindString(2, strMenuPrice);
             insertCmd.bindString(3, strID_menu);

             return insertCmd.executeUpdateDelete();
             *
             */
            ContentValues Val = new ContentValues();
            Val.put("MenuName", strMenuName);
            Val.put("MenuPrice", strMenuPrice);

            long rows = db.update(TABLE_MENU, Val, " ID_menu = ?",
                    new String[] { String.valueOf(strID_menu) });

            db.close();
            return rows; // return rows updated.

        } catch (Exception e) {
            return -1;
        }

    }

    // Delete Data
    public long DeleteData(String strID_menu) {
        // TODO Auto-generated method stub

        try {

            SQLiteDatabase db;
            db = this.getWritableDatabase(); // Write Data

            /**
             * for API 11 and above
             SQLiteStatement insertCmd;
             String strSQL = "DELETE FROM " + TABLE_MENU
             + " WHERE ID_menu = ? ";

             insertCmd = db.compileStatement(strSQL);
             insertCmd.bindString(1, strID_menu);

             return insertCmd.executeUpdateDelete();
             *
             */

            long rows = db.delete(TABLE_MENU, "ID_menu = ?",
                    new String[] { String.valueOf(strID_menu) });

            db.close();
            return rows; // return rows deleted.

        } catch (Exception e) {
            return -1;
        }

    }


}