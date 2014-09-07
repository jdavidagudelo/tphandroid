package com.artica.telesalud.tph.android.dao;

import com.artica.telesalud.tph.android.lightweightmodel.CityDto;
import com.artica.telesalud.tph.android.lightweightmodel.StateDto;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by interoperabilidad on 11/08/14.
 */
public class CityService {
    private DatabaseHelper helper;
    private Dao<CityDto, Integer> cityDao;
    private StateService stateService;
    public CityService(DatabaseHelper helper) throws SQLException {
        setHelper(helper);
    }
    public DatabaseHelper getHelper() {
        return helper;
    }
    private CityDto fillCity(CityDto city) throws SQLException {
        if(city != null) {
            if (city.getState() != null) {
                if (city.getState().getStateId() != null) {
                    city.setState(stateService.getState(city.getState().getStateId()));
                } else if(city.getId() != null) {
                    city.setState(stateService.getStateById(city.getState().getId()));
                }
                else if(city.getStateId() != null)
                {
                    city.setState(stateService.getStateById(city.getStateId()));
                }
            }
            else if(city.getStateId() != null)
            {
                city.setState(stateService.getStateById(city.getStateId()));
            }
        }
        return city;
    }
    public void setHelper(DatabaseHelper helper) throws SQLException {
        if(helper != null) {
            this.helper = helper;
            cityDao = helper.getCityDao();
            stateService = new StateService(helper);
        }
    }
    public synchronized CityDto save(CityDto city) throws SQLException {
        if(city != null)
        {
            if(city.getCityId() != null)
            {
                CityDto current = getCity(city.getCityId());
                if(current != null)
                {
                    city.setId(current.getId());
                }
            }
            else if(city.getName() != null)
            {
                if(city.getState() != null)
                {
                    if(city.getState().getName() != null && city.getState().getCountry() != null)
                    {
                        if(city.getState().getCountry().getName() != null)
                        {
                            CityDto current = getCityByName(city.getName(), city.getState().getName(), city.getState().getCountry().getName());
                            if(current != null)
                            {
                                city.setId(current.getId());
                            }
                        }
                    }
                }
            }
            if(city.getState() != null && stateService != null)
            {
                StateDto state = stateService.save(city.getState());
                if(state != null) {
                    city.setState(state);
                    city.setStateId(state.getId());
                }
            }
            cityDao.createOrUpdate(city);
        }
        return city;
    }
    public CityDto getCity(Integer cityId) throws SQLException {

        CityDto city = cityDao.queryBuilder().where().eq("cityId", cityId).queryForFirst();
        city = fillCity(city);
        return city;
    }
    public CityDto getCityById(Integer id) throws SQLException {

        CityDto city = cityDao.queryBuilder().where().eq("id", id).queryForFirst();
        if(city != null) {
            city = fillCity(city);
        }
        return city;
    }
    public List<CityDto> getCitiesByState(Integer stateId) throws SQLException {
        if(stateId != null) {
            StateDto state = stateService.getState(stateId);
            if (state != null) {
                List<CityDto> cities = cityDao.queryBuilder().where().eq("stateId", state.getId()).query();
                for (CityDto city : cities) {
                    fillCity(city);
                }

                return cities;
            }
        }
        return new ArrayList<CityDto>();
    }
    public CityDto getCityByName(String cityName, String stateName,String countryName) throws SQLException {
        StateDto state = stateService.getStateByName(stateName, countryName);
        if(state != null)
        {
            CityDto city = cityDao.queryBuilder().where().eq("name", cityName).and().eq("stateId", state.getId()).queryForFirst();
            fillCity(city);
            return city;
        }
        return null;
    }
}
