package com.artica.telesalud.tph.android.model;

import com.artica.telesalud.tph.android.lightweightmodel.PersonDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;


public class PersonSpringDto {
    @JsonIgnore
    private Integer localId;
	private Integer personId;
	private String gender;
	private Date birthdate;
	private Boolean birthdateEstimated;
	private Integer specialty;
	private String identification;
	private Boolean dead;
	private Date deathDate;
	private ConceptSpringDto causeOfDeath;
	private Integer age;
	private String registro;
	private String firma;
	private ConceptSpringDto erp;
	private ConceptSpringDto nivel;
	private ConceptSpringDto controlAph;
	private PersonNameSpringDto preferredName;
	private PersonAddressSpringDto preferredAddress;

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
    } public PersonSpringDto()
    {

    }
    public PersonSpringDto(PersonDto personDto)
    {
        personId =personDto.getPersonId();
        gender=personDto.getGender();
        birthdate=personDto.getBirthdate();
        birthdateEstimated=personDto.getBirthdateEstimated();
        specialty=personDto.getSpecialty();
        identification=personDto.getIdentification();
        dead=personDto.getDead();
        deathDate=personDto.getDeathDate();
        if(personDto.getCauseOfDeath() != null)
        causeOfDeath=new ConceptSpringDto(personDto.getCauseOfDeath());
        age=personDto.getAge();
        registro=personDto.getRegistro();
        firma=personDto.getFirma();
        if(personDto.getErp() != null)
        erp=new ConceptSpringDto(personDto.getErp());
        if(personDto.getNivel() != null)
        nivel=new ConceptSpringDto(personDto.getNivel());
        if(personDto.getControlAph() != null)
        controlAph=new ConceptSpringDto(personDto.getControlAph());
        if(personDto.getPreferredName() != null)
        preferredName=new PersonNameSpringDto(personDto.getPreferredName());
        if(personDto.getPreferredAddress() != null)
        preferredAddress=new PersonAddressSpringDto(personDto.getPreferredAddress());
    }

    public Integer getLocalId() {
        return localId;
    }

    public void setLocalId(Integer localId) {
        this.localId = localId;
    }

    /**
	 * @return the personId
	 */
	public Integer getPersonId() {
		return personId;
	}
	/**
	 * @param personId the personId to set
	 */
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the birthdate
	 */
	public Date getBirthdate() {
		return birthdate;
	}
	/**
	 * @param birthdate the birthdate to set
	 */
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	/**
	 * @return the birthdateEstimated
	 */
	public Boolean getBirthdateEstimated() {
		return birthdateEstimated;
	}
	/**
	 * @param birthdateEstimated the birthdateEstimated to set
	 */
	public void setBirthdateEstimated(Boolean birthdateEstimated) {
		this.birthdateEstimated = birthdateEstimated;
	}
	/**
	 * @return the specialty
	 */
	public Integer getSpecialty() {
		return specialty;
	}
	/**
	 * @param specialty the specialty to set
	 */
	public void setSpecialty(Integer specialty) {
		this.specialty = specialty;
	}
	/**
	 * @return the identification
	 */
	public String getIdentification() {
		return identification;
	}
	/**
	 * @param identification the identification to set
	 */
	public void setIdentification(String identification) {
		this.identification = identification;
	}
	/**
	 * @return the dead
	 */
	public Boolean getDead() {
		return dead;
	}
	/**
	 * @param dead the dead to set
	 */
	public void setDead(Boolean dead) {
		this.dead = dead;
	}
	/**
	 * @return the deathDate
	 */
	public Date getDeathDate() {
		return deathDate;
	}
	/**
	 * @param deathDate the deathDate to set
	 */
	public void setDeathDate(Date deathDate) {
		this.deathDate = deathDate;
	}
	/**
	 * @return the causeOfDeath
	 */
	public ConceptSpringDto getCauseOfDeath() {
		return causeOfDeath;
	}
	/**
	 * @param causeOfDeath the causeOfDeath to set
	 */
	public void setCauseOfDeath(ConceptSpringDto causeOfDeath) {
		this.causeOfDeath = causeOfDeath;
	}
	/**
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}
	/**
	 * @return the registro
	 */
	public String getRegistro() {
		return registro;
	}
	/**
	 * @param registro the registro to set
	 */
	public void setRegistro(String registro) {
		this.registro = registro;
	}
	/**
	 * @return the firma
	 */
	public String getFirma() {
		return firma;
	}
	/**
	 * @param firma the firma to set
	 */
	public void setFirma(String firma) {
		this.firma = firma;
	}
	/**
	 * @return the erp
	 */
	public ConceptSpringDto getErp() {
		return erp;
	}
	/**
	 * @param erp the erp to set
	 */
	public void setErp(ConceptSpringDto erp) {
		this.erp = erp;
	}
	/**
	 * @return the nivel
	 */
	public ConceptSpringDto getNivel() {
		return nivel;
	}
	/**
	 * @param nivel the nivel to set
	 */
	public void setNivel(ConceptSpringDto nivel) {
		this.nivel = nivel;
	}
	/**
	 * @return the controlAph
	 */
	public ConceptSpringDto getControlAph() {
		return controlAph;
	}
	/**
	 * @param controlAph the controlAph to set
	 */
	public void setControlAph(ConceptSpringDto controlAph) {
		this.controlAph = controlAph;
	}
	/**
	 * @return the preferredName
	 */
	public PersonNameSpringDto getPreferredName() {
		return preferredName;
	}
	/**
	 * @param preferredName the preferredName to set
	 */
	public void setPreferredName(PersonNameSpringDto preferredName) {
		this.preferredName = preferredName;
	}
	/**
	 * @return the preferredAddress
	 */
	public PersonAddressSpringDto getPreferredAddress() {
		return preferredAddress;
	}
	/**
	 * @param preferredAddress the preferredAddress to set
	 */
	public void setPreferredAddress(PersonAddressSpringDto preferredAddress) {
		this.preferredAddress = preferredAddress;
	}

	
}
