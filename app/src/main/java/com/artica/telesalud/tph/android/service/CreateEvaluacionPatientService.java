package com.artica.telesalud.tph.android.service;

import android.content.res.Resources;

import com.artica.telesalud.tph.android.R;
import com.artica.telesalud.tph.android.activity.BaseActivity;
import com.artica.telesalud.tph.android.activity.CreatePatientTriageActivity;
import com.artica.telesalud.tph.android.dao.EvaluacionCompletaService;
import com.artica.telesalud.tph.android.lightweightmodel.EvaluacionCompletaDto;
import com.artica.telesalud.tph.android.lightweightmodel.UserInSession;
import com.artica.telesalud.tph.android.mapper.DateObjectMapper;
import com.artica.telesalud.tph.android.model.EvaluacionCompletaSpringDto;
import com.artica.telesalud.tph.android.model.EventoSpringDto;

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
public class CreateEvaluacionPatientService  extends SaveAsyncService<Integer, Void, EvaluacionCompletaSpringDto, EvaluacionCompletaDto>{


    public CreateEvaluacionPatientService(BaseActivity activity) {
        super(activity);
    }

    @Override
    protected EvaluacionCompletaSpringDto doInBackground(Integer... params) {
        if(params != null && params.length == 2) {
            StringBuilder sb = new StringBuilder();
            sb.append(activity.getString(R.string.uri_base_rest_services));
            sb.append(activity.getString(R.string.uri_create_evaluacion));
            Resources resources = activity.getResources();
            String[] paramsKeys = resources.getStringArray(R.array.uri_create_evaluacion_params);
            Map<String, Integer> map = new HashMap<String, Integer>();
            for (int i = 0; i < params.length; i++) {
                map.put(paramsKeys[i], params[i]);
            }
            final String url = sb.toString();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(DateObjectMapper.getConverter());
            HttpEntity<EventoSpringDto> requestEntity = new HttpEntity<EventoSpringDto>(
                    UserInSession.getDefaultHeaders());
            ResponseEntity<EvaluacionCompletaSpringDto> entity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, EvaluacionCompletaSpringDto.class, map);
            return entity.getBody();
        }
        else
        {
            return null;
        }
    }
    @Override
    protected void onPostExecute(EvaluacionCompletaSpringDto evaluacion) {
        if(evaluacion != null) {
            EvaluacionCompletaDto evaluacionCompletaDto = new EvaluacionCompletaDto(evaluacion);
            try {
                EvaluacionCompletaService evaluacionCompletaService = new EvaluacionCompletaService(activity.getHelper());
                evaluacionCompletaService.save(evaluacionCompletaDto);

                if (activity != null && activity instanceof CreatePatientTriageActivity) {
                    CreatePatientTriageActivity createPatientActivity = (CreatePatientTriageActivity) activity;
                    createPatientActivity.saveTriagePatient(evaluacionCompletaDto);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
