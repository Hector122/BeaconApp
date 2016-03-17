package com.app.beacon.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.app.beacon.R;
import com.app.beacon.adapters.FavoriteCompaniesAdapter;
import java.util.Random;

/**
 * Created by hector castillo on 14/3/16.
 */
public class FavoriteCompanyFragment extends Fragment {
    private final int SPAN_COUNT = 3;

    private RecyclerView mGridView;
    private RecyclerView.LayoutManager mLayoutManager;
    private Context mContext;
    private FavoriteCompaniesAdapter mAdapter;


    //TODO:
    private boolean isLayoutOne;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        initializeVariables(view);

        setFavoriteAdapter();

        return view;
    }


    private void initializeVariables(View view) {
        //Get Activity context.
        mContext = getActivity();

        //Get the references for the views
        mGridView = (RecyclerView) view.findViewById(R.id.recycle_view);
        mGridView.setHasFixedSize(true);

        //Set the GridViewLayout Manager
        mLayoutManager = new GridLayoutManager(mContext, SPAN_COUNT);
        mGridView.setLayoutManager(mLayoutManager);
    }

    /**
     * Initializer the data with the data need.
     */
    private void setFavoriteAdapter() {
        Random random = new Random();
        int generate = random.nextInt(5);

        boolean x = (generate < 3) ? true : false;

        mAdapter = new FavoriteCompaniesAdapter(x, getActivity());
        mGridView.setAdapter(mAdapter);

//        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            mGridView.addItemDecoration(new CustomItemDecorator(GRID_MARGIN));
//            mGridView.setMinimumHeight(170);
//        }
    }
}
