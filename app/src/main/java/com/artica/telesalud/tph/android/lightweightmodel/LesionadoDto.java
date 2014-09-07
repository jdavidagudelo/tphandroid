package com.artica.telesalud.tph.android.lightweightmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.artica.telesalud.tph.android.model.EvaluacionSpringDto;
import com.artica.telesalud.tph.android.model.LesionadoSpringDto;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jonathan on 16/07/14.
 */
@DatabaseTable()
public class LesionadoDto implements Serializable, Parcelable{

    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField()
    private Integer lesionadoId;

    @DatabaseField()
    private Integer encuentroId;
    private EncounterDto encuentro;

    @DatabaseField()
    private Integer eventoId;
    private EventDto evento;

    @DatabaseField()
    private Integer tripulacionId;
    private TripulacionDto tripulacion;

    @DatabaseField()
    private Integer resultadoId;
    private ConceptDto resultado;

    @DatabaseField()
    private Integer dispositivoTransporteId;
    private ConceptDto dispositivoTransporte;

    @DatabaseField()
    private Boolean niegaAtencion;

    @DatabaseField()
    private Integer tipoNegacionId;
    private ConceptDto tipoNegacion;

    @DatabaseField()
    private String observacionNegacion;

    @DatabaseField()
    private Integer entidadRecepcionId;
    private HospitalDto entidadRecepcion;

    @DatabaseField()
    private String recibidoPor;

    @DatabaseField()
    private String registroRecibe;

    @DatabaseField()
    private Integer epsId;
    private ConceptDto eps;

    @DatabaseField()
    private Integer aseguradoraId;
    private ConceptDto aseguradora;

    @DatabaseField()
    private Integer planBeneficiosId;
    private ConceptDto planBeneficios;

    @DatabaseField()
    private Boolean solicitaTeleasistencia;

    @DatabaseField()
    private Boolean isSynchronized = Boolean.FALSE;
    @DatabaseField()
    private Integer eventLocalIdentifier;

    @DatabaseField()
    private Date dateCreated;
    @DatabaseField()
    private Integer creator;

