package com.ondemandhomerepairservices.on_demandhomerepairservices;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ondemandhomerepairservices.on_demandhomerepairservices.accounts.Account;
import com.ondemandhomerepairservices.on_demandhomerepairservices.accounts.Admin;
import com.ondemandhomerepairservices.on_demandhomerepairservices.accounts.HomeOwner;
import com.ondemandhomerepairservices.on_demandhomerepairservices.accounts.ServiceProvider;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    private EditText _username, _password;
    private Button btnLogin, btnRegister;

//    List<Admin> admins;
    List<ServiceProvider> serviceProviders;
    List<HomeOwner> homeOwners;

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();

//    DatabaseReference databaseAdmins = database.getReference("message");
    DatabaseReference databaseServiceProviders = database.getReference("message");
    DatabaseReference databaseHomeOwners = database.getReference("message");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        _username = (EditText)findViewById(R.id.editTextUsername);
//        _password = (EditText)findViewById(R.id.editTextPassword);
        btnLogin = (Button) findViewById(R.id.buttonLogin);
        btnRegister = (Button) findViewById(R.id.buttonRegister);

//        databaseAdmins = FirebaseDatabase.getInstance().getReference("admins");
        databaseServiceProviders = FirebaseDatabase.getInstance().getReference("serviceProviders");
        databaseHomeOwners = FirebaseDatabase.getInstance().getReference("homeOwners");

//        admins = new ArrayList<>();
        serviceProviders = new ArrayList<>();
        homeOwners = new ArrayList<>();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterMainActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
        });

//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
//                LayoutInflater inflater = getLayoutInflater();
//                final View dialogView = inflater.inflate(R.layout.layout_login_dialog, null);
//                dialogBuilder.setView(dialogView);
//
//                final Button btnAdmin = (Button) dialogView.findViewById(R.id.buttonAdmin);
//                final Button btnServiceProvider = (Button) dialogView.findViewById(R.id.buttonServiceProvider);
//                final Button btnHomeOwner = (Button) dialogView.findViewById(R.id.buttonHomeOwner);
//
//                dialogBuilder.setTitle("What is your role?");
//                final AlertDialog b = dialogBuilder.create();
//                b.show();
//
//                btnAdmin.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
//                        LayoutInflater inflater = getLayoutInflater();
//                        final View dialogView = inflater.inflate(R.layout.layout_login_info_dialog, null);
//                        dialogBuilder.setView(dialogView);
//
//                        final EditText editTextUsername = (EditText)findViewById(R.id.editTextUsername);
//                        final EditText editTextPassword = (EditText)findViewById(R.id.editTextPassword);
//
//                        final Button btnLogin = (Button)findViewById(R.id.buttonLogin);
//
//                        dialogBuilder.setTitle("Admin Login");
//                        final AlertDialog b = dialogBuilder.create();
//                        b.show();
//
//                        btnLogin.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                final String usernameInput = editTextUsername.getText().toString().trim();
//                                final String passwordInput = editTextPassword.getText().toString().trim();
//
//                                databaseAdmins.addValueEventListener(new ValueEventListener() {
//                                    @Override
//                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                        admins.clear();
//                                        for(DataSnapshot postSnapShot : dataSnapshot.getChildren()){
//                                            Admin admin = postSnapShot.getValue(Admin.class);
//                                            admins.add(admin);
//                                        }
//
//                                        if(validate(editTextUsername, editTextPassword)){
//                                            for(Admin admin: admins){
//                                                if(usernameInput.equals(admin.get_username()) && passwordInput.equals(admin.get_password())){
//                                                    Intent intent;
//                                                    intent = new Intent(getApplicationContext(), LoginAdmin.class);
//                                                    intent.putExtra("USERNAME", admin.get_username());
//                                                    b.dismiss();
//                                                    startActivity(intent);
//                                                }else{
//                                                    Toast.makeText(getApplicationContext(), "Username or password wrong", Toast.LENGTH_SHORT);
//                                                }
//                                            }
//                                        }
//
//                                    }
//
//                                    @Override
//                                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                                    }
//                                });
//                            }
//                        });
//
//                    }
//                });
//
//                btnServiceProvider.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                });
//
//                btnHomeOwner.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                });
//            }
//        });


    }





}
