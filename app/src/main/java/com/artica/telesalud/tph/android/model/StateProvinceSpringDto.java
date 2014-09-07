package com.artica.telesalud.tph.android.model;

import com.artica.telesalud.tph.android.lightweightmodel.StateDto;

import java.io.Serializable;



public class StateProvinceSpringDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer stateProvinceId;
	private CountrySpringDto country;
	private String code;
	private String name;
    public StateProvinceSpringDto(StateDto state)
    {
        stateProvinceId = state.getStateId();
        name = state.getName();
        country = new CountrySpringDto(state.getCountry());
    }
    public StateProvinceSpringDto()
    {

    }
	/**
	 * @return the stateProvinceId
	 */
	public Integer getStateProvinceId() {
		return stateProvinceId;
	}
	/**
	 * @param stateProvinceId the stateProvinceId to set
	 */
	public void setStateProvinceId(Integer stateProvinceId) {
		this.stateProvinceId = stateProvinceId;
	}
	/**
	 * @return the country
	 */
	public CountrySpringDto getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(CountrySpringDto country) {
		this.country = country;
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
