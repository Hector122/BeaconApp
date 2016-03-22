package com.app.beacon.fragments;

import android.content.Context;
import android.os.Build;
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
import com.app.beacon.app.AppConstants;
import com.app.beacon.custom.CustomItemDecorator;

/**
 * Created by hector castillo on 14/3/16.
 */
public class FavoriteCompanyFragment extends Fragment {
    private final static String LAYOUT = "beacom.com.do.boolean";

    private RecyclerView mGridView;
    private RecyclerView.LayoutManager mLayoutManager;
    private Context mContext;
    private FavoriteCompaniesAdapter mAdapter;

    private boolean isLayoutOne;

    public static FavoriteCompanyFragment newInstance(boolean value) {
        FavoriteCompanyFragment fragment = new FavoriteCompanyFragment();
        Bundle args = new Bundle();
        args.putBoolean(LAYOUT, value);
        fragment.setArguments(args);
        return fragment;
    }

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
        mLayoutManager = new GridLayoutManager(mContext, AppConstants.SPAN_COUNT_THREE);
        mGridView.setLayoutManager(mLayoutManager);

        isLayoutOne = getArguments().getBoolean(LAYOUT);
    }

    /**
     * Initializer the data with the data need.
     */
    private void setFavoriteAdapter() {
        mAdapter = new FavoriteCompaniesAdapter(isLayoutOne, getActivity());
        mGridView.setAdapter(mAdapter);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (isLayoutOne) {
                mGridView.addItemDecoration(new CustomItemDecorator(AppConstants.GRID_MARGIN));
                mGridView.setMinimumHeight(170);
            } else {
                mGridView.addItemDecoration(new CustomItemDecorator(AppConstants.GRID_MARGIN, true));
                mGridView.setMinimumHeight(120);
            }
        }
    }
}
