package com.example.guertz.tp3.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guertz.tp3.Models.DBHelper;
import com.example.guertz.tp3.Models.Items;
import com.example.guertz.tp3.Models.Restaurant;
import com.example.guertz.tp3.R;
import com.example.guertz.tp3.Tools.DialogHelper.DialogHelper;
import com.example.guertz.tp3.Tools.LogicalCode.Command;
import com.example.guertz.tp3.Tools.ScreenTools.ManualUI;
import com.example.guertz.tp3.Tools.ScreenTools.PageStarter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guijet on 2017-11-14.
 */

public class ChooseRestaurant extends AppCompatActivity implements View.OnClickListener{

    private List<Restaurant> listAllResto;
    Integer currentSelectedId;
    EditText TB_ChooseResto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ManualUI ui = new ManualUI(this);
        ui.setDesignSize(375,667);
        ui.removeTopBar();
        ui.setManualView((int)ui.rw(375),(int)ui.rh(667) - ui.getStatusBarHeight());
        fillListeRestaurant();
        setUpTop(ui);
        setUpButtonAdd(ui);
    }

    private void fillListeRestaurant(){
        //GET ALL RESTAURANTS
        try{
            listAllResto = DBHelper.getAllRestaurant(HomeActivity.bd);
        }
        catch(Exception e){
            Log.e("Erreur:",e.getMessage().toString());
        }
    }

    private void setUpTop(ManualUI ui){

        View background = new View(this);
        background.setBackgroundColor(Color.parseColor("#3F70C3"));
        ui.setPosition(background,0,0,ui.rw(375),ui.rh(667));
        ui.addView(background);

        TextView title = new TextView(this);
        title.setText("Choisissez le restaurant \n" +
                "que vous voulez modifier");
        title.setTextColor(Color.parseColor("#FFFFFF"));
        title.setTextSize(TypedValue.COMPLEX_UNIT_PX,ui.rw(25));
        title.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        ui.setPosition(title,ui.rw(33),ui.rh(60),ui.rw(309),ui.rh(70));
        ui.addView(title);


        TB_ChooseResto = new EditText(this);
        TB_ChooseResto.setHint("Choisir le restaurant");
        TB_ChooseResto.setFocusable(false);
        TB_ChooseResto.setOnClickListener(this);
        TB_ChooseResto.setFocusableInTouchMode(false);
        TB_ChooseResto.setId(R.id.ChooseRestauModify);
        TB_ChooseResto.setFocusable(true);
        TB_ChooseResto.setHintTextColor(Color.parseColor("#FFFFFF"));
        TB_ChooseResto.setTextColor(Color.parseColor("#FFFFFF"));
        TB_ChooseResto.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        TB_ChooseResto.getBackground().mutate().setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
        ui.setPosition(TB_ChooseResto,ui.rw(69),ui.rh(212),ui.rw(237),ui.rh(45));
        ui.addView(TB_ChooseResto);

        //BTN_Modify
    }

    private void setUpButtonAdd(ManualUI ui){
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setColor(Color.parseColor("#FFFFFF"));
        shape.setCornerRadius(20);

        View containerButtonAdd = new View(this);
        containerButtonAdd.setOnClickListener(this);
        containerButtonAdd.setBackgroundColor(Color.parseColor("#FFFFFF"));
        containerButtonAdd.setId(R.id.BTN_Modify);
        containerButtonAdd.setBackground(shape);

        ui.setPosition(containerButtonAdd,ui.rw(94),ui.rh(570),ui.rw(188),ui.rh(50));
        ui.addView(containerButtonAdd);

        TextView textButton1 = new TextView(this);
        textButton1.setText("Suivant");
        textButton1.setTextColor(Color.parseColor("#3F70C3"));
        textButton1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textButton1.setTextSize(TypedValue.COMPLEX_UNIT_PX,ui.rw(18));
        ui.setPosition(textButton1,ui.rw(0),ui.rh(12),ui.rw(188),ui.rh(40));
        ui.addFrom(textButton1,containerButtonAdd);
    }

    private List<Items> getListRestaurantName(){
        List<Items> listeItems = new ArrayList<>();
        if(listAllResto != null && listAllResto.size() > 0) {
            for (Restaurant x:listAllResto) {
                listeItems.add(new Items(x.getNom(),x.getId()));
            }
        }
        return listeItems;
    }

    private void setUpWheelPicker(){
        final DialogHelper myDialogHelper = new DialogHelper(getListRestaurantName());
        myDialogHelper.buildWheelPicker(this, "Choisir le restaurant", new Command() {
            @Override
            public void execute() {
                TB_ChooseResto.setText(myDialogHelper.getSelectedItem().toString());
                currentSelectedId  = myDialogHelper.getSelectedItem().getId();
            }
        });
    }

    private Restaurant getRestaurantByID(Integer currentID){
        Restaurant returnValue = new Restaurant(0,"","","","",0.0f,0);
        for (Restaurant x:listAllResto){
            if(x.getId() == currentID){
                returnValue = x;
                break;
            }
        }
        return returnValue;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.ChooseRestauModify){
            if(getListRestaurantName().size() > 0) {
                setUpWheelPicker();
            }
            else{
                Toast.makeText(this,"Aucun restaurant dans la BD",Toast.LENGTH_LONG).show();
            }
        }
        else if(v.getId() == R.id.BTN_Modify){
            if(!TB_ChooseResto.getText().toString().isEmpty()){
                Intent intent = new Intent(this, ModifyRestaurant.class);
                intent.putExtra("MyClass",getRestaurantByID(currentSelectedId));
                startActivity(intent);
                finish();
            }
            else{
                Toast.makeText(this,"Vous devez choisir un restaurant",Toast.LENGTH_LONG).show();
            }
        }
    }


}
