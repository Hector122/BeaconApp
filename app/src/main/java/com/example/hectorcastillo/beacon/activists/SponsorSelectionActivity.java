package com.example.hectorcastillo.beacon.activists;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

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
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    //Swipe Refresh
   private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sponsor_selection_activity);

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

       int drawableId = getIntent().getIntExtra(DashBoardActivity.EXTRA_DRAWABLE_ID, 0);
       // R.drawable.category_men_lifestyle_625x300

        for (int i = 0; i < 6; i++) {
            Sponsor sponsor = new Sponsor();
            sponsor.setTextTitle("Text Title " + String.valueOf(i));
            sponsor.setTextDateFormat("26, January");
            sponsor.setTextCategory("Health & fitness");
            sponsor.setIdSponsorImage(drawableId);

            list.add(sponsor);

//            list.add(new Sponsor("Text Title " + String.valueOf(i),
//                    "26, January", "Health & fitness",
//                    R.drawable.category_men_lifestyle_625x300));
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
