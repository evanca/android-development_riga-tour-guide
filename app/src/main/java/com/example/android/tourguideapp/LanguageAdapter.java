package com.example.android.tourguideapp;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class LanguageAdapter extends ArrayAdapter<Language> {

    private int mColorResourceId;


    public LanguageAdapter(Context context, ArrayList<Language> phrases, int colorResourceId) {

        super(context, 0, phrases);
        mColorResourceId = colorResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.item_landmark, parent, false);
        }

        final Language currentLanguage = getItem(position);

        LinearLayout translationLinear = listItemView.findViewById(R.id.item_mini_linear);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(translationLinear.getLayoutParams());
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_START);
        layoutParams.setMargins(32, 8, 8, 16);
        translationLinear.setLayoutParams(layoutParams);

        TextView defaultTextView = listItemView.findViewById(R.id.name_text_view);
        defaultTextView.setText(currentLanguage.getLanguageDefault());
        defaultTextView.setGravity(Gravity.START);

        TextView localTextView = listItemView.findViewById(R.id.distance_text_view);
        localTextView.setText(currentLanguage.getLanguageLocal());
        localTextView.setGravity(Gravity.START);

        // Removing image as we don't need it in this fragment
        ImageView imageView = listItemView.findViewById(R.id.image);
        imageView.setVisibility(View.GONE);

        // Setting the background color
        RelativeLayout mainLayout = listItemView.findViewById(R.id.main_layout);
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        mainLayout.setBackgroundColor(color);

        return listItemView;
    }

}
