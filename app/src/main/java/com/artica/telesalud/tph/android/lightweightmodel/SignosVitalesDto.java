package com.artica.telesalud.tph.android.lightweightmodel;

/**
 * Created by interoperabilidad on 25/08/14.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.artica.telesalud.tph.android.model.SignosVitalesSpringDto;
import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.Date;

public class SignosVitalesDto implements Parcelable, Serializable{
    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField()
    private Integer sequenceId;
    @DatabaseField()
    private Date fecha;
    @DatabaseField()
    private Double respiracion;
    @DatabaseField()
    private Double paDiastolica;
    @DatabaseField()
    private Double paSistolica;
    @DatabaseField()
    private Double glicemia;
    @DatabaseField()
    private Double pulso;
    @DatabaseField()
    private Double temperatura;
    @DatabaseField()
    private Double spo2;
    @DatabaseField()
    private Integer encounterId;
    private EncounterDto encounter;
    public static final Parcelable.Creator<SignosVitalesDto> CREATOR = new Parcelable.Creator<SignosVitalesDto>() {
        public SignosVitalesDto createFromParcel(Parcel in) {
            return new SignosVitalesDto(in);
        }

        public SignosVitalesDto[] newArray(int size) {
            return new SignosVitalesDto[size];
        }
    };
    public SignosVitalesDto(SignosVitalesSpringDto signosVitales)
    {
        fecha = signosVitales.getFecha();
        respiracion = signosVitales.getRespiracion();
        paDiastolica = signosVitales.getPaDiastolica();
        paSistolica = signosVitales.getPaSistolica();
        glicemia = signosVitales.getGlicemia();
        pulso = signosVitales.getPulso();
        temperatura = signosVitales.getTemperatura();
        spo2 = signosVitales.getSpo2();
        if(signosVitales.getEncounter() != null)
        {
            encounter = new EncounterDto(signosVitales.getEncounter());
        }
    }
    public SignosVitalesDto(Parcel parcel)
    {
        id = (Integer)parcel.readValue(null);
        fecha = (Date)parcel.readValue(null);
        respiracion = (Double)parcel.readValue(null);
        paDiastolica = (Double)parcel.readValue(null);
        paSistolica = (Double)parcel.readValue(null);
        glicemia = (Double)parcel.readValue(null);
        pulso = (Double)parcel.readValue(null);
        temperatura = (Double)parcel.readValue(null);
        spo2 = (Double)parcel.readValue(null);
        encounter = (EncounterDto)parcel.readValue(EncounterDto.class.getClassLoader());
        encounterId =(Integer)parcel.readValue(null);
        sequenceId = (Integer)parcel.readValue(null);
    }
    public SignosVitalesDto(){

    }

    public Double getPaMedia()
    {
        if(paDiastolica == null || paSistolica == null)
        {
            return null;
        }
        return (paSistolica + 2.0 * paDiastolica)/3.0;
    }
    /**
     * @return the encounter
     */
    public EncounterDto getEncounter() {
        return encounter;
    }
    /**
     * @param encounter the encounter to set
     */
    public void setEncounter(EncounterDto encounter) {
        this.encounter = encounter;
    }
    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }
    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    /**
     * @return the respiracion
     */
    public Double getRespiracion() {
        return respiracion;
    }
    /**
     * @param respiracion the respiracion to set
     */
    public void setRespiracion(Double respiracion) {
        this.respiracion = respiracion;
    }
    /**
     * @return the paDiastolica
     */
    public Double getPaDiastolica() {
        return paDiastolica;
    }
    /**
     * @param paDiastolica the paDiastolica to set
     */
    public void setPaDiastolica(Double paDiastolica) {
        this.paDiastolica = paDiastolica;
    }
    /**
     * @return the paSistolica
     */
    public Double getPaSistolica() {
        return paSistolica;
    }
    /**
     * @param paSistolica the paSistolica to set
     */
    public void setPaSistolica(Double paSistolica) {
        this.paSistolica = paSistolica;
    }
    /**
     * @return the glicemia
     */
    public Double getGlicemia() {
        return glicemia;
    }
    /**
     * @param glicemia the glicemia to set
     */
    public void setGlicemia(Double glicemia) {
        this.glicemia = glicemia;
    }
    /**
     * @return the pulso
     */
    public Double getPulso() {
        return pulso;
    }
    /**
     * @param pulso the pulso to set
     */
    public void setPulso(Double pulso) {
        this.pulso = pulso;
    }
    /**
     * @return the temperatura
     */
    public Double getTemperatura() {
        return temperatura;
    }
    /**
     * @param temperatura the temperatura to set
     */
    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }
    /**
     public SignosVitalesSpringDto(){
     * @return the spo2
     */
    public Double getSpo2() {
        return spo2;
    }
    /**
     * @param spo2 the spo2 to set
     */
    public void setSpo2(Double spo2) {
        this.spo2 = spo2;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(Integer sequenceId) {
        this.sequenceId = sequenceId;
    }

    public Integer getEncounterId() {
        return encounterId;
    }

    public void setEncounterId(Integer encounterId) {
        this.encounterId = encounterId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id );
        dest.writeValue(fecha );
        dest.writeValue(respiracion );
        dest.writeValue(paDiastolica );
        dest.writeValue(paSistolica );
        dest.writeValue(glicemia );
        dest.writeValue(pulso );
        dest.writeValue(temperatura );
        dest.writeValue(spo2);
        dest.writeValue(encounter );
        dest.writeValue(encounterId);
        dest.writeValue(sequenceId );
    }
}

