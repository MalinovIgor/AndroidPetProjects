package com.example.pharmacies_analysis.ui.search;

import androidx.appcompat.app.ActionBar;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.pharmacies_analysis.MainActivity;
import com.example.pharmacies_analysis.R;
import com.google.android.material.appbar.AppBarLayout;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class SearchFragment extends Fragment {

    private SearchViewModel mViewModel;
    private ActionBar mActionBar;
    private static final int STANDARD_APPBAR = 0;
    private static final int SEARCH_APPBAR = 1;
    private int mAppBarState;

    EditText mSearchDrugs;
    private AppBarLayout viewContactsBar, searchBar;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.search_fragment, container, false);
        mActionBar = ((MainActivity) getActivity()).getSupportActionBar();
        mActionBar.hide();
        viewContactsBar = v.findViewById(R.id.viewContactsToolbar);
        searchBar = v.findViewById(R.id.searchToolbar);
        mSearchDrugs = v.findViewById(R.id.etSearchDrugs);
        setAppBarState(STANDARD_APPBAR);
        ImageView ivSearchContact = v.findViewById(R.id.ivSearchIcon);
        ivSearchContact.setOnClickListener(v1 -> toggleToolBarState());

        ImageView ivBackArrow = v.findViewById(R.id.ivBackArrow);
        ImageView ivBackHomeArrow = v.findViewById(R.id.ivBackHomeArrow);
        ivBackArrow.setOnClickListener(v12 -> toggleToolBarState());
        ivBackHomeArrow.setOnClickListener(v13 -> getActivity().getSupportFragmentManager().popBackStack());
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onStop() {
        super.onStop();

        mActionBar.show();
    }

    private void toggleToolBarState() {
        if (mAppBarState == STANDARD_APPBAR) {
            setAppBarState(SEARCH_APPBAR);
            mSearchDrugs.requestFocus();
        } else {
            setAppBarState(STANDARD_APPBAR);
        }
    }

    private void setAppBarState(int state) {
        mAppBarState = state;
        if (mAppBarState == STANDARD_APPBAR) {
            searchBar.setVisibility(View.GONE);
            viewContactsBar.setVisibility(View.VISIBLE);

            View view = getView();
            InputMethodManager im = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            try {
                im.hideSoftInputFromWindow(view.getWindowToken(), 0); // make keyboard hide
            } catch (NullPointerException e) {
                Log.d(TAG, "setAppBaeState: NullPointerException: " + e);
            }
        } else if (mAppBarState == SEARCH_APPBAR) {
            viewContactsBar.setVisibility(View.GONE);
            searchBar.setVisibility(View.VISIBLE);
            InputMethodManager im = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            im.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0); // make keyboard popup

        }
    }
    @Override
    public void onResume() {
        super.onResume();
        setAppBarState(STANDARD_APPBAR);
        mActionBar.hide();
    }

}
