package com.example.guertz.tp3.Tools.Map;

import android.content.Context;
import android.location.Address;
import android.util.Log;
import com.example.guertz.tp3.R;
import com.example.guertz.tp3.Tools.Models.LatLngObject;
import com.example.guertz.tp3.Tools.Memory.Cache;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by guertz on 27/07/17.
 */

public abstract class GoogleMapUtility {

    private static void buildMarker(double latitude,double longitude,GoogleMap map,MarkerOptions ... marker) {
        if (marker.length == 0) {
            MarkerOptions marker2 = new MarkerOptions();
            marker2.position(new LatLng(latitude, longitude));
            //marker2.icon(BitmapDescriptorFactory.fromResource(R.drawable.pin));
            map.addMarker(marker2);
        } else {
            marker[0].position(new LatLng(latitude, longitude));
            //marker[0].icon(BitmapDescriptorFactory.fromResource(R.drawable.pin));
            map.addMarker(marker[0]);
        }
    }

    public static void zoomMultipleMarkers(final Context ctx, final GoogleMap map) {
        map.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition arg0) {
                LatLngObject latLngObject = Cache.getJSON(ctx);
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                if (latLngObject != null) {
                    for (LatLng position : latLngObject.getCoordonates()) {
                        builder.include(position);
                    }
                    map.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 10));
                    map.setOnCameraChangeListener(null);
                }
            }
        });
    }

    public static void zoomSingleMarker(final Context ctx, final GoogleMap map, final LatLng coordinate) {
        map.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition arg0) {
                //Log.d("YAY","Method Works!");
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(coordinate,14.0f));
                //map.setOnCameraChangeListener(null);
            }
        });
    }

    public static void buildSingleMarkerMap(Context ctx, GoogleMap map, LatLng position) {
        buildMarker(position.latitude,position.longitude,map);
    }

    public static void buildFromCache(Context ctx, LatLngObject coordonates, GoogleMap map) {
        for (LatLng position : coordonates.getCoordonates()) {
            buildMarker(position.latitude,position.longitude,map);
        }
    }

}
