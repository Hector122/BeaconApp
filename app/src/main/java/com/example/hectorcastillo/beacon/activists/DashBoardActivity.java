package com.example.hectorcastillo.beacon.activists;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.hectorcastillo.beacon.R;

/**
 * Created by hector castillo on 12/1/16.
 */
public class DashBoardActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private String mDrawerrTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //set toolbar like action bar.
        setToolbar();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        if (navigationView == null) {
            //Add Features.

        }


//TODO: delete this.
        //mPlanetTitles = getResources().getStringArray(R.array.planets_array);
        // mDrawerList = (ListView) findViewById(R.id.left_drawer);


        //mDrawerList.setAdapter(new ArrayAdapter<String>(this,
        //      R.layout.drawer_list_item, mPlanetTitles));
        // Set the list's click listener
        // mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(!mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            getMenuInflater().inflate(R.menu.drawer_view, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //TODO: support previous version.
        final ActionBar actionBar = getSupportActionBar();

        if(null != actionBar){
            // Put icon in the drawer toggle
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

}
