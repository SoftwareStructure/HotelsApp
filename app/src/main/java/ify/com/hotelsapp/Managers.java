package ify.com.hotelsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class
Managers extends BaseActivity implements View.OnClickListener{

    Button add;
    Button show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managers);

        init();
    }


        private void init() {
          add=  findViewById(R.id.addButton);
          add.setOnClickListener(this);
          show=  findViewById(R.id.showButton);
          show.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {

            int id=v.getId();

            if(id==add.getId())
                startActivity(new Intent(this ,AddVacation.class));
            else if(id==show.getId())
                startActivity(new Intent(this,ShowVacation.class));
        }
    }

