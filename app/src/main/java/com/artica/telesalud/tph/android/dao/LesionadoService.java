package com.artica.telesalud.tph.android.dao;

import com.artica.telesalud.tph.android.lightweightmodel.AntecedentesDto;
import com.artica.telesalud.tph.android.lightweightmodel.EncounterDto;
import com.artica.telesalud.tph.android.lightweightmodel.EvaluacionCompletaDto;
import com.artica.telesalud.tph.android.lightweightmodel.EvaluacionDto;
import com.artica.telesalud.tph.android.lightweightmodel.EventDto;
import com.artica.telesalud.tph.android.lightweightmodel.LesionadoDto;
import com.artica.telesalud.tph.android.lightweightmodel.ListEvaluacionesDto;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by interoperabilidad on 11/08/14.
 */
public class LesionadoService {
    private DatabaseHelper helper;
    private Dao<LesionadoDto, Integer> lesionadoDao;
    private EncounterService encounterService;
    private EvaluacionCompletaService evaluacionCompletaService;
    private EvaluacionService evaluacionService;
    private EventService eventService;
    private AntecedentesService antecedentesService;
    public DatabaseHelper getHelper() {
        return helper;
    }

    public void setHelper(DatabaseHelper helper) throws SQLException {
        this.helper = helper;
        lesionadoDao = helper.getLesionadoDao();
        encounterService = new EncounterService(helper);
        evaluacionCompletaService = new EvaluacionCompletaService(helper);
        evaluacionService = new EvaluacionService(helper);
        eventService = new EventService(helper);
        antecedentesService = new AntecedentesService(helper);
    }
    public LesionadoService(DatabaseHelper helper) throws SQLException {
        setHelper(helper);
    }
    public synchronized LesionadoDto save(LesionadoDto lesionado) throws SQLException {
        if(helper == null)
        {
            return null;
        }
        if(lesionado.getLesionadoId() != null) {
            LesionadoDto current = lesionadoDao.queryBuilder().where().eq("lesionadoId", lesionado.getLesionadoId()).queryForFirst();
            if (current != null) {
                lesionado.setId(current.getId());
            }
        }
        if(lesionado.getEvento() != null) {
            EventDto event = lesionado.getEvento();
            event = eventService.save(event);
            if(event != null) {
                lesionado.setEvento(event);
                lesionado.setEventoId(event.getId());
            }
        }

        if(lesionado.getEncuentro() != null) {
            EncounterDto encounter = encounterService.save(lesionado.getEncuentro());
            if(encounter != null) {
                lesionado.setEncuentro(encounter);
                lesionado.setEncuentroId(encounter.getId());
            }
        }
        lesionadoDao.createOrUpdate(lesionado);
        saveAntecentes(lesionado);
        return lesionado;
    }
    public synchronized  LesionadoDto saveAntecentes(LesionadoDto lesionado) throws SQLException {
        for(AntecedentesDto antecedentes : lesionado.getAntecedentes())
        {
            antecedentes.setLesionadoId(lesionado.getId());
            antecedentesService.save(antecedentes);
        }
        return lesionado;
    }
    public synchronized LesionadoDto saveEvaluaciones(LesionadoDto lesionado) throws SQLException {
        for(EvaluacionDto evaluacion : lesionado.getEvaluaciones())
        {
            evaluacionService.save(evaluacion);
            evaluacionService.saveHallazgos(evaluacion);
            evaluacionService.saveProcedimientos(evaluacion);
        }
        return lesionado;
    }
    public List<LesionadoDto> getLesionadosEvent(Integer eventId) throws SQLException {
        if(eventId != null) {
            EventDto current = eventService.getEvent(eventId);
            Integer id = null;
            if (current != null) {
                id = current.getId();
            }
            return getLesionadosEventById(id);
        }

        return new ArrayList<LesionadoDto>();
    }
    public List<LesionadoDto> getLesionadosEventById(Integer id) throws SQLException {

        List<LesionadoDto> lesionados = lesionadoDao.queryBuilder().where().eq("eventoId", id).query();
        if(lesionados != null) {
            for (LesionadoDto lesionado : lesionados) {
                fillLesionado(lesionado);
            }
        }
        return lesionados;
    }

