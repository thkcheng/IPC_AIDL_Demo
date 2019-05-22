package com.demo.twoapp;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import app.demo.aidl_library.IUserCallBack;
import app.demo.aidl_library.User;

public class MainTwoActivity extends AppCompatActivity {

    IUserCallBack callBack = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnSend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("aidl_user_service");
                intent.setPackage("com.demo.twoapp"); //发送端pkg
                boolean isbind = bindService(intent, conn, BIND_AUTO_CREATE);
                Log.e("XXX", "发送数据 isbind == " + isbind);
            }
        });
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            callBack = IUserCallBack.Stub.asInterface(service);
            try {
                User user = new User();
                user.userId = 101;
                user.userName = "张三";
                user.userSex = "男";
                callBack.addUser(user);
                Log.e("XXX", "发送数据  == " + user.toString());
                Toast.makeText(MainTwoActivity.this, "发送成功, 数据看Log日志", Toast.LENGTH_LONG).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            callBack = null;
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unbindService(conn);
    }
}
