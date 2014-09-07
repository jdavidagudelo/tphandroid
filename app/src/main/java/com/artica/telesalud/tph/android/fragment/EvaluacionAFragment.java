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
import com.artica.telesalud.tph.android.lightweightmodel.EvaluacionDto;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EvaluacionAFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EvaluacionAFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class EvaluacionAFragment extends AbstractEvaluacionFragment{
    public EvaluacionAFragment() {
        // Required empty public constructor
    }
    private RadioButton selected = null;
    private EditText editTextCausaObstruccion;
    private SparseArray<RadioButton> hashMapViaAerea = new SparseArray<RadioButton>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = super.onCreateView(inflater, container, savedInstanceState);
        RadioButton radioButtonViaAereaPermeable = (RadioButton)view.findViewById(R.id.radioButtonViaAereaPermeable);
        RadioButton radioButtonViaAereaObstruida = (RadioButton)view.findViewById(R.id.radioButtonViaAereaObstruida);
        editTextCausaObstruccion = (EditText)view.findViewById(R.id.editTextCausaObstruccionViaAerea);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selected != null) {
                    selected.setChecked(false);
                }
                selected = (RadioButton) v;
                if(selected != null && evaluacion != null)
                {

                    evaluacion.setPermeavilidadViaAerea(getPermeabilidadViaAerea(selected));
                    selected.setChecked(true);
                    editTextCausaObstruccion.setEnabled(selected.getId() == R.id.radioButtonViaAereaObstruida);
                    saveEvaluacion(evaluacion);

                }
            }
        };
        editTextCausaObstruccion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                evaluacion.setCausaObstruccionVia(getCausaObstruccionViaAerea());
                saveEvaluacion(evaluacion);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        radioButtonViaAereaObstruida.setOnClickListener(listener);
        radioButtonViaAereaPermeable.setOnClickListener(listener);
        hashMapViaAerea.append(EvaluacionDto.VIA_AEREA_PERMEABLE, radioButtonViaAereaPermeable);
        hashMapViaAerea.append(EvaluacionDto.VIA_AEREA_OBSTRUIDA, radioButtonViaAereaObstruida);
        for(int i = 0; i < hashMapViaAerea.size(); i++)
        {
            hashMapViaAerea.valueAt(i).setChecked(Boolean.FALSE);
        }
        updateEvaluacion(evaluacion);
        return view;
    }

    public Integer getPermeabilidadViaAerea(RadioButton selected)
    {
        if(selected != null) {
            int index = hashMapViaAerea.indexOfValue(selected);
            return hashMapViaAerea.keyAt(index);
        }
        return null;

    }
    public static EvaluacionAFragment  newInstance()
    {

        return new EvaluacionAFragment();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public int getViewId() {
        return R.layout.fragment_evaluacion_a;
    }

    @Override
    public int getFragmentTagId() {
        return R.string.fragment_evaluacion_a_tag;
    }

    @Override
    public void updateButtonSelected() {

        ImageButton button = (ImageButton)view.findViewById(R.id.imageButtonEvaluacionA);
        button.setActivated(Boolean.TRUE);
    }
    public String getCausaObstruccionViaAerea()
    {
        if(editTextCausaObstruccion != null)
        {
            return editTextCausaObstruccion.getText().toString();
        }
        return null;
    }
    public void onDestroyView ()
    {
        super.onDestroyView();
        if(getPermeabilidadViaAerea(selected) != null) {
            evaluacion.setPermeavilidadViaAerea(getPermeabilidadViaAerea(selected));
            evaluacion.setCausaObstruccionVia(getCausaObstruccionViaAerea());
            saveEvaluacion(evaluacion);
        }
    }
    public void updateEvaluacion(final EvaluacionDto evaluacion){
        if(evaluacion != null && hashMapViaAerea != null)
        {
            Integer permeavilidadViaAerea = evaluacion.getPermeavilidadViaAerea();
            if(permeavilidadViaAerea != null) {
                if( editTextCausaObstruccion != null)
                {
                    editTextCausaObstruccion.setText(evaluacion.getCausaObstruccionVia());
                    editTextCausaObstruccion.setEnabled(EvaluacionDto.VIA_AEREA_OBSTRUIDA.equals(permeavilidadViaAerea));
                }
                RadioButton clicked = hashMapViaAerea.get(permeavilidadViaAerea);
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

}
