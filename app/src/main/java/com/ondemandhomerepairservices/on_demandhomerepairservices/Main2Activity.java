package com.ondemandhomerepairservices.on_demandhomerepairservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {

    Button btnAdmin, btnServiceProvider, btnHomeOwner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnAdmin = (Button) findViewById(R.id.buttonAdmin);
        btnServiceProvider = (Button) findViewById(R.id.buttonServiceProvider);
        btnHomeOwner = (Button) findViewById(R.id.buttonHomeOwner);

        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2Activity.this, LoginCheckAdminActivity.class));
                finish();
            }
        });

        btnServiceProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2Activity.this, LoginCheckSPActivity.class));
                finish();
            }
        });

        btnHomeOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2Activity.this, LoginCheckHOActivity.class));
                finish();
            }
        });

    }
}
