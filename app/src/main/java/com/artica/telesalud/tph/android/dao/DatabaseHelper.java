package com.artica.telesalud.tph.android.dao;

/**
 * Created by interoperabilidad on 20/05/14.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.artica.telesalud.tph.android.lightweightmodel.AntecedenteDto;
import com.artica.telesalud.tph.android.lightweightmodel.AntecedentesDto;
import com.artica.telesalud.tph.android.lightweightmodel.CityDto;
import com.artica.telesalud.tph.android.lightweightmodel.ConceptDto;
import com.artica.telesalud.tph.android.lightweightmodel.CountryDto;
import com.artica.telesalud.tph.android.lightweightmodel.EncounterDto;
import com.artica.telesalud.tph.android.lightweightmodel.EncounterTypeDto;
import com.artica.telesalud.tph.android.lightweightmodel.EvaluacionCompletaDto;
import com.artica.telesalud.tph.android.lightweightmodel.EvaluacionDto;
import com.artica.telesalud.tph.android.lightweightmodel.EventDto;
import com.artica.telesalud.tph.android.lightweightmodel.HallazgoDto;
import com.artica.telesalud.tph.android.lightweightmodel.HospitalDto;
import com.artica.telesalud.tph.android.lightweightmodel.LesionadoDto;
import com.artica.telesalud.tph.android.lightweightmodel.PatientDto;
import com.artica.telesalud.tph.android.lightweightmodel.PatientIdentifierDto;
import com.artica.telesalud.tph.android.lightweightmodel.PatientIdentifierTypeDto;
import com.artica.telesalud.tph.android.lightweightmodel.PersonAddressDto;
import com.artica.telesalud.tph.android.lightweightmodel.PersonDto;
import com.artica.telesalud.tph.android.lightweightmodel.PersonNameDto;
import com.artica.telesalud.tph.android.lightweightmodel.ProcedimientosDto;
import com.artica.telesalud.tph.android.lightweightmodel.SignosVitalesDto;
import com.artica.telesalud.tph.android.lightweightmodel.StateDto;
import com.artica.telesalud.tph.android.lightweightmodel.TripulacionDto;
import com.artica.telesalud.tph.android.lightweightmodel.UserDto;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Database helper class used to manage the creation and upgrading of your database. This class also usually provides
 * the DAOs used by the other classes.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    // name of the database button_triage_red for your application -- change to something appropriate for your app
    private static final String DATABASE_NAME = "RCMedicalRecordTPH.db";
    // any time you make changes to your database objects, you may have to increase the database version
    private static final int DATABASE_VERSION = 1;
    private static final String PASSWORD = "abc123";
    // the DAO object we use to access the SimpleData table
    private Dao<ConceptDto, Integer> causaAtencionDao = null;
    private Dao<StateDto, Integer> stateDao = null;
    private Dao<CityDto, Integer> cityDao = null;
    private Dao<CountryDto, Integer> countryDao = null;
    private Dao<EventDto, Integer> eventDao = null;
    private Dao<LesionadoDto, Integer> lesionadoDao = null;
     private Dao<HallazgoDto, Integer> hallazgoDao;
    private Dao<EncounterDto, Integer> encounterDao;
    private Dao<EvaluacionCompletaDto, Integer> evaluacionCompletaDao;
    private Dao<EvaluacionDto, Integer> evaluacionDao;
    private Dao<UserDto, Integer> userDao;
    private Dao<PatientDto, Integer> patientDao;
    private Dao<PersonDto, Integer> personDao;
    private Dao<PatientIdentifierTypeDto, Integer> patientIdentifierTypeDao;
    private Dao<PatientIdentifierDto, Integer> patientIdentifierDao;
    private Dao<PersonAddressDto, Integer> personAddressDao;
    private Dao<PersonNameDto, Integer> personNameDao;
    private Dao<HospitalDto, Integer> hospitalDao;
    private Dao<TripulacionDto, Integer> tripulacionDao;
    private Dao<EncounterTypeDto, Integer> encounterTypeDao;
    private Dao<SignosVitalesDto, Integer> signosVitalesDao;
    private Dao<ProcedimientosDto, Integer> procedimientosDao;
    private Dao<AntecedentesDto, Integer> antecedentesDao;
    private Dao<AntecedenteDto, Integer> antecedenteDao;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is first created. Usually you should call createTable statements here to create
     * the tables that will store your data.
     */
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onCreate");
            TableUtils.createTable(connectionSource, ConceptDto.class);
            TableUtils.createTable(connectionSource, StateDto.class);
            TableUtils.createTable(connectionSource, CityDto.class);
            TableUtils.createTable(connectionSource, EventDto.class);
            TableUtils.createTable(connectionSource, CountryDto.class);
            TableUtils.createTable(connectionSource, UserDto.class);
            TableUtils.createTable(connectionSource, HospitalDto.class);
            TableUtils.createTable(connectionSource, EncounterTypeDto.class);
            TableUtils.createTable(connectionSource, PersonAddressDto.class);
            TableUtils.createTable(connectionSource, PersonNameDto.class);
            TableUtils.createTable(connectionSource, PersonDto.class);
            TableUtils.createTable(connectionSource, PatientIdentifierTypeDto.class);
            TableUtils.createTable(connectionSource, PatientIdentifierDto.class);
            TableUtils.createTable(connectionSource, PatientDto.class);
            TableUtils.createTable(connectionSource, EncounterDto.class);
            TableUtils.createTable(connectionSource, LesionadoDto.class);
            TableUtils.createTable(connectionSource, TripulacionDto.class);
            TableUtils.createTable(connectionSource, EvaluacionCompletaDto.class);
            TableUtils.createTable(connectionSource, EvaluacionDto.class);
            TableUtils.createTable(connectionSource, HallazgoDto.class);
            TableUtils.createTable(connectionSource, SignosVitalesDto.class);
            TableUtils.createTable(connectionSource, ProcedimientosDto.class);
            TableUtils.createTable(connectionSource, AntecedenteDto.class);
            TableUtils.createTable(connectionSource, AntecedentesDto.class);

        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * This is called when your application is upgraded and it has a higher version number. This allows you to adjust
     * the various data to match the new version number.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, ConceptDto.class, true);
            TableUtils.dropTable(connectionSource, CountryDto.class, true);
            TableUtils.dropTable(connectionSource, StateDto.class, true);
            TableUtils.dropTable(connectionSource, CityDto.class, true);
            TableUtils.dropTable(connectionSource, EventDto.class, true);
            TableUtils.dropTable(connectionSource, UserDto.class, true);
            TableUtils.dropTable(connectionSource, LesionadoDto.class, true);
            TableUtils.dropTable(connectionSource, EncounterDto.class, true);
            TableUtils.dropTable(connectionSource, EncounterTypeDto.class, true);
            TableUtils.dropTable(connectionSource, HospitalDto.class, true);
            TableUtils.dropTable(connectionSource, PatientDto.class, true);
            TableUtils.dropTable(connectionSource, PatientIdentifierDto.class, true);
            TableUtils.dropTable(connectionSource, PatientIdentifierTypeDto.class, true);
            TableUtils.dropTable(connectionSource, PersonAddressDto.class, true);
            TableUtils.dropTable(connectionSource, PersonNameDto.class, true);
            TableUtils.dropTable(connectionSource, PersonDto.class, true);
            TableUtils.dropTable(connectionSource, TripulacionDto.class, true);
            TableUtils.dropTable(connectionSource, EvaluacionCompletaDto.class, true);
            TableUtils.dropTable(connectionSource, EvaluacionDto.class, true);
             TableUtils.dropTable(connectionSource, HallazgoDto.class, true);
            TableUtils.dropTable(connectionSource, SignosVitalesDto.class, true);
            TableUtils.dropTable(connectionSource, ProcedimientosDto.class, true);
            TableUtils.dropTable(connectionSource, AntecedenteDto.class, true);
            TableUtils.dropTable(connectionSource, AntecedentesDto.class, true);
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the Database Access Object (DAO) for our SimpleData class. It will create it or just give the cached
     * value.
     */
    public Dao<ConceptDto, Integer> getConceptDao() throws SQLException {
        if (causaAtencionDao == null) {
            causaAtencionDao = getDao(ConceptDto.class);
        }
        return causaAtencionDao;
    }

    public Dao<StateDto, Integer> getStateDao() throws SQLException {
        if (stateDao == null) {
            stateDao = getDao(StateDto.class);
        }
        return stateDao;
    }

    public Dao<CityDto, Integer> getCityDao() throws SQLException {
        if (cityDao == null) {
            cityDao = getDao(CityDto.class);
        }
        return cityDao;
    }

    public Dao<CountryDto, Integer> getCountryDao() throws SQLException {
        if (countryDao == null) {
            countryDao = getDao(CountryDto.class);
        }
        return countryDao;
    }

    public Dao<EventDto, Integer> getEventDao() throws SQLException {
        if (eventDao == null) {
            eventDao = getDao(EventDto.class);
        }
        return eventDao;
    }
    public Dao<UserDto, Integer> getUserDao() throws SQLException {
        if (userDao == null) {
            userDao = getDao(UserDto.class);
        }
        return userDao;
    }
    public Dao<LesionadoDto, Integer> getLesionadoDao() throws SQLException {
        if(lesionadoDao == null)
        {
            lesionadoDao = getDao(LesionadoDto.class);
        }

        return lesionadoDao;
    }

    public Dao<EncounterDto, Integer> getEncounterDao() throws SQLException {
        if(encounterDao == null)
        {
            encounterDao = getDao(EncounterDto.class);
        }
        return encounterDao;
    }
    public Dao<EvaluacionDto, Integer> getEvaluacionDao() throws SQLException {
        if(evaluacionDao == null)
        {
            evaluacionDao = getDao(EvaluacionDto.class);
        }
        return evaluacionDao;
    }public Dao<EvaluacionCompletaDto, Integer> getEvaluacionCompletaDao() throws SQLException {
        if(evaluacionCompletaDao == null)
        {
            evaluacionCompletaDao = getDao(EvaluacionCompletaDto.class);
        }
        return evaluacionCompletaDao;
    }

   public Dao<PatientDto, Integer>getPatientDao() throws SQLException {
       if(patientDao == null)
       {
           patientDao = getDao(PatientDto.class);
       }
       return patientDao;
   }

    public  Dao<PersonDto, Integer> getPersonDao() throws SQLException {
        if(personDao == null)
        {
            personDao = getDao(PersonDto.class);
        }
        return personDao;
    }
    public Dao<PatientIdentifierTypeDto, Integer> getPatientIdentifierTypeDao() throws SQLException {
        if(patientIdentifierTypeDao == null)
        {
            patientIdentifierTypeDao = getDao(PatientIdentifierTypeDto.class);
        }
        return patientIdentifierTypeDao;
    }
    public Dao<PatientIdentifierDto, Integer> getPatientIdentifierDao() throws SQLException {
        if(patientIdentifierDao == null)
        {
            patientIdentifierDao = getDao(PatientIdentifierDto.class);
        }
        return patientIdentifierDao;
    }
    public Dao<PersonAddressDto, Integer> getPersonAddressDao() throws SQLException {
        if(personAddressDao == null)
        {
            personAddressDao = getDao(PersonAddressDto.class);
        }
        return personAddressDao;
    }
    public Dao<PersonNameDto, Integer> getPersonNameDao() throws SQLException {
        if(personNameDao == null)
        {
            personNameDao = getDao(PersonNameDto.class);
        }
        return personNameDao;
    }
    public Dao<HospitalDto, Integer> getHospitalDao() throws SQLException {
        if(hospitalDao == null)
        {
            hospitalDao = getDao(HospitalDto.class);
        }
        return hospitalDao;
    }
    public Dao<TripulacionDto, Integer> getTripulacionDao() throws SQLException {
        if(tripulacionDao == null)
        {
            tripulacionDao = getDao(TripulacionDto.class);
        }
        return tripulacionDao;
    }
    public Dao<EncounterTypeDto, Integer> getEncounterTypeDao() throws SQLException {
        if(encounterTypeDao == null)
        {
            encounterTypeDao = getDao(EncounterTypeDto.class);
        }
        return encounterTypeDao;
    }
     public Dao<HallazgoDto, Integer> getHallazgoDao() throws SQLException {
        if(hallazgoDao == null)
        {
            hallazgoDao = getDao(HallazgoDto.class);
        }
        return hallazgoDao;
    }
    public Dao<SignosVitalesDto, Integer> getSignosVitalesDao() throws SQLException {
        if(signosVitalesDao == null)
        {
            signosVitalesDao = getDao(SignosVitalesDto.class);
        }
        return signosVitalesDao;
    }
    public Dao<ProcedimientosDto, Integer> getProcedimientosDao() throws SQLException {
        if(procedimientosDao == null)
        {
            procedimientosDao = getDao(ProcedimientosDto.class);
        }
        return procedimientosDao;
    }

    public Dao<AntecedentesDto, Integer> getAntecedentesDao() throws SQLException {
        if(antecedentesDao == null)
        {
            antecedentesDao = getDao(AntecedentesDto.class);
        }
        return antecedentesDao;
    }


    public Dao<AntecedenteDto, Integer> getAntecedenteDao() throws SQLException {
        if(antecedenteDao == null)
        {
            antecedenteDao = getDao(AntecedenteDto.class);
        }
        return antecedenteDao;
    }


    /**
     * Close the database connections and clear any cached DAOs.
     */
    @Override
    public void close() {
        super.close();
        causaAtencionDao = null;
        cityDao = null;
        stateDao = null;
        countryDao = null;
        eventDao = null;
        userDao = null;
    }
}