package com.ondemandhomerepairservices.on_demandhomerepairservices;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class AdminUserListActivity extends AppCompatActivity {

    Button buttonBack;
    ListView userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_list);

        userList = (ListView) findViewById(R.id.listViewUserList);

        buttonBack = (Button) findViewById(R.id.buttonBack2);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String[] formatUserList = getIntent().getStringExtra("USER_LIST").split("\t");
        List<String> your_array_list = new ArrayList<String>();
        for(int i=0; i<formatUserList.length; i++) {
            your_array_list.add(formatUserList[i]);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, your_array_list);
        userList.setAdapter(arrayAdapter);
    }
}
