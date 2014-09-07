package com.artica.telesalud.tph.android.lightweightmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.artica.telesalud.tph.android.model.AntecedenteSpringDto;
import com.artica.telesalud.tph.android.model.AntecedentesAmpSpringDto;
import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by interoperabilidad on 5/09/14.
 */
public class AntecedentesDto implements Serializable, Parcelable{
    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField()
    private String historiaClinicaUuid;
    @DatabaseField()
    private String historiaClinicaNombre;
    private List<AntecedenteDto> antecedentes = new ArrayList<AntecedenteDto>();
    @DatabaseField()
    private Integer lesionadoId;
    public AntecedentesDto(AntecedentesAmpSpringDto antecedentesAmpSpringDto)
    {
        historiaClinicaNombre = antecedentesAmpSpringDto.getHistoriaClinicaNombre();
        historiaClinicaUuid = antecedentesAmpSpringDto.getHistoriaClinicaUuid();
        for(AntecedenteSpringDto antecedente : antecedentesAmpSpringDto.getAntecedentes())
        {
            AntecedenteDto a = new AntecedenteDto(antecedente, this);
            antecedentes.add(a);
        }
    }
    public AntecedentesDto()
    {

    }
    public static final Parcelable.Creator<AntecedentesDto> CREATOR = new Parcelable.Creator<AntecedentesDto>() {
        public AntecedentesDto createFromParcel(Parcel in) {
            return new AntecedentesDto(in);
        }

        public AntecedentesDto[] newArray(int size) {
            return new AntecedentesDto[size];
        }
    };
    public AntecedentesDto(Parcel parcel)
    {
        id=(Integer)parcel.readValue(null);
        historiaClinicaUuid=(String)parcel.readValue(null);
        historiaClinicaNombre=(String)parcel.readValue(null);
        parcel.readTypedList(antecedentes, AntecedenteDto.CREATOR);
    }

    public Integer getLesionadoId() {
        return lesionadoId;
    }

    public void setLesionadoId(Integer lesionadoId) {
        this.lesionadoId = lesionadoId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHistoriaClinicaUuid() {
        return historiaClinicaUuid;
    }

    public void setHistoriaClinicaUuid(String historiaClinicaUuid) {
        this.historiaClinicaUuid = historiaClinicaUuid;
    }

    public String getHistoriaClinicaNombre() {
        return historiaClinicaNombre;
    }

    public void setHistoriaClinicaNombre(String historiaClinicaNombre) {
        this.historiaClinicaNombre = historiaClinicaNombre;
    }

    public List<AntecedenteDto> getAntecedentes() {
        return antecedentes;
    }

    public void setAntecedentes(List<AntecedenteDto> antecedentes) {
        this.antecedentes = antecedentes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(historiaClinicaUuid);
       dest.writeValue(historiaClinicaNombre);
       dest.writeTypedList(antecedentes);
    }
}
