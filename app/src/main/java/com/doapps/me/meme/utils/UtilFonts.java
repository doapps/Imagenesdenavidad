package com.doapps.me.meme.utils;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by jonathan on 04/12/2014.
 */
public class UtilFonts {
    public static Typeface setHollyBerry(Context context){
        return Typeface.createFromAsset(context.getAssets(), "fonts/RemachineScript.ttf");
    }
    public static Typeface setHelveticaThin(Context context){
        return Typeface.createFromAsset(context.getAssets(), "fonts/Helvetica_Thin.otf");
    }
}
