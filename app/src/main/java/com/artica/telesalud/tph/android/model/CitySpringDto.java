package com.artica.telesalud.tph.android.model;

import com.artica.telesalud.tph.android.lightweightmodel.CityDto;

import java.io.Serializable;


public class CitySpringDto implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer cityId;
    private StateProvinceSpringDto stateProvince;
    private String code;
    private String name;
    public CitySpringDto(CityDto city)
    {
        name = city.getName();
        cityId = city.getCityId();
        stateProvince = new StateProvinceSpringDto(city.getState());
    }

    public CitySpringDto(){
        super();
    }
    /**
     * @return the cityId
     */
    public Integer getCityId() {
        return cityId;
    }
    /**
     * @param cityId the cityId to set
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
    /**
     * @return the stateProvince
     */
    public StateProvinceSpringDto getStateProvince() {
        return stateProvince;
    }
    /**
     * @param stateProvince the stateProvince to set
     */
    public void setStateProvince(StateProvinceSpringDto stateProvince) {
        this.stateProvince = stateProvince;
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
