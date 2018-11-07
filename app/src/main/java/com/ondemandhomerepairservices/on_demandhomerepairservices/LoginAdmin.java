package com.ondemandhomerepairservices.on_demandhomerepairservices;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LoginAdmin extends AppCompatActivity {

    Button buttonLogout,buttonUserList,buttonServiceList;
    TextView firstName;
    ListView userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);
//        userList = (ListView) findViewById(R.id._userlist);

        firstName = (TextView) findViewById(R.id.textViewFirstName);
        firstName.setText(getIntent().getStringExtra("FIRST_NAME"));

        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        buttonUserList = (Button) findViewById(R.id.buttonUserList);
        buttonUserList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),UserListActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        buttonServiceList = (Button) findViewById(R.id.buttonServiceList);
        buttonServiceList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ServiceListActivity.class);
                v.getContext().startActivity(intent);
            }
        });






//        String[] formatUserList = getIntent().getStringExtra("USER_LIST").split("\t");
//        List<String> your_array_list = new ArrayList<String>();
//        for(int i=0; i<formatUserList.length; i++) {
//            your_array_list.add(formatUserList[i]);
//        }
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, your_array_list);
//        userList.setAdapter(arrayAdapter);

    }
}
