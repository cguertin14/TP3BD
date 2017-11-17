package com.example.guertz.tp3.Models;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.guertz.tp3.Activities.HomeActivity;

import java.sql.PreparedStatement;
import java.util.List;

/**
 * Created by Guijet on 2017-11-16.
 */

public abstract class BDHelperOracle {

    public static void importDBintoOracle(List<Restaurant> listeAllRestaurants,Context ctx){
        for (Restaurant x:listeAllRestaurants) {
            try {
                String sql = "insert into Restaurants values (increment_idRestaurant.nextval,?,?,?,?,?,?)";
                PreparedStatement stm = HomeActivity.conn_.prepareStatement(sql);
                stm.setString(1,x.getNom());
                stm.setString(2,x.getAdresse());
                stm.setString(3,x.getQualiteBouffe());
                stm.setString(4,x.getQualiteService());
                stm.setFloat(5,x.getPrixMoyen());
                stm.setInt(6,x.getNbEtoiles());
                stm.executeUpdate();

            } catch (java.sql.SQLException e) {
                Log.i("Erreur:", e.getMessage());
            }
        }
        killDBLite(ctx);
    }

    private static void killDBLite(Context ctx){
        ctx.deleteDatabase("TP3");
    }
}
