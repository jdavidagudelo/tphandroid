package com.artica.telesalud.tph.android.activity;


import android.app.Activity;
import android.support.v4.app.FragmentActivity;

import com.artica.telesalud.tph.android.dao.DatabaseHelper;
import com.artica.telesalud.tph.android.service.NetworkUtil;

/**
 * Created by interoperabilidad on 20/05/14.
 */
public abstract class BaseActivity extends FragmentActivity {
    public abstract DatabaseHelper getHelper();
    public abstract void showSuccessMessage(String message);
    public abstract void showErrorMessage(String message);
    public Boolean isConnected()
    {
        return !NetworkUtil.getConnectivityStatus(this).equals(NetworkUtil.NetworkType.TYPE_NOT_CONNECTED);
    }
}
