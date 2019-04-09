package ify.com.hotelsapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import models.User;
import models.Vacation;

public class AddVacation extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "AddVacation";

    FloatingActionButton add;

    EditText country;
    EditText checkIn;
    EditText checkOut;
    EditText price;
    EditText hotelName;
    EditText localOrAbroad;

    Button uploadImage;

    FirebaseUser user;
    DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vacation);

        init();

    }

    private void init() {

        add=findViewById(R.id.tyty);
        add.setOnClickListener(this);


        this.country = findViewById(R.id.country);
        this.checkIn = findViewById(R.id.check_in);
        this.checkOut = findViewById(R.id.check_out);
        this.hotelName = findViewById(R.id.Hotel);
        this.price = findViewById(R.id.Price);
        this.localOrAbroad = findViewById(R.id.LocalAbroad);

        this.uploadImage=findViewById(R.id.uploadImage);
        this.uploadImage.setOnClickListener(this);

        user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }







    private void addVacation() {

      //  Disable button so there are no multi-posts
        setEditingEnabled(false);

       showProgressDialog();

        // [START single_value_read]
        final String userId = user.getUid();

        mDatabase.child("users").child(userId).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        User user = dataSnapshot.getValue(User.class);

                        // [START_EXCLUDE]
                        if (user == null) {
                            // User is null, error out
                            Log.e(TAG, "User " + userId + " is unexpectedly null");

                            Toast.makeText(AddVacation.this,
                                    "Error: could not fetch user.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // Write new post
                            uploadNewVacation(userId, user.getEmail(),user.getName());
                        }

                        //Finish this Activity, back to the stream
                        hideProgressDialog();
                        setEditingEnabled(true);
                       finish();

                        // [END_EXCLUDE]
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "getUser:onCancelled", databaseError.toException());
                        // [START_EXCLUDE]
                        setEditingEnabled(true);
                        // [END_EXCLUDE]
                    }
                });
        // [END single_value_read]
    }

    private void setEditingEnabled(boolean enabled) {

        country.setEnabled(enabled);
        checkIn.setEnabled(enabled);
        checkOut.setEnabled(enabled);
        price.setEnabled(enabled);
        localOrAbroad.setEnabled(enabled);
        hotelName.setEnabled(enabled);

        if (enabled)
         add.show();

         else add.hide();
    }

    // [START write_fan_out]
    private void uploadNewVacation(String userId, String username, String email) {
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        String key = mDatabase.child("vacations").push().getKey();
        Vacation vacation = new Vacation(userId, country.getText().toString(),checkIn.getText().toString(),
                checkOut.getText().toString(),hotelName.getText().toString(),price.getText().toString(),localOrAbroad.getText().toString());

        // Map<String, Object> vacationValues = vacation.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/vacations/" + key, vacation);
        childUpdates.put("/user-vacations/" + userId + "/" + key, vacation);

        mDatabase.updateChildren(childUpdates);
    }
    // [END write_fan_out]

    private boolean validateForm() {

        boolean valid = true;

        String country = this.country.getText().toString();
        if (TextUtils.isEmpty(country)) {
            this.country.setError("Required.");
            valid = false;

        } else this.country.setError(null);

        String checkIn = this.checkIn.getText().toString();
        if (TextUtils.isEmpty(checkIn)) {
            this.checkIn.setError("Required.");
            valid = false;

        } else this.checkIn.setError(null);

        String checkOut = this.checkOut.getText().toString();
        if (TextUtils.isEmpty(checkOut)) {
            this.checkOut.setError("Required.");
            valid = false;

        } else this.checkOut.setError(null);

        String price = this.price.getText().toString();
        if (TextUtils.isEmpty(price)) {
            this.price.setError("Required.");
            valid = false;

        } else this.price.setError(null);

        String hotelName = this.hotelName.getText().toString();
        if (TextUtils.isEmpty(hotelName)) {
            this.hotelName.setError("Required.");
            valid = false;

        } else this.hotelName.setError(null);

        String localOrAbroad = this.localOrAbroad.getText().toString();
        if (TextUtils.isEmpty(localOrAbroad)) {
            this.localOrAbroad.setError("Required.");
            valid = false;

        } else this.localOrAbroad.setError(null);

        if(!valid)
            Toast.makeText(AddVacation.this,"Missing Field", Toast.LENGTH_LONG).show();

        return valid;
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        //Check the view's id and send to correspond activity
        if (id == uploadImage.getId())
            startActivity(new Intent(this,UploadImageActivity.class));

        else if (id == add.getId()){

            Log.d(TAG, "Adding");

            if (!validateForm())
                return;

            addVacation();

    }
    }
}







