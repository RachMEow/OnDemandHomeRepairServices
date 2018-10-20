package com.ondemandhomerepairservices.on_demandhomerepairservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class RegisterActivity extends AppCompatActivity {

    Spinner registerAs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerAs = findViewById(R.id.spinnerRegisterAs);
        //create a list of items for the spinner.
        String[] items = new String[]{"Admin", "Service Provider", "Home Owner"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        registerAs.setAdapter(adapter);


        final EditText username = (EditText)findViewById(R.id.editTextUsername);
        final EditText password = (EditText)findViewById(R.id.editTextPassword);
        final EditText firstname = (EditText) findViewById(R.id.editTextFirstName);
        final EditText lastname = (EditText)findViewById(R.id.editTextLastName);


    }

    public void OnMainButton(View view){

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivityForResult(intent, 0);
    }
}
