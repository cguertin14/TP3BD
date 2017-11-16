package com.example.guertz.tp3.Models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.guertz.tp3.Activities.DeleteRestaurant;
import com.example.guertz.tp3.Activities.HomeActivity;
import com.example.guertz.tp3.Models.Restaurant;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guijet on 2017-11-10.
 */

public abstract class DBHelper{

    private List<Restaurant> listAllResto;
    public static void addRestaurants(SQLiteDatabase db,String nom, String adresse, String qualiteBouffe, String qualiteService, Float prixMoyen, int nbEtoiles){

        try{
            db.execSQL("insert into Resto(nomRestaurant,adresseRestaurant,qualiteBouffe,qualiteService,prixMoyen,nbEtoiles) values (" + "'" + nom + "'," + "'" + adresse + "'," + "'" + qualiteBouffe + "'," + "'" +  qualiteService + "'," + prixMoyen + "," + nbEtoiles + " );");
        }
        catch (Exception e){
            Log.i("Error",e.getMessage());
        }
    }
    public static void deleteRestaurant(SQLiteDatabase db, int idRestaurant){
        try{
            db.execSQL("delete from Resto where idResaurant =" + idRestaurant + ";");
        }
        catch (Exception e){
            Log.i("Error",e.getMessage());
        }
    }

    public static void updateRestaurant(SQLiteDatabase db,int idRestaurant, int nbEtoiles, String qualiteBouffe, String qualiteService){
        try{
            db.execSQL("update Resto set nbEtoiles =" + nbEtoiles +", qualiteBouffe =" + qualiteBouffe + ", qualiteService = " + qualiteService + "where idResaurant =" + idRestaurant + ";" );
        }
        catch (Exception e){
            Log.i("Error",e.getMessage());
        }
    }

    public static List<Restaurant> getAllRestaurant(SQLiteDatabase bd){

        List<Restaurant> listRestaurants = new ArrayList<>();
        Cursor c = bd.rawQuery("SELECT * FROM Resto", null);

        if(c != null) {

            int idIndex = c.getColumnIndex("idResaurant");
            int NameIndex = c.getColumnIndex("nomRestaurant");
            int Adresseindex = c.getColumnIndex("adresseRestaurant");
            int QualiteBouffeIndex = c.getColumnIndex("qualiteBouffe");
            int QualiteServiceIndex = c.getColumnIndex("qualiteService");
            int PrixMoyenIndex = c.getColumnIndex("prixMoyen");
            int NbEtoileINdex = c.getColumnIndex("nbEtoiles");

            if (c.moveToFirst()) {
                do {
                    listRestaurants.add(new Restaurant(c.getInt(idIndex), c.getString(NameIndex), c.getString(Adresseindex), c.getString(QualiteBouffeIndex), c.getString(QualiteServiceIndex), c.getFloat(PrixMoyenIndex), c.getInt(NbEtoileINdex)));
                } while (c.moveToNext());
            }
        }

        return listRestaurants;
    }



    public static List<Restaurant> getRestaurantsByStars(SQLiteDatabase bd, int nbEtoile){

        List<Restaurant> listRestaurants = new ArrayList<>();
        Cursor c = bd.rawQuery("SELECT * FROM Resto where nbEtoiles =" + nbEtoile +";", null);

        if(c != null){

            int idIndex = c.getColumnIndex("idResaurant");
            int NameIndex = c.getColumnIndex("nomRestaurant");
            int Adresseindex = c.getColumnIndex("adresseRestaurant");
            int QualiteBouffeIndex = c.getColumnIndex("qualiteBouffe");
            int QualiteServiceIndex = c.getColumnIndex("qualiteService");
            int PrixMoyenIndex = c.getColumnIndex("prixMoyen");
            int NbEtoileINdex = c.getColumnIndex("nbEtoiles");

            if(c.moveToFirst()) {
                do {
                    listRestaurants.add(new Restaurant(c.getInt(idIndex), c.getString(NameIndex), c.getString(Adresseindex), c.getString(QualiteBouffeIndex), c.getString(QualiteServiceIndex), c.getFloat(PrixMoyenIndex), c.getInt(NbEtoileINdex)));
                } while (c.moveToNext());
            }
        }
        return listRestaurants;
    }

}
