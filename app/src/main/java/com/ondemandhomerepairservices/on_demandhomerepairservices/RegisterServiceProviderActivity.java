package com.ondemandhomerepairservices.on_demandhomerepairservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ondemandhomerepairservices.on_demandhomerepairservices.accounts.ServiceProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class RegisterServiceProviderActivity extends AppCompatActivity{
    private EditText _username, _password, _companyname, _address, _phonenumber, _description;
    private Button btnCancel,btnReset, btnRegister;
    private RadioGroup radioGroup;
    private RadioButton radioButton;

    private ServiceProvider serviceProvider= new ServiceProvider();
    List<ServiceProvider> serviceProviders;

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseServiceProviders = database.getReference("message");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_register);

        _username = (EditText)findViewById(R.id.editTextUsername);
        _password = (EditText)findViewById(R.id.editTextPassword);
        _companyname = (EditText)findViewById(R.id.editTextCompanyName);
        _address = (EditText)findViewById(R.id.editTextAddress);
        _phonenumber = (EditText) findViewById(R.id.editTextPhoneNumber);
        _description = (EditText)findViewById(R.id.editTextGeneralDescription);

        btnCancel=(Button)findViewById(R.id.buttonCancel);
        btnReset = (Button)findViewById(R.id.buttonReset);
        btnRegister = (Button)findViewById(R.id.buttonRegister);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        databaseServiceProviders = FirebaseDatabase.getInstance().getReference("serviceProviders");
        serviceProviders = new ArrayList<>();

        btnReset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                reset();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = _username.getText().toString().trim();
                String password = _password.getText().toString().trim();
                String companyname = _companyname.getText().toString().trim();
                String address = _address.getText().toString().trim();
                String phonenumber =_phonenumber.getText().toString().trim();
                String description  = _description.getText().toString().trim();
                boolean licensed = false;

                if(is_validated(username, password, companyname, address, phonenumber, description)){
                    if(radioGroup.getCheckedRadioButtonId() == -1){
                        Toast.makeText(getApplicationContext(), "Please select if you are licensed", Toast.LENGTH_SHORT);

                    }else{

                        //todo: radio button problem
                        int selectedId = radioGroup.getCheckedRadioButtonId();
                        radioButton = (RadioButton) findViewById(selectedId);

                        if(selectedId == 0){
                            licensed = true;
                        }

                        String id = databaseServiceProviders.push().getKey();

                        //create an account object
                        serviceProvider = new ServiceProvider(id, username, password, companyname, address, phonenumber, description, licensed);

                        //saving the account
                        databaseServiceProviders.child(id).setValue(serviceProvider);
                        finish();
                        startActivity(new Intent(RegisterServiceProviderActivity.this, LoginServiceProvider.class));
                    }
                }


            }
        });


    }

    //Reset fields
    public void reset() {
        _username.getText().clear();
        _password.getText().clear();
        _companyname.getText().clear();
        _address.getText().clear();
        _phonenumber.getText().clear();
        _description.getText().clear();
        return;
    }

    public boolean is_validated(String username, String password, String companyName, String address, String phoneNumber, String generalDescription){
        Pattern p1 = Pattern.compile("[^a-z0-9_]", Pattern.CASE_INSENSITIVE);
        Pattern p2 = Pattern.compile("[^a-z ]", Pattern.CASE_INSENSITIVE);
//        Pattern p3 = Pattern.compile("[^0-9]{10}");
        Pattern p3 = Pattern.compile("[0-9]");

        boolean un = p1.matcher( username ).find();
        boolean cn = p1.matcher(companyName).find();
        boolean ad = p1.matcher(address).find();
        boolean gd = p1.matcher(generalDescription).find();

        //Validate fields
        if (TextUtils.isEmpty( username )) {
            Toast.makeText( getApplicationContext(), "Enter username", Toast.LENGTH_SHORT ).show();
            return false;
        }

        if (TextUtils.isEmpty( password )) {
            Toast.makeText( getApplicationContext(), "Enter password", Toast.LENGTH_SHORT ).show();
            return false;
        }

        if(TextUtils.isEmpty(companyName)) {
            Toast.makeText( getApplicationContext(), "Enter company", Toast.LENGTH_SHORT ).show();
            return false;
        }

        if(TextUtils.isEmpty(address)){
            Toast.makeText(getApplicationContext(), "Enter address", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(TextUtils.isEmpty(phoneNumber)){
            Toast.makeText(getApplicationContext(), "Enter phone number", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (un) {
            Toast.makeText( getApplicationContext(), "HomeOwner name can only contain letter, number and underscore", Toast.LENGTH_SHORT ).show();
            return false;
        }

        if (password.length() < 6) {
            Toast.makeText( getApplicationContext(), "Password too short, enter minimum 6 characters", Toast.LENGTH_SHORT ).show();
            return false;
        }

        if(phoneNumber.length()!=10){
            Toast.makeText(getApplicationContext(), "Phone number should have length 10", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!phoneNumber.matches("[0-9]+")){
            Toast.makeText(getApplicationContext(), "Invalid phone number", Toast.LENGTH_SHORT).show();

        }

        if(ad) {
            Toast.makeText(getApplicationContext(), "Address can only contain letter, number and underscore", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(gd) {
            Toast.makeText(getApplicationContext(), "General description can only contains letter, number and underscore", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(cn) {
            Toast.makeText(getApplicationContext(), "Company name can only contains letter", Toast.LENGTH_SHORT).show();
            return false;
        }


        return true;
    }

}
