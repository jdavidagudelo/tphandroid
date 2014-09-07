package com.artica.telesalud.tph.android.intentservice;

/**
 * Created by interoperabilidad on 15/08/14.
 */
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.artica.telesalud.tph.android.R;
import com.artica.telesalud.tph.android.dao.ConceptService;
import com.artica.telesalud.tph.android.dao.DatabaseHelper;
import com.artica.telesalud.tph.android.lightweightmodel.ConceptDto;
import com.artica.telesalud.tph.android.lightweightmodel.ListConcepts;
import com.artica.telesalud.tph.android.lightweightmodel.UserInSession;
import com.artica.telesalud.tph.android.mapper.DateObjectMapper;
import com.artica.telesalud.tph.android.model.ConceptSpringDto;
import com.artica.telesalud.tph.android.service.NetworkUtil;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class CausasAtencionIntentService extends IntentService {
    // Defines a custom Intent action

    // Defines the key for the status "extra" in an Intent
    public static final String EXTENDED_DATA_STATUS =
            "com.example.android.threadsample.STATUS";
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    public static final String ACTION_GET_CAUSAS_ATENCION = "com.artica.telesalud.tph.android.intentservice.action.GET_CAUSAS_ATENCION";

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionGetEvents(Context context, Integer userId) {
        Intent intent = new Intent(context, EventsIntentService.class);
        intent.setAction(ACTION_GET_CAUSAS_ATENCION);
        context.startService(intent);
    }



    public CausasAtencionIntentService() {
        super("EventsIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_GET_CAUSAS_ATENCION.equals(action)) {
                try {
                    handleActionGetCausasAtencion();
                } catch (Exception e) {
                    Log.e("TAG", e.getMessage());
                }
            }
        }
    }
    public Boolean isConnected()
    {
        return !NetworkUtil.getConnectivityStatus(this).equals(NetworkUtil.NetworkType.TYPE_NOT_CONNECTED);
    }
    /**
     * Handle action getEvents  in the provided background thread with the provided
     * parameters.
     */
    private void handleActionGetCausasAtencion() throws SQLException {
        if(isConnected()) {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append(getString(R.string.uri_base_rest_services));
                sb.append(getString(R.string.uri_get_event_causes));
                final String url = sb.toString();
                RestTemplate restTemplate = new RestTemplate();
                HttpEntity<String> requestEntity = new HttpEntity<String>(
                        UserInSession.getDefaultHeaders());
                restTemplate.getMessageConverters().add(DateObjectMapper.getConverter());
                ResponseEntity<ListConcepts> entity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, ListConcepts.class);
                ListConcepts concepts = entity.getBody();
                ConceptService conceptService = new ConceptService(getHelper());
                List<ConceptDto> list = new ArrayList<ConceptDto>();
                list.add(ConceptDto.EMPTY_CAUSA_ATENCION);
                if (concepts != null) {
                    for (ConceptSpringDto concept : concepts) {
                        ConceptDto causaAtencion = new ConceptDto(concept, ConceptDto.TYPE_CAUSA_ATENCION_ID);
                        conceptService.save(causaAtencion);
                        list.add(causaAtencion);
                    }
                }
            } catch (Exception e) {
            }
        }
        Intent localIntent =
                new Intent(Constants.BROADCAST_ACTION)
                        // Puts the status into the Intent
                        .putExtra(EXTENDED_DATA_STATUS, "This is my status");
        // Broadcasts the Intent to receivers in this app.
        LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);

    }
    private DatabaseHelper databaseHelper;
    public DatabaseHelper getHelper() {
        if(databaseHelper == null)
        {
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper                                                                                         ;
    }

}