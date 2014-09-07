package com.artica.telesalud.tph.android.service;

import android.content.res.Resources;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.artica.telesalud.tph.android.R;
import com.artica.telesalud.tph.android.activity.BaseActivity;
import com.artica.telesalud.tph.android.activity.CreateEventActivity;
import com.artica.telesalud.tph.android.activity.EventActivity;
import com.artica.telesalud.tph.android.dao.CityService;
import com.artica.telesalud.tph.android.lightweightmodel.CityDto;
import com.artica.telesalud.tph.android.lightweightmodel.ListCities;
import com.artica.telesalud.tph.android.lightweightmodel.UserInSession;
import com.artica.telesalud.tph.android.mapper.DateObjectMapper;
import com.artica.telesalud.tph.android.model.CitySpringDto;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by interoperabilidad on 20/05/14.
 */
public class CitiesService extends GetListAsyncService<Integer, Void, ListCities> {

    public BaseActivity getActivity() {
        return activity;
    }

    public void setActivity(BaseActivity activity) {
        this.activity = activity;
    }

    private static final String TAG = "CITIES SERVICE";


    public CitiesService(BaseActivity activity) {
        super(activity);
    }

    @Override
    protected ListCities doInBackground(Integer... params) {
        if(isConnected()) {
            try {

                StringBuilder sb = new StringBuilder();
                sb.append(activity.getString(R.string.uri_base_rest_services));
                sb.append(activity.getString(R.string.uri_get_cities_by_state));
                final String url = sb.toString();
                Resources resources = activity.getResources();
                String paramsKey[] = resources.getStringArray(R.array.uri_get_cities_by_state_params);
                HashMap<String, Integer> map = new HashMap<String, Integer>();
                for (int i = 0; i < paramsKey.length; i++) {
                    map.put(paramsKey[i], params[i]);
                }
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(DateObjectMapper.getConverter());
                HttpEntity<String> requestEntity = new HttpEntity<String>(
                        UserInSession.getDefaultHeaders());

                restTemplate.getMessageConverters().add(DateObjectMapper.getConverter());
                ResponseEntity<ListCities> entity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, ListCities.class, map);

                ListCities cities = entity.getBody();
                cities.setStateId(params[0]);
                return cities;
            } catch (Exception e) {
                Log.e(TAG, e.getMessage(), e);
            }

        }
        if (params.length > 0) {
            ListCities cities = new ListCities();
            cities.setStateId(params[0]);
            return cities;
        }
        return null;
    }

    @Override
    protected void onPostExecute(ListCities cities) {
        try {
            CityService cityService = new CityService(activity.getHelper());
            List<CityDto> list = new ArrayList<CityDto>();
            list.add(CityDto.EMPTY_CITY);
            if (cities != null) {

                for (CitySpringDto city : cities) {
                    CityDto ciudad = new CityDto(city);
                    cityService.save(ciudad);
                    list.add(ciudad);
                }
                if(cities.isEmpty())
                {
                    List<CityDto> result = cityService.getCitiesByState(cities.getStateId());
                    for(CityDto city: result)
                    {
                        list.add(city);
                    }
                }

            }
            if(activity != null && (activity instanceof EventActivity || activity instanceof CreateEventActivity))
            {
                ArrayAdapter<CityDto> cityArrayAdapter = new ArrayAdapter<CityDto>(activity,
                        android.R.layout.simple_spinner_item, list);
                cityArrayAdapter
                        .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                Spinner spinnerCity = (Spinner)activity.findViewById(R.id.spinnerCity);
                Adapter adapter = spinnerCity.getAdapter();
                List<CityDto> currentList = new ArrayList<CityDto>();
                for(int i = 0; adapter != null && i < adapter.getCount(); i++)
                {
                    currentList.add((CityDto)adapter.getItem(i));
                }
                Collections.sort(list);
                Collections.sort(currentList);
                if(!currentList.equals(list)) {
                    spinnerCity.setAdapter(cityArrayAdapter);
                }

            }
        } catch (SQLException e) {
        }
    }

}
