package com.artica.telesalud.tph.android.model;


import com.artica.telesalud.tph.android.lightweightmodel.PersonAddressDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class PersonAddressSpringDto {
    @JsonIgnore
    private Integer localId;
	private Integer personAddressId;
	private Integer personId;
	private Boolean preferred;
	private String address;
	private Integer city;
	private String neightborhoodCell;
	private String region;
	private String postalCode;
	private String latitude;
	private String longitude;
	private String phone1;
	private String phone2;
	private String email;
    public PersonAddressSpringDto(){

    }
	public PersonAddressSpringDto(PersonAddressDto personAddress){
        personAddressId=personAddress.getPersonAddressId();
        personId=personAddress.getPersonId();
        preferred=personAddress.getPreferred();
        address=personAddress.getAddress();
        city=personAddress.getCity();
        neightborhoodCell=personAddress.getNeightborhoodCell();
        region=personAddress.getRegion();
        postalCode=personAddress.getPostalCode();
        latitude=personAddress.getLatitude();
        longitude=personAddress.getLongitude();
        phone1=personAddress.getPhone1();
        phone2=personAddress.getPhone2();
        email=personAddress.getEmail();
    }

    public Integer getLocalId() {
        return localId;
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
    }public void setLocalId(Integer localId) {
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
	 * @return the personAddressId
	 */
	public Integer getPersonAddressId() {
		return personAddressId;
	}
	/**
	 * @param personAddressId the personAddressId to set
	 */
	public void setPersonAddressId(Integer personAddressId) {
		this.personAddressId = personAddressId;
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
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the city
	 */
	public Integer getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(Integer city) {
		this.city = city;
	}
	/**
	 * @return the neightborhoodCell
	 */
	public String getNeightborhoodCell() {
		return neightborhoodCell;
	}
	/**
	 * @param neightborhoodCell the neightborhoodCell to set
	 */
	public void setNeightborhoodCell(String neightborhoodCell) {
		this.neightborhoodCell = neightborhoodCell;
	}
	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}
	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}
	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}
	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	/**
	 * @return the phone1
	 */
	public String getPhone1() {
		return phone1;
	}
	/**
	 * @param phone1 the phone1 to set
	 */
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	/**
	 * @return the phone2
	 */
	public String getPhone2() {
		return phone2;
	}
	/**
	 * @param phone2 the phone2 to set
	 */
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
}
