package com.artica.telesalud.tph.android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.artica.telesalud.tph.android.R;
import com.artica.telesalud.tph.android.activity.EvaluacionPatientActivity;
import com.artica.telesalud.tph.android.lightweightmodel.EvaluacionDto;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EmergenciaMedicaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EmergenciaMedicaFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class EmergenciaMedicaFragment extends AbstractEvaluacionFragment {

    private RadioButton selected = null;
    private EditText editTextOtraCausa;
    private SparseArray<RadioButton> hashMapEmergencias = new SparseArray<RadioButton>();
    public EmergenciaMedicaFragment() {
        // Required empty public constructor
    }
    public static EmergenciaMedicaFragment newInstance()
    {
        return new EmergenciaMedicaFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = super.onCreateView(inflater, container, savedInstanceState);
        if(view != null) {
            RadioButton radioButtonParoCardiaco = (RadioButton) view.findViewById(R.id.radioButtonParoCardiaco);
            RadioButton radioButtonGenitoUrinario = (RadioButton) view.findViewById(R.id.radioButtonGenitourinario);
            RadioButton radioButtonOvace = (RadioButton) view.findViewById(R.id.radioButtonOvace);
            RadioButton radioButtonNeurologica = (RadioButton) view.findViewById(R.id.radioButtonNeurologica);
            RadioButton radioButtonGinecoObstetrico = (RadioButton) view.findViewById(R.id.radioButtonGinecoObstetrico);
            RadioButton radioButtonTermica = (RadioButton) view.findViewById(R.id.radioButtonTermica);
            RadioButton radioButtonOrganosSentidos = (RadioButton) view.findViewById(R.id.radioButtonOrganosSentidos);
            RadioButton radioButtonEnfermedadComun = (RadioButton) view.findViewById(R.id.radioButtonEnfermedadComun);
            RadioButton radioButtonShock = (RadioButton) view.findViewById(R.id.radioButtonShock);
            RadioButton radioButtonCardiovascular = (RadioButton) view.findViewById(R.id.radioButtonCardiovascular);
            RadioButton radioButtonPsiquiatrica = (RadioButton) view.findViewById(R.id.radioButtonPsiquiatrica);
            RadioButton radioButtonIntoxicacion = (RadioButton) view.findViewById(R.id.radioButtonIntoxicacion);
            RadioButton radioButtonGastrointestinal = (RadioButton) view.findViewById(R.id.radioButtonGastroIntestinal);
            RadioButton radioButtonOtraEmergencia = (RadioButton) view.findViewById(R.id.radioButtonOtraEmergencia);
            editTextOtraCausa = (EditText)view.findViewById(R.id.editTextCualOtraEmergencia);
            editTextOtraCausa.setEnabled(Boolean.FALSE);
            editTextOtraCausa.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    evaluacion.setOtraEmergenciaMedica(getOtraEmergencia());
                    saveEvaluacion(evaluacion);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (selected != null) {
                        selected.setChecked(false);
                    }
                    selected = (RadioButton) v;
                    if(selected != null && evaluacion != null)
                    {

                        evaluacion.setTipoEmergencia(getTipoEmergencia(selected));
                        evaluacion.setOtraEmergenciaMedica(getOtraEmergencia());
                        saveEvaluacion(evaluacion);
                        selected.setChecked(true);
                        editTextOtraCausa.setEnabled(selected.getId() == R.id.radioButtonOtraEmergencia);

                    }
                }
            };
            radioButtonCardiovascular.setOnClickListener(listener);
            radioButtonParoCardiaco.setOnClickListener(listener);
            radioButtonGenitoUrinario.setOnClickListener(listener);
            radioButtonOvace.setOnClickListener(listener);
            radioButtonNeurologica.setOnClickListener(listener);
            radioButtonGinecoObstetrico.setOnClickListener(listener);
            radioButtonTermica.setOnClickListener(listener);
            radioButtonOrganosSentidos.setOnClickListener(listener);
            radioButtonEnfermedadComun.setOnClickListener(listener);
            radioButtonShock.setOnClickListener(listener);
            radioButtonPsiquiatrica.setOnClickListener(listener);
            radioButtonIntoxicacion.setOnClickListener(listener);
            radioButtonGastrointestinal.setOnClickListener(listener);
            radioButtonOtraEmergencia.setOnClickListener(listener);
            hashMapEmergencias.append(EvaluacionDto.EMERGENCIA_CARDIOVASCULAR, radioButtonCardiovascular);
            hashMapEmergencias.append(EvaluacionDto.EMERGENCIA_PARO_CARDIACO, radioButtonParoCardiaco);
            hashMapEmergencias.append(EvaluacionDto.EMERGENCIA_GENITOURINARIO, radioButtonGenitoUrinario);
            hashMapEmergencias.append(EvaluacionDto.EMERGENCIA_OVACE, radioButtonOvace);
            hashMapEmergencias.append(EvaluacionDto.EMERGENCIA_NEUROLOGIA, radioButtonNeurologica);
            hashMapEmergencias.append(EvaluacionDto.EMERGENCIA_GINECOOBSTETRICO, radioButtonGinecoObstetrico);
            hashMapEmergencias.append(EvaluacionDto.EMERGENCIA_TERMICA, radioButtonTermica);
            hashMapEmergencias.append(EvaluacionDto.EMERGENCIA_ORGANOS_SENTIDOS, radioButtonOrganosSentidos);
            hashMapEmergencias.append(EvaluacionDto.EMERGENCIA_ENFERMEDAD_COMUN, radioButtonEnfermedadComun);
            hashMapEmergencias.append(EvaluacionDto.EMERGENCIA_SHOCK, radioButtonShock);
            hashMapEmergencias.append(EvaluacionDto.EMERGENCIA_SIQUIATRICA, radioButtonPsiquiatrica);
            hashMapEmergencias.append(EvaluacionDto.EMERGENCIA_INTOXICACION, radioButtonIntoxicacion);
            hashMapEmergencias.append(EvaluacionDto.EMERGENCIA_GASTROINTESTINAL, radioButtonGastrointestinal);
            hashMapEmergencias.append(EvaluacionDto.OTRA_EMERGENCIA, radioButtonOtraEmergencia);
            for(int i = 0; i < hashMapEmergencias.size(); i++)
            {
                hashMapEmergencias.valueAt(i).setChecked(Boolean.FALSE);
            }
            updateEvaluacion(evaluacion);
        }
        return view;
    }
    public void savePreviousState(){
        if(getTipoEmergencia(selected) != null) {
            evaluacion.setTipoEmergencia(getTipoEmergencia(selected));
            if(getOtraEmergencia() != null) {
                evaluacion.setOtraEmergenciaMedica(getOtraEmergencia());
            }
            EvaluacionPatientActivity activity = (EvaluacionPatientActivity)getActivity();
            if(activity != null)
            {
                activity.setEvaluacionSelected(evaluacion);
            }
            saveEvaluacion(evaluacion);
        }
    }
    @Override
    public void updateEvaluacion(EvaluacionDto evaluacion)
    {
        if(evaluacion != null && hashMapEmergencias != null)
        {
            Integer emergencia = evaluacion.getTipoEmergencia();
            if(emergencia != null) {
                if(editTextOtraCausa != null)
                {
                    editTextOtraCausa.setText(evaluacion.getOtraEmergenciaMedica());
                    editTextOtraCausa.setEnabled(emergencia.equals(EvaluacionDto.OTRA_EMERGENCIA) );
                }
                RadioButton clicked = hashMapEmergencias.get(emergencia);
                if (selected != null) {
                    selected.setChecked(Boolean.FALSE);
                }
                selected = clicked;
                if (selected != null) {
                    selected.setChecked(Boolean.TRUE);
                }
            }
            else
            {
                if(selected != null)
                {
                    selected.setChecked(Boolean.FALSE);
                }
            }
        }
    }
    public Integer getTipoEmergencia(RadioButton selected)
    {
        if(selected != null) {
            int index = hashMapEmergencias.indexOfValue(selected);
            return hashMapEmergencias.keyAt(index);
        }
        return null;
    }
    public String getOtraEmergencia()
    {
        if(editTextOtraCausa != null)
        {
            return editTextOtraCausa.getText().toString();
        }
        return null;
    }
    public void onDestroyView ()
    {
        super.onDestroyView();
        savePreviousState();
    }
    @Override
    public int getViewId() {
        return R.layout.fragment_emergencia_medica;
    }

    @Override
    public int getFragmentTagId() {
        return R.string.fragment_emergencia_medica_tag;
    }

    @Override
    public void updateButtonSelected() {
        ImageButton button = (ImageButton)view.findViewById(R.id.imageButtonEmergenciaMedica);
        button.setActivated(Boolean.TRUE);
    }

}
