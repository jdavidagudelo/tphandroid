package com.artica.telesalud.tph.android.model;

import java.util.Date;

public class ResponsableAtencionSpringDto {
	private Integer responsableAtencionId;
	private LesionadoSpringDto lesionado;
	private Boolean externo;
	private PersonSpringDto person;	
	private Integer creator;
	private Date dateCreated;
	
	/**
	 * @return the creator
	 */
	public Integer getCreator() {
		return creator;
	}

	/**
	 * @param creator the creator to set
	 */
	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	/**
	 * @return the dateCreated
	 */
	public Date getDateCreated() {
		return dateCreated;
	}

	/**
	 * @param dateCreated the dateCreated to set
	 */
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	
	public ResponsableAtencionSpringDto() {
		super();
	}


	
	/**
	 * @return the responsableAtencionId
	 */
	public Integer getResponsableAtencionId() {
		return responsableAtencionId;
	}
	/**
	 * @param responsableAtencionId the responsableAtencionId to set
	 */
	public void setResponsableAtencionId(Integer responsableAtencionId) {
		this.responsableAtencionId = responsableAtencionId;
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
	 * @return the externo
	 */
	public Boolean getExterno() {
		return externo;
	}
	/**
	 * @param externo the externo to set
	 */
	public void setExterno(Boolean externo) {
		this.externo = externo;
	}
	/**
	 * @return the person
	 */
	public PersonSpringDto getPerson() {
		return person;
	}
	/**
	 * @param person the person to set
	 */
	public void setPerson(PersonSpringDto person) {
		this.person = person;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((externo == null) ? 0 : externo.hashCode());
		result = prime * result
				+ ((lesionado == null) ? 0 : lesionado.hashCode());
		result = prime * result + ((person == null) ? 0 : person.hashCode());
		result = prime
				* result
				+ ((responsableAtencionId == null) ? 0 : responsableAtencionId
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
		if (!(obj instanceof ResponsableAtencionSpringDto)) {
			return false;
		}
		ResponsableAtencionSpringDto other = (ResponsableAtencionSpringDto) obj;
		if (externo == null) {
			if (other.externo != null) {
				return false;
			}
		} else if (!externo.equals(other.externo)) {
			return false;
		}
		if (lesionado == null) {
			if (other.lesionado != null) {
				return false;
			}
		} else if (!lesionado.equals(other.lesionado)) {
			return false;
		}
		if (person == null) {
			if (other.person != null) {
				return false;
			}
		} else if (!person.equals(other.person)) {
			return false;
		}
		if (responsableAtencionId == null) {
			if (other.responsableAtencionId != null) {
				return false;
			}
		} else if (!responsableAtencionId.equals(other.responsableAtencionId)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ResponsableAtencionSpringDto [responsableAtencionId="
				+ responsableAtencionId + ", lesionado=" + lesionado
				+ ", externo=" + externo + ", person=" + person + "]";
	}
	
	
	
}
