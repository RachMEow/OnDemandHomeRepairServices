package com.ondemandhomerepairservices.on_demandhomerepairservices;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginServiceProvider extends AppCompatActivity {

    Button buttonLogout;
    TextView firstName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_service_provider);

        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        firstName = (TextView) findViewById(R.id.textViewFirstName);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(v.getContext(), MainActivity.class);
                //v.getContext().startActivity(intent);
                finish();
            }
        });
        firstName.setText(getIntent().getStringExtra("FIRST_NAME"));
    }
}
