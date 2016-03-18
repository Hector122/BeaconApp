package com.app.beacon.fragments;

import android.support.v4.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.beacon.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hector castillo on 14/3/16.
 */
public class FavoriteTabFragment extends Fragment {
    private AppBarLayout mAppBar;
    private TabLayout mTabs;
    private ViewPager mViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fav_container, container, false);

        if (view != null) {
            initializerVariables(container, view);

            insertTabs();

            setViewPager();
        }

        return view;
    }


    private void initializerVariables(ViewGroup container, View view) {
        mViewPager = (ViewPager) view.findViewById(R.id.pager_view);

        View root = (View) container.getParent();
        mAppBar = (AppBarLayout) root.findViewById(R.id.appbar);
    }

    private void insertTabs() {
        mTabs = new TabLayout(getActivity());
        mTabs.setTabTextColors(Color.parseColor("#FFFFFF"), Color.parseColor("#FFFFFF"));
        mAppBar.addView(mTabs);
    }

    //TODO: here ;)
    private void setViewPager() {
        DivisionAdapter adapter = new DivisionAdapter(getFragmentManager());
        adapter.addFragment(new FavoriteCompanyFragment().newInstance(true), getString(R.string.action_sign_in));
        adapter.addFragment(new FavoriteCompanyFragment().newInstance(false), getString(R.string.action_continue));
        mViewPager.setAdapter(adapter);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mAppBar.removeView(mTabs);
    }

    public class DivisionAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> fragments = new ArrayList<>();
        private final List<String> fragmentsTitles = new ArrayList<>();

        public DivisionAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentsTitles.get(position);
        }

        public void addFragment(Fragment fragment, String title) {
            fragments.add(fragment);
            fragmentsTitles.add(title);
        }
    }
}
