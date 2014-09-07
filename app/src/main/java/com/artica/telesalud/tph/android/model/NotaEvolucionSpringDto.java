package com.artica.telesalud.tph.android.model;

import java.util.Date;
import java.util.List;


public class NotaEvolucionSpringDto {
	private Date fecha;
	private ConceptSpringDto dxPrincipal;
	private List<ConceptSpringDto> dxSecundarios;
	private String recomendaciones;
	private UserSpringDto medicoTratante;
	
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
	 * @return the dxPrincipal
	 */
	public ConceptSpringDto getDxPrincipal() {
		return dxPrincipal;
	}
	/**
	 * @param dxPrincipal the dxPrincipal to set
	 */
	public void setDxPrincipal(ConceptSpringDto dxPrincipal) {
		this.dxPrincipal = dxPrincipal;
	}
	/**
	 * @return the dxSecundarios
	 */
	public List<ConceptSpringDto> getDxSecundarios() {
		return dxSecundarios;
	}
	/**
	 * @param dxSecundarios the dxSecundarios to set
	 */
	public void setDxSecundarios(List<ConceptSpringDto> dxSecundarios) {
		this.dxSecundarios = dxSecundarios;
	}
	/**
	 * @return the recomendaciones
	 */
	public String getRecomendaciones() {
		return recomendaciones;
	}
	/**
	 * @param recomendaciones the recomendaciones to set
	 */
	public void setRecomendaciones(String recomendaciones) {
		this.recomendaciones = recomendaciones;
	}
	/**
	 * @return the medicoTratante
	 */
	public UserSpringDto getMedicoTratante() {
		return medicoTratante;
	}
	/**
	 * @param medicoTratante the medicoTratante to set
	 */
	public void setMedicoTratante(UserSpringDto medicoTratante) {
		this.medicoTratante = medicoTratante;
	}

	
}
