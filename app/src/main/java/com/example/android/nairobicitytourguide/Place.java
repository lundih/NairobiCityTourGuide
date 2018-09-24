package com.example.android.nairobicitytourguide;

import android.content.Context;

class Place {
    private String mName;
    private Location mLocation;
    private String mDescription;
    private static final int NO_IMAGE_PROVIDED = -1;
    private int mImageResource = NO_IMAGE_PROVIDED;
    private Context mContext;

    public String getName() { return mName;}

    public String getLocation() {

        double latitude = mLocation.getLatitude();
        double longitude = mLocation.getLongitude();

        return mContext.getString(R.string.coordinates,latitude,longitude) ;
    }

    public double getLatitude(){return mLocation.getLatitude();}

    public double getLongitude(){return mLocation.getLongitude();}

    public String getDescription() {return mDescription;}

    public int getImageResource(){return mImageResource;}

    public boolean hasImage(){return mImageResource != NO_IMAGE_PROVIDED;}

    public Place(Context context, String name, Location location, String description){
        mContext =context;
        mName = name;
        mLocation = location;
        mDescription = description;
    }

    public Place(Context context, String name, Location location, String description, int imageResource){
        mContext =context;
        mName = name;
        mLocation = location;
        mDescription = description;
        mImageResource = imageResource;
    }
}
