package com.artica.telesalud.tph.android.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.artica.telesalud.tph.android.R;
import com.artica.telesalud.tph.android.lightweightmodel.ConceptDto;

import java.sql.SQLException;
import java.util.List;


public class AseguramientoFragment  extends AbstractPatientFragment{
    @Override
    public int getViewId() {
        return R.layout.fragment_aseguramiento;
    }

    @Override
    public int getFragmentTagId() {
        return R.string.fragment_aseguramiento_tag;
    }
    public void getEpss() throws SQLException {
        Spinner spinner = (Spinner)view.findViewById(R.id.spinnerEps);
        List<ConceptDto> concepts = conceptService.getEpss();
        ArrayAdapter<ConceptDto> adapter = new ArrayAdapter<ConceptDto>(activity,
                android.R.layout.simple_spinner_item, concepts);
        spinner.setAdapter(adapter);
    }
    public void getAseguradora() throws SQLException {
        Spinner spinner = (Spinner)view.findViewById(R.id.spinnerAseguradora);
        List<ConceptDto> concepts = conceptService.getAseguradoras();
        ArrayAdapter<ConceptDto> adapter = new ArrayAdapter<ConceptDto>(activity,
                android.R.layout.simple_spinner_item, concepts);
        spinner.setAdapter(adapter);
    }
    public void getPlanesBeneficios() throws SQLException {
        Spinner spinner = (Spinner)view.findViewById(R.id.spinnerPlanBeneficios);
        List<ConceptDto> concepts = conceptService.getPlanesBeneficios();
        ArrayAdapter<ConceptDto> adapter = new ArrayAdapter<ConceptDto>(activity,
                android.R.layout.simple_spinner_item, concepts);
        spinner.setAdapter(adapter);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = super.onCreateView(inflater, container, savedInstanceState);
        try {
            getEpss();
            getAseguradora();
            getPlanesBeneficios();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        View button = view.findViewById(R.id.buttonAseguramiento);
        button.setActivated(Boolean.TRUE);
        return view;
    }
}
