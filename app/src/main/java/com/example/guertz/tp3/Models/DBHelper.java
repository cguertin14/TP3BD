package com.example.guertz.tp3.Models;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.guertz.tp3.Models.Restaurant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guijet on 2017-11-10.
 */

public class DBHelper {


    public void addRestaurants(SQLiteDatabase db,String nom, String adresse, String qualiteBouffe, String qualiteService, Float prixMoyen, int nbEtoiles){

        try{
            db.execSQL("insert into Restaurants(nomRestaurant,adresseRestaurant,qualiteBouffe,qualiteService,prixMoyen,nbEtoiles) values ("+ nom + "," + adresse + "," + qualiteBouffe + "," + qualiteService + "," + prixMoyen + "," + nbEtoiles + " );");
        }
        catch (Exception e){
            Log.i("Error",e.getMessage());
        }
    }
    public void deleteRestaurant(SQLiteDatabase db, int idRestaurant){
        try{
            db.execSQL("delete from Restaurants where idResaurant =" + idRestaurant + ";");
        }
        catch (Exception e){
            Log.i("Error",e.getMessage());
        }
    }

    public void updateRestaurant(SQLiteDatabase db,int idRestaurant, int nbEtoiles, String qualiteBouffe, String qualiteService){
        try{
            db.execSQL("update Restaurants set nbEtoiles =" + nbEtoiles +", qualiteBouffe =" + qualiteBouffe + ", qualiteService = " + qualiteService + "where idResaurant =" + idRestaurant + ";" );
        }
        catch (Exception e){
            Log.i("Error",e.getMessage());
        }
    }

    public List<Restaurant> getRestaurantsByStars(SQLiteDatabase db, int nbEtoile){
        List<Restaurant> listRestaurants = new ArrayList<Restaurant>();
        Cursor c = db.rawQuery("SELECT * FROM Restaurants", null);

        int idIndex = c.getColumnIndex("event");
        int NameIndex = c.getColumnIndex("year");
        int Adresseindex = c.getColumnIndex("year");
        int QualiteBouffeIndex = c.getColumnIndex("year");
        int QualiteServiceIndex = c.getColumnIndex("year");
        int PrixMoyenIndex = c.getColumnIndex("year");
        int NbEtoileINdex = c.getColumnIndex("year");

        do{
            listRestaurants.add(new Restaurant(c.getInt(idIndex),c.getString(NameIndex),c.getString(Adresseindex),c.getString(QualiteBouffeIndex),c.getString(QualiteServiceIndex),c.getFloat(PrixMoyenIndex),c.getInt(NbEtoileINdex)));
        }while(c.moveToNext());

        return listRestaurants;
    }
}
