package com.example.rickandmorty.Data.Network;

import com.google.gson.annotations.SerializedName;

public class Info {
    @SerializedName("count")
    private Integer count;

    public Integer getCount() {
        return count;
    }
}
