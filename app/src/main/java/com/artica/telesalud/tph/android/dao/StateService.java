package com.artica.telesalud.tph.android.dao;

import com.artica.telesalud.tph.android.lightweightmodel.CountryDto;
import com.artica.telesalud.tph.android.lightweightmodel.StateDto;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by interoperabilidad on 11/08/14.
 */
public class StateService {
    private DatabaseHelper helper;
    private Dao<StateDto, Integer> stateDao;
    private CountryService countryService;

    public DatabaseHelper getHelper() {
        return helper;
    }

    public void setHelper(DatabaseHelper helper) throws SQLException {
        if(helper != null) {
            this.helper = helper;
            stateDao = helper.getStateDao();
            countryService = new CountryService(helper);
        }
    }
    public StateService(DatabaseHelper helper) throws SQLException {
        setHelper(helper);
    }
    public synchronized StateDto save(StateDto state) throws SQLException {
        if(state != null)
        {
            if(state.getStateId() != null)
            {
                StateDto current = getState(state.getStateId());
                if(current != null) {
                    state.setId(current.getId());
                }
            }
            else if(state.getName() != null)
            {
                if(state.getCountry() != null)
                {
                    if(state.getCountry().getName() != null)
                    {
                        StateDto current = getStateByName(state.getName(), state.getCountry().getName());
                        if(current != null) {
                            state.setId(current.getId());
                        }
                    }
                }
            }
            if(state.getCountry() != null)
            {
                CountryDto country = countryService.save(state.getCountry());
                if(country != null) {
                    state.setCountry(country);
                    state.setCountryId(country.getId());
                }
            }
            stateDao.createOrUpdate(state);
        }
        return state;
    }
    public StateDto getStateById(Integer id) throws SQLException {
        StateDto state = stateDao.queryBuilder().where().eq("id", id).queryForFirst();
        if(state != null) {
            fillState(state);
        }
        return state;
    }
    public StateDto getState(Integer stateId) throws SQLException {
        StateDto state = stateDao.queryBuilder().where().eq("stateId", stateId).queryForFirst();
        if(state != null)
        {
            fillState(state);
        }
        return state;
    }
    public List<StateDto> getStatesDefault() throws SQLException {
        return getStatesByCountry(1);
    }
    private void fillState(StateDto state) throws SQLException {
        if(state.getCountry() != null && countryService != null)
        {
            if(state.getCountry().getCountryId() != null) {
                state.setCountry(countryService.getCountry(state.getCountry().getCountryId()));
            }
            else if(state.getCountry().getId() != null)
            {
                state.setCountry(countryService.getCountryById(state.getCountry().getId()));
            }
            else if(state.getCountryId() != null)
            {
                state.setCountry(countryService.getCountryById(state.getCountryId()));

            }
        }
        else if(state.getCountryId() != null && countryService != null)
        {
            state.setCountry(countryService.getCountryById(state.getCountryId()));

        }

    }
    public List<StateDto> getStatesByCountry(Integer countryId) throws SQLException {
        CountryDto country = countryService.getCountry(countryId);
        if(country != null) {
            List<StateDto> states = stateDao.queryBuilder().where().eq("countryId", country.getId()).query();
            for (StateDto state : states) {
                fillState(state);
            }
            return states;
        }
        return new ArrayList<StateDto>();
    }
    public StateDto getStateByName(String stateName, String countryName) throws SQLException {
        CountryDto country = countryService.getCountryByName(countryName);
        if(country != null) {
            StateDto state = stateDao.queryBuilder().where().eq("name", stateName).and().eq("countryId", country.getId()).queryForFirst();
            return state;
        }
        return null;
    }
}
