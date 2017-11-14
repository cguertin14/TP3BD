package com.example.guertz.tp3.Tools.ScreenTools;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by guertz on 25/07/17.
 */

public class Focus {

    public static void request(Context ctx, View view) {
        if (view.requestFocus()) {
            ((Activity)ctx).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}
