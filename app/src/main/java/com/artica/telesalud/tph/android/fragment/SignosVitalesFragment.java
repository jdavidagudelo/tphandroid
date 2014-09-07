package com.artica.telesalud.tph.android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.artica.telesalud.tph.android.R;
import com.artica.telesalud.tph.android.activity.EvaluacionPatientActivity;
import com.artica.telesalud.tph.android.dao.SignosVitalesService;
import com.artica.telesalud.tph.android.lightweightmodel.EncounterDto;
import com.artica.telesalud.tph.android.lightweightmodel.LesionadoDto;
import com.artica.telesalud.tph.android.lightweightmodel.SignosVitalesDto;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by interoperabilidad on 25/08/14.
 */
public class SignosVitalesFragment extends Fragment {
    private SignosVitalesService signosVitalesService;
    private View view;
    private EncounterDto encounter;
    private List<SignosVitalesDto> listSignosVitales = new ArrayList<SignosVitalesDto>();
    public SignosVitalesFragment() {
        // Required empty public constructor
    }
    private TableLayout tableSignosVitales;
    private LayoutInflater inflater;
    public void addRow(SignosVitalesDto signosVitales)
    {
        if(signosVitales != null) {
            View rowView = inflater.inflate(R.layout.row_signos_vitales, null);

            TableRow row = (TableRow) rowView.findViewById(R.id.tableRowSignosVitales);
            TextView textViewFecha = (TextView) row.findViewById(R.id.textViewFechaHoraTable);
            TextView textViewConsecutivo = (TextView) row.findViewById(R.id.textViewConsecutivoSignoVitalTable);
            TextView textViewFrecuenciaRespiratoria = (TextView) row.findViewById(R.id.textViewFrecuenciaRespiratoriaTable);
            TextView textViewPa = (TextView) row.findViewById(R.id.textViewPresionArterialTable);
            TextView textViewGlicemia = (TextView) row.findViewById(R.id.textViewGlicemiaTable);
            TextView textViewFrecuenciaCardiaca = (TextView) row.findViewById(R.id.textViewFrecuenciaCardiacaTable);
            TextView textViewTemperatura = (TextView) row.findViewById(R.id.textViewTemperaturaTable);
            TextView textViewSpo2 = (TextView) row.findViewById(R.id.textViewSaruracionOxigenoTable);
            DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/MM/yyyy - HH:mm:ss");

            DateTime jodatime = new DateTime(signosVitales.getFecha());
            textViewFecha.setText(dtf.print(jodatime));
            textViewConsecutivo.setText(String.valueOf(signosVitales.getSequenceId()));
            String format  = "%.2f";
            textViewFrecuenciaCardiaca.setText(String.format(format,signosVitales.getPulso()));
            textViewFrecuenciaRespiratoria.setText(String.format(format,signosVitales.getRespiracion()));
            textViewPa.setText(String.format(format,signosVitales.getPaMedia()));
            textViewGlicemia.setText(String.format(format,signosVitales.getGlicemia()));
            textViewTemperatura.setText(String.format(format,signosVitales.getTemperatura()));
            textViewSpo2.setText(String.format(format,signosVitales.getSpo2()));
            if(signosVitales.getSequenceId()%2 == 0)
            {
                textViewConsecutivo.setBackgroundColor(getResources().getColor(R.color.color_sequence_signos_vitales1));
                row.setBackgroundColor(getResources().getColor(R.color.table_signos_vitales_color1));
            }
            else
            {
                textViewConsecutivo.setBackgroundColor(getResources().getColor(R.color.color_sequence_signos_vitales2));
                row.setBackgroundColor(getResources().getColor(R.color.table_signos_vitales_color2));
            }
            tableSignosVitales.addView(row, 1);
        }
    }
    public void addSignosdVitales() throws SQLException {
        EditText editTextFrecuenciaRespiratoria = (EditText)view.findViewById(R.id.editTextFrecuenciaRespiratoria);
        EditText editTextFrecuenciaCardiaca =(EditText)view.findViewById(R.id.editTextFrecuenciaCardiaca);
        EditText editTextPresionArterialDiastolica = (EditText)view.findViewById(R.id.editTextPresionArterialDiastolica);
        EditText editTextPresionArterialSistolica =(EditText)view.findViewById(R.id.editTextPresionArterialSistolica);
        EditText editTextTemperatura = (EditText)view.findViewById(R.id.editTextTemperatura);
        EditText editTextGlicemia = (EditText)view.findViewById(R.id.editTextGlicemia);
        EditText editTextSpo2 = (EditText)view.findViewById(R.id.editTextSaturacionOxigeno);
        SignosVitalesDto signosVitales = new SignosVitalesDto();
        signosVitales.setTemperatura(Double.parseDouble(editTextTemperatura.getText().toString()));
        signosVitales.setRespiracion(Double.parseDouble(editTextFrecuenciaRespiratoria.getText().toString()));
        signosVitales.setPulso(Double.parseDouble(editTextFrecuenciaCardiaca.getText().toString()));
        signosVitales.setPaDiastolica(Double.parseDouble(editTextPresionArterialDiastolica.getText().toString()));
        signosVitales.setPaSistolica(Double.parseDouble(editTextPresionArterialSistolica.getText().toString()));
        signosVitales.setGlicemia(Double.parseDouble(editTextGlicemia.getText().toString()));
        signosVitales.setSpo2(Double.parseDouble(editTextSpo2.getText().toString()));
        signosVitales.setFecha(new Date());
        signosVitales.setSequenceId(listSignosVitales.size() + 1);
        signosVitales.setEncounter(encounter);
        if(encounter != null) {
            signosVitales.setEncounterId(encounter.getId());
        }
        listSignosVitales.add(signosVitales);
        signosVitalesService.save(signosVitales);
        addRow(signosVitales);
    }

