package com.ondemandhomerepairservices.on_demandhomerepairservices;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ondemandhomerepairservices.on_demandhomerepairservices.serviceProvider.SPAvailableTime;
import com.ondemandhomerepairservices.on_demandhomerepairservices.admin.Service;
import com.ondemandhomerepairservices.on_demandhomerepairservices.homeOwner.HOBookedService;
import com.ondemandhomerepairservices.on_demandhomerepairservices.serviceProvider.SPProvidedService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HomeOwnerServiceList extends AppCompatActivity {
    Button btnBack;
    ListView listViewServiceProvided;
//    String spId,ho_id,sppsId;
//    String spCompanyName;
//
//    private Service service = new Service();
//    List<Service> services;
//    List<String> servicesListString;
//
//    private HOBookedService hoBookedService = new HOBookedService();
//    List<HOBookedService> hoBookedServices;
//    List<String> hoBookedServicesListString;

    // get the value of intent.putExtra
    String searchType;
    String userInputServiceName;

    private SPProvidedService spProvidedService = new SPProvidedService();
    List<SPProvidedService> spProvidedServices;
    List<String> spProvidedServicesListString;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseProvidedServices = database.getReference("message");
//    DatabaseReference databaseBookedService = database.getReference("message2");
//    DatabaseReference databaseServices = database.getReference("message3");
//    DatabaseReference databaseHomeOwners = database.getReference("message4");

    //Time case variables

    private SPAvailableTime spAvailableTime = new SPAvailableTime();
    List<SPAvailableTime> spAvailableTimes;
    List<String> spAvailableTimeListString;
    List<SPProvidedService> spProvideServices2;
    DatabaseReference databaseAvailableTimes = database.getReference("message");

    String spId,timeFrom,timeTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home_owner_service_list );

        searchType = getIntent().getStringExtra("searchType");

        listViewServiceProvided = (ListView)findViewById(R.id.listViewServiceList);

//        //long click
//        databaseServices = FirebaseDatabase.getInstance().getReference("services");
//        services = new ArrayList<>();
//        servicesListString = new ArrayList<>();
//
//        databaseProvidedService = FirebaseDatabase.getInstance().getReference("spProvidedServices");
//        spProvidedServices = new ArrayList<>();
//        spProvidedServicesListString = new ArrayList<>();
//
//        spId = getIntent().getStringExtra( "SPID" );
//        sppsId= getIntent().getStringExtra( "SPPSID" );
//        ho_id = getIntent().getStringExtra("HOID");
//        spCompanyName = getIntent().getStringExtra("SPCompanyName");
//
        listViewServiceProvided.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

//            int selectedService = listViewAdminServices.getSelectedItemPosition();

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Log.d( "jjj", "kkk" )
//                Service selectedService = services.get(i);
//                final String serviceId = selectedService.get_id();
//                final String serviceName = selectedService.get_serviceName();
//                final double hoursRate = selectedService.get_hoursRate();

//                Toast.makeText(ServiceProviderAddNewService.this,""+selectedService.get_serviceName(), Toast.LENGTH_SHORT).show();

                AlertDialog.Builder yesorno = new AlertDialog.Builder(HomeOwnerServiceList.this);
                yesorno.setMessage( "Are you sure to book this service?" )
                        .setCancelable( false )
//                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//
//                                //add the service to SP provided service
////                                if(isNotExistInProvidedService(serviceId)){
//                                    String id = databaseBookedService.push().getKey();
//                                    //public HOBookedService(String hoBookedService_id, String ho_id, String spProvidedService_id, String sp_id, String spCompanyName, String service_id, String serviceName, double hoursRate)
//                                    hoBookedService = new HOBookedService(id, ho_id,sppsId,spId,spCompanyName, serviceId, serviceName, hoursRate);
//                                    databaseBookedService.child(id).setValue(hoBookedService);
////                                    Toast.makeText(getApplicationContext(), "Service added to your profile", Toast.LENGTH_SHORT).show();
//
////                                }else{
////                                    Toast.makeText(getApplicationContext(), "Unable to add service to your profile", Toast.LENGTH_SHORT).show();
//
////                                }
//
//                            }
//                        })

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


        databaseProvidedServices = FirebaseDatabase.getInstance().getReference("spProvidedServices");
        spProvidedServices = new ArrayList<>();
        spProvidedServicesListString = new ArrayList<>();

        btnBack = (Button) findViewById(R.id.buttonBack);
        btnBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );


        //Time database
        spAvailableTimes = new ArrayList<>();
        spAvailableTimeListString = new ArrayList<>();
        databaseAvailableTimes = FirebaseDatabase.getInstance().getReference("spAvailableTimes");
        spId = getIntent().getStringExtra("SPID");
        timeFrom = getIntent().getStringExtra("timeBegin");
        timeTo = getIntent().getStringExtra("timeEnd");

      //  spProvideServices2 = new ArrayList<>();

    }

    @Override
    protected void onStart(){
        super.onStart();

        switch (searchType){
            case "serviceName":

                userInputServiceName = getIntent().getStringExtra("serviceName");
                Query queryRef = databaseProvidedServices.orderByChild("_serviceName").equalTo(userInputServiceName);

                queryRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        spProvidedServices.clear();

                        for(DataSnapshot postSnapShot : dataSnapshot.getChildren()){

                            SPProvidedService spProvidedService = postSnapShot.getValue(SPProvidedService.class);
                            spProvidedServices.add(spProvidedService);

                        }

                        spProvidedServicesListString.clear();

                        for(SPProvidedService spProvidedService : spProvidedServices){
                            String s = spProvidedService.get_serviceName() + " provided by " + spProvidedService.getSpCompanyName();
                            spProvidedServicesListString.add(s);
                        }

                        ArrayAdapter<String> servicesAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, spProvidedServicesListString);
                        listViewServiceProvided.setAdapter(servicesAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                break;

            case "time":
                //TODO: do something
               Query searchQuery = databaseAvailableTimes.orderByChild("spId").equalTo(spId);
                spProvideServices2 = new ArrayList<>();
                searchQuery.addValueEventListener(new ValueEventListener(){
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       // spAvailableTimes.clear();
                        for (DataSnapshot postSnapShot : dataSnapshot.getChildren()) {
                           // SPAvailableTime spATime = postSnapShot.getValue(SPAvailableTime.class);
                            //spAvailableTimes.add(spATime);
                            Map<String,Object> valueMap = (HashMap<String, Object>) dataSnapshot.getValue();
                             String spidKey = (String)valueMap.get("spId");

                        Query providerQuery = databaseProvidedServices.orderByChild("spId").equalTo(spidKey);
                        providerQuery.addValueEventListener(new ValueEventListener(){
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                spProvidedServices.clear();
                                for (DataSnapshot postSnapShot : dataSnapshot.getChildren()) {

                                        SPProvidedService spProvidedService = postSnapShot.getValue(SPProvidedService.class);

                                        //新的备用list
                                        spProvideServices2.add(spProvidedService);
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                        }

                        spProvidedServicesListString.clear();
                        for (SPProvidedService spProvidedService : spProvideServices2){
                            String s = spProvidedService.toString();
                            spProvidedServicesListString.add(s);

                        }

                        Set<String> spProvidedServiceListString2 = new LinkedHashSet<>(spProvidedServicesListString);

                        spProvidedServicesListString.clear();
                        spProvidedServicesListString.addAll(spProvidedServiceListString2);

                        ArrayAdapter<String> servicesAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, spProvidedServicesListString);
                        listViewServiceProvided.setAdapter(servicesAdapter);
                    }



                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            case "rate":
                //TODO: do something
                break;
        }


    }
}
