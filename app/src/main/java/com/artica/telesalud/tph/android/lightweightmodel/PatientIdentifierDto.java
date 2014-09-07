package com.artica.telesalud.tph.android.lightweightmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.artica.telesalud.tph.android.model.PatientIdentifierSpringDto;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by jonathan on 17/07/14.
 */
@DatabaseTable()
public class PatientIdentifierDto implements Serializable, Parcelable {
    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField()
    private Integer patientIdentifierId;
    @DatabaseField()
    private Integer patientId;
    @DatabaseField()
    private String identifier;
    @DatabaseField()
    private Integer patientIdentifierTypeId;
    private PatientIdentifierTypeDto patientIdentifierType;
    @DatabaseField()
    private Boolean preferred;
    @DatabaseField()
    private Integer country;
    @DatabaseField()
    private Boolean isSynchronized = Boolean.FALSE;
    @DatabaseField()
    private Integer creator;
    @DatabaseField()
    private Date dateCreated;

    public static final Parcelable.Creator<PatientIdentifierDto> CREATOR = new Parcelable.Creator<PatientIdentifierDto>() {
        public PatientIdentifierDto createFromParcel(Parcel in) {
            return new PatientIdentifierDto(in);
        }

        public PatientIdentifierDto[] newArray(int size) {
            return new PatientIdentifierDto[size];
        }
    };

    public PatientIdentifierDto(Parcel in) {
        id=(Integer)in.readValue(null);
        patientIdentifierId = (Integer)in.readValue(null);
        patientId = (Integer)in.readValue(null);
        identifier = (String)in.readValue(null);
        patientIdentifierType = (PatientIdentifierTypeDto)in.readValue(PatientIdentifierTypeDto.class.getClassLoader());
        preferred = (Boolean) in.readValue(Boolean.class.getClassLoader());
        country = (Integer)in.readValue(null);
        isSynchronized = (Boolean)in.readValue(null);
        patientIdentifierTypeId = (Integer)in.readValue(null);
    }
    public PatientIdentifierDto(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PatientIdentifierDto(PatientIdentifierSpringDto preferredIdentifier) {
        patientIdentifierId = preferredIdentifier.getPatientIdentifierId();
        patientId = preferredIdentifier.getPatientId();
        identifier = preferredIdentifier.getIdentifier();
        if(preferredIdentifier.getPatientIdentifierType() != null)
        patientIdentifierType = new PatientIdentifierTypeDto(preferredIdentifier.getPatientIdentifierType());
        preferred = preferredIdentifier.getPreferred();
        country = preferredIdentifier.getCountry();
        isSynchronized = Boolean.TRUE;
    }

    public Integer getPatientIdentifierTypeId() {
        return patientIdentifierTypeId;
    }

    public void setPatientIdentifierTypeId(Integer patientIdentifierTypeId) {
        this.patientIdentifierTypeId = patientIdentifierTypeId;
    }

    public Integer getPatientIdentifierId() {
        return patientIdentifierId;
    }

    public void setPatientIdentifierId(Integer patientIdentifierId) {
        this.patientIdentifierId = patientIdentifierId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public PatientIdentifierTypeDto getPatientIdentifierType() {
        return patientIdentifierType;
    }

    public void setPatientIdentifierType(PatientIdentifierTypeDto patientIdentifierType) {
        this.patientIdentifierType = patientIdentifierType;
    }

    public Boolean getPreferred() {
        return preferred;
    }

    public void setPreferred(Boolean preferred) {
        this.preferred = preferred;
    }

    public Integer getCountry() {
        return country;
    }

    public void setCountry(Integer country) {
        this.country = country;
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
        dest.writeValue(patientIdentifierId);
        dest.writeValue(patientId);
        dest.writeValue(identifier);
        dest.writeValue(patientIdentifierType);
        dest.writeValue(preferred);
        dest.writeValue(country);
        dest.writeValue(isSynchronized);
       dest.writeValue(patientIdentifierTypeId);
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

    public void setupId(PatientIdentifierDto patientIdentifier)
    {
        id = patientIdentifier.getId();
    }
}
