package com.example.android.tourguideapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SightsFragment extends Fragment {

    public LocationService gps;
    public double lat;
    public double lon;

    public SightsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        getLocation();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ImageView imageView = getActivity().findViewById(R.id.header_image);
        imageView.setImageResource(R.drawable.header_1);

        View rootView = inflater.inflate(R.layout.list_landmark, container, false);

        getLocation();

        final ArrayList<Landmark> landmarks = new ArrayList<>();
        landmarks.add(new Landmark(R.string.name_s005, R.string.description_s005, R.raw.s005, 56.94901, 24.10475, 67227573, R.string.website_s005));
        landmarks.add(new Landmark(R.string.name_s004, R.string.description_s004, R.raw.s004, 56.95753, 24.11112, 67181465, R.string.website_s004));
        landmarks.add(new Landmark(R.string.name_s007, R.string.description_s007, R.raw.s007, 56.94754, 24.10932, 67181430, R.string.website_s007));
        landmarks.add(new Landmark(R.string.name_s003, R.string.description_s003, R.raw.s003, 56.94712, 24.10689, 67043678, R.string.website_s003));
        landmarks.add(new Landmark(R.string.name_s002, R.string.description_s002, R.raw.s002, 56.94355, 24.11486, 67229985, R.string.website_s002));
        landmarks.add(new Landmark(R.string.name_s008, R.string.description_s008, R.raw.s008, 56.94835, 24.11413, 67073777, R.string.website_s008));
        landmarks.add(new Landmark(R.string.name_s001, R.string.description_s001, R.raw.s001, 56.95939, 24.10871, 67181465, R.string.website_s001));
        landmarks.add(new Landmark(R.string.name_s006, R.string.description_s006, R.raw.s006, 56.95149, 24.1133, 67037900, R.string.website_s006));

        LandmarkAdapter adapter = new LandmarkAdapter(getActivity(), landmarks, R.color.sightsBackground_light, lat, lon);

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

                Intent intent = new Intent(SightsFragment.this.getActivity(), LandmarkActivity.class);

                intent.putExtra("LandmarkImageResource_FromIntent", landmarks.get(position).getLandmarkImageResourceId());

                intent.putExtra("LandmarkName_FromIntent", landmarks.get(position).getLandmarkNameId());
                intent.putExtra("LandmarkDescription_FromIntent", landmarks.get(position).getLandmarkDescriptionId());
                intent.putExtra("LandmarkPhone_FromIntent", landmarks.get(position).getLandmarkPhoneId());
                intent.putExtra("LandmarkWebsite_FromIntent", landmarks.get(position).getLandmarkWebsiteId());

                intent.putExtra("LandmarkLatitude_FromIntent", landmarks.get(position).getLandmarkLatitudeId());
                intent.putExtra("LandmarkLongitude_FromIntent", landmarks.get(position).getLandmarkLongitudeId());

                intent.putExtra("Background_FromIntent", getResources().getColor(R.color.sightsBackground_light));

                startActivity(intent);

            }
        });

        return rootView;

    }
    public void getLocation () {
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
