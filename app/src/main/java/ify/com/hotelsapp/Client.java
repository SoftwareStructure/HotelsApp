package ify.com.hotelsapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class Client extends AppCompatActivity {

    ListView listViewVacation;
    List<Vacation> vacationList;
    //DatabaseReference UserDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        listViewVacation=(ListView)findViewById(R.id.listViewVacation);
        vacationList=new ArrayList<>();
      //  UserDB=FirebaseDatabase.getInstance().getReference();

    }



    @Override
    protected void onStart() {
        super.onStart();


       /* UserDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                vacationList.clear();
                for(DataSnapshot vacationSnapshot : dataSnapshot.getChildren()){
                    Vacation vacation=vacationSnapshot.getValue(Vacation.class);
                    vacationList.add(vacation);
                }
                VacationList adapter=new VacationList(Client.this, vacationList);
                listViewVacation.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
    }
}
