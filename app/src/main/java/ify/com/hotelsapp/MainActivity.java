package ify.com.hotelsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    Button Hotels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setClickListener();
    }

    private void setClickListener() {

        Hotels.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);            }
                });
    }

    private void init() {
       Hotels = (Button)(findViewById(R.id.Hotels));
    }




    }






