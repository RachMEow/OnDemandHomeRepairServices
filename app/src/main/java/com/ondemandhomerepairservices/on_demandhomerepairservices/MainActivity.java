package com.ondemandhomerepairservices.on_demandhomerepairservices;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText _username, _password;
    private Button btnLogin, btnRegister;
    ListView listViewAccounts;
    SharedPreferences sharedPreferences;

    List<Account> accounts;

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    //get reference to 'accounts' node
    DatabaseReference databaseAccounts = database.getReference("message");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _username = (EditText)findViewById(R.id.editTextUsername);
        _password = (EditText)findViewById(R.id.editTextPassword);
        btnLogin = (Button) findViewById(R.id.buttonLogin);
        btnRegister = (Button) findViewById(R.id.buttonRegister);

        databaseAccounts = FirebaseDatabase.getInstance().getReference("accounts");

        accounts = new ArrayList<>();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });

    }

    @Override
    protected void onStart(){
        super.onStart();
        sharedPreferences = getApplicationContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String usernameInput = _username.getText().toString().trim();
                final String passwordInput = _password.getText().toString().trim();


                //attaching value event listener
                databaseAccounts.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //clearing the previous accounts list
                        accounts.clear();

                        //iterating through all the nodes
                        for(DataSnapshot postSnapShot : dataSnapshot.getChildren()){
                            //getting accounts
                            Account account = postSnapShot.getValue(Account.class);
                            //adding account to the list
                            accounts.add(account);
                        }

                        if(validate()){
                            for(Account account : accounts){
                                if(usernameInput.equals(account.get_username()) && passwordInput.equals(account.get_password())){
//                                Toast.makeText(getApplicationContext(), "Username and password correct", Toast.LENGTH_SHORT).show();
                                    String role = account.get_role();
                                    String firstName = account.get_firstName();

                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("firstName", firstName);
                                    editor.apply();

                                    switch(role){
                                        case "Admin":
                                            startActivity(new Intent(MainActivity.this, LoginAdmin.class));
                                            break;
                                        case "Service Provider":
                                            startActivity(new Intent(MainActivity.this, LoginServiceProvider.class));
                                            break;
                                        case "Home Owner":
                                            startActivity(new Intent(MainActivity.this, LoginUser.class));
                                            break;
                                    }

                                }else{
                                    Toast.makeText(getApplicationContext(),"Username or password wrong", Toast.LENGTH_SHORT).show();
                                }
                            }

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }



    //Valid the inputs are not empty
    private boolean validate(){
        String username = _username.getText().toString().trim();
        String password = _password.getText().toString().trim();

        //Validate fields
        if(TextUtils.isEmpty(username)){
            Toast.makeText(getApplicationContext(), "Enter username", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(), "Enter password", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }



}
