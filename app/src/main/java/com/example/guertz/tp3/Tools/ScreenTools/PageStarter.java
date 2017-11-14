package com.example.guertz.tp3.Tools.ScreenTools;

import android.app.Activity;
import android.content.Intent;

import com.example.guertz.tp3.R;

/**
 * Created by Charles on 2017-06-22.
 */

public class PageStarter
{
    public static void startActivityEnter(Activity context, Intent i) {
        context.startActivity(i);
        context.overridePendingTransition(R.anim.enter,R.anim.exit);
    }

    public static void startActivityEnter(Activity context, Class activity) {
        context.startActivity(new Intent(context, activity));
        context.overridePendingTransition(R.anim.enter, R.anim.exit);
    }

    public static void startActivityBack(Activity context, Class activity) {
        context.startActivity(new Intent(context, activity));
        context.overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }

    public static void finish(Activity context) {
        context.finish();
        context.overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }

    public static void startActivityFromTop(Activity context, Class activity) {
        context.startActivity(new Intent(context, activity));
        context.overridePendingTransition(R.anim.slide_down,R.anim.no_anim);
    }
}
