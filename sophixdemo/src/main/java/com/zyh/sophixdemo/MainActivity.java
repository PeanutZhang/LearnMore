package com.zyh.sophixdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.taobao.sophix.SophixManager;

public class MainActivity extends AppCompatActivity {

    protected Button btnRequest;
    protected Button btnBug;
    protected TextView btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        btnRequest = (Button) findViewById(R.id.btn_request);
        btnBug = (Button) findViewById(R.id.btn_bug);
        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SophixManager.getInstance().queryAndLoadNewPatch();
            }
        });
        btnBug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//               btn.setText("");//bug crash
                Toast.makeText(getBaseContext(), "修复后弹出消息", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
