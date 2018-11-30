package com.ondemandhomerepairservices.on_demandhomerepairservices;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ondemandhomerepairservices.on_demandhomerepairservices.accounts.Admin;
import lib.Sha1;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class LoginCheckAdminActivity extends AppCompatActivity {

    private EditText _username, _password;
    private Button btnLogin,btnBack;

    List<Admin> admins;

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference databaseAdmins = database.getReference("message");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_check_admin);

        _username = (EditText)findViewById(R.id.editTextUsername);
        _password = (EditText)findViewById(R.id.editTextPassword);
        btnLogin = (Button)findViewById(R.id.buttonLogin);

        databaseAdmins = FirebaseDatabase.getInstance().getReference("admins");
        admins = new ArrayList<>();

        btnBack = (Button) findViewById(R.id.buttonBack);
        btnBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(LoginCheckAdminActivity.this, MainActivity.class));
            }
        } );

        btnLogin.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            final String usernameInput = _username.getText().toString().trim();
                                            final String passwordInput = _password.getText().toString().trim();

                                            databaseAdmins.addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    //clearing the previous accounts list
                                                    admins.clear();

                                                    //iterating through all the nodes
                                                    for (DataSnapshot postSnapShot : dataSnapshot.getChildren()) {
                                                        //getting accounts
                                                        Admin admin = postSnapShot.getValue(Admin.class);
                                                        //adding account to the list
                                                        admins.add(admin);
                                                    }

                                                    //todo: also get dataSnapshots of SPs and HOs, and make s listView for each
                                                    String listString = "";
                                                    for (Admin a : admins)
                                                    {
                                                        listString +=  a.get_username() + "\t";
                                                    }

                                                    if (validate()) {
                                                        for (Admin admin : admins) {
                                                            String hashedPassword = passwordInput;
                                                            try {
                                                                hashedPassword = Sha1.hash( passwordInput );
                                                            }  catch(UnsupportedEncodingException ex) {
                                                                System.out.println("UnsupportedEncodingException occurred!");
                                                            }
                                                            if (usernameInput.equals(admin.get_username()) && hashedPassword.equals(admin.get_password())) {
                                                                String username = admin.get_username();

                                                                Intent intent;
                                                                intent = new Intent(LoginCheckAdminActivity.this, LoginAdmin.class);
                                                                intent.putExtra("USERNAME", username);
                                                                intent.putExtra("USER_LIST", listString);
//                                                                finish();
                                                                startActivity(intent);
                                                                Toast.makeText(getApplicationContext(),"Login Success!", Toast.LENGTH_SHORT).show();
                                                                return;//check if success originally exist
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
