package com.example.ura_mobo_s18b23_291;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.material.textfield.TextInputLayout;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class JobDetailsActivity extends AppCompatActivity {
    private Button button3;
    private AwesomeValidation awesomeValidation;
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);
    }



    private void submitForm() {

        if (awesomeValidation.validate()) {

            Intent intent = new Intent(this, feedbackActivity.class);
            startActivity(intent);
        }
    }

    public void feedBack(View view) {
        if (view == button3) {
            submitForm();
        }
    }
}