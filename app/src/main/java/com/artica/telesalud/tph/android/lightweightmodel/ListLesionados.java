package com.artica.telesalud.tph.android.lightweightmodel;

import com.artica.telesalud.tph.android.model.LesionadoSpringDto;
import com.artica.telesalud.tph.android.model.PatientSpringDto;

import java.util.ArrayList;

/**
 * Created by interoperabilidad on 17/07/14.
 */
public class ListLesionados extends ArrayList<LesionadoSpringDto> {
    private Boolean fromServer;
    private Integer eventId;
    public Boolean getFromServer() {
        return fromServer;
    }

    public void setFromServer(Boolean fromServer) {
        this.fromServer = fromServer;
    }

    public ListLesionados()
    {
        super();
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }
}
