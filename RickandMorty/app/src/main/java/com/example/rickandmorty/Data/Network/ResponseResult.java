package com.example.rickandmorty.Data.Network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseResult<T> {
    @SerializedName("info")
    private Info info;
    @SerializedName("results")
    private List<T> results;

    public List<T> getResults() {
        return results;
    }

    public Info getInfo() {
        return info;
    }
}
