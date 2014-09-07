package com.artica.telesalud.tph.android.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.StackView;

import com.artica.telesalud.tph.android.R;
import com.artica.telesalud.tph.android.lightweightmodel.EvaluacionDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by interoperabilidad on 29/08/14.
 */
public class TriageFragment extends AbstractEvaluacionFragment implements View.OnClickListener{

    private CheckBox buttonSelected;
    private HashMap<Integer, List<View>> triageMap = new HashMap<Integer, List<View>>();
    private SparseArray<Integer> indexes = new SparseArray<Integer>();

    private StackView stackViewTriage;
    private CheckBox checkBoxTriageRed;
    private CheckBox checkBoxTriageYellow ;
    private CheckBox checkBoxTriageGreen ;
    private CheckBox checkBoxTriageBlack;
    private CheckBox checkBoxTriageWhite;
    private View views[];
    @Override
    public int getViewId() {
        return R.layout.fragment_triage;
    }

    @Override
    public int getFragmentTagId() {
        return R.string.fragment_triage_tag;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = super.onCreateView(inflater, container, savedInstanceState);
        stackViewTriage = (StackView)view.findViewById(R.id.stackViewTriage);

        init();
        return view;
    }
    public void init()
    {

        final View viewTriageRed;
        final View viewTriageYellow;
        final View viewTriageBlack;
        final View viewTriageGreen;
        final View viewTriageWhite;
        LayoutInflater vi = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewTriageRed = vi.inflate(R.layout.radio_group_triage_red, null);
        viewTriageYellow = vi.inflate(R.layout.radio_group_triage_yellow, null);
        viewTriageBlack = vi.inflate(R.layout.radio_group_triage_black, null);
        viewTriageGreen = vi.inflate(R.layout.radio_group_triage_green, null);
        viewTriageWhite = vi.inflate(R.layout.radio_group_triage_white, null);
        indexes.append(R.id.imageButtonTriageRed, 0);
        indexes.append(R.id.imageButtonTriageYellow, 1);
        indexes.append(R.id.imageButtonTriageBlack, 2);
        indexes.append(R.id.imageButtonTriageGreen, 3);
        indexes.append(R.id.imageButtonTriageWhite, 4);
        views = new View[]{viewTriageRed, viewTriageYellow, viewTriageBlack, viewTriageGreen, viewTriageWhite};
        List<String> list = Arrays.asList("", "", "", "", "");
        StackAdapter adapter = new StackAdapter(activity, list);
        stackViewTriage.setAdapter(adapter);
        buttonSelected = null;
        checkBoxTriageRed = (CheckBox)viewTriageRed.findViewById(R.id.imageButtonTriageRed);
        checkBoxTriageYellow = (CheckBox)viewTriageYellow.findViewById(R.id.imageButtonTriageYellow);
        checkBoxTriageGreen = (CheckBox)viewTriageGreen.findViewById(R.id.imageButtonTriageGreen);
        checkBoxTriageBlack = (CheckBox)viewTriageBlack.findViewById(R.id.imageButtonTriageBlack);
        checkBoxTriageWhite = (CheckBox)viewTriageWhite.findViewById(R.id.imageButtonTriageWhite);

        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    evaluacion.setPrioridadTriage(getSelectedTriage(buttonView));
                    saveEvaluacion(evaluacion);
                }
            }
        };
        checkBoxTriageBlack.setOnCheckedChangeListener(listener);
        checkBoxTriageRed.setOnCheckedChangeListener(listener);
        checkBoxTriageGreen.setOnCheckedChangeListener(listener);
        checkBoxTriageWhite.setOnCheckedChangeListener(listener);
        checkBoxTriageYellow.setOnCheckedChangeListener(listener);
        checkBoxTriageBlack.setChecked(Boolean.FALSE);
        checkBoxTriageYellow.setChecked(Boolean.FALSE);
        checkBoxTriageRed.setChecked(Boolean.FALSE);
        checkBoxTriageGreen.setChecked(Boolean.FALSE);
        checkBoxTriageWhite.setChecked(Boolean.FALSE);
        checkBoxTriageBlack.setOnClickListener(this);
        checkBoxTriageRed.setOnClickListener(this);
        checkBoxTriageYellow.setOnClickListener(this);
        checkBoxTriageGreen.setOnClickListener(this);
        checkBoxTriageWhite.setOnClickListener(this);
        List<View> listTriageRed = new ArrayList<View>();
        CheckBox localCheckBox = (CheckBox)viewTriageRed.findViewById(R.id.radioButtonViaAreaRiesgo);
        localCheckBox.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setTriageViaAreaObstruida(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        listTriageRed.add(localCheckBox);
        localCheckBox = (CheckBox)viewTriageRed.findViewById(R.id.radioButtonInsuficienciaRespiratoria);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setTriageInsuficienciaRespiratoria(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        listTriageRed.add(localCheckBox);
        localCheckBox = (CheckBox)viewTriageRed.findViewById(R.id.radioButtonPa);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setTriagePa90(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        listTriageRed.add(localCheckBox);
        localCheckBox = (CheckBox)viewTriageRed.findViewById(R.id.radioButtonMultiplesHeridas);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setTriageMultiplesHeridas(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        listTriageRed.add(localCheckBox);
        localCheckBox = (CheckBox)viewTriageRed.findViewById(R.id.radioButtonHemorragiasNoControladas);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setTriageHemorragiasNoControladas(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        listTriageRed.add(localCheckBox);
        localCheckBox = (CheckBox)viewTriageRed.findViewById(R.id.radioButtonLesionCervicaIncompleta);
        localCheckBox.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setTriageLesionCervicalIncompleta(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        listTriageRed.add(localCheckBox);
        localCheckBox = (CheckBox)viewTriageRed.findViewById(R.id.radioButtonGlasgow48);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setTriageGlasgow4a8(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        listTriageRed.add(localCheckBox);
        localCheckBox = (CheckBox)viewTriageRed.findViewById(R.id.radioButtonExcitacionPsicomotora);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setTriageExcitacionPsicomotora(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        listTriageRed.add(localCheckBox);
        localCheckBox = (CheckBox)viewTriageRed.findViewById(R.id.radioButtonAbdomenAgudo);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setTriageAbdomenAgudo(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        listTriageRed.add(localCheckBox);
        localCheckBox = (CheckBox)viewTriageRed.findViewById(R.id.radioButtonEvisceracion);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setTriageEvisceracion(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        listTriageRed.add(localCheckBox);
        localCheckBox = (CheckBox)viewTriageRed.findViewById(R.id.radioButtonTrabajoPartoSangrado);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setTriageTrabajoPartoSangrado(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        listTriageRed.add(localCheckBox);
        triageMap.put(R.id.imageButtonTriageRed, listTriageRed);
        List<View> listTriageYellow = new ArrayList<View>();
        localCheckBox = (CheckBox)viewTriageYellow.findViewById(R.id.radioButtonDolorToracico);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setTriageDolorToracico(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        listTriageYellow.add(localCheckBox);
        localCheckBox = (CheckBox)viewTriageYellow.findViewById(R.id.radioButtonArritmias);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setTriageArritmias(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        listTriageYellow.add(localCheckBox);
        localCheckBox = (CheckBox)viewTriageYellow.findViewById(R.id.radioButtonHemorragiasControladas);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setTriageHemorragiasControladas(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        listTriageYellow.add(localCheckBox);
        localCheckBox = (CheckBox)viewTriageYellow.findViewById(R.id.radioButtonLesionMedularDorsal);
        localCheckBox.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setTriageLesionMedularDorsal(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        listTriageYellow.add(localCheckBox);
        localCheckBox = (CheckBox)viewTriageYellow.findViewById(R.id.radioButtonGlasgow913);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setTriageGlasgow9a13(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        listTriageYellow.add(localCheckBox);
        localCheckBox = (CheckBox)viewTriageYellow.findViewById(R.id.radioButtonAlteracionEstadoConciencia);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setTriageAlteracionEstadoConciencia(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        listTriageYellow.add(localCheckBox);
        localCheckBox = (CheckBox)viewTriageYellow.findViewById(R.id.radioButtonFracturasMayores);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setTriageFracturasMayores(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        listTriageYellow.add(localCheckBox);
        localCheckBox = (CheckBox)viewTriageYellow.findViewById(R.id.radioButtonQuemadurasModeradas);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setTriageQuemadurasModeradas(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        listTriageYellow.add(localCheckBox);
        localCheckBox = (CheckBox)viewTriageYellow.findViewById(R.id.radioButtonIntoxicacionTriage);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setTriageIntoxicacion(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        listTriageYellow.add(localCheckBox);
        triageMap.put(R.id.imageButtonTriageYellow, listTriageYellow);
        List<View> listTriageGreen = new ArrayList<View>();
        localCheckBox = (CheckBox)viewTriageGreen.findViewById(R.id.radioButtonLesionMedularLumbar);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setTriageLesionMedularLumbar(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        listTriageGreen.add(localCheckBox);
        localCheckBox = (CheckBox)viewTriageGreen.findViewById(R.id.radioButtonGlasgow1415);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setTriageGlasgow14o15(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        listTriageGreen.add(localCheckBox);
        localCheckBox = (CheckBox)viewTriageGreen.findViewById(R.id.radioButtonFracturasNoProximales);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setTriageFracturasNoProximales(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        listTriageGreen.add(localCheckBox);
        localCheckBox = (CheckBox)viewTriageGreen.findViewById(R.id.radioButtonLesionesSuperficiales);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setTriageLesionesSuperficiales(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        listTriageGreen.add(localCheckBox);
        localCheckBox = (CheckBox)viewTriageGreen.findViewById(R.id.radioButtonQuemadurasGradoI);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setTriageQuemadurasPrimerGrado(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        listTriageGreen.add(localCheckBox);
        localCheckBox = (CheckBox)viewTriageGreen.findViewById(R.id.radioButtonAfectados);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setTriageAfectados(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        listTriageGreen.add(localCheckBox);
        triageMap.put(R.id.imageButtonTriageGreen, listTriageGreen);
        List<View> listTriageBlack = new ArrayList<View>();
        localCheckBox = (CheckBox)viewTriageBlack.findViewById(R.id.radioButtonParoProlongado);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setTriageParoProlongado(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        listTriageBlack.add(localCheckBox);
        localCheckBox = (CheckBox)viewTriageBlack.findViewById(R.id.radioButtonLesionCervicalCompleta);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setTriageLesionCervicalCompleta(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        listTriageBlack.add(localCheckBox);
        localCheckBox = (CheckBox)viewTriageBlack.findViewById(R.id.radioButtonGlasgow3);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setTriageGlasgow3(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        listTriageBlack.add(localCheckBox);
        localCheckBox = (CheckBox)viewTriageBlack.findViewById(R.id.radioButtonExposicionMasaEncefalica);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setTriageExposicionMasaEncefalica(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        listTriageBlack.add(localCheckBox);
        localCheckBox = (CheckBox)viewTriageBlack.findViewById(R.id.radioButtonLesionesImpidenRCP);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setTriageLesionesImpidenRcp(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        listTriageBlack.add(localCheckBox);
        localCheckBox = (CheckBox)viewTriageBlack.findViewById(R.id.radioButtonQuemadurasGraves);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setTriageQuemadurasGraves(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        listTriageBlack.add(localCheckBox);
        triageMap.put(R.id.imageButtonTriageBlack, listTriageBlack);
        List<View> listTriageWhite = new ArrayList<View>();
        localCheckBox = (CheckBox)viewTriageWhite.findViewById(R.id.radioButtonEntregadoEntidad);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setTriageEntregadoEntidad(isChecked);
                saveEvaluacion(evaluacion);
                viewTriageWhite.findViewById(R.id.editTextRegistroPersonaRecepcion).setEnabled(isChecked);
                viewTriageWhite.findViewById(R.id.editTextNombrePersonaRecepcion).setEnabled(isChecked);
                viewTriageWhite.findViewById(R.id.editTextEntidadRecepcionDifunto).setEnabled(isChecked);
            }
        });
        listTriageWhite.add(localCheckBox);
        localCheckBox = (CheckBox)viewTriageWhite.findViewById(R.id.radioButtonCadenaCustodia);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.setTriageCadenaCustodia(isChecked);
                saveEvaluacion(evaluacion);
            }
        });
        listTriageWhite.add(localCheckBox);
        EditText localEditText = (EditText)viewTriageWhite.findViewById(R.id.editTextEntidadRecepcionDifunto);
        localEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                evaluacion.setTriageEntidadEntregaPacienteDifunto(s.toString());
                saveEvaluacion(evaluacion);
            }
        });
        listTriageWhite.add(localEditText);
        localEditText = (EditText)viewTriageWhite.findViewById(R.id.editTextNombrePersonaRecepcion);
        localEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                evaluacion.setTriageNombrePersonaRecibeDifunto(s.toString());
                saveEvaluacion(evaluacion);
            }
        });
        listTriageWhite.add(localEditText);
        localEditText = (EditText)viewTriageWhite.findViewById(R.id.editTextRegistroPersonaRecepcion);
        localEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                evaluacion.setTriageRegistroPersonaRecibeDifunto(s.toString());
                saveEvaluacion(evaluacion);
            }
        });
        listTriageWhite.add(localEditText);
        triageMap.put(R.id.imageButtonTriageWhite, listTriageWhite);
        for(Integer key : triageMap.keySet())
        {
            List<View> checkBoxes = triageMap.get(key);
            for(View checkBox : checkBoxes)
            {
                checkBox.setEnabled(false);
                if(checkBox instanceof CheckBox) {
                    ((CheckBox)checkBox).setChecked(false);
                }
            }
        }
        updateEvaluacion(evaluacion);
    }
    public Integer getSelectedTriage(View buttonSelected)
    {
        if(buttonSelected != null) {
            if (buttonSelected.getId() == R.id.imageButtonTriageYellow) {
                return EvaluacionDto.PRIORIDAD_TRIAGE_AMARILLO;
            }
            if (buttonSelected.getId() == R.id.imageButtonTriageWhite) {
                return EvaluacionDto.PRIORIDAD_TRIAGE_BLANCO;
            }
            if (buttonSelected.getId() == R.id.imageButtonTriageBlack) {
                return EvaluacionDto.PRIORIDAD_TRIAGE_NEGRO;
            }
            if (buttonSelected.getId() == R.id.imageButtonTriageRed) {
                return EvaluacionDto.PRIORIDAD_TRIAGE_ROJO;
            }
            if (buttonSelected.getId() ==R.id.imageButtonTriageGreen) {
                return EvaluacionDto.PRIORIDAD_TRIAGE_VERDE;
            }
        }
        return null;
    }
    @Override
    public void updateButtonSelected() {

        ImageButton button = (ImageButton)view.findViewById(R.id.imageButtonTrauma);
        button.setActivated(Boolean.TRUE);
    }
    public void clear()
    {

        buttonSelected = null;
        if(checkBoxTriageBlack != null)
        {
            checkBoxTriageBlack.setChecked(Boolean.FALSE);
        }
        if(checkBoxTriageWhite != null)
        {
            checkBoxTriageWhite.setChecked(Boolean.FALSE);
        }
        if(checkBoxTriageYellow != null)
        {
            checkBoxTriageYellow.setChecked(Boolean.FALSE);
        }
        if(checkBoxTriageGreen != null)
        {
            checkBoxTriageGreen.setChecked(Boolean.FALSE);
        }
        if(checkBoxTriageRed != null)
        {
            checkBoxTriageRed.setChecked(Boolean.FALSE);
        }
        for(Integer key : triageMap.keySet())
        {
            List<View> checkBoxes = triageMap.get(key);
            for(View checkBox : checkBoxes)
            {
                checkBox.setEnabled(false);
            }
        }
    }
    @Override
    public void updateEvaluacion(EvaluacionDto evaluacion) {
        clear();
        if(evaluacion != null)
        {
            Integer prioridad = evaluacion.getPrioridadTriage();
            Boolean selected;
            if(checkBoxTriageBlack != null)
            {
                selected = EvaluacionDto.PRIORIDAD_TRIAGE_NEGRO.equals(prioridad);
                checkBoxTriageBlack.setChecked(selected);
                if(selected) {
                    buttonSelected = checkBoxTriageBlack;
                }
            }
            if(checkBoxTriageGreen != null)
            {
                selected = EvaluacionDto.PRIORIDAD_TRIAGE_VERDE.equals(prioridad);
                checkBoxTriageGreen.setChecked(selected);
                if(selected) {
                    buttonSelected = checkBoxTriageGreen;
                }
            }
            if(checkBoxTriageRed != null)
            {
                selected = EvaluacionDto.PRIORIDAD_TRIAGE_ROJO.equals(prioridad);
                checkBoxTriageRed.setChecked(selected);
                if(selected) {
                    buttonSelected = checkBoxTriageRed;
                }
            }
            if(checkBoxTriageWhite != null)
            {
                selected = EvaluacionDto.PRIORIDAD_TRIAGE_BLANCO.equals(prioridad);
                checkBoxTriageWhite.setChecked(selected);
                if(selected) {
                    buttonSelected = checkBoxTriageWhite;
                }
            }
            if(checkBoxTriageYellow != null)
            {
                selected = EvaluacionDto.PRIORIDAD_TRIAGE_AMARILLO.equals(prioridad);
                checkBoxTriageYellow.setChecked(selected);
                if(selected) {
                    buttonSelected = checkBoxTriageYellow;
                }
            }
            if(stackViewTriage != null)
            {
                if(buttonSelected != null)
                {
                    stackViewTriage.setSelection(indexes.get(buttonSelected.getId()));
                }
            }
            if(buttonSelected != null) {
                List<View> checkBoxes = triageMap.get(buttonSelected.getId());
                if (checkBoxes != null) {
                    for (View checkBox : checkBoxes) {
                        checkBox.setEnabled(true);
                    }
                }
            }
            if(triageMap != null)
            {
                for(Integer key : triageMap.keySet())
                {
                    for(View view : triageMap.get(key))
                    {
                        if(view instanceof EditText)
                        {
                            EditText editText = (EditText)view;
                            if(view.getId() == R.id.editTextEntidadRecepcionDifunto)
                            {
                                if(evaluacion.getTriageEntidadEntregaPacienteDifunto() != null) {
                                    editText.setText(evaluacion.getTriageEntidadEntregaPacienteDifunto());
                                }
                                else
                                {
                                    editText.setText("");
                                }
                            }
                            if(view.getId() == R.id.editTextNombrePersonaRecepcion)
                            {
                                if(evaluacion.getTriageNombrePersonaRecibeDifunto()!=null) {
                                    editText.setText(evaluacion.getTriageNombrePersonaRecibeDifunto());
                                }
                                else
                                {
                                    editText.setText("");
                                }
                            }
                            if(view.getId() == R.id.editTextRegistroPersonaRecepcion)
                            {
                                if(evaluacion.getTriageRegistroPersonaRecibeDifunto() != null) {
                                    editText.setText(evaluacion.getTriageRegistroPersonaRecibeDifunto());
                                }
                                else
                                {
                                    editText.setText("");
                                }
                            }
                        }
                        if(view instanceof CheckBox)
                        {
                            CheckBox checkBox = (CheckBox)view;
                            if(R.id.radioButtonViaAreaRiesgo == checkBox.getId() && evaluacion.getTriageViaAreaObstruida() != null)
                            {
                                checkBox.setChecked(evaluacion.getTriageViaAreaObstruida());
                            }
                            if(R.id.radioButtonInsuficienciaRespiratoria == checkBox.getId() && evaluacion.getTriageInsuficienciaRespiratoria() != null)
                            {
                                checkBox.setChecked(evaluacion.getTriageInsuficienciaRespiratoria());
                            }
                            if(R.id.radioButtonPa==checkBox.getId() && evaluacion.getTriagePa90() != null)
                            {
                                checkBox.setChecked(evaluacion.getTriagePa90());
                            }
                            if(R.id.radioButtonMultiplesHeridas == checkBox.getId() && evaluacion.getTriageMultiplesHeridas() != null)
                            {
                                checkBox.setChecked(evaluacion.getTriageMultiplesHeridas());
                            }
                            if(R.id.radioButtonHemorragiasNoControladas == checkBox.getId() && evaluacion.getTriageHemorragiasNoControladas() != null)
                            {
                                checkBox.setChecked(evaluacion.getTriageHemorragiasNoControladas());
                            }
                            if(R.id.radioButtonLesionCervicaIncompleta == checkBox.getId() && evaluacion.getTriageLesionCervicalIncompleta() != null)
                            {
                                checkBox.setChecked(evaluacion.getTriageLesionCervicalIncompleta());
                            }
                            if(R.id.radioButtonGlasgow48==checkBox.getId() && evaluacion.getTriageGlasgow4a8() != null)
                            {
                                checkBox.setChecked(evaluacion.getTriageGlasgow4a8());
                            }
                            if(R.id.radioButtonExcitacionPsicomotora == checkBox.getId() && evaluacion.getTriageExcitacionPsicomotora() != null)
                            {
                                checkBox.setChecked(evaluacion.getTriageExcitacionPsicomotora());
                            }
                            if(R.id.radioButtonAbdomenAgudo == checkBox.getId() && evaluacion.getTriageAbdomenAgudo() != null)
                            {
                                checkBox.setChecked(evaluacion.getTriageAbdomenAgudo());
                            }
                            if(R.id.radioButtonEvisceracion == checkBox.getId() && evaluacion.getTriageEvisceracion() != null)
                            {
                                checkBox.setChecked(evaluacion.getTriageEvisceracion());
                            }
                            if(R.id.radioButtonTrabajoPartoSangrado == checkBox.getId() && evaluacion.getTriageTrabajoPartoSangrado() != null)
                            {
                                checkBox.setChecked(evaluacion.getTriageTrabajoPartoSangrado());
                            }
                            if(R.id.radioButtonDolorToracico == checkBox.getId() && evaluacion.getTriageDolorToracico() != null)
                            {
                                checkBox.setChecked(evaluacion.getTriageDolorToracico());
                            }
                            if(R.id.radioButtonArritmias == checkBox.getId() && evaluacion.getTriageArritmias() != null)
                            {
                                checkBox.setChecked(evaluacion.getTriageArritmias());
                            }
                            if(R.id.radioButtonHemorragiasControladas==checkBox.getId() && evaluacion.getTriageHemorragiasControladas() != null)
                            {
                                checkBox.setChecked(evaluacion.getTriageHemorragiasControladas());
                            }
                            if(R.id.radioButtonLesionMedularDorsal==checkBox.getId() && evaluacion.getTriageLesionMedularDorsal() != null)
                            {
                                checkBox.setChecked(evaluacion.getTriageLesionMedularDorsal());
                            }
                            if(R.id.radioButtonGlasgow913 == checkBox.getId() && evaluacion.getTriageGlasgow9a13() != null)
                            {
                                checkBox.setChecked(evaluacion.getTriageGlasgow9a13());
                            }
                            if(R.id.radioButtonAlteracionEstadoConciencia == checkBox.getId() && evaluacion.getTriageAlteracionEstadoConciencia() != null)
                            {
                                checkBox.setChecked(evaluacion.getTriageAlteracionEstadoConciencia());
                            }
                            if(R.id.radioButtonFracturasMayores==checkBox.getId() && evaluacion.getTriageFracturasMayores() != null)
                            {
                                checkBox.setChecked(evaluacion.getTriageFracturasMayores());
                            }
                            if(R.id.radioButtonQuemadurasModeradas == checkBox.getId() && evaluacion.getTriageQuemadurasModeradas() != null)
                            {
                                checkBox.setChecked(evaluacion.getTriageQuemadurasModeradas());
                            }
                            if(R.id.radioButtonIntoxicacionTriage == checkBox.getId() && evaluacion.getTriageIntoxicacion() != null)
                            {
                                checkBox.setChecked(evaluacion.getTriageIntoxicacion());
                            }
                            if(R.id.radioButtonLesionMedularLumbar == checkBox.getId() && evaluacion.getTriageLesionMedularLumbar() != null)
                            {
                                checkBox.setChecked(evaluacion.getTriageLesionMedularLumbar());
                            }
                            if(R.id.radioButtonGlasgow1415 == checkBox.getId() && evaluacion.getTriageGlasgow14o15() != null)
                            {
                                checkBox.setChecked(evaluacion.getTriageGlasgow14o15());
                            }
                            if(R.id.radioButtonFracturasNoProximales == checkBox.getId() && evaluacion.getTriageFracturasNoProximales() != null)
                            {
                                checkBox.setChecked(evaluacion.getTriageFracturasNoProximales());
                            }
                            if(R.id.radioButtonLesionesSuperficiales == checkBox.getId() && evaluacion.getTriageLesionesSuperficiales() != null)
                            {
                                checkBox.setChecked(evaluacion.getTriageLesionesSuperficiales());
                            }
                            if(R.id.radioButtonQuemadurasGradoI == checkBox.getId() && evaluacion.getTriageQuemadurasPrimerGrado() != null)
                            {
                                checkBox.setChecked(evaluacion.getTriageQuemadurasPrimerGrado());
                            }
                            if(R.id.radioButtonAfectados == checkBox.getId() && evaluacion.getTriageAfectados() != null)
                            {
                                checkBox.setChecked(evaluacion.getTriageAfectados());
                            }
                            if(R.id.radioButtonParoProlongado == checkBox.getId() && evaluacion.getTriageParoProlongado() != null)
                            {
                                checkBox.setChecked(evaluacion.getTriageParoProlongado());
                            }
                            if(R.id.radioButtonLesionCervicalCompleta == checkBox.getId() && evaluacion.getTriageLesionCervicalCompleta() != null)
                            {
                                checkBox.setChecked(evaluacion.getTriageLesionCervicalCompleta());
                            }
                            if(R.id.radioButtonGlasgow3 == checkBox.getId() && evaluacion.getTriageGlasgow3() != null)
                            {
                                checkBox.setChecked(evaluacion.getTriageGlasgow3());
                            }
                            if(R.id.radioButtonExposicionMasaEncefalica == checkBox.getId() && evaluacion.getTriageExposicionMasaEncefalica() != null)
                            {
                                checkBox.setChecked(evaluacion.getTriageExposicionMasaEncefalica());
                            }
                            if(R.id.radioButtonLesionesImpidenRCP == checkBox.getId() && evaluacion.getTriageLesionesImpidenRcp() != null)
                            {
                                checkBox.setChecked(evaluacion.getTriageLesionesImpidenRcp());
                            }
                            if(R.id.radioButtonQuemadurasGraves == checkBox.getId() && evaluacion.getTriageQuemadurasGraves() != null)
                            {
                                checkBox.setChecked(evaluacion.getTriageQuemadurasGraves());
                            }
                            if(R.id.radioButtonCadenaCustodia == checkBox.getId() && evaluacion.getTriageCadenaCustodia() != null)
                            {
                                checkBox.setChecked(evaluacion.getTriageCadenaCustodia());
                            }
                            if(R.id.radioButtonEntregadoEntidad == checkBox.getId() && evaluacion.getTriageEntregadoEntidad() != null)
                            {
                                checkBox.setChecked(evaluacion.getTriageEntregadoEntidad());
                            }
                        }
                    }
                }
            }

        }
    }
    public class StackAdapter extends ArrayAdapter<String> {
        public StackAdapter(Context context,
                            List<String> objects) {
            super(context, R.layout.lesionado_item, objects);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(position < views.length)
            {
                View v = views[position];
                return v;
            }
            return convertView;
        }
    }
    @Override
    public void onClick(View v) {
        if(v != null && v instanceof CheckBox) {

            if (buttonSelected != null && buttonSelected != v) {
                buttonSelected.setChecked(false);
                List<View> checkBoxes = triageMap.get(buttonSelected.getId());
                if(checkBoxes != null) {
                    for (View checkBox : checkBoxes) {
                        checkBox.setEnabled(false);
                        if(checkBox instanceof CheckBox)
                        {
                            ((CheckBox)checkBox).setChecked(Boolean.FALSE);
                        }
                        if(checkBox instanceof  EditText)
                        {
                            ((EditText)checkBox).setText("");
                        }
                    }
                }
            }
            buttonSelected = (CheckBox)v;
            buttonSelected.setChecked(true);
            List<View> checkBoxes = triageMap.get(buttonSelected.getId());
            if(checkBoxes != null)
            {
                for (View checkBox : checkBoxes) {
                    checkBox.setEnabled(true);
                }
            }
        }

    }
}
