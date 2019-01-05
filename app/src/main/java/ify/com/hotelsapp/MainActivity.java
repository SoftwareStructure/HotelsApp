package ify.com.hotelsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import dataHolder.UserDataHolder;

import models.Vacation;


public class MainActivity extends BaseActivity implements View.OnClickListener{

    //Button fields Hotels for hotel managers and Customers for clients
    private Button Hotels;
    private Button Customers;

    //hoding user and customer information.



    //Declare on a database reference
    DatabaseReference mUserVacations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Initializing fields
        init();
    }

    private void init() {

        Hotels = findViewById(R.id.Hotels);
        Customers = findViewById(R.id.Customers);

        //get the app database reference
        mUserVacations=FirebaseDatabase.getInstance().getReference();

        //On click listeners for buttons
        Hotels.setOnClickListener(this);
        Customers.setOnClickListener(this);


    }


    @Override
    protected void onStart() {
        super.onStart();

        mUserVacations.child("vacations").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                dataHolder.getVacationList().clear();

                for (DataSnapshot vacationSnapshot : dataSnapshot.getChildren()) {

                    Vacation vac = vacationSnapshot.getValue(Vacation.class);

                  dataHolder.getVacationList().add(vac);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }


    @Override
    public void onClick(View v) {

        int id = v.getId();

        //Check the view's id and send to correspond activity
        if (id == Hotels.getId())
            startActivity(new Intent(this,LoginActivity.class));

        else if (id == Customers.getId())
            startActivity(new Intent(this, Client.class));
    }
}






