package com.artica.telesalud.tph.android.service;

import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.artica.telesalud.tph.android.R;
import com.artica.telesalud.tph.android.activity.BaseActivity;
import com.artica.telesalud.tph.android.activity.CreateEventActivity;
import com.artica.telesalud.tph.android.activity.EventActivity;
import com.artica.telesalud.tph.android.dao.StateService;
import com.artica.telesalud.tph.android.lightweightmodel.ListStates;
import com.artica.telesalud.tph.android.lightweightmodel.StateDto;
import com.artica.telesalud.tph.android.lightweightmodel.UserInSession;
import com.artica.telesalud.tph.android.mapper.DateObjectMapper;
import com.artica.telesalud.tph.android.model.StateProvinceSpringDto;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by interoperabilidad on 20/05/14.
 */
public class StatesService extends GetListAsyncService<Void, Void, ListStates> {


    public BaseActivity getActivity() {
        return activity;
    }

    public void setActivity(BaseActivity activity) {
        this.activity = activity;
    }



    public StatesService(BaseActivity activity) {
        super(activity);
    }

    @Override
    protected ListStates doInBackground(Void... params) {
        if(isConnected()) {
            try {

                StringBuilder sb = new StringBuilder();
                sb.append(activity.getString(R.string.uri_base_rest_services));
                sb.append(activity.getString(R.string.uri_get_states));
                final String url = sb.toString();
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(DateObjectMapper.getConverter());
                HttpEntity<String> requestEntity = new HttpEntity<String>(
                        UserInSession.getDefaultHeaders());
                restTemplate.getMessageConverters().add(DateObjectMapper.getConverter());
                ResponseEntity<ListStates> entity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, ListStates.class);
                return entity.getBody();


            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(ListStates states) {
      try {
          List<StateDto> list = new ArrayList<StateDto>();
          StateService stateService = new StateService(activity.getHelper());
          list.add(StateDto.EMPTY_STATE);
          if (states != null) {

              for (StateProvinceSpringDto stateProvince : states) {
                  StateDto state = new StateDto(stateProvince);
                  state = stateService.save(state);
                  list.add(state);
              }

          } else {
              list.addAll(stateService.getStatesDefault());
          }
          if(activity != null && activity instanceof EventActivity || activity instanceof CreateEventActivity)
          {
              ArrayAdapter<StateDto> stateArrayAdapter = new ArrayAdapter<StateDto>(activity,
                      android.R.layout.simple_spinner_item, list);
              stateArrayAdapter
                      .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
              Spinner spinnerState = (Spinner)activity.findViewById(R.id.spinnerState);
              Adapter adapter =spinnerState.getAdapter();
              List<StateDto> currentList = new ArrayList<StateDto>();
              for(int i = 0; adapter != null && i < adapter.getCount(); i++)
              {
                  currentList.add((StateDto)adapter.getItem(i));
              }
              Collections.sort(list);
              Collections.sort(currentList);
              if(!currentList.equals(list)) {
                  spinnerState.setAdapter(stateArrayAdapter);
              }
          }
      }
      catch (SQLException e) {
          e.printStackTrace();
      }
    }

}