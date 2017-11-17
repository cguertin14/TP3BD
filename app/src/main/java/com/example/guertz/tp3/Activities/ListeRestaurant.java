package com.example.guertz.tp3.Activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guertz.tp3.Activities.RecyclerView.RestaurantsAdapter;
import com.example.guertz.tp3.Models.DBHelper;
import com.example.guertz.tp3.Models.Items;
import com.example.guertz.tp3.Models.Restaurant;
import com.example.guertz.tp3.R;
import com.example.guertz.tp3.Tools.DialogHelper.DialogHelper;
import com.example.guertz.tp3.Tools.LogicalCode.Command;
import com.example.guertz.tp3.Tools.ScreenTools.ManualUI;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Guijet on 2017-11-14.
 */

public class ListeRestaurant extends AppCompatActivity implements View.OnClickListener {


    private List<Items> choicesNote = Arrays.asList(new Items("Tout",0),new Items("1 Etoile",1), new Items("2 Étoile",2), new Items("3 Étoile",3),new Items("4 Étoile",4),new Items("5 Étoile",5));
    private List<Restaurant> listeRestaurant;
    private RestaurantsAdapter adapter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ManualUI ui  = new ManualUI(this);
        ui.setDesignSize(375,667);
        ui.removeTopBar();
        ui.setManualView((int)ui.rw(375),(int)ui.rh(667) - ui.getStatusBarHeight());
        fillListRestaurant();
        setUpRecyclerView(ui);
        buildTopHeader(ui);

    }

    private void fillListRestaurant(){
        listeRestaurant = DBHelper.getAllRestaurant(HomeActivity.bd);
    }

    private void buildTopHeader(ManualUI ui){

        View header = new View(this);
        header.setBackgroundColor(Color.parseColor("#3F70C3"));
        ui.setPosition(header,ui.rw(0),0,ui.rw(375),(ui.rh(667) - (ui.rh(667) - ui.getStatusBarHeight() - ui.rh(75)) - ui.getStatusBarHeight()));
        ui.addView(header);

        TextView title  = new TextView(this);
        title.setText("Liste restaurants");
        title.setTextColor(Color.parseColor("#FFFFFF"));
        title.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        title.setTextSize(TypedValue.COMPLEX_UNIT_PX,ui.rw(20));
        ui.setPosition(title,ui.rw(95),ui.rh(25),ui.rw(185),ui.rh(30));
        ui.addFrom(title,header);

        ImageButton BTN_Search = new ImageButton(this);
        BTN_Search.setBackgroundColor(Color.TRANSPARENT);
        BTN_Search.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.searchlist));
        BTN_Search.setScaleType(ImageView.ScaleType.FIT_CENTER);
        BTN_Search.setId(R.id.BTN_Search);
        BTN_Search.setOnClickListener(this);
        ui.setPosition(BTN_Search,ui.rw(325),ui.rh(20),ui.rw(40),ui.rw(40));
        ui.addView(BTN_Search);

    }

    private void setUpRecyclerView(ManualUI ui){
        adapter = new RestaurantsAdapter(this,listeRestaurant,ui);

        recyclerView = new RecyclerView(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setBackgroundColor(Color.parseColor("#3F70C3"));
        ui.setPosition(recyclerView,0,ui.rh(75),ui.rw(375),ui.rh(667) - ui.getStatusBarHeight() - ui.rh(75));
        ui.addView(recyclerView);

        //LOSRQUE SA CHANGE le data
        //adapter.notifyDataSetChanged();
    }

    private void setUpWheelPicker(){
        final DialogHelper myDialogHelper = new DialogHelper(choicesNote);
        myDialogHelper.buildWheelPicker(this, "Critère de recherche", new Command() {
            @Override
            public void execute() {


                if(myDialogHelper.getSelectedItem().getId() != 0){
                    listeRestaurant.clear();
                    listeRestaurant.addAll(DBHelper.getRestaurantsByStars(HomeActivity.bd,myDialogHelper.getSelectedItem().getId()));
                }
                else {
                    listeRestaurant.clear();
                    listeRestaurant.addAll(DBHelper.getAllRestaurant(HomeActivity.bd));
                }
                adapter.notifyDataSetChanged();
                recyclerView.smoothScrollToPosition(0);
            }
        });
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.BTN_Search){
            setUpWheelPicker();
        }
    }
}
