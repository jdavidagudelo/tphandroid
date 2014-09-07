package com.artica.telesalud.tph.android.lightweightmodel;

import com.artica.telesalud.tph.android.model.CitySpringDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

/**
 * Created by interoperabilidad on 20/05/14.
 */
public class ListCities extends ArrayList<CitySpringDto> {
    @JsonIgnore
    private Integer stateId;

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public ListCities() {

    }

}
