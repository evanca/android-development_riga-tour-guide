package com.example.android.tourguideapp;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class InsidersFragment extends Fragment {

    public LocationService gps;
    public double lat;
    public double lon;

    public InsidersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
        getLocation();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.list_landmark, container, false);

        getLocation();

        //TODO: Add boolean logic to show either food or sight icon

        final ArrayList<Landmark> landmarks = new ArrayList<>();
        landmarks.add(new Landmark(R.string.name_i001, R.string.description_i001, R.drawable.i001, 56.94848, 24.10605, 67220356, R.string.website_i001));
        landmarks.add(new Landmark(R.string.name_i002, R.string.description_i002, R.drawable.i002, 56.95832, 24.11321, 26530164, R.string.website_i002));
        landmarks.add(new Landmark(R.string.name_i003, R.string.description_i003, R.drawable.i003, 56.94316, 24.12192, 67225361, R.string.website_i003));
        landmarks.add(new Landmark(R.string.name_i004, R.string.description_i004, R.drawable.i004, 57.06536, 24.01028, 22822822, R.string.website_i004));
        landmarks.add(new Landmark(R.string.name_i005, R.string.description_i005, R.drawable.i005, 56.91925, 24.05873, 67227444, R.string.website_i005));
        landmarks.add(new Landmark(R.string.name_i006, R.string.description_i006, R.drawable.i006, 56.96962, 24.17469, 26121540, R.string.website_i006));
        landmarks.add(new Landmark(R.string.name_i007, R.string.description_i007, R.drawable.i007, 56.94323, 24.06676, 29402027, R.string.website_i007));
        landmarks.add(new Landmark(R.string.name_i008, R.string.description_i008, R.drawable.i008, 56.94844, 24.11224, 67224279, R.string.website_i008));

        LandmarkAdapter adapter = new LandmarkAdapter(getActivity(), landmarks, R.color.insidersBackground_light, lat, lon);

        ListView listView = rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);

        // What will happen when a used clicks on a list item:
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                /*
                  PASSING DATA BETWEEN ACTIVITIES
                  IMPORTANT: FIRST start new activity, AFTER THAT pass the data to new activity!
                 */
                Intent i = new Intent(getActivity(), LandmarkActivity.class);
                startActivity(i);

                Intent intent = new Intent(InsidersFragment.this.getActivity(), LandmarkActivity.class);

                intent.putExtra("LandmarkImageResource_FromIntent", landmarks.get(position).getLandmarkImageResourceId());

                intent.putExtra("LandmarkName_FromIntent", landmarks.get(position).getLandmarkNameId());
                intent.putExtra("LandmarkDescription_FromIntent", landmarks.get(position).getLandmarkDescriptionId());
                intent.putExtra("LandmarkPhone_FromIntent", landmarks.get(position).getLandmarkPhoneId());
                intent.putExtra("LandmarkWebsite_FromIntent", landmarks.get(position).getLandmarkWebsiteId());

                intent.putExtra("LandmarkLatitude_FromIntent", landmarks.get(position).getLandmarkLatitudeId());
                intent.putExtra("LandmarkLongitude_FromIntent", landmarks.get(position).getLandmarkLongitudeId());

                intent.putExtra("Background_FromIntent", getResources().getColor(R.color.insidersBackground_light));

                startActivity(intent);

            }
        });


        return rootView;

    }

    public void getLocation() {
        // GETTING GPS DATA TO THIS FRAGMENT FROM ANOTHER CLASS
        gps = new LocationService(getActivity().getApplicationContext());

        lat = 0;
        lon = 0;

        // Checking if GPS enabled
        if (gps.canGetLocation()) {

            lat = gps.getLatitude();
            lon = gps.getLongitude();

            // Replace GPS logic with below mock data if needed for test purposes:
            // lat = 56.94703;
            // lon = 24.10646;
        }
    }

    @Override
    public void onResume(){
        super.onResume();

        getLocation ();
    }
}