package com.artica.telesalud.tph.android.service;

import com.artica.telesalud.tph.android.activity.BaseActivity;

/**
 * Created by interoperabilidad on 23/05/14.
 */
public abstract class GetListAsyncService<T, U, V> extends AbstractAsyncService<T, U, V>{
    public GetListAsyncService(BaseActivity activity) {
        super(activity);
    }
    @Override
    protected void onPreExecute() {
    }

}
