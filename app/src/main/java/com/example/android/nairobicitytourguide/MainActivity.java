package com.example.android.nairobicitytourguide;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.navigationDrawer);

        // Set the toolbar as the action bar to facilitate for adding the Navigation Drawer button
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Add the Navigation Drawer button
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle(R.string.title_action_bar_accommodation);
        if (actionbar!= null){
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);

        // Create an adapter that knows which fragment should be shown on each page
        CustomFragmentPagerAdapter adapter = new CustomFragmentPagerAdapter(MainActivity.this, getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        //Attach the tab layout to the view pager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        // Handle click events on the Navigation View using a listener
        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // close drawer when item is tapped
                        drawerLayout.closeDrawers();

                        // Handle navigation view item clicks here.
                        int id = menuItem.getItemId();
                        if(id == R.id.navigationViewMenuItemRecreation){
                            // Sart the activity with recreational activities
                            Intent intent =new Intent(MainActivity.this, RecreationActivity.class);
                            startActivity(intent);

                        }else{
                            // Sart the activity with recreational activities
                            Intent intent =new Intent(MainActivity.this, HistoryActivity.class);
                            startActivity(intent);
                        }
                        return true;
                    }
                });
    }

    // Open the drawer when the Navigation Drawer button is tapped
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
