package com.artica.telesalud.tph.android.service;

import android.content.res.Resources;
import android.util.Log;

import com.artica.telesalud.tph.android.R;
import com.artica.telesalud.tph.android.activity.BaseActivity;
import com.artica.telesalud.tph.android.dao.EventService;
import com.artica.telesalud.tph.android.lightweightmodel.EventDto;
import com.artica.telesalud.tph.android.lightweightmodel.UserInSession;
import com.artica.telesalud.tph.android.mapper.DateObjectMapper;
import com.artica.telesalud.tph.android.model.EventoSpringDto;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by interoperabilidad on 21/05/14.
 */
public class CreateEventService extends SaveAsyncService<EventDto, Void, List<EventoSpringDto>, EventDto> {

    public BaseActivity getActivity() {
        return activity;
    }

    public void setActivity(BaseActivity activity) {
        this.activity = activity;
    }



    public CreateEventService(BaseActivity activity) {

        super(activity);
    }


    @Override
    protected List<EventoSpringDto> doInBackground(EventDto... param) {
        if(isConnected())
        {
        try {

            List<EventoSpringDto> eventos = new ArrayList<EventoSpringDto>();
            for(EventDto event : param) {
                StringBuilder sb = new StringBuilder();
                sb.append(activity.getString(R.string.uri_base_rest_services));
                sb.append(activity.getString(R.string.uri_save_event));
                Resources resources = activity.getResources();
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
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<EventoSpringDto> eventos) {
            if(eventos != null) {
                for(EventoSpringDto evento : eventos) {
                    try {
                        EventService eventService = new EventService(activity.getHelper());
                        EventDto event = new EventDto(evento);
                        event.setId(evento.getLocalId());
                        eventService.save(event);
                    } catch (SQLException e) {
                    }
                }
                if(activity != null) {
                    activity.showSuccessMessage(activity.getString(R.string.create_event_success_text));
                }
            }

    }
}
