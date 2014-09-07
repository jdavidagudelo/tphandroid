package com.artica.telesalud.tph.android.lightweightmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.artica.telesalud.tph.android.model.HallazgoSpringDto;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by interoperabilidad on 29/07/14.
 */
@DatabaseTable()
public class HallazgoDto implements Serializable, Parcelable {
    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField()
    private Integer orden;
    @DatabaseField()
    private Double x;
    @DatabaseField()
    private Double y;
    @DatabaseField()
    private Integer hallazgoId;
    @DatabaseField()
    private Boolean abrasion = Boolean.FALSE;
    @DatabaseField()
    private Boolean amputacion=Boolean.FALSE;
    @DatabaseField()
    private Boolean aplastamiento=Boolean.FALSE;
    @DatabaseField()
    private Boolean avulsion=Boolean.FALSE;
    @DatabaseField()
    private Boolean contusion=Boolean.FALSE;
    @DatabaseField()
    private Boolean dolor=Boolean.FALSE;
    @DatabaseField()
    private Boolean esguince=Boolean.FALSE;
    @DatabaseField()
    private Boolean fracturaAbierta=Boolean.FALSE;
    @DatabaseField()
    private Boolean quemadura=Boolean.FALSE;
    @DatabaseField()
    private Boolean herida=Boolean.FALSE;
    @DatabaseField()
    private Boolean fracturaCerrada=Boolean.FALSE;
    @DatabaseField()
    private Boolean heridaArmaDeFuego=Boolean.FALSE;
    @DatabaseField()
    private Boolean heridaArmaBlanca=Boolean.FALSE;
    @DatabaseField()
    private Boolean hemorragia=Boolean.FALSE;
    @DatabaseField()
    private Boolean laceracion=Boolean.FALSE;
    @DatabaseField()
    private Boolean mordida=Boolean.FALSE;
    @DatabaseField()
    private Boolean picadura=Boolean.FALSE;
    @DatabaseField()
    private Boolean puncion=Boolean.FALSE;
    @DatabaseField()
    private Boolean traumaCerrado=Boolean.FALSE;
    @DatabaseField()
    private Boolean traumaPenetrante=Boolean.FALSE;
    @DatabaseField()
    private Boolean hematoma=Boolean.FALSE;
    @DatabaseField()
    private Integer evaluacionId;
    private EvaluacionCompletaDto evaluacion;

    @Override
    public int describeContents() {
        return 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(orden);
        dest.writeValue(x);
        dest.writeValue(y);
        dest.writeValue(hallazgoId);
        dest.writeValue(evaluacion);
        dest.writeValue(evaluacionId);
        dest.writeValue(abrasion);
        dest.writeValue(amputacion);
        dest.writeValue(aplastamiento);
        dest.writeValue(avulsion);
        dest.writeValue(contusion);
        dest.writeValue(dolor);
        dest.writeValue(esguince);
        dest.writeValue(fracturaAbierta);
        dest.writeValue(quemadura);
        dest.writeValue(herida);
        dest.writeValue(fracturaCerrada);
        dest.writeValue(heridaArmaDeFuego);
        dest.writeValue(heridaArmaBlanca);
        dest.writeValue(hemorragia);
        dest.writeValue(laceracion);
        dest.writeValue(mordida);
        dest.writeValue(picadura);
        dest.writeValue(puncion);
        dest.writeValue(traumaCerrado);
        dest.writeValue(traumaPenetrante);
        dest.writeValue(hematoma);
    }

