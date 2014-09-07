package com.artica.telesalud.tph.android.dao;

import com.artica.telesalud.tph.android.lightweightmodel.EncounterDto;
import com.artica.telesalud.tph.android.lightweightmodel.EncounterTypeDto;
import com.artica.telesalud.tph.android.lightweightmodel.PatientDto;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.Date;

/**
 * Created by interoperabilidad on 11/08/14.
 */
public class EncounterService {
    private DatabaseHelper helper;
    private Dao<EncounterDto, Integer> encounterDao;
    private PatientService patientService;
    private EncounterTypeService encounterTypeService;

    public DatabaseHelper getHelper() {
        return helper;
    }

    public void setHelper(DatabaseHelper helper) throws SQLException {
        if(helper == null)
        {
            return;
        }
        this.helper = helper;
        encounterDao = helper.getEncounterDao();
        patientService = new PatientService(helper);
        encounterTypeService = new EncounterTypeService(helper);
    }
    public EncounterService(DatabaseHelper helper) throws SQLException {
        setHelper(helper);
    }
    public synchronized EncounterDto save(EncounterDto encounter) throws SQLException {
        if(encounterDao != null && encounter != null) {
            if(encounter.getEncounterId() != null) {
                EncounterDto current = encounterDao.queryBuilder().where().eq("encounterId", encounter.getEncounterId()).queryForFirst();
                if (current != null) {
                    encounter.setId(current.getId());
                }
            }
            if(encounter.getEncounterType() != null && encounterTypeService != null) {
                EncounterTypeDto encounterType = encounterTypeService.save(encounter.getEncounterType());
                if(encounterType != null) {
                    encounter.setEncounterType(encounterType);
                    encounter.setEncounterTypeId(encounterType.getId());
                }
            }
            if(encounter.getPatient() != null && patientService != null) {
                PatientDto patient = patientService.save(encounter.getPatient());
                if(patient != null) {
                    encounter.setPatient(patient);
                    encounter.setPatientId(patient.getId());
                }
            }
            encounterDao.createOrUpdate(encounter);
        }
        return encounter;
    }
    private void fillEncounter(EncounterDto encounter) throws SQLException {
        if(encounter != null) {
            if(encounter.getPatient() != null && patientService != null) {
                if(encounter.getPatient().getPatientId() != null) {
                    encounter.setPatient(patientService.getPatient(encounter.getPatient().getPatientId()));
                }
                else if(encounter.getPatient().getId() != null)
                {
                    encounter.setPatient(patientService.getPatientById(encounter.getPatient().getId()));
                }
                else if(encounter.getPatientId() != null)
                {
                    encounter.setPatient(patientService.getPatientById(encounter.getPatientId()));
                }
            }
            else if(encounter.getPatientId() != null && patientService != null)
            {
                encounter.setPatient(patientService.getPatientById(encounter.getPatientId()));
            }
            if(encounter.getEncounterType() != null && encounterTypeService != null) {
                if(encounter.getEncounterType().getEncounterTypeId() != null) {
                    encounter.setEncounterType(encounterTypeService.getEncounterType(encounter.getEncounterType().getEncounterTypeId()));
                }
                else if(encounter.getEncounterType().getId() != null)
                {
                    encounter.setEncounterType(encounterTypeService.getEncounterById(encounter.getEncounterType().getId()));
                }
                else if(encounter.getEncounterTypeId() != null)
                {

                    encounter.setEncounterType(encounterTypeService.getEncounterById(encounter.getEncounterTypeId()));
                }
            }
            else if(encounter.getEncounterTypeId() != null && encounterTypeService != null)
            {

                encounter.setEncounterType(encounterTypeService.getEncounterById(encounter.getEncounterTypeId()));
            }
        }
    }
    public EncounterDto getEncounter(Integer encounterId) throws SQLException {
        EncounterDto encounter = encounterDao.queryBuilder().where().eq("encounterId", encounterId).queryForFirst();
        fillEncounter(encounter);
        return encounter;
    }
    public EncounterDto getEncounterById(Integer id) throws SQLException {
        EncounterDto encounter = encounterDao.queryBuilder().where().eq("id", id).queryForFirst();
        fillEncounter(encounter);
        return encounter;
    }
    public EncounterDto createNew(Integer creator) throws SQLException {
        EncounterDto encounter = new EncounterDto();
        encounter.setCreator(creator);
        encounter.setIsSynchronized(Boolean.FALSE);
        encounter.setEncounterDatetime(new Date());
        encounter.setPatient(patientService.createNew());
        return save(encounter);
    }
}
