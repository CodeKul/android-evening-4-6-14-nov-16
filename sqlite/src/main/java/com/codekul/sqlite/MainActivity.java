package com.codekul.sqlite;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private DbHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new DbHelper(this, "mydb", null, 1);

        findViewById(R.id.btnInsert).setOnClickListener(this::click);
        findViewById(R.id.btnUpdate).setOnClickListener(this::click);
        findViewById(R.id.btnDelete).setOnClickListener(this::click);
        findViewById(R.id.btnDisplay).setOnClickListener(this::click);
    }

    private void click(View view) {
        if(view.getId() == R.id.btnInsert) insert();
        if(view.getId() == R.id.btnUpdate) update();
        if(view.getId() == R.id.btnDelete) delete();
        if(view.getId() == R.id.btnDisplay) display();
    }


    private void insert(){

        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("myName",getMyName());
        values.put("myAge",getMyAge());
        db.insert("myTable",null,values);

        db.close();
    }

    private void update() {

    }

    private void delete() {

    }

    private void display() {

        SQLiteDatabase db = helper.getReadableDatabase();

        String table = "myTable";
        String[] columns = {"myName"};
        String selection = "myAge = ?";
        String[] selectionArgs = {""+getMyAge()};
        String groupBy = null;
        String having =null;
        String orderBy = null;

        Cursor cursor = db.query(table,columns,selection,selectionArgs,groupBy,having,orderBy);

        if(cursor.moveToNext()){
            String myName = cursor.getString(cursor.getColumnIndex("myName"));
            //Integer myAge = cursor.getInt(cursor.getColumnIndex("myAge"));
            //Log.i("@codekul","Name - "+myName +" Age - "+myAge);
            Log.i("@codekul","Name - "+myName );
            ((EditText)findViewById(R.id.edtMyName)).setText(myName);
        }
        db.close();
    }

    private String getMyName() {
        return  ((EditText)findViewById(R.id.edtMyName)).getText().toString();
    }

    private Integer getMyAge() {
        return  Integer.parseInt(((EditText)findViewById(R.id.edtMyAge))
                .getText()
                .toString());
    }
}
