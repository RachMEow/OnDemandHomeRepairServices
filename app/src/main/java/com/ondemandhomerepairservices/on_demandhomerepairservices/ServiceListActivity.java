package com.ondemandhomerepairservices.on_demandhomerepairservices;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
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

    private Button buttonBack, btnAddService;
    private ListView listViewServiceList;
    private EditText serviceName, hoursRate;

    private Service service = new Service();
    List<Service> services;
    List<String> serviceListString;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseServices = database.getReference("messagem");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list);

        serviceName = (EditText) findViewById(R.id.editTextServiceName);
        hoursRate = (EditText) findViewById(R.id.editTextHoursRate);
        listViewServiceList = (ListView) findViewById(R.id.listViewServiceList);

        buttonBack = (Button) findViewById(R.id.buttonBack);
        btnAddService = (Button) findViewById(R.id.buttonAddService);

        databaseServices = FirebaseDatabase.getInstance().getReference("services");

        services = new ArrayList<>();

        serviceListString = new ArrayList<>();

//        btnAddService.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                openDialog();
//            }
//        });

        listViewServiceList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Service service = services.get(i);
                showUpdateServiceDialog(service.get_id(), service.get_serviceName());
                return true;
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addService();
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


                for(DataSnapshot postSnapShot : dataSnapshot.getChildren()){
                    Service service = postSnapShot.getValue(Service.class);
                    services.add(service);
                }

                serviceListString.clear();

                for(Service service : services){
                    String s = service.get_serviceName() + " ($" + service.get_hoursRate() + "/hour)";
                    serviceListString.add(s);
                }

                ArrayAdapter<String> servicesAdapter = new ArrayAdapter<String>(ServiceListActivity.this, android.R.layout.simple_list_item_1, serviceListString);
                listViewServiceList.setAdapter(servicesAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

//    openDialog() need to be edit

//    public void openDialog(){
//        AddServiceDialog addServiceDialog = new AddServiceDialog();
//        addServiceDialog.show(getSupportFragmentManager(),"Add service dialog");
//    }


    public boolean validateAddService(){

        String userInputServiceName = serviceName.getText().toString().trim();
        String userInputHoursRate = hoursRate.getText().toString().trim();
        double numHoursRate = 0;

        Pattern p1 = Pattern.compile("[^a-z0-9_ ]", Pattern.CASE_INSENSITIVE);

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

        return true;
    }
// add a service
    public void addService(){
        String userInputServiceName = serviceName.getText().toString().trim();
        double userInputHoursRate = 0.0;

        if(validateAddService()){

            try{
                userInputHoursRate = Double.parseDouble(String.valueOf(hoursRate.getText().toString()));
                String id = databaseServices.push().getKey();
                service = new Service(id, userInputServiceName, userInputHoursRate);
                databaseServices.child(id).setValue(service);

                serviceName.setText("");
                hoursRate.setText("");

                Toast.makeText(this, "Service added", Toast.LENGTH_LONG).show();
            }catch (NumberFormatException ignore){
                Toast.makeText(this, "Invalid hours rate (number only)", Toast.LENGTH_SHORT).show();
            }


        }
    }

    private void showUpdateServiceDialog(final String serviceId, String serviceName){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.layout_service_longclick_dialogue, null);
        dialogBuilder.setView(dialogView);

        final EditText editTextServiceName = (EditText) dialogView.findViewById(R.id.editTextServiceName);
        final EditText editTextHoursRate = (EditText) dialogView.findViewById(R.id.editTextHoursRate);
        final Button btnUpdateServiceName = (Button) dialogView.findViewById(R.id.buttonUpdateServiceName);
        final Button btnUpdateHoursRate = (Button) dialogView.findViewById(R.id.buttonUpdateHoursRate);
        final Button btnDelete = (Button)dialogView.findViewById(R.id.buttonDeleteService);

        dialogBuilder.setTitle(serviceName);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        //TODO: update service name button event listener

        //TODO: update hours rate button event listener

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteService(serviceId);
                b.dismiss();
            }
        });

    }

    public boolean deleteService(String id){
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("services").child(id);
        dR.removeValue();
        Toast.makeText(getApplicationContext(),"Service Deleted", Toast.LENGTH_LONG).show();
        return true;
    }

    //TODO: update service name and validate
    public void updateServiceName(String id, String serviceName, double hoursRate){
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("services").child(id);
        Service service = new Service(id, serviceName, hoursRate);
        dR.setValue(service);
        Toast.makeText(getApplicationContext(), "Service updated", Toast.LENGTH_LONG).show();
    }

    //TODO: update hours rate
    public void updateHoursRate(String id, String serviceName, double hoursRate){
      /*  DatabaseReference dr = FirebaseDatabase.getInstance().getReference("rate").child(id);
        Service service = new Service(id,serviceName,hoursRate);
        dr.setValue(service);
        Toast.makeText(getApplicationContext(),"Hour rate updated",Toast.LENGTH_LONG).show();*/
    }
}
