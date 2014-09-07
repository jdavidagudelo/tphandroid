package com.artica.telesalud.tph.android.security;

import com.artica.telesalud.tph.android.lightweightmodel.EventDto;

import org.joda.time.DateTime;

/**
 * Created by interoperabilidad on 15/07/14.
 */
public class EncryptionFactoryEvento implements EncryptionFactory<EventDto> {

    public EncryptionFactoryEvento()
    {

    }
    @Override
    public EventDto encrypt(EventDto eventDto) {
        if(eventDto != null)
        {
            DateTime date = new DateTime(eventDto.getCallDate());
            date.toString();
        }
        return null;
    }

    @Override
    public EventDto decrypt(EventDto eventDto) {
        return null;
    }
}
