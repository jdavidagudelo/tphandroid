package com.artica.telesalud.tph.android.service;

import com.artica.telesalud.tph.android.activity.BaseActivity;

/**
 * Created by interoperabilidad on 23/05/14.
 */
public abstract class SaveAsyncService<T, U, V, W> extends AbstractAsyncService<T, U, V> {
    public SaveAsyncService(BaseActivity activity) {
        super(activity);
        this.activity = activity;
    }


}
