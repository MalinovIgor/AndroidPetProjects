package com.example.rickandmorty.Data.Network.Location;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Location implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("dimension")
    @Expose
    private String dimension;
    @SerializedName("residents")
    @Expose
    private List<String> residents = new ArrayList<String>();
    public final static Parcelable.Creator<Location> CREATOR = new Creator<Location>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        public Location[] newArray(int size) {
            return (new Location[size]);
        }

    };

    protected Location(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        this.dimension = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.residents, (java.lang.String.class.getClassLoader()));
    }

    public Location() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public List<String> getResidents() {
        return residents;
    }

    public void setResidents(List<String> residents) {
        this.residents = residents;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(type);
        dest.writeValue(dimension);
        dest.writeList(residents);
    }

    public int describeContents() {
        return 0;
    }

    public static DiffUtil.ItemCallback<Location> DIFF_CALLBACK = new DiffUtil.ItemCallback<Location>() {
        @Override
        public boolean areItemsTheSame(@NonNull Location theCharacter, @NonNull Location t1) {
            return theCharacter.id.equals(t1.id);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Location theCharacter, @NonNull Location t1) {
            return theCharacter.equals(t1);
        }
    };


    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        Location theCharacter = (Location) obj;
        return theCharacter.id.equals(this.id);
    }

    public String getResidentsIds() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (String theCharUrl :
                residents) {
            sb.append(theCharUrl.split("/")[5]);
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }

}
