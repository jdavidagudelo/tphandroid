package com.artica.telesalud.tph.android.dao;

import com.artica.telesalud.tph.android.lightweightmodel.PatientIdentifierDto;
import com.artica.telesalud.tph.android.lightweightmodel.PatientIdentifierTypeDto;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/**
 * Created by interoperabilidad on 11/08/14.
 */
public class PatientIdentifierService {
    private DatabaseHelper helper;
    private Dao<PatientIdentifierDto, Integer> patientIdentifierDao;
    private PatientIdentifierTypeService patientIdentifierTypeService;

    public DatabaseHelper getHelper() {
        return helper;
    }

    public void setHelper(DatabaseHelper helper) throws SQLException {
        this.helper = helper;
        patientIdentifierDao = helper.getPatientIdentifierDao();
        patientIdentifierTypeService = new PatientIdentifierTypeService(helper);
    }
    public PatientIdentifierService(DatabaseHelper helper) throws SQLException {
        setHelper(helper);
    }
    public synchronized PatientIdentifierDto save(PatientIdentifierDto patientIdentifier) throws SQLException {
        if(patientIdentifier != null) {
            if(patientIdentifier.getPatientIdentifierId() != null)
            {
               PatientIdentifierDto current= getPatientIdentifier(patientIdentifier.getPatientIdentifierId());
                if(current != null)
                {
                    patientIdentifier.setId(current.getId());
                }
            }
            if (patientIdentifier.getPatientIdentifierType() != null) {
                PatientIdentifierTypeDto patientIdentifierType = patientIdentifierTypeService.save(patientIdentifier.getPatientIdentifierType());
                if(patientIdentifierType != null) {
                    patientIdentifier.setPatientIdentifierType(patientIdentifierType);
                    patientIdentifier.setPatientIdentifierTypeId(patientIdentifierType.getId());
                }
            }
            patientIdentifierDao.createOrUpdate(patientIdentifier);
        }
        return patientIdentifier;
    }
    public PatientIdentifierDto getPatientIdentifier(Integer patientIdentifierId) throws SQLException {
        PatientIdentifierDto patientIdentifierDto = patientIdentifierDao.queryBuilder().where().eq("patientIdentifierId", patientIdentifierId).queryForFirst();
        fillPatientIdentifier(patientIdentifierDto);
        return patientIdentifierDto;
    }
    private void fillPatientIdentifier(PatientIdentifierDto patientIdentifierDto) throws SQLException {
        if(patientIdentifierDto != null) {
            if (patientIdentifierDto.getPatientIdentifierType() != null && patientIdentifierTypeService != null) {
                if(patientIdentifierDto.getPatientIdentifierType().getPatientIdentifierTypeId() != null) {
                    patientIdentifierDto.setPatientIdentifierType(patientIdentifierTypeService.getPatientIdentifierType(patientIdentifierDto.getPatientIdentifierType().getPatientIdentifierTypeId()));
                }
                else if(patientIdentifierDto.getPatientIdentifierType().getId() != null)
                {
                    patientIdentifierDto.setPatientIdentifierType(patientIdentifierTypeService.getPatientIdentifierTypeById(patientIdentifierDto.getPatientIdentifierType().getId()));
                }
                else if(patientIdentifierDto.getPatientIdentifierTypeId() != null)
                {
                    patientIdentifierDto.setPatientIdentifierType(patientIdentifierTypeService.getPatientIdentifierTypeById(patientIdentifierDto.getPatientIdentifierTypeId()));
                }
            }
            else if(patientIdentifierDto.getPatientIdentifierTypeId() != null && patientIdentifierTypeService != null)
            {
                patientIdentifierDto.setPatientIdentifierType(patientIdentifierTypeService.getPatientIdentifierTypeById(patientIdentifierDto.getPatientIdentifierTypeId()));
            }
        }
    }
    public PatientIdentifierDto getPatientIdentifierById(Integer id) throws SQLException {
        PatientIdentifierDto patientIdentifierDto = patientIdentifierDao.queryBuilder().where().eq("id", id).queryForFirst();
        fillPatientIdentifier(patientIdentifierDto);
        return patientIdentifierDto;
    }
    public PatientIdentifierDto getPatientIdentifierByPatient(Integer patientId) throws SQLException {
        PatientIdentifierDto patientIdentifierDto = patientIdentifierDao.queryBuilder().where().eq("patientId", patientId).queryForFirst();
        fillPatientIdentifier(patientIdentifierDto);
        return patientIdentifierDto;
    }
    public PatientIdentifierDto createNew() throws SQLException {
        PatientIdentifierDto patientIdentifier = new PatientIdentifierDto();
        return save(patientIdentifier);
    }
}
