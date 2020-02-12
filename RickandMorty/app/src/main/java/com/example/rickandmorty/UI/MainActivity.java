package com.example.rickandmorty.UI;

import android.os.Bundle;

import com.example.rickandmorty.R;
import com.example.rickandmorty.UI.Characters.CharactersFilterBottomSheet;
import com.example.rickandmorty.UI.Characters.CharactersListFragment;
import com.example.rickandmorty.UI.Episodes.EpisodesFilterBottomSheet;
import com.example.rickandmorty.UI.Episodes.EpisodesListFragment;
import com.example.rickandmorty.UI.Locations.LocationsFilterBottomSheet;
import com.example.rickandmorty.UI.Locations.LocationsListFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


//        FragmentManager fm = getSupportFragmentManager();
//        fm.beginTransaction().add(R.id.fragment, CharactersListFragment.getInstance()).commit();

        setupTabLayout();
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            switch (mTabLayout.getSelectedTabPosition()) {
                case 0:
                    CharactersFilterBottomSheet bottomSheetFragment = new CharactersFilterBottomSheet();
                    bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
                    break;
                case 1:
                    LocationsFilterBottomSheet bottomSheetFragment1 = new LocationsFilterBottomSheet();
                    bottomSheetFragment1.show(getSupportFragmentManager(), bottomSheetFragment1.getTag());
                    break;
                case 2:
                    EpisodesFilterBottomSheet bottomSheetFragment2 = new EpisodesFilterBottomSheet();
                    bottomSheetFragment2.show(getSupportFragmentManager(), bottomSheetFragment2.getTag());
                    break;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupTabLayout() {
        mTabLayout = findViewById(R.id.tab_layout);
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.tab_label1));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.tab_label2));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.tab_label3));
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager viewPager = findViewById(R.id.pager);
        final PageAdapter adapter = new PageAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new
                TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
}