    public static SignosVitalesFragment newInstance()
    {
        return new SignosVitalesFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_signos_vitales, container, false);
        tableSignosVitales = (TableLayout)view.findViewById(R.id.tableSignosVitales);
        this.inflater = inflater;
        Button buttonAddSignosVitales = (Button)view.findViewById(R.id.buttonAddSignosVitales);
        buttonAddSignosVitales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    addSignosdVitales();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        if(getActivity() instanceof EvaluacionPatientActivity) {
            EvaluacionPatientActivity activity = (EvaluacionPatientActivity) getActivity();
            LesionadoDto lesionado = activity.getLesionado();
            try {
                signosVitalesService = new SignosVitalesService(activity.getHelper());
                if(lesionado != null && lesionado.getEncuentro() != null && lesionado.getEncuentro().getId() != null)
                {
                    encounter = lesionado.getEncuentro();
                    listSignosVitales.clear();
                    listSignosVitales.addAll(signosVitalesService.getSignosVitalesByEncounter(lesionado.getEncuentro().getId()));
                    for(SignosVitalesDto signoVital : listSignosVitales)
                    {
                        addRow(signoVital);
                    }
                }
            } catch (SQLException e) {
            }
        }
        final EditText editTextPresionArterialDiastolica = (EditText)view.findViewById(R.id.editTextPresionArterialDiastolica);
        final EditText editTextPresionArterialSistolica =(EditText)view.findViewById(R.id.editTextPresionArterialSistolica);
        final EditText editTextPresionMedia = (EditText)view.findViewById(R.id.editTextPresionArterialMedia);
        editTextPresionArterialDiastolica.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().isEmpty() && !editTextPresionArterialSistolica.getText().toString().isEmpty()) {
                    Double pad = Double.parseDouble(s.toString());
                    Double pas = Double.parseDouble(editTextPresionArterialSistolica.getText().toString());
                    editTextPresionMedia.setText(getPresionArterialMedia(pad, pas));
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editTextPresionArterialSistolica.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().isEmpty() && !editTextPresionArterialDiastolica.getText().toString().isEmpty()) {
                    Double pas = Double.parseDouble(s.toString());
                    Double pad = Double.parseDouble(editTextPresionArterialDiastolica.getText().toString());
                    editTextPresionMedia.setText(getPresionArterialMedia(pad, pas));
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editTextPresionMedia.setEnabled(Boolean.FALSE);
        return view;
    }
    private String getPresionArterialMedia(Double pad, Double pas)
    {
        String format  = "%.2f";
        if(pas == null || pad == null)
        {
            return String.valueOf(0.0);
        }
        return String.format(format, ((pas +2*pad)/3));
    }
}
