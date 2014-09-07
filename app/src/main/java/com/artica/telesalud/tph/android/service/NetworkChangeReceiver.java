package com.artica.telesalud.tph.android.service;

/**
 * Created by interoperabilidad on 23/05/14.
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.artica.telesalud.tph.android.dao.DatabaseHelper;
import com.artica.telesalud.tph.android.intentservice.SynchronizeIntentService;
import com.j256.ormlite.android.apptools.OpenHelperManager;

public class NetworkChangeReceiver extends BroadcastReceiver {

    private DatabaseHelper databaseHelper;
    @Override
    public void onReceive(final Context context, final Intent intent) {
        String status = NetworkUtil.getConnectivityStatusString(context);
        Toast.makeText(context, status, Toast.LENGTH_LONG).show();
        SynchronizeIntentService.startActionSynchronize(context);
        SynchronizeIntentService.startActionGetLists(context);
    }

    public DatabaseHelper getHelper(Context context) {
        if(databaseHelper == null)
        {
            databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        }
        return databaseHelper                                                                                         ;
    }
}
