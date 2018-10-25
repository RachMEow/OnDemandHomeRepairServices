package com.ondemandhomerepairservices.on_demandhomerepairservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterSuccess extends AppCompatActivity {

    /* login button */
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_success);

        btnLogin = (Button)findViewById(R.id.buttonLoginRegisterSuccess);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterSuccess.this, MainActivity.class));
            }
        });
    }
}
