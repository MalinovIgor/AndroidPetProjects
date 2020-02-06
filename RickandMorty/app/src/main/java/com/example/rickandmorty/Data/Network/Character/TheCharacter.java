package com.example.rickandmorty.Data.Network.Character;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TheCharacter implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("species")
    @Expose
    private String species;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("origin")
    @Expose
    private CharacterOrigin origin;
    @SerializedName("location")
    @Expose
    private CharacterLocation location;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("episode")
    @Expose
    private List<String> episode = new ArrayList<String>();
    public final static Parcelable.Creator<TheCharacter> CREATOR = new Creator<TheCharacter>() {


        @SuppressWarnings({
                "unchecked"
        })
        public TheCharacter createFromParcel(Parcel in) {
            return new TheCharacter(in);
        }

        public TheCharacter[] newArray(int size) {
            return (new TheCharacter[size]);
        }

    }
            ;

    protected TheCharacter(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.species = ((String) in.readValue((String.class.getClassLoader())));
        this.gender = ((String) in.readValue((String.class.getClassLoader())));
        this.origin = ((CharacterOrigin) in.readValue((CharacterOrigin.class.getClassLoader())));
        this.location = ((CharacterLocation) in.readValue((CharacterLocation.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.episode, (java.lang.String.class.getClassLoader()));
    }

    public TheCharacter() {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public CharacterOrigin getOrigin() {
        return origin;
    }

    public void setOrigin(CharacterOrigin origin) {
        this.origin = origin;
    }

    public CharacterLocation getLocation() {
        return location;
    }

    public void setLocation(CharacterLocation location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getEpisode() {
        return episode;
    }

    public void setEpisode(List<String> episode) {
        this.episode = episode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(status);
        dest.writeValue(species);
        dest.writeValue(gender);
        dest.writeValue(origin);
        dest.writeValue(location);
        dest.writeValue(image);
        dest.writeList(episode);
    }

    public int describeContents() {
        return 0;
    }

    public static DiffUtil.ItemCallback<TheCharacter> DIFF_CALLBACK = new DiffUtil.ItemCallback<TheCharacter>() {
        @Override
        public boolean areItemsTheSame(@NonNull TheCharacter theCharacter, @NonNull TheCharacter t1) {
            return theCharacter.id.equals(t1.id);
        }

        @Override
        public boolean areContentsTheSame(@NonNull TheCharacter theCharacter, @NonNull TheCharacter t1) {
            return theCharacter.equals(t1);
        }
    };


    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        TheCharacter theCharacter = (TheCharacter) obj;
        return theCharacter.id.equals(this.id);
    }

}