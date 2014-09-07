package com.artica.telesalud.tph.android.lightweightmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.artica.telesalud.tph.android.model.PersonAddressSpringDto;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by jonathan on 17/07/14.
 */
@DatabaseTable()
public class PersonAddressDto implements Serializable, Parcelable {
    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField()
    private Integer personAddressId;
    @DatabaseField()
    private Integer personId;
    @DatabaseField()
    private Boolean preferred;
    @DatabaseField()
    private String address;
    @DatabaseField()
    private Integer city;
    @DatabaseField()
    private String neightborhoodCell;
    @DatabaseField()
    private String region;
    @DatabaseField()
    private String postalCode;
    @DatabaseField()
    private String latitude;
    @DatabaseField()
    private String longitude;
    @DatabaseField()
    private String phone1;
    @DatabaseField()
    private String phone2;
    @DatabaseField()
    private String email;
    @DatabaseField()
    private Boolean isSynchronized = Boolean.FALSE;

    public static final Parcelable.Creator<PersonAddressDto> CREATOR = new Parcelable.Creator<PersonAddressDto>() {
        public PersonAddressDto createFromParcel(Parcel in) {
            return new PersonAddressDto(in);
        }

        public PersonAddressDto[] newArray(int size) {
            return new PersonAddressDto[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PersonAddressDto(){

}
    public PersonAddressDto(Parcel in) {
        id = (Integer)in.readValue(null);
        personAddressId = (Integer)in.readValue(Integer.class.getClassLoader());
        personId = (Integer)in.readValue(Integer.class.getClassLoader());
        preferred = (Boolean) in.readValue(Boolean.class.getClassLoader());
        address = (String)in.readValue(null);
        city = (Integer)in.readValue(Integer.class.getClassLoader());
        neightborhoodCell = (String)in.readValue(null);
        region = (String)in.readValue(null);
        postalCode = (String)in.readValue(null);
        latitude = (String)in.readValue(null);
        longitude = (String)in.readValue(null);
        phone1 = (String)in.readValue(null);
        phone2 = (String)in.readValue(null);
        email = (String)in.readValue(null);
        isSynchronized = (Boolean)in.readValue(null);
    }

    public PersonAddressDto(PersonAddressSpringDto preferredAddress) {
        personAddressId = preferredAddress.getPersonAddressId();
        personId = preferredAddress.getPersonId();
        preferred = preferredAddress.getPreferred();
        address = preferredAddress.getAddress();
        city = preferredAddress.getCity();
        neightborhoodCell = preferredAddress.getNeightborhoodCell();
        region = preferredAddress.getRegion();
        postalCode = preferredAddress.getPostalCode();
        latitude = preferredAddress.getLatitude();
        longitude = preferredAddress.getLongitude();
        phone1 = preferredAddress.getPhone1();
        phone2 = preferredAddress.getPhone2();
        email = preferredAddress.getEmail();
        isSynchronized = Boolean.TRUE;
    }

    public Integer getPersonAddressId() {
        return personAddressId;
    }

    public void setPersonAddressId(Integer personAddressId) {
        this.personAddressId = personAddressId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Boolean getPreferred() {
        return preferred;
    }

    public void setPreferred(Boolean preferred) {
        this.preferred = preferred;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public String getNeightborhoodCell() {
        return neightborhoodCell;
    }

    public void setNeightborhoodCell(String neightborhoodCell) {
        this.neightborhoodCell = neightborhoodCell;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIsSynchronized() {
        return isSynchronized;
    }

    public void setIsSynchronized(Boolean isSynchronized) {
        this.isSynchronized = isSynchronized;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(personAddressId);
        dest.writeValue(personId);
        dest.writeValue(preferred);
        dest.writeValue(address);
        dest.writeValue(city);
        dest.writeValue(neightborhoodCell);
        dest.writeValue(region);
        dest.writeValue(postalCode);
        dest.writeValue(latitude);
        dest.writeValue(longitude);
        dest.writeValue(phone1);
        dest.writeValue(phone2);
        dest.writeValue(email);
        dest.writeValue(isSynchronized);
    }
    public void setupId(PersonAddressDto personAddress)
    {
        id = personAddress.getId();

    }
}
