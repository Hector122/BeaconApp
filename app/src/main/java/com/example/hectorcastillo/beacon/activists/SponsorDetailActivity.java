package com.example.hectorcastillo.beacon.activists;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hectorcastillo.beacon.R;
import com.example.hectorcastillo.beacon.sponsor.Sponsor;

/**
 * Created by Hector_2 on 1/25/2016.
 */
public class SponsorDetailActivity extends AppCompatActivity {
    public static final String EXTRA_PARAM_ID = "com.example.beacon.extra.ID";
    public static final String VIEW_NAME_HEADER_IMAGE = "sponsored_image_shear";

    private TextView mSponsorTitle;
    private TextView mSponsortTextDetail;
    private ImageView mImageExtended;
    private Sponsor mSponsorItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sponser_details_activity);

        initializeVariables();

        setToolbar();

        setTitleCollapse();

    }

    private void initializeVariables(){
        mSponsorItem = getIntent().getParcelableExtra(EXTRA_PARAM_ID);

        mSponsorTitle = (TextView) findViewById(R.id.title_sponsor);

        mSponsortTextDetail = (TextView) findViewById(R.id.text_sponsor_detail);

        mImageExtended = (ImageView) findViewById(R.id.image_sponsor);

        loadImageExtended();
    }


    private void loadImageExtended() {
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

    private void setTitleCollapse(){
        CollapsingToolbarLayout collapse =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapse.setTitle("Text Title."); // Change title.
    }
}