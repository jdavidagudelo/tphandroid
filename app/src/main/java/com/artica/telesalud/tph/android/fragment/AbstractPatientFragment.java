package com.artica.telesalud.tph.android.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.artica.telesalud.tph.android.activity.EvaluacionPatientActivity;
import com.artica.telesalud.tph.android.dao.CityService;
import com.artica.telesalud.tph.android.dao.ConceptService;
import com.artica.telesalud.tph.android.dao.LesionadoService;
import com.artica.telesalud.tph.android.dao.PatientIdentifierTypeService;
import com.artica.telesalud.tph.android.dao.StateService;
import com.artica.telesalud.tph.android.lightweightmodel.LesionadoDto;

import java.sql.SQLException;

/**
 * Created by interoperabilidad on 26/08/14.
 */
public abstract class AbstractPatientFragment extends Fragment{

    public abstract int getViewId();
    public abstract int getFragmentTagId();
    protected View view;
    protected LesionadoDto lesionado;
    protected PatientIdentifierTypeService patientIdentifierTypeService;
    protected ConceptService conceptService;
    protected EvaluacionPatientActivity activity;
    protected CityService cityService;
    protected StateService stateService;
    protected LesionadoService lesionadoService;
    public void saveLesionado()
    {
        new SaveService().execute();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(getViewId(), container, false);
        activity = ((EvaluacionPatientActivity)getActivity());
        lesionado = activity.getLesionado();
        try {
            patientIdentifierTypeService = new PatientIdentifierTypeService(activity.getHelper());
            conceptService = new ConceptService(activity.getHelper());
            stateService=new StateService(activity.getHelper());
            cityService = new CityService(activity.getHelper());
            lesionadoService = new LesionadoService(activity.getHelper());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        FragmentSelectorManager.initViewAntecedentes(activity, view, activity.getString(getFragmentTagId()));
        return view;
    }
    private class SaveService extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                lesionadoService.save(lesionado);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
