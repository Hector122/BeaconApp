package com.app.beacon.activists;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.net.wifi.ScanResult;

import com.app.beacon.R;

import java.util.List;

/**
 * Created by hector castillo on 11/4/16.
 */
public class MacAddress extends AppCompatActivity implements View.OnClickListener {
    Button macAdress, loginRouter;
    TextView macAdressView, loginRouterView;

    public static final int NETWORK_STATE_SERVICE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad);

        intializerVariables();
    }

    public void intializerVariables() {
        macAdress = (Button) findViewById(R.id.button_mac_address);
        macAdress.setOnClickListener(this);

        macAdressView = (TextView) findViewById(R.id.text_view_mac_address);

        loginRouter = (Button) findViewById(R.id.button_login_router);
        loginRouterView = (TextView) findViewById(R.id.text_login_router);
    }

    //View android 6 permission.

    //TODO:
    public void showMacAddress() {
        if (android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            // only for gingerbread and newer versions
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                //Should we show an explanation
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                    // Show an expanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.
                    Toast.makeText(this, "Rason por la cual se necesita el permiso en la aplicaion. ", Toast.LENGTH_SHORT).show();

                } else {
                    // No explanation needed, we can request the permission.
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            NETWORK_STATE_SERVICE);

                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                }
            } else {
                macAdressView.setText(getMacAddressAfterMashmellow());
            }
        } else {
            macAdressView.setText(getMacAddress());
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case NETWORK_STATE_SERVICE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    macAdressView.setText(getMacAddressAfterMashmellow());
                }
                return;
        }
    }

    private String getMacAddress() {
        WifiManager wimanager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        String macAddress = wimanager.getConnectionInfo().getMacAddress();

        if (macAddress == null) {
            macAddress = "Device don't have mac address or wi-fi is disabled";
        }

        return macAddress;
    }

    private String getMacAddressAfterMashmellow() {
        WifiManager wimanager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        List<ScanResult> scanResults =  wimanager.getScanResults();

        if (scanResults == null) {
            return "Device don't have mac address or wi-fi is disabled";
        }

        String result = "";
        for (ScanResult scanR: scanResults) {
            result += "\n" + scanR.BSSID;
        }

        return result;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_mac_address:
                showMacAddress();
                break;
        }
    }
}
