package com.artica.telesalud.tph.android.activity;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.StackView;

import com.artica.telesalud.tph.android.R;
import com.artica.telesalud.tph.android.dao.DatabaseHelper;
import com.artica.telesalud.tph.android.dao.EvaluacionCompletaService;
import com.artica.telesalud.tph.android.dao.EvaluacionService;
import com.artica.telesalud.tph.android.dao.EventService;
import com.artica.telesalud.tph.android.dao.LesionadoService;
import com.artica.telesalud.tph.android.intentservice.SynchronizeIntentService;
import com.artica.telesalud.tph.android.lightweightmodel.EvaluacionCompletaDto;
import com.artica.telesalud.tph.android.lightweightmodel.EvaluacionDto;
import com.artica.telesalud.tph.android.lightweightmodel.EventDto;
import com.artica.telesalud.tph.android.lightweightmodel.LesionadoDto;
import com.artica.telesalud.tph.android.lightweightmodel.UserDto;
import com.artica.telesalud.tph.android.service.CreateEvaluacionPatientService;
import com.artica.telesalud.tph.android.service.SaveEvaluacionPatientService;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CreatePatientTriageActivity extends BaseActivity implements View.OnClickListener {
    private CheckBox buttonSelected;
    private EventDto event;
    private UserDto userInSession;
    private String lesionadoId;
    private ActionBar bar;
    private HashMap<Integer, List<View>> triageMap = new HashMap<Integer, List<View>>();
    private DatabaseHelper databaseHelper;
    private Boolean patientCreated = Boolean.FALSE;
    private StackView stackViewTriage;
    private View viewTriageWhite;

    private View viewTriageRed;
    private View viewTriageYellow;
    private View viewTriageBlack;
    private View viewTriageGreen;
    private View views[];
    @Override
    public void onClick(View v) {
        if(v != null && v instanceof CheckBox) {

            if (buttonSelected != null) {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_patient_triage);
        stackViewTriage = (StackView)findViewById(R.id.stackViewTriage);
        if(getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //check if no view has focus:
        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null) {
            userInSession = bundle.getParcelable(UserDto.class.getCanonicalName());
            event = bundle.getParcelable(EventDto.class.getCanonicalName());
            lesionadoId = bundle.getString(getString(R.string.current_patients_extra_name));
        }
        if(getActionBar() != null) {
            bar = getActionBar();
            bar.setDisplayHomeAsUpEnabled(true);
            if(lesionadoId != null) {
                bar.setTitle("Paciente " + lesionadoId);
            }
            bar.setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar_background));
            bar.setDisplayOptions(
                    ActionBar.DISPLAY_SHOW_CUSTOM
                            | ActionBar.DISPLAY_HOME_AS_UP
                            | ActionBar.DISPLAY_SHOW_HOME
                            | ActionBar.DISPLAY_SHOW_TITLE);
        }
        init();
    }
    public void init()
    {
        LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewTriageRed = vi.inflate(R.layout.radio_group_triage_red, null);
        viewTriageYellow = vi.inflate(R.layout.radio_group_triage_yellow, null);
        viewTriageBlack = vi.inflate(R.layout.radio_group_triage_black, null);
        viewTriageGreen = vi.inflate(R.layout.radio_group_triage_green, null);
        viewTriageWhite = vi.inflate(R.layout.radio_group_triage_white, null);
        views = new View[]{viewTriageRed, viewTriageYellow, viewTriageBlack, viewTriageGreen, viewTriageWhite};
        List<String> list = Arrays.asList("", "","","","");
        StackAdapter adapter = new StackAdapter(this, list);
        stackViewTriage.setAdapter(adapter);
        buttonSelected = null;
        patientCreated = Boolean.FALSE;
        CheckBox checkBoxTriageRed = (CheckBox)viewTriageRed.findViewById(R.id.imageButtonTriageRed);
        CheckBox checkBoxTriageYellow = (CheckBox)viewTriageYellow.findViewById(R.id.imageButtonTriageYellow);
        CheckBox checkBoxTriageGreen = (CheckBox)viewTriageGreen.findViewById(R.id.imageButtonTriageGreen);
        CheckBox checkBoxTriageBlack = (CheckBox)viewTriageBlack.findViewById(R.id.imageButtonTriageBlack);
        CheckBox checkBoxTriageWhite = (CheckBox)viewTriageWhite.findViewById(R.id.imageButtonTriageWhite);
        checkBoxTriageBlack.setOnClickListener(this);
        checkBoxTriageRed.setOnClickListener(this);
        checkBoxTriageYellow.setOnClickListener(this);
        checkBoxTriageGreen.setOnClickListener(this);
        checkBoxTriageWhite.setOnClickListener(this);
        List<View> listTriageRed = new ArrayList<View>();
        listTriageRed.add(viewTriageRed.findViewById(R.id.radioButtonViaAreaRiesgo));
        listTriageRed.add(viewTriageRed.findViewById(R.id.radioButtonInsuficienciaRespiratoria));
        listTriageRed.add(viewTriageRed.findViewById(R.id.radioButtonPa));
        listTriageRed.add(viewTriageRed.findViewById(R.id.radioButtonMultiplesHeridas));
        listTriageRed.add(viewTriageRed.findViewById(R.id.radioButtonHemorragiasNoControladas));
        listTriageRed.add(viewTriageRed.findViewById(R.id.radioButtonLesionCervicaIncompleta));
        listTriageRed.add(viewTriageRed.findViewById(R.id.radioButtonGlasgow48));
        listTriageRed.add(viewTriageRed.findViewById(R.id.radioButtonExcitacionPsicomotora));
        listTriageRed.add(viewTriageRed.findViewById(R.id.radioButtonAbdomenAgudo));
        listTriageRed.add(viewTriageRed.findViewById(R.id.radioButtonEvisceracion));
        listTriageRed.add(viewTriageRed.findViewById(R.id.radioButtonTrabajoPartoSangrado));
        triageMap.put(R.id.imageButtonTriageRed, listTriageRed);
        List<View> listTriageYellow = new ArrayList<View>();
        listTriageYellow.add(viewTriageYellow.findViewById(R.id.radioButtonDolorToracico));
        listTriageYellow.add(viewTriageYellow.findViewById(R.id.radioButtonArritmias));
        listTriageYellow.add(viewTriageYellow.findViewById(R.id.radioButtonHemorragiasControladas));
        listTriageYellow.add(viewTriageYellow.findViewById(R.id.radioButtonLesionMedularDorsal));
        listTriageYellow.add(viewTriageYellow.findViewById(R.id.radioButtonGlasgow913));
        listTriageYellow.add(viewTriageYellow.findViewById(R.id.radioButtonAlteracionEstadoConciencia));
        listTriageYellow.add(viewTriageYellow.findViewById(R.id.radioButtonFracturasMayores));
        listTriageYellow.add(viewTriageYellow.findViewById(R.id.radioButtonQuemadurasModeradas));
        listTriageYellow.add(viewTriageYellow.findViewById(R.id.radioButtonIntoxicacionTriage));
        triageMap.put(R.id.imageButtonTriageYellow, listTriageYellow);
        List<View> listTriageGreen = new ArrayList<View>();
        listTriageGreen.add(viewTriageGreen.findViewById(R.id.radioButtonLesionMedularLumbar));
        listTriageGreen.add(viewTriageGreen.findViewById(R.id.radioButtonGlasgow1415));
        listTriageGreen.add(viewTriageGreen.findViewById(R.id.radioButtonFracturasNoProximales));
        listTriageGreen.add(viewTriageGreen.findViewById(R.id.radioButtonLesionesSuperficiales));
        listTriageGreen.add(viewTriageGreen.findViewById(R.id.radioButtonQuemadurasGradoI));
        listTriageGreen.add(viewTriageGreen.findViewById(R.id.radioButtonAfectados));
        triageMap.put(R.id.imageButtonTriageGreen, listTriageGreen);
        List<View> listTriageBlack = new ArrayList<View>();
        listTriageBlack.add(viewTriageBlack.findViewById(R.id.radioButtonParoProlongado));
        listTriageBlack.add(viewTriageBlack.findViewById(R.id.radioButtonLesionCervicalCompleta));
        listTriageBlack.add(viewTriageBlack.findViewById(R.id.radioButtonGlasgow3));
        listTriageBlack.add(viewTriageBlack.findViewById(R.id.radioButtonExposicionMasaEncefalica));
        listTriageBlack.add(viewTriageBlack.findViewById(R.id.radioButtonLesionesImpidenRCP));
        listTriageBlack.add(viewTriageBlack.findViewById(R.id.radioButtonQuemadurasGraves));
        triageMap.put(R.id.imageButtonTriageBlack, listTriageBlack);
        List<View> listTriageWhite = new ArrayList<View>();
        listTriageWhite.add(viewTriageWhite.findViewById(R.id.radioButtonCadenaCustodia));
        listTriageWhite.add(viewTriageWhite.findViewById(R.id.radioButtonEntregadoEntidad));
        CheckBox localCheckBox = (CheckBox)viewTriageWhite.findViewById(R.id.radioButtonEntregadoEntidad);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                viewTriageWhite.findViewById(R.id.editTextRegistroPersonaRecepcion).setEnabled(isChecked);
                viewTriageWhite.findViewById(R.id.editTextNombrePersonaRecepcion).setEnabled(isChecked);
                viewTriageWhite.findViewById(R.id.editTextEntidadRecepcionDifunto).setEnabled(isChecked);
            }
        });
        listTriageWhite.add(viewTriageWhite.findViewById(R.id.editTextRegistroPersonaRecepcion));
        listTriageWhite.add(viewTriageWhite.findViewById(R.id.editTextNombrePersonaRecepcion));
        listTriageWhite.add(viewTriageWhite.findViewById(R.id.editTextEntidadRecepcionDifunto));
        triageMap.put(R.id.imageButtonTriageWhite, listTriageWhite);
        for(Integer key : triageMap.keySet())
        {
            List<View> checkBoxes = triageMap.get(key);
            for(View checkBox : checkBoxes)
            {
                checkBox.setEnabled(false);
                if(checkBox instanceof  CheckBox) {
                    ((CheckBox)checkBox).setChecked(false);
                }
            }
        }
    }
    public void clear()
    {
        if (buttonSelected != null) {
            buttonSelected.setChecked(false);
            List<View> checkBoxes = triageMap.get(buttonSelected.getId());
            if (checkBoxes != null) {
                for (View checkBox : checkBoxes) {
                    checkBox.setEnabled(false);
                    if (checkBox instanceof CheckBox) {
                        ((CheckBox) checkBox).setChecked(Boolean.FALSE);
                    }
                    if (checkBox instanceof EditText) {
                        ((EditText) checkBox).setText("");
                    }
                }
            }
        }
        buttonSelected = null;
        patientCreated = Boolean.FALSE;
        for(Integer key : triageMap.keySet())
        {
            List<View> checkBoxes = triageMap.get(key);
            for(View checkBox : checkBoxes)
            {
                checkBox.setEnabled(false);
                if(checkBox instanceof  CheckBox) {
                    ((CheckBox)checkBox).setChecked(false);
                }
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.create_patient_triage, menu);

        MenuItem showPatientItem = menu.findItem(R.id.action_show_user_id);
        showPatientItem.setTitle(userInSession.getPersonName());
        MenuItem showEventItem = menu.findItem(R.id.action_show_event);
        showEventItem.setTitle("Evento "+event.getCaseNumber());
        return true;
    }
    public void onStart()
    {
        super.onStart();
        patientCreated = Boolean.FALSE;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.action_save_patient)
        {
            try {
                createPatient();
            } catch (SQLException e) {
                Log.e(CreatePatientTriageActivity.class.getCanonicalName(), e.getMessage());
            }
            return true;
        }
        if(id == android.R.id.home) {
            if(event != null && userInSession != null) {
                Intent intent = new Intent(CreatePatientTriageActivity.this, EventActivity.class);
                intent.putExtra(
                        UserDto.class.getCanonicalName(),
                        (Parcelable) userInSession);
                intent.putExtra(EventDto.class.getCanonicalName(), (Parcelable) event);
                startActivity(intent);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void createPatient() throws SQLException {
        if(event != null && userInSession != null && !patientCreated) {
            patientCreated = Boolean.TRUE;
            Integer eventLocalId = Integer.parseInt(lesionadoId.substring(0, lesionadoId.indexOf("/")));
            EventService eventService = new EventService(this.getHelper());
            EventDto eventDto = eventService.getEventById(event.getId());
            if (eventDto != null) {
                event = eventDto;
            }
            LesionadoService lesionadoService = new LesionadoService(getHelper());
            LesionadoDto lesionado = lesionadoService.createNew(event, userInSession.getUserId());
            lesionado.setEventLocalIdentifier(eventLocalId);
            lesionadoService.save(lesionado);
            EvaluacionCompletaService evaluacionService = new EvaluacionCompletaService(getHelper());
            EvaluacionCompletaDto evaluacion = evaluacionService.createNew(lesionado.getEncuentro(), userInSession.getUserId());
            EvaluacionDto evaluacionDto = new EvaluacionDto();
            evaluacionDto.setEvaluacion(evaluacion);
            evaluacionDto.setPrioridadTriage(getSelectedTriage());
            evaluacionDto = getEvaluacion(evaluacionDto);
            EvaluacionService evaluacionDtoService = new EvaluacionService(getHelper());
            evaluacionDtoService.save(evaluacionDto);
            if(event != null)
            {
                event.setPatientsCount(event.getPatientsCount()+1);
            }
            patientCreated();
        }
    }

    public void callSynchronizeService()
    {
        SynchronizeIntentService.startActionSynchronize(this);
    }
    public void proccessNewLesionado(LesionadoDto lesionado)
    {
        if(userInSession != null) {
            Integer userId = userInSession.getUserId();
            if (lesionado != null && lesionado.getEncuentro() != null) {
                new CreateEvaluacionPatientService(this).execute(lesionado.getEncuentro().getEncounterId(), userId);
            }
        }
    }
    public void saveTriagePatient(EvaluacionCompletaDto evaluacion)
    {
        EvaluacionDto evaluacionDto = new EvaluacionDto();
        evaluacionDto.setEvaluacion(evaluacion);
        evaluacionDto.setPrioridadTriage(getSelectedTriage());
        evaluacionDto = getEvaluacion(evaluacionDto);
        new SaveEvaluacionPatientService(this).execute(evaluacionDto);
    }
    public void patientCreated()
    {
        String[] split = lesionadoId.split("/");
        Integer id = Integer.parseInt(split[0]);
        Integer quantity = Integer.parseInt(split[1]);

        if(quantity.equals(id)) {

            Intent intent = new Intent(CreatePatientTriageActivity.this,
                    EventActivity.class);
            intent.putExtra(EventDto.class.getCanonicalName(), (Parcelable)event);
            intent.putExtra(UserDto.class.getCanonicalName(), (Parcelable)userInSession);
            startActivity(intent);
        }
        else
        {
            lesionadoId = (id+1)+"/"+quantity;
            clear();
        }

        if(lesionadoId != null && bar != null) {
            bar.setTitle("Paciente " + lesionadoId);
        }
    }
    public void triageAlmacenado(List<EvaluacionDto> evaluacion)
    {
        patientCreated();
    }
    @Override
    public DatabaseHelper getHelper() {
        if(databaseHelper == null)
        {
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper;
    }

    @Override
    public void showSuccessMessage(String message) {

    }

    @Override
    public void showErrorMessage(String message) {

    }
    public Integer getSelectedTriage()
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
    public EvaluacionDto getEvaluacion(EvaluacionDto evaluacion)
    {
        evaluacion.setTriageViaAreaObstruida(((CheckBox)viewTriageRed.findViewById(R.id.radioButtonViaAreaRiesgo)).isChecked() && (viewTriageRed.findViewById(R.id.radioButtonViaAreaRiesgo)).isEnabled());
        evaluacion.setTriageInsuficienciaRespiratoria(((CheckBox) viewTriageRed.findViewById(R.id.radioButtonInsuficienciaRespiratoria)).isChecked() && (viewTriageRed.findViewById(R.id.radioButtonInsuficienciaRespiratoria)).isEnabled());
        evaluacion.setTriagePa90(((CheckBox) viewTriageRed.findViewById(R.id.radioButtonPa)).isChecked() && viewTriageRed.findViewById(R.id.radioButtonPa).isEnabled());
        evaluacion.setTriageMultiplesHeridas(((CheckBox) viewTriageRed.findViewById(R.id.radioButtonMultiplesHeridas)).isChecked() && viewTriageRed.findViewById(R.id.radioButtonMultiplesHeridas).isEnabled());
        evaluacion.setTriageHemorragiasNoControladas(((CheckBox) viewTriageRed.findViewById(R.id.radioButtonHemorragiasNoControladas)).isChecked() && viewTriageRed.findViewById(R.id.radioButtonHemorragiasNoControladas).isEnabled());
        evaluacion.setTriageLesionCervicalIncompleta(((CheckBox) viewTriageRed.findViewById(R.id.radioButtonLesionCervicaIncompleta)).isChecked() && viewTriageRed.findViewById(R.id.radioButtonLesionCervicaIncompleta).isEnabled());
        evaluacion.setTriageGlasgow4a8(((CheckBox) viewTriageRed.findViewById(R.id.radioButtonGlasgow48)).isChecked() && viewTriageRed.findViewById(R.id.radioButtonGlasgow48).isEnabled());
        evaluacion.setTriageExcitacionPsicomotora(((CheckBox) viewTriageRed.findViewById(R.id.radioButtonExcitacionPsicomotora)).isChecked() && viewTriageRed.findViewById(R.id.radioButtonExcitacionPsicomotora).isEnabled());
        evaluacion.setTriageAbdomenAgudo(((CheckBox) viewTriageRed.findViewById(R.id.radioButtonAbdomenAgudo)).isChecked() && viewTriageRed.findViewById(R.id.radioButtonAbdomenAgudo).isEnabled());
        evaluacion.setTriageEvisceracion(((CheckBox) viewTriageRed.findViewById(R.id.radioButtonEvisceracion)).isChecked() && viewTriageRed.findViewById(R.id.radioButtonEvisceracion).isEnabled());
        evaluacion.setTriageTrabajoPartoSangrado(((CheckBox) viewTriageRed.findViewById(R.id.radioButtonTrabajoPartoSangrado)).isChecked() && viewTriageRed.findViewById(R.id.radioButtonTrabajoPartoSangrado).isEnabled());

        evaluacion.setTriageDolorToracico(((CheckBox) viewTriageYellow.findViewById(R.id.radioButtonDolorToracico)).isChecked() && viewTriageYellow.findViewById(R.id.radioButtonDolorToracico).isEnabled());
        evaluacion.setTriageArritmias(((CheckBox) viewTriageYellow.findViewById(R.id.radioButtonArritmias)).isChecked() && viewTriageYellow.findViewById(R.id.radioButtonArritmias).isEnabled());
        evaluacion.setTriageHemorragiasControladas(((CheckBox) viewTriageYellow.findViewById(R.id.radioButtonHemorragiasControladas)).isChecked() && viewTriageYellow.findViewById(R.id.radioButtonHemorragiasControladas).isEnabled());
        evaluacion.setTriageLesionMedularDorsal(((CheckBox) viewTriageYellow.findViewById(R.id.radioButtonLesionMedularDorsal)).isChecked() && viewTriageYellow.findViewById(R.id.radioButtonLesionMedularDorsal).isEnabled());
        evaluacion.setTriageGlasgow9a13(((CheckBox) viewTriageYellow.findViewById(R.id.radioButtonGlasgow913)).isChecked() && viewTriageYellow.findViewById(R.id.radioButtonGlasgow913).isEnabled());
        evaluacion.setTriageAlteracionEstadoConciencia(((CheckBox) viewTriageYellow.findViewById(R.id.radioButtonAlteracionEstadoConciencia)).isChecked() && viewTriageYellow.findViewById(R.id.radioButtonAlteracionEstadoConciencia).isEnabled());
        evaluacion.setTriageFracturasMayores(((CheckBox) viewTriageYellow.findViewById(R.id.radioButtonFracturasMayores)).isChecked() && viewTriageYellow.findViewById(R.id.radioButtonFracturasMayores).isEnabled());
        evaluacion.setTriageQuemadurasModeradas(((CheckBox) viewTriageYellow.findViewById(R.id.radioButtonQuemadurasModeradas)).isChecked() && viewTriageYellow.findViewById(R.id.radioButtonQuemadurasModeradas).isEnabled());
        evaluacion.setTriageIntoxicacion(((CheckBox) viewTriageYellow.findViewById(R.id.radioButtonIntoxicacionTriage)).isChecked() && viewTriageYellow.findViewById(R.id.radioButtonIntoxicacionTriage).isEnabled());

        evaluacion.setTriageLesionMedularLumbar(((CheckBox) viewTriageGreen.findViewById(R.id.radioButtonLesionMedularLumbar)).isChecked() && viewTriageGreen.findViewById(R.id.radioButtonLesionMedularLumbar).isEnabled());
        evaluacion.setTriageGlasgow14o15(((CheckBox) viewTriageGreen.findViewById(R.id.radioButtonGlasgow1415)).isChecked() && viewTriageGreen.findViewById(R.id.radioButtonGlasgow1415).isEnabled());
        evaluacion.setTriageFracturasNoProximales(((CheckBox) viewTriageGreen.findViewById(R.id.radioButtonFracturasNoProximales)).isChecked() && viewTriageGreen.findViewById(R.id.radioButtonFracturasNoProximales).isEnabled());
        evaluacion.setTriageLesionesSuperficiales(((CheckBox) viewTriageGreen.findViewById(R.id.radioButtonLesionesSuperficiales)).isChecked() && viewTriageGreen.findViewById(R.id.radioButtonLesionesSuperficiales).isEnabled());
        evaluacion.setTriageQuemadurasPrimerGrado(((CheckBox) viewTriageGreen.findViewById(R.id.radioButtonQuemadurasGradoI)).isChecked() && viewTriageGreen.findViewById(R.id.radioButtonQuemadurasGradoI).isEnabled());
        evaluacion.setTriageAfectados(((CheckBox) viewTriageGreen.findViewById(R.id.radioButtonAfectados)).isChecked() && viewTriageGreen.findViewById(R.id.radioButtonAfectados).isEnabled());

        evaluacion.setTriageParoProlongado(((CheckBox) viewTriageBlack.findViewById(R.id.radioButtonParoProlongado)).isChecked() && viewTriageBlack.findViewById(R.id.radioButtonParoProlongado).isEnabled());
        evaluacion.setTriageLesionCervicalCompleta(((CheckBox) viewTriageBlack.findViewById(R.id.radioButtonLesionCervicalCompleta)).isChecked() && viewTriageBlack.findViewById(R.id.radioButtonLesionCervicalCompleta).isEnabled());
        evaluacion.setTriageGlasgow3(((CheckBox) viewTriageBlack.findViewById(R.id.radioButtonGlasgow3)).isChecked() && viewTriageBlack.findViewById(R.id.radioButtonGlasgow3).isEnabled());
        evaluacion.setTriageExposicionMasaEncefalica(((CheckBox) viewTriageBlack.findViewById(R.id.radioButtonExposicionMasaEncefalica)).isChecked() && viewTriageBlack.findViewById(R.id.radioButtonExposicionMasaEncefalica).isEnabled());
        evaluacion.setTriageLesionesImpidenRcp(((CheckBox) viewTriageBlack.findViewById(R.id.radioButtonLesionesImpidenRCP)).isChecked() && viewTriageBlack.findViewById(R.id.radioButtonLesionesImpidenRCP).isEnabled());
        evaluacion.setTriageQuemadurasGraves(((CheckBox) viewTriageBlack.findViewById(R.id.radioButtonQuemadurasGraves)).isChecked() && viewTriageBlack.findViewById(R.id.radioButtonQuemadurasGraves).isEnabled());

        evaluacion.setTriageCadenaCustodia(((CheckBox) viewTriageWhite.findViewById(R.id.radioButtonCadenaCustodia)).isChecked() && viewTriageWhite.findViewById(R.id.radioButtonCadenaCustodia).isEnabled());
        evaluacion.setTriageEntregadoEntidad(((CheckBox) viewTriageWhite.findViewById(R.id.radioButtonEntregadoEntidad)).isChecked() && viewTriageWhite.findViewById(R.id.radioButtonEntregadoEntidad).isEnabled());
        evaluacion.setTriageEntidadEntregaPacienteDifunto(((EditText) viewTriageWhite.findViewById(R.id.editTextEntidadRecepcionDifunto)).getText().toString());
        evaluacion.setTriageNombrePersonaRecibeDifunto(((EditText) viewTriageWhite.findViewById(R.id.editTextNombrePersonaRecepcion)).getText().toString());
        evaluacion.setTriageRegistroPersonaRecibeDifunto(((EditText) viewTriageWhite.findViewById(R.id.editTextRegistroPersonaRecepcion)).getText().toString());

        return evaluacion;
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
                return views[position];
            }
            return convertView;
        }
    }
}
