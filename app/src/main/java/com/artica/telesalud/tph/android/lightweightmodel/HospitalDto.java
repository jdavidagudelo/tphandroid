package com.artica.telesalud.tph.android.lightweightmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.artica.telesalud.tph.android.model.HospitalSpringDto;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by jonathan on 16/07/14.
 */
@DatabaseTable()
public class HospitalDto implements Serializable, Parcelable {
    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField()
    private Integer hospitalId;
    @DatabaseField()
    private String nombre;
    @DatabaseField()
    private Integer ciudadId;
    private CityDto ciudad;
    @DatabaseField()
    private String direccion;
    @DatabaseField()
    private Integer nivelComplejidadId;
    private ConceptDto nivelComplejidad;
    @DatabaseField()
    private Boolean isSynchronized = Boolean.FALSE;

    public static final Parcelable.Creator<HospitalDto> CREATOR = new Parcelable.Creator<HospitalDto>() {
        public HospitalDto createFromParcel(Parcel in) {
            return new HospitalDto(in);
        }

        public HospitalDto[] newArray(int size) {
            return new HospitalDto[size];
        }
    };
public HospitalDto(){

}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCiudadId() {
        return ciudadId;
    }

    public void setCiudadId(Integer ciudadId) {
        this.ciudadId = ciudadId;
    }

    public Integer getNivelComplejidadId() {
        return nivelComplejidadId;
    }

    public void setNivelComplejidadId(Integer nivelComplejidadId) {
        this.nivelComplejidadId = nivelComplejidadId;
    }

    public Boolean getIsSynchronized() {
        return isSynchronized;
    }

    public void setIsSynchronized(Boolean isSynchronized) {
        this.isSynchronized = isSynchronized;
    }

    public HospitalDto(Parcel hospital) {
        id = (Integer)hospital.readValue(null);
        hospitalId = (Integer)hospital.readValue(null);
        nombre = (String)hospital.readValue(null);
        ciudad = (CityDto)hospital.readValue(CityDto.class.getClassLoader());
        direccion = (String)hospital.readValue(null);
        nivelComplejidadId = (Integer)hospital.readValue(null);
        nivelComplejidad = (ConceptDto)hospital.readValue(ConceptDto.class.getClassLoader());
        isSynchronized = (Boolean)hospital.readValue(null);
    }

    public HospitalDto(HospitalSpringDto hospital) {
        hospitalId = hospital.getHospitalId();
        nombre = hospital.getNombre();
        if(hospital.getCiudad() != null)
        ciudad = new CityDto(hospital.getCiudad());
        direccion = hospital.getDireccion();
        nivelComplejidad = new ConceptDto(hospital.getNivelComplejidad(), ConceptDto.TYPE_NIVEL_ID);
        this.isSynchronized = Boolean.TRUE;
    }

    /**
     * @return the hospitalId
     */
    public Integer getHospitalId() {
        return hospitalId;
    }
    /**
     * @param hospitalId the hospitalId to set
     */
    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * @return the ciudad
     */
    public CityDto getCiudad() {
        return ciudad;
    }
    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(CityDto ciudad) {
        this.ciudad = ciudad;
    }
    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }
    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    /**
     * @return the nivelComplejidad
     */
    public ConceptDto getNivelComplejidad() {
        return nivelComplejidad;
    }
    /**
     * @param nivelComplejidad the nivelComplejidad to set
     */
    public void setNivelComplejidad(ConceptDto nivelComplejidad) {
        this.nivelComplejidad = nivelComplejidad;
    }
    
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeValue(id);
        dest.writeValue(hospitalId);
        dest.writeValue(nombre);
        dest.writeValue(ciudad);
        dest.writeValue(direccion);
        dest.writeValue(nivelComplejidadId);
        dest.writeValue(nivelComplejidad);
        dest.writeValue(isSynchronized);
    }
}
