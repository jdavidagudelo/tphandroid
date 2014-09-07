package com.artica.telesalud.tph.android.lightweightmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.artica.telesalud.tph.android.model.EncounterSpringDto;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by jonathan on 16/07/14.
 */
@DatabaseTable()
public class EncounterDto implements Serializable, Parcelable {
    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField()
    private Integer encounterId;

    @DatabaseField()
    private Integer encounterTypeId;
    private EncounterTypeDto encounterType;

    @DatabaseField()
    private Integer patientId;
    private PatientDto patient;
    @DatabaseField()
    private Integer providerId;
    private PersonDto provider;
    @DatabaseField()
    private Integer locationId;
    private CityDto location;
    @DatabaseField()
    private Date encounterDatetime;
    @DatabaseField()
    private String state;
    @DatabaseField()
    private Boolean isSynchronized = Boolean.FALSE;
    @DatabaseField()
    private Integer creator;
    @DatabaseField()
    private Date dateCreated;

    public static final Parcelable.Creator<EncounterDto> CREATOR = new Parcelable.Creator<EncounterDto>() {
        public EncounterDto createFromParcel(Parcel in) {
            return new EncounterDto(in);
        }

        public EncounterDto[] newArray(int size) {
            return new EncounterDto[size];
        }
    };
    public EncounterDto(){

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

    public EncounterDto(Parcel encounter) {
        id=(Integer)encounter.readValue(null);
        encounterId = (Integer)encounter.readValue(null);
        encounterType = (EncounterTypeDto)encounter.readValue(EncounterTypeDto.class.getClassLoader());
        patient = (PatientDto)encounter.readValue(PatientDto.class.getClassLoader());
        provider = (PersonDto)encounter.readValue(PersonDto.class.getClassLoader());
        location = (CityDto)encounter.readValue(CityDto.class.getClassLoader());
        encounterDatetime = (Date) encounter.readValue(Date.class.getClassLoader());
        state = (String)encounter.readValue(null);
        isSynchronized =( Boolean)encounter.readValue(null);
        encounterTypeId = (Integer)encounter.readValue(null);
        patientId = (Integer)encounter.readValue(null);
        providerId = (Integer)encounter.readValue(null);
        locationId = (Integer)encounter.readValue(null);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EncounterDto(EncounterSpringDto encuentro) {
        encounterId = encuentro.getEncounterId();
        if(encuentro.getEncounterType() != null) {
            encounterType = new EncounterTypeDto(encuentro.getEncounterType());
        }
        if(encuentro.getPatient() != null) {
            patient = new PatientDto(encuentro.getPatient());
        }
        if(encuentro.getProvider() != null) {
            provider = new PersonDto(encuentro.getProvider());
        }
        if(encuentro.getLocation() != null) {
            location = new CityDto(encuentro.getLocation());
        }
        encounterDatetime = encuentro.getEncounterDatetime();
        state = encuentro.getState();
        isSynchronized = Boolean.TRUE;
    }

    public Integer getEncounterId() {
        return encounterId;
    }

    public void setEncounterId(Integer encounterId) {
        this.encounterId = encounterId;
    }

    public EncounterTypeDto getEncounterType() {
        return encounterType;
    }

    public void setEncounterType(EncounterTypeDto encounterType) {
        this.encounterType = encounterType;
    }

    public PatientDto getPatient() {
        return patient;
    }

    public void setPatient(PatientDto patient) {
        this.patient = patient;
    }

    public PersonDto getProvider() {
        return provider;
    }

    public void setProvider(PersonDto provider) {
        this.provider = provider;
    }

    public CityDto getLocation() {
        return location;
    }

    public void setLocation(CityDto location) {
        this.location = location;
    }

    public Date getEncounterDatetime() {
        return encounterDatetime;
    }

    public void setEncounterDatetime(Date encounterDatetime) {
        this.encounterDatetime = encounterDatetime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public Integer getEncounterTypeId() {
        return encounterTypeId;
    }

    public void setEncounterTypeId(Integer encounterTypeId) {
        this.encounterTypeId = encounterTypeId;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(encounterId);
        dest.writeValue(encounterType);
        dest.writeValue(patient);
        dest.writeValue(provider);
        dest.writeValue(location);
        dest.writeValue(encounterDatetime);
        dest.writeValue(state);
        dest.writeValue(isSynchronized);
        dest.writeValue(encounterTypeId);
        dest.writeValue(patientId);
        dest.writeValue(providerId);
        dest.writeValue(locationId);
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public void setupId(EncounterDto encounter)
    {
        id = encounter.getId();
        if(patient != null)
        {
            patient.setupId(encounter.getPatient());
        }
    }
}
