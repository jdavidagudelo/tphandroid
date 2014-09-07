package com.artica.telesalud.tph.android.service;

import android.util.Log;

import com.artica.telesalud.tph.android.R;
import com.artica.telesalud.tph.android.activity.BaseActivity;
import com.artica.telesalud.tph.android.dao.ConceptService;
import com.artica.telesalud.tph.android.lightweightmodel.ConceptDto;
import com.artica.telesalud.tph.android.lightweightmodel.ListConcepts;
import com.artica.telesalud.tph.android.lightweightmodel.UserInSession;
import com.artica.telesalud.tph.android.mapper.DateObjectMapper;
import com.artica.telesalud.tph.android.model.ConceptSpringDto;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by interoperabilidad on 20/05/14.
 */
public class CausasAtencionService extends GetListAsyncService<Void, Void, ListConcepts> {

    public BaseActivity getActivity() {
        return activity;
    }

    public void setActivity(BaseActivity activity) {
        this.activity = activity;
    }
    private static final String TAG = "CAUSAS ATENCION SERVICE";


    public CausasAtencionService(BaseActivity activity) {

        super(activity);
    }

    @Override
    protected ListConcepts doInBackground(Void... params) {
        if(isConnected()) {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append(activity.getString(R.string.uri_base_rest_services));
                sb.append(activity.getString(R.string.uri_get_event_causes));
                final String url = sb.toString();
                RestTemplate restTemplate = new RestTemplate();
                HttpEntity<String> requestEntity = new HttpEntity<String>(
                        UserInSession.getDefaultHeaders());
                restTemplate.getMessageConverters().add(DateObjectMapper.getConverter());
                ResponseEntity<ListConcepts> entity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, ListConcepts.class);
                ListConcepts concepts = entity.getBody();
                return concepts;
            } catch (Exception e) {
                Log.e(TAG, e.getMessage(), e);
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(ListConcepts concepts) {
        try {
            ConceptService conceptService = new ConceptService(activity.getHelper());
            List<ConceptDto> list = new ArrayList<ConceptDto>();
            list.add(ConceptDto.EMPTY_CAUSA_ATENCION);
            if (concepts != null) {

                for (ConceptSpringDto concept : concepts) {
                    ConceptDto causaAtencion = new ConceptDto(concept, ConceptDto.TYPE_CAUSA_ATENCION_ID);
                    conceptService.save(causaAtencion);
                }

            }

        } catch (SQLException e) {
        }
    }

}
