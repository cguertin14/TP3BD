package com.example.guertz.tp3.Tools.Memory;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.guertz.tp3.Tools.Models.LatLngObject;
import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by guertz on 28/07/17.
 */

public class Cache {

    public static LatLngObject getJSON(Context ctx) {
        String json = getString(ctx,"ProjetCoordonates","Coordonates");
        return new Gson().fromJson(json, LatLngObject.class);
    }

    public static void editString(Context ctx, String cacheName, String cacheKey, String cacheElements) {
        SharedPreferences preferences = ctx.getSharedPreferences(cacheName,Context.MODE_PRIVATE);
        SharedPreferences.Editor cache = preferences.edit();
        cache.putString(cacheKey, cacheElements);
        cache.apply();
    }

    public static String getString(Context ctx, String cacheName, String cacheKey) {
        SharedPreferences sp = ctx.getSharedPreferences(cacheName,MODE_PRIVATE);
        return sp.getString(cacheKey,null);
    }

}