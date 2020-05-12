package com.example.pharmacies_analysis.data.sharedPreference;

import android.content.SharedPreferences;

public class SharedPreferenceIntegerLiveData extends SharedPreferenceLiveData<Integer> {
    public SharedPreferenceIntegerLiveData(SharedPreferences sharedPrefs, String key, Integer defaultValue) {
        super(sharedPrefs, key, defaultValue);
    }

    @Override
    Integer getValueFromPreferences(String key, Integer defValue) {
        return sharedPrefs.getInt(key, defValue);
    }
}
