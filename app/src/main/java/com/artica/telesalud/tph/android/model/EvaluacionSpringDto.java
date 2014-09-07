package com.artica.telesalud.tph.android.model;

import com.artica.telesalud.tph.android.lightweightmodel.EvaluacionDto;
import com.artica.telesalud.tph.android.lightweightmodel.HallazgoDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;


public class EvaluacionSpringDto {

    public static final Integer RESPUESTA_OCULAR1 = 1, RESPUESTA_OCULAR2 = 2, RESPUESTA_OCULAR3 = 3, RESPUESTA_OCULAR4 = 4;
    public static final  Integer RESPUESTA_VERBAL1 = 1, RESPUESTA_VERBAL2 = 2, RESPUESTA_VERBAL3 = 3, RESPUESTA_VERBAL4 = 4,
            RESPUESTA_VERBAL5 = 5;
    public static final Integer RESPUESTA_MOTORA1=1,RESPUESTA_MOTORA2=2,RESPUESTA_MOTORA3=3,RESPUESTA_MOTORA4 = 4,RESPUESTA_MOTORA5=5,
            RESPUESTA_MOTORA6 = 6;
    public static final Integer VIA_AEREA_PERMEABLE = 1, VIA_AEREA_OBSTRUIDA = 2,
            RESPIRACION_AUSENTE = 3, RESPIRACION_PRESENTE =4, ESTADO_RESPIRACION_DIFICULTOSA = 5,
            ESTADO_RESPIRACION_NORMAL = 6, ESTADO_RESPIRACION_SIMETRICA = 7,
            ESTADO_RESPIRACION_ASIMETRICA = 8, CIANOSIS_EXISTENTE=9,CIANOSIS_AUSENTE=10,
            SANGRADO_EXISTENTE = 11,SANGRADO_AUSENTE = 12,
            PULSO_AUSENTE = 13, PULSO_FUERTE = 14, PULSO_DEBIL = 15,
            UBICACION_PULSO_RADIAL = 16, UBICACION_PULSO_CAROTIDEA = 36,
            LLENADO_CAPILAR_MENOR=37, LLENADO_CAPILAR_MAYOR=38,
            EMERGENCIA_PARO_CARDIACO = 17, EMERGENCIA_NEUROLOGIA = 18,
            EMERGENCIA_ORGANOS_SENTIDOS = 19, EMERGENCIA_CARDIOVASCULAR = 20,
            EMERGENCIA_GASTROINTESTINAL = 21, EMERGENCIA_GENITOURINARIO = 22,
            EMERGENCIA_GINECOOBSTETRICO = 23, EMERGENCIA_SHOCK = 24,
            EMERGENCIA_INTOXICACION = 25, EMERGENCIA_SIQUIATRICA = 26,
            EMERGENCIA_OVACE = 27, EMERGENCIA_TERMICA = 28,
            EMERGENCIA_ENFERMEDAD_COMUN = 29, PRIORIDAD_TRIAGE_AMARILLO = 31,
            PRIORIDAD_TRIAGE_ROJO = 30, PRIORIDAD_TRIAGE_NEGRO = 32,
            PRIORIDAD_TRIAGE_VERDE = 33, PRIORIDAD_TRIAGE_BLANCO = 34,
            OTRA_EMERGENCIA=35;

    @JsonIgnore
    private Integer localId;

    private Boolean pupilasNormales=Boolean.FALSE;
    private Boolean pupilasAnisocoricas=Boolean.FALSE;
	private EvaluacionCompletaSpringDto evaluacion;
    private ProcedimientosSpringDto procedimientos;
    private List<HallazgoSpringDto> hallazgos;

    private Integer llenadoCapilar;

    private Integer ubicacionPulso;

    private Integer sangrado;

    private String otraEmergenciaMedica;

    private Integer permeavilidadViaAerea;

    private String causaObstruccionVia;

    private Integer estadoRespiracion;
    private Integer cianosis;

    private Integer respiracion;

    private Integer pulso;

    private Integer ritmoPulso;

    private Integer fuerzaPulso;

    private Integer nivelRespuesta;

    private Boolean pielNormal=Boolean.FALSE;

    private Boolean pielPalida=Boolean.FALSE;

    private Boolean pielCaliente=Boolean.FALSE;

    private Boolean pielCianotica=Boolean.FALSE;

    private Boolean pielIcterica=Boolean.FALSE;

    private Boolean pielFria=Boolean.FALSE;

    private Boolean pielHumeda=Boolean.FALSE;

    private Boolean pielSeca=Boolean.FALSE;

