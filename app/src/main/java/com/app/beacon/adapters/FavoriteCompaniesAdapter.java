package com.app.beacon.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.beacon.R;
import com.app.beacon.activists.DashboardContainerActivity;
import com.app.beacon.activists.SponsorSelectionActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hector castillo on 15/3/16.
 */
public class FavoriteCompaniesAdapter extends RecyclerView.Adapter<FavoriteCompaniesAdapter.ViewHolder> {
    //private List<Sponsor> sponsors;
    private Boolean isLayoutOne;
    private Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView companyName;
        private ImageView companyImage;
        private View viewHolder;

        public ViewHolder(View view) {
            super(view);
            viewHolder = view;
            companyName = (TextView) view.findViewById(R.id.fav_company_title);
            companyImage = (ImageView) view.findViewById(R.id.fav_company_logo);
        }
    }

    public FavoriteCompaniesAdapter(boolean isLayoutOne, Context context) {
        this.isLayoutOne = isLayoutOne;
        mContext = context;
    }

    //TODO: change the layout here.
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = 0;

        if (isLayoutOne) {
            layoutId = R.layout.item_favorite_companies_one;
        } else {
            layoutId = R.layout.item_favorite_companies_two;
        }

        View view = LayoutInflater.from(parent.getContext()).
                inflate(layoutId, parent, false);

        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
       final FavoritesCompany item = FavoritesCompany.COMPANIES.get(position);

        holder.companyName.setText(item.name);
        //holder.companyImage.setImageResource(item.logo);
        Glide.with(holder.companyImage.getContext())
                .load(item.logo)
                .crossFade(300)
                .into(holder.companyImage);


        holder.viewHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SponsorSelectionActivity.class);
                intent.putExtra(DashboardContainerActivity.EXTRA_DRAWABLE_ID, item.logo);
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return FavoritesCompany.COMPANIES.size();
    }

    //TODO: class used only for testing in production make FavoritesCompany outside here.
    public static class FavoritesCompany {
        public String name;
        public int logo;

        public FavoritesCompany(String name, int logo) {
            this.name = name;
            this.logo = logo;
        }

        public final static List<FavoritesCompany> COMPANIES = new ArrayList<FavoritesCompany>();

        static {
            COMPANIES.add(new FavoritesCompany("ZARA_1", R.drawable.category_all));
            COMPANIES.add(new FavoritesCompany("ZARA", R.drawable.category_all));
            COMPANIES.add(new FavoritesCompany("ZARA",  R.drawable.category_all));
            COMPANIES.add(new FavoritesCompany("ZARA", R.drawable.category_all));
            COMPANIES.add(new FavoritesCompany("ZARA", R.drawable.category_all));
            COMPANIES.add(new FavoritesCompany("ZARA",  R.drawable.category_all));
            COMPANIES.add(new FavoritesCompany("ZARA", R.drawable.category_all));
            COMPANIES.add(new FavoritesCompany("ZARA", R.drawable.category_all));
            COMPANIES.add(new FavoritesCompany("ZARA", R.drawable.category_all));
            COMPANIES.add(new FavoritesCompany("ZAR_2",  R.drawable.category_all));
        }
    }
}
