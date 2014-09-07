package com.artica.telesalud.tph.android.lightweightmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.artica.telesalud.tph.android.model.CitySpringDto;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by interoperabilidad on 20/05/14.
 */
@DatabaseTable()
public class CityDto implements Comparable<CityDto>, Parcelable{
    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField()
    private Integer cityId;
    @DatabaseField()
    private String name;
    @DatabaseField()
    private Integer stateId;
    private StateDto state;
    public static final CityDto EMPTY_CITY = new CityDto();

    public static final Parcelable.Creator<CityDto> CREATOR = new Parcelable.Creator<CityDto>() {
        public CityDto createFromParcel(Parcel in) {
            return new CityDto(in);
        }

        public CityDto[] newArray(int size) {
            return new CityDto[size];
        }
    };

    public CityDto(CitySpringDto city) {
        this.cityId = city.getCityId();
        this.name = city.getName();
        this.state = new StateDto(city.getStateProvince());
    }

    public CityDto(Parcel in) {
        id = (Integer)in.readValue(null);
        cityId = (Integer)in.readValue(null);
        name = (String)in.readValue(null);
        state = (StateDto)in.readValue(StateDto.class.getClassLoader());
        stateId = (Integer)in.readValue(null);
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StateDto getState() {
        return state;
    }

    public void setState(StateDto state) {
        this.state = state;
    }

    public CityDto() {

    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
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
        if (!(o instanceof CityDto)) return false;

        CityDto cityDto = (CityDto) o;

        if (cityId != null ? !cityId.equals(cityDto.cityId) : cityDto.cityId != null) return false;
        if (name != null ? !name.equals(cityDto.name) : cityDto.name != null) return false;
        if (state != null ? !state.equals(cityDto.state) : cityDto.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cityId != null ? cityId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(CityDto another) {

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
        dest.writeValue(cityId);
        dest.writeValue(name);
        dest.writeValue(state);
        dest.writeValue(stateId);
    }
}
