package com.artica.telesalud.tph.android.model;

import com.artica.telesalud.tph.android.lightweightmodel.ConceptDto;

import java.io.Serializable;


public class ConceptSpringDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer conceptId;
    private String shortName;
    private String description;

    public ConceptSpringDto(ConceptDto concept)
    {
        conceptId = concept.getConceptId();
        shortName = concept.getName();

    }

    public ConceptSpringDto(){
        super();
    }

	/**
	 * @return the conceptId
	 */
	public Integer getConceptId() {
		return conceptId;
	}
	/**
	 * @param conceptId the conceptId to set
	 */
	public void setConceptId(Integer conceptId) {
		this.conceptId = conceptId;
	}
	/**
	 * @return the shortName
	 */
	public String getShortName() {
		return shortName;
	}
	/**
	 * @param shortName the shortName to set
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
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
