package ify.com.hotelsapp;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import static ify.com.hotelsapp.LoginActivity.currentemail;

public class AddVacation extends AppCompatActivity  {

    Button add;
    EditText country;
    EditText checkIn;
    EditText checkOut;
    EditText Price;
    EditText HotelName;
    EditText LocalOrAbroad;
    DatabaseReference VacationDB;
    static DatabaseReference UserDB;
    static  String counter="1";
    FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();

    String UserEmail=user.getEmail();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vacation);
        init();
        onCLick();
    }
    private void init() {
        this.add= findViewById(R.id.Add);
        this.country=findViewById(R.id.country);
        this.checkIn=findViewById(R.id.check_in);
        this.checkOut=findViewById(R.id.check_out);
        this.HotelName=findViewById(R.id.Hotel);
        this.Price=findViewById(R.id.Price);
        this.LocalOrAbroad=findViewById(R.id.LocalAbroad);
        VacationDB=FirebaseDatabase.getInstance().getReference("Vacation");
        UserDB=FirebaseDatabase.getInstance().getReference("UsersId");
    }
    private void onCLick() {

        this.add.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
        if(
                validfield(country)&&
                validfield(checkIn)&&
                validfield(checkOut)&&
                validfield(Price)&&
                validfield(LocalOrAbroad)&&
                validfield(HotelName)) {
                Toast.makeText(AddVacation.this,"Vacation added"+UserEmail, Toast.LENGTH_LONG).show();
                String id1 =VacationDB.push().getKey();
                String id2 =UserDB.push().getKey();
                Vacation vac=new Vacation(country.getText().toString(),checkIn.getText().toString(),checkOut.getText().toString(),HotelName.getText().toString(),Integer.parseInt(Price.getText().toString()),0);
                UserDB.child(UserEmail).child("vac2").setValue(vac);






        }
        else return;








            }
        });
    }
    private boolean validfield(EditText a){
        if(TextUtils.isEmpty(a.getText().toString())){
            a.setError("Enter please");
            Toast.makeText(AddVacation.this,"erorrrrrrrrrrrrrrr", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }







}
