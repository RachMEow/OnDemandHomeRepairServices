package com.ondemandhomerepairservices.on_demandhomerepairservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ondemandhomerepairservices.on_demandhomerepairservices.accounts.Account;
import com.ondemandhomerepairservices.on_demandhomerepairservices.accounts.HomeOwner;

import java.util.List;
import java.util.regex.Pattern;

public class RegisterHomeOwnerActivity extends AppCompatActivity {

    private EditText _username, _password, _firstName, _lastName, _address, _postalCode;
    private Button btnclear, btnRegister, btnCancel;

    private HomeOwner homeOwner = new HomeOwner();
    List<HomeOwner> homeOwners;

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseHomeOwners = database.getReference("message");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_owner_register);

        _username = (EditText) findViewById(R.id.editTextUsername);
        _password = (EditText) findViewById(R.id.editTextPassword);
        _firstName = (EditText) findViewById(R.id.editTextFirstName);
        _lastName = (EditText) findViewById(R.id.editTextLastName);
        _address = (EditText) findViewById(R.id.editTextAddress);
        _postalCode = (EditText) findViewById(R.id.editTextPostalCode);

        databaseHomeOwners = FirebaseDatabase.getInstance().getReference("homeOwners");

        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = _username.getText().toString().trim();
                String password = _password.getText().toString().trim();
                String firstName = _firstName.getText().toString().trim();
                String lastName = _lastName.getText().toString().trim();
                String address = _address.getText().toString().trim();
                String postalCode = _postalCode.getText().toString().trim();

                if(is_validate(username, password, firstName, lastName, address, postalCode)){
                      String id = databaseHomeOwners.push().getKey();

                      homeOwner = new HomeOwner(id, username, password, firstName, lastName, address, postalCode);

                    //saving the account
                     databaseHomeOwners.child(id).setValue(homeOwner);

                     Toast.makeText(getApplicationContext(), "Successfully registered!", Toast.LENGTH_SHORT);
//                    finish();
//                    startActivity(new Intent(RegisterHomeOwnerActivity.this, RegisterSuccess.class));
                }

            }
        });
    }

    public void reset() {
        _firstName.getText().clear();
        _lastName.getText().clear();
        _address.getText().clear();
        _postalCode.getText().clear();
        return;
    }

    public boolean is_validate(String username, String password, String firstName, String lastName, String address, String postalCode){

        Pattern p1 = Pattern.compile("[^-," +
                "^a-zA-Z_0-9" + "^\\t]", Pattern.CASE_INSENSITIVE);
        Pattern p2 = Pattern.compile("[^a-zA-z ]", Pattern.CASE_INSENSITIVE);
        Pattern p3 = Pattern.compile("[^a-zA-Z0-9 \\t]");

        boolean un = p1.matcher( username ).find();
        boolean fn = p2.matcher(firstName).find();
        boolean ln = p2.matcher(lastName).find();
        boolean ad = p1.matcher(address).find();
        boolean pc = p3.matcher(postalCode).find();


        //Validate


        if (TextUtils.isEmpty( username )) {
            Toast.makeText( getApplicationContext(), "Enter username", Toast.LENGTH_SHORT ).show();
            return false;
        }

        if (TextUtils.isEmpty( password )) {
            Toast.makeText( getApplicationContext(), "Enter password", Toast.LENGTH_SHORT ).show();
            return false;
        }

        if (TextUtils.isEmpty(firstName)) {
            Toast.makeText(getApplicationContext(), "Enter First Name", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(lastName)) {
            Toast.makeText(getApplicationContext(), "Enter Last Name", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(address)) {
            Toast.makeText(getApplicationContext(), "Enter Address", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(postalCode)) {
            Toast.makeText(getApplicationContext(), "Enter Post Code", Toast.LENGTH_SHORT).show();
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

        if (fn) {
            Toast.makeText(getApplicationContext(), "First name can only contains letter", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (ln) {
            Toast.makeText(getApplicationContext(), "Last name can only contains letter", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (ad) {
            Toast.makeText(getApplicationContext(), "Invalid address", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (pc || postalCode.length() < 6) {
            Toast.makeText(getApplicationContext(), "Invalid Post Code", Toast.LENGTH_SHORT).show();
            return false;
        }


        return true;
    }

}

