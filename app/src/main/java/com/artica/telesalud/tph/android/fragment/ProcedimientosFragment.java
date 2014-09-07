package com.artica.telesalud.tph.android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;

import com.artica.telesalud.tph.android.R;
import com.artica.telesalud.tph.android.lightweightmodel.EvaluacionDto;
import com.artica.telesalud.tph.android.lightweightmodel.ProcedimientosDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProcedimientosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProcedimientosFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class ProcedimientosFragment extends AbstractEvaluacionFragment {

    public ProcedimientosFragment() {
        // Required empty public constructor
    }
    private List<View> listProcedimientos = new ArrayList<View>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = super.onCreateView(inflater, container, savedInstanceState);
        CheckBox localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonAspiracionSecreciones);
        listProcedimientos.add(localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.getProcedimientos().setAspiracionSecreciones(isChecked);
                saveProcedimientos(evaluacion);
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonBvm);
        listProcedimientos.add(localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.getProcedimientos().setBvm(isChecked);
                saveProcedimientos(evaluacion);
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonCanulaNasal);
        listProcedimientos.add(localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.getProcedimientos().setCanulaNasal(isChecked);
                saveProcedimientos(evaluacion);
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonCanulaNasofaringea);
        listProcedimientos.add(localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.getProcedimientos().setCanulaNasofaringea(isChecked);
                saveProcedimientos(evaluacion);
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonCanulaOrofaringea);
        listProcedimientos.add(localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.getProcedimientos().setCanulaOrofaringea(isChecked);
                saveProcedimientos(evaluacion);
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonCollarCervical);
        listProcedimientos.add(localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.getProcedimientos().setCollarCervical(isChecked);
                saveProcedimientos(evaluacion);
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonDea);
        listProcedimientos.add(localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.getProcedimientos().setDea(isChecked);
                saveProcedimientos(evaluacion);
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonDespejeManual);
        listProcedimientos.add(localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.getProcedimientos().setDespejeManual(isChecked);
                saveProcedimientos(evaluacion);
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonHemostasia);
        listProcedimientos.add(localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.getProcedimientos().setHemostasia(isChecked);
                saveProcedimientos(evaluacion);
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonMasajeCardiaco);
        listProcedimientos.add(localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.getProcedimientos().setMasajeCardiaco(isChecked);
                saveProcedimientos(evaluacion);
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonMascaraNoReinhalacion);
        listProcedimientos.add(localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.getProcedimientos().setMascaraNoReinhalacion(isChecked);
                saveProcedimientos(evaluacion);
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonMascaraSimple);
        listProcedimientos.add(localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.getProcedimientos().setMascaraSimple(isChecked);
                saveProcedimientos(evaluacion);
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonMonitoreoSignosVitales);
        listProcedimientos.add(localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.getProcedimientos().setMonitoreoSignosVitales(isChecked);
                saveProcedimientos(evaluacion);
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonReanimacionRespiratoria);
        listProcedimientos.add(localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.getProcedimientos().setReanimacionRespiratoria(isChecked);
                saveProcedimientos(evaluacion);
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonChalecoExtraccionVehicular);
        listProcedimientos.add(localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.getProcedimientos().setChalecoExtracionVehicular(isChecked);
                saveProcedimientos(evaluacion);
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonEspinalCorta);
        listProcedimientos.add(localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.getProcedimientos().setTablaEspinalCorta(isChecked);
                saveProcedimientos(evaluacion);
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonEspinalLarga);
        listProcedimientos.add(localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.getProcedimientos().setTablaEspinalLarga(isChecked);
                saveProcedimientos(evaluacion);
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonExposicion);
        listProcedimientos.add(localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.getProcedimientos().setExposicion(isChecked);
                saveProcedimientos(evaluacion);
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonInmovilizacionFerulas);
        listProcedimientos.add(localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.getProcedimientos().setInmovilizacionFerulas(isChecked);
                saveProcedimientos(evaluacion);
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonMantaTermica);
        listProcedimientos.add(localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.getProcedimientos().setMantaTermica(isChecked);
                saveProcedimientos(evaluacion);
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonLavadoCuracion);
        listProcedimientos.add(localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.getProcedimientos().setLavadoCuracion(isChecked);
                saveProcedimientos(evaluacion);
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonMovimientoBloque);
        listProcedimientos.add(localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.getProcedimientos().setMovimientoBloque(isChecked);
                saveProcedimientos(evaluacion);
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonAccesoVenosoCentral);
        listProcedimientos.add(localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.getProcedimientos().setAccesoVenosoCentral(isChecked);
                saveProcedimientos(evaluacion);
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonAccesoVenosoPeriferico);
        listProcedimientos.add(localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.getProcedimientos().setAccesoVenosoPeriferico(isChecked);
                saveProcedimientos(evaluacion);
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonColoides);
        listProcedimientos.add(localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.getProcedimientos().setColoides(isChecked);
                saveProcedimientos(evaluacion);
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonCricotiroidotomia);
        listProcedimientos.add(localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.getProcedimientos().setCricotiroidotomia(isChecked);
                saveProcedimientos(evaluacion);
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonDescompresionTorax);
        listProcedimientos.add(localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.getProcedimientos().setDescompresionTorax(isChecked);
                saveProcedimientos(evaluacion);
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonDispositivoSupragliotico);
        listProcedimientos.add(localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.getProcedimientos().setDispositivoSupragliotico(isChecked);
                saveProcedimientos(evaluacion);
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonIntubacionOrtotraqueal);
        listProcedimientos.add(localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.getProcedimientos().setIntubacionOrotraqueal(isChecked);
                saveProcedimientos(evaluacion);
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonPuncionOsea);
        listProcedimientos.add(localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.getProcedimientos().setPuncionOsea(isChecked);
                saveProcedimientos(evaluacion);
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonRehidratacionOral);
        listProcedimientos.add(localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.getProcedimientos().setRehidratacionOral(isChecked);
                saveProcedimientos(evaluacion);
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonVentilacionMecanica);
        listProcedimientos.add(localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                evaluacion.getProcedimientos().setVentilacionMecanica(isChecked);
                saveProcedimientos(evaluacion);
            }
        });
        EditText localEditText = (EditText)view.findViewById(R.id.editTextOtrosProcedimientos);
        localEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                evaluacion.getProcedimientos().setOtrosProcedimientos(s.toString());
                saveProcedimientos(evaluacion);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        listProcedimientos.add(localEditText);
        updateEvaluacion(evaluacion);
        return view;
    }

    @Override
    public int getViewId() {
        return R.layout.fragment_procedimientos;
    }

    @Override
    public int getFragmentTagId() {
        return R.string.fragment_procedimientos_tag;
    }

    public void updateEvaluacion(final EvaluacionDto evaluacion){
        if(evaluacion != null && evaluacion.getProcedimientos() != null)
        {
            ProcedimientosDto procedimientos = evaluacion.getProcedimientos();
            for(View view : listProcedimientos)
            {
                if(view instanceof EditText)
                {
                    EditText editText = (EditText)view;
                    if(editText.getId() == R.id.editTextOtrosProcedimientos)
                    {
                        if(procedimientos.getOtrosProcedimientos() != null) {
                            editText.setText(procedimientos.getOtrosProcedimientos());
                        }
                        else
                        {
                            editText.setText("");
                        }
                    }
                }
                if(view instanceof CheckBox) {
                    CheckBox checkBox = (CheckBox)view;
                    if (checkBox.getId() == R.id.radioButtonAspiracionSecreciones && procedimientos.getDea() != null) {
                        checkBox.setChecked(procedimientos.getAspiracionSecreciones() && procedimientos.getAspiracionSecreciones() != null);
                    }
                    if (checkBox.getId() == R.id.radioButtonBvm && procedimientos.getBvm() != null) {
                        checkBox.setChecked(procedimientos.getBvm());
                    }
                    if (checkBox.getId() == R.id.radioButtonCanulaNasal && procedimientos.getCanulaNasal() != null) {
                        checkBox.setChecked(procedimientos.getCanulaNasal());
                    }
                    if (checkBox.getId() == R.id.radioButtonCanulaNasofaringea && procedimientos.getCanulaNasofaringea() != null) {
                        checkBox.setChecked(procedimientos.getCanulaNasofaringea());
                    }
                    if (checkBox.getId() == R.id.radioButtonCanulaOrofaringea && procedimientos.getCanulaOrofaringea() != null) {
                        checkBox.setChecked(procedimientos.getCanulaOrofaringea());
                    }
                    if (checkBox.getId() == R.id.radioButtonCollarCervical && procedimientos.getCollarCervical() != null) {
                        checkBox.setChecked(procedimientos.getCollarCervical());
                    }
                    if (checkBox.getId() == R.id.radioButtonDea && procedimientos.getDea() != null) {
                        checkBox.setChecked(procedimientos.getDea());
                    }
                    if (checkBox.getId() == R.id.radioButtonDespejeManual && procedimientos.getDespejeManual() != null) {
                        checkBox.setChecked(procedimientos.getDespejeManual());
                    }
                    if (checkBox.getId() == R.id.radioButtonHemostasia && procedimientos.getHemostasia() != null) {
                        checkBox.setChecked(procedimientos.getHemostasia());
                    }
                    if (checkBox.getId() == R.id.radioButtonMasajeCardiaco && procedimientos.getMasajeCardiaco() != null) {
                        checkBox.setChecked(procedimientos.getMasajeCardiaco());
                    }
                    if (checkBox.getId() == R.id.radioButtonMascaraNoReinhalacion && procedimientos.getMascaraNoReinhalacion() != null) {
                        checkBox.setChecked(procedimientos.getMascaraNoReinhalacion());
                    }
                    if (checkBox.getId() == R.id.radioButtonMascaraSimple && procedimientos.getMascaraSimple() != null) {
                        checkBox.setChecked(procedimientos.getMascaraSimple());
                    }
                    if (checkBox.getId() == R.id.radioButtonMonitoreoSignosVitales && procedimientos.getMonitoreoSignosVitales() != null) {
                        checkBox.setChecked(procedimientos.getMonitoreoSignosVitales());
                    }
                    if (checkBox.getId() == R.id.radioButtonReanimacionRespiratoria && procedimientos.getReanimacionRespiratoria() != null) {
                        checkBox.setChecked(procedimientos.getReanimacionRespiratoria());
                    }
                    if (checkBox.getId() == R.id.radioButtonChalecoExtraccionVehicular && procedimientos.getChalecoExtracionVehicular() != null) {
                        checkBox.setChecked(procedimientos.getChalecoExtracionVehicular());
                    }
                    if (checkBox.getId() == R.id.radioButtonEspinalCorta && procedimientos.getTablaEspinalCorta() != null) {
                        checkBox.setChecked(procedimientos.getTablaEspinalCorta());
                    }
                    if (checkBox.getId() == R.id.radioButtonEspinalLarga && procedimientos.getTablaEspinalLarga() != null) {
                        checkBox.setChecked(procedimientos.getTablaEspinalLarga());
                    }
                    if (checkBox.getId() == R.id.radioButtonExposicion && procedimientos.getExposicion() != null) {
                        checkBox.setChecked(procedimientos.getExposicion());
                    }
                    if (checkBox.getId() == R.id.radioButtonInmovilizacionFerulas && procedimientos.getInmovilizacionFerulas() != null) {
                        checkBox.setChecked(procedimientos.getInmovilizacionFerulas());
                    }
                    if (checkBox.getId() == R.id.radioButtonLavadoCuracion && procedimientos.getLavadoCuracion() != null) {
                        checkBox.setChecked(procedimientos.getLavadoCuracion());
                    }
                    if (checkBox.getId() == R.id.radioButtonMantaTermica && procedimientos.getMantaTermica() != null) {
                        checkBox.setChecked(procedimientos.getMantaTermica());
                    }
                    if (checkBox.getId() == R.id.radioButtonMovimientoBloque && procedimientos.getMovimientoBloque() != null) {
                        checkBox.setChecked(procedimientos.getMantaTermica());
                    }
                    if (checkBox.getId() == R.id.radioButtonAccesoVenosoCentral && procedimientos.getAccesoVenosoCentral() != null) {
                        checkBox.setChecked(procedimientos.getAccesoVenosoCentral());
                    }
                    if (checkBox.getId() == R.id.radioButtonAccesoVenosoPeriferico && procedimientos.getAccesoVenosoPeriferico() != null) {
                        checkBox.setChecked(procedimientos.getAccesoVenosoPeriferico());
                    }
                    if (checkBox.getId() == R.id.radioButtonColoides && procedimientos.getColoides() != null) {
                        checkBox.setChecked(procedimientos.getColoides());
                    }
                    if (checkBox.getId() == R.id.radioButtonCricotiroidotomia && procedimientos.getCricotiroidotomia() != null) {
                        checkBox.setChecked(procedimientos.getCricotiroidotomia());
                    }
                    if (checkBox.getId() == R.id.radioButtonDescompresionTorax && procedimientos.getDescompresionTorax() != null) {
                        checkBox.setChecked(procedimientos.getDescompresionTorax());
                    }
                    if (checkBox.getId() == R.id.radioButtonDispositivoSupragliotico && procedimientos.getDispositivoSupragliotico() != null) {
                        checkBox.setChecked(procedimientos.getDispositivoSupragliotico());
                    }
                    if (checkBox.getId() == R.id.radioButtonIntubacionOrtotraqueal && procedimientos.getIntubacionOrotraqueal() != null) {
                        checkBox.setChecked(procedimientos.getIntubacionOrotraqueal());
                    }
                    if (checkBox.getId() == R.id.radioButtonPuncionOsea && procedimientos.getPuncionOsea() != null) {
                        checkBox.setChecked(procedimientos.getPuncionOsea());
                    }
                    if (checkBox.getId() == R.id.radioButtonRehidratacionOral && procedimientos.getRehidratacionOral() != null) {
                        checkBox.setChecked(procedimientos.getRehidratacionOral());
                    }
                    if (checkBox.getId() == R.id.radioButtonVentilacionMecanica && procedimientos.getVentilacionMecanica() != null) {
                        checkBox.setChecked(procedimientos.getVentilacionMecanica());
                    }
                }
            }

        }
    }
    @Override
    public void updateButtonSelected() {

        ImageButton button = (ImageButton)view.findViewById(R.id.imageButtonProcedimientos);
        button.setActivated(Boolean.TRUE);
    }
    public void saveProcedimientos(EvaluacionDto evaluacion)
    {

        try {
            evaluacionService.saveProcedimientos(evaluacion);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
