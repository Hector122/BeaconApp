package com.app.beacon.sponsor;

import android.os.Parcel;
import android.os.Parcelable;


import com.app.beacon.R;

import java.util.Random;


/**
 * Created by Hector Castillo on 1/24/2016.
 */
public class Sponsor implements Parcelable {
    private String textTitle;
    private String textDateFormat;
    private String textCategory;
    private int idSponsorImage;

    public Sponsor() {

    }

    /**
     * Constructor
     * need to be reader in the order that is write LIFO structure.
     *
     * @param source
     */
    public Sponsor(Parcel source) {
        this.textTitle = source.readString();
        this.textDateFormat = source.readString();
        this.textCategory = source.readString();
        this.idSponsorImage = source.readInt();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(textTitle);
        dest.writeString(textDateFormat);
        dest.writeString(textCategory);
        dest.writeInt(idSponsorImage);
    }

    public static final Parcelable.Creator<Sponsor> CREATOR = new Parcelable
            .Creator<Sponsor>() {

        @Override
        public Sponsor createFromParcel(Parcel source) {
            return new Sponsor(source);
        }

        @Override
        public Sponsor[] newArray(int size) {
            return new Sponsor[size];
        }
    };


    //Getters and Setters
    public String getTextTitle() {
        return textTitle;
    }

    public void setTextTitle(String textTitle) {
        this.textTitle = textTitle;
    }

    public String getTextDateFormat() {
        return textDateFormat;
    }

    public void setTextDateFormat(String textDateFormat) {
        this.textDateFormat = textDateFormat;
    }

    public String getTextCategory() {
        return textCategory;
    }

    public void setTextCategory(String textCategory) {
        this.textCategory = textCategory;
    }

    public int getIdSponsorImage() {
        return idSponsorImage;
    }

    public void setIdSponsorImage(int idSponsorImage) {
        this.idSponsorImage = idSponsorImage;
    }


    /**
     * Asigna un drawable en forma aleatoria
     *
     * @return id del drawable
     */
    public static int getRandomSponsorDrawable() {
        Random rnd = new Random();
        switch (rnd.nextInt(9)) {
            default:
            case 0:
                return R.drawable.category_food_315x215;
            case 1:
                return R.drawable.category_health_and_fitness_325x189;
            case 2:
                return R.drawable.category_sport;
            case 3:
                return R.drawable.category_shopping_470x220;
            case 4:
                return R.drawable.category_supermarket_310x221;
            case 5:
                return R.drawable.category_home_and_garden_260x215;
            case 6:
                return R.drawable.category_men_lifestyle_230x240;
            case 7:
                return R.drawable.category_thecnology;
            case 8:
                return R.drawable.category_sports_entertainment;
        }
    }

    public static String getRandomSponsorTitle() {

        String[] titles = {"Why Supergirl & The Flash Should Have a Musical Crossover",
                "Pro-rape 'pick-up artist' cancels global meetups for safety of his men",
                "Etiquette Monster: How Rude Am I Allowed to Be on an Airplane?",
                "Apple will soon let customers trade in busted iPhones toward a new one",
                "If you have more title =, please say to me right now"};

        // Resources res = getResources();
        // String[] titles = res.getStringArray(R.array.Titles_sponsor_examples);

        Random random = new Random();

        // Restricción de tamaño
        //count = Math.min(5, titles.length);

        return titles[random.nextInt(titles.length)];
    }


    public static String getRandomSponsorCategory() {

        String[] titles = {"Entertainment",
                "Food & Drink",
                "Supermarket",
                "Women's Lifestyle",
                "Sport",
                "Home & Garden"};

        // Resources res = getResources();
        // String[] titles = res.getStringArray(R.array.Titles_sponsor_examples);

        Random random = new Random();

        // Restricción de tamaño
        //count = Math.min(5, titles.length);

        return titles[random.nextInt(titles.length)];
    }


    public static String getRandomSponsorDate() {

        String[] titles = {"1 hour ago",
                "4 hour ago",
                "Yerterday",
                "1, Febrero",
                "28,Enero"};

        // Resources res = getResources();
        // String[] titles = res.getStringArray(R.array.Titles_sponsor_examples);

        Random random = new Random();

        // Restricción de tamaño
        //count = Math.min(5, titles.length);

        return titles[random.nextInt(titles.length)];
    }


}
