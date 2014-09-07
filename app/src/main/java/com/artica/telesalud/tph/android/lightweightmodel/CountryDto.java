package com.artica.telesalud.tph.android.lightweightmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.artica.telesalud.tph.android.model.CountrySpringDto;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by interoperabilidad on 20/05/14.
 */
@DatabaseTable()
public class CountryDto implements Comparable<CountryDto>, Parcelable{

    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField()
    private Integer countryId;
    @DatabaseField(index = true)
    private String name;

    public static final Parcelable.Creator<CountryDto> CREATOR = new Parcelable.Creator<CountryDto>() {
        public CountryDto createFromParcel(Parcel in) {
            return new CountryDto(in);
        }

        public CountryDto[] newArray(int size) {
            return new CountryDto[size];
        }
    };

    public CountryDto() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static final CountryDto EMPTY_COUNTRY = new CountryDto();
    public CountryDto(CountrySpringDto country) {

        this.countryId = country.getCountryId();
        this.name = country.getName();
    }

    public CountryDto(Parcel in) {
        id = (Integer)in.readValue(null);
        countryId = (Integer)in.readValue(null);
        name = (String)in.readValue(null);
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CountryDto)) return false;

        CountryDto that = (CountryDto) o;

        if (countryId != null ? !countryId.equals(that.countryId) : that.countryId != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = countryId != null ? countryId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
    @Override
    public int compareTo(CountryDto another) {

        if(name != null && another != null && another.name != null) {
            return name.compareTo(another.name);
        }  if(name == null)
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
        dest.writeValue(countryId);
        dest.writeValue(name);
    }
    public void setupId(CountryDto country)
    {
        id = country.getId();
    }
}
