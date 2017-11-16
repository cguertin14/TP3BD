package com.example.guertz.tp3.Activities.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.guertz.tp3.Models.Restaurant;
import com.example.guertz.tp3.Tools.ScreenTools.ManualUI;

import java.util.List;

/**
 * Created by Guijet on 2017-11-16.
 */

public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.MyViewHolder> {

    private List<Restaurant> restaurants;
    private Context ctx;
    private ManualUI ui;

    public RestaurantsAdapter(Context ctx, List<Restaurant> restaurants) {
        this.restaurants = restaurants;
        this.ctx = ctx;
        ui = new ManualUI((AppCompatActivity) this.ctx);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_nom,tv_adresse,tv_qualiteBouffe,tv_qualiteService,tv_prix,tv_etoiles;

        public MyViewHolder(View view) {
            super(view);
            //tv_nom = view.findViewById(R.id.nom);
        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View toReturn = null;
        return new MyViewHolder(toReturn);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        //holder.

    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }
}
