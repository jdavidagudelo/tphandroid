package com.artica.telesalud.tph.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.artica.telesalud.tph.android.R;
import com.artica.telesalud.tph.android.fragment.AbstractPatientFragment;
import com.artica.telesalud.tph.android.lightweightmodel.LesionadoDto;
import com.artica.telesalud.tph.android.lightweightmodel.PatientIdentifierTypeDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by interoperabilidad on 5/09/14.
 */
public class DocumentTypeArrayAdapter extends ArrayAdapter<PatientIdentifierTypeDto> {
    public DocumentTypeArrayAdapter(Context context, int resource, List<PatientIdentifierTypeDto> objects, final AbstractPatientFragment abstractPatientFragment, final LesionadoDto lesionado) {
        super(context, resource, objects);
        this.abstractPatientFragment = abstractPatientFragment;
        this.lesionado = lesionado;
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for(final PatientIdentifierTypeDto concept : objects) {
            View convertView = inflater.inflate(R.layout.radio_button_item, null);
            RadioButton button = (RadioButton)convertView.findViewById(R.id.checkBoxItem);
            button.setText(concept.getName());
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (selected != null) {
                        selected.setChecked(Boolean.FALSE);
                        selected = null;
                        patientIdentifierTypeSelected = null;
                    }
                    selected = (RadioButton) v;
                    selected.setChecked(Boolean.TRUE);
                    patientIdentifierTypeSelected = concept;
                }
            });
            button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked) {
                             if (lesionado != null && lesionado.getEncuentro() != null && lesionado.getEncuentro().getPatient() != null &&
                                     lesionado.getEncuentro().getPatient().getPreferredIdentifier() != null) {
                                 lesionado.getEncuentro().getPatient().getPreferredIdentifier().setPatientIdentifierType(concept);
                                lesionado.getEncuentro().getPatient().getPreferredIdentifier().setPatientIdentifierTypeId(concept.getId());
                                if (abstractPatientFragment != null) {
                                    abstractPatientFragment.saveLesionado();
                                }
                            }

                    }
                }
            });
            button.setChecked(concept.getSelected() != null && concept.getSelected());
            if(concept.getSelected() != null && concept.getSelected())
            {
                if (selected != null) {
                    selected.setChecked(Boolean.FALSE);
                    selected = null;
                }
                selected = button;
            }
            views.add(convertView);
        }
    }
    private List<View> views = new ArrayList<View>();
    private RadioButton selected;
    private PatientIdentifierTypeDto patientIdentifierTypeSelected;
    private AbstractPatientFragment abstractPatientFragment;
    private LesionadoDto lesionado;
    public PatientIdentifierTypeDto getPatientIdentifierTypeSelected() {
        return patientIdentifierTypeSelected;
    }

    public LesionadoDto getLesionado() {
        return lesionado;
    }

    public void setLesionado(LesionadoDto lesionado) {
        this.lesionado = lesionado;
    }

    public AbstractPatientFragment getAbstractPatientFragment() {
        return abstractPatientFragment;
    }

    public void setAbstractPatientFragment(AbstractPatientFragment abstractPatientFragment) {
        this.abstractPatientFragment = abstractPatientFragment;
    }

    public void setPatientIdentifierTypeSelected(PatientIdentifierTypeDto patientIdentifierTypeSelected) {
        this.patientIdentifierTypeSelected = patientIdentifierTypeSelected;
    }

    private static class ViewHolder
    {
        public View radioButtonItem;
    }
    private void clearSelectedItem()
    {

        if (selected != null) {
            selected.setChecked(Boolean.FALSE);
            patientIdentifierTypeSelected.setSelected(Boolean.FALSE);
            selected = null;
            patientIdentifierTypeSelected = null;
        }
    }
    private void setSelectedItem(View v, int position)
    {
        if(v instanceof RadioButton) {
            clearSelectedItem();
            selected = (RadioButton) v;
            selected.setChecked(Boolean.TRUE);
            patientIdentifierTypeSelected = getItem(position);
            patientIdentifierTypeSelected.setSelected(Boolean.TRUE);
        }

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        return views.get(position);
    }

}
