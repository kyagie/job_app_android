package com.example.ura_mobo_s18b23_291;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PersonalDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);
    }
    public void jobDetails(View view) {
        Intent intent = new Intent(this, JobDetailsActivity.class);
        startActivity(intent);
    }
}