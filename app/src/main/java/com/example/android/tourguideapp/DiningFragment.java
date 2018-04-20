package com.example.android.tourguideapp;

// TODO: Update coordinates

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
public class DiningFragment extends Fragment {

    public LocationService gps;
    public double lat;
    public double lon;

    public DiningFragment() {
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

        final ArrayList<Landmark> landmarks = new ArrayList<>();
        landmarks.add(new Landmark(R.string.name_d001, R.string.description_d001, R.raw.d001, 56.95185, 24.117, 20225000, R.string.website_d001));
        landmarks.add(new Landmark(R.string.name_d002, R.string.description_d002, R.raw.d002, 56.94882, 24.12259, 67099763, R.string.website_d002));
        landmarks.add(new Landmark(R.string.name_d003, R.string.description_d003, R.raw.d003, 56.94976, 24.11024, 67224576, R.string.website_d003));
        landmarks.add(new Landmark(R.string.name_d004, R.string.description_d004, R.raw.d004, 56.9272, 24.16021, 67504420, R.string.website_d004));
        landmarks.add(new Landmark(R.string.name_d005, R.string.description_d005, R.raw.d005, 56.95108, 24.10241, 29533523, R.string.website_d005));
        landmarks.add(new Landmark(R.string.name_d006, R.string.description_d006, R.raw.d006, 56.94716, 24.11095, 67225699, R.string.website_d006));
        landmarks.add(new Landmark(R.string.name_d007, R.string.description_d007, R.raw.d007, 56.95156, 24.10779, 20370537, R.string.website_d007));
        landmarks.add(new Landmark(R.string.name_d008, R.string.description_d008, R.raw.d008, 56.94966, 24.10307, 29529200, R.string.website_d008));
        landmarks.add(new Landmark(R.string.name_d009, R.string.description_d009, R.raw.d009, 56.95886, 24.10452, 67332830, R.string.website_d008));

        LandmarkAdapter adapter = new LandmarkAdapter(getActivity(), landmarks, R.color.diningBackground_light, lat, lon);

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

                Intent intent = new Intent(DiningFragment.this.getActivity(), LandmarkActivity.class);

                intent.putExtra("LandmarkImageResource_FromIntent", landmarks.get(position).getLandmarkImageResourceId());

                intent.putExtra("LandmarkName_FromIntent", landmarks.get(position).getLandmarkNameId());
                intent.putExtra("LandmarkDescription_FromIntent", landmarks.get(position).getLandmarkDescriptionId());
                intent.putExtra("LandmarkPhone_FromIntent", landmarks.get(position).getLandmarkPhoneId());
                intent.putExtra("LandmarkWebsite_FromIntent", landmarks.get(position).getLandmarkWebsiteId());

                intent.putExtra("LandmarkLatitude_FromIntent", landmarks.get(position).getLandmarkLatitudeId());
                intent.putExtra("LandmarkLongitude_FromIntent", landmarks.get(position).getLandmarkLongitudeId());

                intent.putExtra("Background_FromIntent", getResources().getColor(R.color.diningBackground_light));

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