package com.artica.telesalud.tph.android.model;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;




public class TripulacionSpringDto {
	private Integer tripulacionId;
	private EventoSpringDto evento;
	private String placa;
	private Date horaDespacho;
	private Date horaLlegada;
	private Date horaRegreso;

    private Integer creator;
    private Date dateCreated;

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
    }
    /**
	 * @return the tripulacionId
	 */
	public Integer getTripulacionId() {
		return tripulacionId;
	}
	/**
	 * @param tripulacionId the tripulacionId to set
	 */
	public void setTripulacionId(Integer tripulacionId) {
		this.tripulacionId = tripulacionId;
	}
	/**
	 * @return the evento
	 */
	public EventoSpringDto getEvento() {
		return evento;
	}
	/**
	 * @param evento the evento to set
	 */
	public void setEvento(EventoSpringDto evento) {
		this.evento = evento;
	}
	/**
	 * @return the placa
	 */
	public String getPlaca() {
		return placa;
	}
	/**
	 * @param placa the placa to set
	 */
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	/**
	 * @return the horaDespacho
	 */
	public Date getHoraDespacho() {
		return horaDespacho;
	}
	/**
	 * @param horaDespacho the horaDespacho to set
	 */
	public void setHoraDespacho(Date horaDespacho) {
		this.horaDespacho = horaDespacho;
	}
	/**
	 * @return the horaLlegada
	 */
	public Date getHoraLlegada() {
		return horaLlegada;
	}
	/**
	 * @param horaLlegada the horaLlegada to set
	 */
	public void setHoraLlegada(Date horaLlegada) {
		this.horaLlegada = horaLlegada;
	}
	/**
	 * @return the horaRegreso
	 */
	public Date getHoraRegreso() {
		return horaRegreso;
	}
	/**
	 * @param horaRegreso the horaRegreso to set
	 */
	public void setHoraRegreso(Date horaRegreso) {
		this.horaRegreso = horaRegreso;
	}
	
}
