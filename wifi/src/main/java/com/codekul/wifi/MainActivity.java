package com.codekul.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private WifiManager manager;

    private BroadcastReceiver receiverWifi = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            WifiManager manager = (WifiManager) getSystemService(WIFI_SERVICE);
            List<ScanResult> scanedResults = manager.getScanResults();
            for (ScanResult scanedResult : scanedResults) {
                Log.i("@codekul","Scanned SSID - "+scanedResult.SSID);
                Log.i("@codekul","Scanned BSSID - "+scanedResult.BSSID);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = (WifiManager) getSystemService(WIFI_SERVICE);

        if(!manager.isWifiEnabled()) manager.setWifiEnabled(true);

        findViewById(R.id.btnConnected).setOnClickListener(v -> connectedWifi());
        findViewById(R.id.btnScan).setOnClickListener(v -> manager.startScan());
        findViewById(R.id.btnConnect).setOnClickListener(v -> connect("Rajans","12345678"));

        registerReceiver(receiverWifi, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
    }

    private void connect(String ssid, String password) {
        WifiConfiguration wifiConfig = new WifiConfiguration();
        wifiConfig.SSID = String.format("\"%s\"", ssid);
        wifiConfig.preSharedKey = String.format("\"%s\"", password);

        int netId = manager.addNetwork(wifiConfig);
        manager.disconnect();
        manager.enableNetwork(netId, true);
        manager.reconnect();
    }

    private void connectedWifi(){
        List<WifiConfiguration> connectedDevices = manager.getConfiguredNetworks();
        Log.i("@codekul", "Total Configured Devices "+connectedDevices.size());

        for (WifiConfiguration connectedDevice : connectedDevices) {
            Log.i("@codekul", "SSID - "+connectedDevice.SSID);
            Log.i("@codekul", "BSSID - "+connectedDevice.BSSID);
        }
    }
}
