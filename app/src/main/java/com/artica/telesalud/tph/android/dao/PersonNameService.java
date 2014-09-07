package com.artica.telesalud.tph.android.dao;

import com.artica.telesalud.tph.android.lightweightmodel.PersonNameDto;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/**
 * Created by interoperabilidad on 11/08/14.
 */
public class PersonNameService {
    private DatabaseHelper helper;

    public DatabaseHelper getHelper() {
        return helper;
    }

    public void setHelper(DatabaseHelper helper) throws SQLException {
        this.helper = helper;
        personNameDao = helper.getPersonNameDao();
    }
    public PersonNameService(DatabaseHelper helper) throws SQLException {
        setHelper(helper);
    }
    private Dao<PersonNameDto, Integer> personNameDao;
    public synchronized PersonNameDto save(PersonNameDto personName) throws SQLException {
        if(personName.getPersonNameId() != null) {
            PersonNameDto current = personNameDao.queryBuilder().where().eq("personNameId", personName.getPersonNameId()).queryForFirst();
            if (current != null) {
                personName.setId(current.getId());
            }
        }
        personNameDao.createOrUpdate(personName);
        return personName;
    }
    public PersonNameDto getPersonName(Integer personNameId) throws SQLException {
        PersonNameDto personName = personNameDao.queryBuilder().where().eq("personNameId", personNameId).queryForFirst();
        return personName;
    }
    public PersonNameDto getPersonNameByPerson(Integer personId) throws SQLException {

        PersonNameDto personName = personNameDao.queryBuilder().where().eq("personId", personId).queryForFirst();
        return personName;
    }
    public PersonNameDto getPersonNameById(Integer id) throws SQLException {
        PersonNameDto personName = personNameDao.queryBuilder().where().eq("id", id).queryForFirst();
        return personName;
    }

    public PersonNameDto createNew() throws SQLException {
        PersonNameDto personName = new PersonNameDto();
        return save(personName);
    }
}
