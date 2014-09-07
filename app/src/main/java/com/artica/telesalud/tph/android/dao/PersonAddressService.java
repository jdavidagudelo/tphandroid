package com.artica.telesalud.tph.android.dao;

import com.artica.telesalud.tph.android.lightweightmodel.PersonAddressDto;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/**
 * Created by interoperabilidad on 11/08/14.
 */
public class PersonAddressService {
    private DatabaseHelper helper;
    private Dao<PersonAddressDto, Integer> personAddressDao;

    public DatabaseHelper getHelper() {
        return helper;
    }

    public void setHelper(DatabaseHelper helper) throws SQLException {
        this.helper = helper;
        personAddressDao = helper.getPersonAddressDao();
    }
    public PersonAddressService(DatabaseHelper helper) throws SQLException {
        setHelper(helper);
    }
    public synchronized PersonAddressDto save(PersonAddressDto personAddress) throws SQLException {
        if(personAddress.getPersonAddressId() != null) {
            PersonAddressDto current = personAddressDao.queryBuilder().where().eq("personAddressId", personAddress.getPersonAddressId()).queryForFirst();
            if (current != null) {
                personAddress.setId(current.getId());
            }
        }
        personAddressDao.createOrUpdate(personAddress);
        return personAddress;
    }
    public PersonAddressDto getPersonAddressByPerson(Integer personId) throws SQLException {
        PersonAddressDto personAddressDto = personAddressDao.queryBuilder().where().eq("personId", personId).queryForFirst();
        return personAddressDto;
    }
    public PersonAddressDto getPersonAddress(Integer personAddressId) throws SQLException {
        PersonAddressDto personAddressDto = personAddressDao.queryBuilder().where().eq("personAddressId", personAddressId).queryForFirst();
        return personAddressDto;
    }
    public PersonAddressDto getPersonAddressById(Integer id) throws SQLException {
        PersonAddressDto personAddressDto = personAddressDao.queryBuilder().where().eq("id", id).queryForFirst();
        return personAddressDto;
    }
    public PersonAddressDto createNew() throws SQLException {
        PersonAddressDto personAddress = new PersonAddressDto();
        return save(personAddress);
    }
}
