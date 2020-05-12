package com.example.pharmacies_analysis.ui.main.PharmacyMap;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.pharmacies_analysis.R;
import com.example.pharmacies_analysis.databinding.MapSettingBottomSheetBinding;
import com.example.pharmacies_analysis.ui.ViewModelFactory;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class MapSettingsBottomSheet extends BottomSheetDialogFragment implements SeekBar.OnSeekBarChangeListener{
    MapSettingBottomSheetBinding mapSettingBottomSheetBinding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         mapSettingBottomSheetBinding = DataBindingUtil.inflate(inflater,
                R.layout.map_setting_bottom_sheet, container, false);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
         mapSettingBottomSheetBinding.radiusValue.setText(String.valueOf(roundToHalf(Double.parseDouble(sharedPreferences.getString("RADIUS", "1000"))/1000)));
         mapSettingBottomSheetBinding.radius.setProgress(Integer.parseInt(sharedPreferences.getString("RADIUS", "1000")));
         mapSettingBottomSheetBinding.radius.setOnSeekBarChangeListener(this);
//        ViewModelFactory viewModelFactory = new ViewModelFactory(getContext());
//        PharmacyMapViewModel mViewModel = new ViewModelProvider(this, viewModelFactory).get(PharmacyMapViewModel.class);
         mapSettingBottomSheetBinding.search.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 SharedPreferences.Editor editor = sharedPreferences.edit();
                 editor.putString("RADIUS",String.valueOf(mapSettingBottomSheetBinding.radius.getProgress()));
                 editor.apply();
             }
         });
        return mapSettingBottomSheetBinding.getRoot();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        mapSettingBottomSheetBinding.radiusValue.setText(String.valueOf(roundToHalf((double)progress/1000)));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mapSettingBottomSheetBinding.radiusValue.setText(String.valueOf(roundToHalf((double)seekBar.getProgress()/1000)));
    }

    private static double roundToHalf(double d) {
        return Math.round(d * 2) / 2.0;
    }
}