    private Boolean pupilasMioticas=Boolean.FALSE;

    private Boolean pupilasMidriaticas=Boolean.FALSE;
    private Boolean pupilasIsocoricas=Boolean.FALSE;

    private Boolean pupilasReactivas=Boolean.FALSE;

    private Boolean pupilasNoReactivas=Boolean.FALSE;

    private Boolean pupilasNoEvaluables=Boolean.FALSE;
    private Integer glasgowRO;

    private Integer glasgowRV;

    private Integer glasgowRM;

    private Integer tipoEmergencia;

    private Boolean politrauma=Boolean.FALSE;

    private Boolean tec=Boolean.FALSE;

    private Boolean maxilofacial=Boolean.FALSE;

    private Boolean raquimedular=Boolean.FALSE;

    private Boolean torax=Boolean.FALSE;

    private Boolean abdomen=Boolean.FALSE;

    private Boolean pelvico=Boolean.FALSE;

    private Boolean extremidadSuperior=Boolean.FALSE;

    private Boolean extremidadInferior=Boolean.FALSE;

    private Boolean tejidosBlandos=Boolean.FALSE;

    private Boolean osteomuscular=Boolean.FALSE;

    private Boolean organosSentidos=Boolean.FALSE;

    private Boolean otroTrauma=Boolean.FALSE;

    private String cualOtroTrauma;

    private Integer prioridadTriage;


    private Boolean triageViaAreaObstruida=Boolean.FALSE;

    private Boolean triageInsuficienciaRespiratoria=Boolean.FALSE;

    private Boolean triagePa90=Boolean.FALSE;

    private Boolean triageMultiplesHeridas=Boolean.FALSE;

    private Boolean triageHemorragiasNoControladas=Boolean.FALSE;

    private Boolean triageLesionCervicalIncompleta=Boolean.FALSE;

    private Boolean triageGlasgow4a8=Boolean.FALSE;

    private Boolean triageExcitacionPsicomotora=Boolean.FALSE;

    private Boolean triageAbdomenAgudo=Boolean.FALSE;

    private Boolean triageEvisceracion=Boolean.FALSE;

    private Boolean triageTrabajoPartoSangrado=Boolean.FALSE;

    private Boolean triageDolorToracico=Boolean.FALSE;

    private Boolean triageArritmias=Boolean.FALSE;

    private Boolean triageHemorragiasControladas=Boolean.FALSE;

    private Boolean triageLesionMedularDorsal=Boolean.FALSE;

    private Boolean triageGlasgow9a13=Boolean.FALSE;

    private Boolean triageAlteracionEstadoConciencia=Boolean.FALSE;

    private Boolean triageFracturasMayores=Boolean.FALSE;

    private Boolean triageQuemadurasModeradas=Boolean.FALSE;

    private Boolean triageIntoxicacion=Boolean.FALSE;

    private Boolean triageLesionMedularLumbar=Boolean.FALSE;

    private Boolean triageFracturasNoProximales=Boolean.FALSE;

    private Boolean triageLesionesSuperficiales=Boolean.FALSE;

    private Boolean triageQuemadurasPrimerGrado=Boolean.FALSE;

    private Boolean triageAfectados=Boolean.FALSE;

    private Boolean triageParoProlongado=Boolean.FALSE;

    private Boolean triageLesionCervicalCompleta=Boolean.FALSE;

    private Boolean triageGlasgow3=Boolean.FALSE;

    private Boolean triageExposicionMasaEncefalica=Boolean.FALSE;

    private Boolean triageLesionesImpidenRcp=Boolean.FALSE;

    private Boolean triageQuemadurasGraves=Boolean.FALSE;

    private Boolean triageGlasgow14o15=Boolean.FALSE;

    private Boolean triageCadenaCustodia = Boolean.FALSE;

    private Boolean triageEntregadoEntidad = Boolean.FALSE;

    private String triageEntidadEntregaPacienteDifunto;

    private String triageNombrePersonaRecibeDifunto;

    private String triageRegistroPersonaRecibeDifunto;

    private String triageOtro;

	public EvaluacionSpringDto() {

	}

    public ProcedimientosSpringDto getProcedimientos() {
        return procedimientos;
    }

    public void setProcedimientos(ProcedimientosSpringDto procedimientos) {
        this.procedimientos = procedimientos;
    }

    public Integer getLocalId() {
        return localId;
    }

    public void setLocalId(Integer localId) {
        this.localId = localId;
    }

    public List<HallazgoSpringDto> getHallazgos() {
        return hallazgos;
    }

    public void setHallazgos(List<HallazgoSpringDto> hallazgos) {
        this.hallazgos = hallazgos;
    }

