package com.artica.telesalud.tph.android.model;


import com.artica.telesalud.tph.android.lightweightmodel.PatientIdentifierDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class PatientIdentifierSpringDto {
    @JsonIgnore
    private Integer localId;
	private Integer patientIdentifierId;
	private Integer patientId;
	private String identifier;
	private PatientIdentifierTypeSpringDto patientIdentifierType;
	private Boolean preferred;
	private Integer country;
    public PatientIdentifierSpringDto(){

    }
    public PatientIdentifierSpringDto(PatientIdentifierDto patientIdentifier){
        patientIdentifierId = patientIdentifier.getPatientIdentifierId();
         patientId=patientIdentifier.getPatientId();
       identifier=patientIdentifier.getIdentifier();
        if(patientIdentifier.getPatientIdentifierType() != null) {
            patientIdentifierType = new PatientIdentifierTypeSpringDto(patientIdentifier.getPatientIdentifierType());
        }
         preferred=patientIdentifier.getPreferred();
        country=patientIdentifier.getCountry();
    }

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
    } public Integer getLocalId() {
        return localId;
    }

    public void setLocalId(Integer localId) {
        this.localId = localId;
    }

    /**
	 * @return the patientIdentifierId
	 */
	public Integer getPatientIdentifierId() {
		return patientIdentifierId;
	}
	/**
	 * @param patientIdentifierId the patientIdentifierId to set
	 */
	public void setPatientIdentifierId(Integer patientIdentifierId) {
		this.patientIdentifierId = patientIdentifierId;
	}
	/**
	 * @return the patient
	 */
	public Integer getPatientId() {
		return patientId;
	}
	/**
	 * @param patientId the patient to set
	 */
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	/**
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}
	/**
	 * @param identifier the identifier to set
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	/**
	 * @return the patientIdentifierType
	 */
	public PatientIdentifierTypeSpringDto getPatientIdentifierType() {
		return patientIdentifierType;
	}
	/**
	 * @param patientIdentifierType the patientIdentifierType to set
	 */
	public void setPatientIdentifierType(
			PatientIdentifierTypeSpringDto patientIdentifierType) {
		this.patientIdentifierType = patientIdentifierType;
	}
	/**
	 * @return the preferred
	 */
	public Boolean getPreferred() {
		return preferred;
	}
	/**
	 * @param preferred the preferred to set
	 */
	public void setPreferred(Boolean preferred) {
		this.preferred = preferred;
	}
	/**
	 * @return the country
	 */
	public Integer getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(Integer country) {
		this.country = country;
	}
	
}
