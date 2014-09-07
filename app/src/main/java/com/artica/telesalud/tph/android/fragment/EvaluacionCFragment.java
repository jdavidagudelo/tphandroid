package com.artica.telesalud.tph.android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.artica.telesalud.tph.android.R;
import com.artica.telesalud.tph.android.lightweightmodel.EvaluacionDto;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EvaluacionCFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EvaluacionCFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class EvaluacionCFragment extends AbstractEvaluacionFragment{

    private RadioButton sangradoSelected;
    private RadioButton pulsoSelected;
    private RadioButton ubicacionSelected;
    private RadioButton llenadoCapilarSelected;
    private SparseArray<RadioButton> hashMapSangrado = new SparseArray<RadioButton>();
    private SparseArray<RadioButton> hashMapPulso = new SparseArray<RadioButton>();
    private SparseArray<RadioButton> hashMapUbicacion = new SparseArray<RadioButton>();
    private SparseArray<RadioButton> hashMapLlenadoCapilar = new SparseArray<RadioButton>();
    private CheckBox checkBoxPielCaliente;
    private CheckBox checkBoxPielCianotica;
    private CheckBox checkBoxPielFria ;
    private CheckBox checkBoxPielHumeda;
    private  CheckBox checkBoxPielPalida ;
    private  CheckBox checkBoxPielIcterica;
    private CheckBox checkBoxPielSeca ;
    private CheckBox checkBoxPielNormal;

    public EvaluacionCFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = super.onCreateView(inflater, container, savedInstanceState);
        RadioButton radioButtonSangrado = (RadioButton)view.findViewById(R.id.radioButtonSangradoSi);
        RadioButton radioButtonNoSangrado = (RadioButton)view.findViewById(R.id.radioButtonSangradoNo);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v instanceof RadioButton) {
                    if (sangradoSelected != null) {
                        sangradoSelected.setChecked(Boolean.FALSE);
                    }
                    sangradoSelected = (RadioButton)v;
                    evaluacion.setSangrado(getSangrado(sangradoSelected));
                    saveEvaluacion(evaluacion);
                    sangradoSelected.setChecked(Boolean.TRUE);
                }
            }
        };
        radioButtonSangrado.setOnClickListener(listener);
        radioButtonNoSangrado.setOnClickListener(listener);
        hashMapSangrado.append(EvaluacionDto.SANGRADO_AUSENTE, radioButtonNoSangrado);
        hashMapSangrado.append(EvaluacionDto.SANGRADO_EXISTENTE, radioButtonSangrado);
        RadioButton radioButtonPulsoFuerte = (RadioButton)view.findViewById(R.id.radioButtonFortalezaFuerte);
        RadioButton radioButtonPulsoDebil = (RadioButton)view.findViewById(R.id.radioButtonFortalezaDebil);
        RadioButton radioButtonPulsoAusente = (RadioButton)view.findViewById(R.id.radioButtonFortalezaAusente);
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v instanceof RadioButton) {
                    if (pulsoSelected != null) {
                        pulsoSelected.setChecked(Boolean.FALSE);
                    }
                   pulsoSelected = (RadioButton)v;
                    evaluacion.setPulso(getPulso(pulsoSelected));
                    saveEvaluacion(evaluacion);
                    pulsoSelected.setChecked(Boolean.TRUE);
                }
            }
        };
        radioButtonPulsoAusente.setOnClickListener(listener);
        radioButtonPulsoDebil.setOnClickListener(listener);
        radioButtonPulsoFuerte.setOnClickListener(listener);
        hashMapPulso.append(EvaluacionDto.PULSO_AUSENTE, radioButtonPulsoAusente);
        hashMapPulso.append(EvaluacionDto.PULSO_DEBIL, radioButtonPulsoDebil);
        hashMapPulso.append(EvaluacionDto.PULSO_FUERTE, radioButtonPulsoFuerte);
        RadioButton radioButtonUbicacionRadialPulso = (RadioButton)view.findViewById(R.id.radioButtonUbicacionRadial);
        RadioButton radioButtonUbicacionCarotideoPulso = (RadioButton)view.findViewById(R.id.radioButtonUbicacionCarotideo);
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v instanceof RadioButton) {
                    if (ubicacionSelected != null) {
                        ubicacionSelected.setChecked(Boolean.FALSE);
                    }
                    ubicacionSelected = (RadioButton)v;
                    evaluacion.setUbicacionPulso(getUbicacion(ubicacionSelected));
                    saveEvaluacion(evaluacion);
                    ubicacionSelected.setChecked(Boolean.TRUE);
                }
            }
        };
        radioButtonUbicacionRadialPulso.setOnClickListener(listener);
        radioButtonUbicacionCarotideoPulso.setOnClickListener(listener);
        hashMapUbicacion.append(EvaluacionDto.UBICACION_PULSO_CAROTIDEA, radioButtonUbicacionCarotideoPulso);
        hashMapUbicacion.append(EvaluacionDto.UBICACION_PULSO_RADIAL, radioButtonUbicacionRadialPulso);
        RadioButton radioButtonLlenadoCapilarMenor3 = (RadioButton)view.findViewById(R.id.radioButtonLlenadoCapilarMenor3Segundos);
        RadioButton radioButtonLlenadoCapilarMayor3 = (RadioButton)view.findViewById(R.id.radioButtonLlenadoCapilarMayor3Segundos);
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v instanceof RadioButton) {
                    if (llenadoCapilarSelected != null) {
                        llenadoCapilarSelected.setChecked(Boolean.FALSE);
                    }
                    llenadoCapilarSelected = (RadioButton)v;
                    evaluacion.setLlenadoCapilar(getLlenadoCapilar(llenadoCapilarSelected));
                    saveEvaluacion(evaluacion);
                    llenadoCapilarSelected.setChecked(Boolean.TRUE);
                }
            }
        };
        radioButtonLlenadoCapilarMayor3.setOnClickListener(listener);
        radioButtonLlenadoCapilarMenor3.setOnClickListener(listener);
        hashMapLlenadoCapilar.append(EvaluacionDto.LLENADO_CAPILAR_MAYOR, radioButtonLlenadoCapilarMayor3);
        hashMapLlenadoCapilar.append(EvaluacionDto.LLENADO_CAPILAR_MENOR, radioButtonLlenadoCapilarMenor3);

       checkBoxPielCaliente = (CheckBox)view.findViewById(R.id.radioButtonPielCaliente);
       checkBoxPielCianotica =(CheckBox)view.findViewById(R.id.radioButtonPielCianotica);
       checkBoxPielFria = (CheckBox)view.findViewById(R.id.radioButtonPielFria);
       checkBoxPielHumeda = (CheckBox)view.findViewById(R.id.radioButtonPielHumeda);
       checkBoxPielPalida = (CheckBox)view.findViewById(R.id.radioButtonPielPalida);
       checkBoxPielIcterica = (CheckBox)view.findViewById(R.id.radioButtonPielIcterica);
       checkBoxPielSeca = (CheckBox)view.findViewById(R.id.radioButtonPielSeca);
       checkBoxPielNormal = (CheckBox)view.findViewById(R.id.radioButtonPielNormal);
        checkBoxPielIcterica.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setPielIcterica(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        checkBoxPielSeca.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setPielSeca(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        checkBoxPielNormal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setPielNormal(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        checkBoxPielCaliente.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setPielCaliente(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        checkBoxPielCianotica.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setPielCianotica(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        checkBoxPielFria.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setPielFria(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        checkBoxPielHumeda.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setPielHumeda(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        checkBoxPielPalida.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setPielPalida(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        updateEvaluacion(evaluacion);
        return view;
    }
    public Integer getLlenadoCapilar(RadioButton selected)
    {

        if(selected != null) {
            int index = hashMapLlenadoCapilar.indexOfValue(selected);
            return hashMapLlenadoCapilar.keyAt(index);
        }
        return null;
    }
    public Integer getUbicacion(RadioButton selected)
    {
        if(selected != null) {
            int index = hashMapUbicacion.indexOfValue(selected);
            return hashMapUbicacion.keyAt(index);
        }
        return null;

    }
    public Integer getPulso(RadioButton selected)
    {
        if(selected != null) {
            int index = hashMapPulso.indexOfValue(selected);
            return hashMapPulso.keyAt(index);
        }
        return null;
    }
    public Integer getSangrado(RadioButton selected)
    {
        if(selected != null) {
            int index = hashMapSangrado.indexOfValue(selected);
            return hashMapSangrado.keyAt(index);
        }
        return null;
    }
    @Override
    public int getViewId() {
        return R.layout.fragment_evaluacion_c;
    }

    @Override
    public int getFragmentTagId() {
        return R.string.fragment_evaluacion_c_tag;
    }
    public void clear() {

        for(int i = 0; i < hashMapPulso.size(); i++)
        {
            hashMapPulso.valueAt(i).setChecked(Boolean.FALSE);
        }
        for(int i = 0; i < hashMapLlenadoCapilar.size(); i++)
        {
            hashMapLlenadoCapilar.valueAt(i).setChecked(Boolean.FALSE);
        }
        for(int i = 0; i < hashMapSangrado.size(); i++)
        {
            hashMapSangrado.valueAt(i).setChecked(Boolean.FALSE);
        }
        for(int i = 0; i < hashMapUbicacion.size(); i++)
        {
            hashMapUbicacion.valueAt(i).setChecked(Boolean.FALSE);
        }
    }
    public void updateEvaluacion(final EvaluacionDto evaluacion){
        clear();
        if(evaluacion != null){
            Integer sangrado = evaluacion.getSangrado();
            Integer pulso = evaluacion.getPulso();
            Integer ubicacion = evaluacion.getUbicacionPulso();
            Integer llenadoCapilar = evaluacion.getLlenadoCapilar();
            if(hashMapLlenadoCapilar != null && llenadoCapilar != null)
            {
                if(llenadoCapilarSelected != null)
                {
                    llenadoCapilarSelected.setChecked(Boolean.FALSE);
                }
                llenadoCapilarSelected = hashMapLlenadoCapilar.get(llenadoCapilar);
                if(llenadoCapilarSelected != null)
                {
                    llenadoCapilarSelected.setChecked(Boolean.TRUE);
                }
            }
            if(hashMapPulso != null && pulso != null)
            {
                if(pulsoSelected != null)
                {
                    pulsoSelected.setChecked(Boolean.FALSE);
                }
                pulsoSelected = hashMapPulso.get(pulso);
                if(pulsoSelected != null)
                {
                    pulsoSelected.setChecked(Boolean.TRUE);
                }
            }
            if(hashMapUbicacion != null && ubicacion != null)
            {
                if(ubicacionSelected != null)
                {
                    ubicacionSelected.setChecked(Boolean.FALSE);
                }
                ubicacionSelected = hashMapUbicacion.get(ubicacion);
                if(ubicacionSelected != null)
                {
                    ubicacionSelected.setChecked(Boolean.TRUE);
                }
            }
            if(hashMapSangrado != null && sangrado != null)
            {
                if(sangradoSelected != null)
                {
                    sangradoSelected.setChecked(Boolean.FALSE);
                }
                sangradoSelected = hashMapSangrado.get(sangrado);
                if(sangradoSelected != null)
                {
                    sangradoSelected.setChecked(Boolean.TRUE);
                }
            }
            if(checkBoxPielCaliente != null && evaluacion.getPielCaliente() != null)
            {
                checkBoxPielCaliente.setChecked(evaluacion.getPielCaliente());
            }
            if(checkBoxPielCianotica != null && evaluacion.getPielCianotica() != null)
            {
                checkBoxPielCianotica.setChecked(evaluacion.getPielCianotica());
            }
            if(checkBoxPielFria != null && evaluacion.getPielFria() != null)
            {
                checkBoxPielFria.setChecked(evaluacion.getPielFria());
            }
            if(checkBoxPielHumeda != null && evaluacion.getPielHumeda() != null)
            {
                checkBoxPielHumeda.setChecked(evaluacion.getPielHumeda());
            }
            if(checkBoxPielIcterica != null && evaluacion.getPielIcterica() != null)
            {
                checkBoxPielIcterica.setChecked(evaluacion.getPielIcterica());
            }
            if(checkBoxPielNormal != null && evaluacion.getPielNormal() != null)
            {
                checkBoxPielNormal.setChecked(evaluacion.getPielNormal());
            }
            if(checkBoxPielPalida != null && evaluacion.getPielPalida() != null)
            {
                checkBoxPielPalida.setChecked(evaluacion.getPielPalida());
            }
            if(checkBoxPielSeca != null && evaluacion.getPielSeca() != null)
            {
                checkBoxPielSeca.setChecked(evaluacion.getPielSeca());
            }
        }
    }
    @Override
    public void updateButtonSelected() {

        ImageButton button = (ImageButton)view.findViewById(R.id.imageButtonEvaluacionC);
        button.setActivated(Boolean.TRUE);
    }
}
