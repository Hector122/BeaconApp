package com.app.beacon.activists;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.app.beacon.adapters.SponsorAdapter;
import com.app.beacon.R;
import com.app.beacon.sponsor.Sponsor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hector_2 on 1/24/2016.
 */
public class SponsorSelectionActivity extends AppCompatActivity {
    //Global instance.
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    //Swipe Refresh
   private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsor_selection);

        initializeVariables();

        setToolbar();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initializeVariables() {
        //Get the recycler view
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        mRecyclerView.setHasFixedSize(true);

        //Use manager for the Linear layout
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //Get the refreshLayout
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);

        //Set the colors
        mSwipeRefreshLayout.setColorSchemeResources(
                R.color.c1,
                R.color.c2,
                R.color.c3,
                R.color.c4 );

        //Set Refresh listener
        mSwipeRefreshLayout.setOnRefreshListener(new MyOnRefreshListeners());

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

       int drawableId = getIntent().getIntExtra(DashboardContainerActivity.EXTRA_DRAWABLE_ID, 0);

        //This is the reference to the category chosen.
        Sponsor sponsor = new Sponsor();
        sponsor.setTextTitle("Text Title " + 1);
        sponsor.setTextDateFormat("26, January");
        sponsor.setTextCategory("Health & fitness");
        sponsor.setIdSponsorImage(drawableId);

        list.add(sponsor);



        //Dummy Random data for the demonstration.
        for (int i = 0; i < 5; i++) {
            sponsor = new Sponsor();
            sponsor.setTextTitle(Sponsor.getRandomSponsorTitle());
            sponsor.setTextDateFormat(Sponsor.getRandomSponsorDate());
            sponsor.setTextCategory(Sponsor.getRandomSponsorCategory());
            sponsor.setIdSponsorImage(Sponsor.getRandomSponsorDrawable());

            list.add(sponsor);
        }

        //Set the adapter
        mAdapter = new SponsorAdapter(list);
        mRecyclerView.setAdapter(mAdapter);

    }


    class MyOnRefreshListeners implements SwipeRefreshLayout.OnRefreshListener{

        @Override
        public void onRefresh() {
            new HackingBackgroundTask().execute();
        }
    }

    /***
     *
     */

    private class HackingBackgroundTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
                return true;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }
}
