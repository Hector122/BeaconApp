package com.app.beacon.sponsor;

import com.app.beacon.R;

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
            new SponsorCategory("All", R.drawable.category_all),
            new SponsorCategory("Entertainment", R.drawable.category_sports_entertainment),
            new SponsorCategory("Food & Drink", R.drawable.category_food_315x215),
            new SponsorCategory("Supermarket", R.drawable.category_supermarket_310x221),
            new SponsorCategory("Health & fitness", R.drawable.category_health_and_fitness_325x189),
            new SponsorCategory("Home & garden", R.drawable.category_home_and_garden_260x215),
            new SponsorCategory("Men's Lifestyle", R.drawable.category_men_lifestyle_230x240),
            new SponsorCategory("Women's Lifestyle", R.drawable.category_shopping_470x220),
            new SponsorCategory("TECHNOLOGY", R.drawable.category_thecnology),
            new SponsorCategory("Sport", R.drawable.category_sport)
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
