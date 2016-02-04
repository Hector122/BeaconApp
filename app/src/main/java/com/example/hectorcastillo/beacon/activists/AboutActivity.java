package com.example.hectorcastillo.beacon.activists;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.hectorcastillo.beacon.R;

/**
 * Created by Hector_2 on 2/2/2016.
 */
public class AboutActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_about);
    }



    //    LinearLayout mLinearLayout;
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
//
//        View view = inflater.inflate(R.layout.activity_about, container, false);
//
//        mLinearLayout = (mLinearLayout) view.findViewById(R.id.about_container);
//        setToolbar()
//
//        return view;
//    }
//
//    private void setToolbar() {
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        //TODO: support previous version.
//        final ActionBar actionBar = getSupportActionBar();
//
//        if (null != actionBar) {
//            // Put icon in the drawer toggle
//            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }
//    }


}
