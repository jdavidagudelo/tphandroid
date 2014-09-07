package com.artica.telesalud.tph.android.lightweightmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.artica.telesalud.tph.android.model.EventoSpringDto;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by interoperabilidad on 21/05/14.
 */
@DatabaseTable()
public class EventDto implements Serializable, Parcelable{
    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField()
    private Integer eventId;
    public static final String ESTADO_ACTIVO = "Activo";
    public static final String ESTADO_FINALIZADO = "Finalizado";
    @DatabaseField()
    private String direccion;
    @DatabaseField()
    private Integer cityId;
    private CityDto city;
    @DatabaseField()
    private String zone;
    @DatabaseField()
    private Integer causaAtencionId;
    private ConceptDto causaAtencion;
    @DatabaseField()
    private String caseNumber;
    @DatabaseField()
    private Date callDate;
    @DatabaseField()
    private String state;
    @DatabaseField()
    private Date dateCreated;
    @DatabaseField()
    private Integer creator;
    @DatabaseField()
    private String coordinates;
    @DatabaseField()
    private Boolean isSynchronized = Boolean.FALSE;
    @DatabaseField()
    private String otherCause;
    @DatabaseField()
    private String cityName;
    @DatabaseField()
    private String stateName;
    private Integer patientsCount;

    public static final Parcelable.Creator<EventDto> CREATOR = new Parcelable.Creator<EventDto>() {
        public EventDto createFromParcel(Parcel in) {
            return new EventDto(in);
        }

        public EventDto[] newArray(int size) {
            return new EventDto[size];
        }
    };

    public Integer getPatientsCount() {
        return patientsCount;
    }

