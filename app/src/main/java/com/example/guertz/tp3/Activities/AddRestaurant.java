package com.example.guertz.tp3.Activities;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guertz.tp3.Models.DBHelper;
import com.example.guertz.tp3.Models.Items;
import com.example.guertz.tp3.R;
import com.example.guertz.tp3.Tools.DialogHelper.DialogHelper;
import com.example.guertz.tp3.Tools.LogicalCode.Command;
import com.example.guertz.tp3.Tools.ScreenTools.ManualUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Guijet on 2017-11-14.
 */

public class AddRestaurant extends AppCompatActivity implements View.OnClickListener{


    private List<Items> choicesNote = Arrays.asList(new Items("Horrible",0), new Items("Médiocre",0), new Items("Moyen",0),new Items("Bien",0),new Items("Excellent",0));
    private EditText TB_Nom,TB_Adresse,TB_Bouffe,TB_Service,TB_Prix;
    private RatingBar rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE|WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        ManualUI ui  = new ManualUI(this);
        ui.setDesignSize(375,667);
        ui.removeTopBar();
        ui.setManualView((int)ui.rw(375),(int)ui.rh(667) - ui.getStatusBarHeight());

        setTextTop(ui);
        setTextEdits(ui);
        setUpButtonAdd(ui);
    }

    private void setTextTop(ManualUI ui){

        View background = new View(this);
        background.setBackgroundColor(Color.parseColor("#3F70C3"));
        ui.setPosition(background,0,0,ui.rw(375),ui.rh(667));
        ui.addView(background);

        ImageView imageTop = new ImageView(this);
        imageTop.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.addform));
        ui.setPosition(imageTop,ui.rw(147),ui.rh(40),ui.rw(81),ui.rh(81));
        ui.addView(imageTop);

        TextView title = new TextView(this);
        title.setText("Ajouter un restaurant");
        title.setTextColor(Color.parseColor("#FFFFFF"));
        title.setTextSize(TypedValue.COMPLEX_UNIT_PX,ui.rw(25));
        title.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        ui.setPosition(title,ui.rw(53),ui.rh(125),ui.rw(269),ui.rh(40));
        ui.addView(title);
    }

    private void setTextEdits(ManualUI ui){
        TB_Nom = new EditText(this);
        TB_Nom.setHint("Nom Restaurant");
        TB_Nom.setId(R.id.TB_Nom);
        TB_Nom.setHintTextColor(Color.parseColor("#FFFFFF"));
        TB_Nom.setTextColor(Color.parseColor("#FFFFFF"));
        TB_Nom.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        TB_Nom.getBackground().mutate().setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
        ui.setPosition(TB_Nom,ui.rw(69),ui.rh(196),ui.rw(237),ui.rh(45));
        ui.addView(TB_Nom);

        TB_Adresse = new EditText(this);
        TB_Adresse.setHint("Adresse");
        TB_Adresse.setId(R.id.TB_Adresse);
        TB_Adresse.setHintTextColor(Color.parseColor("#FFFFFF"));
        TB_Adresse.setTextColor(Color.parseColor("#FFFFFF"));
        TB_Adresse.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        TB_Adresse.getBackground().mutate().setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
        ui.setPosition(TB_Adresse,ui.rw(69),ui.rh(255),ui.rw(237),ui.rh(45));
        ui.addView(TB_Adresse);

        TB_Bouffe = new EditText(this);
        TB_Bouffe.setOnClickListener(this);
        TB_Bouffe.setHint("Qualité bouffe");
        TB_Bouffe.setId(R.id.TB_Bouffe);
        TB_Bouffe.setHintTextColor(Color.parseColor("#FFFFFF"));
        TB_Bouffe.setTextColor(Color.parseColor("#FFFFFF"));
        TB_Bouffe.setFocusable(false);
        TB_Bouffe.setFocusableInTouchMode(false);
        TB_Bouffe.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        TB_Bouffe.getBackground().mutate().setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
        ui.setPosition(TB_Bouffe,ui.rw(69),ui.rh(314),ui.rw(237),ui.rh(45));
        ui.addView(TB_Bouffe);

        TB_Service = new EditText(this);
        TB_Service.setOnClickListener(this);
        TB_Service.setHint("Qualité service");
        TB_Service.setId(R.id.TB_Service);
        TB_Service.setFocusable(false);
        TB_Service.setFocusableInTouchMode(false);
        TB_Service.setHintTextColor(Color.parseColor("#FFFFFF"));
        TB_Service.setTextColor(Color.parseColor("#FFFFFF"));
        TB_Service.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        TB_Service.getBackground().mutate().setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
        ui.setPosition(TB_Service,ui.rw(69),ui.rh(375),ui.rw(237),ui.rh(45));
        ui.addView(TB_Service);

        TB_Prix = new EditText(this);
        TB_Prix.setHint("Prix Moyen");
        TB_Prix.setId(R.id.TB_Prix);
        TB_Prix.setHintTextColor(Color.parseColor("#FFFFFF"));
        TB_Prix.setFocusable(true);
        TB_Prix.setInputType(InputType.TYPE_CLASS_NUMBER);
        TB_Prix.setFocusableInTouchMode(true);
        TB_Prix.setTextColor(Color.parseColor("#FFFFFF"));
        TB_Prix.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        TB_Prix.getBackground().mutate().setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
        ui.setPosition(TB_Prix,ui.rw(69),ui.rh(436),ui.rw(237),ui.rh(45));
        ui.addView(TB_Prix);


        rating = new RatingBar(this);
        rating.setId(R.id.Nb_Etoile);
        rating.setNumStars(5);
        rating.setStepSize((float) 1.0);
        LayerDrawable stars = (LayerDrawable) rating.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        ui.setPosition(rating,ui.rw(80),ui.rh(500),ui.rw(215),ui.rh(52));
        ui.addView(rating);
    }

    private void setUpButtonAdd(ManualUI ui){
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setColor(Color.parseColor("#FFFFFF"));
        shape.setCornerRadius(20);

        View containerButtonAdd = new View(this);
        containerButtonAdd.setOnClickListener(this);
        containerButtonAdd.setBackgroundColor(Color.parseColor("#FFFFFF"));
        containerButtonAdd.setId(R.id.BTN_Add);
        containerButtonAdd.setBackground(shape);

        ui.setPosition(containerButtonAdd,ui.rw(94),ui.rh(570),ui.rw(188),ui.rh(50));
        ui.addView(containerButtonAdd);

        TextView textButton1 = new TextView(this);
        textButton1.setText("Ajouter");
        textButton1.setTextColor(Color.parseColor("#3F70C3"));
        textButton1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textButton1.setTextSize(TypedValue.COMPLEX_UNIT_PX,ui.rw(18));
        ui.setPosition(textButton1,ui.rw(0),ui.rh(12),ui.rw(188),ui.rh(40));
        ui.addFrom(textButton1,containerButtonAdd);
    }

    private void setUpWheelPicker(final EditText tb,String titre){
        final DialogHelper myDialogHelper = new DialogHelper(choicesNote);
        myDialogHelper.buildWheelPicker(this, titre, new Command() {
            @Override
            public void execute() {
                tb.setText(myDialogHelper.getSelectedItem().toString());
            }
        });
    }

    private void resetActivity(){
        finish();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.BTN_Add){
            if(!TB_Adresse.getText().toString().isEmpty() && !TB_Bouffe.getText().toString().isEmpty() && !TB_Nom.getText().toString().isEmpty() && !TB_Prix.getText().toString().isEmpty() && !TB_Service.getText().toString().isEmpty()){
                DBHelper.addRestaurants(HomeActivity.bd,TB_Nom.getText().toString(),TB_Adresse.getText().toString(),TB_Bouffe.getText().toString(),TB_Service.getText().toString(),Float.parseFloat(TB_Prix.getText().toString()),(int)rating.getRating());
                Toast.makeText(this,"Restaurant ajouter avec succès",Toast.LENGTH_LONG).show();
                resetActivity();
            }
            else{
                Toast.makeText(this,"Vous devez remplir tout les champs",Toast.LENGTH_LONG).show();
            }
        }
        else if(v.getId() == R.id.TB_Bouffe){
            setUpWheelPicker(TB_Bouffe,"Qualité de la bouffe");
        }
        else if(v.getId() == R.id.TB_Service){
            setUpWheelPicker(TB_Service,"Qualite du service");
        }
    }
}
