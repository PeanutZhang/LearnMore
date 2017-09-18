package com.zyh.sophixdemo;

import android.app.Application;
import android.util.Log;

import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;

/**
 * Created by zyh on 2017/8/20.
 */

public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

    initHotfix();

    }


    private  void initHotfix(){

        String appVersion;
        try {
            appVersion = this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
        } catch (Exception e) {
            appVersion = "1.0.0";
        }
        SophixManager.getInstance().setContext(this)
                .setAppVersion(appVersion)
                .setAesKey(null)
               // .setSecretMetaData("24591495-1","6a988d6fd722e58dbe74a6b07a90dceb","MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCb3hctOyNB5xXJGOSmvYA4jowe6YViLV8eKhjlcJlov3a20emMaIcZwFLd6Q7nFkHRo9/H2zyGmiPMGbu+lu2lIQVgl0/chHQTxKTw5krMgH884LD8oEfqakj2yvg3FusiIhW2Sa07gVlotp4GMmq0X0j16mKAbONH9tnXOozyIXke/6LfaQxqJi3+jrYKxk9aEOGsnE7PpQmBuPmJ5BHHhAYIm6X1NSZrbo2kvxaeZ1OrWGY3p8aT6emFbwIS7Q2EVK7kMTqqQOtPLCitb7gBaZTW2S6HihWRju5+SoD55amx7mqwhUt5jTd8wiBJ8QWClzgCO9eb+oIs69ECuPcdAgMBAAECggEAGbu9D9anp4XDbwQRHAZFWREPyTq8rszW22AP5DGG4mhZ7S0S5f+L7pB2HfUQZM6j5zyrAZAMmhxwtI27FWhNq+mbvaq9oKatk2Xv5WXgC4tbdm2SSuMbfHQ3v6ca+91ozKXb99CKLTzZyhP8WwGP5F+m6BLeWF2h2IvEw2gDRSZGFibvFXycad6G27FZWQd2hYiR0nb18QGm9Jtcq4olPFTgFAgZ9S+I2uXgXOks01E1LVcfWQhT7WHhd8tbDp7olXwx25tNOVNVxYxgLbuuEHcKcojOm38Jwb5CZzXYgm7E4p9NPr8pr9j+zLvjgZvMPjmGQtRnvC2TkUNNf2Y0AQKBgQD4T0bFn82Q4HvQ8IxcdROMJxHwf2744lltQ6LtBYuZtZ7OIZoFAAsOomsXQ92/ye03XbcdHv6vOl5U56SH34iYPx9I4KwmeROXLAQCKV6IN+9SLSuegq+4MFtJkdWyneEoYYW5Ck+IOWSTgUWn46jgjwZwvMruEw1XSQBovx5HXQKBgQCgseLzGaoDGPmPQXz6am3H6FjFMSIomGnNHiNEqgpHRZz/qj5XG733XGW3PDfkrttjaXJsNDVTBuHTCfHHqe3kvxX+Y1y+Jl/7cG0yS8KLxfMkDpTVqp9f6p40dQPoYPS3aj27EJNBnirBL2E1W/uJgWNvP4Kk0BB5X7wd6HkywQKBgQDCABY4IvFVpr218aqINgRutLvEXZvTIe1ie7yYWIPINOj8/a27CFybM3Rd+e0PgfEvF64QN34eY4j95vlaGuA6qt6Dbt2xNVmD+6SqF64+2QzG9Hmm303wpv87jJAcfFNE3GTAvHiE4upcgFxunmT8BWKvxCD8PrBmqKeELwW2FQKBgAzwZeLHNT5Mm4OidUrAmRhYqBUwsJxuMU6314Um3+7/NvYLIjvnWvN4+CO58JI0Viv6CKGXzpdh3XsduN8n2qTpkf7Kx2P0ORRI6DH9kaupg7tqhSdzNqQFgzmcFnV2oR3HqzLN0d2du7mUdBJU1rsqtPP3PDDDJCBxyfbqih/BAoGALiNA36nb/dBnVZlndaeQK2uI19E38+0V64euJnUqn78SjjMrUeZLtDdjeNf8217HrETkc2k34IB6KTa6A1ISv47GXC5lRSokCy05XAbcfFe6+kbRYomRUCmYKFRl0apczw6jKfblAZ614cn5BcxPquZxi8RNZvQH/iIM+hX0e2U=")
                .setEnableDebug(true)
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
//                        PatchStatus
                        String msg = new StringBuilder("").append("Mode:").append(mode)
                                .append(" Code:").append(code)
                                .append(" Info:").append(info)
                                .append(" HandlePatchVersion:").append(handlePatchVersion).toString();
                        Log.e("zyh","initSophixManger-- ---"+msg);
                    }
                }).initialize();


    }
}
