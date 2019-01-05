package ify.com.hotelsapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.Collections;
import java.util.Comparator;

import models.Vacation;

public class Client extends BaseActivity {

    private RecyclerView mRecyclerView;
    private VacationAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    Button sort;

    CheckBox sortPrice;
    CheckBox sortCheckIn;
    CheckBox sortCheckOut;
    CheckBox sortName;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        init();


    }

    private void init() {
        findViewById(R.id.sortBut).setOnClickListener(onClick);
        mRecyclerView = (RecyclerView) findViewById(R.id.client_recycle);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new VacationAdapter(dataHolder.getVacationList());
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

    View.OnClickListener onClick = ((View v) -> {

        Collections.sort(dataHolder.getVacationList(), new Comparator<Vacation>() {
            @Override
            public int compare(Vacation o1, Vacation o2) {
                if (Integer.parseInt(o1.getPrice()) > Integer.parseInt(o2.getPrice()))
                    return 1;
                else if (Integer.parseInt(o1.getPrice()) == Integer.parseInt(o2.getPrice()))
                    return 0;
                else return -1;
            }
        });

        mAdapter=new VacationAdapter(dataHolder.getVacationList());
        mRecyclerView.setAdapter(mAdapter);

    });
}







