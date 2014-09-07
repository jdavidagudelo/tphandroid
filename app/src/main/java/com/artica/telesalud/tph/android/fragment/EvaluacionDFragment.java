package com.artica.telesalud.tph.android.fragment;



import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.artica.telesalud.tph.android.R;
import com.artica.telesalud.tph.android.lightweightmodel.EvaluacionDto;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class EvaluacionDFragment extends AbstractEvaluacionFragment {
    private EditText editTextRespuestaOcular;
    private EditText editTextRespuestaVerbal;
    private EditText editTextRespuestaMotora;
    private RadioButton radioButtonPupilasSizeNormales;
    private RadioButton radioButtonPupilasSizeMidriaticas ;
    private RadioButton radioButtonPupilasSizeMioticas ;
    private RadioButton radioButtonPupilasReactivas ;
    private RadioButton radioButtonPupilasNoReactivas;
    private RadioButton radioButtonPupilasNoEvaluables ;
    private RadioButton radioButtonPupilasIsocoricas ;
    private RadioButton radioButtonPupilasAnisocoricas;

    private EditText editTextTotal;
    private RadioButton pupilasSizeSelected;
    private RadioButton pupilasReactionSelected;
    private RadioButton pupilasSymmetrySelected;
    private RadioButton respuestaOcularSelected;
    private RadioButton respuestaVerbalSelected;
    private RadioButton respuestaMotoraSelected;
    private SparseArray<RadioButton> hashMapRespuestaOcular = new SparseArray<RadioButton>();
    private SparseArray<RadioButton> hashMapRespuestaVerbal = new SparseArray<RadioButton>();
    private SparseArray<RadioButton> hashMapRespuestaMotora = new SparseArray<RadioButton>();

    public EvaluacionDFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = super.onCreateView(inflater, container, savedInstanceState);
        editTextRespuestaMotora = (EditText)view.findViewById(R.id.editTextGlasgowMotora);
        editTextRespuestaOcular = (EditText)view.findViewById(R.id.editTextGlasgowOcular);
        editTextRespuestaVerbal = (EditText)view.findViewById(R.id.editTextGlasgowVerbal);
        editTextTotal = (EditText)view.findViewById(R.id.editTextGlasgowTotal);
        RadioButton radioButtonRespuestaOcular1 = (RadioButton)view.findViewById(R.id.radioButtonRespuestaOcularNoRespuesta);
        RadioButton radioButtonRespuestaOcular2 = (RadioButton)view.findViewById(R.id.radioButtonRespuestaOcularRespuestaDolor);
        RadioButton radioButtonRespuestaOcular3 = (RadioButton)view.findViewById(R.id.radioButtonRespuestaOcularRespuestaLlamado);
        RadioButton radioButtonRespuestaOcular4 = (RadioButton)view.findViewById(R.id.radioButtonRespuestaOcularAbreOjos);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v instanceof RadioButton) {
                    if (respuestaOcularSelected != null) {
                        respuestaOcularSelected.setChecked(Boolean.FALSE);
                    }
                    respuestaOcularSelected = (RadioButton) v;
                    respuestaOcularSelected.setChecked(Boolean.TRUE);
                    new EditTextUpdate().execute();
                }
            }
        };

        radioButtonRespuestaOcular1.setOnClickListener(listener);
        radioButtonRespuestaOcular2.setOnClickListener(listener);
        radioButtonRespuestaOcular3.setOnClickListener(listener);
        radioButtonRespuestaOcular4.setOnClickListener(listener);
        hashMapRespuestaOcular.append(EvaluacionDto.RESPUESTA_OCULAR1, radioButtonRespuestaOcular1);
        hashMapRespuestaOcular.append(EvaluacionDto.RESPUESTA_OCULAR2, radioButtonRespuestaOcular2);
        hashMapRespuestaOcular.append(EvaluacionDto.RESPUESTA_OCULAR3, radioButtonRespuestaOcular3);
        hashMapRespuestaOcular.append(EvaluacionDto.RESPUESTA_OCULAR4, radioButtonRespuestaOcular4);
        RadioButton radioButtonRespuestaVerbal1 = (RadioButton)view.findViewById(R.id.radioButtonRespuestaVerbalNoRespuesta);
        RadioButton radioButtonRespuestaVerbal2 = (RadioButton)view.findViewById(R.id.radioButtonRespuestaVerbalSonidoIncomprensible);
        RadioButton radioButtonRespuestaVerbal3 = (RadioButton)view.findViewById(R.id.radioButtonRespuestaVerbalHablaInapropiada);
        RadioButton radioButtonRespuestaVerbal4 = (RadioButton)view.findViewById(R.id.radioButtonRespuestaVerbalConversacionConfusa);
        RadioButton radioButtonRespuestaVerbal5 = (RadioButton)view.findViewById(R.id.radioButtonRespuestaVerbalUbicada);
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v instanceof RadioButton) {
                    if (respuestaVerbalSelected != null) {
                        respuestaVerbalSelected.setChecked(Boolean.FALSE);
                    }
                    respuestaVerbalSelected = (RadioButton) v;
                    respuestaVerbalSelected.setChecked(Boolean.TRUE);
                    new EditTextUpdate().execute();
                }
            }
        };
        radioButtonRespuestaVerbal1.setOnClickListener(listener);
        radioButtonRespuestaVerbal2.setOnClickListener(listener);
        radioButtonRespuestaVerbal3.setOnClickListener(listener);
        radioButtonRespuestaVerbal4.setOnClickListener(listener);
        radioButtonRespuestaVerbal5.setOnClickListener(listener);
        hashMapRespuestaVerbal.append(EvaluacionDto.RESPUESTA_VERBAL1, radioButtonRespuestaVerbal1);
        hashMapRespuestaVerbal.append(EvaluacionDto.RESPUESTA_VERBAL2, radioButtonRespuestaVerbal2);
        hashMapRespuestaVerbal.append(EvaluacionDto.RESPUESTA_VERBAL3, radioButtonRespuestaVerbal3);
        hashMapRespuestaVerbal.append(EvaluacionDto.RESPUESTA_VERBAL4, radioButtonRespuestaVerbal4);
        hashMapRespuestaVerbal.append(EvaluacionDto.RESPUESTA_VERBAL5, radioButtonRespuestaVerbal5);

        RadioButton radioButtonRespuestaMotora1 = (RadioButton)view.findViewById(R.id.radioButtonRespuestaMotoraNoRespuesta);
        RadioButton radioButtonRespuestaMotora2 = (RadioButton)view.findViewById(R.id.radioButtonRespuestaMotoraRespuestaExtensora);
        RadioButton radioButtonRespuestaMotora3 = (RadioButton)view.findViewById(R.id.radioButtonRespuestaMotoraRespuestaFlexora);
        RadioButton radioButtonRespuestaMotora4 = (RadioButton)view.findViewById(R.id.radioButtonRespuestaMotoraRespuestaRetirada);
        RadioButton radioButtonRespuestaMotora5 = (RadioButton)view.findViewById(R.id.radioButtonRespuestaMotoraLocalizaDolor);
        RadioButton radioButtonRespuestaMotora6 = (RadioButton)view.findViewById(R.id.radioButtonRespuestaMotoraObedeceOrdenes);
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v instanceof RadioButton) {
                    if (respuestaMotoraSelected != null) {
                        respuestaMotoraSelected.setChecked(Boolean.FALSE);
                    }
                    respuestaMotoraSelected = (RadioButton) v;
                    respuestaMotoraSelected.setChecked(Boolean.TRUE);
                    new EditTextUpdate().execute();
                }
            }
        };
        radioButtonRespuestaMotora1.setOnClickListener(listener);
        radioButtonRespuestaMotora2.setOnClickListener(listener);
        radioButtonRespuestaMotora3.setOnClickListener(listener);
        radioButtonRespuestaMotora4.setOnClickListener(listener);
        radioButtonRespuestaMotora5.setOnClickListener(listener);
        radioButtonRespuestaMotora6.setOnClickListener(listener);
        hashMapRespuestaMotora.append(EvaluacionDto.RESPUESTA_MOTORA1, radioButtonRespuestaMotora1);
        hashMapRespuestaMotora.append(EvaluacionDto.RESPUESTA_MOTORA2, radioButtonRespuestaMotora2);
        hashMapRespuestaMotora.append(EvaluacionDto.RESPUESTA_MOTORA3, radioButtonRespuestaMotora3);
        hashMapRespuestaMotora.append(EvaluacionDto.RESPUESTA_MOTORA4, radioButtonRespuestaMotora4);
        hashMapRespuestaMotora.append(EvaluacionDto.RESPUESTA_MOTORA5, radioButtonRespuestaMotora5);
        hashMapRespuestaMotora.append(EvaluacionDto.RESPUESTA_MOTORA6, radioButtonRespuestaMotora6);
        radioButtonPupilasSizeNormales = (RadioButton)view.findViewById(R.id.radioButtonPupilasNormales);
        radioButtonPupilasSizeMidriaticas = (RadioButton)view.findViewById(R.id.radioButtonPupilasMidriaticas);
        radioButtonPupilasSizeMioticas = (RadioButton)view.findViewById(R.id.radioButtonPupilasMioticas);
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v instanceof RadioButton)
                {
                    if(pupilasSizeSelected != null)
                    {
                        pupilasSizeSelected.setChecked(Boolean.FALSE);
                    }
                    pupilasSizeSelected = (RadioButton)v;
                    pupilasSizeSelected.setChecked(Boolean.TRUE);
                }
            }
        };
        radioButtonPupilasSizeMioticas.setOnClickListener(listener);
        radioButtonPupilasSizeMidriaticas.setOnClickListener(listener);
        radioButtonPupilasSizeNormales.setOnClickListener(listener);
        radioButtonPupilasSizeMioticas.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setPupilasMioticas(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        radioButtonPupilasSizeMidriaticas.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setPupilasMidriaticas(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        radioButtonPupilasSizeNormales.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setPupilasNormales(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
       radioButtonPupilasReactivas = (RadioButton)view.findViewById(R.id.radioButtonPupilasReactivas);
        radioButtonPupilasNoReactivas = (RadioButton)view.findViewById(R.id.radioButtonPupilasNoReactivas);
        radioButtonPupilasNoEvaluables = (RadioButton)view.findViewById(R.id.radioButtonPupilasNoEvaluables);
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v instanceof RadioButton)
                {
                    if(pupilasReactionSelected != null)
                    {
                        pupilasReactionSelected.setChecked(Boolean.FALSE);
                    }
                    pupilasReactionSelected = (RadioButton)v;
                    pupilasReactionSelected.setChecked(Boolean.TRUE);
                }
            }
        };
        radioButtonPupilasReactivas.setOnClickListener(listener);
        radioButtonPupilasNoReactivas.setOnClickListener(listener);
        radioButtonPupilasNoEvaluables.setOnClickListener(listener);
        radioButtonPupilasReactivas.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setPupilasReactivas(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        radioButtonPupilasNoReactivas.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setPupilasNoReactivas(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        radioButtonPupilasNoEvaluables.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setPupilasNoEvaluables(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        radioButtonPupilasIsocoricas = (RadioButton)view.findViewById(R.id.radioButtonPupilasSimetriaIsocorica);
        radioButtonPupilasAnisocoricas = (RadioButton)view.findViewById(R.id.radioButtonPupilasSimetriaAnisocorica);
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v instanceof RadioButton)
                {
                    if(pupilasSymmetrySelected != null)
                    {
                        pupilasSymmetrySelected.setChecked(Boolean.FALSE);
                    }
                    pupilasSymmetrySelected = (RadioButton)v;
                    pupilasSymmetrySelected.setChecked(Boolean.TRUE);
                }
            }
        };
        radioButtonPupilasAnisocoricas.setOnClickListener(listener);
        radioButtonPupilasIsocoricas.setOnClickListener(listener);
        radioButtonPupilasAnisocoricas.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setPupilasAnisocoricas(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        radioButtonPupilasIsocoricas.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setPupilasIsocoricas(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        updateEvaluacion(evaluacion);
        return view;
    }
    public Integer getTotal()
    {
        Integer total = 0;
        if(editTextRespuestaOcular != null && editTextRespuestaOcular.getText().toString() != null && !editTextRespuestaOcular.getText().toString().isEmpty())
        {
            total += Integer.parseInt(editTextRespuestaOcular.getText().toString());
        }
        if(editTextRespuestaVerbal != null && editTextRespuestaVerbal.getText().toString() != null && !editTextRespuestaVerbal.getText().toString().isEmpty())
        {
            total += Integer.parseInt(editTextRespuestaVerbal.getText().toString());
        }
        if(editTextRespuestaMotora != null && editTextRespuestaMotora.getText().toString() != null && !editTextRespuestaMotora.getText().toString().isEmpty())
        {
            total += Integer.parseInt(editTextRespuestaMotora.getText().toString());
        }
        return total;
    }
    public Integer getRespuestaMotora(RadioButton selected)
    {
        if(selected != null)
        {
            int index = hashMapRespuestaMotora.indexOfValue(selected);
            return hashMapRespuestaMotora.keyAt(index);
        }
        return null;
    }
    public Integer getRespuestaVerbal(RadioButton selected)
    {
        if(selected != null)
        {
            int index = hashMapRespuestaVerbal.indexOfValue(selected);
            return hashMapRespuestaVerbal.keyAt(index);
        }
        return null;
    }
    public Integer getRespuestaOcular(RadioButton selected)
    {
        if(selected != null)
        {
            int index = hashMapRespuestaOcular.indexOfValue(selected);
            return hashMapRespuestaOcular.keyAt(index);
        }
        return null;
    }
    @Override
    public int getViewId() {
        return R.layout.fragment_evaluacion_d;
    }

    @Override
    public int getFragmentTagId() {
        return R.string.fragment_evaluacion_d_tag;
    }

    public void clear()
    {

        for(int i = 0; i < hashMapRespuestaMotora.size(); i++)
        {
            hashMapRespuestaMotora.valueAt(i).setChecked(Boolean.FALSE);
        }
        for(int i = 0; i < hashMapRespuestaOcular.size(); i++) {
            hashMapRespuestaOcular.valueAt(i).setChecked(Boolean.FALSE);
        }
        for(int i = 0; i < hashMapRespuestaVerbal.size(); i++) {
            hashMapRespuestaVerbal.valueAt(i).setChecked(Boolean.FALSE);
        }
    }
    public void updateEvaluacion(final EvaluacionDto evaluacion){
        clear();
        if(evaluacion != null)
        {
            Integer ro = evaluacion.getGlasgowRO();
            Integer rv = evaluacion.getGlasgowRV();
            Integer rm = evaluacion.getGlasgowRM();
            if(rm != null)
            {
                if(editTextRespuestaMotora != null)
                {
                    editTextRespuestaMotora.setText(String.valueOf(rm));
                }
                if(hashMapRespuestaMotora != null)
                {
                    if(respuestaMotoraSelected != null)
                    {
                        respuestaMotoraSelected.setChecked(Boolean.FALSE);
                    }
                    respuestaMotoraSelected = hashMapRespuestaMotora.get(rm);
                    if(respuestaMotoraSelected != null)
                    {
                        respuestaMotoraSelected.setChecked(Boolean.TRUE);
                    }
                }
            }
            if(ro != null)
            {
                if(editTextRespuestaOcular != null)
                {
                    editTextRespuestaOcular.setText(String.valueOf(ro));
                }
                if(hashMapRespuestaOcular != null)
                {
                    if(respuestaOcularSelected != null)
                    {
                        respuestaOcularSelected.setChecked(Boolean.FALSE);
                    }
                    respuestaOcularSelected = hashMapRespuestaOcular.get(ro);
                    if(respuestaOcularSelected != null)
                    {
                        respuestaOcularSelected.setChecked(Boolean.TRUE);
                    }
                }
            }
            if(rv != null)
            {
                if(editTextRespuestaVerbal != null)
                {
                    editTextRespuestaVerbal.setText(String.valueOf(rv));
                }
                if(hashMapRespuestaVerbal != null)
                {
                    if(respuestaVerbalSelected != null)
                    {
                        respuestaVerbalSelected.setChecked(Boolean.FALSE);
                    }
                    respuestaVerbalSelected = hashMapRespuestaVerbal.get(rv);
                    if(respuestaVerbalSelected != null)
                    {
                        respuestaVerbalSelected.setChecked(Boolean.TRUE);
                    }
                }
            }
            if(radioButtonPupilasAnisocoricas != null && evaluacion.getPupilasAnisocoricas() != null)
            {
                radioButtonPupilasAnisocoricas.setChecked(evaluacion.getPupilasAnisocoricas());
                if(evaluacion.getPupilasAnisocoricas())
                {
                    if(pupilasSymmetrySelected!= null)
                    {
                        pupilasSymmetrySelected.setChecked(Boolean.FALSE);
                    }
                    pupilasSymmetrySelected = radioButtonPupilasAnisocoricas;
                    pupilasSymmetrySelected.setChecked(Boolean.TRUE);
                }
            }
            if(radioButtonPupilasIsocoricas != null && evaluacion.getPupilasIsocoricas() != null)
            {
                radioButtonPupilasIsocoricas.setChecked(evaluacion.getPupilasIsocoricas());
                if(evaluacion.getPupilasIsocoricas())
                {
                    if(pupilasSymmetrySelected!= null)
                    {
                        pupilasSymmetrySelected.setChecked(Boolean.FALSE);
                    }
                    pupilasSymmetrySelected = radioButtonPupilasIsocoricas;
                    pupilasSymmetrySelected.setChecked(Boolean.TRUE);
                }
            }
            if(radioButtonPupilasSizeNormales != null && evaluacion.getPupilasNormales() != null)
            {
                radioButtonPupilasSizeNormales.setChecked(evaluacion.getPupilasNormales());
                if(evaluacion.getPupilasNormales())
                {
                    if(pupilasSizeSelected != null)
                    {
                        pupilasSizeSelected.setChecked(Boolean.FALSE);
                    }
                    pupilasSizeSelected = radioButtonPupilasSizeNormales;
                    pupilasSizeSelected.setChecked(Boolean.TRUE);
                }
            }
            if(radioButtonPupilasSizeMidriaticas != null && evaluacion.getPupilasMidriaticas() != null)
            {
                radioButtonPupilasSizeMidriaticas.setChecked(evaluacion.getPupilasMidriaticas());
                if(evaluacion.getPupilasMidriaticas())
                {
                    if(pupilasSizeSelected != null)
                    {
                        pupilasSizeSelected.setChecked(Boolean.FALSE);
                    }
                    pupilasSizeSelected = radioButtonPupilasSizeMidriaticas;
                    pupilasSizeSelected.setChecked(Boolean.TRUE);
                }
            }
            if(radioButtonPupilasSizeMioticas != null && evaluacion.getPupilasMioticas() != null)
            {
                radioButtonPupilasSizeMioticas.setChecked(evaluacion.getPupilasMioticas());
                if(evaluacion.getPupilasMioticas())
                {
                    if(pupilasSizeSelected != null)
                    {
                        pupilasSizeSelected.setChecked(Boolean.FALSE);
                    }
                    pupilasSizeSelected = radioButtonPupilasSizeMioticas;
                    pupilasSizeSelected.setChecked(Boolean.TRUE);
                }
            }
            if(radioButtonPupilasReactivas != null && evaluacion.getPupilasReactivas() != null)
            {
                radioButtonPupilasReactivas.setChecked(evaluacion.getPupilasReactivas());
                if(evaluacion.getPupilasReactivas())
                {
                    if(pupilasReactionSelected != null)
                    {
                        pupilasReactionSelected.setChecked(Boolean.FALSE);
                    }
                    pupilasReactionSelected = radioButtonPupilasReactivas;
                    pupilasReactionSelected.setChecked(Boolean.TRUE);
                }
            }
            if(radioButtonPupilasNoReactivas != null && evaluacion.getPupilasNoReactivas() != null)
            {
                radioButtonPupilasNoReactivas.setChecked(evaluacion.getPupilasNoReactivas());
                if(evaluacion.getPupilasNoReactivas())
                {
                    if(pupilasReactionSelected != null)
                    {
                        pupilasReactionSelected.setChecked(Boolean.FALSE);
                    }
                    pupilasReactionSelected = radioButtonPupilasNoReactivas;
                    pupilasReactionSelected.setChecked(Boolean.TRUE);
                }
            }
            if(radioButtonPupilasNoEvaluables != null && evaluacion.getPupilasNoEvaluables() != null)
            {
                radioButtonPupilasNoEvaluables.setChecked(evaluacion.getPupilasNoEvaluables());
                if(evaluacion.getPupilasNoEvaluables())
                {
                    if(pupilasReactionSelected != null)
                    {
                        pupilasReactionSelected.setChecked(Boolean.FALSE);
                    }
                    pupilasReactionSelected = radioButtonPupilasNoEvaluables;
                    pupilasReactionSelected.setChecked(Boolean.TRUE);
                }
            }
            new EditTextUpdate().execute();

        }
    }
    @Override
    public void updateButtonSelected() {

        ImageButton button = (ImageButton)view.findViewById(R.id.imageButtonEvaluacionD);
        button.setActivated(Boolean.TRUE);
    }
    public void touchViews()
    {
        Integer ro = getRespuestaOcular(respuestaOcularSelected);
        Integer rm = getRespuestaMotora(respuestaMotoraSelected);
        Integer rv = getRespuestaVerbal(respuestaVerbalSelected);
        if(rv != null) {
            evaluacion.setGlasgowRV(rv);
            editTextRespuestaVerbal.setText(String.valueOf(rv));
        }
        if(rm != null) {
            evaluacion.setGlasgowRM(rm);
            editTextRespuestaMotora.setText(String.valueOf(rm));
        }
        if(ro != null) {
            evaluacion.setGlasgowRO(ro);
            editTextRespuestaOcular.setText(String.valueOf(ro));
        }
        saveEvaluacion(evaluacion);
        editTextTotal.setText(String.valueOf(getTotal()));

    }
    private class EditTextUpdate extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected Void doInBackground(Void... params) {

            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    touchViews();
                }
            });

            return null;
        }


    }
}
