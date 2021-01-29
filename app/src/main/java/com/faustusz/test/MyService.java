package com.faustusz.test;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.util.Log;

public class MyService extends Service {

    private static final String TAG = "AIDLTestService";
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "MyService onBind.");
        return mBinder;
    }

    private final IMyAidlInterface.Stub mBinder = new IMyAidlInterface.Stub() {
        public int getPid() {
            return Process.myPid();
        }
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
                               double aDouble, String aString) {
            aString = "int: " + anInt+ ", long: " + aLong + ", boolean: " + aBoolean + ", " +
                    "float: " + aFloat + ", double: " + aDouble + ", String: " + aString;
            Log.d(TAG, aString);
        }
    };
}
