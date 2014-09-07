package com.artica.telesalud.tph.android.model;

import com.artica.telesalud.tph.android.lightweightmodel.HallazgoDto;

public class HallazgoSpringDto {
	private Integer orden;
	private Double x, y;
	private Integer hallazgoId;
    private Boolean abrasion = Boolean.FALSE;
    private Boolean amputacion=Boolean.FALSE;
    private Boolean aplastamiento=Boolean.FALSE;
    private Boolean avulsion=Boolean.FALSE;
    private Boolean contusion=Boolean.FALSE;
    private Boolean dolor=Boolean.FALSE;
    private Boolean esguince=Boolean.FALSE;
    private Boolean fracturaAbierta=Boolean.FALSE;
    private Boolean quemadura=Boolean.FALSE;
    private Boolean herida=Boolean.FALSE;
    private Boolean fracturaCerrada=Boolean.FALSE;
    private Boolean heridaArmaDeFuego=Boolean.FALSE;
    private Boolean heridaArmaBlanca=Boolean.FALSE;
    private Boolean hemorragia=Boolean.FALSE;
    private Boolean laceracion=Boolean.FALSE;
    private Boolean mordida=Boolean.FALSE;
    private Boolean picadura=Boolean.FALSE;
    private Boolean puncion=Boolean.FALSE;
    private Boolean traumaCerrado=Boolean.FALSE;
    private Boolean traumaPenetrante=Boolean.FALSE;
    private Boolean hematoma=Boolean.FALSE;
	private EvaluacionCompletaSpringDto evaluacion;

    public HallazgoSpringDto(HallazgoDto hallazgo)
    {

        orden = hallazgo.getOrden();
        x = hallazgo.getX();
        y = hallazgo.getY();
        if(hallazgo.getEvaluacion() != null)
        {
           evaluacion = new EvaluacionCompletaSpringDto(hallazgo.getEvaluacion());
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

	public HallazgoSpringDto() {
	}


	public static enum Lesiones {
		abrasion, amputacion, aplastamiento, avulsion, contusion, dolor, esguince, fracturaAbierta, quemadura, herida, fracturaCerrada, heridaArmaDeFuego, heridaArmaBlanca, hemorragia, laceracion, mordida, picadura, puncion, traumaCerrado, traumaPenetrante, hematoma
	}

	/**
	 * @return the orden
	 */
	public Integer getOrden() {
		return orden;
	}

	/**
	 * @param orden
	 *            the orden to set
	 */
	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	/**
	 * @return the x
	 */
	public Double getX() {
		return x;
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(Double x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public Double getY() {
		return y;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(Double y) {
		this.y = y;
	}




	/**
	 * @return the hallazgoId
	 */
	public Integer getHallazgoId() {
		return hallazgoId;
	}

	/**
	 * @param hallazgoId
	 *            the hallazgoId to set
	 */
	public void setHallazgoId(Integer hallazgoId) {
		this.hallazgoId = hallazgoId;
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
}
