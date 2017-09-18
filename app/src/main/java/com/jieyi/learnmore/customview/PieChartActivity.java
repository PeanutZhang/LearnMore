package com.jieyi.learnmore.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.jieyi.learnmore.R;

import java.util.ArrayList;
import java.util.Random;

public class PieChartActivity extends AppCompatActivity {

    private ArrayList<PieData> mdata;
    private PieChartView pieChartView;
    private EditText meditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);
        pieChartView = (PieChartView) findViewById(R.id.pie_view);

        findViewById(R.id.add_pieData).setOnClickListener(v -> {
            setPieData();
        });
        meditText = (EditText) findViewById(R.id.edit_setStartAngle);
        findViewById(R.id.set_starangle).setOnClickListener(v->{
             setStartAngle();

        });
    }

    private void setStartAngle(){

        String startAngle = meditText.getText().toString();
        if(TextUtils.isEmpty(startAngle)){
            Toast.makeText(getBaseContext(),"请输入开始绘制角度",Toast.LENGTH_SHORT).show();
            return;
        }
        int mstartAngle = Integer.parseInt(startAngle);
        pieChartView.setStartangle(mstartAngle);
    }

    private void setPieData(){


            PieData pieData1 = new PieData("10K",getRandomFloat());
            PieData pieData2 = new PieData("10-13k",getRandomFloat());
            PieData pieData3 = new PieData("13-15K",getRandomFloat());
            PieData pieData4 = new PieData("15-20K",getRandomFloat());
            PieData pieData5 = new PieData("20-35K",getRandomFloat());

            mdata = new ArrayList<>();
            mdata.add(pieData1);
            mdata.add(pieData2);
            mdata.add(pieData3);
            mdata.add(pieData4);
            mdata.add(pieData5);
        for (int i = 0; i < mdata.size(); i++) {
            Log.e("zyh","value--> ["+ i +"] "+mdata.get(i).getValue());
        }
         pieChartView.setPieData(mdata);
    }

    private float getRandomFloat(){

        Random  rdm = new Random();
        return  rdm.nextFloat()* 1000;


    }

}
