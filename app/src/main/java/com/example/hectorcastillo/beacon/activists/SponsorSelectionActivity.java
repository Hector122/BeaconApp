package com.example.hectorcastillo.beacon.activists;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.hectorcastillo.beacon.R;
import com.example.hectorcastillo.beacon.adapters.SponsorAdapter;
import com.example.hectorcastillo.beacon.sponsor.Sponsor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hector_2 on 1/24/2016.
 */
public class SponsorSelectionActivity extends AppCompatActivity {
    //Global instance.
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sponsor_selection_activity);

        initializeVariables();

        setToolbar();
    }

    private void initializeVariables() {
        //Get the recycler view
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        mRecyclerView.setHasFixedSize(true);

        //Use manager for the Linear layout
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //Create a new Adapter
        setSponsorAdapter();
    }

    /**
     * Set the Android Toolbar
     */
    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            // Providing back navigation.
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setSponsorAdapter() {
        //TODO: now is fix, this data suppose to parse form JSON object.
        List<Sponsor> list = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            Sponsor sponsor = new Sponsor();
            sponsor.setTextTitle("Text Title " + String.valueOf(i));
            sponsor.setTextDateFormat("26, January");
            sponsor.setTextCategory("Health & fitness");
            sponsor.setIdSponsorImage(R.drawable.category_men_lifestyle_625x300);

            list.add(sponsor);

//            list.add(new Sponsor("Text Title " + String.valueOf(i),
//                    "26, January", "Health & fitness",
//                    R.drawable.category_men_lifestyle_625x300));
        }

        //Set the adapter
        mAdapter = new SponsorAdapter(list);
        mRecyclerView.setAdapter(mAdapter);

    }
}
