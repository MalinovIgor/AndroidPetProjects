package com.example.pharmacies_analysis.ui.search;

import androidx.appcompat.app.ActionBar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.pharmacies_analysis.MainActivity;
import com.example.pharmacies_analysis.R;
import com.example.pharmacies_analysis.data.db.Medicine;
import com.example.pharmacies_analysis.databinding.SearchFragmentBinding;
import com.example.pharmacies_analysis.ui.ViewModelFactory;
import com.google.android.material.appbar.AppBarLayout;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class SearchFragment extends Fragment {

    private SearchViewModel mViewModel;
    private ActionBar mActionBar;
    private static final int STANDARD_APPBAR = 0;
    private static final int SEARCH_APPBAR = 1;
    private int mAppBarState;
    private SearchFragmentBinding mSearchFragmentBinding;

    EditText mSearchMedicine;
    private AppBarLayout viewFormsBar, searchBar;
    SearchResultAdapter mAdapter;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mSearchFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.search_fragment, container, false);
        View v = mSearchFragmentBinding.getRoot();
        mActionBar = ((MainActivity) getActivity()).getSupportActionBar();
        mActionBar.hide();
        viewFormsBar = v.findViewById(R.id.viewFormsToolbar);
        searchBar = v.findViewById(R.id.searchToolbar);
        mSearchMedicine = v.findViewById(R.id.etSearchMedicine);

        setAppBarState(SEARCH_APPBAR);
        ImageView ivSearchForms = v.findViewById(R.id.ivSearchIcon);
        ivSearchForms.setOnClickListener(v1 -> toggleToolBarState());

        ImageView ivSearch = v.findViewById(R.id.ivSearch);
        ivSearch.setOnClickListener(v14 -> search(mSearchMedicine.getText().toString()));

        ImageView ivBackArrow = v.findViewById(R.id.ivBackArrow);
        ImageView ivBackHomeArrow = v.findViewById(R.id.ivBackHomeArrow);
        ivBackArrow.setOnClickListener(v12 -> getActivity().getSupportFragmentManager().popBackStack());
        ivBackHomeArrow.setOnClickListener(v13 -> getActivity().getSupportFragmentManager().popBackStack());

        RecyclerView recyclerView = mSearchFragmentBinding.searchResult;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        ViewModelFactory viewModelFactory = new ViewModelFactory(getContext());
        mViewModel = new ViewModelProvider(this, viewModelFactory).get(SearchViewModel.class);
        mAdapter = new SearchResultAdapter((holder, medicine, type) -> {
            if (type == SearchResultAdapter.ADD){
                holder.searchResultListItemBinding.addedItem.setVisibility(View.VISIBLE);
                holder.searchResultListItemBinding.addItem.setVisibility(View.GONE);
                mViewModel.insert(medicine);
            } else if (type == SearchResultAdapter.DELETE){
                holder.searchResultListItemBinding.addedItem.setVisibility(View.GONE);
                holder.searchResultListItemBinding.addItem.setVisibility(View.VISIBLE);
                mViewModel.delete(medicine);
            }
        });
        recyclerView.setAdapter(mAdapter);

        mSearchMedicine.setOnKeyListener((v15, keyCode, event) -> {
            if (event.getAction()!=KeyEvent.ACTION_DOWN)
                return false;
            if(keyCode == KeyEvent.KEYCODE_ENTER ){
                search(mSearchMedicine.getText().toString());
                return true;
            }
            return false;
        });
        return v;
    }

    private void search(String query) {
        mViewModel.getForms(query)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(this::showLoading)
                .doOnTerminate(this::hideLoading)
                .subscribe(this::handleResult, this::handleError);
    }

    private void showLoading(Disposable disposable) {
        mSearchFragmentBinding.loading.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        mSearchFragmentBinding.loading.setVisibility(View.GONE);
    }


    private void handleError(Throwable throwable) {
        mSearchFragmentBinding.error.setVisibility(View.VISIBLE);
    }

    private void handleResult(List<Medicine> medicines) {
        if (medicines.size() == 0){
            mSearchFragmentBinding.nothingToShow.setVisibility(View.VISIBLE);
        }
        else {
            mSearchFragmentBinding.nothingToShow.setVisibility(View.GONE);
            mSearchFragmentBinding.error.setVisibility(View.GONE);
            mAdapter.setMedicinesList(medicines);
            mAdapter.notifyDataSetChanged();
        }
        toggleToolBarState();
    }

    @Override
    public void onStop() {
        super.onStop();

        mActionBar.show();
    }

    private void toggleToolBarState() {
        if (mAppBarState == STANDARD_APPBAR) {
            setAppBarState(SEARCH_APPBAR);
        } else {
            setAppBarState(STANDARD_APPBAR);
        }
    }

    private void setAppBarState(int state) {
        mAppBarState = state;
        if (mAppBarState == STANDARD_APPBAR) {
            searchBar.setVisibility(View.GONE);
            viewFormsBar.setVisibility(View.VISIBLE);

            View view = getView();
            InputMethodManager im = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            try {
                im.hideSoftInputFromWindow(view.getWindowToken(), 0); // make keyboard hide
            } catch (NullPointerException e) {
                Log.d(TAG, "setAppBaeState: NullPointerException: " + e);
            }
        } else if (mAppBarState == SEARCH_APPBAR) {
            viewFormsBar.setVisibility(View.GONE);
            searchBar.setVisibility(View.VISIBLE);
            InputMethodManager im = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            im.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0); // make keyboard popup
            mSearchMedicine.requestFocus();
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        //setAppBarState(SEARCH_APPBAR);
        mActionBar.hide();
    }

}
