package com.example.rickandmorty.UI.Characters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.rickandmorty.R;
import com.example.rickandmorty.ViewModel.Character.ListCharactersViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Hashtable;

public class CharactersFilterBottomSheet extends BottomSheetDialogFragment implements View.OnClickListener {
    private EditText name;
    private EditText species;
    private Spinner gender;
    private Spinner status;
    private Button search;

    public CharactersFilterBottomSheet() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.character_bottom_sheet, container, false);
        findViews(v);
        setupSpinners();
        search.setOnClickListener(this);
        return v;
    }

    private void setupSpinners(){
        ArrayAdapter<CharSequence> genderSpinnerAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.character_gender_array, android.R.layout.simple_spinner_item);
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(genderSpinnerAdapter);

        ArrayAdapter<CharSequence> statusSpinnerAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.character_status_array, android.R.layout.simple_spinner_item);
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status.setAdapter(statusSpinnerAdapter);
    }

    private void findViews(View v){
        name = v.findViewById(R.id.name);
        species= v.findViewById(R.id.species);
        gender = v.findViewById(R.id.sp_gender);
        status = v.findViewById(R.id.sp_status);
        search = v.findViewById(R.id.search);
    }

    @Override
    public void onClick(View v) {
        ListCharactersViewModel model = new ViewModelProvider(requireActivity()).get(ListCharactersViewModel.class);
        String name = this.name.getText().toString();
        String species = this.species.getText().toString();
        String gender = this.gender.getSelectedItem().toString().equals("Unselected")?"":this.gender.getSelectedItem().toString();
        String status = this.status.getSelectedItem().toString().equals("Unselected")?"":this.status.getSelectedItem().toString();
        model.setQuery(new Hashtable<String, String>(){{
            put("name", name);
            put("species", species);
            put("gender", gender);
            put("status", status);
        }});
        dismiss();
    }
}
