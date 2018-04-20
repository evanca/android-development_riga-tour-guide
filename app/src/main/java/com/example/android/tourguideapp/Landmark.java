package com.example.android.tourguideapp;

public class Landmark {

    private int mLandmarkNameId;

    private int mLandmarkDescriptionId;

    private int mLandmarkImageResourceId;

    private double mLandmarkLatitudeId;

    private double mLandmarkLongitudeId;

    private int mLandmarkPhoneId;

    private int mLandmarkWebsiteId;

    public Landmark(int landmarkNameId, int landmarkDescriptionId, int landmarkImageResourceId, double landmarkLatitudeId, double landmarkLongitudeId, int landmarkPhoneId, int landmarkWebsiteId) {

        mLandmarkNameId = landmarkNameId;
        mLandmarkDescriptionId = landmarkDescriptionId;
        mLandmarkImageResourceId = landmarkImageResourceId;
        mLandmarkLatitudeId = landmarkLatitudeId;
        mLandmarkLongitudeId = landmarkLongitudeId;
        mLandmarkPhoneId = landmarkPhoneId;
        mLandmarkWebsiteId = landmarkWebsiteId;

    }

    /**
     * Public methods (can be used by any other Java code) to get the resources
     */

    public int getLandmarkNameId() {

        return mLandmarkNameId;
    }

    public int getLandmarkDescriptionId() {

        return mLandmarkDescriptionId;
    }

    public int getLandmarkImageResourceId() {

        return mLandmarkImageResourceId;
    }

    public double getLandmarkLatitudeId() {

        return mLandmarkLatitudeId;
    }

    public double getLandmarkLongitudeId() {

        return mLandmarkLongitudeId;
    }

    public int getLandmarkPhoneId() {

        return mLandmarkPhoneId;
    }

    public int getLandmarkWebsiteId() {

        return mLandmarkWebsiteId;
    }

}
