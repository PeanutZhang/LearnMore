package com.jieyi.learnmore.customview;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jieyi.learnmore.R;

import java.util.ArrayList;
import java.util.Random;

public class PieChartActivity extends AppCompatActivity {

    private ArrayList<PieData> mdata;
    private PieChartView pieChartView;
    private EditText meditText;
    private LeafLoadingView mLeafLoadingView;
    private int mProgress = 0;
    private TextView mProgressText;
    private View mFanView;
    private static final int REFRESH_PROGRESS = 0x10;

    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESH_PROGRESS:
                    if (mProgress < 40) {
                        mProgress += 1;
                        // 随机800ms以内刷新一次
                        mHandler.sendEmptyMessageDelayed(REFRESH_PROGRESS,
                                new Random().nextInt(800));
                        mLeafLoadingView.setProgress(mProgress);
                    } else {
                        mProgress += 1;
                        // 随机1200ms以内刷新一次
                        mHandler.sendEmptyMessageDelayed(REFRESH_PROGRESS,
                                new Random().nextInt(1200));
                        mLeafLoadingView.setProgress(mProgress);

                    }
                    break;

                default:
                    break;
            }
        };
    };
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
        mProgressText = (TextView) findViewById(R.id.text_progress);
        mLeafLoadingView = (LeafLoadingView) findViewById(R.id.leaf_loading);
        findViewById(R.id.add_progress).setOnClickListener(v->{
            mProgress++;
            mLeafLoadingView.setProgress(mProgress);
            mProgressText.setText(String.valueOf(mProgress));
        });
        findViewById(R.id.clear_progress).setOnClickListener(v->{
            mLeafLoadingView.setProgress(0);
            mHandler.removeCallbacksAndMessages(null);
            mProgress = 0;
        });

        mFanView = findViewById(R.id.fan_pic);
        RotateAnimation rotateAnimation = AnimationUtils.initRotateAnimation(false, 1500, true,
                Animation.INFINITE);
        mFanView.startAnimation(rotateAnimation);
        mHandler.sendEmptyMessageDelayed(REFRESH_PROGRESS,3000);
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
