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
import com.artica.telesalud.tph.android.lightweightmodel.ConceptDto;
import com.artica.telesalud.tph.android.lightweightmodel.LesionadoDto;

import java.util.ArrayList;
import java.util.List;


public class ConceptArrayAdapter extends ArrayAdapter<ConceptDto> {

    public ConceptArrayAdapter(Context context, int textViewResourceId, List<ConceptDto> objects) {
        super(context, textViewResourceId, objects);
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for(final ConceptDto concept : objects) {
            View convertView = inflater.inflate(R.layout.radio_button_item, null);
            RadioButton button = (RadioButton)convertView.findViewById(R.id.checkBoxItem);
            button.setText(concept.getName());
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(radioButtonOther != null)
                    {
                        radioButtonOther.setChecked(Boolean.FALSE);
                    }
                    if (selected != null) {
                        selected.setChecked(Boolean.FALSE);
                        selected = null;
                        conceptSelected = null;
                    }
                    selected = (RadioButton) v;
                    selected.setChecked(Boolean.TRUE);
                    conceptSelected = concept;
                }
            });
            button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked) {
                        if (conceptType == ConceptType.MARITAL_STATUS) {
                            if (lesionado != null && lesionado.getEncuentro() != null && lesionado.getEncuentro().getPatient() != null) {
                                lesionado.getEncuentro().getPatient().setMaritalStatus(concept);
                                lesionado.getEncuentro().getPatient().setMaritalStatusId(concept.getId());
                                if (abstractPatientFragment != null) {
                                    abstractPatientFragment.saveLesionado();
                                }
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
    public enum ConceptType  {MARITAL_STATUS,
        ANTECEDENT_TYPE}
    private ConceptType conceptType = ConceptType.MARITAL_STATUS;
    private RadioButton selected;
    private ConceptDto conceptSelected;
    private RadioButton radioButtonOther;
    private List<View> views = new ArrayList<View>();

    public AbstractPatientFragment getAbstractPatientFragment() {
        return abstractPatientFragment;
    }

    public void setAbstractPatientFragment(AbstractPatientFragment abstractPatientFragment) {
        this.abstractPatientFragment = abstractPatientFragment;
    }

    public LesionadoDto getLesionado() {
        return lesionado;
    }

    public void setLesionado(LesionadoDto lesionado) {
        this.lesionado = lesionado;
    }

    public ConceptType getConceptType() {
        return conceptType;
    }

    public void setConceptType(ConceptType conceptType) {
        this.conceptType = conceptType;
    }

    private AbstractPatientFragment abstractPatientFragment;
    private LesionadoDto lesionado;



    public RadioButton getRadioButtonOther() {
        return radioButtonOther;
    }

    public void setRadioButtonOther(RadioButton radioButtonOther) {
        this.radioButtonOther = radioButtonOther;
        radioButtonOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelectedItem();
            }
        });
    }

    public ConceptDto getConceptSelected() {
        return conceptSelected;
    }

    public void setConceptSelected(ConceptDto conceptSelected) {
        this.conceptSelected = conceptSelected;
    }
    public void clearSelectedItem()
    {
        if (selected != null) {
            selected.setChecked(Boolean.FALSE);
            conceptSelected.setSelected(Boolean.FALSE);
            selected = null;
            conceptSelected = null;
        }
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        return views.get(position);

    }
}
