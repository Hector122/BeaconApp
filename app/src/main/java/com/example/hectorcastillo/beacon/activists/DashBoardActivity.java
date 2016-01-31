package com.example.hectorcastillo.beacon.activists;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.hectorcastillo.beacon.R;
import com.example.hectorcastillo.beacon.adapters.CategoryAdapter;
import com.example.hectorcastillo.beacon.helper.ParseConfig;
import com.example.hectorcastillo.beacon.helper.PreferenceManager;

/**
 * Created by hector castillo on 12/1/16.
 */
public class DashBoardActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener {
    public static final String EXTRA_DRAWABLE_ID = "app.android.beacon.image";

    private DrawerLayout mDrawerLayout;
    private String mDrawerTitle;

    private GridView mGridView;
    private CategoryAdapter mAdapter;

    private PreferenceManager mPreferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // set the corresponding view.
        initializeVariables();

        //set toolbar like action bar.
        setToolbar();

        //Register email address in parse.
        registerEmailInParse();

        //Initialize the adapter
        setCategoryAdapter();

        mDrawerTitle = getResources().getString(R.string.item_home);
        if (savedInstanceState == null) {
            selectItem(mDrawerTitle);
        }
    }

    private void initializeVariables() {
        //Get the references for the views
        mGridView = (GridView) findViewById(R.id.grid_view);
        mGridView.setDrawSelectorOnTop(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mPreferenceManager = new PreferenceManager(this);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
    }

    private void registerEmailInParse() {
        //TODO:  Check this.
        String emailFromIntent = getIntent().getStringExtra(LoginActivity.EXTRA_EMAIL);
        String email = (emailFromIntent == null) ? "example@test.com.do" : emailFromIntent;

        //
        mPreferenceManager.createLoginSession(email, "password");
        //
        ParseConfig.subscribeWithEmail(email);
    }

    private void setCategoryAdapter() {
        mAdapter = new CategoryAdapter(this);
        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_action, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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

        if (null != actionBar) {
            // Put icon in the drawer toggle
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // Mark pressed item
                        menuItem.setChecked(true);
                        // Create a new Fragment
                        String title = menuItem.getTitle().toString();
                        selectItem(title);
                        return true;
                    }
                }
        );
//TODO: set the email form the intent
        TextView emailNavigationView = (TextView) navigationView.findViewById(R.id.nav_email_text_view);

//
//        if (mPreferenceManager.isLoggeIn() && mPreferenceManager.getEmail() != null) {
//            emailNavigationView.setText(mPreferenceManager.getEmail());
//        }
    }

    private void selectItem(String title) {
        //Close drawer
        mDrawerLayout.closeDrawers();

        //Set current title
        setTitle(title);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        Intent intent = new Intent(DashBoardActivity.this, SponsorSelectionActivity.class);
        intent.putExtra(EXTRA_DRAWABLE_ID, mAdapter.getItem(position).getIdDrawable());
        startActivity(intent);
    }
}
