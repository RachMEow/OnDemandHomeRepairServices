package com.ondemandhomerepairservices.on_demandhomerepairservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HomeOwnerSearchServiceProvider extends AppCompatActivity {

    Button btnBack;
    Button btnSearch1, btnSearch2, btnSearch3;
    EditText editTextServiceName;
    String searchType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home_owner_search_service_provider );

        btnBack = (Button) findViewById(R.id.buttonBack);
        btnBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );

        btnSearch1 = (Button) findViewById(R.id.buttonSearch1);
        btnSearch2 = (Button) findViewById(R.id.buttonSearch2);
        btnSearch3 = (Button) findViewById(R.id.buttonSearch3);

        editTextServiceName = (EditText) findViewById(R.id.editTextServiceName);

        btnSearch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(HomeOwnerSearchServiceProvider.this, HomeOwnerServiceList.class);
                searchType = "serviceName";
                intent.putExtra("searchType", searchType);
                startActivity(intent);
            }
        });

    }
}
