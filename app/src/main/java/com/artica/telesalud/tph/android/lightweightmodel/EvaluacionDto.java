package com.artica.telesalud.tph.android.lightweightmodel;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;

import com.artica.telesalud.tph.android.model.EvaluacionSpringDto;
import com.artica.telesalud.tph.android.model.HallazgoSpringDto;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by interoperabilidad on 18/07/14.
 */
@DatabaseTable()
public class EvaluacionDto implements Serializable, Parcelable {

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


    private Boolean selected = Boolean.FALSE;
    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField()
    private Integer llenadoCapilar;
    @DatabaseField()
    private Integer ubicacionPulso;
    @DatabaseField()
    private Integer sangrado;
    @DatabaseField()
    private String otraEmergenciaMedica;
    @DatabaseField()
    private Integer permeavilidadViaAerea;
    @DatabaseField()
    private String causaObstruccionVia;
    @DatabaseField()
    private Integer estadoRespiracion;
    @DatabaseField
    private Integer cianosis;
    @DatabaseField()
    private Integer respiracion;
    @DatabaseField()
    private Integer pulso;
    @DatabaseField()
    private Integer ritmoPulso;
    @DatabaseField()
    private Integer fuerzaPulso;
    @DatabaseField()
    private Integer nivelRespuesta;
    @DatabaseField()
    private Boolean pielNormal=Boolean.FALSE;
    @DatabaseField()
    private Boolean pielPalida=Boolean.FALSE;
    @DatabaseField()
    private Boolean pielCaliente=Boolean.FALSE;
    @DatabaseField()
    private Boolean pielCianotica=Boolean.FALSE;
    @DatabaseField()
    private Boolean pielIcterica=Boolean.FALSE;
    @DatabaseField()
    private Boolean pielFria=Boolean.FALSE;
    @DatabaseField()
    private Boolean pielHumeda=Boolean.FALSE;
    @DatabaseField()
    private Boolean pielSeca=Boolean.FALSE;
    @DatabaseField()
    private Boolean pupilasMioticas=Boolean.FALSE;
    @DatabaseField()
    private Boolean pupilasMidriaticas=Boolean.FALSE;
    @DatabaseField()
    private Boolean pupilasAnisocoricas=Boolean.FALSE;
    @DatabaseField()
    private Boolean pupilasIsocoricas=Boolean.FALSE;
    @DatabaseField()
    private Boolean pupilasReactivas=Boolean.FALSE;
    @DatabaseField()
    private Boolean pupilasNoReactivas=Boolean.FALSE;
    @DatabaseField()
    private Boolean pupilasNoEvaluables=Boolean.FALSE;
    @DatabaseField()
    private Boolean pupilasNormales=Boolean.FALSE;
    @DatabaseField()
    private Integer glasgowRO;
    @DatabaseField()
    private Integer glasgowRV;
    @DatabaseField()
    private Integer glasgowRM;
    @DatabaseField()
    private Integer tipoEmergencia;
    @DatabaseField()
    private Boolean politrauma=Boolean.FALSE;
    @DatabaseField()
    private Boolean tec=Boolean.FALSE;
    @DatabaseField()
    private Boolean maxilofacial=Boolean.FALSE;
    @DatabaseField()
    private Boolean raquimedular=Boolean.FALSE;
    @DatabaseField()
    private Boolean torax=Boolean.FALSE;
    @DatabaseField()
    private Boolean abdomen=Boolean.FALSE;
    @DatabaseField()
    private Boolean pelvico=Boolean.FALSE;
    @DatabaseField()
    private Boolean extremidadSuperior=Boolean.FALSE;
    @DatabaseField()
    private Boolean extremidadInferior=Boolean.FALSE;
    @DatabaseField()
    private Boolean tejidosBlandos=Boolean.FALSE;
    @DatabaseField()
    private Boolean osteomuscular=Boolean.FALSE;
    @DatabaseField()
    private Boolean organosSentidos=Boolean.FALSE;
    @DatabaseField()
    private Boolean otroTrauma=Boolean.FALSE;
    @DatabaseField()
    private String cualOtroTrauma;
    @DatabaseField()
    private Integer prioridadTriage;
    @DatabaseField()
    private Integer evaluacionId;
    private EvaluacionCompletaDto evaluacion;
    @DatabaseField()
    private Integer procedimientosId;
    private ProcedimientosDto procedimientos = new ProcedimientosDto();
    @DatabaseField()
    private Boolean triageViaAreaObstruida=Boolean.FALSE;
    @DatabaseField()
    private Boolean triageInsuficienciaRespiratoria=Boolean.FALSE;
    @DatabaseField()
    private Boolean triagePa90=Boolean.FALSE;
    @DatabaseField()
    private Boolean triageMultiplesHeridas=Boolean.FALSE;
    @DatabaseField()
    private Boolean triageHemorragiasNoControladas=Boolean.FALSE;
    @DatabaseField()
    private Boolean triageLesionCervicalIncompleta=Boolean.FALSE;
    @DatabaseField()
    private Boolean triageGlasgow4a8=Boolean.FALSE;
    @DatabaseField()
    private Boolean triageExcitacionPsicomotora=Boolean.FALSE;
    @DatabaseField()
    private Boolean triageAbdomenAgudo=Boolean.FALSE;
    @DatabaseField()
    private Boolean triageEvisceracion=Boolean.FALSE;
    @DatabaseField()
    private Boolean triageTrabajoPartoSangrado=Boolean.FALSE;
    @DatabaseField()
    private Boolean triageDolorToracico=Boolean.FALSE;
    @DatabaseField()
    private Boolean triageArritmias=Boolean.FALSE;
    @DatabaseField()
    private Boolean triageHemorragiasControladas=Boolean.FALSE;
    @DatabaseField()
    private Boolean triageLesionMedularDorsal=Boolean.FALSE;
    @DatabaseField()
    private Boolean triageGlasgow9a13=Boolean.FALSE;
    @DatabaseField()
    private Boolean triageAlteracionEstadoConciencia=Boolean.FALSE;
    @DatabaseField()
    private Boolean triageFracturasMayores=Boolean.FALSE;
    @DatabaseField()
    private Boolean triageQuemadurasModeradas=Boolean.FALSE;
    @DatabaseField()
    private Boolean triageIntoxicacion=Boolean.FALSE;
    @DatabaseField()
    private Boolean triageLesionMedularLumbar=Boolean.FALSE;
    @DatabaseField()
    private Boolean triageFracturasNoProximales=Boolean.FALSE;
    @DatabaseField()
    private Boolean triageLesionesSuperficiales=Boolean.FALSE;
    @DatabaseField()
    private Boolean triageQuemadurasPrimerGrado=Boolean.FALSE;
    @DatabaseField()
    private Boolean triageAfectados=Boolean.FALSE;
    @DatabaseField()
    private Boolean triageParoProlongado=Boolean.FALSE;
    @DatabaseField()
    private Boolean triageLesionCervicalCompleta=Boolean.FALSE;
    @DatabaseField()
    private Boolean triageGlasgow3=Boolean.FALSE;
    @DatabaseField()
    private Boolean triageExposicionMasaEncefalica=Boolean.FALSE;
    @DatabaseField()
    private Boolean triageLesionesImpidenRcp=Boolean.FALSE;
    @DatabaseField()
    private Boolean triageQuemadurasGraves=Boolean.FALSE;
    @DatabaseField()
    private Boolean triageGlasgow14o15=Boolean.FALSE;
    @DatabaseField()
    private Boolean triageCadenaCustodia = Boolean.FALSE;
    @DatabaseField()
    private Boolean triageEntregadoEntidad = Boolean.FALSE;
    @DatabaseField()
    private String triageEntidadEntregaPacienteDifunto;
    @DatabaseField()
    private String triageNombrePersonaRecibeDifunto;
    @DatabaseField()
    private String triageRegistroPersonaRecibeDifunto;
    @DatabaseField()
    private String triageOtro;
    private List<HallazgoDto> hallazgos = new ArrayList<HallazgoDto>();
    @DatabaseField()
    private Boolean isSynchronized = Boolean.FALSE;

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

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public Boolean getIsSynchronized() {
        return isSynchronized;
    }

