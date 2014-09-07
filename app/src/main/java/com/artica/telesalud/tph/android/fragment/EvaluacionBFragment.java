package com.artica.telesalud.tph.android.fragment;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.artica.telesalud.tph.android.R;
import com.artica.telesalud.tph.android.lightweightmodel.EvaluacionDto;


/**
 * A simple {@link android.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EvaluacionBFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EvaluacionBFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class EvaluacionBFragment extends AbstractEvaluacionFragment {

    private RadioButton respiracionSelected;
    private RadioButton estadoRespiracionSelected;
    private RadioButton cianosisSelected;
    private SparseArray<RadioButton> hashMapRespiracion = new SparseArray<RadioButton>();
    private SparseArray<RadioButton> hashMapEstadoRespiracion = new SparseArray<RadioButton>();
    private SparseArray<RadioButton> hashMapCianosis = new SparseArray<RadioButton>();
    public EvaluacionBFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {

        view = super.onCreateView(inflater, container, savedInstanceState);
        RadioButton radioButtonRespiracionPresente =(RadioButton)view.findViewById(R.id.radioButtonRespiracionPresente);
        RadioButton radioButtonRespiracionAusente = (RadioButton)view.findViewById(R.id.radioButtonRespiracionAusente);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v instanceof RadioButton) {
                    if (respiracionSelected != null) {
                        respiracionSelected.setChecked(Boolean.FALSE);
                    }
                    respiracionSelected = (RadioButton)v;
                    Boolean enabled = respiracionSelected.getId() == R.id.radioButtonRespiracionPresente;
                    for(int i = 0; i < hashMapEstadoRespiracion.size(); i++)
                    {
                        hashMapEstadoRespiracion.valueAt(i).setEnabled(enabled);
                    }
                    evaluacion.setRespiracion(getRespiracion(respiracionSelected));
                    saveEvaluacion(evaluacion);
                    respiracionSelected.setChecked(Boolean.TRUE);
                }
            }
        };
        radioButtonRespiracionAusente.setOnClickListener(listener);
        radioButtonRespiracionPresente.setOnClickListener(listener);
        hashMapRespiracion.append(EvaluacionDto.RESPIRACION_PRESENTE, radioButtonRespiracionPresente);
        hashMapRespiracion.append(EvaluacionDto.RESPIRACION_AUSENTE, radioButtonRespiracionAusente);
        RadioButton radioButtonRespiracionNormal = (RadioButton)view.findViewById(R.id.radioButtonProfundidadNormal);
        RadioButton radioButtonRespiracionDificultosa = (RadioButton)view.findViewById(R.id.radioButtonProfundidadDificultad);
        RadioButton radioButtonRespiracionSimetrica = (RadioButton)view.findViewById(R.id.radioButtonProfundidadSimetrica);
        RadioButton radioButtonRespiracionAsimetrica = (RadioButton)view.findViewById(R.id.radioButtonProfundidadAsimetrica);
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v instanceof RadioButton)
                {
                    if(estadoRespiracionSelected != null)
                    {
                        estadoRespiracionSelected.setChecked(Boolean.FALSE);
                    }
                    estadoRespiracionSelected = (RadioButton)v;
                    evaluacion.setEstadoRespiracion(getEstadoRespiracion(estadoRespiracionSelected));
                    saveEvaluacion(evaluacion);
                    estadoRespiracionSelected.setChecked(Boolean.TRUE);
                }
            }
        };
        radioButtonRespiracionAsimetrica.setOnClickListener(listener);
        radioButtonRespiracionDificultosa.setOnClickListener(listener);
        radioButtonRespiracionNormal.setOnClickListener(listener);
        radioButtonRespiracionSimetrica.setOnClickListener(listener);
        hashMapEstadoRespiracion.append(EvaluacionDto.ESTADO_RESPIRACION_ASIMETRICA, radioButtonRespiracionAsimetrica);
        hashMapEstadoRespiracion.append(EvaluacionDto.ESTADO_RESPIRACION_DIFICULTOSA, radioButtonRespiracionDificultosa);
        hashMapEstadoRespiracion.append(EvaluacionDto.ESTADO_RESPIRACION_SIMETRICA, radioButtonRespiracionSimetrica);
        hashMapEstadoRespiracion.append(EvaluacionDto.ESTADO_RESPIRACION_NORMAL, radioButtonRespiracionNormal);
        RadioButton radioButtonCianosisExistente = (RadioButton)view.findViewById(R.id.radioButtonCianosisPositiva);
        RadioButton radioButtonCianosisNoExistente = (RadioButton)view.findViewById(R.id.radioButtonCianosisNegativa);
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v instanceof RadioButton)
                {
                    if(cianosisSelected != null)
                    {
                        cianosisSelected.setChecked(Boolean.FALSE);
                    }
                    cianosisSelected = (RadioButton)v;
                    evaluacion.setCianosis(getCianosis(cianosisSelected));
                    saveEvaluacion(evaluacion);
                    cianosisSelected.setChecked(Boolean.TRUE);
                }
            }
        };
        radioButtonCianosisExistente.setOnClickListener(listener);
        radioButtonCianosisNoExistente.setOnClickListener(listener);
        hashMapCianosis.append(EvaluacionDto.CIANOSIS_AUSENTE, radioButtonCianosisNoExistente);
        hashMapCianosis.append(EvaluacionDto.CIANOSIS_EXISTENTE, radioButtonCianosisExistente);
        updateEvaluacion(evaluacion);
        return view;
    }
    public Integer getCianosis(RadioButton selected)
    {
        if(selected != null) {
            int index = hashMapCianosis.indexOfValue(selected);
            return hashMapCianosis.keyAt(index);
        }
        return null;
    }
    public Integer getEstadoRespiracion(RadioButton selected)
    {
        if(selected != null) {
            int index = hashMapEstadoRespiracion.indexOfValue(selected);
            return hashMapEstadoRespiracion.keyAt(index);
        }
        return null;

    }
    public Integer getRespiracion(RadioButton selected)
    {
        if(selected != null) {
            int index = hashMapRespiracion.indexOfValue(selected);
            return hashMapRespiracion.keyAt(index);
        }
        return null;
    }
    public static EvaluacionBFragment  newInstance()
    {
        return new EvaluacionBFragment();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public int getViewId() {
        return R.layout.fragment_evaluacion_b;
    }

    @Override
    public int getFragmentTagId() {
        return R.string.fragment_evaluacion_b_tag;
    }
    public void clear()
    {
        for(int i = 0; i < hashMapCianosis.size(); i++)
        {
            hashMapCianosis.valueAt(i).setChecked(Boolean.FALSE);
        }
        for(int i = 0; i < hashMapEstadoRespiracion.size(); i++)
        {
            hashMapEstadoRespiracion.valueAt(i).setChecked(Boolean.FALSE);
        }

        for(int i = 0; i < hashMapRespiracion.size(); i++)
        {
            hashMapRespiracion.valueAt(i).setChecked(Boolean.FALSE);
        }
    }
    public void updateEvaluacion(final EvaluacionDto evaluacion){
        clear();
        if(evaluacion != null)
        {
            Integer estadoRespiracion = evaluacion.getEstadoRespiracion();
            Integer respiracion = evaluacion.getRespiracion();
            Integer cianosis = evaluacion.getCianosis();

            if(hashMapEstadoRespiracion != null && estadoRespiracion != null)
            {
                if(estadoRespiracionSelected != null)
                {
                    estadoRespiracionSelected.setChecked(Boolean.FALSE);
                }
                estadoRespiracionSelected = hashMapEstadoRespiracion.get(estadoRespiracion);
                if(estadoRespiracionSelected != null) {
                    estadoRespiracionSelected.setChecked(Boolean.TRUE);
                }
            }
            if(hashMapRespiracion != null && respiracion != null) {
                if (respiracionSelected != null) {
                    respiracionSelected.setChecked(Boolean.FALSE);
                }
                respiracionSelected = hashMapRespiracion.get(respiracion);
                if (respiracionSelected != null) {
                    respiracionSelected.setChecked(Boolean.TRUE);
                    Boolean enabled = respiracionSelected.getId() == R.id.radioButtonRespiracionPresente;
                    for (int i = 0; i < hashMapEstadoRespiracion.size(); i++) {
                        RadioButton button = hashMapEstadoRespiracion.valueAt(i);
                        button.setEnabled(enabled);
                        button.setChecked(enabled && button.isChecked());
                    }
                }
            }
            if(hashMapCianosis != null && cianosis != null)
            {
                if(cianosisSelected != null)
                {
                    cianosisSelected.setChecked(Boolean.FALSE);
                }
                cianosisSelected = hashMapCianosis.get(cianosis);
                if(cianosisSelected != null)
                {
                    cianosisSelected.setChecked(Boolean.TRUE);
                }
            }
        }
    }
    @Override
    public void updateButtonSelected() {

        ImageButton button = (ImageButton)view.findViewById(R.id.imageButtonEvaluacionB);
        button.setActivated(Boolean.TRUE);
    }
}
