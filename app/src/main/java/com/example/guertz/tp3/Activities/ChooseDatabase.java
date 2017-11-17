package com.example.guertz.tp3.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guertz.tp3.R;
import com.example.guertz.tp3.Tools.ScreenTools.ManualUI;

/**
 * Created by Guijet on 2017-11-16.
 */

public class ChooseDatabase extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ManualUI ui  = new ManualUI(this);
        ui.setDesignSize(375,667);
        ui.removeTopBar();
        ui.setManualView((int)ui.rw(375),(int)ui.rh(667) - ui.getStatusBarHeight());

        setUpTop(ui);
        setButtonSQLITE(ui);
        setUpButtonOracle(ui);
    }



    private void setUpTop(ManualUI ui){

        View background = new View(this);
        background.setBackgroundColor(Color.parseColor("#3F70C3"));
        ui.setPosition(background,0,0,ui.rw(375),ui.rh(667));
        ui.addView(background);


        TextView title = new TextView(this);
        title.setText("Choisir la base\n de données");
        title.setTextColor(Color.parseColor("#FFFFFF"));
        title.setTextSize(TypedValue.COMPLEX_UNIT_PX,ui.rw(25));
        title.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        ui.setPosition(title,ui.rw(78),ui.rh(150),ui.rw(220),ui.rh(64));
        ui.addView(title);
    }

    private void setUpButtonOracle(ManualUI ui){

        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setColor(Color.parseColor("#FFFFFF"));
        shape.setCornerRadius(30);

        View oracle = new View(this);
        oracle.setOnClickListener(this);
        oracle.setBackgroundColor(Color.parseColor("#FFFFFF"));
        oracle.setId(R.id.BTN_Oracle);
        oracle.setBackground(shape);

        ui.setPosition(oracle,ui.rw(34),ui.rh(278),ui.rw(135),ui.rh(135));
        ui.addView(oracle);


        TextView textButton1 = new TextView(this);
        textButton1.setText("Oracle");
        textButton1.setTextColor(Color.parseColor("#3F70C3"));
        textButton1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textButton1.setTextSize(TypedValue.COMPLEX_UNIT_PX,ui.rw(30));
        ui.setPosition(textButton1,ui.rw(0),ui.rh(45),ui.rw(135),ui.rh(40));
        ui.addFrom(textButton1,oracle);
    }

    private void setButtonSQLITE(ManualUI ui){

        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setColor(Color.parseColor("#FFFFFF"));
        shape.setCornerRadius(30);

        View sqlLite = new View(this);
        sqlLite.setOnClickListener(this);
        sqlLite.setBackgroundColor(Color.parseColor("#FFFFFF"));
        sqlLite.setId(R.id.BTN_SQLite);
        sqlLite.setBackground(shape);

        ui.setPosition(sqlLite,ui.rw(209),ui.rh(278),ui.rw(135),ui.rh(135));
        ui.addView(sqlLite);


        TextView textButton1 = new TextView(this);
        textButton1.setText("SQLite");
        textButton1.setTextColor(Color.parseColor("#3F70C3"));
        textButton1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textButton1.setTextSize(TypedValue.COMPLEX_UNIT_PX,ui.rw(30));
        ui.setPosition(textButton1,ui.rw(0),ui.rh(45),ui.rw(135),ui.rh(40));
        ui.addFrom(textButton1,sqlLite);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.BTN_Oracle){
            Intent intent = new Intent(this, ListeOracle.class);
            startActivity(intent);
        }
        else if(v.getId() == R.id.BTN_SQLite){
            Intent intent = new Intent(this, ListeRestaurant.class);
            startActivity(intent);
        }
    }
}
