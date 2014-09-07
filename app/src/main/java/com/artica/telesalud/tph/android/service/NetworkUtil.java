package com.artica.telesalud.tph.android.service;

/**
 * Created by interoperabilidad on 23/05/14.
 */
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil {

    public enum NetworkType{
        TYPE_WIFI("Wifi enabled 1"), TYPE_MOBILE("Mobile data enabled 1"), TYPE_NOT_CONNECTED("Not connected to Internet 1");
        private String description;
        private NetworkType(String description){
            this.description = description;
        }
    };

    public static NetworkType getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return NetworkType.TYPE_WIFI;

            if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return NetworkType.TYPE_MOBILE;
        }
        return NetworkType.TYPE_NOT_CONNECTED;
    }

    public static String getConnectivityStatusString(Context context) {
        NetworkType conn = NetworkUtil.getConnectivityStatus(context);
        return conn.description;
    }
}