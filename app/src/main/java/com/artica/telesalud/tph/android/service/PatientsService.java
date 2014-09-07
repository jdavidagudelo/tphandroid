package com.artica.telesalud.tph.android.service;

import android.content.res.Resources;
import android.util.Log;
import android.widget.GridView;

import com.artica.telesalud.tph.android.R;
import com.artica.telesalud.tph.android.activity.BaseActivity;
import com.artica.telesalud.tph.android.activity.EventActivity;
import com.artica.telesalud.tph.android.adapter.LesionadoArrayAdapter;
import com.artica.telesalud.tph.android.dao.LesionadoService;
import com.artica.telesalud.tph.android.lightweightmodel.EvaluacionDto;
import com.artica.telesalud.tph.android.lightweightmodel.LesionadoDto;
import com.artica.telesalud.tph.android.lightweightmodel.ListLesionados;
import com.artica.telesalud.tph.android.lightweightmodel.UserInSession;
import com.artica.telesalud.tph.android.mapper.DateObjectMapper;
import com.artica.telesalud.tph.android.model.LesionadoSpringDto;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by interoperabilidad on 17/07/14.
 */
public class PatientsService extends GetListAsyncService<Integer, Void, ListLesionados> {
    public PatientsService(BaseActivity activity) {

        super(activity);
    }

    @Override
    protected ListLesionados doInBackground(Integer... params) {
        if(isConnected()) {
            try {

                StringBuilder sb = new StringBuilder();
                sb.append(activity.getString(R.string.uri_base_rest_services));
                sb.append(activity.getString(R.string.uri_get_lesionados_evento));
                Resources resources = activity.getResources();
                String paramsKey[] = resources.getStringArray(R.array.uri_get_lesionados_evento_params);
                final String url = sb.toString();
                Map<String, String> map = new HashMap<String, String>();
                for (int i = 0; i < params.length - 1; i++) {
                    map.put(paramsKey[i], String.valueOf(params[i]));
                }
                RestTemplate restTemplate = new RestTemplate();
                HttpEntity<String> requestEntity = new HttpEntity<String>(
                        UserInSession.getDefaultHeaders());
                restTemplate.getMessageConverters().add(DateObjectMapper.getConverter());
                ResponseEntity<ListLesionados> entity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, ListLesionados.class, map);
                ListLesionados events = entity.getBody();
                events.setFromServer(true);
                return events;
            } catch (Exception e) {
                Log.e("TAG", e.getMessage());
            }
        }

        ListLesionados listLesionados = new ListLesionados();
        if(params.length > 2)
        {
            listLesionados.setEventId(params[2]);
        }
        listLesionados.setFromServer(false);
        return listLesionados;
    }

    @Override
    protected void onPostExecute(ListLesionados lesionados) {
        List<LesionadoDto> list = new ArrayList<LesionadoDto>();
        try {
            LesionadoService lesionadoService = new LesionadoService(activity.getHelper());

            if(lesionados != null)
            {
                for(LesionadoSpringDto lesionado : lesionados)
                {
                    LesionadoDto lesionadoDto = new LesionadoDto(lesionado);
                    lesionadoService.save(lesionadoDto);
                    list.add(lesionadoDto);
                }
                if(!lesionados.getFromServer())
                {
                    if(lesionados.getEventId() != null) {
                        List<LesionadoDto> lesionadosList = lesionadoService.getLesionadosEventById(lesionados.getEventId());
                        list.addAll(lesionadosList);
                    }
                }
                if(activity != null && activity instanceof EventActivity && !list.isEmpty())
                {
                    EventActivity eventActivity = (EventActivity)activity;

                    List<LesionadoDto> copy = new ArrayList<LesionadoDto>();
                    copy.addAll(list);
                        Collections.sort(list, new Comparator<LesionadoDto>() {
                            @Override
                            public int compare(LesionadoDto lhs, LesionadoDto rhs) {

                                    if (lhs.getEvaluaciones() != null && !lhs.getEvaluaciones().isEmpty()) {
                                        if (rhs.getEvaluaciones() != null && !rhs.getEvaluaciones().isEmpty()) {
                                            EvaluacionDto el = lhs.getEvaluaciones().get(lhs.getEvaluaciones().size() - 1);
                                            if (el != null) {
                                                EvaluacionDto er = rhs.getEvaluaciones().get(rhs.getEvaluaciones().size() - 1);
                                                if (er != null) {
                                                    if (er.getPrioridadTriage() != null && el.getPrioridadTriage() != null) {
                                                        return el.getPrioridadTriage().compareTo(er.getPrioridadTriage());
                                                    }
                                                }
                                            }
                                        }
                                }
                                return 0;
                            }
                        });
                    //eventActivity.setLesionados(copy);
                    GridView grid = (GridView)eventActivity.findViewById(R.id.listViewPatients);
                    LesionadoArrayAdapter adapter = new LesionadoArrayAdapter(activity, list);
                    grid.setAdapter(adapter);

                }
            }
        } catch (SQLException e) {
            Log.e("TAG", e.getMessage());
        }
    }
}
