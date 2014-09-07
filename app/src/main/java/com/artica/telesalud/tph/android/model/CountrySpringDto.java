package com.artica.telesalud.tph.android.model;


import com.artica.telesalud.tph.android.lightweightmodel.CountryDto;

public class CountrySpringDto {
	private Integer countryId;
	private String code;
	private String name;
    public CountrySpringDto(CountryDto country)
    {
        countryId = country.getCountryId();
        name = country.getName();
    }
    public CountrySpringDto()
    {

    }
	/**
	 * @return the countryId
	 */
	public Integer getCountryId() {
		return countryId;
	}
	/**
	 * @param countryId the countryId to set
	 */
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
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
	
}
