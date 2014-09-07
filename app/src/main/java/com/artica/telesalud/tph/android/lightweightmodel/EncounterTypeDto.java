package com.artica.telesalud.tph.android.lightweightmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.artica.telesalud.tph.android.model.EncounterTypeSpringDto;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by jonathan on 17/07/14.
 */
@DatabaseTable()
public class EncounterTypeDto implements Serializable, Parcelable{
    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField()
    private Integer encounterTypeId;
    @DatabaseField()
    private String name;
    @DatabaseField()
    private String description;
    @DatabaseField()
    private Boolean isSynchronized = Boolean.FALSE;

    public static final Parcelable.Creator<EncounterTypeDto> CREATOR = new Parcelable.Creator<EncounterTypeDto>() {
        public EncounterTypeDto createFromParcel(Parcel in) {
            return new EncounterTypeDto(in);
        }

        public EncounterTypeDto[] newArray(int size) {
            return new EncounterTypeDto[size];
        }
    };

    public EncounterTypeDto(Parcel in) {
        id = (Integer)in.readValue(null);
        encounterTypeId = (Integer)in.readValue(null);
        name = (String)in.readValue(null);
        description = (String)in.readValue(null);
        isSynchronized =(Boolean)in.readValue(null);
    }

    public EncounterTypeDto(EncounterTypeSpringDto encounterType) {
        encounterTypeId = encounterType.getEncounterTypeId();
        name = encounterType.getName();
        description = encounterType.getDescription();
        isSynchronized = Boolean.TRUE;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEncounterTypeId() {
        return encounterTypeId;
    }

    public void setEncounterTypeId(Integer encounterTypeId) {
        this.encounterTypeId = encounterTypeId;
    }
    public EncounterTypeDto()
    {

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
        dest.writeValue(encounterTypeId);
        dest.writeValue(name);
        dest.writeValue(description);
        dest.writeValue(isSynchronized);
    }
    public void setupId(EncounterTypeDto encounterType)
    {
        id = encounterType.getId();
    }
}
