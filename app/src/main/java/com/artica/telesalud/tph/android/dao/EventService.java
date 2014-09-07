package com.artica.telesalud.tph.android.dao;

import android.util.SparseArray;

import com.artica.telesalud.tph.android.lightweightmodel.CityDto;
import com.artica.telesalud.tph.android.lightweightmodel.ConceptDto;
import com.artica.telesalud.tph.android.lightweightmodel.EventDto;
import com.artica.telesalud.tph.android.lightweightmodel.LesionadoDto;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by interoperabilidad on 11/08/14.
 */
public class EventService {
    public DatabaseHelper helper;
    private Dao<EventDto, Integer> eventDao;
    private ConceptService conceptService;
    private CityService cityService;
    private Dao<LesionadoDto, Integer> lesionadoDao;

    public DatabaseHelper getHelper() {
        return helper;
    }

    public void setHelper(DatabaseHelper helper) throws SQLException {
        if(helper != null) {
            this.helper = helper;
            eventDao = helper.getEventDao();
            conceptService = new ConceptService(helper);
            cityService = new CityService(helper);
            lesionadoDao = helper.getLesionadoDao();
        }
    }
    public EventService(DatabaseHelper helper) throws SQLException {
        setHelper(helper);
    }

    private void fillEvent(EventDto event) throws SQLException {
        if(event != null) {
            if (event.getCausaAtencion() != null && conceptService != null) {
                if(event.getCausaAtencion().getConceptId() != null) {
                    event.setCausaAtencion(conceptService.getConcept(event.getCausaAtencion().getConceptId()));
                }
                else if(event.getCausaAtencion().getId() != null)
                {
                    event.setCausaAtencion(conceptService.getConceptById(event.getCausaAtencion().getId()));
                }
                else if(event.getCausaAtencionId() != null)
                {
                    event.setCausaAtencion(conceptService.getConceptById(event.getCausaAtencionId()));
                }
            }
            else if(event.getCausaAtencionId() != null && conceptService != null)
            {
                event.setCausaAtencion(conceptService.getConceptById(event.getCausaAtencionId()));
            }
            if(event.getCity() != null && cityService != null)
            {
                if(event.getCity().getCityId() != null) {
                    event.setCity(cityService.getCity(event.getCity().getCityId()));
                }
                else if(event.getCity().getId() != null)
                {
                    event.setCity(cityService.getCityById(event.getCity().getId()));
                }
                else if(event.getCityId() != null)
                {
                    event.setCity(cityService.getCityById(event.getCityId()));
                }
            }
            else if(event.getCityId() != null && cityService != null)
            {
                event.setCity(cityService.getCityById(event.getCityId()));
            }
            if(lesionadoDao != null && event.getId() != null) {
                Integer patientsCount = getPatientsCountEvent(event.getId());
                event.setPatientsCount(patientsCount);
            }
        }
    }
    public Integer getPatientsCountEvent(Integer id) throws SQLException {
        List<LesionadoDto> lesionados = lesionadoDao.queryBuilder().where().eq("eventoId", id).query();
        return lesionados.size();
    }
    public synchronized void saveBatch(List<EventDto> events) throws SQLException {
        List<EventDto> currentEvents = getEventsActive();
        SparseArray<EventDto> map = new SparseArray<EventDto>();
        for(EventDto event : currentEvents)
        {
            map.put(event.getEventId(), event);
        }
        for(EventDto event : events)
        {
            EventDto current = map.get(event.getEventId());
            if(current != null) {
                event.setId(current.getId());
            }
            event.setState(EventDto.ESTADO_ACTIVO);
           if (event.getCity() != null) {
               CityDto city = cityService.save(event.getCity());
               if(city != null) {
                   event.setCity(city);
                   event.setCityId(city.getId());
               }
            }
            if (event.getCausaAtencion() != null) {
                ConceptDto causaAtencion = conceptService.save(event.getCausaAtencion());
                if(causaAtencion != null) {
                    event.setCausaAtencion(causaAtencion);
                    event.setCausaAtencionId(causaAtencion.getId());
                }
            }
            eventDao.createOrUpdate(event);
        }
    }
    public synchronized EventDto save(EventDto event) throws SQLException {
        if(event != null && eventDao != null)
        {
            if(event.getEventId() != null) {
                EventDto current = eventDao.queryBuilder().where().eq("eventId", event.getEventId()).queryForFirst();
                if(current != null) {
                    event.setId(current.getId());
                }
            }
            event.setState(EventDto.ESTADO_ACTIVO);
            if (event.getCity() != null) {
                CityDto city = cityService.save(event.getCity());
                if(city != null) {
                    event.setCity(city);
                    event.setCityId(city.getId());
                }
            }
            if (event.getCausaAtencion() != null) {
                ConceptDto causaAtencion = conceptService.save(event.getCausaAtencion());
                if(causaAtencion != null) {
                    event.setCausaAtencion(causaAtencion);
                    event.setCausaAtencionId(causaAtencion.getId());
                }
                else
                {
                    event.setCausaAtencion(null);
                    event.setCausaAtencionId(null);
                }
            }
            else
            {
                event.setCausaAtencion(null);
                event.setCausaAtencionId(null);
            }
            eventDao.createOrUpdate(event);
        }
        return event;
    }
    public List<EventDto> getEventsToSave() throws SQLException {
        List<EventDto> events = eventDao.queryBuilder().where().isNotNull("caseNumber").and().ne("caseNumber", "").and().eq("isSynchronized", Boolean.FALSE).query();
        for(EventDto event : events)
        {
            fillEvent(event);
        }
        return events;
    }
    public List<EventDto> getEventsNotSaved() throws SQLException {
        List<EventDto> events = eventDao.queryBuilder().where().eq("state", EventDto.ESTADO_ACTIVO).and().eq("isSynchronized", Boolean.FALSE).query();
        for(EventDto event : events)
        {
            fillEvent(event);
        }
        return events;
    }
    public List<EventDto> getEventsActive() throws SQLException {
        List<EventDto> events = eventDao.queryBuilder().where().eq("state", EventDto.ESTADO_ACTIVO).and().eq("isSynchronized", Boolean.TRUE).query();
        for(EventDto event : events)
        {
            fillEvent(event);
        }
        return events;
    }
    public EventDto getEvent(Integer eventId) throws SQLException {
        EventDto event = eventDao.queryBuilder().where().eq("eventId", eventId).queryForFirst();
        fillEvent(event);
        return event;
    }
    public EventDto getEventById(Integer id) throws SQLException {
        EventDto event = eventDao.queryBuilder().where().eq("id", id).queryForFirst();
        fillEvent(event);
        return event;
    }
    public void deleteLocalEvent(Integer eventId) throws SQLException {
        eventDao.deleteById(eventId);
    }

}
