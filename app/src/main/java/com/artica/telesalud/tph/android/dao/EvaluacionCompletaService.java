package com.artica.telesalud.tph.android.dao;

import com.artica.telesalud.tph.android.lightweightmodel.EncounterDto;
import com.artica.telesalud.tph.android.lightweightmodel.EvaluacionCompletaDto;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by interoperabilidad on 11/08/14.
 */
public class EvaluacionCompletaService {
    private DatabaseHelper helper;
    private Dao<EvaluacionCompletaDto, Integer> evaluacionCompletaDao;
    private EncounterService encounterService;
    public EvaluacionCompletaService(DatabaseHelper helper) throws SQLException {
        setHelper(helper);
    }
    public DatabaseHelper getHelper() {
        return helper;
    }

    public void setHelper(DatabaseHelper helper) throws SQLException {
        this.helper = helper;
        evaluacionCompletaDao = helper.getEvaluacionCompletaDao();
        encounterService = new EncounterService(helper);
    }
    public synchronized EvaluacionCompletaDto save(EvaluacionCompletaDto evaluacion) throws SQLException {
        if(evaluacion != null) {
            if(evaluacion.getObsId() != null)
            {
                EvaluacionCompletaDto current = evaluacionCompletaDao.queryBuilder().where().eq("obsId", evaluacion.getObsId()).queryForFirst();
                if(current != null)
                {
                    evaluacion.setId(current.getId());
                }
            }
            EncounterDto encounter = evaluacion.getEncounter();
            if (encounter != null) {
                encounter = encounterService.save(encounter);
                if(encounter != null)
                {
                    evaluacion.setEncounterId(encounter.getId());
                    evaluacion.setEncounter(encounter);
                }
            }
            evaluacionCompletaDao.createOrUpdate(evaluacion);
        }
        return evaluacion;
    }
    public List<EvaluacionCompletaDto> getEvaluacionesEncounter(Integer encounterId) throws SQLException {
        List<EvaluacionCompletaDto> evaluaciones = evaluacionCompletaDao.queryBuilder().where().eq("encounterId", encounterId).query();
        return evaluaciones;
    }
    public EvaluacionCompletaDto createNew(EncounterDto encounter, Integer creator) throws SQLException {
        EvaluacionCompletaDto evaluacion = new EvaluacionCompletaDto();
        evaluacion.setCreator(creator);
        evaluacion.setEncounter(encounter);
        evaluacion.setDateCreated(new Date());
        return save(evaluacion);
    }
    private void fillEvaluacionCompleta(EvaluacionCompletaDto evaluacion) throws SQLException {
        if(evaluacion != null) {
            if (evaluacion.getEncounter() != null) {
                EncounterDto encounter = evaluacion.getEncounter();
                if (encounter.getEncounterId() != null) {
                    evaluacion.setEncounter(encounterService.getEncounter(encounter.getEncounterId()));
                } else if(encounter.getId() != null)  {
                    evaluacion.setEncounter(encounterService.getEncounterById(encounter.getId()));
                }
                else if(evaluacion.getEncounterId() != null)
                {
                    evaluacion.setEncounter(encounterService.getEncounterById(evaluacion.getEncounterId()));
                }
            }
            else if(evaluacion.getEncounterId() != null)
            {evaluacion.setEncounter(encounterService.getEncounterById(evaluacion.getEncounterId()));
            }
        }
    }
    public EvaluacionCompletaDto getEvaluacionCompletaById(Integer id) throws SQLException {
        EvaluacionCompletaDto evaluacion = evaluacionCompletaDao.queryBuilder().where().eq("id", id).queryForFirst();
        fillEvaluacionCompleta(evaluacion);
        return evaluacion;
    }
    public EvaluacionCompletaDto getEvaluacionCompleta(Integer obsId) throws SQLException {
        EvaluacionCompletaDto evaluacion = evaluacionCompletaDao.queryBuilder().where().eq("obsId", obsId).queryForFirst();
        fillEvaluacionCompleta(evaluacion);
        return evaluacion;
    }
}
