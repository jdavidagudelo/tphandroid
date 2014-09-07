package com.artica.telesalud.tph.android.model;

public class AntecedenteSpringDto {
	private String description;
	private String date;
	private TipoAntecedenteSpringDto tipoAntecedente;
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the tipoAntecedente
	 */
	public TipoAntecedenteSpringDto getTipoAntecedente() {
		return tipoAntecedente;
	}
	/**
	 * @param tipoAntecedente the tipoAntecedente to set
	 */
	public void setTipoAntecedente(TipoAntecedenteSpringDto tipoAntecedente) {
		this.tipoAntecedente = tipoAntecedente;
	}
	
}
