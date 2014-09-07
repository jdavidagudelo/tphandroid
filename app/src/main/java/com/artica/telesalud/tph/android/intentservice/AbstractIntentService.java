package com.artica.telesalud.tph.android.intentservice;

import android.app.IntentService;
import android.content.res.Resources;
import android.util.Log;

import com.artica.telesalud.tph.android.R;
import com.artica.telesalud.tph.android.dao.CityService;
import com.artica.telesalud.tph.android.dao.ConceptService;
import com.artica.telesalud.tph.android.dao.DatabaseHelper;
import com.artica.telesalud.tph.android.dao.EventService;
import com.artica.telesalud.tph.android.dao.LesionadoService;
import com.artica.telesalud.tph.android.dao.PatientIdentifierTypeService;
import com.artica.telesalud.tph.android.dao.StateService;
import com.artica.telesalud.tph.android.lightweightmodel.CityDto;
import com.artica.telesalud.tph.android.lightweightmodel.ConceptDto;
import com.artica.telesalud.tph.android.lightweightmodel.EvaluacionCompletaDto;
import com.artica.telesalud.tph.android.lightweightmodel.EvaluacionDto;
import com.artica.telesalud.tph.android.lightweightmodel.EventDto;
import com.artica.telesalud.tph.android.lightweightmodel.HallazgoDto;
import com.artica.telesalud.tph.android.lightweightmodel.LesionadoDto;
import com.artica.telesalud.tph.android.lightweightmodel.ListCities;
import com.artica.telesalud.tph.android.lightweightmodel.ListConcepts;
import com.artica.telesalud.tph.android.lightweightmodel.ListEvaluacionesDto;
import com.artica.telesalud.tph.android.lightweightmodel.ListLesionados;
import com.artica.telesalud.tph.android.lightweightmodel.ListPatientIdentifierTypes;
import com.artica.telesalud.tph.android.lightweightmodel.ListStates;
import com.artica.telesalud.tph.android.lightweightmodel.PatientIdentifierTypeDto;
import com.artica.telesalud.tph.android.lightweightmodel.StateDto;
import com.artica.telesalud.tph.android.lightweightmodel.UserInSession;
import com.artica.telesalud.tph.android.mapper.DateObjectMapper;
import com.artica.telesalud.tph.android.model.CitySpringDto;
import com.artica.telesalud.tph.android.model.ConceptSpringDto;
import com.artica.telesalud.tph.android.model.EvaluacionCompletaSpringDto;
import com.artica.telesalud.tph.android.model.EvaluacionSpringDto;
import com.artica.telesalud.tph.android.model.EventoSpringDto;
import com.artica.telesalud.tph.android.model.LesionadoSpringDto;
import com.artica.telesalud.tph.android.model.PatientIdentifierTypeSpringDto;
import com.artica.telesalud.tph.android.model.StateProvinceSpringDto;
import com.artica.telesalud.tph.android.service.NetworkUtil;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractIntentService extends IntentService{


    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public AbstractIntentService(String name) {
        super(name);
    }
    protected void loadCities()
    {
        if(isConnected()) {
            try {

                StringBuilder sb = new StringBuilder();
                sb.append(getString(R.string.uri_base_rest_services));
                sb.append(getString(R.string.uri_get_all_cities));
                final String url = sb.toString();
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(DateObjectMapper.getConverter());
                HttpEntity<String> requestEntity = new HttpEntity<String>(
                        UserInSession.getDefaultHeaders());

                restTemplate.getMessageConverters().add(DateObjectMapper.getConverter());
                ResponseEntity<ListCities> entity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, ListCities.class);
                ListCities cities = entity.getBody();
                CityService cityService = new CityService(getHelper());
                for(CitySpringDto city : cities)
                {
                    CityDto cityDto = new CityDto(city);
                    cityService.save(cityDto);
                }
            } catch (Exception e) {
                Log.e(AbstractIntentService.class.getCanonicalName(), e.getMessage());
            }

        }
    }
    protected void loadStates()
    {
        if(isConnected()) {
            try {

                StringBuilder sb = new StringBuilder();
                sb.append(getString(R.string.uri_base_rest_services));
                sb.append(getString(R.string.uri_get_states));
                final String url = sb.toString();
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(DateObjectMapper.getConverter());
                HttpEntity<String> requestEntity = new HttpEntity<String>(
                        UserInSession.getDefaultHeaders());
                restTemplate.getMessageConverters().add(DateObjectMapper.getConverter());
                ResponseEntity<ListStates> entity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, ListStates.class);
                ListStates states = entity.getBody();
                for(StateProvinceSpringDto state : states)
                {
                    StateDto stateDto = new StateDto(state);
                    StateService stateService = new StateService(getHelper());
                    stateService.save(stateDto);
                }
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }
        }
    }
    protected void loadCausasAtencion()
    {
        if(isConnected()) {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append(getString(R.string.uri_base_rest_services));
                sb.append(getString(R.string.uri_get_event_causes));
                final String url = sb.toString();
                RestTemplate restTemplate = new RestTemplate();
                HttpEntity<String> requestEntity = new HttpEntity<String>(
                        UserInSession.getDefaultHeaders());
                restTemplate.getMessageConverters().add(DateObjectMapper.getConverter());
                ResponseEntity<ListConcepts> entity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, ListConcepts.class);
                ListConcepts concepts = entity.getBody();
                ConceptService conceptService = new ConceptService(getHelper());
                List<ConceptDto> list = new ArrayList<ConceptDto>();
                list.add(ConceptDto.EMPTY_CAUSA_ATENCION);
                if (concepts != null) {

                    for (ConceptSpringDto concept : concepts) {
                        ConceptDto causaAtencion = new ConceptDto(concept, ConceptDto.TYPE_CAUSA_ATENCION_ID);
                        conceptService.save(causaAtencion);
                    }

                }
            } catch (Exception e) {
                Log.e(AbstractIntentService.class.getCanonicalName(), e.getMessage());
            }
        }
    }
    protected void getMaritalStatuses() throws SQLException {
        if(isConnected()) {
            StringBuilder sb = new StringBuilder();
            sb.append(getString(R.string.uri_base_rest_services));
            sb.append(getString(R.string.uri_get_estados_civiles));
            final String url = sb.toString();
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<String> requestEntity = new HttpEntity<String>(
                    UserInSession.getDefaultHeaders());
            restTemplate.getMessageConverters().add(DateObjectMapper.getConverter());
            ResponseEntity<ListConcepts> entity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, ListConcepts.class);

            ListConcepts concepts = entity.getBody();
            ConceptService conceptService = new ConceptService(getHelper());
            if (concepts != null && entity.getStatusCode() == HttpStatus.OK) {

                for (ConceptSpringDto concept : concepts) {
                    ConceptDto conceptDto = new ConceptDto(concept, ConceptDto.TYPE_MARITAL_STATUS_ID);
                    ConceptDto stored = conceptService.getConcept(concept.getConceptId());
                    if (stored == null) {
                        conceptService.save(conceptDto);
                    }
                }
            }
        }
    }
    protected EvaluacionCompletaDto createEvaluacionCompleta(EvaluacionCompletaDto evaluacionCompleta)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(getString(R.string.uri_base_rest_services));
        sb.append(getString(R.string.uri_create_evaluacion));
        Resources resources = getResources();
        Integer params[] = new Integer[]{evaluacionCompleta.getEncounter().getEncounterId(), evaluacionCompleta.getCreator()};
        String[] paramsKeys = resources.getStringArray(R.array.uri_create_evaluacion_params);
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < params.length; i++) {
            map.put(paramsKeys[i], params[i]);
        }
        final String url = sb.toString();
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(DateObjectMapper.getConverter());
        HttpEntity<EventoSpringDto> requestEntity = new HttpEntity<EventoSpringDto>(
                UserInSession.getDefaultHeaders());
        ResponseEntity<EvaluacionCompletaSpringDto> entity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, EvaluacionCompletaSpringDto.class, map);
        return new EvaluacionCompletaDto(entity.getBody());
    }
    protected EvaluacionDto saveEvaluacion(EvaluacionDto evaluacion)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(getString(R.string.uri_base_rest_services));
        sb.append(getString(R.string.uri_save_evaluacion_patient));
        Resources resources = getResources();
        String[] paramsKeys = resources.getStringArray(R.array.uri_save_evaluacion_patient_params);
        String params[] = evaluacion.getParamKeys();
        Map<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < params.length; i++) {
            map.put(paramsKeys[i], params[i]);
        }
        final String url = sb.toString();
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(DateObjectMapper.getConverter());
        EvaluacionSpringDto evaluation = new EvaluacionSpringDto(evaluacion);
        HttpEntity<EvaluacionSpringDto> requestEntity = new HttpEntity<EvaluacionSpringDto>(evaluation,
                UserInSession.getDefaultHeaders());
        ResponseEntity<EvaluacionSpringDto> entity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, EvaluacionSpringDto.class, map);
        evaluation = entity.getBody();
        if(entity.getStatusCode() == HttpStatus.OK) {
            EvaluacionDto result = new EvaluacionDto(evaluation);
            result.setupId(evaluacion);
            return result;
        }
        return null;
    }
    protected LesionadoDto createLesionado(LesionadoDto lesionado)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(getString(R.string.uri_base_rest_services));
        sb.append(getString(R.string.uri_create_lesionado));
        Resources resources = getResources();
        String[] paramsKeys = resources.getStringArray(R.array.uri_create_lesionado_params);
        Map<String, Integer> map = new HashMap<String, Integer>();
        Integer params[] = new Integer[]{lesionado.getEvento().getEventId(), lesionado.getCreator(), lesionado.getEventLocalIdentifier()};
        for (int i = 0; i < params.length; i++) {
            map.put(paramsKeys[i], params[i]);
        }
        final String url = sb.toString();
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(DateObjectMapper.getConverter());
        HttpEntity<EventoSpringDto> requestEntity = new HttpEntity<EventoSpringDto>(
                UserInSession.getDefaultHeaders());
        ResponseEntity<LesionadoSpringDto> entity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, LesionadoSpringDto.class, map);
        LesionadoDto created = new LesionadoDto(entity.getBody());
        return created;
    }
    protected void saveLesionados() throws SQLException {
        LesionadoService lesionadoService = new LesionadoService(getHelper());
        List<LesionadoDto> lesionados = lesionadoService.getLesionadosToSave();
        for (LesionadoDto lesionado : lesionados) {
            if (lesionado.getEvento().getEventId() != null) {
                LesionadoDto created = lesionado;
                if (lesionado.getLesionadoId() == null) {
                    created = createLesionado(lesionado);
                }
                ListEvaluacionesDto evaluaciones = new ListEvaluacionesDto();
                created.setupId(lesionado);
                for (EvaluacionDto evaluacion : lesionado.getEvaluaciones()) {
                    EvaluacionCompletaDto evaluacionCompletaDto = evaluacion.getEvaluacion();
                    evaluacionCompletaDto.setEncounter(created.getEncuentro());
                    EvaluacionCompletaDto evaluacionCreated = evaluacionCompletaDto;
                    if(evaluacionCompletaDto.getObsId() == null) {
                        evaluacionCreated = createEvaluacionCompleta(evaluacionCompletaDto);
                    }
                    evaluacionCreated.setupId(evaluacionCompletaDto);
                    evaluacion.setEvaluacion(evaluacionCreated);
                    if(evaluacion.getProcedimientos() != null)
                    {
                        evaluacion.getProcedimientos().setEvaluacion(evaluacionCreated);
                    }
                    if(evaluacion.getHallazgos() != null)
                    {
                        for(HallazgoDto hallazgo: evaluacion.getHallazgos())
                        {
                            hallazgo.setEvaluacion(evaluacionCreated);
                        }
                    }
                    EvaluacionDto evaluacionDto = saveEvaluacion(evaluacion);
                    if(evaluacionDto != null) {
                        evaluacionDto.setEvaluacion(evaluacionCreated);
                        evaluaciones.add(evaluacionDto);
                    }
                }
                created.setEvaluaciones(evaluaciones);
                created.setIsSynchronized(Boolean.TRUE);
                lesionadoService.save(created);
                lesionadoService.saveEvaluaciones(created);

            }
        }
    }
    protected void getLesionados(Integer userId, Integer eventId) throws SQLException {
        Integer params[] = new Integer[]{eventId, userId};
        StringBuilder sb = new StringBuilder();
        sb.append(getString(R.string.uri_base_rest_services));
        sb.append(getString(R.string.uri_get_lesionados_evento));
        Resources resources = getResources();
        String paramsKey[] = resources.getStringArray(R.array.uri_get_lesionados_evento_params);
        final String url = sb.toString();
        Map<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < params.length; i++) {
            map.put(paramsKey[i], String.valueOf(params[i]));
        }
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> requestEntity = new HttpEntity<String>(
                UserInSession.getDefaultHeaders());
        restTemplate.getMessageConverters().add(DateObjectMapper.getConverter());
        ResponseEntity<ListLesionados> entity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, ListLesionados.class, map);

        ListLesionados lesionados = entity.getBody();
        LesionadoService lesionadoService = new LesionadoService(getHelper());
        if (lesionados != null && entity.getStatusCode() == HttpStatus.OK) {

            for (LesionadoSpringDto lesionado : lesionados) {
                LesionadoDto lesionadoDto = new LesionadoDto(lesionado);
                LesionadoDto stored = lesionadoService.getLesionado(lesionado.getLesionadoId());
                if(stored == null || stored.getIsSynchronized()) {
                    lesionadoService.save(lesionadoDto);
                    lesionadoService.saveEvaluaciones(lesionadoDto);
                }
            }
        }
    }


    public void getPatientIdentifierTypes() throws SQLException {
        StringBuilder sb = new StringBuilder();
        sb.append(getString(R.string.uri_base_rest_services));
        sb.append(getString(R.string.uri_get_patient_identifier_types));
       final String url = sb.toString();
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> requestEntity = new HttpEntity<String>(
                UserInSession.getDefaultHeaders());
        restTemplate.getMessageConverters().add(DateObjectMapper.getConverter());
        ResponseEntity<ListPatientIdentifierTypes> entity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, ListPatientIdentifierTypes.class);

        ListPatientIdentifierTypes patientIdentifierTypes = entity.getBody();
        PatientIdentifierTypeService patientIdentifierService = new PatientIdentifierTypeService(getHelper());
        if (patientIdentifierTypes != null && entity.getStatusCode() == HttpStatus.OK) {

            for (PatientIdentifierTypeSpringDto patientIdentifier : patientIdentifierTypes) {
                PatientIdentifierTypeDto patientIdentifierTypeDto = new PatientIdentifierTypeDto(patientIdentifier);
                PatientIdentifierTypeDto stored = patientIdentifierService.getPatientIdentifierType(patientIdentifierTypeDto.getPatientIdentifierTypeId());
                if(stored == null || stored.getIsSynchronized()) {
                    patientIdentifierService.save(patientIdentifierTypeDto);
                }
            }
        }
    }
    protected void getAseguradoras() throws SQLException {
        if(isConnected()) {
            StringBuilder sb = new StringBuilder();
            sb.append(getString(R.string.uri_base_rest_services));
            sb.append(getString(R.string.uri_get_aseguradoras));
            final String url = sb.toString();
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<String> requestEntity = new HttpEntity<String>(
                    UserInSession.getDefaultHeaders());
            restTemplate.getMessageConverters().add(DateObjectMapper.getConverter());
            ResponseEntity<ListConcepts> entity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, ListConcepts.class);

            ListConcepts concepts = entity.getBody();
            ConceptService conceptService = new ConceptService(getHelper());
            if (concepts != null && entity.getStatusCode() == HttpStatus.OK) {

                for (ConceptSpringDto concept : concepts) {
                    ConceptDto conceptDto = new ConceptDto(concept, ConceptDto.TYPE_ASEGURADORA_ID);
                    ConceptDto stored = conceptService.getConcept(concept.getConceptId());
                    if (stored == null) {
                        conceptService.save(conceptDto);
                    }
                }
            }
        }
    }
    protected void getEpss() throws SQLException {
        if(isConnected()) {
            StringBuilder sb = new StringBuilder();
            sb.append(getString(R.string.uri_base_rest_services));
            sb.append(getString(R.string.uri_get_epss));
            final String url = sb.toString();
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<String> requestEntity = new HttpEntity<String>(
                    UserInSession.getDefaultHeaders());
            restTemplate.getMessageConverters().add(DateObjectMapper.getConverter());
            ResponseEntity<ListConcepts> entity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, ListConcepts.class);

            ListConcepts concepts = entity.getBody();
            ConceptService conceptService = new ConceptService(getHelper());
            if (concepts != null && entity.getStatusCode() == HttpStatus.OK) {

                for (ConceptSpringDto concept : concepts) {
                    ConceptDto conceptDto = new ConceptDto(concept, ConceptDto.TYPE_EPS_ID);
                    ConceptDto stored = conceptService.getConcept(concept.getConceptId());
                    if (stored == null) {
                        conceptService.save(conceptDto);
                    }
                }
            }
        }
    }
    protected void getPlanesBeneficios() throws SQLException {
        if(isConnected()) {
            StringBuilder sb = new StringBuilder();
            sb.append(getString(R.string.uri_base_rest_services));
            sb.append(getString(R.string.uri_get_planes_beneficios));
            final String url = sb.toString();
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<String> requestEntity = new HttpEntity<String>(
                    UserInSession.getDefaultHeaders());
            restTemplate.getMessageConverters().add(DateObjectMapper.getConverter());
            ResponseEntity<ListConcepts> entity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, ListConcepts.class);

            ListConcepts concepts = entity.getBody();
            ConceptService conceptService = new ConceptService(getHelper());
            if (concepts != null && entity.getStatusCode() == HttpStatus.OK) {

                for (ConceptSpringDto concept : concepts) {
                    ConceptDto conceptDto = new ConceptDto(concept, ConceptDto.TYPE_PLAN_BENEFICIOS_ID);
                    ConceptDto stored = conceptService.getConcept(concept.getConceptId());
                    if (stored == null) {
                        conceptService.save(conceptDto);
                    }
                }
            }
        }
    }
    protected void getTiposAntecedentes() throws SQLException {
        if(isConnected()) {
            StringBuilder sb = new StringBuilder();
            sb.append(getString(R.string.uri_base_rest_services));
            sb.append(getString(R.string.uri_get_tipos_antecedentes));
            final String url = sb.toString();
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<String> requestEntity = new HttpEntity<String>(
                    UserInSession.getDefaultHeaders());
            restTemplate.getMessageConverters().add(DateObjectMapper.getConverter());
            ResponseEntity<ListConcepts> entity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, ListConcepts.class);

            ListConcepts concepts = entity.getBody();
            ConceptService conceptService = new ConceptService(getHelper());
            if (concepts != null && entity.getStatusCode() == HttpStatus.OK) {

                for (ConceptSpringDto concept : concepts) {
                    ConceptDto conceptDto = new ConceptDto(concept, ConceptDto.TYPE_ANTECEDENT_TYPE_ID);
                    ConceptDto stored = conceptService.getConcept(concept.getConceptId());
                    if (stored == null) {
                        conceptService.save(conceptDto);
                    }
                }
            }
        }
    }
    protected void saveEventos() throws SQLException {
        EventService eventService = new EventService(getHelper());
        List<EventoSpringDto> eventos = new ArrayList<EventoSpringDto>();
        List<EventDto> events = eventService.getEventsToSave();
        for (EventDto event : events) {
            StringBuilder sb = new StringBuilder();
            sb.append(getString(R.string.uri_base_rest_services));
            sb.append(getString(R.string.uri_save_event));
            Resources resources = getResources();
            String[] paramsKeys = resources.getStringArray(R.array.uri_save_event_params);
            String params[] = event.getParamKeys();
            Map<String, String> map = new HashMap<String, String>();
            for (int i = 0; i < params.length; i++) {
                map.put(paramsKeys[i], params[i]);
            }
            final String url = sb.toString();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(DateObjectMapper.getConverter());
            EventoSpringDto evento = new EventoSpringDto(event);
            HttpEntity<EventoSpringDto> requestEntity = new HttpEntity<EventoSpringDto>(evento,
                    UserInSession.getDefaultHeaders());
            ResponseEntity<EventoSpringDto> entity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, EventoSpringDto.class, map);
            evento = entity.getBody();
            if (evento != null) {
                evento.setLocalId(event.getId());
                eventos.add(evento);
            }
            for (EventoSpringDto eventSpring : eventos) {
                try {
                    Integer id = event.getId();
                    event = new EventDto(eventSpring);
                    event.setId(id);
                    eventService.save(event);
                } catch (SQLException e) {
                }
            }
        }
    }
    private DatabaseHelper databaseHelper;
    public DatabaseHelper getHelper() {
        if(databaseHelper == null)
        {
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper                                                                                         ;
    }
    public Boolean isConnected()
    {
        return !NetworkUtil.getConnectivityStatus(this).equals(NetworkUtil.NetworkType.TYPE_NOT_CONNECTED);
    }

}
