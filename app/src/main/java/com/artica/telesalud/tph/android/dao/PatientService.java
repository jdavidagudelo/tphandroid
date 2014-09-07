package com.artica.telesalud.tph.android.dao;

import com.artica.telesalud.tph.android.lightweightmodel.ConceptDto;
import com.artica.telesalud.tph.android.lightweightmodel.PatientDto;
import com.artica.telesalud.tph.android.lightweightmodel.PatientIdentifierDto;
import com.artica.telesalud.tph.android.lightweightmodel.PersonDto;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/**
 * Created by interoperabilidad on 11/08/14.
 */
public class PatientService {
    private DatabaseHelper helper;
    private Dao<PatientDto, Integer> patientDao;
    private PersonService personService;
    private PatientIdentifierService patientIdentifierService;
    private ConceptService conceptService;
    public DatabaseHelper getHelper() {
        return helper;
    }

    public void setHelper(DatabaseHelper helper) throws SQLException {
        if(helper == null)
        {
            return;
        }
        this.helper = helper;
        patientDao = helper.getPatientDao();
        personService = new PersonService(helper);
        patientIdentifierService = new PatientIdentifierService(helper);
        conceptService = new ConceptService(helper);
    }
    public PatientService(DatabaseHelper helper) throws SQLException {
        setHelper(helper);
    }
    public synchronized PatientDto save(PatientDto patient) throws SQLException {
        if(patient != null) {
            if(patient.getPatientId() != null) {
                PatientDto current = patientDao.queryBuilder().where().eq("patientId", patient.getPatientId()).queryForFirst();
                if (current != null) {
                    patient.setId(current.getId());
                }
            }
            if(patient.getPerson() != null && personService != null) {
                PersonDto person = personService.save(patient.getPerson());
                if(person != null) {
                    patient.setPerson(person);
                    patient.setPersonId(person.getId());
                }
            }
            if(patient.getPreferredIdentifier() != null && patientIdentifierService != null) {
                PatientIdentifierDto patientIdentifier = patientIdentifierService.save(patient.getPreferredIdentifier());
                if(patientIdentifier != null) {
                    patient.setPreferredIdentifier(patientIdentifier);
                    patient.setPreferredIdentifierId(patientIdentifier.getId());
                }
            }
            if (patient.getMaritalStatus() != null && conceptService != null) {
                ConceptDto maritalStatus = conceptService.save(patient.getMaritalStatus());
                if(maritalStatus != null) {
                    patient.setMaritalStatus(maritalStatus);
                    patient.setMaritalStatusId(maritalStatus.getId());
                }
            }
            patientDao.createOrUpdate(patient);
        }
        return patient;
    }
    private void fillPatient(PatientDto patient) throws SQLException {
        if(patient != null) {
            if(patient.getMaritalStatusId() != null)
            {
                patient.setMaritalStatus(conceptService.getConceptById(patient.getMaritalStatusId()));
            }
            if(patient.getPerson() != null && personService != null) {
                if(patient.getPerson().getPersonId() != null) {
                    patient.setPerson(personService.getPerson(patient.getPerson().getPersonId()));
                }
                else if(patient.getPerson().getId() != null)
                {
                    patient.setPerson(personService.getPersonById(patient.getPerson().getId()));
                }
                else if(patient.getPersonId() != null)
                {
                    patient.setPerson(personService.getPersonById(patient.getPersonId()));

                }
            }
            else if(patient.getPersonId() != null&& personService != null)
            {
                patient.setPerson(personService.getPersonById(patient.getPersonId()));
            }
            if(patient.getPreferredIdentifier() != null && patientIdentifierService != null) {
                if(patient.getPreferredIdentifier().getPatientIdentifierId() != null) {
                    patient.setPreferredIdentifier(patientIdentifierService.getPatientIdentifier(patient.getPreferredIdentifier().getPatientIdentifierId()));
                }
                else if(patient.getPreferredIdentifier().getId() != null)
                {
                    patient.setPreferredIdentifier(patientIdentifierService.getPatientIdentifierById(patient.getPreferredIdentifier().getId()));
                }
                else if(patient.getPreferredIdentifierId() != null)
                {

                    patient.setPreferredIdentifier(patientIdentifierService.getPatientIdentifierById(patient.getPreferredIdentifierId()));
                }
            }
            else if(patient.getPreferredIdentifierId() != null && patientIdentifierService != null)
            {
                patient.setPreferredIdentifier(patientIdentifierService.getPatientIdentifierById(patient.getPreferredIdentifierId()));
            }
            else if(patientIdentifierService != null && patient.getPatientId() != null)
            {
                patient.setPreferredIdentifier(patientIdentifierService.getPatientIdentifierByPatient(patient.getPatientId()));
            }
        }
    }
    public PatientDto getPatient(Integer patientId) throws SQLException {
        PatientDto patient = patientDao.queryBuilder().where().eq("patientId", patientId).queryForFirst();
        fillPatient(patient);
        return patient;
    }
    public PatientDto getPatientById(Integer id) throws SQLException {

        PatientDto patient = patientDao.queryBuilder().where().eq("id", id).queryForFirst();
        fillPatient(patient);
        return patient;
    }
    public PatientDto createNew() throws SQLException {
        PatientDto patient = new PatientDto();
        patient.setPreferredIdentifier(patientIdentifierService.createNew());
        patient.setPerson(personService.createNew());
        return save(patient);
    }
}
