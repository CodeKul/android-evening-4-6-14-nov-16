package com.codekul.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private static final int REQ_ENABLE_BT =4689 ;
    private BluetoothAdapter adapter;

    private BroadcastReceiver receiverDeviceFound = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("@codekul", "Device Found");

            BluetoothDevice remoteDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            Log.i("@codekul","Remote Device Name - "+remoteDevice.getName());
            Log.i("@codekul","Remote Device Address - "+remoteDevice.getAddress());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = BluetoothAdapter.getDefaultAdapter();
        Log.i("@codekul","My Address - "+adapter.getAddress());

        if(!isBluetoothAvailable()){
            Toast.makeText(this,"Bluetooth Hardware not available",Toast.LENGTH_SHORT).show();
            finish();
        }

        registerReceiver(receiverDeviceFound, new IntentFilter(BluetoothDevice.ACTION_FOUND));

        findViewById(R.id.btnEnable).setOnClickListener(this::enableBluetooth);
        findViewById(R.id.btnConnectedDevices).setOnClickListener(this::connectedDevices);
        findViewById(R.id.btnStartDiscovery).setOnClickListener(this::discoverDevices);
        findViewById(R.id.btnMakeDiscoverable).setOnClickListener(this::makeDiscoverable);
        findViewById(R.id.btnServer).setOnClickListener(this::server);
        findViewById(R.id.btnClient).setOnClickListener(this::client);
    }

    private void client(View view) {
        new Thread(this::clientRunnable).start();
    }

    private void server(View view) {
        new Thread(this::serverRunnable).start();
    }

    private void clientRunnable(){
        try {
            BluetoothDevice remoteDevice = adapter.getRemoteDevice("60:D9:A0:F3:25:BB");
            BluetoothSocket socket = remoteDevice.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"));
            socket.connect();
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            String fromServer = dis.readUTF();
            Log.i("@codekul","Server Says - "+fromServer);
            //dis.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void serverRunnable() {
        try {
            BluetoothServerSocket bss = adapter.listenUsingRfcommWithServiceRecord("myService", UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"));
            BluetoothSocket bs = bss.accept();
            DataOutputStream dos = new DataOutputStream(bs.getOutputStream());
            dos.writeUTF("This is hello from server");
           // dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void makeDiscoverable(View view) {
        Intent discoverableIntent =
                new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        startActivity(discoverableIntent);
    }

    private void discoverDevices(View view) {
        adapter.startDiscovery();
    }

    private void connectedDevices(View view) {
        Set<BluetoothDevice> pairedDevices = adapter.getBondedDevices();

        for (BluetoothDevice pairedDevice : pairedDevices) {
            Log.i("@codekul"," Name - "+pairedDevice.getName());
            Log.i("@codekul"," Address - "+pairedDevice.getAddress());
        }
    }

    private Boolean isBluetoothAvailable(){
        return adapter != null;
    }

    private void enableBluetooth(View view) {
        startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE),REQ_ENABLE_BT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQ_ENABLE_BT){
            if(resultCode == RESULT_OK) mt("Enabled");
            else mt("Not Enabled");
        }
    }

    private void mt(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiverDeviceFound);
    }
}
