package com.artica.telesalud.tph.android.lightweightmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.artica.telesalud.tph.android.model.AntecedenteSpringDto;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by interoperabilidad on 5/09/14.
 */
@DatabaseTable()
public class AntecedenteDto implements Parcelable, Serializable{
    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField()
    private String description;
    @DatabaseField()
    private Integer antecedentesId;
    private AntecedentesDto antecedentes;
    @DatabaseField()
    private String tipoAntecedente;
    @DatabaseField()
    private String date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public static final Parcelable.Creator<AntecedenteDto> CREATOR = new Parcelable.Creator<AntecedenteDto>() {
        public AntecedenteDto createFromParcel(Parcel in) {
            return new AntecedenteDto(in);
        }

        public AntecedenteDto[] newArray(int size) {
            return new AntecedenteDto[size];
        }
    };
    public AntecedenteDto()
    {

    }
    public AntecedenteDto(Parcel parcel)
    {
        id = (Integer)parcel.readValue(null);
        tipoAntecedente=(String)parcel.readValue(null);
        antecedentesId=(Integer)parcel.readValue(null);
        antecedentes=(AntecedentesDto)parcel.readValue(null);
        date=(String)parcel.readValue(null);
        description=(String)parcel.readValue(null);
    }
    public AntecedenteDto(AntecedenteSpringDto antecedente, AntecedentesDto parent) {

        setDescription(antecedente.getDescription());
        setDate(antecedente.getDate());
        setAntecedentes(parent);
        setTipoAntecedente(antecedente.getTipoAntecedente().getTipoAntecedente());
    }

    public String getTipoAntecedente() {
        return tipoAntecedente;
    }

    public void setTipoAntecedente(String tipoAntecedente) {
        this.tipoAntecedente = tipoAntecedente;
    }

    public Integer getAntecedentesId() {
        return antecedentesId;
    }

    public void setAntecedentesId(Integer antecedentesId) {
        this.antecedentesId = antecedentesId;
    }

    public AntecedentesDto getAntecedentes() {
        return antecedentes;
    }

    public void setAntecedentes(AntecedentesDto antecedentes) {
        this.antecedentes = antecedentes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(tipoAntecedente);
        dest.writeValue(antecedentesId);
        dest.writeValue(antecedentes);
        dest.writeValue(date);
        dest.writeValue(description);
    }
}