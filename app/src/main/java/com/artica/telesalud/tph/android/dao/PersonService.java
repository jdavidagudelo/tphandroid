package com.artica.telesalud.tph.android.dao;


import com.artica.telesalud.tph.android.lightweightmodel.PersonAddressDto;
import com.artica.telesalud.tph.android.lightweightmodel.PersonDto;
import com.artica.telesalud.tph.android.lightweightmodel.PersonNameDto;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/**
 * Created by interoperabilidad on 11/08/14.
 */
public class PersonService {
    private DatabaseHelper helper;
    private Dao<PersonDto, Integer> personDao;
    private PersonAddressService personAddressService;
    private PersonNameService personNameService;

    public DatabaseHelper getHelper() {
        return helper;
    }

    public void setHelper(DatabaseHelper helper) throws SQLException {
        this.helper = helper;
        personDao = helper.getPersonDao();
        personAddressService = new PersonAddressService(helper);
        personNameService = new PersonNameService(helper);
    }
    public PersonService(DatabaseHelper helper) throws SQLException {
        setHelper(helper);
    }
    public synchronized PersonDto save(PersonDto person) throws SQLException {
        if(person != null) {
            if(person.getPersonId() != null) {
                PersonDto current = personDao.queryBuilder().where().eq("personId", person.getPersonId()).queryForFirst();
                if (current != null) {
                    person.setId(current.getId());
                }
            }
            if(person.getPreferredAddress() != null && personAddressService != null)
            {
                PersonAddressDto personAddress = personAddressService.save(person.getPreferredAddress());
                if(personAddress != null) {
                    person.setPreferredAddress(personAddress);
                    person.setPreferredAddressId(personAddress.getId());
                }
            }
            if(person.getPreferredName() != null && personNameService != null)
            {
                PersonNameDto personName = personNameService.save(person.getPreferredName());
                if(personName != null) {
                    person.setPreferredName(personName);
                    person.setPreferredNameId(personName.getId());
                }

            }
            personDao.createOrUpdate(person);
        }
        return person;
    }
    private void fillPerson(PersonDto person) throws SQLException {
        if(person != null) {
            if (person.getPreferredAddress() != null && personAddressService != null) {
                if (person.getPreferredAddress().getPersonAddressId() != null) {
                    person.setPreferredAddress(personAddressService.getPersonAddress(person.getPreferredAddress().getPersonAddressId()));
                } else if (person.getPreferredAddress().getId() != null) {
                    person.setPreferredAddress(personAddressService.getPersonAddressById(person.getPreferredAddress().getId()));
                }
                else if(person.getPreferredAddressId() != null)
                {
                    person.setPreferredAddress(personAddressService.getPersonAddressById(person.getPreferredAddressId()));
                }
            }
            else if(personAddressService != null && person.getPreferredAddressId() != null)
            {
                person.setPreferredAddress(personAddressService.getPersonAddressById(person.getPreferredAddressId()));
            }
            else if(personAddressService != null && person.getPersonId() != null)
            {
                person.setPreferredAddress(personAddressService.getPersonAddressByPerson(person.getPersonId()));
            }
            if (person.getPreferredName() != null && personNameService != null) {
                if (person.getPreferredName().getPersonNameId() != null) {
                    person.setPreferredName(personNameService.getPersonName(person.getPreferredName().getPersonNameId()));
                } else if(person.getPreferredName().getId() != null){
                    person.setPreferredName(personNameService.getPersonNameById(person.getPreferredName().getId()));
                }
                else if(person.getPreferredNameId() != null)
                {
                    person.setPreferredName(personNameService.getPersonNameById(person.getPreferredNameId()));

                }
            }
            else if(personNameService != null && person.getPreferredNameId() != null)
            {
                person.setPreferredName(personNameService.getPersonNameById(person.getPreferredNameId()));

            }
            else if(personNameService != null && person.getPersonId() != null)
            {
                person.setPreferredName(personNameService.getPersonNameByPerson(person.getPersonId()));
            }

        }
    }
    public PersonDto getPerson(Integer personId) throws SQLException {
        PersonDto person = personDao.queryBuilder().where().eq("personId", personId).queryForFirst();
      fillPerson(person);
        return person;
    }
    public PersonDto getPersonById(Integer id) throws SQLException {

        PersonDto person = personDao.queryBuilder().where().eq("id", id).queryForFirst();
        fillPerson(person);
        return person;
    }
    public PersonDto createNew() throws SQLException {
        PersonDto person = new PersonDto();
        person.setPreferredName(personNameService.createNew());
        person.setPreferredAddress(personAddressService.createNew());
        return save(person);
    }
}
