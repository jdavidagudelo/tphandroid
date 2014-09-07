package com.artica.telesalud.tph.android.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.artica.telesalud.tph.android.R;

/**
 * Created by interoperabilidad on 15/08/14.
 */
public class TriageYellowFragment extends Fragment {
    public TriageYellowFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.radio_group_triage_yellow, container, false);
        return view;
    }
}