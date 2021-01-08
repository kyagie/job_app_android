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
    private TextInputLayout filledDescTextField, filledQuaTextField;
    private Button button3;
    private AwesomeValidation awesomeValidation;
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        long newRowId = getIntent().getExtras().getLong("newRowId");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);
        Spinner spinner = (Spinner) findViewById(R.id.spinner2);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.job_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        filledDescTextField = (TextInputLayout) findViewById(R.id.filledDescTextField);
        filledQuaTextField = (TextInputLayout) findViewById(R.id.filledQuaTextField);

        button3 = (Button) findViewById(R.id.button3);

        Toast.makeText(getApplicationContext(),"RowID:  "+ newRowId + " received", Toast.LENGTH_LONG).show();

        awesomeValidation.addValidation(this, R.id.filledDescTextField,  "[A-Za-z0-9 _.,;!\"'/$]*", R.string.descerror);
        awesomeValidation.addValidation(this, R.id.filledQuaTextField,  "[A-Za-z0-9 _.,;!\"'/$]*", R.string.descerror);

        button3.setOnClickListener(this::feedBack);
    }

    public static final class PersonalDetails {
        // To prevent someone from accidentally instantiating the contract class,
        // make the constructor private.
        private PersonalDetails() {}

        /* Inner class that defines the table contents */
        public static class Details implements BaseColumns {
            public static final String TABLE_NAME = "personalDetails";
            public static final String COLUMN_NAME_TITLE = "title";
            public static final String COLUMN_NAME_FN = "first_name";
            public static final String COLUMN_NAME_LN = "last_name";
            public static final String COLUMN_NAME_ADD = "address";
            public static final String COLUMN_NAME_TEL = "telephone";
            public static final String COLUMN_NAME_GEN = "gender";
            public static final String COLUMN_NAME_DOB = "dob";
            public static final String COLUMN_NAME_JOB_APPLIED = "job_applied";
            public static final String COLUMN_NAME_QUALIFICATIONS = "qualifications";
            public static final String COLUMN_NAME_DESC = "description";
        }
        private static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + PersonalDetailsActivity.PersonalDetails.Details.TABLE_NAME + " (" +
                        PersonalDetailsActivity.PersonalDetails.Details._ID + " INTEGER PRIMARY KEY," +
                        PersonalDetailsActivity.PersonalDetails.Details.COLUMN_NAME_TITLE + " TEXT," +
                        PersonalDetailsActivity.PersonalDetails.Details.COLUMN_NAME_FN + " TEXT," +
                        PersonalDetailsActivity.PersonalDetails.Details.COLUMN_NAME_LN + " TEXT," +
                        PersonalDetailsActivity.PersonalDetails.Details.COLUMN_NAME_ADD + " TEXT,"+
                        PersonalDetailsActivity.PersonalDetails.Details.COLUMN_NAME_TEL + " TEXT,"+
                        PersonalDetailsActivity.PersonalDetails.Details.COLUMN_NAME_GEN + " TEXT,"+
                        PersonalDetailsActivity.PersonalDetails.Details.COLUMN_NAME_DOB + " TEXT,"+
                        PersonalDetailsActivity.PersonalDetails.Details.COLUMN_NAME_JOB_APPLIED + " TEXT," +
                        PersonalDetailsActivity.PersonalDetails.Details.COLUMN_NAME_QUALIFICATIONS + " TEXT," +
                        PersonalDetailsActivity.PersonalDetails.Details.COLUMN_NAME_DESC + " TEXT)"  ;

        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + PersonalDetailsActivity.PersonalDetails.Details.TABLE_NAME;


        public static class PersonalDetailsDbHelper extends SQLiteOpenHelper {
            // If you change the database schema, you must increment the database version.
            public static final int DATABASE_VERSION = 2;
            public static final String DATABASE_NAME = "personalDetails.db";

            public PersonalDetailsDbHelper(Context context) {
                super(context, DATABASE_NAME, null, DATABASE_VERSION);
            }
            public void onCreate(SQLiteDatabase db) {
                db.execSQL(SQL_CREATE_ENTRIES);
            }
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                // This database is only a cache for online data, so its upgrade policy is
                // to simply to discard the data and start over
                db.execSQL(SQL_DELETE_ENTRIES);
                onCreate(db);
            }
            public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                onUpgrade(db, oldVersion, newVersion);
            }
        }

    }


    private void submitForm() {
        JobDetailsActivity.PersonalDetails p = new JobDetailsActivity.PersonalDetails();
        JobDetailsActivity.PersonalDetails.Details d = new JobDetailsActivity.PersonalDetails.Details();
        JobDetailsActivity.PersonalDetails.PersonalDetailsDbHelper h = new JobDetailsActivity.PersonalDetails.PersonalDetailsDbHelper(getBaseContext());


        if (awesomeValidation.validate()) {
            spinner = (Spinner) findViewById(R.id.spinner2);
            String jobapplied = spinner.getSelectedItem().toString();
            filledQuaTextField = (TextInputLayout) findViewById(R.id.filledQuaTextField);
            String qualifations = filledQuaTextField.getEditText().getText().toString();
            filledDescTextField = (TextInputLayout) findViewById(R.id.filledDescTextField);
            String desc =filledDescTextField.getEditText().getText().toString();

            ContentValues values = new ContentValues();
            values.put(d.COLUMN_NAME_JOB_APPLIED, jobapplied);
            values.put(d.COLUMN_NAME_QUALIFICATIONS, qualifations);
            values.put(d.COLUMN_NAME_DESC, desc);

            String selection =




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