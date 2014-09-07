package com.artica.telesalud.tph.android.service;

import android.os.AsyncTask;

import com.artica.telesalud.tph.android.activity.BaseActivity;

/**
 * Created by interoperabilidad on 22/05/14.
 */
public abstract class AbstractAsyncService<T, U, V> extends AsyncTask<T, U, V>{

    protected BaseActivity activity;
    public AbstractAsyncService(BaseActivity activity)
    {
        this.activity = activity;
    }

    public Boolean isConnected()
    {
        return !NetworkUtil.getConnectivityStatus(activity).equals(NetworkUtil.NetworkType.TYPE_NOT_CONNECTED);
    }

}