    public void fillLesionado(LesionadoDto lesionado) throws SQLException {
        if(lesionado != null) {
            if(lesionado.getEncuentro() != null) {
                if(lesionado.getEncuentro().getId() != null) {
                    List<EvaluacionCompletaDto> evaluaciones = evaluacionCompletaService.getEvaluacionesEncounter(lesionado.getEncuentro().getId());
                    ListEvaluacionesDto listEvaluaciones = new ListEvaluacionesDto();
                    for (EvaluacionCompletaDto evaluacionCompletaDto : evaluaciones) {
                        List<EvaluacionDto> evaluacionesPatient = evaluacionService.getEvaluaciones(evaluacionCompletaDto.getId());
                        listEvaluaciones.addAll(evaluacionesPatient);
                    }
                    lesionado.setEvaluaciones(listEvaluaciones);
                    lesionado.setEncuentro(encounterService.getEncounterById(lesionado.getEncuentro().getId()));
                }
            }
            else if(lesionado.getEncuentroId() != null)
            {

                List<EvaluacionCompletaDto> evaluaciones = evaluacionCompletaService.getEvaluacionesEncounter(lesionado.getEncuentroId());
                ListEvaluacionesDto listEvaluaciones = new ListEvaluacionesDto();
                for (EvaluacionCompletaDto evaluacionCompletaDto : evaluaciones) {
                    List<EvaluacionDto> evaluacionesPatient = evaluacionService.getEvaluaciones(evaluacionCompletaDto.getId());
                    listEvaluaciones.addAll(evaluacionesPatient);
                }
                lesionado.setEvaluaciones(listEvaluaciones);
                lesionado.setEncuentro(encounterService.getEncounterById(lesionado.getEncuentroId()));
            }
            if(lesionado.getEvento() != null)
            {
                if(lesionado.getEvento().getEventId() != null)
                {
                    lesionado.setEvento(eventService.getEvent(lesionado.getEvento().getEventId()));
                }
                else
                {
                    lesionado.setEvento(eventService.getEventById(lesionado.getEvento().getId()));
                }
            }
            else if(lesionado.getEventoId() != null)
            {
                lesionado.setEvento(eventService.getEventById(lesionado.getEventoId()));
            }
        }
    }
    public LesionadoDto createNew(EventDto event, Integer creator) throws SQLException {
        LesionadoDto lesionado = new LesionadoDto();
        lesionado.setCreator(creator);
        if(event != null)
        {
            lesionado.setEvento(event);
        }
        lesionado.setIsSynchronized(Boolean.FALSE);
        EncounterDto encounter = encounterService.createNew(creator);
        lesionado.setEncuentro(encounter);

        return save(lesionado);
    }
    public LesionadoDto getLesionado(Integer lesionadoId) throws SQLException {
        LesionadoDto lesionado = lesionadoDao.queryBuilder().where().eq("lesionadoId", lesionadoId).queryForFirst();
        fillLesionado(lesionado);
        return lesionado;
    }
    public LesionadoDto getLesionadoById(Integer id) throws SQLException {
        LesionadoDto lesionado = lesionadoDao.queryBuilder().where().eq("id", id).queryForFirst();
        fillLesionado(lesionado);
        return lesionado;
    }
    public List<LesionadoDto> getLesionadosToSave() throws SQLException {
        List<LesionadoDto> lesionados = lesionadoDao.queryBuilder().where().eq("isSynchronized", Boolean.FALSE).query();
        for(LesionadoDto lesionado : lesionados)
        {
            fillLesionado(lesionado);
        }
        return lesionados;
    }

}
