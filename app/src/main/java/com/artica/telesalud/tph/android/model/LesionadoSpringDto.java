package com.artica.telesalud.tph.android.model;


import com.artica.telesalud.tph.android.lightweightmodel.EvaluacionDto;
import com.artica.telesalud.tph.android.lightweightmodel.LesionadoDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LesionadoSpringDto {
    @JsonIgnore
    private Boolean fromServer = Boolean.FALSE;
    @JsonIgnore
    private Integer localId;
	private Integer lesionadoId;
	private EncounterSpringDto encuentro;
	private EventoSpringDto evento;
	private TripulacionSpringDto tripulacion;
	private ConceptSpringDto resultado;
	private ConceptSpringDto dispositivoTransporte;
	private Boolean niegaAtencion;
	private ConceptSpringDto tipoNegacion;
	private String observacionNegacion;
	private HospitalSpringDto entidadRecepcion;
	private String recibidoPor;
	private String registroRecibe;
	private ConceptSpringDto eps;
	private ConceptSpringDto aseguradora;
	private ConceptSpringDto planBeneficios;
	private Boolean solicitaTeleasistencia;
    private Integer eventLocalIdentifier;
    private List<EvaluacionSpringDto> evaluaciones;
    private List<AntecedentesAmpSpringDto> antecedentesAmp = new ArrayList<AntecedentesAmpSpringDto>();
    private List<SignosVitalesSpringDto> signosVitales = new ArrayList<SignosVitalesSpringDto>();
    private List<TeleasistenciaSpringDto> teleasistencias = new ArrayList<TeleasistenciaSpringDto>();
    private List<ResponsableAtencionSpringDto> responsables = new ArrayList<ResponsableAtencionSpringDto>();

    private Integer creator;
    private Date dateCreated;
    public LesionadoSpringDto(LesionadoDto lesionado)
    {
        lesionadoId = lesionado.getLesionadoId();
        if(lesionado.getEncuentro() != null)
            encuentro = new EncounterSpringDto(lesionado.getEncuentro());
        if(lesionado.getEvento() != null)
            evento = new EventoSpringDto(lesionado.getEvento());
        if(lesionado.getTripulacion() != null)
        {
            //tripulacion = new TripulacionSpringDto(lesionado.getTripulacion());
        }
        if(lesionado.getResultado() != null)
            resultado = new ConceptSpringDto(lesionado.getResultado());
        if(lesionado.getDispositivoTransporte() != null)
            dispositivoTransporte = new ConceptSpringDto(lesionado.getDispositivoTransporte());
        niegaAtencion = lesionado.getNiegaAtencion();
        if(lesionado.getTipoNegacion() != null)
            tipoNegacion = new ConceptSpringDto(lesionado.getTipoNegacion());
        observacionNegacion = lesionado.getObservacionNegacion();
        if(lesionado.getEntidadRecepcion() != null) {
            //entidadRecepcion = new HospitalSpringDto(lesionado.getEntidadRecepcion());
        }
        recibidoPor = lesionado.getRecibidoPor();
        registroRecibe = lesionado.getRegistroRecibe();
        if(lesionado.getEps() != null)
            eps = new ConceptSpringDto(lesionado.getEps());
        if(lesionado.getAseguradora() != null)
            aseguradora = new ConceptSpringDto(lesionado.getAseguradora());
        if(lesionado.getPlanBeneficios() != null)
            planBeneficios = new ConceptSpringDto(lesionado.getPlanBeneficios());
        solicitaTeleasistencia = lesionado.getSolicitaTeleasistencia();
        if(evaluaciones != null) {
            evaluaciones.clear();
        }
        if(lesionado.getEvaluaciones() != null)
        {
            for(EvaluacionDto evaluacion : lesionado.getEvaluaciones())
            {
                evaluaciones.add(new EvaluacionSpringDto(evaluacion));
            }
        }
        eventLocalIdentifier = lesionado.getEventLocalIdentifier();
        creator = lesionado.getCreator();
        dateCreated = lesionado.getDateCreated();
    }
    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }public LesionadoSpringDto()
    {

    }

    public Integer getLocalId() {
        return localId;
    }

    public void setLocalId(Integer localId) {
        this.localId = localId;
    }

    public Boolean getFromServer() {
        return fromServer;
    }

    public void setFromServer(Boolean fromServer) {
        this.fromServer = fromServer;
    }

    public Integer getEventLocalIdentifier() {
        return eventLocalIdentifier;
    }

    public void setEventLocalIdentifier(Integer eventLocalIdentifier) {
        this.eventLocalIdentifier = eventLocalIdentifier;
    }

    public List<EvaluacionSpringDto> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(List<EvaluacionSpringDto> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

    public List<AntecedentesAmpSpringDto> getAntecedentesAmp() {
        return antecedentesAmp;
    }

    public void setAntecedentesAmp(List<AntecedentesAmpSpringDto> antecedentesAmp) {
        this.antecedentesAmp = antecedentesAmp;
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
	public EncounterSpringDto getEncuentro() {
		return encuentro;
	}

	/**
	 * @param encuentro
	 *            the encuentro to set
	 */
	public void setEncuentro(EncounterSpringDto encuentro) {
		this.encuentro = encuentro;
	}

	/**
	 * @return the evento
	 */
	public EventoSpringDto getEvento() {
		return evento;
	}

	/**
	 * @param evento
	 *            the evento to set
	 */
	public void setEvento(EventoSpringDto evento) {
		this.evento = evento;
	}

	/**
	 * @return the tripulacion
	 */
	public TripulacionSpringDto getTripulacion() {
		return tripulacion;
	}

	/**
	 * @param tripulacion
	 *            the tripulacion to set
	 */
	public void setTripulacion(TripulacionSpringDto tripulacion) {
		this.tripulacion = tripulacion;
	}

	/**
	 * @return the resultado
	 */
	public ConceptSpringDto getResultado() {
		return resultado;
	}

	/**
	 * @param resultado
	 *            the resultado to set
	 */
	public void setResultado(ConceptSpringDto resultado) {
		this.resultado = resultado;
	}

	/**
	 * @return the dispositivoTransporte
	 */
	public ConceptSpringDto getDispositivoTransporte() {
		return dispositivoTransporte;
	}

	/**
	 * @param dispositivoTransporte
	 *            the dispositivoTransporte to set
	 */
	public void setDispositivoTransporte(ConceptSpringDto dispositivoTransporte) {
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
	public ConceptSpringDto getTipoNegacion() {
		return tipoNegacion;
	}

	/**
	 * @param tipoNegacion
	 *            the tipoNegacion to set
	 */
	public void setTipoNegacion(ConceptSpringDto tipoNegacion) {
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
	public HospitalSpringDto getEntidadRecepcion() {
		return entidadRecepcion;
	}

	/**
	 * @param entidadRecepcion
	 *            the entidadRecepcion to set
	 */
	public void setEntidadRecepcion(HospitalSpringDto entidadRecepcion) {
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
	public ConceptSpringDto getEps() {
		return eps;
	}

	/**
	 * @param eps
	 *            the eps to set
	 */
	public void setEps(ConceptSpringDto eps) {
		this.eps = eps;
	}

	/**
	 * @return the aseguradora
	 */
	public ConceptSpringDto getAseguradora() {
		return aseguradora;
	}

	/**
	 * @param aseguradora
	 *            the aseguradora to set
	 */
	public void setAseguradora(ConceptSpringDto aseguradora) {
		this.aseguradora = aseguradora;
	}

	/**
	 * @return the planBeneficios
	 */
	public ConceptSpringDto getPlanBeneficios() {
		return planBeneficios;
	}

	/**
	 * @param planBeneficios
	 *            the planBeneficios to set
	 */
	public void setPlanBeneficios(ConceptSpringDto planBeneficios) {
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

    public List<SignosVitalesSpringDto> getSignosVitales() {
        return signosVitales;
    }

    public void setSignosVitales(List<SignosVitalesSpringDto> signosVitales) {
        this.signosVitales = signosVitales;
    }

    public List<TeleasistenciaSpringDto> getTeleasistencias() {
        return teleasistencias;
    }

    public void setTeleasistencias(List<TeleasistenciaSpringDto> teleasistencias) {
        this.teleasistencias = teleasistencias;
    }

    public List<ResponsableAtencionSpringDto> getResponsables() {
        return responsables;
    }

    public void setResponsables(List<ResponsableAtencionSpringDto> responsables) {
        this.responsables = responsables;
    }
}
