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

public class DidYouKnowFactsFragment extends Fragment {

    final ArrayList<Place> places = new ArrayList<>();

    public DidYouKnowFactsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_did_you_know_facts, container, false);

        places.add(new Place(getContext(),getString(R.string.facts_name1),
                new Location(1.3616,36.8452),
                getString(R.string.facts_description1)));
        places.add(new Place(getContext(),getString(R.string.facts_name2),
                new Location(1.2997, 36.8129),
                getString(R.string.facts_description2)));
        places.add(new Place(getContext(),getString(R.string.facts_name3),
                new Location(1.2921, 36.8219),
                getString(R.string.facts_description3)));
        places.add(new Place(getContext(),getString(R.string.facts_name4),
                new Location(1.2921, 36.8219),
                getString(R.string.facts_description4)));
        places.add(new Place(getContext(),getString(R.string.facts_name5),
                new Location(1.2921, 36.8219),
                getString(R.string.facts_description5)));

        sortList();

        PlaceAdapter placeAdapter = new PlaceAdapter(getActivity(), places, R.color.historyCategory);
        ListView listView= (ListView) rootView.findViewById(R.id.listView);

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
