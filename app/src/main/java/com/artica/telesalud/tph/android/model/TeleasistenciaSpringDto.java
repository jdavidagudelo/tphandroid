package com.artica.telesalud.tph.android.model;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Seconds;


public class TeleasistenciaSpringDto {

	private Integer teleasistenciaId;
	private Date fecha;
	private String motivoSolicitud;
	private String detalles;
	private ConceptSpringDto medio;
	private List<NotaEvolucionSpringDto> notasEvolucion;
	private UserSpringDto medicoSolicitante;
	
	public TeleasistenciaSpringDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the teleasistenciaId
	 */
	public Integer getTeleasistenciaId() {
		return teleasistenciaId;
	}
	/**
	 * @param teleasistenciaId the teleasistenciaId to set
	 */
	public void setTeleasistenciaId(Integer teleasistenciaId) {
		this.teleasistenciaId = teleasistenciaId;
	}
	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return the motivoSolicitud
	 */
	public String getMotivoSolicitud() {
		return motivoSolicitud;
	}
	/**
	 * @param motivoSolicitud the motivoSolicitud to set
	 */
	public void setMotivoSolicitud(String motivoSolicitud) {
		this.motivoSolicitud = motivoSolicitud;
	}
	/**
	 * @return the detalles
	 */
	public String getDetalles() {
		return detalles;
	}
	/**
	 * @param detalles the detalles to set
	 */
	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}
	/**
	 * @return the medio
	 */
	public ConceptSpringDto getMedio() {
		return medio;
	}
	/**
	 * @param medio the medio to set
	 */
	public void setMedio(ConceptSpringDto medio) {
		this.medio = medio;
	}
	/**
	 * @return the notasEvolucion
	 */
	public List<NotaEvolucionSpringDto> getNotasEvolucion() {
		return notasEvolucion;
	}
	/**
	 * @param notasEvolucion the notasEvolucion to set
	 */
	public void setNotasEvolucion(List<NotaEvolucionSpringDto> notasEvolucion) {
		this.notasEvolucion = notasEvolucion;
	}
	/**
	 * @return the medicoSolicitante
	 */
	public UserSpringDto getMedicoSolicitante() {
		return medicoSolicitante;
	}
	/**
	 * @param medicoSolicitante the medicoSolicitante to set
	 */
	public void setMedicoSolicitante(UserSpringDto medicoSolicitante) {
		this.medicoSolicitante = medicoSolicitante;
	}
	

	
	
}
