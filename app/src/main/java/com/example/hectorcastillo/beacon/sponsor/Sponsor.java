package com.example.hectorcastillo.beacon.sponsor;

import android.widget.ImageView;

/**
 * Created by Hector Castillo on 1/24/2016.
 */
public class Sponsor {
    private String textTitle;
    private String textDateFormat;
    private String textCategory;
    private int idSponsorImage;

    public Sponsor(String textTitle, String textDateFormat,String textCategory ,int idSponsorImage) {
        this.textTitle = textTitle;
        this.textDateFormat = textDateFormat;
        this.textCategory = textCategory;
        this.idSponsorImage = idSponsorImage;
    }

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
}
