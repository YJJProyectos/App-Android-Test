package com.example.jyang.miappandroid;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by jyang on 19/3/2018.
 */

public class Test {

    public static void myToast(Context context) {

//        Activity activity = this;
        Toast.makeText(context, "Hola amigos", Toast.LENGTH_SHORT).show();
    }
}
