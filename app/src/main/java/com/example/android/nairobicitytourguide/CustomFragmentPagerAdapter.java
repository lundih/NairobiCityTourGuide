package com.example.android.nairobicitytourguide;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class CustomFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private int mCategory = ACCOMMODATION;
    private static final int ACCOMMODATION = 1;
    private static final int RECREATION = 2;

    public CustomFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    public CustomFragmentPagerAdapter(Context context, FragmentManager fm, int category) {
        super(fm);
        mContext = context;
        mCategory = category;
    }

    @Override
    public Fragment getItem(int position) {
        if(mCategory == ACCOMMODATION){
            if(position == 0) {
                return new HotelsFragment();
            }else {
                return new RestaurantsFragment();
            }
        }else if(mCategory == RECREATION){
            if(position == 0) {
                return new WildlifeFragment();
            }else if(position == 1){
                return new ParksFragment();
            }else {
                return new EntertainmentFragment();
            }
        }else{
            if(position == 0) {
                return new LandmarksFragment();
            }else if (position == 1){
                return new MuseumsFragment();
            }else {
                return new DidYouKnowFactsFragment();
            }
        }
    }

    @Override
    public int getCount() {
        if(mCategory == ACCOMMODATION){
            return 2;
        }else if(mCategory == RECREATION){
            return 3;
        }else{
            return 3;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //Arrays to hold tab titles
        String[] accommodation = new String [] {mContext.getString(R.string.accommodation_tab1), mContext.getString(R.string.accommodation_tab2)};
        String[] recreation = new String [] {mContext.getString(R.string.recreation_tab1), mContext.getString(R.string.recreation_tab2), mContext.getString(R.string.recreation_tab3)};
        String[] history = new String [] {mContext.getString(R.string.history_tab1), mContext.getString(R.string.history_tab2), mContext.getString(R.string.history_tab3)};

        if(mCategory == ACCOMMODATION){
            return  accommodation[position];
        }else if(mCategory == RECREATION){
            return recreation[position];
        }else{
            return history[position];
        }
    }
}
