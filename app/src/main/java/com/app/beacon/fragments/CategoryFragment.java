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
import com.app.beacon.adapters.CategoryAdapter;
import com.app.beacon.custom.CustomItemDecorator;
import com.app.beacon.sponsor.SponsorCategory;

/**
 * Created by hector castillo on 11/3/16.
 */
public class CategoryFragment extends Fragment {
    //Number of column in the gridView.
    private static final int SPAN_COUNT = 2;
    private static final int GRID_MARGIN = 20;

    //Views Reference Variables.
    private RecyclerView mGridView;
    private RecyclerView.LayoutManager mLayoutManager;
    private CategoryAdapter mAdapter;

    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_category, container, false);

        initializeVariables(view);

        setCategoryAdapter();

        return view;
    }

    /**
     * Initializer Variables from layout.
     *
     * @param view
     */
    private void initializeVariables(View view) {
        //Get Activity context.
        mContext = getActivity().getApplicationContext();

        //Get the references for the views
        mGridView = (RecyclerView) view.findViewById(R.id.recycle_view_category_grid);
        mGridView.setHasFixedSize(true);

        //Set the GridViewLayout Manager
        mLayoutManager = new GridLayoutManager(mContext, SPAN_COUNT);
        mGridView.setLayoutManager(mLayoutManager);

    }

    /**
     * Initializer the data with the data need.
     */
    private void setCategoryAdapter() {
        mAdapter = new CategoryAdapter(SponsorCategory.ITEMS, mContext);
        mGridView.setAdapter(mAdapter);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mGridView.addItemDecoration(new CustomItemDecorator(GRID_MARGIN));
            mGridView.setMinimumHeight(170);
        }
    }
}
