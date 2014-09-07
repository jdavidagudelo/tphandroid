package com.artica.telesalud.tph.android.lightweightmodel;

import com.artica.telesalud.tph.android.model.EventoSpringDto;

import java.util.ArrayList;

/**
 * Created by interoperabilidad on 26/05/14.
 */
public class ListEvents extends ArrayList<EventoSpringDto> {
    public ListEvents() {
        super();
    }
    private Boolean fromServer;

    public Boolean getFromServer() {
        return fromServer;
    }

    public void setFromServer(Boolean fromServer) {
        this.fromServer = fromServer;
    }
}
