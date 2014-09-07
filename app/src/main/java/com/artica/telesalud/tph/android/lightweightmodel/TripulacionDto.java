package com.artica.telesalud.tph.android.lightweightmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.artica.telesalud.tph.android.model.TripulacionSpringDto;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by jonathan on 16/07/14.
 */
@DatabaseTable()
public class TripulacionDto implements Serializable, Parcelable{
    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField()
    private Integer tripulacionId;
    @DatabaseField()
    private Integer eventoId;
    private EventDto evento;
    @DatabaseField()
    private String placa;
    @DatabaseField()
    private Date horaDespacho;
    @DatabaseField()
    private Date horaLlegada;
    @DatabaseField()
    private Date horaRegreso;
    @DatabaseField()
    private Boolean isSynchronized = Boolean.FALSE;

    public static final Parcelable.Creator<TripulacionDto> CREATOR = new Parcelable.Creator<TripulacionDto>() {
        public TripulacionDto createFromParcel(Parcel in) {
            return new TripulacionDto(in);
        }

        public TripulacionDto[] newArray(int size) {
            return new TripulacionDto[size];
        }
    };

    public Integer getEventoId() {
        return eventoId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEventoId(Integer eventoId) {
        this.eventoId = eventoId;
    }

    public TripulacionDto(){

}
    public TripulacionDto(Parcel tripulacion) {
        id=(Integer)tripulacion.readValue(null);
        tripulacionId = (Integer)tripulacion.readValue(null);
        evento = (EventDto)tripulacion.readValue(EventDto.class.getClassLoader());
        placa = (String)tripulacion.readValue(null);
        horaDespacho = (Date) tripulacion.readValue(Date.class.getClassLoader());
        horaLlegada = (Date) tripulacion.readValue(Date.class.getClassLoader());
        horaRegreso = (Date) tripulacion.readValue(Date.class.getClassLoader());
        eventoId = (Integer)tripulacion.readValue(null);
    }

    public TripulacionDto(TripulacionSpringDto tripulacionSpringDto){
        tripulacionId = tripulacionSpringDto.getTripulacionId();
        evento = new EventDto(tripulacionSpringDto.getEvento());
        placa = tripulacionSpringDto.getPlaca();
        horaDespacho = tripulacionSpringDto.getHoraDespacho();
        horaLlegada = tripulacionSpringDto.getHoraLlegada();
        horaRegreso = tripulacionSpringDto.getHoraRegreso();
    }

    public Integer getTripulacionId() {
        return tripulacionId;
    }

    public void setTripulacionId(Integer tripulacionId) {
        this.tripulacionId = tripulacionId;
    }

    public EventDto getEvento() {
        return evento;
    }

    public void setEvento(EventDto evento) {
        this.evento = evento;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Date getHoraDespacho() {
        return horaDespacho;
    }

    public void setHoraDespacho(Date horaDespacho) {
        this.horaDespacho = horaDespacho;
    }

    public Date getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(Date horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public Date getHoraRegreso() {
        return horaRegreso;
    }

    public void setHoraRegreso(Date horaRegreso) {
        this.horaRegreso = horaRegreso;
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
        dest.writeValue(tripulacionId);
        dest.writeValue(evento);
        dest.writeValue(placa);
        dest.writeValue(horaDespacho);
        dest.writeValue(horaLlegada);
        dest.writeValue(horaRegreso);
        dest.writeValue(eventoId);
    }
}