    public void setPatientsCount(Integer patientsCount) {
        this.patientsCount = patientsCount;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getCausaAtencionId() {
        return causaAtencionId;
    }

    public void setCausaAtencionId(Integer causaAtencionId) {
        this.causaAtencionId = causaAtencionId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getOtherCause() {
        return otherCause;
    }

    public void setOtherCause(String otherCause) {
        this.otherCause = otherCause;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public Boolean getIsSynchronized() {
        return isSynchronized;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public void setIsSynchronized(Boolean isSynchronized) {
        this.isSynchronized = isSynchronized;
    }

    public static final EventDto EMPTY_EVENT = new EventDto();

    public EventDto(Parcel evento) {
        id= (Integer)evento.readValue(null);
        coordinates = (String)evento.readValue(null);
        callDate = (Date)evento.readValue(null);
        caseNumber = (String)evento.readValue(null);
        causaAtencion = (ConceptDto)evento.readValue(ConceptDto.class.getClassLoader());
        city = (CityDto)evento.readValue(CityDto.class.getClassLoader());
        direccion = (String)evento.readValue(null);
        eventId = (Integer)evento.readValue(null);
        state = (String)evento.readValue(null);
        zone = (String)evento.readValue(null);
        isSynchronized =(Boolean)evento.readValue(null);
        otherCause = (String) evento.readValue(String.class.getClassLoader());
        stateName = (String)evento.readValue(null);
        cityName =(String)evento.readValue(null);
        creator =(Integer)evento.readValue(null);
        dateCreated = (Date)evento.readValue(null);
        cityId = (Integer)evento.readValue(null);
        causaAtencionId =(Integer)evento.readValue(null);
        patientsCount = (Integer)evento.readValue(null);

    }

    public EventDto(EventoSpringDto evento) {
        if(evento == null)
        {
            return;
        }
        this.callDate = evento.getFechaLlamada();
        this.caseNumber = evento.getNumeroCaso();
        if(evento.getCausaAtencion() != null)
        this.causaAtencion = new ConceptDto(evento.getCausaAtencion(), ConceptDto.TYPE_CAUSA_ATENCION_ID);
        if(evento.getCiudad() != null) {
            this.city = new CityDto(evento.getCiudad());
            cityName = evento.getCiudad().getName();
            if(city.getState() != null)
            {
                stateName = city.getState().getName();
            }
        }
        this.direccion = evento.getDireccion();
        this.eventId = evento.getEventoId();
        this.state = evento.getEstado();
        if(evento.getZona() != null) {
            this.zone = evento.getZona().getShortName();
        }
        this.isSynchronized = Boolean.TRUE;
        this.coordinates = evento.getCoordinates();
        this.otherCause = evento.getOtherCause();
        creator = evento.getCreator();
        dateCreated = evento.getDateCreated();
    }
    public String[] getParamKeys()
    {
        return new String[]{
                String.valueOf(creator)
        };
    }
    public EventDto() {
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public CityDto getCity() {
        return city;
    }

    public void setCity(CityDto city) {
        this.city = city;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public ConceptDto getCausaAtencion() {
        return causaAtencion;
    }

    public void setCausaAtencion(ConceptDto causaAtencion) {
        this.causaAtencion = causaAtencion;
    }

    public String getCaseNumber() {
        if(caseNumber == null || caseNumber.isEmpty())
        {
            return String.valueOf("Tp"+id);
        }
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCallDate() {
        return callDate;
    }

    public void setCallDate(Date callDate) {
        this.callDate = callDate;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventDto)) return false;

        EventDto eventDto = (EventDto) o;

        if (callDate != null ? !callDate.equals(eventDto.callDate) : eventDto.callDate != null)
            return false;
        if (caseNumber != null ? !caseNumber.equals(eventDto.caseNumber) : eventDto.caseNumber != null)
            return false;
        if (causaAtencion != null ? !causaAtencion.equals(eventDto.causaAtencion) : eventDto.causaAtencion != null)
            return false;
        if (city != null ? !city.equals(eventDto.city) : eventDto.city != null) return false;
        if (coordinates != null ? !coordinates.equals(eventDto.coordinates) : eventDto.coordinates != null)
            return false;
        if (creator != null ? !creator.equals(eventDto.creator) : eventDto.creator != null)
            return false;
        if (dateCreated != null ? !dateCreated.equals(eventDto.dateCreated) : eventDto.dateCreated != null)
            return false;
        if (direccion != null ? !direccion.equals(eventDto.direccion) : eventDto.direccion != null)
            return false;
        if (eventId != null ? !eventId.equals(eventDto.eventId) : eventDto.eventId != null)
            return false;
        if (isSynchronized != null ? !isSynchronized.equals(eventDto.isSynchronized) : eventDto.isSynchronized != null)
            return false;
        if (otherCause != null ? !otherCause.equals(eventDto.otherCause) : eventDto.otherCause != null)
            return false;
        if (state != null ? !state.equals(eventDto.state) : eventDto.state != null) return false;
        if (zone != null ? !zone.equals(eventDto.zone) : eventDto.zone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = eventId != null ? eventId.hashCode() : 0;
        result = 31 * result + (direccion != null ? direccion.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (zone != null ? zone.hashCode() : 0);
        result = 31 * result + (causaAtencion != null ? causaAtencion.hashCode() : 0);
        result = 31 * result + (caseNumber != null ? caseNumber.hashCode() : 0);
        result = 31 * result + (callDate != null ? callDate.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = 31 * result + (coordinates != null ? coordinates.hashCode() : 0);
        result = 31 * result + (isSynchronized != null ? isSynchronized.hashCode() : 0);
        result = 31 * result + (otherCause != null ? otherCause.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int args) {
        parcel.writeValue(id);
        parcel.writeValue(coordinates);
        parcel.writeValue(callDate);
        parcel.writeValue(caseNumber);
        parcel.writeValue(causaAtencion);
        parcel.writeValue(city);
        parcel.writeValue(direccion);
        parcel.writeValue(eventId);
        parcel.writeValue(state);
        parcel.writeValue(zone);
        parcel.writeValue(isSynchronized);
        parcel.writeValue(otherCause);
        parcel.writeValue(stateName);
        parcel.writeValue(cityName);
        parcel.writeValue(creator);
        parcel.writeValue(dateCreated);
        parcel.writeValue(cityId);
        parcel.writeValue(causaAtencionId);
        parcel.writeValue(patientsCount);
    }

    public void setupId(EventDto event)
    {
        id = event.getId();
    }
}
