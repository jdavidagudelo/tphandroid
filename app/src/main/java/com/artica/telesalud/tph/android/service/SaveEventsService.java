package com.artica.telesalud.tph.android.service;

import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Log;

import com.artica.telesalud.tph.android.R;
import com.artica.telesalud.tph.android.dao.DatabaseHelper;
import com.artica.telesalud.tph.android.dao.EventService;
import com.artica.telesalud.tph.android.lightweightmodel.EventDto;
import com.artica.telesalud.tph.android.lightweightmodel.ListEvents;
import com.artica.telesalud.tph.android.lightweightmodel.UserInSession;
import com.artica.telesalud.tph.android.mapper.DateObjectMapper;
import com.artica.telesalud.tph.android.model.EventoSpringDto;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by interoperabilidad on 13/08/14.
 */
public class SaveEventsService extends AsyncTask<EventDto, EventDto, ListEvents> {

    private Context context;
    private DatabaseHelper helper;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public DatabaseHelper getHelper() {
        return helper;
    }

    public void setHelper(DatabaseHelper helper) {
        this.helper = helper;
    }

    public Boolean isConnected()
    {
        return !NetworkUtil.getConnectivityStatus(context).equals(NetworkUtil.NetworkType.TYPE_NOT_CONNECTED);
    }
    @Override
    protected ListEvents doInBackground(EventDto... param) {

            try {

                ListEvents eventos = new ListEvents();
                for(EventDto event : param) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(context.getString(R.string.uri_base_rest_services));
                    sb.append(context.getString(R.string.uri_save_event));
                    Resources resources = context.getResources();
                    String[] paramsKeys = resources.getStringArray(R.array.uri_save_event_params);
                    String params[] = event.getParamKeys();
                    Map<String, String> map = new HashMap<String, String>();
                    for (int i = 0; i < params.length; i++) {
                        map.put(paramsKeys[i], params[i]);
                    }
                    final String url = sb.toString();
                    RestTemplate restTemplate = new RestTemplate();
                    restTemplate.getMessageConverters().add(DateObjectMapper.getConverter());
                    EventoSpringDto evento = new EventoSpringDto(event);
                    HttpEntity<EventoSpringDto> requestEntity = new HttpEntity<EventoSpringDto>(evento,
                            UserInSession.getDefaultHeaders());
                    ResponseEntity<EventoSpringDto> entity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, EventoSpringDto.class, map);
                    evento = entity.getBody();
                    if(evento != null) {
                        evento.setLocalId(event.getId());
                        eventos.add(evento);
                    }
                }
                return eventos;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }
        return null;
    }
    @Override
    protected void onPostExecute(ListEvents events) {
        try {
            if(events != null) {
                EventService eventService = new EventService(helper);
                for (EventoSpringDto event : events) {
                    EventDto eventDto = new EventDto(event);
                    eventDto.setId(event.getLocalId());
                    eventService.save(eventDto);
                }
            }
        } catch (SQLException e) {
            Log.e("TAG", e.getMessage());
        }
    }
}