    private ListEvaluacionesDto evaluaciones = new ListEvaluacionesDto();
    private List<AntecedentesDto> antecedentes = new ArrayList<AntecedentesDto>();
    public static final Parcelable.Creator<LesionadoDto> CREATOR = new Parcelable.Creator<LesionadoDto>() {
        public LesionadoDto createFromParcel(Parcel in) {
            return new LesionadoDto(in);
        }

        public LesionadoDto[] newArray(int size) {
            return new LesionadoDto[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public List<AntecedentesDto> getAntecedentes() {
        return antecedentes;
    }

    public void setAntecedentes(List<AntecedentesDto> antecedentes) {
        this.antecedentes = antecedentes;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LesionadoDto(Parcel lesionado) {
        id= (Integer)lesionado.readValue(null);
        lesionadoId = (Integer)lesionado.readValue(null);
        encuentro = (EncounterDto)lesionado.readValue(EncounterDto.class.getClassLoader());
        evento = (EventDto)lesionado.readValue(EventDto.class.getClassLoader());
        tripulacion = (TripulacionDto)lesionado.readValue(TripulacionDto.class.getClassLoader());
        resultado = (ConceptDto)lesionado.readValue(ConceptDto.class.getClassLoader());
        dispositivoTransporte = (ConceptDto)lesionado.readValue(ConceptDto.class.getClassLoader());
        niegaAtencion = (Boolean) lesionado.readValue(Boolean.class.getClassLoader());
        tipoNegacion = (ConceptDto)lesionado.readValue(ConceptDto.class.getClassLoader());
        observacionNegacion = (String)lesionado.readValue(null);
        entidadRecepcion = (HospitalDto)lesionado.readValue(HospitalDto.class.getClassLoader());
        recibidoPor = (String)lesionado.readValue(null);
        registroRecibe =(String)lesionado.readValue(null);
        eps = (ConceptDto)lesionado.readValue(ConceptDto.class.getClassLoader());
        aseguradora = (ConceptDto)lesionado.readValue(ConceptDto.class.getClassLoader());
        planBeneficios = (ConceptDto)lesionado.readValue(ConceptDto.class.getClassLoader());
        solicitaTeleasistencia = (Boolean) lesionado.readValue(Boolean.class.getClassLoader());
        isSynchronized = (Boolean)lesionado.readValue(Boolean.class.getClassLoader());
        lesionado.readTypedList(evaluaciones, EvaluacionDto.CREATOR);
        eventLocalIdentifier = (Integer)lesionado.readValue(null);
        creator = (Integer)lesionado.readValue(null);
        dateCreated =(Date)lesionado.readValue(null);
        encuentroId = (Integer)lesionado.readValue(null);
        eventoId = (Integer)lesionado.readValue(null);
        tripulacionId = (Integer)lesionado.readValue(null);
        resultadoId = (Integer)lesionado.readValue(null);
        dispositivoTransporteId = (Integer)lesionado.readValue(null);
        tipoNegacionId  = (Integer)lesionado.readValue(null);
        entidadRecepcionId  = (Integer)lesionado.readValue(null);
        epsId = (Integer)lesionado.readValue(null);
        aseguradoraId  = (Integer)lesionado.readValue(null);
        planBeneficiosId  = (Integer)lesionado.readValue(null);
        lesionado.readTypedList(antecedentes, AntecedentesDto.CREATOR);
    }

    public LesionadoDto(LesionadoSpringDto lesionado) {

       lesionadoId = lesionado.getLesionadoId();
        if(lesionado.getEncuentro() != null)
        encuentro = new EncounterDto(lesionado.getEncuentro());
        if(lesionado.getEvento() != null)
        evento = new EventDto(lesionado.getEvento());
        if(lesionado.getTripulacion() != null)
        tripulacion = new TripulacionDto(lesionado.getTripulacion());
        if(lesionado.getResultado() != null)
        resultado = new ConceptDto(lesionado.getResultado(), ConceptDto.TYPE_RESULTADO_ID);
        if(lesionado.getDispositivoTransporte() != null)
        dispositivoTransporte = new ConceptDto(lesionado.getDispositivoTransporte(), ConceptDto.TYPE_DISPOSITIVO_TRANSPORTE_ID);
        niegaAtencion = lesionado.getNiegaAtencion();
        if(lesionado.getTipoNegacion() != null)
        tipoNegacion = new ConceptDto(lesionado.getTipoNegacion(), ConceptDto.TYPE_TIPO_NEGACION_ID);
        observacionNegacion = lesionado.getObservacionNegacion();
        if(lesionado.getEntidadRecepcion() != null)
        entidadRecepcion = new HospitalDto(lesionado.getEntidadRecepcion());
        recibidoPor = lesionado.getRecibidoPor();
        registroRecibe = lesionado.getRegistroRecibe();
        if(lesionado.getEps() != null)
        eps = new ConceptDto(lesionado.getEps(), ConceptDto.TYPE_EPS_ID);
        if(lesionado.getAseguradora() != null)
        aseguradora = new ConceptDto(lesionado.getAseguradora(), ConceptDto.TYPE_ASEGURADORA_ID);
        if(lesionado.getPlanBeneficios() != null)
        planBeneficios = new ConceptDto(lesionado.getPlanBeneficios(), ConceptDto.TYPE_PLAN_BENEFICIOS_ID);
        solicitaTeleasistencia = lesionado.getSolicitaTeleasistencia();
        if(evaluaciones != null) {
            evaluaciones.clear();
        }
        if(lesionado.getEvaluaciones() != null)
        {
            for(EvaluacionSpringDto evaluacion : lesionado.getEvaluaciones())
            {
                evaluaciones.add(new EvaluacionDto(evaluacion));
            }
        }
        isSynchronized = Boolean.TRUE;
        eventLocalIdentifier = lesionado.getEventLocalIdentifier();
        creator = lesionado.getCreator();
        dateCreated = lesionado.getDateCreated();
    }

    /**
     * @return the lesionadoId
     */
    public Integer getLesionadoId() {
        return lesionadoId;
    }

    /**
     * @param lesionadoId
     *            the lesionadoId to set
     */
    public void setLesionadoId(Integer lesionadoId) {
        this.lesionadoId = lesionadoId;
    }

    /**
     * @return the encuentro
     */
    public EncounterDto getEncuentro() {
        return encuentro;
    }

    /**
     * @param encuentro
     *            the encuentro to set
     */
    public void setEncuentro(EncounterDto encuentro) {
        this.encuentro = encuentro;
    }

    /**
     * @return the evento
     */
    public EventDto getEvento() {
        return evento;
    }

    /**
     * @param evento
     *            the evento to set
     */
    public void setEvento(EventDto evento) {
        this.evento = evento;
    }

    /**
     * @return the tripulacion
     */
    public TripulacionDto getTripulacion() {
        return tripulacion;
    }

    /**
     * @param tripulacion
     *            the tripulacion to set
     */
    public void setTripulacion(TripulacionDto tripulacion) {
        this.tripulacion = tripulacion;
    }

    /**
     * @return the resultado
     */
    public ConceptDto getResultado() {
        return resultado;
    }

    /**
     * @param resultado
     *            the resultado to set
     */
    public void setResultado(ConceptDto resultado) {
        this.resultado = resultado;
    }

    /**
     * @return the dispositivoTransporte
     */
    public ConceptDto getDispositivoTransporte() {
        return dispositivoTransporte;
    }

    /**
     * @param dispositivoTransporte
     *            the dispositivoTransporte to set
     */
    public void setDispositivoTransporte(ConceptDto dispositivoTransporte) {
        this.dispositivoTransporte = dispositivoTransporte;
    }

    /**
     * @return the niegaAtencion
     */
    public Boolean getNiegaAtencion() {
        return niegaAtencion;
    }

    /**
     * @param niegaAtencion
     *            the niegaAtencion to set
     */
    public void setNiegaAtencion(Boolean niegaAtencion) {
        this.niegaAtencion = niegaAtencion;
    }

    /**
     * @return the tipoNegacion
     */
    public ConceptDto getTipoNegacion() {
        return tipoNegacion;
    }

    /**
     * @param tipoNegacion
     *            the tipoNegacion to set
     */
    public void setTipoNegacion(ConceptDto tipoNegacion) {
        this.tipoNegacion = tipoNegacion;
    }

    /**
     * @return the observacionNegacion
     */
    public String getObservacionNegacion() {
        return observacionNegacion;
    }

    /**
     * @param observacionNegacion
     *            the observacionNegacion to set
     */
    public void setObservacionNegacion(String observacionNegacion) {
        this.observacionNegacion = observacionNegacion;
    }

    /**
     * @return the entidadRecepcion
     */
    public HospitalDto getEntidadRecepcion() {
        return entidadRecepcion;
    }

    /**
     * @param entidadRecepcion
     *            the entidadRecepcion to set
     */
    public void setEntidadRecepcion(HospitalDto entidadRecepcion) {
        this.entidadRecepcion = entidadRecepcion;
    }

    /**
     * @return the recibidoPor
     */
    public String getRecibidoPor() {
        return recibidoPor;
    }

    /**
     * @param recibidoPor
     *            the recibidoPor to set
     */
    public void setRecibidoPor(String recibidoPor) {
        this.recibidoPor = recibidoPor;
    }

    /**
     * @return the registroRecibe
     */
    public String getRegistroRecibe() {
        return registroRecibe;
    }

    /**
     * @param registroRecibe
     *            the registroRecibe to set
     */
    public void setRegistroRecibe(String registroRecibe) {
        this.registroRecibe = registroRecibe;
    }

    /**
     * @return the eps
     */
    public ConceptDto getEps() {
        return eps;
    }

    /**
     * @param eps
     *            the eps to set
     */
    public void setEps(ConceptDto eps) {
        this.eps = eps;
    }

    /**
     * @return the aseguradora
     */
    public ConceptDto getAseguradora() {
        return aseguradora;
    }

    /**
     * @param aseguradora
     *            the aseguradora to set
     */
    public void setAseguradora(ConceptDto aseguradora) {
        this.aseguradora = aseguradora;
    }

    /**
     * @return the planBeneficios
     */
    public ConceptDto getPlanBeneficios() {
        return planBeneficios;
    }

    /**
     * @param planBeneficios
     *            the planBeneficios to set
     */
    public void setPlanBeneficios(ConceptDto planBeneficios) {
        this.planBeneficios = planBeneficios;
    }

    /**
     * @return the solicitaTeleasistencia
     */
    public Boolean getSolicitaTeleasistencia() {
        return solicitaTeleasistencia;
    }

    /**
     * @param solicitaTeleasistencia
     *            the solicitaTeleasistencia to set
     */
    public void setSolicitaTeleasistencia(Boolean solicitaTeleasistencia) {
        this.solicitaTeleasistencia = solicitaTeleasistencia;
    }

    public Boolean getIsSynchronized() {
        return isSynchronized;
    }

    public void setIsSynchronized(Boolean isSynchronized) {
        this.isSynchronized = isSynchronized;
    }


    public LesionadoDto() {
        super();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public List<EvaluacionDto> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(ListEvaluacionesDto evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

    public Integer getEventLocalIdentifier() {
        return eventLocalIdentifier;
    }

    public void setEventLocalIdentifier(Integer eventLocalIdentifier) {
        this.eventLocalIdentifier = eventLocalIdentifier;
    }

    @Override
    public void writeToParcel(Parcel parcel, int args) {
        parcel.writeValue(id);
        parcel.writeValue(lesionadoId);
        parcel.writeValue(encuentro);
        parcel.writeValue(evento);
        parcel.writeValue(tripulacion);
        parcel.writeValue(resultado);
        parcel.writeValue(dispositivoTransporte);
        parcel.writeValue(niegaAtencion);
        parcel.writeValue(tipoNegacion);
        parcel.writeValue(observacionNegacion);
        parcel.writeValue(entidadRecepcion);
        parcel.writeValue(recibidoPor);
        parcel.writeValue(registroRecibe);
        parcel.writeValue(eps);
        parcel.writeValue(aseguradora);
        parcel.writeValue(planBeneficios);
        parcel.writeValue(solicitaTeleasistencia);
        parcel.writeValue(isSynchronized);
        parcel.writeTypedList(evaluaciones);
        parcel.writeValue(eventLocalIdentifier);
        parcel.writeValue(creator);
        parcel.writeValue(dateCreated);

        parcel.writeValue(encuentroId);
        parcel.writeValue(eventoId );
        parcel.writeValue(tripulacionId);
        parcel.writeValue(resultadoId );
        parcel.writeValue(dispositivoTransporteId);
        parcel.writeValue(tipoNegacionId );
        parcel.writeValue(entidadRecepcionId );
        parcel.writeValue(epsId);
        parcel.writeValue(aseguradoraId);
        parcel.writeValue(planBeneficiosId );
        parcel.writeTypedList(antecedentes);
    }

    public Integer getEncuentroId() {
        return encuentroId;
    }

    public void setEncuentroId(Integer encuentroId) {
        this.encuentroId = encuentroId;
    }

    public Integer getEventoId() {
        return eventoId;
    }

    public void setEventoId(Integer eventoId) {
        this.eventoId = eventoId;
    }

    public Integer getTripulacionId() {
        return tripulacionId;
    }

    public void setTripulacionId(Integer tripulacionId) {
        this.tripulacionId = tripulacionId;
    }

    public Integer getResultadoId() {
        return resultadoId;
    }

    public void setResultadoId(Integer resultadoId) {
        this.resultadoId = resultadoId;
    }

    public Integer getDispositivoTransporteId() {
        return dispositivoTransporteId;
    }

    public void setDispositivoTransporteId(Integer dispositivoTransporteId) {
        this.dispositivoTransporteId = dispositivoTransporteId;
    }

    public Integer getTipoNegacionId() {
        return tipoNegacionId;
    }

    public void setTipoNegacionId(Integer tipoNegacionId) {
        this.tipoNegacionId = tipoNegacionId;
    }

    public Integer getEntidadRecepcionId() {
        return entidadRecepcionId;
    }

    public void setEntidadRecepcionId(Integer entidadRecepcionId) {
        this.entidadRecepcionId = entidadRecepcionId;
    }

    public Integer getEpsId() {
        return epsId;
    }

    public void setEpsId(Integer epsId) {
        this.epsId = epsId;
    }

    public Integer getAseguradoraId() {
        return aseguradoraId;
    }

    public void setAseguradoraId(Integer aseguradoraId) {
        this.aseguradoraId = aseguradoraId;
    }

    public Integer getPlanBeneficiosId() {
        return planBeneficiosId;
    }

    public void setPlanBeneficiosId(Integer planBeneficiosId) {
        this.planBeneficiosId = planBeneficiosId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }
    public void setupId(LesionadoDto lesionado)
    {
        id = lesionado.getId();
        if(encuentro != null)
        {
            encuentro.setupId(lesionado.getEncuentro());
        }
    }
}
