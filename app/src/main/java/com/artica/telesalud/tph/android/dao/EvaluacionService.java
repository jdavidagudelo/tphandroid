package com.artica.telesalud.tph.android.dao;

import com.artica.telesalud.tph.android.lightweightmodel.EvaluacionCompletaDto;
import com.artica.telesalud.tph.android.lightweightmodel.EvaluacionDto;
import com.artica.telesalud.tph.android.lightweightmodel.HallazgoDto;
import com.artica.telesalud.tph.android.lightweightmodel.ProcedimientosDto;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by interoperabilidad on 11/08/14.
 */
public class EvaluacionService {

    private DatabaseHelper helper;
    private Dao<EvaluacionDto, Integer> evaluacionDao;
    private EvaluacionCompletaService evaluacionCompletaService;
    private HallazgoService hallazgoService;
    private ProcedimientosService procedimientosService;

    public DatabaseHelper getHelper() {
        return helper;
    }

    public void setHelper(DatabaseHelper helper) throws SQLException {
        this.helper = helper;
        evaluacionDao = helper.getEvaluacionDao();
        hallazgoService = new HallazgoService(helper);
        evaluacionCompletaService = new EvaluacionCompletaService(helper);
        procedimientosService = new ProcedimientosService(helper);
    }

    public EvaluacionService(DatabaseHelper helper) throws SQLException {
        setHelper(helper);
    }
    public synchronized EvaluacionDto save(EvaluacionDto evaluacion) throws SQLException {
        if(evaluacion != null) {
            if (evaluacion.getEvaluacion() != null) {
                EvaluacionCompletaDto evaluacionCompleta = evaluacionCompletaService.save(evaluacion.getEvaluacion());
                if (evaluacionCompleta != null) {
                    EvaluacionDto current = evaluacionDao.queryBuilder().where().eq("evaluacionId", evaluacionCompleta.getId()).queryForFirst();
                    if (current != null) {
                        evaluacion.setId(current.getId());
                    }
                    evaluacion.setEvaluacion(evaluacionCompleta);
                    evaluacion.setEvaluacionId(evaluacionCompleta.getId());
                }
            }
            evaluacionDao.createOrUpdate(evaluacion);

        }
        return evaluacion;
    }
    public void saveProcedimientos(EvaluacionDto evaluacion) throws SQLException {
        if(evaluacion.getProcedimientos() != null)
        {
            ProcedimientosDto procedimientos = evaluacion.getProcedimientos();
            if(evaluacion.getEvaluacion() != null)
            {
                procedimientos.setEvaluacionId(evaluacion.getEvaluacion().getId());
                procedimientos.setEvaluacion(evaluacion.getEvaluacion());
            }
            procedimientosService.save(procedimientos);
            evaluacion.setProcedimientos(procedimientos);
            evaluacion.setProcedimientosId(procedimientos.getId());
            evaluacionDao.createOrUpdate(evaluacion);
        }
    }
    public void saveHallazgos(EvaluacionDto evaluacion) throws SQLException {
        if(evaluacion.getHallazgos() != null) {
            for (HallazgoDto hallazgo : evaluacion.getHallazgos()) {
                    if (evaluacion.getEvaluacion() != null) {
                        hallazgo.setEvaluacion(evaluacion.getEvaluacion());
                        hallazgo.setEvaluacionId(evaluacion.getEvaluacion().getId());
                    }
                    hallazgoService.save(hallazgo);
            }
        }
    }
    private void fillEvaluacion(EvaluacionDto evaluacion) throws SQLException {
        if(evaluacion != null)
        {
            if(evaluacion.getEvaluacion() != null)
            {
                if(evaluacion.getEvaluacion().getObsId() != null)
                {
                    evaluacion.setEvaluacion(evaluacionCompletaService.getEvaluacionCompleta(evaluacion.getEvaluacion().getObsId()));
                }
                else if(evaluacion.getEvaluacion().getId() != null)
                {
                    evaluacion.setEvaluacion(evaluacionCompletaService.getEvaluacionCompletaById(evaluacion.getEvaluacion().getId()));
                }
                else if(evaluacion.getEvaluacionId() != null)
                {
                    evaluacion.setEvaluacion(evaluacionCompletaService.getEvaluacionCompletaById(evaluacion.getEvaluacionId()));
                }
            }
            else if(evaluacion.getEvaluacionId() != null) {
                evaluacion.setEvaluacion(evaluacionCompletaService.getEvaluacionCompletaById(evaluacion.getEvaluacionId()));
            }
            if(evaluacion.getEvaluacion() != null && evaluacion.getEvaluacion().getId() != null)
            {
                ProcedimientosDto procedimientos = procedimientosService.getProcedimientosByEvaluacion(evaluacion.getEvaluacion().getId());
                if(procedimientos != null)
                {
                    evaluacion.setProcedimientos(procedimientos);
                }
                else if(evaluacion.getProcedimientosId() != null)
                {
                    evaluacion.setProcedimientos(procedimientosService.getProcedimientosById(evaluacion.getProcedimientosId()));
                }
                List<HallazgoDto> hallazgos = hallazgoService.getHallazgosByEvaluacion(evaluacion.getEvaluacion().getId());
                if(hallazgos != null) {
                    evaluacion.setHallazgos(hallazgos);
                }
            }
        }
    }
    public List<EvaluacionDto> getEvaluaciones(Integer evaluacionId) throws SQLException {
        List<EvaluacionDto> evaluaciones = evaluacionDao.queryBuilder().where().eq("evaluacionId", evaluacionId).query();
        for(EvaluacionDto evaluacion : evaluaciones)
        {
            fillEvaluacion(evaluacion);
        }
        return evaluaciones;
    }
}
