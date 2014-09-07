package com.artica.telesalud.tph.android.service;

import com.artica.telesalud.tph.android.activity.BaseActivity;
import com.artica.telesalud.tph.android.lightweightmodel.EventDto;
import com.artica.telesalud.tph.android.lightweightmodel.LesionadoDto;
import com.artica.telesalud.tph.android.model.EventoSpringDto;

import java.util.List;

/**
 * Created by interoperabilidad on 18/07/14.
 */
public class SavePatientService extends SaveAsyncService<LesionadoDto, Void, LesionadoDto, LesionadoDto>{

    public SavePatientService(BaseActivity activity) {
        super(activity);
    }

    @Override
    protected LesionadoDto doInBackground(LesionadoDto... params) {
        return null;
    }
}
