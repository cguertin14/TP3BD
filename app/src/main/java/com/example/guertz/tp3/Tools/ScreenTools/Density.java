package com.example.guertz.tp3.Tools.ScreenTools;

import android.app.Activity;
import android.content.Context;

public class Density extends Activity
{
    public static float get(Context context)
    {
        return context.getResources().getDisplayMetrics().density;
    }
}
