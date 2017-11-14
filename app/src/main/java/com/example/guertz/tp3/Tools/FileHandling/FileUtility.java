package com.example.guertz.tp3.Tools.FileHandling;

import android.content.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by guertz on 08/08/17.
 */

public class FileUtility {

    public static ArrayList<String> read(Context ctx,String filename) {
        ArrayList<String> retour = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(ctx.getAssets().open(filename)));
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                retour.add(mLine);
            }
            return retour;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
