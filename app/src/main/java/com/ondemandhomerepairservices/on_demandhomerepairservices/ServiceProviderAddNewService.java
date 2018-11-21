package com.ondemandhomerepairservices.on_demandhomerepairservices;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.content.DialogInterface;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ondemandhomerepairservices.on_demandhomerepairservices.admin.Service;
import com.ondemandhomerepairservices.on_demandhomerepairservices.serviceProvider.SPProvidedService;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import android.util.Log;

public class ServiceProviderAddNewService extends AppCompatActivity {

    ListView listViewAdminServices;
    Button btnBack,btnYes;
    String spId;

    private Service service = new Service();
    List<Service> services;
    List<String> servicesListString;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseServices = database.getReference("message");

    private SPProvidedService spProvidedService = new SPProvidedService();
    List<SPProvidedService> spProvidedServices;
    List<String> spProvidedServicesListString;

    DatabaseReference databaseProvidedService = database.getReference("message2");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_add_new_service);

        listViewAdminServices = (ListView)findViewById(R.id.listViewAdminServiceList);

        databaseServices = FirebaseDatabase.getInstance().getReference("services");
        services = new ArrayList<>();
        servicesListString = new ArrayList<>();

        databaseProvidedService = FirebaseDatabase.getInstance().getReference("spProvidedServices");
        spProvidedServices = new ArrayList<>();
        spProvidedServicesListString = new ArrayList<>();

        spId = getIntent().getStringExtra( "SPID" );

        btnBack = (Button) findViewById(R.id.buttonBack);
        btnBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );

        listViewAdminServices = (ListView)findViewById(R.id.listViewAdminServiceList);

        listViewAdminServices.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

//            int selectedService = listViewAdminServices.getSelectedItemPosition();

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Log.d( "jjj", "kkk" )
                Service selectedService = services.get(i);
                final String serviceId = selectedService.get_id();
                final String serviceName = selectedService.get_serviceName();
                final double hoursRate = selectedService.get_hoursRate();

//                Toast.makeText(ServiceProviderAddNewService.this,""+selectedService.get_serviceName(), Toast.LENGTH_SHORT).show();

                AlertDialog.Builder yesorno = new AlertDialog.Builder(ServiceProviderAddNewService.this);
                yesorno.setMessage( "Are you sure to add this service to your profile?" )
                        .setCancelable( false )
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                //add the service to SP provided service
                                if(isNotExistInProvidedService(serviceId)){
                                    String id = databaseProvidedService.push().getKey();
                                    spProvidedService = new SPProvidedService(id, spId, serviceId, serviceName, hoursRate);
                                    databaseProvidedService.child(id).setValue(spProvidedService);

//                                    Toast.makeText(getApplicationContext(), "Service added to your profile", Toast.LENGTH_SHORT).show();

//                                }else{
//                                    Toast.makeText(getApplicationContext(), "Unable to add service to your profile", Toast.LENGTH_SHORT).show();

                                }

                            }
                        })

                        .setNegativeButton("No",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }) ;
                final AlertDialog b = yesorno.create();
                b.show();


                return true;
            }
        });

    }

    @Override
    protected void onStart(){
        super.onStart();

        // Retrieve services added by ADMIN from database
        databaseServices.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                services.clear();

                for(DataSnapshot postSnapShot : dataSnapshot.getChildren()){
                    Service service = postSnapShot.getValue(Service.class);
                    services.add(service);
                }

                servicesListString.clear();

                for(Service service : services){
                    String s = service.toString();
                    servicesListString.add(s);
                }

                ArrayAdapter<String> servicesAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, servicesListString);
                listViewAdminServices.setAdapter(servicesAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    //TODO: check if the service SP already added
    public boolean isNotExistInProvidedService(final String selectedServiceId){
        //get data that belongs to this SP

        Query queryRef = databaseProvidedService.orderByChild("spId").equalTo(spId);
        // Retrieve services added by ADMIN from database
        queryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                spProvidedServices.clear();
                Hashtable<String, Integer> check = new Hashtable<String, Integer>();
                for(DataSnapshot postSnapShot : dataSnapshot.getChildren()){
                    Service temp = postSnapShot.getValue(Service.class);
                    String serviceName = temp.get_serviceName();
                    String id = temp.get_id();
                    if(check.containsKey( serviceName )) {
                        Toast.makeText(getApplicationContext(), "Unable to add service to your profile", Toast.LENGTH_SHORT).show();
                        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("spProvidedServices").child(postSnapShot.getKey());
                        Log.i("removing: ", postSnapShot.getKey());
                        dR.removeValue();
                        return;
                    } else {
                        check.put( serviceName, 1 );
                    }
                }
                Toast.makeText(getApplicationContext(), "Service added to your profile", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return true;
    }





}
