package com.example.ura_mobo_s18b23_291;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.material.textfield.TextInputLayout;
public class JobDetailsActivity extends AppCompatActivity {
    private TextInputLayout filledDescTextField, filledQuaTextField;
    private Button button3;
    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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


        awesomeValidation.addValidation(this, R.id.filledDescTextField,  "[A-Za-z0-9 _.,;!\"'/$]*", R.string.descerror);
        awesomeValidation.addValidation(this, R.id.filledQuaTextField,  "[A-Za-z0-9 _.,;!\"'/$]*", R.string.descerror);

        button3.setOnClickListener(this::feedBack);
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