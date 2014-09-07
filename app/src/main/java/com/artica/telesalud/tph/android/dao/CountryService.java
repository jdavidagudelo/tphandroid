package com.artica.telesalud.tph.android.dao;

import com.artica.telesalud.tph.android.lightweightmodel.CountryDto;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/**
 * Created by interoperabilidad on 11/08/14.
 */
public class CountryService {
    private DatabaseHelper helper;
    private Dao<CountryDto, Integer> countryDao;

    public DatabaseHelper getHelper() {
        return helper;
    }

    public void setHelper(DatabaseHelper helper) throws SQLException {
        if(helper != null) {
            this.helper = helper;
            countryDao = helper.getCountryDao();
        }
    }
    public CountryService(DatabaseHelper helper) throws SQLException {
        setHelper(helper);
    }
    public synchronized CountryDto save(CountryDto country) throws SQLException {
        if(country != null) {
            if(country.getCountryId() != null) {
                CountryDto current = getCountry(country.getCountryId());
                if(current != null)
                {
                    country.setId(current.getId());
                }
            }
            else if(country.getName() != null)
            {
                CountryDto current = getCountryByName(country.getName());
                if(current != null)
                {
                    country.setId(current.getId());
                }
            }
            countryDao.createOrUpdate(country);
        }
        return country;
    }
    public CountryDto getCountry(Integer countryId) throws SQLException {
        CountryDto country = countryDao.queryBuilder().where().eq("countryId", countryId).queryForFirst();
        return country;
    }
    public CountryDto getCountryById(Integer id) throws SQLException {

        CountryDto country = countryDao.queryBuilder().where().eq("id", id).queryForFirst();
        return country;
    }
    public CountryDto getCountryByName(String name) throws SQLException {

        CountryDto country = countryDao.queryBuilder().where().eq("name", name).queryForFirst();
        return country;
    }

}
