package com.example.pharmacies_analysis.ui.main.PharmacyMap;

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

public class MapSettingsBottomSheet extends BottomSheetDialogFragment implements SeekBar.OnSeekBarChangeListener {
    MapSettingBottomSheetBinding mapSettingBottomSheetBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mapSettingBottomSheetBinding = DataBindingUtil.inflate(inflater,
                R.layout.map_setting_bottom_sheet, container, false);
        ViewModelFactory viewModelFactory = new ViewModelFactory(getContext());
        PharmacyMapViewModel mViewModel = new ViewModelProvider(this, viewModelFactory).get(PharmacyMapViewModel.class);
        mViewModel.getRadius().observe(getViewLifecycleOwner(), radius -> {
            mapSettingBottomSheetBinding.radiusValue.setText(convertIntToRadiusValue(radius));
            mapSettingBottomSheetBinding.radius.setProgress(radius);
        });
        mapSettingBottomSheetBinding.radius.setOnSeekBarChangeListener(this);
        mapSettingBottomSheetBinding.search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.saveRadius(mapSettingBottomSheetBinding.radius.getProgress()/500*500);
                dismiss();
            }
        });
        return mapSettingBottomSheetBinding.getRoot();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        mapSettingBottomSheetBinding.radiusValue.setText(convertIntToRadiusValue(progress));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mapSettingBottomSheetBinding.radiusValue.setText(convertIntToRadiusValue(seekBar.getProgress()));
    }

    private double roundToHalf(double d) {
        return Math.round(d * 2) / 2.0;
    }
    private String convertIntToRadiusValue(Integer radius){
        return String.valueOf(roundToHalf((double)radius/1000));
    }
}
