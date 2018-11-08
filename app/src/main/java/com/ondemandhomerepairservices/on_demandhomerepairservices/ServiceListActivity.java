package com.ondemandhomerepairservices.on_demandhomerepairservices;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ServiceListActivity extends AppCompatActivity {

    Button buttonBack, buttonAddService;
    ListView listViewServiceList;
    EditText editTextAddService;
    EditText editTextHoursRate;

    List<Service> services;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list);

        //AddServicebutton
        buttonAddService = (Button)findViewById( R.id.buttonAddService);
        buttonBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                openDialog();
            }
        });


        buttonBack = (Button) findViewById(R.id.buttonLogout);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        databaseServices = FirebaseDatabase.getInstance().getReference("services");

        services = new ArrayList<>();

        databaseServices.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                services.clear();

                for(DataSnapshot postSnapShot : dataSnapshot.getChildren()){
                    Service service = postSnapShot.getValue(Service.class);
                    services.add(service);
                }

                String serviceList = "";
                for(Service s: services){
                    serviceList += s.get_serviceName() + " " + s.get_hoursRate() + "\t";
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        buttonAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo: get valud from user
            }
        });
    }

    //openDialog() need to be edit

    public void openDialog(){
        AddServiceDialog addServiceDialog = new AddServiceDialog();
        addServiceDialog.show(getSupportFragmentManager(),"Add service dialog");

    }




    //todo: validateAddService()

    public boolean validateAddService(){

        return true;
    }
}
