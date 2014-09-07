package com.artica.telesalud.tph.android.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.artica.telesalud.tph.android.R;
import com.artica.telesalud.tph.android.adapter.ConceptArrayAdapter;
import com.artica.telesalud.tph.android.lightweightmodel.ConceptDto;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by interoperabilidad on 26/08/14.
 */
public class AntecedentesPatientFragment extends AbstractPatientFragment{
    public AntecedentesPatientFragment() {
    }

    @Override
    public int getViewId() {
        return R.layout.fragment_antecedentes;
    }

    @Override
    public int getFragmentTagId() {
        return R.string.fragment_antecedentes_patient_tag;
    }
    public void getTiposAntecedentes() throws SQLException {
        GridView grid =(GridView)view.findViewById(R.id.gridViewAntecedentType);
        List<ConceptDto> antecedentTypes = conceptService.getTiposAntecedentes();
        ConceptArrayAdapter adapter = new ConceptArrayAdapter(activity,  R.id.tableRowSignosVitales, antecedentTypes);
        grid.setAdapter(adapter);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = super.onCreateView(inflater, container, savedInstanceState);
        View button = view.findViewById(R.id.buttonAntecedentes);
        button.setActivated(Boolean.TRUE);
        try {
            getTiposAntecedentes();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return view;
    }
}