    public HallazgoDto()
    {
    }
    public static final Parcelable.Creator<HallazgoDto> CREATOR = new Parcelable.Creator<HallazgoDto>() {
        public HallazgoDto createFromParcel(Parcel in) {
            return new HallazgoDto(in);
        }

        public HallazgoDto[] newArray(int size) {
            return new HallazgoDto[size];
        }
    };
    public HallazgoDto(Parcel parcel) {
        id = (Integer)parcel.readValue(null);
        orden = (Integer)parcel.readValue(Integer.class.getClassLoader());
        x = (Double)parcel.readValue(Double.class.getClassLoader());
        y = (Double)parcel.readValue(Double.class.getClassLoader());
        hallazgoId = (Integer)parcel.readValue(Integer.class.getClassLoader());
        evaluacion = (EvaluacionCompletaDto)parcel.readValue(EvaluacionCompletaDto.class.getClassLoader());
        evaluacionId = (Integer)parcel.readValue(null);
        abrasion=(Boolean)parcel.readValue(null);
        amputacion=(Boolean)parcel.readValue(null);
        aplastamiento=(Boolean)parcel.readValue(null);
        avulsion=(Boolean)parcel.readValue(null);
        contusion=(Boolean)parcel.readValue(null);
        dolor=(Boolean)parcel.readValue(null);
        esguince=(Boolean)parcel.readValue(null);
        fracturaAbierta=(Boolean)parcel.readValue(null);
        quemadura=(Boolean)parcel.readValue(null);
        herida=(Boolean)parcel.readValue(null);
        fracturaCerrada=(Boolean)parcel.readValue(null);
        heridaArmaDeFuego=(Boolean)parcel.readValue(null);
        heridaArmaBlanca=(Boolean)parcel.readValue(null);
        hemorragia=(Boolean)parcel.readValue(null);
        laceracion=(Boolean)parcel.readValue(null);
        mordida=(Boolean)parcel.readValue(null);
        picadura=(Boolean)parcel.readValue(null);
        puncion=(Boolean)parcel.readValue(null);
        traumaCerrado=(Boolean)parcel.readValue(null);
        traumaPenetrante=(Boolean)parcel.readValue(null);
        hematoma=(Boolean)parcel.readValue(null);

    }
    public HallazgoDto(HallazgoSpringDto hallazgo)
    {

        orden = hallazgo.getOrden();
        x = hallazgo.getX();
        y = hallazgo.getY();
        if(hallazgo.getEvaluacion() != null)
        {
            evaluacion = new EvaluacionCompletaDto(hallazgo.getEvaluacion());
        }
        hallazgoId = hallazgo.getHallazgoId();
        abrasion = hallazgo.getAbrasion();
        amputacion=hallazgo.getAmputacion();
        aplastamiento=hallazgo.getAplastamiento();
        avulsion=hallazgo.getAvulsion();
        contusion=hallazgo.getContusion();
        dolor=hallazgo.getDolor();
        esguince=hallazgo.getEsguince();
        fracturaAbierta=hallazgo.getFracturaAbierta();
        quemadura=hallazgo.getQuemadura();
        herida=hallazgo.getHerida();
        fracturaCerrada=hallazgo.getFracturaCerrada();
        heridaArmaDeFuego=hallazgo.getHeridaArmaDeFuego();
        heridaArmaBlanca=hallazgo.getHeridaArmaBlanca();
        hemorragia=hallazgo.getHemorragia();
        laceracion=hallazgo.getLaceracion();
        mordida=hallazgo.getMordida();
        picadura=hallazgo.getPicadura();
        puncion=hallazgo.getPuncion();
        traumaCerrado=hallazgo.getTraumaCerrado();
        traumaPenetrante=hallazgo.getTraumaPenetrante();
        hematoma=hallazgo.getHematoma();

    }
    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Integer getEvaluacionId() {
        return evaluacionId;
    }

    public void setEvaluacionId(Integer evaluacionId) {
        this.evaluacionId = evaluacionId;
    }

    public Integer getHallazgoId() {
        return hallazgoId;
    }

    public void setHallazgoId(Integer hallazgoId) {
        this.hallazgoId = hallazgoId;
    }


    public EvaluacionCompletaDto getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(EvaluacionCompletaDto evaluacion) {
        this.evaluacion = evaluacion;
    }

    public Boolean getAbrasion() {
        return abrasion;
    }

