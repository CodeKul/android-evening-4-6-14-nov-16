package com.codekul.comman;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class MyService extends Service {

    private MyImpl impl = new MyImpl();

    @Override
    public IBinder onBind(Intent intent) {
        return impl;
    }
    private class MyImpl extends IComman.Stub {

        @Override
        public int calculate(int num1, int num2) throws RemoteException {
            return num1 + num2;
        }
    }
}
