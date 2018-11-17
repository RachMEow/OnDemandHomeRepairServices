package com.ondemandhomerepairservices.on_demandhomerepairservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterMainActivity extends AppCompatActivity {

    Button btnAdmin, btnServiceProvider, btnHomeOwner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_main);

        btnAdmin = (Button)findViewById(R.id.buttonAdmin);
        btnServiceProvider = (Button)findViewById(R.id.buttonServiceProvider);
        btnHomeOwner = (Button)findViewById(R.id.buttonHomeOwner);

        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterMainActivity.this, RegisterAdminActivity.class));
            }
        });

        btnServiceProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterMainActivity.this, RegisterServiceProviderActivity.class));
            }
        });

        btnHomeOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterMainActivity.this, RegisterHomeOwnerActivity.class));
            }
        });
    }
}
