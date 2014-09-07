package com.artica.telesalud.tph.android.model;


import com.artica.telesalud.tph.android.lightweightmodel.PatientIdentifierTypeDto;

import java.util.Date;

public class PatientIdentifierTypeSpringDto {
	private Integer patientIdentifierTypeId;
	private String name;
	private String description;
    public PatientIdentifierTypeSpringDto()
    {

    }
    public PatientIdentifierTypeSpringDto(PatientIdentifierTypeDto patientIdentifierTypeDto){
        patientIdentifierTypeId=patientIdentifierTypeDto.getPatientIdentifierTypeId();
        name=patientIdentifierTypeDto.getName();
       description=patientIdentifierTypeDto.getDescription();
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
    }/**
	 * @return the patientIdentifierTypeId
	 */
	public Integer getPatientIdentifierTypeId() {
		return patientIdentifierTypeId;
	}
	/**
	 * @param patientIdentifierTypeId the patientIdentifierTypeId to set
	 */
	public void setPatientIdentifierTypeId(Integer patientIdentifierTypeId) {
		this.patientIdentifierTypeId = patientIdentifierTypeId;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
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
	
}