    public void setAbrasion(Boolean abrasion) {
        this.abrasion = abrasion;
    }

    public Boolean getAmputacion() {
        return amputacion;
    }

    public void setAmputacion(Boolean amputacion) {
        this.amputacion = amputacion;
    }

    public Boolean getAplastamiento() {
        return aplastamiento;
    }

    public void setAplastamiento(Boolean aplastamiento) {
        this.aplastamiento = aplastamiento;
    }

    public Boolean getAvulsion() {
        return avulsion;
    }

    public void setAvulsion(Boolean avulsion) {
        this.avulsion = avulsion;
    }

    public Boolean getContusion() {
        return contusion;
    }

    public void setContusion(Boolean contusion) {
        this.contusion = contusion;
    }

    public Boolean getDolor() {
        return dolor;
    }

    public void setDolor(Boolean dolor) {
        this.dolor = dolor;
    }

    public Boolean getEsguince() {
        return esguince;
    }

    public void setEsguince(Boolean esguince) {
        this.esguince = esguince;
    }

    public Boolean getFracturaAbierta() {
        return fracturaAbierta;
    }

    public void setFracturaAbierta(Boolean fracturaAbierta) {
        this.fracturaAbierta = fracturaAbierta;
    }

    public Boolean getQuemadura() {
        return quemadura;
    }

    public void setQuemadura(Boolean quemadura) {
        this.quemadura = quemadura;
    }

    public Boolean getHerida() {
        return herida;
    }

    public void setHerida(Boolean herida) {
        this.herida = herida;
    }

    public Boolean getFracturaCerrada() {
        return fracturaCerrada;
    }

    public void setFracturaCerrada(Boolean fracturaCerrada) {
        this.fracturaCerrada = fracturaCerrada;
    }

    public Boolean getHeridaArmaDeFuego() {
        return heridaArmaDeFuego;
    }

    public void setHeridaArmaDeFuego(Boolean heridaArmaDeFuego) {
        this.heridaArmaDeFuego = heridaArmaDeFuego;
    }

    public Boolean getHeridaArmaBlanca() {
        return heridaArmaBlanca;
    }

    public void setHeridaArmaBlanca(Boolean heridaArmaBlanca) {
        this.heridaArmaBlanca = heridaArmaBlanca;
    }

    public Boolean getHemorragia() {
        return hemorragia;
    }

    public void setHemorragia(Boolean hemorragia) {
        this.hemorragia = hemorragia;
    }

    public Boolean getLaceracion() {
        return laceracion;
    }

    public void setLaceracion(Boolean laceracion) {
        this.laceracion = laceracion;
    }

    public Boolean getMordida() {
        return mordida;
    }

    public void setMordida(Boolean mordida) {
        this.mordida = mordida;
    }

    public Boolean getPicadura() {
        return picadura;
    }

    public void setPicadura(Boolean picadura) {
        this.picadura = picadura;
    }

    public Boolean getPuncion() {
        return puncion;
    }

    public void setPuncion(Boolean puncion) {
        this.puncion = puncion;
    }

    public Boolean getTraumaCerrado() {
        return traumaCerrado;
    }

    public void setTraumaCerrado(Boolean traumaCerrado) {
        this.traumaCerrado = traumaCerrado;
    }

    public Boolean getTraumaPenetrante() {
        return traumaPenetrante;
    }

    public void setTraumaPenetrante(Boolean traumaPenetrante) {
        this.traumaPenetrante = traumaPenetrante;
    }

    public Boolean getHematoma() {
        return hematoma;
    }

    public void setHematoma(Boolean hematoma) {
        this.hematoma = hematoma;
    }

    public void setupId(HallazgoDto hallazgo)
    {
        if(hallazgo != null) {
            id = hallazgo.getId();
            if (evaluacion != null && hallazgo.getEvaluacion() != null) {
                evaluacion.setupId(hallazgo.getEvaluacion());
                evaluacionId = evaluacion.getId();
            }
        }
    }
}
