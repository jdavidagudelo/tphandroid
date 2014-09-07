package com.artica.telesalud.tph.android.model;

public class TipoAntecedenteSpringDto {
	private String tipoAntecedente;

	/**
	 * @return the tipoAntecedente
	 */
	public String getTipoAntecedente() {
		return tipoAntecedente;
	}

	/**
	 * @param tipoAntecedente the tipoAntecedente to set
	 */
	public void setTipoAntecedente(String tipoAntecedente) {
		this.tipoAntecedente = tipoAntecedente;
	}
	public TipoAntecedenteSpringDto(String tipoAntecedente)
	{
		this.tipoAntecedente = tipoAntecedente;
	}
}
