package com.artica.telesalud.tph.android.lightweightmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.artica.telesalud.tph.android.model.ConceptSpringDto;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Causa de una atencion de emergencias.
 */
@DatabaseTable()
public class ConceptDto implements Comparable<ConceptDto>, Parcelable {
    public static final Integer TYPE_CAUSA_ATENCION_ID = 1;
    public static final Integer TYPE_ZONA_ID = 2;
    public static final Integer TYPE_CAUSA_MUERTE_ID = 3;
    public static final Integer TYPE_ERP_ID = 4;
    public static final Integer TYPE_NIVEL_ID = 5;
    public static final Integer TYPE_CONTROL_APH_ID = 6;
    public static final Integer TYPE_RESULTADO_ID = 7;
    public static final Integer TYPE_DISPOSITIVO_TRANSPORTE_ID=8;
    public static final Integer TYPE_TIPO_NEGACION_ID=9;
    public static final Integer TYPE_EPS_ID=10;
    public static final Integer TYPE_ASEGURADORA_ID=11;
   public static final Integer TYPE_PLAN_BENEFICIOS_ID=12;
       public static final Integer TYPE_MARITAL_STATUS_ID=13;
    public static final Integer TYPE_USER_TYPE_ID=14;
    public static final Integer TYPE_AFFILIATE_TYPE_ID = 15;
    public static final Integer TYPE_CAUSA_EXTERNA_ID=16;
    public static final Integer TYPE_ANTECEDENT_TYPE_ID = 17;
    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField()
    private Integer conceptId;
    @DatabaseField()
    private Integer conceptTypeId;
    @DatabaseField()
    private String name;
    private Boolean selected;

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public Integer getConceptTypeId() {
        return conceptTypeId;
    }

    public void setConceptTypeId(Integer conceptTypeId) {
        this.conceptTypeId = conceptTypeId;
    }

    public static final ConceptDto EMPTY_CAUSA_ATENCION = new ConceptDto();

    public static final Parcelable.Creator<ConceptDto> CREATOR = new Parcelable.Creator<ConceptDto>() {
        public ConceptDto createFromParcel(Parcel in) {
            return new ConceptDto(in);
        }

        public ConceptDto[] newArray(int size) {
            return new ConceptDto[size];
        }
    };

    public ConceptDto() {

    }

    public ConceptDto(Integer conceptId, String name) {
        this.conceptId = conceptId;
        this.name = name;
    }

    public ConceptDto(ConceptSpringDto concept, Integer conceptTypeId) {
        this.conceptId = concept.getConceptId();
        this.name = concept.getShortName();
        this.conceptTypeId = conceptTypeId;
    }

    public ConceptDto(Parcel in) {
        id=(Integer)in.readValue(null);
        conceptId = (Integer)in.readValue(null);
        name = (String)in.readValue(null);
        conceptTypeId = (Integer)in.readValue(null);
    }

    public Integer getConceptId() {
        return conceptId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setConceptId(Integer conceptId) {
        this.conceptId = conceptId;
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
        if (!(o instanceof ConceptDto)) return false;

        ConceptDto that = (ConceptDto) o;

        if (conceptId != null ? !conceptId.equals(that.conceptId) : that.conceptId != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = conceptId != null ? conceptId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(ConceptDto another) {

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
        dest.writeValue(conceptId);
        dest.writeValue(name);
        dest.writeValue(conceptTypeId);
    }
    public void setupId(ConceptDto concept)
    {
        id = concept.getId();
    }

}
