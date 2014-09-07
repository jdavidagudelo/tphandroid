package com.artica.telesalud.tph.android.model;

import com.artica.telesalud.tph.android.lightweightmodel.ConceptDto;
import com.artica.telesalud.tph.android.lightweightmodel.EventDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;


public class EventoSpringDto implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public static String ESTADO_ACTIVO = "Activo";
    public static String ESTADO_FINALIZADO = "Finalizado";
    @JsonIgnore
    private Integer localId;
    private Integer eventoId;
    private String coordinates;
    private String direccion;
    private CitySpringDto ciudad;
    private ConceptSpringDto zona;
    private ConceptSpringDto causaAtencion;
    private String numeroCaso;
    private Date fechaLlamada;
    private String estado;
    private String otherCause;
    private EventAddressSpringDto eventAddress;

    private Integer creator;
    private Date dateCreated;

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    } public EventoSpringDto(EventDto event)
    {
        localId = event.getEventId();
        eventoId = event.getEventId();
        coordinates = event.getCoordinates();
        direccion = event.getDireccion();
        if(event.getCity() != null) {
            ciudad = new CitySpringDto(event.getCity());
        }
        ConceptDto zone = new ConceptDto();
        zone.setName(event.getZone());
        zona = new ConceptSpringDto(zone);
        if(event.getCausaAtencion() != null) {
            causaAtencion = new ConceptSpringDto(event.getCausaAtencion());
        }
        numeroCaso = event.getCaseNumber();
        fechaLlamada = event.getCallDate();
        estado = event.getState();
        otherCause = event.getOtherCause();
    }

    public EventAddressSpringDto getEventAddress() {
        return eventAddress;
    }

    public void setEventAddress(EventAddressSpringDto eventAddress) {
        this.eventAddress = eventAddress;
    }

    public String getOtherCause() {
        return otherCause;
    }

    public void setOtherCause(String otherCause) {
        this.otherCause = otherCause;
    }

    public Integer getLocalId() {
        return localId;
    }

    public void setLocalId(Integer localId) {
        this.localId = localId;
    }

    /**
     * @return the coordinates
     */
    public String getCoordinates() {
        return coordinates;
    }

    /**
     * @param coordinates the coordinates to set
     */
    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public EventoSpringDto() {
        super();
    }

    public Integer getEventoId() {
        return eventoId;
    }
    public void setEventoId(Integer eventoId) {
        this.eventoId = eventoId;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public CitySpringDto getCiudad() {
        return ciudad;
    }
    public void setCiudad(CitySpringDto ciudad) {
        this.ciudad = ciudad;
    }
    public ConceptSpringDto getZona() {
        return zona;
    }
    public void setZona(ConceptSpringDto zona) {
        this.zona = zona;
    }
    public ConceptSpringDto getCausaAtencion() {
        return causaAtencion;
    }
    public void setCausaAtencion(ConceptSpringDto causaAtencion) {
        this.causaAtencion = causaAtencion;
    }
    public String getNumeroCaso() {
        return numeroCaso;
    }
    public void setNumeroCaso(String numeroCaso) {
        this.numeroCaso = numeroCaso;
    }
    public Date getFechaLlamada() {
        return fechaLlamada;
    }
    public void setFechaLlamada(Date fechaLlamada) {
        this.fechaLlamada = fechaLlamada;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "EventoSpringDto [eventoId=" + eventoId + ", direccion="
                + direccion + ", ciudad=" + ciudad + ", zona=" + zona
                + ", causaAtencion=" + causaAtencion + ", numeroCaso="
                + numeroCaso + ", fechaLlamada=" + fechaLlamada + ", estado="
                + estado + "]";
    }



}
