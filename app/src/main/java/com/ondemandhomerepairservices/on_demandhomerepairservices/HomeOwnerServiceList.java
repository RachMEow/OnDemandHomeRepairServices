package com.ondemandhomerepairservices.on_demandhomerepairservices;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.ondemandhomerepairservices.on_demandhomerepairservices.homeOwner.Rating;
import com.ondemandhomerepairservices.on_demandhomerepairservices.serviceProvider.SPAvailableTime;
import com.ondemandhomerepairservices.on_demandhomerepairservices.admin.Service;
import com.ondemandhomerepairservices.on_demandhomerepairservices.homeOwner.HOBookedService;
import com.ondemandhomerepairservices.on_demandhomerepairservices.serviceProvider.SPProvidedService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HomeOwnerServiceList extends AppCompatActivity {
    Button btnBack;
    ListView listViewServiceProvided;
    String ho_id;
//    String spCompanyName;

//    private Service service = new Service();
//    List<Service> services;
//    List<String> servicesListString;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
//    DatabaseReference databaseServices = database.getReference("message2");



    // get the value of intent.putExtra
    String searchType;
    String userInputServiceName;

    // set sp provided services
    private SPProvidedService spProvidedService = new SPProvidedService();
    List<SPProvidedService> spProvidedServices;
    List<String> spProvidedServicesListString;

    DatabaseReference databaseProvidedServices = database.getReference("message");

//    DatabaseReference databaseProvidedService = database.getReference("message3");

    // set ho booked services
    private HOBookedService hoBookedService = new HOBookedService();
    List<HOBookedService> hoBookedServices;
    List<String> hoBookedServicesListString;

    DatabaseReference databasehoBookedService = database.getReference("message4");

    private Rating rating = new Rating();
    List<Rating> ratings;
    List<String> spps_ids_from_rating;

    List<Integer> ratingNums;

    private SPProvidedService spProvidedService_from_rating = new SPProvidedService();
    List<SPProvidedService> spProvidedServices_from_rating;
    List<String> listStringSPPS_from_rating;

    DatabaseReference databaseRating;

    //Time case variables

    private SPAvailableTime spAvailableTime = new SPAvailableTime();
    List<SPAvailableTime> spAvailableTimes;
    List<String> spAvailableTimeListString;
    List<SPProvidedService> spProvideServices2;
    DatabaseReference databaseAvailableTimes = database.getReference("message");

//    String spId,timeFrom,timeTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home_owner_service_list );

        searchType = getIntent().getStringExtra("searchType");

        listViewServiceProvided = (ListView)findViewById(R.id.listViewServiceList);

        databaseProvidedServices = FirebaseDatabase.getInstance().getReference("spProvidedServices");
        spProvidedServices = new ArrayList<>();
        spProvidedServicesListString = new ArrayList<>();

        databasehoBookedService = FirebaseDatabase.getInstance().getReference("hoBookedServices");
        hoBookedServices = new ArrayList<>();
        hoBookedServicesListString = new ArrayList<>();

        // for rating case
        databaseRating = database.getReference("ratings");
        ratings = new ArrayList<>();
        spps_ids_from_rating = new ArrayList<>();

        spProvidedServices_from_rating = new ArrayList<>();
        listStringSPPS_from_rating = new ArrayList<>();

        ratingNums = new ArrayList<>();


        ho_id = getIntent().getStringExtra("HOID");

        listViewServiceProvided.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

//            int selectedService = listViewAdminServices.getSelectedItemPosition();

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Log.d( "jjj", "kkk" )
                SPProvidedService selectedSPProvidedService = spProvidedServices.get(i);
                final String spProvidedService_id = selectedSPProvidedService.getSpProvidedService_id();
                final String sp_id = selectedSPProvidedService.getSpId();
                final String sp_companyName = selectedSPProvidedService.getSpCompanyName();
                final String service_id = selectedSPProvidedService.get_id();
                final String service_name = selectedSPProvidedService.get_serviceName();
                final double hoursRate = selectedSPProvidedService.get_hoursRate();

//                Toast.makeText(HomeOwnerServiceList.this,""+selectedSPProvidedService.getSpProvidedService_id(), Toast.LENGTH_SHORT).show();

                AlertDialog.Builder yesorno = new AlertDialog.Builder(HomeOwnerServiceList.this);
                yesorno.setMessage( "Are you sure to book this service?" )
                        .setCancelable( false )
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if(isNotExistInProvidedService()){
                                    String id = databasehoBookedService.push().getKey();
                                    hoBookedService = new HOBookedService(id, ho_id, spProvidedService_id, sp_id, sp_companyName,service_id,service_name,hoursRate);
                                    databasehoBookedService.child(id).setValue(hoBookedService);
                                }
//
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
/*        spId = getIntent().getStringExtra("SPID");
        timeFrom = getIntent().getStringExtra("timeBegin");
        timeTo = getIntent().getStringExtra("timeEnd");*/

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
                //TODO: searchType = time
               /*Query searchQuery = databaseAvailableTimes.orderByChild("spId").equalTo(spId);
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
                */



            case "rating":
                //TODO: searchType = rate
                int rating = getIntent().getIntExtra("rating", 0);
//                Toast.makeText(getApplicationContext(), ""+rating, Toast.LENGTH_SHORT).show();
                databaseRating.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ratings.clear();

                        for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                            Rating rating1 = postSnapshot.getValue(Rating.class);
                            ratings.add(rating1);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                spps_ids_from_rating.clear();

                ratingNums.clear();
//
//                double sum = 0;
//                int count = 0;
//
//                for(Rating rating2 : ratings){
//                    int ratingNum2 = rating2.getRate();
//                    sum += ratingNum2;
//                    count++;
//                    ratingNums.add(ratingNum2);
//                }
//
//                double avgRating = sum/count;

                //TODO: calculate the avg of the rating for the satisfied sppsIDs

                for(Rating rating2 : ratings){
                    if(rating2.getRate() >= rating){
                        spps_ids_from_rating.add(rating2.getSpProvidedService_id());
                    }
                }

                for(String spps_id_from_rating : spps_ids_from_rating){
                    Query query = databaseProvidedServices.orderByChild("spProvidedService_id").equalTo(spps_id_from_rating);
                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                                SPProvidedService spProvidedService1 = postSnapshot.getValue(SPProvidedService.class);
                                spProvidedServices_from_rating.add(spProvidedService1);
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

                }

                break;
        }


    }

    //check if the provided service already in the booked list
    public boolean isNotExistInProvidedService(){

        Query queryRef = databasehoBookedService.orderByChild("ho_id").equalTo(ho_id);

        queryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Hashtable<String, Integer> check = new Hashtable<>();
                for(DataSnapshot postSnapShot : dataSnapshot.getChildren()){
                    SPProvidedService temp = postSnapShot.getValue(SPProvidedService.class);
                    String spProvidedService_id = temp.getSpProvidedService_id();

                    if(check.containsKey(spProvidedService_id)){
                        Toast.makeText(getApplicationContext(), "The service has already been booked!", Toast.LENGTH_SHORT).show();
                        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("hoBookedServices").child(postSnapShot.getKey());
                        Log.i("removing: ", postSnapShot.getKey());
                        dR.removeValue();
                        return;
                    }else{
                        check.put(spProvidedService_id, 1);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return true;
    }

    public double calculatedRate(List<Integer> ratingNums){
        double result = 0;



        return result;
    }
}
