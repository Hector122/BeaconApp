package com.app.beacon.activists;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.app.beacon.R;
import com.app.beacon.fragments.AboutFragment;
import com.app.beacon.fragments.CategoryFragment;
import com.app.beacon.fragments.FavoriteTabFragment;
import com.app.beacon.fragments.MainSponsorFragment;
import com.app.beacon.helper.ParseConfig;
import com.app.beacon.helper.PreferenceManager;


/**
 * Created by hector castillo on 12/1/16.
 */
public class DashboardContainerActivity extends AppCompatActivity {
    public static final String EXTRA_DRAWABLE_ID = "app.android.beacon.image";

    //View reference variables.
    private DrawerLayout mDrawerLayout;
    private String mDrawerTitle;
    private PreferenceManager mPreferenceManager;

    //Reference variables in the xml layout.
    private TextView mEmailHeader;
    private TextView mUsernameHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_container);

        // set the corresponding view.
        initializeVariables();

        //set toolbar like action bar.
        setToolbar();

        //Register email address in parse.
        registerEmailInParse();

        mDrawerTitle = getResources().getString(R.string.item_home);
        if (savedInstanceState == null) {
            selectItemTitle(mDrawerTitle);
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

    //TODO: Check is this is needed.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 5 && resultCode == RESULT_OK) {
            replaceCurrentFragment(new AboutFragment());
        }
    }

    /**
     * Initializer activity variables
     */

    private void initializeVariables() {
        //Get the SharePreference Manager
        mPreferenceManager = new PreferenceManager(this);

        //Get the Navigation Drawer
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        //Set the Navigation Drawer content view.
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            //set the content in drawer
            setUpDrawerContent(navigationView);

            //Select item by default
            selectItem(navigationView.getMenu().getItem(0));

            //Reference to header container in the Nav.
            View view = navigationView.getHeaderView(0);

            mEmailHeader = (TextView) view.findViewById(R.id.nav_email_text_view);
            mUsernameHeader = (TextView) view.findViewById(R.id.nav_user_name_text_view);
        }
    }

    /**
     * Get and set the Toolbar in main layout view.
     */

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //support previous version.
        final ActionBar actionBar = getSupportActionBar();

        if (null != actionBar) {
            // Put icon in the drawer toggle
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * Register email in Parse for Push notification and save the Email and Password
     * in SharedPreference, NOTE: Password in plain Text only for the demo, when the
     * app is in production encrypted before store.
     */

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

    /**
     * @param navigationView
     */
    private void setUpDrawerContent(final NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        selectItem(menuItem);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    /**
     * Select the item fo ingfla
     * @param menuItem
     */

    private void selectItem(MenuItem menuItem) {
        // Create a new Fragment
        Fragment fragment = null;

        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                fragment = new MainSponsorFragment();
                break;

            case R.id.nav_favorite:
                fragment = new FavoriteTabFragment();
                break;

            case R.id.nav_explore:
                fragment = new CategoryFragment();
                break;

            case R.id.nav_about:
                fragment = new AboutFragment();
                break;

            case R.id.nav_sub_logout:
                logout();
                break;
        }

        if (fragment != null) {
            replaceCurrentFragment(fragment);
        }

        //Set the actual title.
        String title = menuItem.getTitle().toString();
        selectItemTitle(title);
    }

    /**
     *  Set the fragment activity to show in the principal activity.
     * @param fragment
     */

    private void replaceCurrentFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.principal_content_relative, fragment)
                .commit();
    }

    /***
     * select name of the menu in the toolbar.
     *
     * @param title String with the name of
     */

    private void selectItemTitle(String title) {
        //Close drawer
        mDrawerLayout.closeDrawers();

        //Set current title
        setTitle(title);
    }

    /**
     * Remove the user credentials in SharePreference.
     */

    private void logout() {
        mPreferenceManager.logout();

        Intent intent = new Intent(this,
                MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
