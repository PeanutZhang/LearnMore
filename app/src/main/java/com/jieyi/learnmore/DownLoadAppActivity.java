package com.jieyi.learnmore;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import me.shenfan.updateapp.UpdateService;

public class DownLoadAppActivity extends AppCompatActivity {
    private static final String URL = "http://27.221.81.15/dd.myapp.com/16891/63C4DA61823B87026BBC8C22BBBE212F.apk?mkey=575e443c53406290&f=8b5d&c=0&fsname=com.daimajia.gold_3.2.0_80.apk&p=.apk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_load_app);

        findViewById(R.id.updataAPP).setOnClickListener(v -> {
            UpdateService.Builder.create(URL)
                    .build(this);
        });
        findViewById(R.id.updataAPP2).setOnClickListener(v -> {

        });
        findViewById(R.id.updataAPP3).setOnClickListener(v -> {

        });
    }
}
