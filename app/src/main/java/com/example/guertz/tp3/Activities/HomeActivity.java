package com.example.guertz.tp3.Activities;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.guertz.tp3.R;
import com.example.guertz.tp3.Tools.ScreenTools.ManualUI;
import org.sqlite.SQLiteConnection;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    //MY DATABASE
    //USER HomeActivity.bd --> DANS LES AUTRES FILES
    public static SQLiteDatabase bd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        instantiateDatabase(bd);

        ManualUI ui  = new ManualUI(this);
        ui.setDesignSize(375,667);
        ui.removeTopBar();
        ui.setManualView((int)ui.rw(375),(int)ui.rh(667) - ui.getStatusBarHeight());

        //SET UP PAGES COMPONENTS
        setUpTop(ui);
        setButtonAdd(ui);
        setButtonDelete(ui);
        setButtonModify(ui);
        setButtonListe(ui);
    }
    private void instantiateDatabase(SQLiteDatabase db){
        db = openOrCreateDatabase("TP3",Context.MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Restaurants(Id INTEGER PRIMARY KEY AUTOINCREMENT, Nom VARCHAR, Adresse VARCHAR, qualiteBouffe INTEGER, qualiteService INTEGER, prixMoyen REAL, Cote INTEGER );");
    }

    private void setUpTop(ManualUI ui){

        View background = new View(this);
        background.setBackgroundColor(Color.parseColor("#3F70C3"));
        ui.setPosition(background,0,0,ui.rw(375),ui.rh(667));
        ui.addView(background);

        ImageView imageTop = new ImageView(this);
        imageTop.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.menuimage));
        ui.setPosition(imageTop,ui.rw(143),ui.rh(51),ui.rw(89),ui.rh(89));
        ui.addView(imageTop);

        TextView title = new TextView(this);
        title.setText("Menu de Gestion\n de restaurants");
        title.setTextColor(Color.parseColor("#FFFFFF"));
        title.setTextSize(TypedValue.COMPLEX_UNIT_PX,ui.rw(25));
        title.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        ui.setPosition(title,ui.rw(78),ui.rh(150),ui.rw(220),ui.rh(64));
        ui.addView(title);
    }

    private void setButtonAdd(ManualUI ui){

        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setColor(Color.parseColor("#FFFFFF"));
        shape.setCornerRadius(30);

        View containerButtonAdd = new View(this);
        containerButtonAdd.setOnClickListener(this);
        containerButtonAdd.setBackgroundColor(Color.parseColor("#FFFFFF"));
        containerButtonAdd.setId(R.id.MenuAdd);
        containerButtonAdd.setBackground(shape);

        ui.setPosition(containerButtonAdd,ui.rw(34),ui.rh(278),ui.rw(135),ui.rh(135));
        ui.addView(containerButtonAdd);

        ImageView imageButton1 = new ImageView(this);
        imageButton1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.addmenu));
        ui.setPosition(imageButton1,ui.rw(46),ui.rh(22),ui.rw(42),ui.rh(42));
        ui.addFrom(imageButton1,containerButtonAdd);

        TextView textButton1 = new TextView(this);
        textButton1.setText("Ajouter un\n restaurant");
        textButton1.setTextColor(Color.parseColor("#3F70C3"));
        textButton1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textButton1.setTextSize(TypedValue.COMPLEX_UNIT_PX,ui.rw(15));
        ui.setPosition(textButton1,ui.rw(0),ui.rh(79),ui.rw(135),ui.rh(40));
        ui.addFrom(textButton1,containerButtonAdd);
    }

    private void setButtonDelete(ManualUI ui){

        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setColor(Color.parseColor("#FFFFFF"));
        shape.setCornerRadius(30);

        View containerButtonDelete = new View(this);
        containerButtonDelete.setOnClickListener(this);
        containerButtonDelete.setBackgroundColor(Color.parseColor("#FFFFFF"));
        containerButtonDelete.setId(R.id.MenuDelete);
        containerButtonDelete.setBackground(shape);

        ui.setPosition(containerButtonDelete,ui.rw(209),ui.rh(278),ui.rw(135),ui.rh(135));
        ui.addView(containerButtonDelete);

        ImageView imageButton2 = new ImageView(this);
        imageButton2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.deletemenu));
        ui.setPosition(imageButton2,ui.rw(46),ui.rh(22),ui.rw(42),ui.rh(42));
        ui.addFrom(imageButton2,containerButtonDelete);

        TextView textButton2 = new TextView(this);
        textButton2.setText("Supprimer un\n restaurant");
        textButton2.setTextColor(Color.parseColor("#3F70C3"));
        textButton2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textButton2.setTextSize(TypedValue.COMPLEX_UNIT_PX,ui.rw(15));
        ui.setPosition(textButton2,ui.rw(0),ui.rh(79),ui.rw(135),ui.rh(40));
        ui.addFrom(textButton2,containerButtonDelete);
    }

    private void setButtonModify(ManualUI ui){

        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setColor(Color.parseColor("#FFFFFF"));
        shape.setCornerRadius(30);

        View containerButtonModify = new View(this);
        containerButtonModify.setBackgroundColor(Color.parseColor("#FFFFFF"));
        containerButtonModify.setId(R.id.MenuEdit);
        containerButtonModify.setBackground(shape);
        containerButtonModify.setOnClickListener(this);

        ui.setPosition(containerButtonModify,ui.rw(35),ui.rh(441),ui.rw(135),ui.rh(135));
        ui.addView(containerButtonModify);

        ImageView imageButton3 = new ImageView(this);
        imageButton3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.editmenu));
        ui.setPosition(imageButton3,ui.rw(46),ui.rh(22),ui.rw(42),ui.rh(42));
        ui.addFrom(imageButton3,containerButtonModify);

        TextView textButton3 = new TextView(this);
        textButton3.setText("Modifier un\n restaurant");
        textButton3.setTextColor(Color.parseColor("#3F70C3"));
        textButton3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textButton3.setTextSize(TypedValue.COMPLEX_UNIT_PX,ui.rw(15));
        ui.setPosition(textButton3,ui.rw(0),ui.rh(79),ui.rw(135),ui.rh(40));
        ui.addFrom(textButton3,containerButtonModify);
    }

    private void setButtonListe(ManualUI ui){

        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setColor(Color.parseColor("#FFFFFF"));
        shape.setCornerRadius(30);

        View containerButtonListe = new View(this);
        containerButtonListe.setBackgroundColor(Color.parseColor("#FFFFFF"));
        containerButtonListe.setId(R.id.MenuListe);
        containerButtonListe.setOnClickListener(this);
        containerButtonListe.setBackground(shape);

        ui.setPosition(containerButtonListe,ui.rw(209),ui.rh(441),ui.rw(135),ui.rh(135));
        ui.addView(containerButtonListe);

        ImageView imageButton4 = new ImageView(this);
        imageButton4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.listemenu));
        ui.setPosition(imageButton4,ui.rw(46),ui.rh(22),ui.rw(42),ui.rh(42));
        ui.addFrom(imageButton4,containerButtonListe);x

        TextView textButton4 = new TextView(this);
        textButton4.setText("Liste des\n restaurants");
        textButton4.setTextColor(Color.parseColor("#3F70C3"));
        textButton4.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textButton4.setTextSize(TypedValue.COMPLEX_UNIT_PX,ui.rw(15));
        ui.setPosition(textButton4,ui.rw(0),ui.rh(79),ui.rw(135),ui.rh(40));
        ui.addFrom(textButton4,containerButtonListe);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.MenuAdd){
            Intent intent = new Intent(this, AddRestaurant.class);
            startActivity(intent);
        }
        else if(v.getId() == R.id.MenuDelete){
            Intent intent = new Intent(this, DeleteRestaurant.class);
            startActivity(intent);
        }
        else if(v.getId() == R.id.MenuEdit){
            Intent intent = new Intent(this, ChooseRestaurant.class);
            startActivity(intent);
        }
        else if(v.getId() == R.id.MenuListe){
            Intent intent = new Intent(this, ListeRestaurant.class);
            startActivity(intent);
    }
    }
}
