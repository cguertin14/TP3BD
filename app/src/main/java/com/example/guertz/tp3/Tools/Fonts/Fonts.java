package com.example.guertz.tp3.Tools.Fonts;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by Charles on 2017-06-16.
 */

public class Fonts {
    public static Typeface LatoBold(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "Lato-Bold.ttf");
    }

    public static Typeface LatoRegular(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "Lato-Regular.ttf");
    }

    public static Typeface LatoBlack(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "Lato-Black.ttf");
    }

    public static Typeface LatoLight(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "Lato-Light.ttf");
    }

    public static Typeface MontserratBold(Context context) {
        return Typeface.createFromAsset(context.getAssets(),"Montserrat-Bold.otf");
    }
}
