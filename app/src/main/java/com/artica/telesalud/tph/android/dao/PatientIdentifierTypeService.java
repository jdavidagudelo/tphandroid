package com.artica.telesalud.tph.android.dao;

import com.artica.telesalud.tph.android.lightweightmodel.PatientIdentifierTypeDto;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by interoperabilidad on 11/08/14.
 */
public class PatientIdentifierTypeService {
    private DatabaseHelper helper;
    private Dao<PatientIdentifierTypeDto, Integer> patientIdentifierTypeDao;

    public DatabaseHelper getHelper() {
        return helper;
    }

    public void setHelper(DatabaseHelper helper) throws SQLException {
        this.helper = helper;
        patientIdentifierTypeDao = helper.getPatientIdentifierTypeDao();
    }
    public PatientIdentifierTypeService(DatabaseHelper helper) throws SQLException {
        setHelper(helper);
    }
    public synchronized PatientIdentifierTypeDto save(PatientIdentifierTypeDto patientIdentifierType) throws SQLException {
        if(patientIdentifierType != null)
        {
            if(patientIdentifierType.getPatientIdentifierTypeId() != null)
            {
                PatientIdentifierTypeDto current = getPatientIdentifierType(patientIdentifierType.getPatientIdentifierTypeId());
                if(current != null)
                {
                    patientIdentifierType.setId(current.getId());
                }
            }
            patientIdentifierTypeDao.createOrUpdate(patientIdentifierType);
        }
        return patientIdentifierType;
    }
    public List<PatientIdentifierTypeDto> getPatientIdentifierTypes() throws SQLException {
        return patientIdentifierTypeDao.queryForAll();
    }
    public PatientIdentifierTypeDto getPatientIdentifierType(Integer patientIdentifierTypeId) throws SQLException {
        return patientIdentifierTypeDao.queryBuilder().where().eq("patientIdentifierTypeId", patientIdentifierTypeId).queryForFirst();
    }
    public PatientIdentifierTypeDto getPatientIdentifierTypeById(Integer id) throws SQLException {
        return patientIdentifierTypeDao.queryBuilder().where().eq("id",id).queryForFirst();

    }
}
