package com.artica.telesalud.tph.android.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.artica.telesalud.tph.android.R;
import com.artica.telesalud.tph.android.lightweightmodel.CityDto;
import com.artica.telesalud.tph.android.lightweightmodel.StateDto;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by interoperabilidad on 26/08/14.
 */
public class PatientAddressFragment extends AbstractPatientFragment {
    public PatientAddressFragment()
    {

    }

    @Override
    public int getViewId() {
        return R.layout.fragment_patient_address;
    }

    @Override
    public int getFragmentTagId() {
        return R.string.fragment_domicilio_tag;
    }
    public void getStates() throws SQLException {
        Spinner spinner = (Spinner)view.findViewById(R.id.spinnerStatePatient);
        List<StateDto> states = stateService.getStatesDefault();
        final ArrayAdapter<StateDto> adapter = new ArrayAdapter<StateDto>(activity,
                android.R.layout.simple_spinner_item, states);
        spinner.setAdapter(adapter);
        final Spinner spinnerCities = (Spinner)view.findViewById(R.id.spinnerCityPatient);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                StateDto state = adapter.getItem(position);
                if(state != null && state.getId() != null)
                {
                    try {
                        List<CityDto> cities = cityService.getCitiesByState(state.getId());
                        ArrayAdapter<CityDto> adapterCities =  new ArrayAdapter<CityDto>(activity,
                                android.R.layout.simple_spinner_item, cities);
                        spinnerCities.setAdapter(adapterCities);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = super.onCreateView(inflater, container, savedInstanceState);
        View button = view.findViewById(R.id.buttonDomicilio);
        button.setActivated(Boolean.TRUE);
        try {
            getStates();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return view;
    }

}
