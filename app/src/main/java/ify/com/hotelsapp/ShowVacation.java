package ify.com.hotelsapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import models.Vacation;

public class ShowVacation extends BaseActivity {

    private RecyclerView mRecyclerView;
    private VacationAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    List<Vacation> userVacations=new ArrayList<>();

    Button b;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_vacation);


        init();

        findViewById(R.id.removeBut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ShowVacation.this,String.valueOf(dataHolder.getVacationList().size()),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void init() {


        userVacations.clear();
        for(Vacation vac: dataHolder.getVacationList())
            if(vac.getUserId().equals(dataHolder.getUserId()))
                userVacations.add(vac);


        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new VacationAdapter(userVacations);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

    }
}

