package com.artica.telesalud.tph.android.service;

import android.util.Log;

import com.artica.telesalud.tph.android.R;
import com.artica.telesalud.tph.android.activity.BaseActivity;
import com.artica.telesalud.tph.android.lightweightmodel.CityDto;
import com.artica.telesalud.tph.android.lightweightmodel.ListCities;
import com.artica.telesalud.tph.android.lightweightmodel.UserInSession;
import com.artica.telesalud.tph.android.mapper.DateObjectMapper;
import com.artica.telesalud.tph.android.model.CitySpringDto;
import com.j256.ormlite.dao.Dao;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by interoperabilidad on 11/08/14.
 */
    public class GetAllCitiesService extends GetListAsyncService<Void, Void, ListCities> {

        public BaseActivity getActivity() {
            return activity;
        }

        public void setActivity(BaseActivity activity) {
            this.activity = activity;
        }

        private static final String TAG = "CITIES SERVICE";


        public GetAllCitiesService(BaseActivity activity) {
            super(activity);
        }

        @Override
        protected ListCities doInBackground(Void... params) {
            if(isConnected()) {
                try {
                    StringBuilder sb = new StringBuilder();
                    sb.append(activity.getString(R.string.uri_base_rest_services));
                    sb.append(activity.getString(R.string.uri_get_all_cities));
                    final String url = sb.toString();
                    RestTemplate restTemplate = new RestTemplate();
                    restTemplate.getMessageConverters().add(DateObjectMapper.getConverter());
                    HttpEntity<String> requestEntity = new HttpEntity<String>(
                            UserInSession.getDefaultHeaders());

                    restTemplate.getMessageConverters().add(DateObjectMapper.getConverter());
                    ResponseEntity<ListCities> entity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, ListCities.class);

                    ListCities cities = entity.getBody();
                    return cities;
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage(), e);
                }

            }
            return null;
        }

        @Override
        protected void onPostExecute(ListCities cities) {
            try {
                Dao<CityDto, Integer> cityDao = activity.getHelper().getCityDao();
                List<CityDto> list = new ArrayList<CityDto>();
                list.add(CityDto.EMPTY_CITY);
                if (cities != null) {
                    for (CitySpringDto city : cities) {
                        CityDto ciudad = new CityDto(city);
                        cityDao.createOrUpdate(ciudad);
                    }

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