    public EvaluacionSpringDto(EvaluacionDto evaluacionDto) {
        pupilasNormales=evaluacionDto.getPupilasNormales();
        pupilasAnisocoricas=evaluacionDto.getPupilasAnisocoricas();

        if(evaluacionDto.getEvaluacion() != null) {
            evaluacion = new EvaluacionCompletaSpringDto(evaluacionDto.getEvaluacion());
        }
        if(evaluacionDto.getProcedimientos() != null) {
            procedimientos = new ProcedimientosSpringDto(evaluacionDto.getProcedimientos());
        }
        if(evaluacionDto.getHallazgos() != null) {
            hallazgos = new ArrayList<HallazgoSpringDto>();
            for(HallazgoDto hallazgo : evaluacionDto.getHallazgos())
            {
                hallazgos.add(new HallazgoSpringDto(hallazgo));
            }
        }

        llenadoCapilar=evaluacionDto.getLlenadoCapilar();
        ubicacionPulso=evaluacionDto.getUbicacionPulso();
        sangrado=evaluacionDto.getSangrado();

        otraEmergenciaMedica=evaluacionDto.getOtraEmergenciaMedica();

        permeavilidadViaAerea=evaluacionDto.getPermeavilidadViaAerea();

        causaObstruccionVia=evaluacionDto.getCausaObstruccionVia();

        estadoRespiracion=evaluacionDto.getEstadoRespiracion();
        cianosis=evaluacionDto.getCianosis();

        respiracion=evaluacionDto.getRespiracion();

        pulso=evaluacionDto.getPulso();

        ritmoPulso=evaluacionDto.getRitmoPulso();

        fuerzaPulso=evaluacionDto.getFuerzaPulso();

        nivelRespuesta=evaluacionDto.getNivelRespuesta();

        pielNormal=evaluacionDto.getPielNormal();

        pielPalida=evaluacionDto.getPielPalida();

        pielCaliente=evaluacionDto.getPielCaliente();

        pielCianotica=evaluacionDto.getPielCianotica();

        pielIcterica=evaluacionDto.getPielIcterica();

        pielFria=evaluacionDto.getPielFria();

        pielHumeda=evaluacionDto.getPielHumeda();

        pielSeca=evaluacionDto.getPielSeca();

        pupilasMioticas=evaluacionDto.getPupilasMioticas();

        pupilasMidriaticas=evaluacionDto.getPupilasMidriaticas();
        pupilasIsocoricas=evaluacionDto.getPupilasIsocoricas();

        pupilasReactivas=evaluacionDto.getPupilasReactivas();

        pupilasNoReactivas=evaluacionDto.getPupilasNoReactivas();

        pupilasNoEvaluables=evaluacionDto.getPupilasNoEvaluables();
        glasgowRO=evaluacionDto.getGlasgowRO();

        glasgowRV=evaluacionDto.getGlasgowRV();

        glasgowRM=evaluacionDto.getGlasgowRM();

        tipoEmergencia=evaluacionDto.getTipoEmergencia();

        politrauma=evaluacionDto.getPolitrauma();

        tec=evaluacionDto.getTec();

        maxilofacial=evaluacionDto.getMaxilofacial();

        raquimedular=evaluacionDto.getRaquimedular();

        torax=evaluacionDto.getTorax();

        abdomen=evaluacionDto.getAbdomen();

        pelvico=evaluacionDto.getPelvico();

        extremidadSuperior=evaluacionDto.getExtremidadSuperior();

        extremidadInferior=evaluacionDto.getExtremidadInferior();

        tejidosBlandos=evaluacionDto.getTejidosBlandos();

        osteomuscular=evaluacionDto.getOsteomuscular();

        organosSentidos=evaluacionDto.getOrganosSentidos();

        otroTrauma=evaluacionDto.getOtroTrauma();

        cualOtroTrauma=evaluacionDto.getCualOtroTrauma();

        prioridadTriage=evaluacionDto.getPrioridadTriage();


        triageViaAreaObstruida=evaluacionDto.getTriageViaAreaObstruida();

        triageInsuficienciaRespiratoria=evaluacionDto.getTriageInsuficienciaRespiratoria();

        triagePa90=evaluacionDto.getTriagePa90();

        triageMultiplesHeridas=evaluacionDto.getTriageMultiplesHeridas();

        triageHemorragiasNoControladas=evaluacionDto.getTriageHemorragiasNoControladas();

        triageLesionCervicalIncompleta=evaluacionDto.getTriageLesionCervicalIncompleta();

        triageGlasgow4a8=evaluacionDto.getTriageGlasgow4a8();

        triageExcitacionPsicomotora=evaluacionDto.getTriageExcitacionPsicomotora();

        triageAbdomenAgudo=evaluacionDto.getTriageAbdomenAgudo();

        triageEvisceracion=evaluacionDto.getTriageEvisceracion();

        triageTrabajoPartoSangrado=evaluacionDto.getTriageTrabajoPartoSangrado();

        triageDolorToracico=evaluacionDto.getTriageDolorToracico();

        triageArritmias=evaluacionDto.getTriageArritmias();

        triageHemorragiasControladas=evaluacionDto.getTriageHemorragiasControladas();

        triageLesionMedularDorsal=evaluacionDto.getTriageLesionMedularDorsal();

        triageGlasgow9a13=evaluacionDto.getTriageGlasgow9a13();

        triageAlteracionEstadoConciencia=evaluacionDto.getTriageAlteracionEstadoConciencia();

        triageFracturasMayores=evaluacionDto.getTriageFracturasMayores();

        triageQuemadurasModeradas=evaluacionDto.getTriageQuemadurasModeradas();

        triageIntoxicacion=evaluacionDto.getTriageIntoxicacion();

        triageLesionMedularLumbar=evaluacionDto.getTriageLesionMedularLumbar();

        triageFracturasNoProximales=evaluacionDto.getTriageFracturasNoProximales();

        triageLesionesSuperficiales=evaluacionDto.getTriageLesionesSuperficiales();

        triageQuemadurasPrimerGrado=evaluacionDto.getTriageQuemadurasPrimerGrado();

        triageAfectados=evaluacionDto.getTriageAfectados();

        triageParoProlongado=evaluacionDto.getTriageParoProlongado();

        triageLesionCervicalCompleta=evaluacionDto.getTriageLesionCervicalCompleta();

        triageGlasgow3=evaluacionDto.getTriageGlasgow3();

        triageExposicionMasaEncefalica=evaluacionDto.getTriageExposicionMasaEncefalica();

        triageLesionesImpidenRcp=evaluacionDto.getTriageLesionesImpidenRcp();

        triageQuemadurasGraves=evaluacionDto.getTriageQuemadurasGraves();

        triageGlasgow14o15=evaluacionDto.getTriageGlasgow14o15();

        triageCadenaCustodia=evaluacionDto.getTriageCadenaCustodia();

        triageEntregadoEntidad=evaluacionDto.getTriageEntregadoEntidad();

        triageEntidadEntregaPacienteDifunto=evaluacionDto.getTriageEntidadEntregaPacienteDifunto();

        triageNombrePersonaRecibeDifunto=evaluacionDto.getTriageNombrePersonaRecibeDifunto();

        triageRegistroPersonaRecibeDifunto=evaluacionDto.getTriageRegistroPersonaRecibeDifunto();

        triageOtro=evaluacionDto.getTriageOtro();
    }

