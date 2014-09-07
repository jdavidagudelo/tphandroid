package com.artica.telesalud.tph.android.dao;

import com.artica.telesalud.tph.android.lightweightmodel.EvaluacionCompletaDto;
import com.artica.telesalud.tph.android.lightweightmodel.ProcedimientosDto;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/**
 * Created by interoperabilidad on 1/09/14.
 */
public class ProcedimientosService {
    private DatabaseHelper helper;
    private Dao<ProcedimientosDto, Integer> procedimientosDao;
    private EvaluacionCompletaService evaluacionCompletaService;

    public DatabaseHelper getHelper() {
        return helper;
    }

    public void setHelper(DatabaseHelper helper) throws SQLException {
        this.helper = helper;
        procedimientosDao = helper.getProcedimientosDao();
        evaluacionCompletaService = new EvaluacionCompletaService(helper);
    }

    public ProcedimientosService(DatabaseHelper helper) throws SQLException {
        setHelper(helper);
    }
    public synchronized ProcedimientosDto save(ProcedimientosDto procedimientos) throws SQLException {
        if(procedimientos != null)
        {
            if(procedimientos.getEvaluacion() != null)
            {
                if(procedimientos.getEvaluacion().getObsId() != null)
                {
                    EvaluacionCompletaDto evaluacion = evaluacionCompletaService.getEvaluacionCompleta(procedimientos.getEvaluacion().getObsId());
                    if(evaluacion != null && evaluacion.getId() != null)
                    {
                        ProcedimientosDto current = getProcedimientosByEvaluacion(procedimientos.getEvaluacion().getId());
                        if(current != null)
                        {
                            procedimientos.setId(current.getId());
                        }
                    }
                    else if(procedimientos.getEvaluacionId() != null)
                    {
                        ProcedimientosDto current = getProcedimientosByEvaluacion(procedimientos.getEvaluacionId());
                        if(current != null)
                        {
                            procedimientos.setId(current.getId());
                        }
                    }
                }
                if(procedimientos.getEvaluacion().getId() != null)
                {
                    ProcedimientosDto current = getProcedimientosByEvaluacion(procedimientos.getEvaluacion().getId());
                    if(current != null)
                    {
                        procedimientos.setId(current.getId());
                    }
                }
                else if(procedimientos.getEvaluacionId() != null)
                {
                    ProcedimientosDto current = getProcedimientosByEvaluacion(procedimientos.getEvaluacionId());
                    if(current != null)
                    {
                        procedimientos.setId(current.getId());
                    }
                }
            }
            else if(procedimientos.getEvaluacionId() != null)
            {
                ProcedimientosDto current = getProcedimientosByEvaluacion(procedimientos.getEvaluacionId());
                if(current != null)
                {
                    procedimientos.setId(current.getId());
                }
            }
            procedimientosDao.createOrUpdate(procedimientos);
        }
        return procedimientos;
    }
    public void fillProcedimientos(ProcedimientosDto procedimientos) throws SQLException {
        if(procedimientos != null)
        {
            if(procedimientos.getEvaluacionId() != null)
            {
                procedimientos.setEvaluacion(evaluacionCompletaService.getEvaluacionCompletaById(procedimientos.getEvaluacionId()));
            }
        }
    }
    public ProcedimientosDto getProcedimientosByEvaluacion(Integer evaluacionId) throws SQLException {
        ProcedimientosDto procedimientos = procedimientosDao.queryBuilder().where().eq("evaluacionId", evaluacionId).queryForFirst();
        fillProcedimientos(procedimientos);
        return procedimientos;
    }
    public ProcedimientosDto getProcedimientosById(Integer id) throws SQLException {
        ProcedimientosDto procedimientos = procedimientosDao.queryBuilder().where().eq("id", id).queryForFirst();
        fillProcedimientos(procedimientos);
        return procedimientos;
    }
}
