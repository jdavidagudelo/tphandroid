package com.artica.telesalud.tph.android.lightweightmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.artica.telesalud.tph.android.model.PersonNameSpringDto;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by jonathan on 17/07/14.
 */
@DatabaseTable()
public class PersonNameDto implements Serializable, Parcelable {
    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField()
    private Integer personNameId;
    @DatabaseField()
    private Boolean preferred;
    @DatabaseField()
    private Integer personId;
    @DatabaseField()
    private String prefix;
    @DatabaseField()
    private String givenName;
    @DatabaseField()
    private String middleName;
    @DatabaseField()
    private String familyNamePrefix;
    @DatabaseField()
    private String familyName;
    @DatabaseField()
    private String familyName2;
    @DatabaseField()
    private String familyNameSuffix;
    @DatabaseField()
    private String degree;
    @DatabaseField()
    private Boolean isSynchronized = Boolean.FALSE;
    @DatabaseField()
    private Integer creator;
    @DatabaseField()
    private Date dateCreated;

    public static final Parcelable.Creator<PersonNameDto> CREATOR = new Parcelable.Creator<PersonNameDto>() {
        public PersonNameDto createFromParcel(Parcel in) {
            return new PersonNameDto(in);
        }

        public PersonNameDto[] newArray(int size) {
            return new PersonNameDto[size];
        }
    };
public PersonNameDto(){

}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PersonNameDto(Parcel in) {
        id=(Integer)in.readValue(null);
        personNameId = (Integer)in.readValue(null);
        preferred = (Boolean) in.readValue(Boolean.class.getClassLoader());
        personId = (Integer)in.readValue(null);
        prefix = (String)in.readValue(null);
        givenName = (String)in.readValue(null);
        middleName = (String)in.readValue(null);
        familyNamePrefix = (String)in.readValue(null);
        familyName = (String)in.readValue(null);
        familyName2 = (String)in.readValue(null);
        familyNameSuffix = (String)in.readValue(null);
        degree = (String)in.readValue(null);
        isSynchronized = (Boolean)in.readValue(null);
    }

    public PersonNameDto(PersonNameSpringDto preferredName) {
        personNameId = preferredName.getPersonNameId();
        preferred = preferredName.getPreferred();
        personId = preferredName.getPersonId();
        prefix = preferredName.getPrefix();
        givenName = preferredName.getGivenName();
        middleName = preferredName.getMiddleName();
        familyNamePrefix = preferredName.getFamilyNamePrefix();
        familyName = preferredName.getFamilyName();
        familyName2 = preferredName.getFamilyName2();
        familyNameSuffix = preferredName.getFamilyNameSuffix();
        degree = preferredName.getDegree();
        isSynchronized = Boolean.TRUE;
    }

    public Integer getPersonNameId() {
        return personNameId;
    }

    public void setPersonNameId(Integer personNameId) {
        this.personNameId = personNameId;
    }

    public Boolean getPreferred() {
        return preferred;
    }

    public void setPreferred(Boolean preferred) {
        this.preferred = preferred;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getFamilyNamePrefix() {
        return familyNamePrefix;
    }

    public void setFamilyNamePrefix(String familyNamePrefix) {
        this.familyNamePrefix = familyNamePrefix;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getFamilyName2() {
        return familyName2;
    }

    public void setFamilyName2(String familyName2) {
        this.familyName2 = familyName2;
    }

    public String getFamilyNameSuffix() {
        return familyNameSuffix;
    }

    public void setFamilyNameSuffix(String familyNameSuffix) {
        this.familyNameSuffix = familyNameSuffix;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
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
        dest.writeValue(personNameId);
        dest.writeValue(preferred);
        dest.writeValue(personId);
        dest.writeValue(prefix);
        dest.writeValue(givenName);
        dest.writeValue(middleName);
        dest.writeValue(familyNamePrefix);
        dest.writeValue(familyName);
        dest.writeValue(familyName2);
        dest.writeValue(familyNameSuffix);
        dest.writeValue(degree);
        dest.writeValue(isSynchronized);
    }
    public void setupId(PersonNameDto personName)
    {
        id=personName.getId();
    }

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
}