	/**
	 * @return the evaluacion
	 */
	public EvaluacionCompletaSpringDto getEvaluacion() {
		return evaluacion;
	}

	/**
	 * @param evaluacion the evaluacion to set
	 */
	public void setEvaluacion(EvaluacionCompletaSpringDto evaluacion) {
		this.evaluacion = evaluacion;
	}



	public Integer getPermeavilidadViaAerea() {
		return permeavilidadViaAerea;
	}

	public void setPermeavilidadViaAerea(Integer permeavilidadViaAerea) {
		this.permeavilidadViaAerea = permeavilidadViaAerea;
	}

	public String getCausaObstruccionVia() {
		return causaObstruccionVia;
	}

	public void setCausaObstruccionVia(String causaObstruccionVia) {
		this.causaObstruccionVia = causaObstruccionVia;
	}

	public Integer getRespiracion() {
		return respiracion;
	}

	public void setRespiracion(Integer respiracion) {
		this.respiracion = respiracion;
	}

	public Integer getPulso() {
		return pulso;
	}

	public void setPulso(Integer pulso) {
		this.pulso = pulso;
	}

	public Integer getRitmoPulso() {
		return ritmoPulso;
	}

	public void setRitmoPulso(Integer ritmoPulso) {
		this.ritmoPulso = ritmoPulso;
	}

	public Integer getFuerzaPulso() {
		return fuerzaPulso;
	}

	public void setFuerzaPulso(Integer fuerzaPulso) {
		this.fuerzaPulso = fuerzaPulso;
	}

	public Integer getNivelRespuesta() {
		return nivelRespuesta;
	}

