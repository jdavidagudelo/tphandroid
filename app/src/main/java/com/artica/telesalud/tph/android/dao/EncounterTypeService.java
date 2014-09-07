package com.artica.telesalud.tph.android.dao;

import com.artica.telesalud.tph.android.lightweightmodel.EncounterTypeDto;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/**
 * Created by interoperabilidad on 11/08/14.
 */
public class EncounterTypeService {
    private DatabaseHelper helper;
    private Dao<EncounterTypeDto, Integer> encounterTypeDao;


    public DatabaseHelper getHelper() {
        return helper;
    }

    public void setHelper(DatabaseHelper helper) throws SQLException {
        if(helper != null) {
            this.helper = helper;
            encounterTypeDao = helper.getEncounterTypeDao();
        }
    }
    public EncounterTypeService(DatabaseHelper helper) throws SQLException {
        setHelper(helper);
    }
    public synchronized EncounterTypeDto save(EncounterTypeDto encounter) throws SQLException {
        if(encounter != null) {
            if(encounter.getEncounterTypeId() != null) {
                EncounterTypeDto current = getEncounterType(encounter.getEncounterTypeId());
                if(current != null)
                {
                    encounter.setId(current.getId());
                }
            }
            encounterTypeDao.createOrUpdate(encounter);

        }
        return encounter;
    }
    public EncounterTypeDto getEncounterType(Integer encounterTypeId) throws SQLException {
        EncounterTypeDto encounterType = encounterTypeDao.queryBuilder().where().eq("encounterTypeId", encounterTypeId).queryForFirst();
        return encounterType;
    }
    public EncounterTypeDto getEncounterById(Integer id) throws SQLException {

        EncounterTypeDto encounterType = encounterTypeDao.queryBuilder().where().eq("id", id).queryForFirst();
        return encounterType;
    }
}
