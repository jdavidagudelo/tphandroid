package com.artica.telesalud.tph.android.lightweightmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.artica.telesalud.tph.android.model.PatientIdentifierTypeSpringDto;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by jonathan on 17/07/14.
 */
@DatabaseTable()
public class PatientIdentifierTypeDto implements Serializable, Parcelable {
    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField()
    private Integer patientIdentifierTypeId;
    @DatabaseField()
    private String name;
    @DatabaseField()
    private String description;
    @DatabaseField()
    private Boolean isSynchronized = Boolean.FALSE;
    private Boolean selected;

    public static final Parcelable.Creator<PatientIdentifierTypeDto> CREATOR = new Parcelable.Creator<PatientIdentifierTypeDto>() {
        public PatientIdentifierTypeDto createFromParcel(Parcel in) {
            return new PatientIdentifierTypeDto(in);
        }

        public PatientIdentifierTypeDto[] newArray(int size) {
            return new PatientIdentifierTypeDto[size];
        }
    };

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PatientIdentifierTypeDto(Parcel in) {
        id=(Integer)in.readValue(null);
        patientIdentifierTypeId = (Integer)in.readValue(null);
        name = (String)in.readValue(null);
        description =(String) in.readValue(null);
        isSynchronized = (Boolean)in.readValue(null);
    }
    public PatientIdentifierTypeDto()
    {

    }    public PatientIdentifierTypeDto(PatientIdentifierTypeSpringDto patientIdentifierType) {
        patientIdentifierTypeId = patientIdentifierType.getPatientIdentifierTypeId();
        name = patientIdentifierType.getName();
        description = patientIdentifierType.getDescription();
        isSynchronized = Boolean.TRUE;
    }

    public Integer getPatientIdentifierTypeId() {
        return patientIdentifierTypeId;
    }

    public void setPatientIdentifierTypeId(Integer patientIdentifierTypeId) {
        this.patientIdentifierTypeId = patientIdentifierTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        dest.writeValue(patientIdentifierTypeId);
        dest.writeValue(name);
        dest.writeValue(description);
        dest.writeValue(isSynchronized);
    }
}
