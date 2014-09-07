package com.artica.telesalud.tph.android.model;


import com.artica.telesalud.tph.android.lightweightmodel.EncounterTypeDto;

import java.util.Date;

public class EncounterTypeSpringDto {
	private Integer encounterTypeId;
	private String name;
	private String description;

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
    public EncounterTypeSpringDto(){

    }

    public EncounterTypeSpringDto(EncounterTypeDto encounterTypeDto){
        encounterTypeId = encounterTypeDto.getEncounterTypeId();
        name = encounterTypeDto.getName();
        description = encounterTypeDto.getDescription();
    }
	/**
	 * @return the encounterTypeId
	 */
	public Integer getEncounterTypeId() {
		return encounterTypeId;
	}
	/**
	 * @param encounterTypeId the encounterTypeId to set
	 */
	public void setEncounterTypeId(Integer encounterTypeId) {
		this.encounterTypeId = encounterTypeId;
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
