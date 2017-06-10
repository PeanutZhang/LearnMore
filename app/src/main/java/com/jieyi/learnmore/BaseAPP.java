package com.jieyi.learnmore;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

/**
 * ***********************************************
 * **                  _oo0oo_                  **
 * **                 o8888888o                 **
 * **                 88" . "88                 **
 * **                 (| -_- |)                 **
 * **                 0\  =  /0                 **
 * **               ___/'---'\___               **
 * **            .' \\\|     |// '.             **
 * **           / \\\|||  :  |||// \\           **
 * **          / _ ||||| -:- |||||- \\          **
 * **          | |  \\\\  -  /// |   |          **
 * **          | \_|  ''\---/''  |_/ |          **
 * **          \  .-\__  '-'  __/-.  /          **
 * **        ___'. .'  /--.--\  '. .'___        **
 * **     ."" '<  '.___\_<|>_/___.' >'  "".     **
 * **    | | : '-  \'.;'\ _ /';.'/ - ' : | |    **
 * **    \  \ '_.   \_ __\ /__ _/   .-' /  /    **
 * **====='-.____'.___ \_____/___.-'____.-'=====**
 * **                  '=---='                  **
 * ***********************************************
 * **              佛祖保佑  永无Bug              **
 * ***********************************************
 */

public class BaseAPP extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


     registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
//         @Override
//         public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
//            Log.e("zyh","---------"+activity.getClass().getSimpleName());
//             Log.e("zyh","----R.id.toobar---"+activity.findViewById(R.id.toolbar).getClass().getSimpleName());
//            if(activity.findViewById(R.id.toolbar) !=null){
//
//                Log.e("zyh","activity activity.findViewById(R.id.toolbar) !=null   is going----");
//                if(activity instanceof  AppCompatActivity) {
//                    ((AppCompatActivity) activity).setSupportActionBar((Toolbar) activity.findViewById(R.id.toolbar));
//                    ((AppCompatActivity) activity).getSupportActionBar().setDisplayShowTitleEnabled(false);
//                }else {
//
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                        activity.setActionBar((android.widget.Toolbar) activity.findViewById(R.id.toolbar));
//                        activity.getActionBar().setDisplayShowTitleEnabled(false);
//                    }
//                }
//
//                if(activity instanceof MainActivity){
//                    activity.findViewById(R.id.toolbar).setVisibility(View.GONE);
//
//                }
//
//            }
//
//
//
//             if (activity.findViewById(R.id.toolbar_title) != null) { //找到 Toolbar 的标题栏并设置标题名
//                 ((TextView) activity.findViewById(R.id.toolbar_title)).setText(activity.getTitle());
//                 Log.e("zyh","------setTite-----");
//             }
//             if (activity.findViewById(R.id.btn_toolbar_back) != null) { //找到 Toolbar 的返回按钮,并且设置点击事件,点击关闭这个 Activity
//                 Log.e("zyh","------back title-----");
//
//                 activity.findViewById(R.id.btn_toolbar_back).setOnClickListener(v -> {
//                             activity.finish();
//                         }
//                 );
//
//
//             }
//         }

         @Override
         public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
             //这里全局给Activity设置toolbar和title,你想象力有多丰富,这里就有多强大,以前放到BaseActivity的操作都可以放到这里
             if (activity.findViewById(R.id.toolbar) != null) { //找到 Toolbar 并且替换 Actionbar
                 if (activity instanceof AppCompatActivity) {

                     ((AppCompatActivity) activity).setSupportActionBar((Toolbar) activity.findViewById(R.id.toolbar));

                     ((AppCompatActivity) activity).getSupportActionBar().setDisplayShowTitleEnabled(false);
                 } else {
                     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                         activity.setActionBar((android.widget.Toolbar) activity.findViewById(R.id.toolbar));
                         activity.getActionBar().setDisplayShowTitleEnabled(false);
                     }
                 }
             }
             if (activity.findViewById(R.id.toolbar_title) != null) { //找到 Toolbar 的标题栏并设置标题名
                 ((TextView) activity.findViewById(R.id.toolbar_title)).setText(activity.getTitle());
             }
             if (activity.findViewById(R.id.btn_toolbar_back) != null) { //找到 Toolbar 的返回按钮,并且设置点击事件,点击关闭这个 Activity
                 activity.findViewById(R.id.btn_toolbar_back).setOnClickListener(v -> {
                    activity.finish();
                 });
             }
         }

         @Override
         public void onActivityStarted(Activity activity) {

         }

         @Override
         public void onActivityResumed(Activity activity) {

         }

         @Override
         public void onActivityPaused(Activity activity) {

         }

         @Override
         public void onActivityStopped(Activity activity) {

         }

         @Override
         public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

         }

         @Override
         public void onActivityDestroyed(Activity activity) {

         }
     });




    }
}
