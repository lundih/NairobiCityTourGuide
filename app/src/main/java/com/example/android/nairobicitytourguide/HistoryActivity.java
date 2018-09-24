package com.example.android.nairobicitytourguide;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class HistoryActivity extends AppCompatActivity{

    static final int HISTORY = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // Set the toolbar as the action bar to facilitate for adding the Navigation Drawer button
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle(R.string.title_action_bar_history);
        if (actionbar!= null){
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        }

        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);

        // Create an adapter that knows which fragment should be shown on each page
        CustomFragmentPagerAdapter adapter = new CustomFragmentPagerAdapter(HistoryActivity.this, getSupportFragmentManager(), HISTORY);

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        // Increase off screen page limit to retain fragments
        // hence keeping the list items from being duplicated when one swipes away from the fragment
        viewPager.setOffscreenPageLimit (3);

        //Attach the tab layout to the view pager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
