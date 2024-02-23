package com.example.rickandmorty.Data.Network.Character;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.rickandmorty.Data.Network.ApiClient;
import com.example.rickandmorty.Data.Network.Location.Location;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class CharacterLocationAndOrigin {
    private MutableLiveData<HashMap<String, Location>> locations = new MutableLiveData<>();

    public CharacterLocationAndOrigin(TheCharacter character) {
        loadLocations(character);
    }

    private void loadLocations(TheCharacter character) {
        HashMap locationAndOriginIds = character.getLocationAndOriginIds();
        StringBuilder query = new StringBuilder();
        query.append("[");
        if (locationAndOriginIds.get("origin").equals(locationAndOriginIds.get("location"))) {
            query.append(locationAndOriginIds.get("origin"));
        } else {
            if (!locationAndOriginIds.get("origin").equals("unknown")) {
                query.append(locationAndOriginIds.get("origin"));
                query.append(",");
            }
            if (!locationAndOriginIds.get("location").equals("unknown")) {
                query.append(locationAndOriginIds.get("location"));
            }
        }
        query.append("]");
        if (query.length() == 2) {
            locations.setValue(new HashMap<String, Location>() {{
                put("origin", null);
                put("location", null);
            }});
            return;
        }
        ApiClient.getInstance()
                .getApi()
                .getLocationsById(query.toString())
                .enqueue(new Callback<List<Location>>() {
                    @Override
                    @EverythingIsNonNull
                    public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {
                        if (response.body() != null) {
                            HashMap<String, Location> result = new HashMap<>();
                            if (locationAndOriginIds.get("origin").equals(locationAndOriginIds.get("location"))) {
                                result.put("origin", response.body().get(0));
                                result.put("location", response.body().get(0));
                            } else {
                                if (!locationAndOriginIds.get("origin").equals("unknown")) {
                                    result.put("origin", response.body().get(0));
                                } else {
                                    result.put("origin", null);
                                }
                                if (!locationAndOriginIds.get("location").equals("unknown")) {
                                    if (!locationAndOriginIds.get("origin").equals("unknown")) {
                                        result.put("location", response.body().get(1));
                                    } else {
                                        result.put("location", response.body().get(0));
                                    }
                                } else {
                                    result.put("location", null);
                                }
                            }
                            locations.setValue(result);
                        }
                    }

                    @Override
                    @EverythingIsNonNull
                    public void onFailure(Call<List<Location>> call, Throwable t) {
                        Log.e("LOAD RESIDENTS", "onFailure: ", t);
                    }
                });
    }

    public LiveData<HashMap<String, Location>> getLocations() {
        return locations;
    }
}
