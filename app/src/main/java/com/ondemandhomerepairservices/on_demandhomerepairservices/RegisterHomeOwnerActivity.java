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

import java.util.List;
import java.util.regex.Pattern;

public class RegisterHomeOwnerActivity extends AppCompatActivity {

    private EditText firstName, lastName, address, postalCode;
    private Button btnclear, btnRegister, btnCancel;

    private Account account = new Account();
    List<Account> accounts;

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseAccounts = database.getReference("message");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_owner_register);

        firstName = (EditText) findViewById(R.id.editTextFirstName);
        lastName = (EditText) findViewById(R.id.editTextLastName);
        address = (EditText) findViewById(R.id.editTextAddress);
        postalCode = (EditText) findViewById(R.id.editTextPostalCode);

        databaseAccounts = FirebaseDatabase.getInstance().getReference("homeOwners");

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
               // String id;
                String firstN = trimString(firstName.getText().toString());
                String lastN = trimString(lastName.getText().toString());
                String addr = trimString(address.getText().toString());
                String postC = trimString(postalCode.getText().toString());

                Pattern p1 = Pattern.compile("[^-," +
                        "^a-zA-Z_0-9" + "^\\t]", Pattern.CASE_INSENSITIVE);
                Pattern p2 = Pattern.compile("[^a-zA-z ]", Pattern.CASE_INSENSITIVE);
                Pattern p3 = Pattern.compile("[^a-zA-Z0-9 \\t]");

                boolean fn = p2.matcher(firstN).find();
                boolean ln = p2.matcher(lastN).find();
                boolean ad = p1.matcher(addr).find();
                boolean pc = p3.matcher(postC).find();


                //Validate

                if(fn) {
                    Toast.makeText(getApplicationContext(), "First name can only contains letter", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(ln){
                    Toast.makeText(getApplicationContext(), "Last name can only contains letter", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(ad){
                    Toast.makeText(getApplicationContext(), "Invalid address", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(pc || postC.length()<6){
                    Toast.makeText(getApplicationContext(), "Invalid Post Code", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(firstN)) {
                    Toast.makeText( getApplicationContext(), "Enter First Name", Toast.LENGTH_SHORT ).show();
                    return;
                }

                if(TextUtils.isEmpty(lastN)){
                    Toast.makeText(getApplicationContext(), "Enter Last Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(addr)){
                    Toast.makeText(getApplicationContext(), "Enter Address", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(postC)){
                    Toast.makeText(getApplicationContext(), "Enter Post Code", Toast.LENGTH_SHORT).show();
                    return;
                }

              //  String id = databaseAccounts.push().getKey();

                //create an account object
              //  account = new Account(id, firstN, lastN, addr, pc, role);

                //saving the account
               // databaseAccounts.child(id).setValue(account);

                finish();
                startActivity(new Intent(RegisterHomeOwnerActivity.this, LoginUser.class));


            }
        });
    }

    public void reset(){
        firstName.getText().clear();
        lastName.getText().clear();
        address.getText().clear();
        postalCode.getText().clear();
        return;

    }

    public String trimString(String e) {
        return e.trim();
    }


}
