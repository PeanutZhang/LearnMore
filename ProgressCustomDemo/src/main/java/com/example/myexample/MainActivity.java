package com.example.myexample;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

	private CustomProgress dialog;
	private  Handler mhandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		dialog = CustomProgress.cpCreate(MainActivity.this, "正在加载...，昂稍后", true, null);
		dialog.cpShow();
		findViewById(R.id.startydialgo).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Log.e("zyh","onstart is going.....");
			     dialog = CustomProgress.cpCreate(MainActivity.this,"改变后的的",true,null);

				dialog.cpShow();
			}
		});
		findViewById(R.id.stopdialog).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Log.e("zyh","onstop is going.....");
              dialog.cpDismiss();
			}
		});
        findViewById(R.id.changemsg).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				dialog.setMessage(null);
				dialog.show();

			}
		});
		mhandler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
                dialog.cpDismiss();
				Log.e("zyh","handlerMESG IS GOING....");
			}
		};

		mhandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				mhandler.sendEmptyMessage(1);
				Log.e("zyh","mhandler sed message.....");
			}
		},1800);

	}


}
