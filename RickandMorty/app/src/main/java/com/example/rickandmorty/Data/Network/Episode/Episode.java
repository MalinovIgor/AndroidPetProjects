package com.example.rickandmorty.Data.Network.Episode;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Episode implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("air_date")
    @Expose
    private String airDate;
    @SerializedName("episode")
    @Expose
    private String episode;
    @SerializedName("characters")
    @Expose
    private List<String> characters = new ArrayList<String>();
    public final static Parcelable.Creator<Episode> CREATOR = new Creator<Episode>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Episode createFromParcel(Parcel in) {
            return new Episode(in);
        }

        public Episode[] newArray(int size) {
            return (new Episode[size]);
        }

    };

    protected Episode(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.airDate = ((String) in.readValue((String.class.getClassLoader())));
        this.episode = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.characters, (java.lang.String.class.getClassLoader()));
    }

    public Episode() {
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

    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public List<String> getCharacters() {
        return characters;
    }

    public void setCharacters(List<String> characters) {
        this.characters = characters;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(airDate);
        dest.writeValue(episode);
        dest.writeList(characters);
    }

    public int describeContents() {
        return 0;
    }

    public static DiffUtil.ItemCallback<Episode> DIFF_CALLBACK = new DiffUtil.ItemCallback<Episode>() {
        @Override
        public boolean areItemsTheSame(@NonNull Episode theCharacter, @NonNull Episode t1) {
            return theCharacter.id.equals(t1.id);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Episode theCharacter, @NonNull Episode t1) {
            return theCharacter.equals(t1);
        }
    };


    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        Episode theCharacter = (Episode) obj;
        return theCharacter.id.equals(this.id);
    }
    public String getCharactersIds() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (String theCharUrl :
                characters) {
            sb.append(theCharUrl.split("/")[5]);
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }
}