	public void setNivelRespuesta(Integer nivelRespuesta) {
		this.nivelRespuesta = nivelRespuesta;
	}

	public Boolean getPielNormal() {
		return pielNormal;
	}

	public void setPielNormal(Boolean pielNormal) {
		this.pielNormal = pielNormal;
	}

	public Boolean getPielPalida() {
		return pielPalida;
	}

	public void setPielPalida(Boolean pielPalida) {
		this.pielPalida = pielPalida;
	}

	public Boolean getPielCaliente() {
		return pielCaliente;
	}

	public void setPielCaliente(Boolean pielCaliente) {
		this.pielCaliente = pielCaliente;
	}

	public Boolean getPielCianotica() {
		return pielCianotica;
	}

	public void setPielCianotica(Boolean pielCianotica) {
		this.pielCianotica = pielCianotica;
	}

	public Boolean getPielHumeda() {
		return pielHumeda;
	}

	public void setPielHumeda(Boolean pielHumeda) {
		this.pielHumeda = pielHumeda;
	}



	public Boolean getPielSeca() {
		return pielSeca;
	}

	public void setPielSeca(Boolean pielSeca) {
		this.pielSeca = pielSeca;
	}

	public Boolean getPupilasMioticas() {
		return pupilasMioticas;
	}

	public void setPupilasMioticas(Boolean pupilasMioticas) {
		this.pupilasMioticas = pupilasMioticas;
	}

	public Boolean getPupilasMidriaticas() {
		return pupilasMidriaticas;
	}

	public void setPupilasMidriaticas(Boolean pupilasMidriaticas) {
		this.pupilasMidriaticas = pupilasMidriaticas;
	}



	public Boolean getPupilasIsocoricas() {
		return pupilasIsocoricas;
	}

	public void setPupilasIsocoricas(Boolean pupilasIsocoricas) {
		this.pupilasIsocoricas = pupilasIsocoricas;
	}

	public Boolean getPupilasReactivas() {
		return pupilasReactivas;
	}

	public void setPupilasReactivas(Boolean pupilasReactivas) {
		this.pupilasReactivas = pupilasReactivas;
	}

	public Boolean getPupilasNoReactivas() {
		return pupilasNoReactivas;
	}

	public void setPupilasNoReactivas(Boolean pupilasNoReactivas) {
		this.pupilasNoReactivas = pupilasNoReactivas;
	}

	public Boolean getPupilasNoEvaluables() {
		return pupilasNoEvaluables;
	}

	public void setPupilasNoEvaluables(Boolean pupilasNoEvaluables) {
		this.pupilasNoEvaluables = pupilasNoEvaluables;
	}



	public Integer getGlasgowRO() {
		return glasgowRO;
	}

	public void setGlasgowRO(Integer glasgowRO) {
		this.glasgowRO = glasgowRO;
	}

	public Integer getGlasgowRV() {
		return glasgowRV;
	}

	public void setGlasgowRV(Integer glasgowRV) {
		this.glasgowRV = glasgowRV;
	}

	public Integer getGlasgowRM() {
		return glasgowRM;
	}

	public void setGlasgowRM(Integer glasgowRM) {
		this.glasgowRM = glasgowRM;
	}

	public Integer getTipoEmergencia() {
		return tipoEmergencia;
	}

	public void setTipoEmergencia(Integer tipoEmergencia) {
		this.tipoEmergencia = tipoEmergencia;
	}

	public Boolean getPolitrauma() {
		return politrauma;
	}

	public void setPolitrauma(Boolean politrauma) {
		this.politrauma = politrauma;
	}

	public Boolean getTec() {
		return tec;
	}

	public void setTec(Boolean tec) {
		this.tec = tec;
	}

	public Boolean getMaxilofacial() {
		return maxilofacial;
	}

	public void setMaxilofacial(Boolean maxilofacial) {
		this.maxilofacial = maxilofacial;
	}

	public Boolean getRaquimedular() {
		return raquimedular;
	}

	public void setRaquimedular(Boolean raquimedular) {
		this.raquimedular = raquimedular;
	}

	public Boolean getTorax() {
		return torax;
	}

	public void setTorax(Boolean torax) {
		this.torax = torax;
	}

	public Boolean getAbdomen() {
		return abdomen;
	}

	public void setAbdomen(Boolean abdomen) {
		this.abdomen = abdomen;
	}

	public Boolean getPelvico() {
		return pelvico;
	}

	public void setPelvico(Boolean pelvico) {
		this.pelvico = pelvico;
	}

	public Boolean getExtremidadSuperior() {
		return extremidadSuperior;
	}

