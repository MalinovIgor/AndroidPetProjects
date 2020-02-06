package com.example.rickandmorty.Data.Network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseResult<T> {
    @SerializedName("results")
    private List<T> results;

    public List<T> getResults() {
        return results;
    }
}
