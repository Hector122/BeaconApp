package com.example.hectorcastillo.beacon.sponsor;

import com.example.hectorcastillo.beacon.R;

/**
 * Created by Hector_2 on 1/24/2016.
 */
public class SponsorCategory {
    //Represent the name and the image Resource
    private String name;
    private int idDrawable;

    public SponsorCategory(String name, int idDrawable) {
        this.name = name;
        this.idDrawable = idDrawable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdDrawable() {
        return idDrawable;
    }

    public void setIdDrawable(int idDrawable) {
        this.idDrawable = idDrawable;
    }

    public int getId() {
        return name.hashCode();
    }

    //TODO: by know are fixed.
    public static SponsorCategory[] ITEMS = {
            new SponsorCategory("ALL", R.drawable.category_cellphone_625x300),
            new SponsorCategory("Entertainment", R.drawable.category_cellphone_625x300),
            new SponsorCategory("Food & Drink", R.drawable.category_men_lifestyle_625x300),
            new SponsorCategory("Health & fitness", R.drawable.category_supermarket_625x300),
            new SponsorCategory("Home & garden", R.drawable.category_men_lifestyle_625x300),
            new SponsorCategory("Men's Lifestyle", R.drawable.category_men_lifestyle_625x300),
            new SponsorCategory("NEWS & POLITICS", R.drawable.category_cellphone_625x300),
            new SponsorCategory("SCIENCE & TECHNOLOGY", R.drawable.category_supermarket_625x300),
            new SponsorCategory("Especial Interes", R.drawable.category_men_lifestyle_625x300),
            new SponsorCategory("Sport", R.drawable.category_supermarket_625x300)
    };


    public static SponsorCategory getItem(int id) {
        for (SponsorCategory item : ITEMS) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
}
