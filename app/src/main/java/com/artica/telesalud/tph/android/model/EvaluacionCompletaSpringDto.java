package com.artica.telesalud.tph.android.model;

import com.artica.telesalud.tph.android.lightweightmodel.EncounterDto;
import com.artica.telesalud.tph.android.lightweightmodel.EvaluacionCompletaDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;


public class EvaluacionCompletaSpringDto {
    @JsonIgnore
    private Integer localId;
	private Integer obsId;
	private Integer conceptId;
	private Date dateCreated;
	private Integer creator;
    private EncounterSpringDto encounter;
	public EvaluacionCompletaSpringDto()
	{
		
	}

    public EncounterSpringDto getEncounter() {
        return encounter;
    }

    public void setEncounter(EncounterSpringDto encounter) {
        this.encounter = encounter;
    }

    public EvaluacionCompletaSpringDto(EvaluacionCompletaDto evaluacion)
    {
        obsId = evaluacion.getObsId();
        conceptId = evaluacion.getConceptId();
        dateCreated = evaluacion.getDateCreated();
        creator = evaluacion.getCreator();
        if(evaluacion.getEncounter() != null)
        {
            encounter = new EncounterSpringDto(evaluacion.getEncounter());
        }

    }

    public Integer getLocalId() {
        return localId;
    }

    public void setLocalId(Integer localId) {
        this.localId = localId;
    }

    /**
	 * @return the obsId
	 */
	public Integer getObsId() {
		return obsId;
	}
	/**
	 * @param obsId the obsId to set
	 */
	public void setObsId(Integer obsId) {
		this.obsId = obsId;
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
	
}
