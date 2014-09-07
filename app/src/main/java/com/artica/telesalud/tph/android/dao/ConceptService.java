package com.artica.telesalud.tph.android.dao;

import com.artica.telesalud.tph.android.lightweightmodel.ConceptDto;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by interoperabilidad on 11/08/14.
 */
public class ConceptService {
    private DatabaseHelper helper;
    private Dao<ConceptDto, Integer> conceptDao;

    public DatabaseHelper getHelper() {
        return helper;
    }

    public void setHelper(DatabaseHelper helper) throws SQLException {
        if(helper != null) {
            this.helper = helper;
            conceptDao = helper.getConceptDao();
        }
    }
    public ConceptService(DatabaseHelper helper) throws SQLException {
        setHelper(helper);
    }
    public synchronized ConceptDto save(ConceptDto concept) throws SQLException {
        if(concept != null)
        {
            if(concept.getConceptId() != null)
            {
                ConceptDto current = getConcept(concept.getConceptId());
                if(current != null)
                {
                    concept.setId(current.getId());
                }
            }
            conceptDao.createOrUpdate(concept);
        }
        return concept;
    }
    public ConceptDto getConcept(Integer conceptId) throws SQLException {
        ConceptDto concept = conceptDao.queryBuilder().where().eq("conceptId", conceptId).queryForFirst();
        return concept;
    }
    public ConceptDto getConceptById(Integer id) throws SQLException {
        ConceptDto concept = conceptDao.queryBuilder().where().eq("id", id).queryForFirst();
        return concept;
    }
    public List<ConceptDto> getCausasAtencion() throws SQLException {
        List<ConceptDto> causasAtencion = conceptDao.queryBuilder().where().eq("conceptTypeId", ConceptDto.TYPE_CAUSA_ATENCION_ID).query();
        return causasAtencion;
    }
    public List<ConceptDto> getMaritalStatuses() throws SQLException {
        return conceptDao.queryBuilder().where().eq("conceptTypeId", ConceptDto.TYPE_MARITAL_STATUS_ID).query();
    }
    public List<ConceptDto> getAseguradoras() throws SQLException {
        return conceptDao.queryBuilder().where().eq("conceptTypeId", ConceptDto.TYPE_ASEGURADORA_ID).query();
    }
    public List<ConceptDto> getEpss() throws SQLException {
        return conceptDao.queryBuilder().where().eq("conceptTypeId", ConceptDto.TYPE_EPS_ID).query();
    }
    public List<ConceptDto> getPlanesBeneficios() throws SQLException {
        return conceptDao.queryBuilder().where().eq("conceptTypeId", ConceptDto.TYPE_PLAN_BENEFICIOS_ID).query();
    }
    public List<ConceptDto> getTiposAntecedentes() throws SQLException {
        return conceptDao.queryBuilder().where().eq("conceptTypeId", ConceptDto.TYPE_ANTECEDENT_TYPE_ID).query();
    }

    public ConceptDto getCausaAtencion(Integer conceptId) throws SQLException {
        Where<ConceptDto, Integer> where = conceptDao.queryBuilder().where().eq("conceptId", conceptId).and().eq("conceptTypeId", ConceptDto.TYPE_CAUSA_ATENCION_ID);
        ConceptDto causaAtencion = where.queryForFirst();
        return causaAtencion;
    }
}
