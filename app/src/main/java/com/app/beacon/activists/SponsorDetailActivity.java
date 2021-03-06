package com.app.beacon.activists;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.beacon.sponsor.Sponsor;
import com.bumptech.glide.Glide;
import com.app.beacon.R;

/**
 * Created by Hector_2 on 1/25/2016.
 */
public class SponsorDetailActivity extends AppCompatActivity
        implements View.OnClickListener {
    public static final String EXTRA_PARAM_ID = "com.example.beacon.extra.ID";
    public static final String VIEW_NAME_HEADER_IMAGE = "sponsored_image_shear";

    private TextView mSponsorTitle;
    private TextView mSponsortTextDetail;
    private ImageView mImageExtended;
    private ImageView mImageMapsPreview;

    private Sponsor mSponsorItem;

    private FloatingActionButton mFloatingActionButton;


    private static final String MAPS_PREVIEW = "https://maps.googleapis.com/maps/api/staticmap?" +
            "size=500x500&zoom=18&maptype=roadmap%5C%20&markers=icon:https://turitiando.com/media/" +
            "app_icon.png|18.4849124,-69.9368235";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponser_details);

        initializeVariables();

        setToolbar();

        setTitleCollapse();
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
        mSponsorItem = getIntent().getParcelableExtra(EXTRA_PARAM_ID);

        mSponsorTitle = (TextView) findViewById(R.id.title_sponsor);

        mSponsortTextDetail = (TextView) findViewById(R.id.text_sponsor_detail);

        mImageExtended = (ImageView) findViewById(R.id.image_sponsor);

        mImageMapsPreview = (ImageView) findViewById(R.id.image_map_sponsor);
        mImageMapsPreview.setOnClickListener(this);

        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.floating_button);
        mFloatingActionButton.setOnClickListener(this);

        loadImagesGlide();

    }

    private void loadImagesGlide() {

        //load maps image preview
        Glide.with(mImageMapsPreview.getContext())
                .load(MAPS_PREVIEW)
                .into(mImageMapsPreview);

        Glide.with(mImageExtended.getContext())
                .load(mSponsorItem.getIdSponsorImage())
                .into(mImageExtended);
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

    private void setTitleCollapse() {
        CollapsingToolbarLayout collapse =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapse.setTitle(mSponsorItem.getTextCategory()); // Change title.


        //TODO: make another function for this.

        mSponsorTitle.setText(mSponsorItem.getTextTitle());

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.image_map_sponsor:
                Intent intent = new Intent(this, MapActivity.class);
                startActivity(intent);
                break;

            case R.id.floating_button:
                Snackbar.make(v, "Save this in favorite.", Snackbar.LENGTH_SHORT).show();
                break;

        }
    }
}
