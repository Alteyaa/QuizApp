package com.quizapp.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.quizapp.R;
import com.quizapp.history.HistoryFragment;
import com.quizapp.history.HistoryViewModel;
import com.quizapp.settings.SettingsFragment;
import com.quizapp.settings.SettingsViewModel;

public class MainActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener {


    private ViewPager mViewPager;
    private MainPagerAdapter mAdapter;
    private BottomNavigationView mBottomNav;

    private MainViewModel mMainViewModel;
    private HistoryViewModel mHistoryViewModel;
    private SettingsViewModel mSettingsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainViewModel = ViewModelProviders.of(this)
                .get(MainViewModel.class);
        mHistoryViewModel = ViewModelProviders.of(this)
                .get(HistoryViewModel.class);
        mSettingsViewModel = ViewModelProviders.of(this)
                .get(SettingsViewModel.class);

        mHistoryViewModel.title.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d("ololo", "Main activirty " + s);
                mSettingsViewModel.onHistoryCleared();
            }
        });

        mSettingsViewModel.title.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d("ololo", "Main activity " + s);
            }
        });

        mViewPager = findViewById(R.id.main_view_pager);
        mBottomNav = findViewById(R.id.main_bottom_nav);

        mAdapter = new MainPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);

        mBottomNav.setOnNavigationItemSelectedListener(this);

        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                int selectedItem = R.id.nav_main;

                switch (position) {
                    case 1:
                        selectedItem = R.id.nav_history;
                        break;
                    case 2:
                        selectedItem = R.id.nav_settings;
                        break;
                }

                mBottomNav.setSelectedItemId(selectedItem);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int page = 0;

        switch (menuItem.getItemId()) {
            case R.id.nav_history:
                page = 1;
                break;
            case R.id.nav_settings:
                page = 2;
                break;
        }

        mViewPager.setCurrentItem(page);
        return true;
    }

    private class MainPagerAdapter extends FragmentPagerAdapter {
        public MainPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        public MainPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            Fragment fragment;

            switch (position) {
                case 0:
                    fragment = new MainFragment();
                    break;

                case 1:
                    fragment = new HistoryFragment();
                    break;

                default:
                    fragment = new SettingsFragment();
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}