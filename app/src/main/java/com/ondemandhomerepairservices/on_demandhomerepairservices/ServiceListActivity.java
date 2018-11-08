package com.ondemandhomerepairservices.on_demandhomerepairservices;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ServiceListActivity extends AppCompatActivity {

    Button buttonBack, btnAddService;
    ListView listViewServiceList;
    EditText editTextServiceName;
    EditText editTextHoursRate;

    List<Service> services;

    Service service = new Service();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list);

        editTextServiceName = (EditText) findViewById(R.id.editTextServiceName);
        editTextHoursRate = (EditText) findViewById(R.id.editTextHoursRate);
        listViewServiceList = (ListView) findViewById(R.id.listViewServiceList);

        btnAddService = (Button) findViewById(R.id.buttonAddService);

//        btnAddService.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                openDialog();
//            }
//        });


        buttonBack = (Button) findViewById(R.id.buttonLogout);
        buttonBack = (Button) findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        databaseServices = FirebaseDatabase.getInstance().getReference("services");

        services = new ArrayList<>();

        btnAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                addService();

                Toast.makeText(ServiceListActivity.this, "Add Service button works", Toast.LENGTH_LONG).show();


            }
        });

    }

    @Override
    protected void onStart(){
        super.onStart();

        databaseServices.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                services.clear();

//                String[] serviceListArray = new String[100];

                for(DataSnapshot postSnapShot : dataSnapshot.getChildren()){
                    Service service = postSnapShot.getValue(Service.class);
                    services.add(service);
                }

//                int i=0;
//                for(Service service : services){
//                    serviceListArray[i] = service.get_serviceName() + " " + service.get_hoursRate();
//                    i++;
//                }
//
//                List<String> arrayList = new ArrayList<String>();
//                for(int k = 0; k < serviceListArray.length; k++){
//                    arrayList.add(serviceListArray[i]);
//                }
//                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ServiceListActivity.this, android.R.layout.simple_list_item_1, arrayList);
//                listViewServiceList.setAdapter(arrayAdapter);

//                String[] serviceList;
//                String serviceList = "";
//                for(Service s: services){
//                    serviceList += s.get_serviceName() + " " + s.get_hoursRate() + "\t";
//                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //openDialog() need to be edit

//    public void openDialog(){
//        AddServiceDialog addServiceDialog = new AddServiceDialog();
//        addServiceDialog.show(getSupportFragmentManager(),"Add service dialog");
//
//    }


    public boolean validateAddService(){

        String userInputServiceName = editTextServiceName.getText().toString().trim();
        String userInputHoursRate = editTextHoursRate.getText().toString().trim();

        Pattern p1 = Pattern.compile("[^a-z0-9_]", Pattern.CASE_INSENSITIVE);

        if(TextUtils.isEmpty(userInputServiceName)){
            Toast.makeText(this,"Please enter a service name", Toast.LENGTH_SHORT).show();
            return false;
        }else if(TextUtils.isEmpty(userInputHoursRate)){
            Toast.makeText(this,"Please enter a hours rate", Toast.LENGTH_SHORT).show();
            return false;
        }else if(p1.matcher(userInputServiceName).find()){
            Toast.makeText(getApplicationContext(), "Service name can only contain letter, number and underscore", Toast.LENGTH_SHORT).show();
            return false;
        }
//        else {
//            while (true) {
//                try {
//                    hoursRate = Double.parseDouble(String.valueOf(editTextHoursRate.getText().toString()));
//                    break;
//                } catch (NumberFormatException ignore) {
//                    Toast.makeText(this, "Please enter an invalid hours rate (number only)", Toast.LENGTH_SHORT).show();
//                }
//                return false;
//            }
//        }

        return true;
    }

    public void addService(){
        String userInputServiceName = editTextServiceName.getText().toString().trim();
        double userInputHoursRate = Double.parseDouble(String.valueOf(editTextHoursRate.getText().toString()));

        if(true){
            String id = databaseServices.push().getKey();
            service = new Service(id, userInputServiceName, userInputHoursRate);
            databaseServices.child(id).setValue(service);

            editTextServiceName.setText("");
            editTextHoursRate.setText("");

            Toast.makeText(this, "Service added", Toast.LENGTH_LONG).show();
        }
    }
}
