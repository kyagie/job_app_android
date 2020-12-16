package com.example.ura_mobo_s18b23_291;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
//import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.material.textfield.TextInputLayout;

public class PersonalDetailsActivity extends AppCompatActivity {

    private TextInputLayout outlinedFNTextField, outlinedLNTextField, filledAddTextField, filledTeleTextField;
    private Button textButton;
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
        //first validate the form then move ahead
        //if this becomes true that means validation is successfull
        if (awesomeValidation.validate()) {
//            Toast.makeText(this, "Validation Successfull", Toast.LENGTH_LONG).show();
            //process the data further
            Intent intent = new Intent(this, JobDetailsActivity.class);
            startActivity(intent);
        }
    }

    public void jobDetails(View view) {
        if (view == textButton) {
            submitForm();
        }
    }



}