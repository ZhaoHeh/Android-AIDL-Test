package com.faustusz.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "AIDLTest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate");

        TextView tv = findViewById(R.id.my_text_view);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "TextView onClick.");
                mHandler.sendEmptyMessage(0);
            }
        });

        Intent intent = new Intent(this, MyService.class);
        intent.setAction(MyService.class.getName());
        bindService(intent, mConn, Context.BIND_AUTO_CREATE);
    }

    MyHandler mHandler = new MyHandler();

    IMyAidlInterface iRemoteService;
    private ServiceConnection mConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "MainActivity onServiceConnected.");
            iRemoteService = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iRemoteService = null;
        }
    };

    private class MyHandler extends Handler {

        @Override
        public void handleMessage(@NonNull Message msg) {
            Log.d(TAG, "MyHandler handleMessage.");
            try {
                String str = "";
                iRemoteService.basicTypes(32, 64, true, 0.32f, 0.64, str);
                Log.d(TAG, str);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}