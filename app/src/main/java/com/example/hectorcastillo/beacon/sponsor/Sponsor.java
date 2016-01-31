package com.example.hectorcastillo.beacon.sponsor;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

/**
 * Created by Hector Castillo on 1/24/2016.
 */
public class Sponsor implements Parcelable {
    private String textTitle;
    private String textDateFormat;
    private String textCategory;
    private int idSponsorImage;

    public Sponsor (){

    }

    //TODO:used this from sponsored messages
    //private String textSponsor;
//
//    public Sponsor(String textTitle, String textDateFormat, String textCategory,
//                   int idSponsorImage) {
//        this.textTitle = textTitle;
//        this.textDateFormat = textDateFormat;
//        this.textCategory = textCategory;
//        this.idSponsorImage = idSponsorImage;
//    }

    /**
     * Constructor
     * need to be reader in the order that is write LIFO structure.
     * @param source
     */
    public Sponsor(Parcel source){
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
}
