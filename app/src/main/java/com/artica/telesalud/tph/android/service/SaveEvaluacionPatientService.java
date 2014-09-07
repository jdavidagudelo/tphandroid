package com.artica.telesalud.tph.android.service;

import android.content.res.Resources;
import android.util.Log;

import com.artica.telesalud.tph.android.R;
import com.artica.telesalud.tph.android.activity.BaseActivity;
import com.artica.telesalud.tph.android.activity.CreatePatientTriageActivity;
import com.artica.telesalud.tph.android.dao.EvaluacionService;
import com.artica.telesalud.tph.android.lightweightmodel.EvaluacionDto;
import com.artica.telesalud.tph.android.lightweightmodel.UserInSession;
import com.artica.telesalud.tph.android.mapper.DateObjectMapper;
import com.artica.telesalud.tph.android.model.EvaluacionSpringDto;

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
 * Created by interoperabilidad on 18/07/14.
 */
public class SaveEvaluacionPatientService extends SaveAsyncService<EvaluacionDto, Void, List<EvaluacionSpringDto>, EvaluacionDto>{


    public SaveEvaluacionPatientService(BaseActivity activity) {

        super(activity);
    }


    @Override
    protected List<EvaluacionSpringDto> doInBackground(EvaluacionDto... param) {
        try {
            List<EvaluacionSpringDto> evaluaciones = new ArrayList<EvaluacionSpringDto>();
            for(EvaluacionDto evaluacion : param) {
                StringBuilder sb = new StringBuilder();
                sb.append(activity.getString(R.string.uri_base_rest_services));
                sb.append(activity.getString(R.string.uri_save_evaluacion_patient));
                Resources resources = activity.getResources();
                String[] paramsKeys = resources.getStringArray(R.array.uri_save_evaluacion_patient_params);
                String params[] = evaluacion.getParamKeys();
                Map<String, String> map = new HashMap<String, String>();
                for (int i = 0; i < params.length; i++) {
                    map.put(paramsKeys[i], params[i]);
                }
                final String url = sb.toString();
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(DateObjectMapper.getConverter());
                EvaluacionSpringDto evaluation = new EvaluacionSpringDto(evaluacion);
                HttpEntity<EvaluacionSpringDto> requestEntity = new HttpEntity<EvaluacionSpringDto>(evaluation,
                        UserInSession.getDefaultHeaders());
                ResponseEntity<EvaluacionSpringDto> entity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, EvaluacionSpringDto.class, map);
                evaluation = entity.getBody();
                if(evaluation != null) {
                    evaluaciones.add(evaluation);
                }
            }
            return evaluaciones;
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(), e);
        }

        return null;
    }



    @Override
    protected void onPostExecute(List<EvaluacionSpringDto> evaluaciones) {
        try {
            if(evaluaciones != null) {
                EvaluacionService evaluacionService = new EvaluacionService(activity.getHelper());
                for (EvaluacionSpringDto evaluacion : evaluaciones) {
                    EvaluacionDto current = new EvaluacionDto(evaluacion);
                    evaluacionService.save(current);
                }

                if (activity != null && activity instanceof CreatePatientTriageActivity && evaluaciones != null) {
                    CreatePatientTriageActivity createPatientActivity = (CreatePatientTriageActivity) activity;
                    List<EvaluacionDto> result = new ArrayList<EvaluacionDto>();
                    for (EvaluacionSpringDto e : evaluaciones) {
                        result.add(new EvaluacionDto(e));
                    }
                    createPatientActivity.triageAlmacenado(result);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
