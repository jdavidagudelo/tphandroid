package com.artica.telesalud.tph.android.model;

import java.util.ArrayList;
import java.util.List;


public class AntecedentesAmpSpringDto {

	private String historiaClinicaUuid;
	private String historiaClinicaNombre;
	private List<AntecedenteSpringDto> antecedentes = new ArrayList<AntecedenteSpringDto>();
	private LesionadoSpringDto lesionado;
	public AntecedentesAmpSpringDto()
	{
	}

	
	/**
	 * @return the lesionado
	 */
	public LesionadoSpringDto getLesionado() {
		return lesionado;
	}
	/**
	 * @param lesionado the lesionado to set
	 */
	public void setLesionado(LesionadoSpringDto lesionado) {
		this.lesionado = lesionado;
	}
	/**
	 * @return the historiaClinicaUuid
	 */
	public String getHistoriaClinicaUuid() {
		return historiaClinicaUuid;
	}
	/**
	 * @param historiaClinicaUuid the historiaClinicaUuid to set
	 */
	public void setHistoriaClinicaUuid(String historiaClinicaUuid) {
		this.historiaClinicaUuid = historiaClinicaUuid;
	}
	/**
	 * @return the historiaClinicaNombre
	 */
	public String getHistoriaClinicaNombre() {
		return historiaClinicaNombre;
	}
	/**
	 * @param historiaClinicaNombre the historiaClinicaNombre to set
	 */
	public void setHistoriaClinicaNombre(String historiaClinicaNombre) {
		this.historiaClinicaNombre = historiaClinicaNombre;
	}

	/**
	 * @return the antecedentes
	 */
	public List<AntecedenteSpringDto> getAntecedentes() {
		return antecedentes;
	}
	/**
	 * @param antecedentes the antecedentes to set
	 */
	public void setAntecedentes(List<AntecedenteSpringDto> antecedentes) {
		this.antecedentes = antecedentes;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((antecedentes == null) ? 0 : antecedentes.hashCode());
		result = prime
				* result
				+ ((historiaClinicaNombre == null) ? 0 : historiaClinicaNombre
						.hashCode());
		result = prime
				* result
				+ ((historiaClinicaUuid == null) ? 0 : historiaClinicaUuid
						.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof AntecedentesAmpSpringDto)) {
			return false;
		}
		AntecedentesAmpSpringDto other = (AntecedentesAmpSpringDto) obj;
		if (antecedentes == null) {
			if (other.antecedentes != null) {
				return false;
			}
		} else if (!antecedentes.equals(other.antecedentes)) {
			return false;
		}
		if (historiaClinicaNombre == null) {
			if (other.historiaClinicaNombre != null) {
				return false;
			}
		} else if (!historiaClinicaNombre.equals(other.historiaClinicaNombre)) {
			return false;
		}
		if (historiaClinicaUuid == null) {
			if (other.historiaClinicaUuid != null) {
				return false;
			}
		} else if (!historiaClinicaUuid.equals(other.historiaClinicaUuid)) {
			return false;
		}
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AntecedentesAmpSpringDto [historiaClinicaUuid="
				+ historiaClinicaUuid + ", historiaClinicaNombre="
				+ historiaClinicaNombre + ", antecedentes=" + antecedentes
				+ "]";
	}
	
}
