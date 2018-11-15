package com.ondemandhomerepairservices.on_demandhomerepairservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServiceProviderRegister extends AppCompatActivity{
    private EditText _companyname, _address, _phonenumber, _description;
    private Button btnCancel,btnReset, btnRegister;

//    private Account account = new Account();
//    List<Account> accounts;

    // Write a message to the database
//    FirebaseDatabase database = FirebaseDatabase.getInstance();
//    DatabaseReference databaseAccounts = database.getReference("message");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_register);

//        registerAs = findViewById(R.id.spinnerRegisterAs);
//        //create a list of items for the spinner.
//        String[] items = new String[]{"Admin", "Service Provider", "Home Owner"};

        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//        //set the spinners adapter to the previously created one.
//        registerAs.setAdapter(adapter);

        //Get Firebase auth instance
//        auth = FirebaseAuth.getInstance();

        _companyname = (EditText)findViewById(R.id.editTextCompanyName);
        _address = (EditText)findViewById(R.id.editTextAddress);
        _phonenumber = (EditText) findViewById(R.id.editTextPhoneNumber);
        _description = (EditText)findViewById(R.id.editTextGeneralDescription);

        btnCancel=(Button)findViewById(R.id.buttonCancel);
        btnReset = (Button)findViewById(R.id.buttonReset);
        btnRegister = (Button)findViewById(R.id.buttonRegister);

//        databaseAccounts = FirebaseDatabase.getInstance().getReference("accounts");
//
//        accounts = new ArrayList<>();

        btnReset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                reset();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String id;
                String companyname = _companyname.getText().toString().trim();
                String address = _address.getText().toString().trim();
                String phonenumber =_phonenumber.getText().toString().trim();
                String decription  = _description.getText().toString().trim();
//                String role = registerAs.getSelectedItem().toString();

                Pattern p1 = Pattern.compile("[^a-z0-9_]", Pattern.CASE_INSENSITIVE);
                Pattern p2 = Pattern.compile("[^a-z ]", Pattern.CASE_INSENSITIVE);
                Pattern p3 = Pattern.compile("[^0-9]{10}");
                boolean cn = p2.matcher(companyname).find();
                boolean ad = p1.matcher(address).find();
                boolean gd = p1.matcher(decription).find();

                //Validate fields
                if(ad) {
                    Toast.makeText(getApplicationContext(), "Address can only contain letter, number and underscore", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(gd) {
                    Toast.makeText(getApplicationContext(), "General description can only contains letter, number and underscore", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(cn) {
                    Toast.makeText(getApplicationContext(), "Company name can only contains letter", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(companyname)) {
                    Toast.makeText( getApplicationContext(), "Enter company", Toast.LENGTH_SHORT ).show();
                    return;
                }

                if(TextUtils.isEmpty(phonenumber)){
                    Toast.makeText(getApplicationContext(), "Enter phone number", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(phonenumber.length() < 10){
                    Toast.makeText(getApplicationContext(), "Phone number is too short, enter valid phone number", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(address)){
                    Toast.makeText(getApplicationContext(), "Enter address", Toast.LENGTH_SHORT).show();
                    return;
                }

//                if(TextUtils.isEmpty(decription)){
//                    Toast.makeText(getApplicationContext(), "Enter general description", Toast.LENGTH_SHORT).show();
//                    return;
//                }

//                String id = databaseAccounts.push().getKey();
//
//                //create an account object
//                account = new Account(id, username, password, firstName, lastName, role);
//
//                //saving the account
//                databaseAccounts.child(id).setValue(account);

                finish();
                startActivity(new Intent(ServiceProviderRegister.this, LoginServiceProvider.class));

            }
        });


    }

    //Reset fields
    public void reset() {
        _companyname.getText().clear();
        _address.getText().clear();
        _phonenumber.getText().clear();
        _description.getText().clear();
        return;
    }

}
