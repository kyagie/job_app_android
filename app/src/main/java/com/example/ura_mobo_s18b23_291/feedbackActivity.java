package com.example.ura_mobo_s18b23_291;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class feedbackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        PersonalDetailsActivity.PersonalDetails.PersonalDetailsDbHelper h = new PersonalDetailsActivity.PersonalDetails.PersonalDetailsDbHelper(getBaseContext());

        SQLiteDatabase db = h.getWritableDatabase();
        PersonalDetailsActivity.PersonalDetails.Details d = new PersonalDetailsActivity.PersonalDetails.Details();

        String[] projection = {
                BaseColumns._ID,
                d.COLUMN_NAME_TITLE,
                d.COLUMN_NAME_FN
        };
        String selection = d.COLUMN_NAME_TITLE;

        Cursor cursor = db.query(
                d.TABLE_NAME,
                projection,
                selection,
                null,
                null,
                null,
                null

        );
        List itemIds = new ArrayList<>();
        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(d._ID));
            itemIds.add(itemId);
        }
        cursor.close();


//        Toast.makeText(getApplicationContext(),"Here:  "+ projection[2] + " saved", Toast.LENGTH_LONG).show();



    }
}