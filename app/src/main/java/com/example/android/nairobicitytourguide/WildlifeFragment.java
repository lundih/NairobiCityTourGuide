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


public class WildlifeFragment extends Fragment {

    final ArrayList<Place> places = new ArrayList<>();

    public WildlifeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_wildlife, container, false);

        places.add(new Place(getContext(),getString(R.string.wildlife_name_place1),
                new Location(1.3616,36.8452),
                getString(R.string.wildife_description_place1),
                R.drawable.nairobi_national_park));
        places.add(new Place(getContext(),getString(R.string.wildife_name_place2),
                new Location(1.3338,36.7503),
                getString(R.string.wildlife_descriptoion_place2),
                R.drawable.mamba_village));
        places.add(new Place(getContext(),getString(R.string.wildlife_name_place3),
                new Location(1.2745, 36.8148),
                getString(R.string.wildlife_descriptoion_place3),
                R.drawable.snake_park));
        places.add(new Place(getContext(),getString(R.string.wildlife_name_place4),
                new Location(1.3764,36.7443),
                getString(R.string.wildlife_descriptoion_place4),
                R.drawable.giraffe_center));
        places.add(new Place(getContext(),getString(R.string.wildlife_name_place5),
                new Location(1.3364, 36.7776),
                getString(R.string.wildlife_descriptoion_place5),
                R.drawable.safari_walk));

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
