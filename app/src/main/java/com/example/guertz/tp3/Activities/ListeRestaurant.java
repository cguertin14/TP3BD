package com.example.guertz.tp3.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.guertz.tp3.Activities.RecyclerView.RestaurantsAdapter;

import java.util.List;

/**
 * Created by Guijet on 2017-11-14.
 */

public class ListeRestaurant extends AppCompatActivity {
    private RestaurantsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new RestaurantsAdapter(this,);
        RecyclerView test = new RecyclerView(this);
        test.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        test.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        test.setNestedScrollingEnabled(false);
    }
}
