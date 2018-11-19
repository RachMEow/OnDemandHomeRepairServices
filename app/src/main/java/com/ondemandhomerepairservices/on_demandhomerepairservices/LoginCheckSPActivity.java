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
import com.ondemandhomerepairservices.on_demandhomerepairservices.accounts.ServiceProvider;

import java.util.ArrayList;
import java.util.List;

public class LoginCheckSPActivity extends AppCompatActivity {

    private EditText _username, _password;
    private Button btnLogin;

    List<ServiceProvider> serviceProviders;

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference databaseSPs = database.getReference("message");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_check_sp);

        _username = (EditText)findViewById(R.id.editTextUsername);
        _password = (EditText)findViewById(R.id.editTextPassword);
        btnLogin = (Button)findViewById(R.id.buttonLogin);

        databaseSPs = FirebaseDatabase.getInstance().getReference("serviceProviders");
        serviceProviders = new ArrayList<>();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String usernameInput = _username.getText().toString().trim();
                final String passwordInput = _password.getText().toString().trim();

                databaseSPs.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //clearing the previous accounts list
                        serviceProviders.clear();

                        //iterating through all the nodes
                        for (DataSnapshot postSnapShot : dataSnapshot.getChildren()) {
                            //getting accounts
                            ServiceProvider serviceProvider = postSnapShot.getValue(ServiceProvider.class);
                            //adding account to the list
                            serviceProviders.add(serviceProvider);
                        }

                        if (validate()) {
                            for (ServiceProvider serviceProvider : serviceProviders) {
                                if (usernameInput.equals(serviceProvider.get_username()) && passwordInput.equals(serviceProvider.get_password())) {
                                    String username = serviceProvider.get_username();
//                                    String spId = serviceProvider.getId();

                                    Intent intent;
                                    intent = new Intent(LoginCheckSPActivity.this, LoginServiceProvider.class);
                                    intent.putExtra("USERNAME", username);
//                                    intent.putExtra( "SPID", spId );
                                    finish();
                                    startActivity(intent);
                                    Toast.makeText(getApplicationContext(),"Login Success!", Toast.LENGTH_SHORT).show();
                                    //check if success originally exist
                                    return;
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
