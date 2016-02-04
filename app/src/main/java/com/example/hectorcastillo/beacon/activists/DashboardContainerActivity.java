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
 * Created by hector castillo on 3/2/16.
 */
public class DashboardContainerActivity extends AppCompatActivity
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
        setContentView(R.layout.activity_category);

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
        //mGridView.setExpanded(true);

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


                        //TODO: put here the action to make in the navigation drawer

                        switch (menuItem.getItemId()) {

                            case R.id.nav_home:
                                break;

                            case R.id.nav_favorite:
                                break;

                            case R.id.nav_explore:
                                break;

                            case R.id.nav_about:
                                break;

                            case R.id.nav_sub_logout:
                                logout();
                                break;
                        }

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

    /***
     * select name of the menu in the toolbar.
     *
     * @param title String with the name of
     */

    private void selectItem(String title) {
        //Close drawer
        mDrawerLayout.closeDrawers();

        //Set current title
        setTitle(title);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(DashboardContainerActivity.this, SponsorSelectionActivity.class);
        intent.putExtra(EXTRA_DRAWABLE_ID, mAdapter.getItem(position).getIdDrawable());
        startActivity(intent);
    }

    private void logout() {
        mPreferenceManager.logout();

        Intent intent = new Intent(this,
                MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
