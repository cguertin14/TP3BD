package com.example.guertz.tp3.Tools.Models;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by guertz on 27/07/17.
 */

public class LatLngObject implements Serializable {

    private List<LatLng> coordonates;


    public LatLngObject() {
        coordonates = new ArrayList<>();
    }

    // region GET-set

    public List<LatLng> getCoordonates() { return this.coordonates; }

    // endregion
}
