package ify.com.hotelsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddVacation extends AppCompatActivity  {

    Button add;
    EditText country;
    EditText checkIn;
    EditText checkOut;
    EditText Price;
    EditText HotelName;
    EditText LocalOrAbroad;

    DatabaseReference vacationDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vacation);
        init();
        add.setOnClickListener(onClick1);
    }

    private void init() {
        this.add= findViewById(R.id.Add);
        this.country=findViewById(R.id.country);
        this.checkIn=findViewById(R.id.check_in);
        this.checkOut=findViewById(R.id.check_out);
        this.HotelName=findViewById(R.id.Hotel);
        this.Price=findViewById(R.id.Price);
        this.LocalOrAbroad=findViewById(R.id.LocalAbroad);
        vacationDB=FirebaseDatabase.getInstance().getReference("Vacations");

    }

    View.OnClickListener onClick1=(v)->{

        if(validField(country)&&validField(checkIn)&&validField(checkOut)&&validField(Price)&&validField(LocalOrAbroad)&&validField(HotelName)) {

            Vacation v1=new Vacation(country.getText().toString(),checkIn.getText().toString(), checkOut.getText().toString(), HotelName.getText().toString(),
            Integer.parseInt(Price.getText().toString()),Integer.parseInt(LocalOrAbroad.getText().toString()));


            Toast.makeText(AddVacation.this,"Vacation added successfully", Toast.LENGTH_LONG).show();

            vacationDB.push().




        }
        else  return;


    };

    private boolean validField(EditText text){

        if(TextUtils.isEmpty(text.getText().toString())){
            text.setError("Enter please");
            Toast.makeText(AddVacation.this,"Missing field", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    }