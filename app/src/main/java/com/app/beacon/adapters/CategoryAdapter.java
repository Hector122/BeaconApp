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
import com.app.beacon.sponsor.SponsorCategory;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hector castillo on 10/2/16.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private final List<SponsorCategory> items;
    private Context mContext;

    /***
     * Constructor
     * @param sponsors
     * @param context
     */

    public CategoryAdapter(SponsorCategory[] sponsors, Context context) {
        items = new ArrayList<SponsorCategory>();
        mContext = context;

        //populate the list with the array of element.
        for (SponsorCategory sponsor : sponsors) {
            items.add(sponsor);
        }
    }

    /***
     * View Holder patter
     */

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View viewHolder;
        public TextView categoryName;
        public ImageView categoryImage;

        public ViewHolder(View view) {
            super(view);
            viewHolder = view;
            categoryImage = (ImageView) view.findViewById(R.id.category_image);
            categoryName = (TextView) view.findViewById(R.id.category_name);
        }
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //get the item
        final SponsorCategory item = items.get(position);

        holder.categoryName.setText(item.getName());
        Glide.with(holder.categoryImage.getContext())
                .load(item.getIdDrawable())
                .crossFade(300)
                .into(holder.categoryImage);


        holder.viewHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SponsorSelectionActivity.class);
                intent.putExtra(DashboardContainerActivity.EXTRA_DRAWABLE_ID, items.get(position).getIdDrawable());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category_grid, parent, false);

        return new ViewHolder(view);
    }
}
