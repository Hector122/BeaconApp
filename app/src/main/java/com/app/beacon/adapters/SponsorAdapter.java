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
import com.app.beacon.activists.SponsorDetailActivity;
import com.app.beacon.sponsor.Sponsor;

import java.util.List;

/**
 * Created by Hector_2 on 1/24/2016.
 */
public class SponsorAdapter extends RecyclerView.Adapter<SponsorAdapter.ViewHolder> {
    //List container the sponsor item.
    private List<Sponsor> sponsorList;

    private boolean value;

    //ViewHolder patters for better performance.
    public static class ViewHolder extends RecyclerView.ViewHolder {
        //item fields
        public View viewHolder;
        public ImageView image;
        public TextView title;
        public TextView timeReceive;
        public TextView category;

        public ViewHolder(View view) {
            super(view);
            viewHolder = view;
            image = (ImageView) view.findViewById(R.id.image_sponsor);
            title = (TextView) view.findViewById(R.id.title_sponsor);
            timeReceive = (TextView) view.findViewById(R.id.time_receive_sponsor);
            category = (TextView) view.findViewById(R.id.category_sponsor);
        }
    }

    /**
     * Constructor.
     *
     * @param sponsorList list of sponsor with the information need.
     */
    public SponsorAdapter(List<Sponsor> sponsorList ) {
        this.sponsorList = sponsorList;
    }


    public SponsorAdapter(List<Sponsor> sponsorList, boolean value ) {
        this.sponsorList = sponsorList;
        this.value = value;
    }

    /**
     * remove all element in the recycler
     */
    public void clear() {
        sponsorList.clear();
        notifyDataSetChanged();
    }


    /**
     * Return the size of the list
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return sponsorList.size();
    }

    /**
     * This method is called when the custom ViewHolder needs to be initialized
     *
     * @param parent
     * @param viewType
     * @return ViewHolder object.
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        if (value) {
            view = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.item_fav_sponsor_list, parent, false);

        } else {
            view = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.item_sponsor, parent, false);
        }

        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    /***
     * Specify the contents of each item of the RecyclerView
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //set the view data source.
        holder.title.setText(sponsorList.get(position).getTextTitle());
        holder.timeReceive.setText(sponsorList.get(position).getTextDateFormat());
        holder.category.setText(sponsorList.get(position).getTextCategory());
        holder.image.setImageResource(sponsorList.get(position).getIdSponsorImage());

        holder.viewHolder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final Context context = v.getContext();
                Intent intent = new Intent(context, SponsorDetailActivity.class);
                intent.putExtra(SponsorDetailActivity.EXTRA_PARAM_ID,
                        sponsorList.get(position));

                context.startActivity(intent);
            }
        });
    }
}
