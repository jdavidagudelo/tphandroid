package com.artica.telesalud.tph.android.dao;

import com.artica.telesalud.tph.android.lightweightmodel.EvaluacionCompletaDto;
import com.artica.telesalud.tph.android.lightweightmodel.HallazgoDto;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by interoperabilidad on 20/08/14.
 */
public class HallazgoService {
    private DatabaseHelper helper;
    private Dao<HallazgoDto, Integer> hallazgoDao;
    private EvaluacionCompletaService evaluacionCompletaService;
    public void setHelper(DatabaseHelper helper) throws SQLException {
        this.helper = helper;
        hallazgoDao = helper.getHallazgoDao();
        evaluacionCompletaService = new EvaluacionCompletaService(helper);
     }

    public DatabaseHelper getHelper() {
        return helper;
    }
    public HallazgoService(DatabaseHelper helper) throws SQLException {
        setHelper(helper);
    }
    public HallazgoDto createNew(EvaluacionCompletaDto evaluacion) throws SQLException {
        HallazgoDto hallazgo = new HallazgoDto();
        if(evaluacion != null) {
            hallazgo.setEvaluacion(evaluacion);
            hallazgo.setEvaluacionId(evaluacion.getId());
        }
        return save(hallazgo);
    }
    public synchronized HallazgoDto save(HallazgoDto hallazgo) throws SQLException {
        if (hallazgo != null) {
            if (hallazgo.getHallazgoId() != null) {
                HallazgoDto current = getHallazgo(hallazgo.getHallazgoId());
                if (current != null) {
                    hallazgo.setId(current.getId());
                }
            }
            if (hallazgo.getEvaluacion() != null) {
                EvaluacionCompletaDto evaluacionCompleta = evaluacionCompletaService.save(hallazgo.getEvaluacion());
                if (evaluacionCompleta != null) {
                    hallazgo.setEvaluacionId(evaluacionCompleta.getId());
                    hallazgo.setEvaluacion(evaluacionCompleta);
                }
            }
            hallazgoDao.createOrUpdate(hallazgo);

        }
        return hallazgo;
    }
    public void fillHallazgo(HallazgoDto hallazgo) throws SQLException {
        if(hallazgo != null)
        {
            if(hallazgo.getEvaluacion() != null)
            {
                if(hallazgo.getEvaluacion().getObsId() != null)
                {
                    hallazgo.setEvaluacion(evaluacionCompletaService.getEvaluacionCompleta(hallazgo.getEvaluacion().getObsId()));
                }
                else if(hallazgo.getEvaluacion().getId() != null)
                {
                    hallazgo.setEvaluacion(evaluacionCompletaService.getEvaluacionCompleta(hallazgo.getEvaluacion().getId()));
                }
                else if(hallazgo.getEvaluacionId() != null)
                {
                    hallazgo.setEvaluacion(evaluacionCompletaService.getEvaluacionCompleta(hallazgo.getEvaluacionId()));
                }
            }
            else if(hallazgo.getEvaluacionId() != null)
            {
                hallazgo.setEvaluacion(evaluacionCompletaService.getEvaluacionCompleta(hallazgo.getEvaluacionId()));

            }
        }
    }
    public HallazgoDto getHallazgo(Integer hallazgoId) throws SQLException {
        HallazgoDto hallazgo = hallazgoDao.queryBuilder().where().eq("hallazgoId", hallazgoId).queryForFirst();
        fillHallazgo(hallazgo);
        return hallazgo;
    }
    public HallazgoDto getHallazgoById(Integer id) throws SQLException {
        HallazgoDto hallazgo = hallazgoDao.queryBuilder().where().eq("id", id).queryForFirst();
        fillHallazgo(hallazgo);
        return hallazgo;
    }
    public Integer getNextOrden(Integer evaluacionId) throws SQLException {
        return getHallazgosByEvaluacion(evaluacionId).size();
    }
    public void deleteHallazgoById(Integer id) throws SQLException {
        HallazgoDto hallazgo = getHallazgoById(id);
        if(hallazgo != null) {
            hallazgoDao.deleteById(id);
        }
    }
    public List<HallazgoDto> getHallazgosByEvaluacion(Integer evaluacionId) throws SQLException {
        List<HallazgoDto> hallazgos = hallazgoDao.queryBuilder().where().eq("evaluacionId", evaluacionId).query();
        for(HallazgoDto hallazgo : hallazgos)
        {
            fillHallazgo(hallazgo);
        }
        return hallazgos;
    }
}
