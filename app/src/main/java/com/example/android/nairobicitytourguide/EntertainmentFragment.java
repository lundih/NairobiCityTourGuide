package com.example.android.nairobicitytourguide;


import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class EntertainmentFragment extends Fragment {

    final ArrayList<Place> places = new ArrayList<Place>();

    public EntertainmentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_entertainment, container, false);

        places.add(new Place(getContext(),getString(R.string.entertainment_name1),
                new Location(1.2781,36.8150),
                getString(R.string.entertainment_description1),
                R.drawable.kenya_national_theatre));
        places.add(new Place(getContext(),getString(R.string.entertainment_name2),
                new Location(1.3285, 36.8005),
                getString(R.string.entertainment_description2),
                R.drawable.carnivore_grounds));
        places.add(new Place(getContext(),getString(R.string.entertainment_name3),
                new Location(1.3297, 36.8040),
                getString(R.string.entertainment_description3),
                R.drawable.splash));
        places.add(new Place(getContext(),getString(R.string.entertainment_name4),
                new Location(1.2855, 36.8228),
                getString(R.string.entertainment_description),
                R.drawable.imax));

        sortList();

        PlaceAdapter placeAdapter = new PlaceAdapter(getActivity(), places, R.color.recreationCategory);
        ListView listView = (ListView) rootView.findViewById(R.id.listView);

        //allow listView to interact with collapsible toolbar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            listView.setNestedScrollingEnabled(true);
            listView.startNestedScroll(View.OVER_SCROLL_ALWAYS);
        }
        listView.setAdapter(placeAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //obtain coordinates and pass a URI to google maps
                double latitude = places.get(position).getLatitude();
                double longitude = places.get(position).getLongitude();
                //convert latitude to meaningful format
                latitude = convertLatitude(latitude);
                //set up coordinates to be used in mapping location
                String coordinates = (getString(R.string.coordinates_format, latitude, longitude, places.get(position).getName()));

                Uri intentUri = Uri.parse(coordinates);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, intentUri);
                mapIntent.setPackage(getString(R.string.google_maps_package));
                if (mapIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(mapIntent);
                }else {
                    Toast.makeText(getActivity(), R.string.maps_app_missing_message, Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }

    // Function to sort list items
    private void sortList(){
        Collections.sort(places, new Comparator<Place>() {
            @Override
            public int compare(Place o1, Place o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }

    // Change latitude from using North-South convention to "+"-"-" convention to pass to the maps intent URI
    private double convertLatitude(double latitude){
        return  0 - latitude;
    }
}
