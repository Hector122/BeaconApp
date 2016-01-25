package com.example.hectorcastillo.beacon.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hectorcastillo.beacon.R;
import com.example.hectorcastillo.beacon.sponsor.SponsorCategory;

/**
 * Created by Hector_2 on 1/24/2016.
 */
public class CategoryAdapter extends BaseAdapter {
    private Context context;

    public CategoryAdapter(Context context) {
        this.context = context;
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public SponsorCategory getItem(int position) {
        return SponsorCategory.ITEMS[position];
    }


    @Override
    public int getCount() {
        return SponsorCategory.ITEMS.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.
                    getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.category_grid_item, parent, false);
        }

        final ImageView categoryImage = (ImageView) convertView.findViewById(R.id.category_image);
        final TextView categoryName = (TextView) convertView.findViewById(R.id.category_name);

        final SponsorCategory item = getItem(position);
        categoryName.setText(item.getName());
        Glide.with(categoryImage.getContext())
                .load(item.getIdDrawable())
                .into(categoryImage);

        return convertView;
    }

}
