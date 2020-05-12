package com.example.pharmacies_analysis.data.sharedPreference;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.pharmacies_analysis.R;

public class SharedPreferenceManager {
    private SharedPreferences mSharedPreferences;
    private final String RADIUS_KEY = "RADIUS_KEY";
    public static final String NAME_PREFERENCE = "SETTINGS";

    public SharedPreferenceManager(SharedPreferences mSharedPreferences) {
        this.mSharedPreferences = mSharedPreferences;
    }

    public int getRadius(){
        return mSharedPreferences.getInt(RADIUS_KEY, 1000);
    }

    public void setRadius(int radius){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(RADIUS_KEY, radius);
        editor.apply();
    }

//    private SharedPreferenceIntegerLiveData sharedPreferenceLiveData;
//
//    public SharedPreferenceIntegerLiveData getSharedPrefs(){
//        return sharedPreferenceLiveData;
//    }
//
//    public void setSharedPreferences(String key, boolean value) {
//
//        SharedPreferences userDetails = context.getSharedPreferences(APP_PREFERENCE,
//                Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = userDetails.edit();
//        editor.putBoolean(key, value);
//        editor.apply();
//        sharedPreferenceLiveData = new SharedPreferenceBooleanLiveData(userDetails,key,value);
//    }
}
