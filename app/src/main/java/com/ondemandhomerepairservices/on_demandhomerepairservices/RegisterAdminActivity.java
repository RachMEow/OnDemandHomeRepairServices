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
import com.ondemandhomerepairservices.on_demandhomerepairservices.accounts.Account;
import com.ondemandhomerepairservices.on_demandhomerepairservices.accounts.Admin;
import lib.Sha1;
import java.io.UnsupportedEncodingException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

//import com.google.firebase.auth.FirebaseAuth;
import android.util.Log;
public class RegisterAdminActivity extends AppCompatActivity {

//    private Spinner registerAs;
    private EditText _username, _password, _firstName, _lastName;
    private Button btnReset, btnNext, btnLogin, btnCancel;

    private Admin admin = new Admin();
    List<Admin> admins;

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseAdmins = database.getReference( "message" );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_admin_register);

//        registerAs = findViewById( R.id.spinnerRegisterAs );
        //create a list of items for the spinner.
//        String[] items = new String[]{"Admin", "Service Provider", "Home Owner"};

        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
//        ArrayAdapter<String> adapter = new ArrayAdapter<>( this, android.R.layout.simple_spinner_dropdown_item, items );
        //set the spinners adapter to the previously created one.
//        registerAs.setAdapter( adapter );

        //Get Firebase auth instance
//        auth = FirebaseAuth.getInstance();

        _username = (EditText) findViewById( R.id.editTextUsername );
        _password = (EditText) findViewById( R.id.editTextPassword );
        _firstName = (EditText) findViewById(R.id.editTextFirstName);
        _lastName = (EditText)findViewById(R.id.editTextLastName);
        btnReset = (Button) findViewById( R.id.buttonReset );
        btnNext = (Button) findViewById( R.id.buttonRegister );
        btnCancel = (Button) findViewById(R.id.buttonCancel);

        databaseAdmins = FirebaseDatabase.getInstance().getReference( "admins" );

        admins = new ArrayList<>();

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnReset.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        } );

        btnNext.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v){

                String username = _username.getText().toString().trim();
                String password = _password.getText().toString().trim();
                String firstName = _firstName.getText().toString().trim();
                String lastName = _lastName.getText().toString().trim();

                try {
                    password = Sha1.hash(password);
                } catch(UnsupportedEncodingException ex) {
                    System.out.println("UnsupportedEncodingException occurred!");
                }
                if(is_Validate(username, password, firstName, lastName)){
                    String id = databaseAdmins.push().getKey();
//
//                    admin.setId(id);
//                    admin.set_username(username);
//                    admin.set_password(password);
//                    admin.set_FirstName(firstName);
//                    admin.set_LastName(lastName);
                    Log.i( "hash: ",  password);
                    admin = new Admin(id, username, password, firstName, lastName);

                    //saving the account
                    databaseAdmins.child( id ).setValue( admin );

                    finish();

                    startActivity( new Intent( RegisterAdminActivity.this, RegisterSuccess.class ) );
                }

            }
        } );

        btnLogin = (Button)findViewById(R.id.buttonLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(RegisterAdminActivity.this, LoginCheckAdminActivity.class));
            }
        });


    }
    //Reset fields
    public void reset(){
        _username.getText().clear();
        _firstName.getText().clear();
        _lastName.getText().clear();
        _password.getText().clear();
        return;

    }

    public boolean is_Validate(String username, String password, String firstName, String lastName){

        Pattern p1 = Pattern.compile( "[^a-z0-9_]", Pattern.CASE_INSENSITIVE );
        Pattern p2 = Pattern.compile( "[^a-z ]", Pattern.CASE_INSENSITIVE );
        boolean un = p1.matcher( username ).find();
        boolean fn = p2.matcher(firstName).find();
        boolean ln = p2.matcher(lastName).find();

        //Validate fields
        if (un) {
            Toast.makeText( getApplicationContext(), "HomeOwner name can only contain letter, number and underscore", Toast.LENGTH_SHORT ).show();
            return false;
        }

        if(fn) {
            Toast.makeText(getApplicationContext(), "First name can only contains letter", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(ln) {
            Toast.makeText(getApplicationContext(), "Last name can only contains letter", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty( username )) {
            Toast.makeText( getApplicationContext(), "Enter username", Toast.LENGTH_SHORT ).show();
            return false;
        }

        if (TextUtils.isEmpty( password )) {
            Toast.makeText( getApplicationContext(), "Enter password", Toast.LENGTH_SHORT ).show();
            return false;
        }

        if (password.length() < 6) {
            Toast.makeText( getApplicationContext(), "Password too short, enter minimum 6 characters", Toast.LENGTH_SHORT ).show();
            return false;
        }

        if(TextUtils.isEmpty(firstName)){
            Toast.makeText(getApplicationContext(), "Enter first name", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(TextUtils.isEmpty(lastName)){
            Toast.makeText(getApplicationContext(), "Enter last name", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }



}
