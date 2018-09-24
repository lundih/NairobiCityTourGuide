package com.example.android.nairobicitytourguide;

class Location {
    private double mLatitude;
    private double mLongitude;

    public double getLatitude() {return mLatitude;}

    public double getLongitude() {return mLongitude;}

    public Location(double latitude, double longitude){
        mLatitude = latitude;
        mLongitude = longitude;
    }
}
