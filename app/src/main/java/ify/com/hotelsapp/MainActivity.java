package ify.com.hotelsapp;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.ImageView;

import com.google.android.gms.common.api.Api;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button Hotels;
    private Button Customers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }


    private void init() {
        Hotels = findViewById(R.id.Hotels);
        Hotels.setOnClickListener(this);
        Customers = findViewById(R.id.Customers);
        Customers.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == Hotels.getId()) {
            SendtoHotelsctivity();
        }
        else if (id == Customers.getId()) {
            SendtoCustomers();
        }
    }

    private void SendtoCustomers() {
        Intent managerIntent = new Intent(MainActivity.this, Client.class);
        startActivity(managerIntent);
    }

    private void SendtoHotelsctivity() {
        Intent managerIntent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(managerIntent);
    }
}






