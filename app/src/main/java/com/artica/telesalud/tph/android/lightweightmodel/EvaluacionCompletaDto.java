package com.artica.telesalud.tph.android.lightweightmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.artica.telesalud.tph.android.model.EvaluacionCompletaSpringDto;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by interoperabilidad on 18/07/14.
 */
@DatabaseTable()
public class EvaluacionCompletaDto  implements Serializable, Parcelable {
    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField()
    private Integer obsId;
    @DatabaseField()
    private Integer conceptId;
    @DatabaseField()
    private Date dateCreated;
    @DatabaseField()
    private Integer creator;

    @DatabaseField()
    private Integer encounterId;
    private EncounterDto encounter;
    @DatabaseField()
    private Boolean isSynchronized = Boolean.FALSE;
    public static final Parcelable.Creator<EvaluacionCompletaDto> CREATOR = new Parcelable.Creator<EvaluacionCompletaDto>() {
        public EvaluacionCompletaDto createFromParcel(Parcel in) {
            return new EvaluacionCompletaDto(in);
        }

        public EvaluacionCompletaDto[] newArray(int size) {
            return new EvaluacionCompletaDto[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EvaluacionCompletaDto(Parcel parcel)
    {
        id=(Integer)parcel.readValue(null);
        obsId = (Integer)parcel.readValue(null);
        conceptId = (Integer)parcel.readValue(null);
        dateCreated = (Date)parcel.readValue(null);
        creator = (Integer)parcel.readValue(null);
        encounter = (EncounterDto)parcel.readValue(EncounterDto.class.getClassLoader());
        isSynchronized = (Boolean)parcel.readValue(null);
        encounterId = (Integer)parcel.readValue(null);
    }
    public EvaluacionCompletaDto() {
    }
    public EvaluacionCompletaDto(EvaluacionCompletaSpringDto evaluacion)
    {
        obsId = evaluacion.getObsId();
        conceptId = evaluacion.getConceptId();
        dateCreated = evaluacion.getDateCreated();
        creator = evaluacion.getCreator();
        if(evaluacion.getEncounter() != null)
        {
            encounter = new EncounterDto(evaluacion.getEncounter());
        }
    }

    public Integer getEncounterId() {
        return encounterId;
    }

    public void setEncounterId(Integer encounterId) {
        this.encounterId = encounterId;
    }

    public Boolean getIsSynchronized() {
        return isSynchronized;
    }

    public void setIsSynchronized(Boolean isSynchronized) {
        this.isSynchronized = isSynchronized;
    }

    public EncounterDto getEncounter() {
        return encounter;
    }

    public void setEncounter(EncounterDto encounter) {
        this.encounter = encounter;
    }

    public Integer getObsId() {
        return obsId;
    }

    public void setObsId(Integer obsId) {
        this.obsId = obsId;
    }

    public Integer getConceptId() {
        return conceptId;
    }

    public void setConceptId(Integer conceptId) {
        this.conceptId = conceptId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(obsId);
        dest.writeValue(conceptId);
        dest.writeValue(dateCreated);
        dest.writeValue(creator);
        dest.writeValue(encounter);
        dest.writeValue(isSynchronized);
        dest.writeValue(encounterId);
    }
    public void setupId(EvaluacionCompletaDto evaluacionCompleta)
    {
        id = evaluacionCompleta.getId();
        if(encounter != null)
        {
            encounter.setupId(evaluacionCompleta.getEncounter());
            encounterId = encounter.getId();
        }
    }
}
