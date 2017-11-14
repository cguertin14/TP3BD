package com.example.guertz.tp3.Tools.Models;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

/**
 * Created by guertz on 31/07/17.
 */

public class LocationObject implements Serializable {

    private double latitude;
    private double longitude;

    public LocationObject(LatLng location) {
        setLatitude(location.latitude);
        setLongitude(location.longitude);
    }

    //region GET-set

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    //endregion GET-set

    public LatLng toLatLng() {
        return new LatLng(getLatitude(),getLongitude());
    }
}
