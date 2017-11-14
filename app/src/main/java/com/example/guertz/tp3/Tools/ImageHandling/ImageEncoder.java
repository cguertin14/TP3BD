package com.example.guertz.tp3.Tools.ImageHandling;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * Created by guertz on 04/09/17.
 */

public abstract class ImageEncoder {
    public static String getBase64Image(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        final String encoded = Base64.encodeToString(byteArray, Base64.NO_WRAP);
        return encoded;
    }
}
