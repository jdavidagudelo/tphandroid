package com.artica.telesalud.tph.android.intentservice;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.sql.SQLException;

public class SynchronizeIntentService extends AbstractIntentService {
    // Defines a custom Intent action

    // Defines the key for the status "extra" in an Intent
    public static final String EXTENDED_DATA_STATUS =
            "com.example.android.threadsample.STATUS";
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    public static final String ACTION_SYNCHRONIZE = "com.artica.telesalud.tph.android.intentservice.action.SYNCHRONIZE";
    public static final String ACTION_GET_LISTS = "com.artica.telesalud.tph.android.intentservice.action.GET_LISTS";
    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionSynchronize(Context context) {
        Intent intent = new Intent(context, SynchronizeIntentService.class);
        intent.setAction(ACTION_SYNCHRONIZE);
        context.startService(intent);
    }
    public static void startActionGetLists(Context context) {
        Intent intent = new Intent(context, SynchronizeIntentService.class);
        intent.setAction(ACTION_GET_LISTS);
        context.startService(intent);
    }


    public SynchronizeIntentService() {
        super("EventsIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_SYNCHRONIZE.equals(action)) {
                try {
                    handleActionSynchronize();
                } catch (Exception e) {
                    Log.e("TAG", e.getMessage());
                }
            }
            else if(ACTION_GET_LISTS.equals(action))
            {
                handleActionGetLists();
            }
        }
    }

    private void handleActionGetLists()
    {
        loadCausasAtencion();
        loadStates();
        loadCities();
        Intent localIntent =
                new Intent(Constants.BROADCAST_ACTION_RECEIVE_LISTS)
                        // Puts the status into the Intent
                        .putExtra(EXTENDED_DATA_STATUS, "This is my status");
        // Broadcasts the Intent to receivers in this app.
        LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);

    }
    /**
     * Handle action getEvents  in the provided background thread with the provided
     * parameters.
     */
    private void handleActionSynchronize() throws SQLException {
        try {
            if (isConnected()) {
                saveEventos();
                saveLesionados();
            }
        }catch(Exception e)
        {
            Log.e(SynchronizeIntentService.class.getCanonicalName(), e.getMessage());
        }
        Intent localIntent =
                new Intent(Constants.BROADCAST_ACTION)
                        // Puts the status into the Intent
                        .putExtra(EXTENDED_DATA_STATUS, "This is my status");
        // Broadcasts the Intent to receivers in this app.
        LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);

    }





}