    public void setIsSynchronized(Boolean isSynchronized) {
        this.isSynchronized = isSynchronized;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static final Parcelable.Creator<EvaluacionDto> CREATOR = new Parcelable.Creator<EvaluacionDto>() {
        public EvaluacionDto createFromParcel(Parcel in) {
            return new EvaluacionDto(in);
        }

        public EvaluacionDto[] newArray(int size) {
            return new EvaluacionDto[size];
        }
    };

    public ProcedimientosDto getProcedimientos() {
        return procedimientos;
    }

    public void setProcedimientos(ProcedimientosDto procedimientos) {
        this.procedimientos = procedimientos;
    }

    public List<HallazgoDto> getHallazgos() {
        return hallazgos;
    }

    public void setHallazgos(List<HallazgoDto> hallazgos) {
        this.hallazgos = hallazgos;
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

    public Boolean getPupilasNormales() {
        return pupilasNormales;
    }

    public void setPupilasNormales(Boolean pupilasNormales) {
        this.pupilasNormales = pupilasNormales;
    }

    public Integer getCianosis() {
        return cianosis;
    }

    public void setCianosis(Integer cianosis) {
        this.cianosis = cianosis;
    }

    public EvaluacionDto(Parcel parcel) {
        id = (Integer) parcel.readValue(Integer.class.getClassLoader());
        permeavilidadViaAerea = (Integer) parcel.readValue(Integer.class.getClassLoader());
        causaObstruccionVia = (String) parcel.readValue(null);
        respiracion = (Integer) parcel.readValue(Integer.class.getClassLoader());
        pulso = (Integer) parcel.readValue(Integer.class.getClassLoader());
        ritmoPulso = (Integer) parcel.readValue(Integer.class.getClassLoader());
        fuerzaPulso = (Integer) parcel.readValue(Integer.class.getClassLoader());
        nivelRespuesta = (Integer) parcel.readValue(Integer.class.getClassLoader());
        pielNormal = (Boolean) parcel.readValue(null);
        pielPalida = (Boolean) parcel.readValue(null);
        pielCaliente = (Boolean) parcel.readValue(null);
        pielCianotica = (Boolean) parcel.readValue(null);
        pielHumeda = (Boolean) parcel.readValue(null);
        pielSeca = (Boolean) parcel.readValue(null);
        pupilasNormales = (Boolean) parcel.readValue(null);
        pupilasMioticas = (Boolean) parcel.readValue(null);
        pupilasMidriaticas = (Boolean) parcel.readValue(null);
        pupilasAnisocoricas = (Boolean) parcel.readValue(null);
        pupilasIsocoricas = (Boolean) parcel.readValue(null);
        pupilasReactivas = (Boolean) parcel.readValue(null);
        pupilasNoReactivas = (Boolean) parcel.readValue(null);
        pupilasNoEvaluables = (Boolean) parcel.readValue(null);
        glasgowRO = (Integer) parcel.readValue(Integer.class.getClassLoader());
        glasgowRV = (Integer) parcel.readValue(Integer.class.getClassLoader());
        glasgowRM = (Integer) parcel.readValue(Integer.class.getClassLoader());
        tipoEmergencia = (Integer) parcel.readValue(Integer.class.getClassLoader());
        politrauma = (Boolean) parcel.readValue(null);
        tec = (Boolean) parcel.readValue(null);
        maxilofacial = (Boolean) parcel.readValue(null);
        raquimedular = (Boolean) parcel.readValue(null);
        torax = (Boolean) parcel.readValue(null);
        abdomen = (Boolean) parcel.readValue(null);
        pelvico = (Boolean) parcel.readValue(null);
        extremidadSuperior = (Boolean) parcel.readValue(null);
        extremidadInferior = (Boolean) parcel.readValue(null);
        tejidosBlandos = (Boolean) parcel.readValue(null);
        osteomuscular = (Boolean) parcel.readValue(null);
        organosSentidos = (Boolean) parcel.readValue(null);
        otroTrauma = (Boolean) parcel.readValue(null);
        cualOtroTrauma = (String) parcel.readValue(null);
        prioridadTriage = (Integer) parcel.readValue(Integer.class.getClassLoader());
        evaluacion = (EvaluacionCompletaDto) parcel.readValue(EvaluacionCompletaDto.class.getClassLoader());
        triageViaAreaObstruida = (Boolean) parcel.readValue(null);
        triageInsuficienciaRespiratoria = (Boolean) parcel.readValue(null);
        triagePa90 = (Boolean) parcel.readValue(null);
        triageMultiplesHeridas = (Boolean) parcel.readValue(null);
        triageHemorragiasNoControladas = (Boolean) parcel.readValue(null);
        triageLesionCervicalIncompleta = (Boolean) parcel.readValue(null);
        triageGlasgow4a8 = (Boolean) parcel.readValue(null);
        triageExcitacionPsicomotora = (Boolean) parcel.readValue(null);
        triageAbdomenAgudo = (Boolean) parcel.readValue(null);
        triageEvisceracion = (Boolean) parcel.readValue(null);
        triageTrabajoPartoSangrado = (Boolean) parcel.readValue(null);
        triageDolorToracico = (Boolean) parcel.readValue(null);
        triageArritmias = (Boolean) parcel.readValue(null);
        triageHemorragiasControladas = (Boolean) parcel.readValue(null);
        triageLesionMedularDorsal = (Boolean) parcel.readValue(null);
        triageGlasgow9a13 = (Boolean) parcel.readValue(null);
        triageAlteracionEstadoConciencia = (Boolean) parcel.readValue(null);
        triageFracturasMayores = (Boolean) parcel.readValue(null);
        triageQuemadurasModeradas = (Boolean) parcel.readValue(null);
        triageIntoxicacion = (Boolean) parcel.readValue(null);
        triageLesionMedularLumbar = (Boolean) parcel.readValue(null);
        triageFracturasNoProximales = (Boolean) parcel.readValue(null);
        triageLesionesSuperficiales = (Boolean) parcel.readValue(null);
        triageQuemadurasPrimerGrado = (Boolean) parcel.readValue(null);
        triageAfectados = (Boolean) parcel.readValue(null);
        triageParoProlongado = (Boolean) parcel.readValue(null);
        triageLesionCervicalCompleta = (Boolean) parcel.readValue(null);
        triageGlasgow3 = (Boolean) parcel.readValue(null);
        triageExposicionMasaEncefalica = (Boolean) parcel.readValue(null);
        triageLesionesImpidenRcp = (Boolean) parcel.readValue(null);
        triageQuemadurasGraves = (Boolean) parcel.readValue(null);
        triageOtro = (String) parcel.readValue(null);
        triageGlasgow14o15 = (Boolean) parcel.readValue(null);
        isSynchronized = (Boolean) parcel.readValue(null);
        evaluacionId = (Integer) parcel.readValue(null);
        procedimientosId = (Integer) parcel.readValue(null);
        procedimientos = (ProcedimientosDto) parcel.readValue(ProcedimientosDto.class.getClassLoader());
        parcel.readTypedList(hallazgos, HallazgoDto.CREATOR);
        otraEmergenciaMedica = (String) parcel.readValue(null);
        estadoRespiracion = (Integer) parcel.readValue(null);
        cianosis = (Integer) parcel.readValue(null);
        llenadoCapilar = (Integer) parcel.readValue(null);
        ubicacionPulso = (Integer) parcel.readValue(null);
        sangrado = (Integer) parcel.readValue(null);
        pielIcterica = (Boolean) parcel.readValue(null);
        pielFria = (Boolean) parcel.readValue(null);

        triageCadenaCustodia = (Boolean) parcel.readValue(null);
        triageEntregadoEntidad = (Boolean) parcel.readValue(null);
        triageEntidadEntregaPacienteDifunto = (String) parcel.readValue(null);
        triageNombrePersonaRecibeDifunto = (String) parcel.readValue(null);
        triageRegistroPersonaRecibeDifunto = (String) parcel.readValue(null);
    }

    public Integer getEvaluacionId() {
        return evaluacionId;
    }

    public void setEvaluacionId(Integer evaluacionId) {
        this.evaluacionId = evaluacionId;
    }

    public Integer getProcedimientosId() {
        return procedimientosId;
    }

    public void setProcedimientosId(Integer procedimientosId) {
        this.procedimientosId = procedimientosId;
    }

    public EvaluacionDto() {
    }
    public EvaluacionDto(EvaluacionSpringDto evaluacionDto) {
        isSynchronized = Boolean.TRUE;
        pupilasNormales=evaluacionDto.getPupilasNormales();
        pupilasAnisocoricas=evaluacionDto.getPupilasAnisocoricas();

        if(evaluacionDto.getEvaluacion() != null) {
            evaluacion = new EvaluacionCompletaDto(evaluacionDto.getEvaluacion());
        }
        if(evaluacionDto.getProcedimientos() != null) {
            procedimientos = new ProcedimientosDto(evaluacionDto.getProcedimientos());
        }
        if(evaluacionDto.getHallazgos() != null) {
            hallazgos = new ArrayList<HallazgoDto>();
            for(HallazgoSpringDto hallazgo : evaluacionDto.getHallazgos())
            {
                hallazgos.add(new HallazgoDto(hallazgo));
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

    public String getTriageOtro() {
        return triageOtro;
    }

    public void setTriageOtro(String triageOtro) {
        this.triageOtro = triageOtro;
    }

    public Integer getPermeavilidadViaAerea() {
        return permeavilidadViaAerea;
    }

    public void setPermeavilidadViaAerea(Integer permeavilidadViaAerea) {
        this.permeavilidadViaAerea = permeavilidadViaAerea;
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

    public Boolean getPupilasAnisocoricas() {
        return pupilasAnisocoricas;
    }

    public void setPupilasAnisocoricas(Boolean pupilasAnisocoricas) {
        this.pupilasAnisocoricas = pupilasAnisocoricas;
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


    public EvaluacionCompletaDto getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(EvaluacionCompletaDto evaluacion) {
        this.evaluacion = evaluacion;
    }

    public Boolean getTriageViaAreaObstruida() {
        return triageViaAreaObstruida;
    }

    public void setTriageViaAreaObstruida(Boolean triageViaAreaObstruida) {
        this.triageViaAreaObstruida = triageViaAreaObstruida;
    }

    public Boolean getTriageInsuficienciaRespiratoria() {
        return triageInsuficienciaRespiratoria;
    }

    public void setTriageInsuficienciaRespiratoria(Boolean triageInsuficienciaRespiratoria) {
        this.triageInsuficienciaRespiratoria = triageInsuficienciaRespiratoria;
    }

    public Boolean getTriagePa90() {
        return triagePa90;
    }

    public void setTriagePa90(Boolean triagePa90) {
        this.triagePa90 = triagePa90;
    }

    public Boolean getTriageMultiplesHeridas() {
        return triageMultiplesHeridas;
    }

    public void setTriageMultiplesHeridas(Boolean triageMultiplesHeridas) {
        this.triageMultiplesHeridas = triageMultiplesHeridas;
    }

    public Boolean getTriageHemorragiasNoControladas() {
        return triageHemorragiasNoControladas;
    }

    public void setTriageHemorragiasNoControladas(Boolean triageHemorragiasNoControladas) {
        this.triageHemorragiasNoControladas = triageHemorragiasNoControladas;
    }

    public Boolean getTriageLesionCervicalIncompleta() {
        return triageLesionCervicalIncompleta;
    }

    public void setTriageLesionCervicalIncompleta(Boolean triageLesionCervicalIncompleta) {
        this.triageLesionCervicalIncompleta = triageLesionCervicalIncompleta;
    }

    public Boolean getTriageGlasgow4a8() {
        return triageGlasgow4a8;
    }

    public void setTriageGlasgow4a8(Boolean triageGlasgow4a8) {
        this.triageGlasgow4a8 = triageGlasgow4a8;
    }

    public Boolean getTriageExcitacionPsicomotora() {
        return triageExcitacionPsicomotora;
    }

    public void setTriageExcitacionPsicomotora(Boolean triageExcitacionPsicomotora) {
        this.triageExcitacionPsicomotora = triageExcitacionPsicomotora;
    }

    public Boolean getTriageAbdomenAgudo() {
        return triageAbdomenAgudo;
    }

    public void setTriageAbdomenAgudo(Boolean triageAbdomenAgudo) {
        this.triageAbdomenAgudo = triageAbdomenAgudo;
    }

    public Boolean getTriageEvisceracion() {
        return triageEvisceracion;
    }

    public void setTriageEvisceracion(Boolean triageEvisceracion) {
        this.triageEvisceracion = triageEvisceracion;
    }

    public Boolean getTriageTrabajoPartoSangrado() {
        return triageTrabajoPartoSangrado;
    }

    public void setTriageTrabajoPartoSangrado(Boolean triageTrabajoPartoSangrado) {
        this.triageTrabajoPartoSangrado = triageTrabajoPartoSangrado;
    }

    public Boolean getTriageDolorToracico() {
        return triageDolorToracico;
    }

    public void setTriageDolorToracico(Boolean triageDolorToracico) {
        this.triageDolorToracico = triageDolorToracico;
    }

    public Boolean getTriageArritmias() {
        return triageArritmias;
    }

    public void setTriageArritmias(Boolean triageArritmias) {
        this.triageArritmias = triageArritmias;
    }

    public Boolean getTriageHemorragiasControladas() {
        return triageHemorragiasControladas;
    }

    public void setTriageHemorragiasControladas(Boolean triageHemorragiasControladas) {
        this.triageHemorragiasControladas = triageHemorragiasControladas;
    }

    public Boolean getTriageLesionMedularDorsal() {
        return triageLesionMedularDorsal;
    }

    public void setTriageLesionMedularDorsal(Boolean triageLesionMedularDorsal) {
        this.triageLesionMedularDorsal = triageLesionMedularDorsal;
    }

    public Boolean getTriageGlasgow9a13() {
        return triageGlasgow9a13;
    }

    public void setTriageGlasgow9a13(Boolean triageGlasgow9a13) {
        this.triageGlasgow9a13 = triageGlasgow9a13;
    }

    public Boolean getTriageAlteracionEstadoConciencia() {
        return triageAlteracionEstadoConciencia;
    }

    public void setTriageAlteracionEstadoConciencia(Boolean triageAlteracionEstadoConciencia) {
        this.triageAlteracionEstadoConciencia = triageAlteracionEstadoConciencia;
    }

    public Boolean getTriageFracturasMayores() {
        return triageFracturasMayores;
    }

    public void setTriageFracturasMayores(Boolean triageFracturasMayores) {
        this.triageFracturasMayores = triageFracturasMayores;
    }

    public Boolean getTriageQuemadurasModeradas() {
        return triageQuemadurasModeradas;
    }

    public void setTriageQuemadurasModeradas(Boolean triageQuemadurasModeradas) {
        this.triageQuemadurasModeradas = triageQuemadurasModeradas;
    }

    public Boolean getTriageIntoxicacion() {
        return triageIntoxicacion;
    }

    public void setTriageIntoxicacion(Boolean triageIntoxicacion) {
        this.triageIntoxicacion = triageIntoxicacion;
    }

    public Boolean getTriageLesionMedularLumbar() {
        return triageLesionMedularLumbar;
    }

    public void setTriageLesionMedularLumbar(Boolean triageLesionMedularLumbar) {
        this.triageLesionMedularLumbar = triageLesionMedularLumbar;
    }

    public Boolean getTriageFracturasNoProximales() {
        return triageFracturasNoProximales;
    }

    public void setTriageFracturasNoProximales(Boolean triageFracturasNoProximales) {
        this.triageFracturasNoProximales = triageFracturasNoProximales;
    }

    public Boolean getTriageLesionesSuperficiales() {
        return triageLesionesSuperficiales;
    }

    public void setTriageLesionesSuperficiales(Boolean triageLesionesSuperficiales) {
        this.triageLesionesSuperficiales = triageLesionesSuperficiales;
    }

    public Boolean getTriageQuemadurasPrimerGrado() {
        return triageQuemadurasPrimerGrado;
    }

    public void setTriageQuemadurasPrimerGrado(Boolean triageQuemadurasPrimerGrado) {
        this.triageQuemadurasPrimerGrado = triageQuemadurasPrimerGrado;
    }

    public Boolean getTriageAfectados() {
        return triageAfectados;
    }

    public void setTriageAfectados(Boolean triageAfectados) {
        this.triageAfectados = triageAfectados;
    }

    public Boolean getTriageParoProlongado() {
        return triageParoProlongado;
    }

    public void setTriageParoProlongado(Boolean triageParoProlongado) {
        this.triageParoProlongado = triageParoProlongado;
    }

    public Boolean getTriageLesionCervicalCompleta() {
        return triageLesionCervicalCompleta;
    }

    public void setTriageLesionCervicalCompleta(Boolean triageLesionCervicalCompleta) {
        this.triageLesionCervicalCompleta = triageLesionCervicalCompleta;
    }

    public Boolean getTriageGlasgow3() {
        return triageGlasgow3;
    }

    public void setTriageGlasgow3(Boolean triageGlasgow3) {
        this.triageGlasgow3 = triageGlasgow3;
    }

    public Boolean getTriageExposicionMasaEncefalica() {
        return triageExposicionMasaEncefalica;
    }

    public void setTriageExposicionMasaEncefalica(Boolean triageExposicionMasaEncefalica) {
        this.triageExposicionMasaEncefalica = triageExposicionMasaEncefalica;
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

    public Boolean getTriageGlasgow14o15() {
        return triageGlasgow14o15;
    }

    public void setTriageGlasgow14o15(Boolean triageGlasgow14o15) {
        this.triageGlasgow14o15 = triageGlasgow14o15;
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(permeavilidadViaAerea);
        dest.writeValue(causaObstruccionVia);
        dest.writeValue(respiracion);
        dest.writeValue(pulso);
        dest.writeValue(ritmoPulso );
        dest.writeValue(fuerzaPulso );
        dest.writeValue(nivelRespuesta);
        dest.writeValue(pielNormal);
        dest.writeValue(pielPalida);
        dest.writeValue(pielCaliente);
        dest.writeValue(pielCianotica );
        dest.writeValue(pielHumeda);
        dest.writeValue( pielSeca );
        dest.writeValue(pupilasNormales);
        dest.writeValue(pupilasMioticas);
        dest.writeValue(pupilasMidriaticas);
        dest.writeValue(pupilasAnisocoricas);
        dest.writeValue(pupilasIsocoricas);
        dest.writeValue(pupilasReactivas);
        dest.writeValue(pupilasNoReactivas);
        dest.writeValue(pupilasNoEvaluables);
        dest.writeValue(glasgowRO);
        dest.writeValue(glasgowRV);
        dest.writeValue(glasgowRM);
        dest.writeValue(tipoEmergencia);
        dest.writeValue(politrauma);
        dest.writeValue(tec);
        dest.writeValue(maxilofacial);
        dest.writeValue(raquimedular);
        dest.writeValue(torax);
        dest.writeValue(abdomen);
        dest.writeValue(pelvico);
        dest.writeValue(extremidadSuperior);
        dest.writeValue(extremidadInferior);
        dest.writeValue(tejidosBlandos);
        dest.writeValue(osteomuscular);
        dest.writeValue(organosSentidos);
        dest.writeValue(otroTrauma);
        dest.writeValue(cualOtroTrauma);
        dest.writeValue(prioridadTriage);
        dest.writeValue(evaluacion);
        dest.writeValue( triageViaAreaObstruida);
        dest.writeValue( triageInsuficienciaRespiratoria);
        dest.writeValue( triagePa90);
        dest.writeValue( triageMultiplesHeridas);
        dest.writeValue( triageHemorragiasNoControladas);
        dest.writeValue( triageLesionCervicalIncompleta);
        dest.writeValue( triageGlasgow4a8);
        dest.writeValue( triageExcitacionPsicomotora);
        dest.writeValue( triageAbdomenAgudo);
        dest.writeValue( triageEvisceracion);
        dest.writeValue( triageTrabajoPartoSangrado);
        dest.writeValue( triageDolorToracico);
        dest.writeValue( triageArritmias);
        dest.writeValue( triageHemorragiasControladas);
        dest.writeValue( triageLesionMedularDorsal);
        dest.writeValue( triageGlasgow9a13);
        dest.writeValue( triageAlteracionEstadoConciencia);
        dest.writeValue( triageFracturasMayores);
        dest.writeValue( triageQuemadurasModeradas);
        dest.writeValue( triageIntoxicacion);
        dest.writeValue( triageLesionMedularLumbar);
        dest.writeValue( triageFracturasNoProximales);
        dest.writeValue( triageLesionesSuperficiales);
        dest.writeValue( triageQuemadurasPrimerGrado);
        dest.writeValue( triageAfectados);
        dest.writeValue( triageParoProlongado);
        dest.writeValue( triageLesionCervicalCompleta);
        dest.writeValue( triageGlasgow3);
        dest.writeValue( triageExposicionMasaEncefalica);
        dest.writeValue( triageLesionesImpidenRcp);
        dest.writeValue( triageQuemadurasGraves);
        dest.writeValue (triageOtro);
        dest.writeValue(triageGlasgow14o15);
        dest.writeValue(isSynchronized);
        dest.writeValue(evaluacionId);
        dest.writeValue(procedimientosId);
        dest.writeValue(procedimientos);
        dest.writeTypedList(hallazgos);
        dest.writeValue(otraEmergenciaMedica);
        dest.writeValue(estadoRespiracion);
        dest.writeValue(cianosis);

        dest.writeValue(llenadoCapilar);
        dest.writeValue(ubicacionPulso);
        dest.writeValue(sangrado);

        dest.writeValue(pielIcterica);
        dest.writeValue(pielFria);

        dest.writeValue(triageCadenaCustodia);
        dest.writeValue(triageEntregadoEntidad);
        dest.writeValue(triageEntidadEntregaPacienteDifunto);
        dest.writeValue(triageNombrePersonaRecibeDifunto);
        dest.writeValue(triageRegistroPersonaRecibeDifunto);
    }
    public String[] getParamKeys()
    {
        if(evaluacion != null) {
            return new String[]{
                    String.valueOf(evaluacion.getCreator()), String.valueOf(evaluacion.getObsId())
            };
        }
        return new String[]{};
    }
    public void setupId(EvaluacionDto evaluacion)
    {
        id=evaluacion.getId();
        if(this.evaluacion != null)
        {
            this.evaluacion.setupId(evaluacion.getEvaluacion());
            evaluacionId = evaluacion.getId();
        }
        if(procedimientos != null && evaluacion.getProcedimientos() != null)
        {
            procedimientos.setupId(evaluacion.getProcedimientos());
            procedimientosId = procedimientos.getId();
        }
        if(hallazgos != null && evaluacion.getHallazgos() != null) {
            SparseArray<HallazgoDto> map = new SparseArray<HallazgoDto>();
            for (HallazgoDto hallazgo : evaluacion.getHallazgos()) {
                if (hallazgo.getOrden() != null) {
                    map.append(hallazgo.getOrden(), hallazgo);
                }
            }
            for(HallazgoDto hallazgoDto : hallazgos)
            {
                HallazgoDto hallazgo = map.get(hallazgoDto.getOrden());
                hallazgoDto.setupId(hallazgo);
            }

        }
    }
}
