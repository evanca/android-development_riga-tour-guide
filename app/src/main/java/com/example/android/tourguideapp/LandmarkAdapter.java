package com.example.android.tourguideapp;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class LandmarkAdapter extends ArrayAdapter<Landmark> {

    private int mColorResourceId;
    private double mLat;
    private double mLon;

    public LandmarkAdapter(Context context, ArrayList<Landmark> landmarks, int colorResourceId, double lat, double lon) {

        super(context, 0, landmarks);
        mColorResourceId = colorResourceId;
        mLat = lat;
        mLon = lon;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Checking if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.item_landmark, parent, false);
        }

        // Getting the object located at this position in the list
        final Landmark currentLandmark = getItem(position);

        // Setting the name of the landmark to the first TextView
        TextView nameTextView = listItemView.findViewById(R.id.name_text_view);
        nameTextView.setText(currentLandmark.getLandmarkNameId());

        TextView addressTextView = listItemView.findViewById(R.id.distance_text_view);

        // Depending on GPS data provided, either set or remove "Distance: " text:
        if (mLat != 0 && mLon != 0) {

            Location currentLocation = new Location("CURRENT");

            currentLocation.setLatitude(mLat);
            currentLocation.setLongitude(mLon);

            Location nextLocation = new Location("NEXT");
            nextLocation.setLatitude(currentLandmark.getLandmarkLatitudeId());
            nextLocation.setLongitude(currentLandmark.getLandmarkLongitudeId());

            float distance = currentLocation.distanceTo(nextLocation) / 1000;   // in km
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            String showDistance = String.valueOf(decimalFormat.format(distance));

            if (distance < 1.00) {
                int closeDistanceColor = ContextCompat.getColor(getContext(), R.color.closeDistance_blue);
                addressTextView.setTextColor(closeDistanceColor);
            } else {
                int distanceColor = ContextCompat.getColor(getContext(), R.color.distance_gray);
                addressTextView.setTextColor(distanceColor);
            }

            String string = String.format("%s%s%s%s%s", getContext().getString(R.string.distance_string), " ", showDistance, " ", getContext().getString(R.string.km));
            addressTextView.setText(string);
        } else {

            String string = String.format("%s", getContext().getString(R.string.Please_enable_GPS));
            addressTextView.setText(string);
            int color = ContextCompat.getColor(getContext(), R.color.enable_gps);
            addressTextView.setTextColor(color);

        }

        // Setting the image of the landmark to the ImageView
        ImageView imageView = listItemView.findViewById(R.id.image);
        imageView.setImageResource(currentLandmark.getLandmarkImageResourceId());

        // Setting the background color

        RelativeLayout mainLayout = listItemView.findViewById(R.id.main_layout);
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        mainLayout.setBackgroundColor(color);

        return listItemView;
    }

}
