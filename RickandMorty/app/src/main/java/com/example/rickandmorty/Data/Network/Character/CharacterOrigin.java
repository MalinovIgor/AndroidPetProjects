package com.example.rickandmorty.Data.Network.Character;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CharacterOrigin implements Parcelable
{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("url")
    @Expose
    private String url;
    public final static Parcelable.Creator<CharacterOrigin> CREATOR = new Creator<CharacterOrigin>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CharacterOrigin createFromParcel(Parcel in) {
            return new CharacterOrigin(in);
        }

        public CharacterOrigin[] newArray(int size) {
            return (new CharacterOrigin[size]);
        }

    }
            ;

    protected CharacterOrigin(Parcel in) {
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.url = ((String) in.readValue((String.class.getClassLoader())));
    }

    public CharacterOrigin() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeValue(url);
    }

    public int describeContents() {
        return 0;
    }

}
