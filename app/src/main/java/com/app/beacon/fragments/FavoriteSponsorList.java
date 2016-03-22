package com.app.beacon.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.beacon.R;
import com.app.beacon.adapters.SponsorAdapter;
import com.app.beacon.sponsor.Sponsor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hector castillo on 21/3/16.
 */
public class FavoriteSponsorList extends Fragment {

    //Global instance.
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sponsor_selection,container, false);

        initializeVariables(view);

        return view;
    }

    private void initializeVariables(View view) {
        //Get the recycler view
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_view);
        mRecyclerView.setHasFixedSize(true);

        //Use manager for the Linear layout
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        //Create a new Adapter
        setSponsorAdapter();
    }

    private void setSponsorAdapter() {
        List<Sponsor> list = new ArrayList<>();

        //Dummy Random data for the demonstration.
        for (int i = 0; i < 9; i++) {
          Sponsor  sponsor = new Sponsor();
            sponsor.setTextTitle(Sponsor.getRandomSponsorTitle());
            sponsor.setTextDateFormat(Sponsor.getRandomSponsorDate());
            sponsor.setTextCategory(Sponsor.getRandomSponsorCategory());
            sponsor.setIdSponsorImage(Sponsor.getRandomSponsorDrawable());

            list.add(sponsor);
        }

        //Set the adapter
        mAdapter = new SponsorAdapter(list, true);
        mRecyclerView.setAdapter(mAdapter);
    }
}
