package com.jieyi.learnmore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jieyi.learnmore.customview.CustomViewActivity;

public class MainActivity extends AppCompatActivity {

    private final String[] MTITLES ={"电话本","流式布局使用","下载并安装apk","自定义饼状图demo 入门学习"};
    private final Class[]  ACTS={PhoneBookActivity.class,AutoFlowLayoutActivity.class,
            DownLoadAppActivity.class,CustomViewActivity.class};
    private ListView mlistview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        initView();

    }

    private void initView() {



        mlistview = (ListView) findViewById(R.id.home_list);
        ArrayAdapter<String> madapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,MTITLES);
        mlistview.setAdapter(madapter);
        mlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(MainActivity.this,ACTS[position]);
                startActivity(it);
            }
        });



    }





}
