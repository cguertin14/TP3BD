package com.example.guertz.tp3.Activities.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.guertz.tp3.Models.Restaurant;
import com.example.guertz.tp3.R;
import com.example.guertz.tp3.Tools.ScreenTools.ManualUI;

import java.util.List;

/**
 * Created by Guijet on 2017-11-16.
 */

public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.MyViewHolder> {

    private List<Restaurant> restaurants;
    private Context ctx;
    private ManualUI ui;

    public RestaurantsAdapter(Context ctx, List<Restaurant> restaurants,ManualUI fixedUI) {
        this.restaurants = restaurants;
        this.ctx = ctx;
        ui = fixedUI;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_nom,tv_adresse,tv_qualiteBouffe,tv_qualiteService,tv_prix,tv_etoiles;

        public MyViewHolder(View view) {
            super(view);
            tv_nom = view.findViewById(R.id.TV_Nom);
            tv_adresse = view.findViewById(R.id.TV_Adresse);
            tv_qualiteBouffe = view.findViewById(R.id.TV_Bouffe);
            tv_qualiteService = view.findViewById(R.id.TV_Service);
            tv_prix = view.findViewById(R.id.TV_Prix);
            tv_etoiles = view.findViewById(R.id.TV_Etoile);
        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_container, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_nom.setText(restaurants.get(position).getNom());
        holder.tv_adresse.setText(restaurants.get(position).getAdresse());
        holder.tv_etoiles.setText(String.valueOf(restaurants.get(position).getNbEtoiles()) + " Étoiles");
        holder.tv_prix.setText(String.valueOf(restaurants.get(position).getPrixMoyen()));
        holder.tv_qualiteBouffe.setText(restaurants.get(position).getQualiteBouffe());
        holder.tv_qualiteService.setText(restaurants.get(position).getQualiteService());
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }
}
