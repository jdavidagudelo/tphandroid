package com.artica.telesalud.tph.android.model;


import com.artica.telesalud.tph.android.lightweightmodel.PersonNameDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class PersonNameSpringDto {
    @JsonIgnore
    private Integer localId;
	private Integer personNameId;
	private Boolean preferred;
	private Integer personId;
	private String prefix;
	private String givenName;
	private String middleName;
	private String familyNamePrefix;
	private String familyName;
	private String familyName2;
	private String familyNameSuffix;
	private String degree;

    public PersonNameSpringDto()
    {

    }
    public PersonNameSpringDto(PersonNameDto personName)
    {
        personNameId=personName.getPersonNameId();
        preferred=personName.getPreferred();
        personId=personName.getPersonId();
        prefix=personName.getPrefix();
        givenName=personName.getGivenName();
        middleName=personName.getMiddleName();
        familyNamePrefix=personName.getFamilyNamePrefix();
        familyName=personName.getFamilyName();
        familyName2=personName.getFamilyName2();
        familyNameSuffix=personName.getFamilyNameSuffix();
        degree=personName.getDegree();
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
    }
    public Integer getLocalId() {
        return localId;
    }

    public void setLocalId(Integer localId) {
        this.localId = localId;
    }

    /**
	 * @return the personNameId
	 */
	public Integer getPersonNameId() {
		return personNameId;
	}
	/**
	 * @param personNameId the personNameId to set
	 */
	public void setPersonNameId(Integer personNameId) {
		this.personNameId = personNameId;
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
	 * @return the person
	 */
	public Integer getPersonId() {
		return personId;
	}
	/**
	 * @param person the person to set
	 */
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	/**
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}
	/**
	 * @param prefix the prefix to set
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	/**
	 * @return the givenName
	 */
	public String getGivenName() {
		return givenName;
	}
	/**
	 * @param givenName the givenName to set
	 */
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}
	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	/**
	 * @return the familyNamePrefix
	 */
	public String getFamilyNamePrefix() {
		return familyNamePrefix;
	}
	/**
	 * @param familyNamePrefix the familyNamePrefix to set
	 */
	public void setFamilyNamePrefix(String familyNamePrefix) {
		this.familyNamePrefix = familyNamePrefix;
	}
	/**
	 * @return the familyName
	 */
	public String getFamilyName() {
		return familyName;
	}
	/**
	 * @param familyName the familyName to set
	 */
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	/**
	 * @return the familyName2
	 */
	public String getFamilyName2() {
		return familyName2;
	}
	/**
	 * @param familyName2 the familyName2 to set
	 */
	public void setFamilyName2(String familyName2) {
		this.familyName2 = familyName2;
	}
	/**
	 * @return the familyNameSuffix
	 */
	public String getFamilyNameSuffix() {
		return familyNameSuffix;
	}
	/**
	 * @param familyNameSuffix the familyNameSuffix to set
	 */
	public void setFamilyNameSuffix(String familyNameSuffix) {
		this.familyNameSuffix = familyNameSuffix;
	}
	/**
	 * @return the degree
	 */
	public String getDegree() {
		return degree;
	}
	/**
	 * @param degree the degree to set
	 */
	public void setDegree(String degree) {
		this.degree = degree;
	}

}
