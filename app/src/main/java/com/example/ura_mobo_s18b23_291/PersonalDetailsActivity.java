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
//import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.material.textfield.TextInputLayout;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class PersonalDetailsActivity extends AppCompatActivity {

    private TextInputLayout outlinedFNTextField, outlinedLNTextField, filledAddTextField, filledTeleTextField;
    private Button textButton;
    private Spinner spinner;
    private RadioGroup radioGender;
    private RadioButton radioButton;
    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.title_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
//
        outlinedFNTextField = (TextInputLayout) findViewById(R.id.outlinedFNTextField);
        outlinedLNTextField = (TextInputLayout) findViewById(R.id.outlinedLNTextField);
        filledAddTextField = (TextInputLayout) findViewById(R.id.filledAddTextField);
        filledTeleTextField = (TextInputLayout) findViewById(R.id.filledTeleTextField);
//
        textButton = (Button) findViewById(R.id.textButton);
//
        awesomeValidation.addValidation(this, R.id.outlinedFNTextField,  "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.namerror);
        awesomeValidation.addValidation(this, R.id.outlinedLNTextField,  "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.namerror);
        awesomeValidation.addValidation(this, R.id.filledTeleTextField, "^[0-9]{2}[0-9]{8}$", R.string.telerror);
        awesomeValidation.addValidation(this, R.id.filledAddTextField,  "[A-Za-z0-9 _.,;!\"'/$]*", R.string.addresserror);

        textButton.setOnClickListener(this::jobDetails);

    }

    private void submitForm() {
//       PersonalDetails p = new PersonalDetails();
//     PersonalDetails.Details d = new PersonalDetails.Details();
//        PersonalDetails.PersonalDetailsDbHelper h = new PersonalDetails.PersonalDetailsDbHelper(getBaseContext());


        outlinedFNTextField = (TextInputLayout) findViewById(R.id.outlinedFNTextField);
        String FirstName = outlinedFNTextField.getEditText().getText().toString();
        outlinedLNTextField = (TextInputLayout) findViewById(R.id.outlinedLNTextField);
        String LastName = outlinedLNTextField.getEditText().getText().toString();
        spinner = (Spinner) findViewById(R.id.spinner);
        String Title = spinner.getSelectedItem().toString();
        filledTeleTextField = (TextInputLayout) findViewById(R.id.filledTeleTextField);
        String Telephone = filledTeleTextField.getEditText().getText().toString();
        radioGender = (RadioGroup) findViewById(R.id.radioGender);
        int selectedId = radioGender.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selectedId);
        String Gender = radioButton.getText().toString();
        //first validate the form then move ahead
        //if this becomes true that means validation is successfull


        if (awesomeValidation.validate()) {
            ArrayList<String> personalDetails = new ArrayList<String>();
            personalDetails.add(Title);
            personalDetails.add(FirstName);
            personalDetails.add(LastName);
            personalDetails.add(Telephone);
            personalDetails.add(Gender);

            // SQLiteDatabase db = h.getWritableDatabase();
//            ContentValues values = new ContentValues();
//            values.put(d.COLUMN_NAME_TITLE, Title);
//            values.put(d.COLUMN_NAME_FN, FirstName);
//            values.put(d.COLUMN_NAME_LN, LastName);
//            values.put(d.COLUMN_NAME_TEL, Telephone);
//            values.put(d.COLUMN_NAME_GEN, Gender);
//
//            long newRowId = db.insert(d.TABLE_NAME, null, values);
//
//            Toast.makeText(getApplicationContext(),"Personal Details for:  "+ newRowId + " saved", Toast.LENGTH_LONG).show();
//        String id = newRo;


            Intent intent = new Intent(this, JobDetailsActivity.class);
            intent.putExtra("personalDetailsArray", personalDetails);
            startActivity(intent);
        }
    }

    public void jobDetails(View view) {
        if (view == textButton) {
            submitForm();
        }
    }

}