	public void setExtremidadSuperior(Boolean extremidadSuperior) {
		this.extremidadSuperior = extremidadSuperior;
	}

	public Boolean getExtremidadInferior() {
		return extremidadInferior;
	}

	public void setExtremidadInferior(Boolean extremidadInferior) {
		this.extremidadInferior = extremidadInferior;
	}

	public Boolean getTejidosBlandos() {
		return tejidosBlandos;
	}

	public void setTejidosBlandos(Boolean tejidosBlandos) {
		this.tejidosBlandos = tejidosBlandos;
	}

	public Boolean getOsteomuscular() {
		return osteomuscular;
	}

	public void setOsteomuscular(Boolean osteomuscular) {
		this.osteomuscular = osteomuscular;
	}

	public Boolean getOrganosSentidos() {
		return organosSentidos;
	}

	public void setOrganosSentidos(Boolean organosSentidos) {
		this.organosSentidos = organosSentidos;
	}

	public Boolean getOtroTrauma() {
		return otroTrauma;
	}

	public void setOtroTrauma(Boolean otroTrauma) {
		this.otroTrauma = otroTrauma;
	}

	public String getCualOtroTrauma() {
		return cualOtroTrauma;
	}

	public void setCualOtroTrauma(String cualOtroTrauma) {
		this.cualOtroTrauma = cualOtroTrauma;
	}

	public Integer getPrioridadTriage() {
		return prioridadTriage;
	}

	public void setPrioridadTriage(Integer prioridadTriage) {
		this.prioridadTriage = prioridadTriage;
	}




    public String getTriageOtro() {
        return triageOtro;
    }

    public void setTriageOtro(String triageOtro) {
        this.triageOtro = triageOtro;
    }

    public Boolean getTriageGlasgow14o15() {
        return triageGlasgow14o15;
    }

    public void setTriageGlasgow14o15(Boolean triageGlasgow14o15) {
        this.triageGlasgow14o15 = triageGlasgow14o15;
    }

    public Boolean getTriageQuemadurasGraves() {
        return triageQuemadurasGraves;
    }

    public void setTriageQuemadurasGraves(Boolean triageQuemadurasGraves) {
        this.triageQuemadurasGraves = triageQuemadurasGraves;
    }

    public Boolean getTriageLesionesImpidenRcp() {
        return triageLesionesImpidenRcp;
    }

    public void setTriageLesionesImpidenRcp(Boolean triageLesionesImpidenRcp) {
        this.triageLesionesImpidenRcp = triageLesionesImpidenRcp;
    }

    public Boolean getTriageExposicionMasaEncefalica() {
        return triageExposicionMasaEncefalica;
    }

    public void setTriageExposicionMasaEncefalica(Boolean triageExposicionMasaEncefalica) {
        this.triageExposicionMasaEncefalica = triageExposicionMasaEncefalica;
    }

    public Boolean getTriageGlasgow3() {
        return triageGlasgow3;
    }

    public void setTriageGlasgow3(Boolean triageGlasgow3) {
        this.triageGlasgow3 = triageGlasgow3;
    }

    public Boolean getTriageLesionCervicalCompleta() {
        return triageLesionCervicalCompleta;
    }

    public void setTriageLesionCervicalCompleta(Boolean triageLesionCervicalCompleta) {
        this.triageLesionCervicalCompleta = triageLesionCervicalCompleta;
    }

    public Boolean getTriageParoProlongado() {
        return triageParoProlongado;
    }

    public void setTriageParoProlongado(Boolean triageParoProlongado) {
        this.triageParoProlongado = triageParoProlongado;
    }

    public Boolean getTriageAfectados() {
        return triageAfectados;
    }

    public void setTriageAfectados(Boolean triageAfectados) {
        this.triageAfectados = triageAfectados;
    }

    public Boolean getTriageQuemadurasPrimerGrado() {
        return triageQuemadurasPrimerGrado;
    }

    public void setTriageQuemadurasPrimerGrado(Boolean triageQuemadurasPrimerGrado) {
        this.triageQuemadurasPrimerGrado = triageQuemadurasPrimerGrado;
    }

    public Boolean getTriageLesionesSuperficiales() {
        return triageLesionesSuperficiales;
    }

    public void setTriageLesionesSuperficiales(Boolean triageLesionesSuperficiales) {
        this.triageLesionesSuperficiales = triageLesionesSuperficiales;
    }

    public Boolean getTriageFracturasNoProximales() {
        return triageFracturasNoProximales;
    }

    public void setTriageFracturasNoProximales(Boolean triageFracturasNoProximales) {
        this.triageFracturasNoProximales = triageFracturasNoProximales;
    }

