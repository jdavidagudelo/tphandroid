package com.artica.telesalud.tph.android.fragment;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.artica.telesalud.tph.android.R;
import com.artica.telesalud.tph.android.adapter.ConceptArrayAdapter;
import com.artica.telesalud.tph.android.adapter.DocumentTypeArrayAdapter;
import com.artica.telesalud.tph.android.lightweightmodel.ConceptDto;
import com.artica.telesalud.tph.android.lightweightmodel.PatientIdentifierTypeDto;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class IdentificacionPacienteFragment extends AbstractPatientFragment {
    public IdentificacionPacienteFragment()
    {

    }
    private Date birthDate;
    private GridView gridViewDocumentTypes;
    private GridView gridViewMaritalStatuses;
    private RadioButton selectedGender;
    @Override
    public int getViewId() {
        return R.layout.fragment_identificacion_paciente;
    }

    @Override
    public int getFragmentTagId() {
        return R.string.fragment_identification_patient_tag;
    }
    public void getPatientIdentifierTypes() {
        try {
            List<PatientIdentifierTypeDto> patientIdentifierTypes = patientIdentifierTypeService.getPatientIdentifierTypes();
            if (lesionado != null && lesionado.getEncuentro() != null && lesionado.getEncuentro().getPatient() != null &&
                    lesionado.getEncuentro().getPatient().getPreferredIdentifier() != null &&
                    lesionado.getEncuentro().getPatient().getPreferredIdentifier().getPatientIdentifierType() != null) {
                for (PatientIdentifierTypeDto pi : patientIdentifierTypes) {
                    if (pi.getId() != null && pi.getId().equals(lesionado.getEncuentro().getPatient().getPreferredIdentifier().getPatientIdentifierType().getId())) {
                        pi.setSelected(Boolean.TRUE);
                    }
                }
            }
            final DocumentTypeArrayAdapter adapter = new DocumentTypeArrayAdapter(activity, R.id.tableRowSignosVitales, patientIdentifierTypes, this, lesionado);
            gridViewDocumentTypes.setAdapter(adapter);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void init() {
        initEditTexts();
        getPatientIdentifierTypes();
        getMaritalStatuses();
        getBirthDate();
        initGenero();
    }
    public void initGenero()
    {
        RadioButton radioButton = (RadioButton)view.findViewById(R.id.radioButtonMasculino);
        radioButton.setChecked(testPerson() && radioButton.getText().toString().equals(lesionado.getEncuentro().getPatient().getPerson().getGender()));
        if(radioButton.isChecked())
        {
            selectedGender = radioButton;
        }
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v instanceof RadioButton) {
                    if (selectedGender != null) {
                        selectedGender.setChecked(false);
                    }
                    selectedGender = (RadioButton) v;
                    selectedGender.setChecked(true);
                }
            }
        };
        radioButton.setOnClickListener(listener);
        CompoundButton.OnCheckedChangeListener changeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(testPerson() && isChecked)
                {
                    lesionado.getEncuentro().getPatient().getPerson().setGender(buttonView.getText().toString());
                    saveLesionado();
                }
            }
        };
        radioButton.setOnCheckedChangeListener(changeListener);
        radioButton = (RadioButton)view.findViewById(R.id.radioButtonFemenino);
        radioButton.setChecked(testPerson() && radioButton.getText().toString().equals(lesionado.getEncuentro().getPatient().getPerson().getGender()));
        if(radioButton.isChecked())
        {
            selectedGender = radioButton;
        }
        radioButton.setOnClickListener(listener);
        radioButton.setOnCheckedChangeListener(changeListener);
    }
    public Boolean testPerson()
    {
        return lesionado != null && lesionado.getEncuentro() != null && lesionado.getEncuentro().getPatient() != null && lesionado.getEncuentro().getPatient().getPerson() != null;
    }
    @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        view = super.onCreateView(inflater, container, savedInstanceState);
        gridViewDocumentTypes = (GridView)view.findViewById(R.id.gridViewDocumentTypes);
        gridViewMaritalStatuses = (GridView)view.findViewById(R.id.gridViewMaritalStatuses);
        View button = view.findViewById(R.id.buttonIdentificacion);
        button.setActivated(Boolean.TRUE);

        init();
        return view;
    }
    public void initEditTexts()
    {
        EditText editText = (EditText)view.findViewById(R.id.editTextNumeroDocumento);
        if (lesionado != null && lesionado.getEncuentro() != null && lesionado.getEncuentro().getPatient() != null &&
                lesionado.getEncuentro().getPatient().getPreferredIdentifier() != null) {

            editText.setText(lesionado.getEncuentro().getPatient().getPreferredIdentifier().getIdentifier());
        }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().isEmpty()) {
                    if (lesionado != null && lesionado.getEncuentro() != null && lesionado.getEncuentro().getPatient() != null && lesionado.getEncuentro().getPatient().getPreferredIdentifier() != null) {
                        lesionado.getEncuentro().getPatient().getPreferredIdentifier().setIdentifier(s.toString());
                        saveLesionado();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editText = (EditText)view.findViewById(R.id.editTextPrimerNombre);
        if(testPatientNameNotNull()) {
            editText.setText(lesionado.getEncuentro().getPatient().getPerson().getPreferredName().getGivenName());

            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (testPatientNameNotNull()) {
                        lesionado.getEncuentro().getPatient().getPerson().getPreferredName().setGivenName(s.toString());
                        saveLesionado();
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            editText = (EditText) view.findViewById(R.id.editTextSegundoNombre);
            editText.setText(lesionado.getEncuentro().getPatient().getPerson().getPreferredName().getMiddleName());
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(testPatientNameNotNull())
                    {
                        lesionado.getEncuentro().getPatient().getPerson().getPreferredName().setMiddleName(s.toString());
                        saveLesionado();
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
        editText = (EditText) view.findViewById(R.id.editTextPrimerApellido);
        editText.setText(lesionado.getEncuentro().getPatient().getPerson().getPreferredName().getFamilyName());
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(testPatientNameNotNull())
                {
                    lesionado.getEncuentro().getPatient().getPerson().getPreferredName().setFamilyName(s.toString());
                    saveLesionado();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editText = (EditText) view.findViewById(R.id.editTextSegundoApellido);
        editText.setText(lesionado.getEncuentro().getPatient().getPerson().getPreferredName().getFamilyName2());
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(testPatientNameNotNull())
                {
                    lesionado.getEncuentro().getPatient().getPerson().getPreferredName().setFamilyName2(s.toString());
                    saveLesionado();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
    private Boolean testPatientNameNotNull()
    {
        return lesionado != null && lesionado.getEncuentro() != null && lesionado.getEncuentro().getPatient() != null && lesionado.getEncuentro().getPatient().getPerson() != null &&
                lesionado.getEncuentro().getPatient().getPerson().getPreferredName() != null;
    }
    public void getBirthDate()
    {
        final TextView textViewFechaHora = (TextView) view.findViewById(R.id.textViewFechaNacimiento);
        ImageButton buttonFechaHora = (ImageButton) view.findViewById(R.id.imageButtonFechaNacimiento);
        final EditText editTextMonths = (EditText)view.findViewById(R.id.editTextMonths);
        final EditText editTextDays = (EditText)view.findViewById(R.id.editTextDays);
        final EditText editTextYears = (EditText)view.findViewById(R.id.editTextYears);
        editTextDays.setKeyListener(null);
        editTextMonths.setKeyListener(null);
        editTextYears.setKeyListener(null);
        DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/MM/yyyy");
        Date date = new Date();
        DateTime currentTime = new DateTime(date);
        if(lesionado.getEncuentro() != null && lesionado.getEncuentro().getPatient() != null &&
            lesionado.getEncuentro().getPatient().getPerson() != null && lesionado.getEncuentro().getPatient().getPerson().getBirthdate() != null)
        {
            date = lesionado.getEncuentro().getPatient().getPerson().getBirthdate();
        }
        DateTime jodatime = new DateTime(date);
        textViewFechaHora.setText(dtf.print(jodatime));
        birthDate = date;
        Period period = new Period(jodatime, currentTime, PeriodType.yearMonthDay());
        editTextDays.setText(String.valueOf(period.getDays()));
        editTextMonths.setText(String.valueOf(period.getMonths()));
        editTextYears.setText(String.valueOf(period.getYears()));
        textViewFechaHora.setText(dtf.print(jodatime));
        buttonFechaHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date value = birthDate;
                final Calendar cal = Calendar.getInstance();
                cal.setTime(value);
                new DatePickerDialog(activity,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view,
                                                  int y, int m, int d) {
                                cal.set(Calendar.YEAR, y);
                                cal.set(Calendar.MONTH, m);
                                cal.set(Calendar.DAY_OF_MONTH, d);
                                Date date = cal.getTime();
                                DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/MM/yyyy");
                                DateTime jodatime = new DateTime(date);
                                Date current = new Date();
                                DateTime currentTime = new DateTime(current);
                                Period period = new Period(jodatime, currentTime, PeriodType.yearMonthDay());
                                editTextDays.setText(String.valueOf(period.getDays()));
                                editTextMonths.setText(String.valueOf(period.getMonths()));
                                editTextYears.setText(String.valueOf(period.getYears()));
                                textViewFechaHora.setText(dtf.print(jodatime));
                                birthDate = date;
                                if(lesionado.getEncuentro() != null && lesionado.getEncuentro().getPatient() != null &&
                                         lesionado.getEncuentro().getPatient().getPerson() != null)
                                {
                                    lesionado.getEncuentro().getPatient().getPerson().setBirthdate(birthDate);
                                    saveLesionado();
                                }
                            }
                        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)
                ).show();
            }
        });
    }
    private void getMaritalStatuses() {
        try {
            List<ConceptDto> maritalStatuses = conceptService.getMaritalStatuses();
            if(lesionado != null && lesionado.getEncuentro().getPatient() != null && lesionado.getEncuentro().getPatient().getMaritalStatus() != null)
            {
                for(ConceptDto ms : maritalStatuses)
                {
                    if(ms.getId() != null && ms.getId().equals(lesionado.getEncuentro().getPatient().getMaritalStatus().getId()))
                    {
                        ms.setSelected(Boolean.TRUE);
                    }
                    else
                    {
                        ms.setSelected(Boolean.FALSE);
                    }
                }
            }
            ConceptArrayAdapter adapter = new ConceptArrayAdapter(getActivity(), R.id.tableRowSignosVitales, maritalStatuses);
            adapter.setLesionado(lesionado);
            adapter.setConceptType(ConceptArrayAdapter.ConceptType.MARITAL_STATUS);
            adapter.setAbstractPatientFragment(this);
            gridViewMaritalStatuses.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            for(View v : gridViewMaritalStatuses.getTouchables())
            {
                ((RadioButton)v).setChecked(true);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   /* private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new LesionFragment();
        }

        @Override
        public int getCount() {
            return 5;
        }
    }*/
}
