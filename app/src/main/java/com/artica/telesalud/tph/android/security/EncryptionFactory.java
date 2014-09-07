package com.artica.telesalud.tph.android.security;

import com.artica.telesalud.tph.android.lightweightmodel.EventDto;

/**
 * Created by interoperabilidad on 15/07/14.
 */
public interface EncryptionFactory<T> {
    public T encrypt(T t);
    public T decrypt(T t);
}