    public Boolean getTriageLesionMedularLumbar() {
        return triageLesionMedularLumbar;
    }

    public void setTriageLesionMedularLumbar(Boolean triageLesionMedularLumbar) {
        this.triageLesionMedularLumbar = triageLesionMedularLumbar;
    }

    public Boolean getTriageIntoxicacion() {
        return triageIntoxicacion;
    }

    public void setTriageIntoxicacion(Boolean triageIntoxicacion) {
        this.triageIntoxicacion = triageIntoxicacion;
    }

    public Boolean getTriageQuemadurasModeradas() {
        return triageQuemadurasModeradas;
    }

    public void setTriageQuemadurasModeradas(Boolean triageQuemadurasModeradas) {
        this.triageQuemadurasModeradas = triageQuemadurasModeradas;
    }

    public Boolean getTriageFracturasMayores() {
        return triageFracturasMayores;
    }

    public void setTriageFracturasMayores(Boolean triageFracturasMayores) {
        this.triageFracturasMayores = triageFracturasMayores;
    }

    public Boolean getTriageAlteracionEstadoConciencia() {
        return triageAlteracionEstadoConciencia;
    }

    public void setTriageAlteracionEstadoConciencia(Boolean triageAlteracionEstadoConciencia) {
        this.triageAlteracionEstadoConciencia = triageAlteracionEstadoConciencia;
    }

    public Boolean getTriageGlasgow9a13() {
        return triageGlasgow9a13;
    }

    public void setTriageGlasgow9a13(Boolean triageGlasgow9a13) {
        this.triageGlasgow9a13 = triageGlasgow9a13;
    }

    public Boolean getTriageLesionMedularDorsal() {
        return triageLesionMedularDorsal;
    }

    public void setTriageLesionMedularDorsal(Boolean triageLesionMedularDorsal) {
        this.triageLesionMedularDorsal = triageLesionMedularDorsal;
    }

    public Boolean getTriageHemorragiasControladas() {
        return triageHemorragiasControladas;
    }

    public void setTriageHemorragiasControladas(Boolean triageHemorragiasControladas) {
        this.triageHemorragiasControladas = triageHemorragiasControladas;
    }

    public Boolean getTriageArritmias() {
        return triageArritmias;
    }

    public void setTriageArritmias(Boolean triageArritmias) {
        this.triageArritmias = triageArritmias;
    }

    public Boolean getTriageDolorToracico() {
        return triageDolorToracico;
    }

    public void setTriageDolorToracico(Boolean triageDolorToracico) {
        this.triageDolorToracico = triageDolorToracico;
    }

    public Boolean getTriageTrabajoPartoSangrado() {
        return triageTrabajoPartoSangrado;
    }

    public void setTriageTrabajoPartoSangrado(Boolean triageTrabajoPartoSangrado) {
        this.triageTrabajoPartoSangrado = triageTrabajoPartoSangrado;
    }

    public Boolean getTriageEvisceracion() {
        return triageEvisceracion;
    }

    public void setTriageEvisceracion(Boolean triageEvisceracion) {
        this.triageEvisceracion = triageEvisceracion;
    }

    public Boolean getTriageAbdomenAgudo() {
        return triageAbdomenAgudo;
    }

    public void setTriageAbdomenAgudo(Boolean triageAbdomenAgudo) {
        this.triageAbdomenAgudo = triageAbdomenAgudo;
    }

    public Boolean getTriageExcitacionPsicomotora() {
        return triageExcitacionPsicomotora;
    }

    public void setTriageExcitacionPsicomotora(Boolean triageExcitacionPsicomotora) {
        this.triageExcitacionPsicomotora = triageExcitacionPsicomotora;
    }

    public Boolean getTriageGlasgow4a8() {
        return triageGlasgow4a8;
    }

    public void setTriageGlasgow4a8(Boolean triageGlasgow4a8) {
        this.triageGlasgow4a8 = triageGlasgow4a8;
    }

    public Boolean getTriageLesionCervicalIncompleta() {
        return triageLesionCervicalIncompleta;
    }

    public void setTriageLesionCervicalIncompleta(Boolean triageLesionCervicalIncompleta) {
        this.triageLesionCervicalIncompleta = triageLesionCervicalIncompleta;
    }

    public Boolean getTriageHemorragiasNoControladas() {
        return triageHemorragiasNoControladas;
    }

    public void setTriageHemorragiasNoControladas(Boolean triageHemorragiasNoControladas) {
        this.triageHemorragiasNoControladas = triageHemorragiasNoControladas;
    }

