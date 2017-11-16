package com.example.guertz.tp3.Activities;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guertz.tp3.Models.DBHelper;
import com.example.guertz.tp3.Models.Items;
import com.example.guertz.tp3.Models.Restaurant;
import com.example.guertz.tp3.R;
import com.example.guertz.tp3.Tools.DialogHelper.DialogHelper;
import com.example.guertz.tp3.Tools.LogicalCode.Command;
import com.example.guertz.tp3.Tools.ScreenTools.ManualUI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guijet on 2017-11-14.
 */

public class DeleteRestaurant extends AppCompatActivity implements View.OnClickListener{

    private Integer currentSelectedId;
    private EditText TB_ChooseResto;
    private List<Restaurant> listAllResto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ManualUI ui  = new ManualUI(this);
        ui.setDesignSize(375,667);
        ui.removeTopBar();
        ui.setManualView((int)ui.rw(375),(int)ui.rh(667) - ui.getStatusBarHeight());

        setUpPage(ui);
        setUpButtonAdd(ui);

        fillListeRestaurant();
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

    private void setUpPage(ManualUI ui){
        View background = new View(this);
        background.setBackgroundColor(Color.parseColor("#3F70C3"));
        ui.setPosition(background,0,0,ui.rw(375),ui.rh(667));
        ui.addView(background);

        ImageView imageTop = new ImageView(this);
        imageTop.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.deleteform));
        ui.setPosition(imageTop,ui.rw(147),ui.rh(40),ui.rw(81),ui.rh(81));
        ui.addView(imageTop);

        TextView title = new TextView(this);
        title.setText("Supprimer un restaurant");
        title.setTextColor(Color.parseColor("#FFFFFF"));
        title.setTextSize(TypedValue.COMPLEX_UNIT_PX,ui.rw(25));
        title.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        ui.setPosition(title,ui.rw(53),ui.rh(125),ui.rw(269),ui.rh(40));
        ui.addView(title);

        TB_ChooseResto = new EditText(this);
        TB_ChooseResto.setHint("Choisir le restaurant");
        TB_ChooseResto.setFocusable(false);
        TB_ChooseResto.setOnClickListener(this);
        TB_ChooseResto.setFocusableInTouchMode(false);
        TB_ChooseResto.setId(R.id.ChooseRestauDelete);
        TB_ChooseResto.setFocusable(true);
        TB_ChooseResto.setHintTextColor(Color.parseColor("#FFFFFF"));
        TB_ChooseResto.setTextColor(Color.parseColor("#FFFFFF"));
        TB_ChooseResto.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        TB_ChooseResto.getBackground().mutate().setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
        ui.setPosition(TB_ChooseResto,ui.rw(69),ui.rh(212),ui.rw(237),ui.rh(45));
        ui.addView(TB_ChooseResto);
    }

    private void setUpButtonAdd(ManualUI ui){
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setColor(Color.parseColor("#FFFFFF"));
        shape.setCornerRadius(20);

        View containerButtonAdd = new View(this);
        containerButtonAdd.setOnClickListener(this);
        containerButtonAdd.setBackgroundColor(Color.parseColor("#FFFFFF"));
        containerButtonAdd.setId(R.id.BTN_Delete);
        containerButtonAdd.setBackground(shape);

        ui.setPosition(containerButtonAdd,ui.rw(94),ui.rh(570),ui.rw(188),ui.rh(50));
        ui.addView(containerButtonAdd);

        TextView textButton1 = new TextView(this);
        textButton1.setText("Supprimer");
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

    private void resetActivity(){
        finish();
        startActivity(getIntent());
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.ChooseRestauDelete){
            //GET LE ID ET DELETE BY ID APRES
            if(getListRestaurantName().size() > 0){
                setUpWheelPicker();
            }
            else{
                Toast.makeText(this,"Aucun restaurant dans la BD",Toast.LENGTH_LONG).show();
            }
        }

        else if (v.getId() == R.id.BTN_Delete) {

            if(!TB_ChooseResto.getText().toString().isEmpty()){
                DBHelper.deleteRestaurant(HomeActivity.bd,currentSelectedId);
                Toast.makeText(this,"Restaurant supprimer avec succes",Toast.LENGTH_LONG).show();
                resetActivity();
            }
            else{
                Toast.makeText(this,"Vous devez choisir un restaurant",Toast.LENGTH_LONG).show();
            }
        }
    }
}
