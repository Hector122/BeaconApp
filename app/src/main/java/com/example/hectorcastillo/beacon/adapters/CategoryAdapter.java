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
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.
                    getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.category_grid_item, parent, false);

            // initialize the view holder
            holder = new ViewHolder();

            //get the reference view
            holder.image = (ImageView) convertView.findViewById(R.id.category_image);
            holder.name = (TextView) convertView.findViewById(R.id.category_name);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //get the item to
        final SponsorCategory item = getItem(position);

        holder.name.setText(item.getName());
        Glide.with(holder.image.getContext())
                .load(item.getIdDrawable())
                .crossFade(300)
                .into(holder.image);


        return convertView;
    }

    public class ViewHolder {
        TextView name;
        ImageView image;


    }

}
