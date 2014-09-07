package com.artica.telesalud.tph.android.service;

import android.content.res.Resources;

import com.artica.telesalud.tph.android.R;
import com.artica.telesalud.tph.android.activity.BaseActivity;
import com.artica.telesalud.tph.android.activity.CreatePatientActivity;
import com.artica.telesalud.tph.android.activity.CreatePatientTriageActivity;
import com.artica.telesalud.tph.android.dao.LesionadoService;
import com.artica.telesalud.tph.android.lightweightmodel.LesionadoDto;
import com.artica.telesalud.tph.android.lightweightmodel.UserInSession;
import com.artica.telesalud.tph.android.mapper.DateObjectMapper;
import com.artica.telesalud.tph.android.model.EventoSpringDto;
import com.artica.telesalud.tph.android.model.LesionadoSpringDto;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by interoperabilidad on 18/07/14.
 */
public class CreateLesionadoEventoService extends SaveAsyncService<Integer, Void, LesionadoSpringDto, LesionadoDto>{

    public CreateLesionadoEventoService(BaseActivity activity) {
        super(activity);
    }

    @Override
    protected LesionadoSpringDto doInBackground(Integer... params) {
        if(params != null && params.length == 3) {
            StringBuilder sb = new StringBuilder();
            sb.append(activity.getString(R.string.uri_base_rest_services));
            sb.append(activity.getString(R.string.uri_create_lesionado));
            Resources resources = activity.getResources();
            String[] paramsKeys = resources.getStringArray(R.array.uri_create_lesionado_params);
            Map<String, Integer> map = new HashMap<String, Integer>();
            for (int i = 0; i < params.length; i++) {
                map.put(paramsKeys[i], params[i]);
            }
            final String url = sb.toString();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(DateObjectMapper.getConverter());
            HttpEntity<EventoSpringDto> requestEntity = new HttpEntity<EventoSpringDto>(
                    UserInSession.getDefaultHeaders());
            ResponseEntity<LesionadoSpringDto> entity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, LesionadoSpringDto.class, map);
            return entity.getBody();
        }
        else
        {
            return null;
        }
    }
    @Override
    protected void onPostExecute(LesionadoSpringDto lesionado) {
        if(lesionado != null) {
            try {
                LesionadoService lesionadoService = new LesionadoService(activity.getHelper());

            LesionadoDto lesionadoDto = new LesionadoDto(lesionado);
                lesionadoService.save(lesionadoDto);

            if(activity != null && activity instanceof CreatePatientTriageActivity)
            {
                CreatePatientTriageActivity createPatientActivity = (CreatePatientTriageActivity) activity;
                createPatientActivity.proccessNewLesionado(lesionadoDto);
            }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
