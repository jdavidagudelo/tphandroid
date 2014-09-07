package com.artica.telesalud.tph.android.intentservice;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.artica.telesalud.tph.android.dao.DatabaseHelper;
import com.artica.telesalud.tph.android.service.NetworkUtil;
import com.j256.ormlite.android.apptools.OpenHelperManager;


public class PatientsIntentService extends AbstractIntentService {
    // Defines a custom Intent action

    // Defines the key for the status "extra" in an Intent
    public static final String EXTENDED_DATA_STATUS =
            "com.example.android.threadsample.STATUS";
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    public static final String ACTION_GET_PATIENTS = "com.artica.telesalud.tph.android.intentservice.action.GET_PATIENTS";
    // TODO: Rename parameters
    public static final String EXTRA_PARAM_EVENT_ID = "com.artica.telesalud.tph.android.intentservice.extra.EVENT_ID";
    public static final String EXTRA_PARAM_USER_ID = "com.artica.telesalud.tph.android.intentservice.extra.USER_ID";
    public static final String EXTRA_PARAM_REQUEST_COUNT = "com.artica.telesalud.tph.android.intentservice.extra.REQUEST_COUNT";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public PatientsIntentService() {
        super("Patients Service");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionGetPatients(Context context, Integer userId, Integer eventId) {
        Intent intent = new Intent(context, PatientsIntentService.class);
        intent.setAction(ACTION_GET_PATIENTS);
        intent.putExtra(EXTRA_PARAM_USER_ID, userId);
        intent.putExtra(EXTRA_PARAM_EVENT_ID, eventId);
        context.startService(intent);
    }
    private DatabaseHelper databaseHelper;
    public DatabaseHelper getHelper() {
        if(databaseHelper == null)
        {
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper                                                                                         ;
    }

    public Boolean isConnected()
    {
        return !NetworkUtil.getConnectivityStatus(this).equals(NetworkUtil.NetworkType.TYPE_NOT_CONNECTED);
    }

    public void handleActionGetPatients(Integer userId, Integer eventId) {
        if (isConnected()) {
            try {
               saveLesionados();
               getLesionados(userId, eventId);
                getPatientIdentifierTypes();
                getMaritalStatuses();
                getAseguradoras();
                getEpss();
                getPlanesBeneficios();
                getTiposAntecedentes();
            }  catch (Exception e) {
                Log.e("TAG", e.getMessage());
            }
        }
        Intent localIntent =
                new Intent(Constants.BROADCAST_ACTION_RECEIVE_PATIENTS)
                        // Puts the status into the Intent
                        .putExtra(EXTENDED_DATA_STATUS, "This is my status");
        // Broadcasts the Intent to receivers in this app.
        LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_GET_PATIENTS.equals(action)) {
                final Integer userId = intent.getIntExtra(EXTRA_PARAM_USER_ID, 0);
                final Integer eventId = intent.getIntExtra(EXTRA_PARAM_EVENT_ID, 0);
                try {
                        handleActionGetPatients(userId, eventId);
                } catch (Exception e) {
                    Log.e("TAG", e.getMessage());
                }
            }
        }
    }

}
