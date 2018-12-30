package ify.com.hotelsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Date;

public class Managers extends AppCompatActivity implements View.OnClickListener {
    Button add, show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managers);
        init();


    }

    private void init() {
        show = findViewById(R.id.show);
        show.setOnClickListener(this);
        add = findViewById(R.id.add);
        add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == show.getId()) {
            SendtoShowactivity();
        }
            else if (id == add.getId()) {
                SendtoAddVacation();
            }
        }

        private void SendtoShowactivity () {
            Intent managerIntent = new Intent(Managers.this, ShowVacation.class);
            startActivity(managerIntent);
        }




    private void SendtoAddVacation() {
        Intent managerIntent = new Intent(Managers.this, AddVacation.class);

        startActivity(managerIntent);
    }
}