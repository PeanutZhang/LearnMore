package com.jieyi.learnmore.customview;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by zyh on 2017/9/19.
 */

public class UiUtils {

public static int getScreenWidth(Context context){

    DisplayMetrics dm = new DisplayMetrics();
    ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(dm);
    return  dm.widthPixels;
}

 public static int dip2px(Context context,int dip){
     return (int) (dip * getScreenDensity(context)+0.5f);
 }


 public static float getScreenDensity(Context context){

     try {
         DisplayMetrics dm = new DisplayMetrics();
         ((WindowManager ) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(dm);
         return dm.density;
     } catch (Exception e) {
         e.printStackTrace();
        return DisplayMetrics.DENSITY_DEFAULT;
     }
 }


}
