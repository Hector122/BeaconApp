package com.app.beacon.activists;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.app.beacon.R;
import com.app.beacon.adapters.CustomCategoryAdapter;
import com.app.beacon.custom.CustomItemDecorator;
import com.app.beacon.helper.ParseConfig;
import com.app.beacon.helper.PreferenceManager;
import com.app.beacon.sponsor.SponsorCategory;


/**
 * Created by hector castillo on 12/1/16.
 */
public class CategoryActivity extends AppCompatActivity {
    public static final String EXTRA_DRAWABLE_ID = "app.android.beacon.image";
    private static final int SPAN_COUNT = 2;
    private static final int GRID_MARGIN = 20;

    private DrawerLayout mDrawerLayout;
    private String mDrawerTitle;
    private RecyclerView mGridView;
    private RecyclerView.LayoutManager mLayoutManager;
    private CustomCategoryAdapter mAdapter;
    private PreferenceManager mPreferenceManager;

    private TextView mEmailHeader;
    private TextView mUsernameHeader;

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
        mGridView = (RecyclerView) findViewById(R.id.recycle_view_category_grid);
        mGridView.setHasFixedSize(true);

        //Set the GridViewLayout Manager
        mLayoutManager = new GridLayoutManager(this,SPAN_COUNT);
        mGridView.setLayoutManager(mLayoutManager);

        //Get the SharePreference Manger
        mPreferenceManager = new PreferenceManager(this);

        //Get the Navigation Drawer
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        //set the navigation drawer content views.
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);

            View view = navigationView.getHeaderView(0);

            mEmailHeader = (TextView) view.findViewById(R.id.nav_email_text_view);
            mUsernameHeader = (TextView) view.findViewById(R.id.nav_user_name_text_view);
        }
    }

    private void registerEmailInParse() {

        //TODO:  Check this.
        String emailFromIntent = getIntent().getStringExtra(LoginActivity.EXTRA_EMAIL);
        String email = (emailFromIntent == null) ? "example@test.com.do" : emailFromIntent;

        mEmailHeader.setText(email);
        mUsernameHeader.setText("Sr. Test Example");

        if (!mPreferenceManager.isLoggeIn()) {
            mPreferenceManager.createLoginSession(email, "password");
            ParseConfig.subscribeWithEmail(email);
        }
    }

    private void setCategoryAdapter() {
        mAdapter = new CustomCategoryAdapter(SponsorCategory.ITEMS, CategoryActivity.this);
        mGridView.setAdapter(mAdapter);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mGridView.addItemDecoration(new CustomItemDecorator(GRID_MARGIN));

            int check = mGridView.getHeight();
            Log.i("heigth", String.valueOf(check));

            mGridView.setMinimumHeight(170);
        }
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
                                startActivity(new Intent(CategoryActivity.this, AboutActivity.class));
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

//
//    @Override
//    public void onClick(View v) {
//        Intent intent = new Intent(CategoryActivity.this, SponsorSelectionActivity.class);
//        intent.putExtra(EXTRA_DRAWABLE_ID, mAdapter.getItemViewType(position).getIdDrawable());
//
//        mAdapter.g
//
//        startActivity(intent);
//
//    }
//
//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Intent intent = new Intent(CategoryActivity.this, SponsorSelectionActivity.class);
//        intent.putExtra(EXTRA_DRAWABLE_ID, mAdapter.getItem(position).getIdDrawable());
//        startActivity(intent);
//    }

    private void logout() {
        mPreferenceManager.logout();

        Intent intent = new Intent(this,
                MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
