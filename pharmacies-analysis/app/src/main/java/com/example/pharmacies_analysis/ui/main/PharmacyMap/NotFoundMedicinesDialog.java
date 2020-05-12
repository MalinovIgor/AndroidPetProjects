package com.example.pharmacies_analysis.ui.main.PharmacyMap;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pharmacies_analysis.R;
import com.example.pharmacies_analysis.databinding.DialogRecyclerBinding;

import java.util.ArrayList;

public class NotFoundMedicinesDialog extends DialogFragment {
    DialogRecyclerBinding dialogRecyclerBinding;

    public static NotFoundMedicinesDialog newInstance(ArrayList<String> notFoundMedicine) {

        Bundle args = new Bundle();
        args.putStringArrayList("LIST", notFoundMedicine);
        NotFoundMedicinesDialog fragment = new NotFoundMedicinesDialog();
        fragment.setArguments(args);
        return fragment;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final RecyclerView recyclerView = (RecyclerView) getActivity().getLayoutInflater().inflate(R.layout.dialog_recycler, null);
//        LayoutInflater inflater = getActivity().getLayoutInflater();
//        View dialogView = inflater.inflate(R.layout.dialog_recycler, null);

//        RecyclerView recyclerView = dialogView.findViewById(R.id.recycler);
        NotFoundMedicinesAdapter notFoundMedicinesAdapter = new NotFoundMedicinesAdapter();
        notFoundMedicinesAdapter.setMedicinesList(getArguments().getStringArrayList("LIST"));
        recyclerView.setAdapter(notFoundMedicinesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        builder.setView(inflater.inflate(R.layout.dialog_recycler, null))
//                .setPositiveButton(R.string.ok, (dialog, which) -> dismiss());
        // Create the AlertDialog object and return it
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.title)
                .setView(recyclerView)
                .setPositiveButton(R.string.ok, (dialog, which) -> dismiss())
                .create();
    }
}
