package com.example.android.nairobicitytourguide;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

class PlaceAdapter extends ArrayAdapter<Place>{
    private int mDefaultImage = 0;
    private int mBackgroundColour ;

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.custom_list_item_layout, parent, false);
        }

        Place currentPlace = getItem(position);

        // Have different background colours for different fragments
        LinearLayout  linearLayoutText = (LinearLayout)listItemView.findViewById(R.id.linearLayoutText);
        linearLayoutText.setBackgroundColor(ContextCompat.getColor(getContext(), mBackgroundColour));

        TextView name = (TextView) listItemView.findViewById(R.id.textViewName);
        name.setText(currentPlace.getName());

        TextView location = (TextView) listItemView.findViewById(R.id.textViewLocation);
        location.setText(currentPlace.getLocation());

        TextView description = (TextView) listItemView.findViewById(R.id.textViewDescription);
        description.setText(currentPlace.getDescription());

        ImageView image = (ImageView) listItemView.findViewById(R.id.imageViewPlaceImage);
        if (currentPlace.hasImage()){
            image.setImageResource(currentPlace.getImageResource());
            image.setVisibility(convertView.VISIBLE);
        }else{
            if(mDefaultImage != 0) {
                image.setImageResource(mDefaultImage);
                image.setVisibility(convertView.VISIBLE);
            }else{
                image.setVisibility(convertView.GONE);
            }
        }

        return listItemView;
    }

    // Constructor with argument that allows for different background colours
    public PlaceAdapter(Activity context, ArrayList<Place>place, int backgroundColour){
        super (context, 0, place);
        mBackgroundColour = backgroundColour ;
    }

    // Constructor with argument that allows for a background color and a default image for each fragment
    public PlaceAdapter(Activity context, ArrayList<Place>place, int  backgroundColour, int defaultImage){
        super (context, 0, place);
        mBackgroundColour = backgroundColour;
        mDefaultImage =defaultImage;
    }


}

