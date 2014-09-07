package com.artica.telesalud.tph.android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.artica.telesalud.tph.android.activity.EvaluacionPatientActivity;

/**
 * Created by interoperabilidad on 26/08/14.
 */
public abstract class AbstractRemisionFragment extends Fragment {
    public abstract int getViewId();
    public abstract int getFragmentTagId();
    private View view;
    private EvaluacionPatientActivity activity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(getViewId(), container, false);
        activity = ((EvaluacionPatientActivity)getActivity());
        FragmentSelectorManager.initViewRemision(activity, view, activity.getString(getFragmentTagId()));
        return view;
    }
}
