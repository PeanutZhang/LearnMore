package com.jieyi.learnmore;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Manifest;

public class PhoneBookActivity extends AppCompatActivity {

    private ListView mlistview;
    private Button btn_readcontact;
    private ArrayList<String> mdatas;
    private ArrayAdapter<String> madapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_book);

           initView();
    }

    private void initView() {

        btn_readcontact = (Button) findViewById(R.id.btn_readcontacts);
        mlistview = (ListView) findViewById(R.id.mlistview);
        mdatas = new ArrayList<>();
        mdatas.add("this the first msg");
        mdatas.add("this the second msg");
        mdatas.add("this the three msg");

        madapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mdatas);
        mlistview.setAdapter(madapter);
       btn_readcontact.setOnClickListener(new View.OnClickListener() {
           @RequiresApi(api = Build.VERSION_CODES.M)
           @Override
           public void onClick(View v) {
               Log.e("zyh","onClick is going------------------");
//               AndPermission.with(PhoneBookActivity.this)
//                       .requestCode(10)
//                       .permission(android.Manifest.permission.READ_CONTACTS)
//                       .rationale(new RationaleListener() {
//                           @Override
//                           public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
//
//                               AndPermission.rationaleDialog(PhoneBookActivity.this, rationale).show();
//                           }
//                       })
//                       .callback(new PermissionListener() {
//                           @Override
//                           public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
//                               readContacts();
//                           }
//
//                           @Override
//                           public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
//
//                           }
//                       })
//                       .start();

               if(ContextCompat.checkSelfPermission(getBaseContext(), android.Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
                   Log.e("zyh","没有联系人权限执行了-------------");
                       requestPermissions(new String[]{android.Manifest.permission.READ_CONTACTS},1);

               }else {
                   Log.e("zyh","有联系人权限执行了-------------");
                   readContacts();
               }

           }
       });


         mlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 Toast.makeText(PhoneBookActivity.this,"点击了"+mdatas.get(position),Toast.LENGTH_SHORT).show();
             }
         });
    }

   private void  readContacts(){


       try {
           Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
           Log.e("zyh","cursou==null is going-----" );
           if(cursor==null){
           }
           Log.e("zyh","cursor.getColumnCount()-----"+cursor.getCount());

           int index =0;
           if(cursor.getCount()<0 || cursor.getCount()==0){
               Toast.makeText(PhoneBookActivity.this,"您拒绝了读取联系人权限，是否重新开启",Toast.LENGTH_LONG).show();
           openSetting();

               return;
           }
           while (cursor.moveToNext()){

               String phoneName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
               String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

               mdatas.add("No."+(++index)+": "+phoneName+"\r\n"+number);

           }
       } catch (Exception e) {
          // e.printStackTrace();
           Log.e("zyh","-----try catch is going -------");
           Toast.makeText(PhoneBookActivity.this,"您拒绝了读取联系人权限，",Toast.LENGTH_LONG).show();
       }


            madapter.notifyDataSetChanged();

   }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == 1){

            if(permissions.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Log.e("zyh","授予访问联系人权限执行了-------------");
                readContacts();
            }else {
                Log.e("zyh","绝了访问通讯录权限权限执行了-------------");
                Toast.makeText(this,"您拒绝了访问通讯录权限",Toast.LENGTH_SHORT).show();
            }



        }


    }

    private  void  openSetting(){


        AlertDialog.Builder builder = new AlertDialog.Builder(PhoneBookActivity.this);
        builder.setTitle("打开设置页面");
        builder.setMessage("请打开权限设置页面，允许访问手机通讯录~~");
        builder.setNegativeButton("取消",null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.putExtra("packageName", BuildConfig.APPLICATION_ID);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                try {
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        });
        builder.show();


    }
}