    public Boolean getTriageMultiplesHeridas() {
        return triageMultiplesHeridas;
    }

    public void setTriageMultiplesHeridas(Boolean triageMultiplesHeridas) {
        this.triageMultiplesHeridas = triageMultiplesHeridas;
    }

    public Boolean getTriagePa90() {
        return triagePa90;
    }

    public void setTriagePa90(Boolean triagePa90) {
        this.triagePa90 = triagePa90;
    }

    public Boolean getTriageInsuficienciaRespiratoria() {
        return triageInsuficienciaRespiratoria;
    }

    public void setTriageInsuficienciaRespiratoria(Boolean triageInsuficienciaRespiratoria) {
        this.triageInsuficienciaRespiratoria = triageInsuficienciaRespiratoria;
    }

    public Boolean getTriageViaAreaObstruida() {
        return triageViaAreaObstruida;
    }

    public void setTriageViaAreaObstruida(Boolean triageViaAreaObstruida) {
        this.triageViaAreaObstruida = triageViaAreaObstruida;
    }

    public Boolean getPupilasNormales() {
        return pupilasNormales;
    }

    public void setPupilasNormales(Boolean pupilasNormales) {
        this.pupilasNormales = pupilasNormales;
    }

    public Boolean getPupilasAnisocoricas() {
        return pupilasAnisocoricas;
    }

    public void setPupilasAnisocoricas(Boolean pupilasAnisocoricas) {
        this.pupilasAnisocoricas = pupilasAnisocoricas;
    }

    public Integer getLlenadoCapilar() {
        return llenadoCapilar;
    }

    public void setLlenadoCapilar(Integer llenadoCapilar) {
        this.llenadoCapilar = llenadoCapilar;
    }

    public Integer getUbicacionPulso() {
        return ubicacionPulso;
    }

    public void setUbicacionPulso(Integer ubicacionPulso) {
        this.ubicacionPulso = ubicacionPulso;
    }

    public Integer getSangrado() {
        return sangrado;
    }

    public void setSangrado(Integer sangrado) {
        this.sangrado = sangrado;
    }

    public String getOtraEmergenciaMedica() {
        return otraEmergenciaMedica;
    }

    public void setOtraEmergenciaMedica(String otraEmergenciaMedica) {
        this.otraEmergenciaMedica = otraEmergenciaMedica;
    }

    public Integer getEstadoRespiracion() {
        return estadoRespiracion;
    }

    public void setEstadoRespiracion(Integer estadoRespiracion) {
        this.estadoRespiracion = estadoRespiracion;
    }

    public Integer getCianosis() {
        return cianosis;
    }

    public void setCianosis(Integer cianosis) {
        this.cianosis = cianosis;
    }

    public Boolean getPielIcterica() {
        return pielIcterica;
    }

    public void setPielIcterica(Boolean pielIcterica) {
        this.pielIcterica = pielIcterica;
    }

    public Boolean getPielFria() {
        return pielFria;
    }

    public void setPielFria(Boolean pielFria) {
        this.pielFria = pielFria;
    }

    public Boolean getTriageCadenaCustodia() {
        return triageCadenaCustodia;
    }

    public void setTriageCadenaCustodia(Boolean triageCadenaCustodia) {
        this.triageCadenaCustodia = triageCadenaCustodia;
    }

    public Boolean getTriageEntregadoEntidad() {
        return triageEntregadoEntidad;
    }

    public void setTriageEntregadoEntidad(Boolean triageEntregadoEntidad) {
        this.triageEntregadoEntidad = triageEntregadoEntidad;
    }

    public String getTriageEntidadEntregaPacienteDifunto() {
        return triageEntidadEntregaPacienteDifunto;
    }

    public void setTriageEntidadEntregaPacienteDifunto(String triageEntidadEntregaPacienteDifunto) {
        this.triageEntidadEntregaPacienteDifunto = triageEntidadEntregaPacienteDifunto;
    }

    public String getTriageNombrePersonaRecibeDifunto() {
        return triageNombrePersonaRecibeDifunto;
    }

    public void setTriageNombrePersonaRecibeDifunto(String triageNombrePersonaRecibeDifunto) {
        this.triageNombrePersonaRecibeDifunto = triageNombrePersonaRecibeDifunto;
    }

    public String getTriageRegistroPersonaRecibeDifunto() {
        return triageRegistroPersonaRecibeDifunto;
    }

    public void setTriageRegistroPersonaRecibeDifunto(String triageRegistroPersonaRecibeDifunto) {
        this.triageRegistroPersonaRecibeDifunto = triageRegistroPersonaRecibeDifunto;
    }
}
