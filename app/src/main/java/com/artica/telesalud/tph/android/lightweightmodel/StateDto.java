package com.artica.telesalud.tph.android.lightweightmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.artica.telesalud.tph.android.model.StateProvinceSpringDto;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Clase que representa la lista de departamentos
 */
@DatabaseTable()
public class StateDto implements Comparable<StateDto>, Parcelable{
    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField()
    private Integer stateId;
    @DatabaseField()
    private String name;
    @DatabaseField()
    private Integer countryId;
    private CountryDto country;

    public static final StateDto EMPTY_STATE = new StateDto();

    public static final Parcelable.Creator<StateDto> CREATOR = new Parcelable.Creator<StateDto>() {
        public StateDto createFromParcel(Parcel in) {
            return new StateDto(in);
        }

        public StateDto[] newArray(int size) {
            return new StateDto[size];
        }
    };

    public StateDto(Parcel in) {
        id=(Integer)in.readValue(null);
        stateId = (Integer)in.readValue(null);
        name = (String)in.readValue(null);
        country = (CountryDto)in.readValue(CountryDto.class.getClassLoader());
        countryId = (Integer)in.readValue(null);
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CountryDto getCountry() {
        return country;
    }

    public void setCountry(CountryDto country) {
        this.country = country;
    }

    public StateDto() {

    }
    public StateDto(StateProvinceSpringDto state) {
        this.stateId = state.getStateProvinceId();
        this.name = state.getName();
        this.country = new CountryDto(state.getCountry());

    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("");
        if(name != null) {
            sb.append(name);
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StateDto)) return false;

        StateDto stateDto = (StateDto) o;

        if (country != null ? !country.equals(stateDto.country) : stateDto.country != null)
            return false;
        if (name != null ? !name.equals(stateDto.name) : stateDto.name != null) return false;
        if (stateId != null ? !stateId.equals(stateDto.stateId) : stateDto.stateId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = stateId != null ? stateId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }
    @Override
        public int compareTo(StateDto another) {
        if(name != null && another != null && another.name != null) {
            return name.compareTo(another.name);
        }
        if(name == null)
        {
            return -1;
        }
        else if(another != null && another.name == null)
        {
            return 1;
        }
        else {
            return 0;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(stateId);
        dest.writeValue(name);
        dest.writeValue(country);
        dest.writeValue(countryId);
    }
}
