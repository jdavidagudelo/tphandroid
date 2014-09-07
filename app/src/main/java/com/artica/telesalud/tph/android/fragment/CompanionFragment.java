package com.artica.telesalud.tph.android.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.artica.telesalud.tph.android.R;

/**
 * Created by interoperabilidad on 26/08/14.
 */
public class CompanionFragment extends AbstractPatientFragment {
    public CompanionFragment()
    {

    }
    @Override
    public int getViewId() {
        return R.layout.fragment_companion;
    }

    @Override
    public int getFragmentTagId() {
        return R.string.fragment_companion_tag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        View button = view.findViewById(R.id.buttonCompanion);
        button.setActivated(Boolean.TRUE);
        return view;
    }
}
