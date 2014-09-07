package com.artica.telesalud.tph.android.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

import com.artica.telesalud.tph.android.R;
import com.artica.telesalud.tph.android.dao.DatabaseHelper;
import com.artica.telesalud.tph.android.dao.LesionadoService;
import com.artica.telesalud.tph.android.fragment.DummyTabContent;
import com.artica.telesalud.tph.android.fragment.EmergenciaMedicaFragment;
import com.artica.telesalud.tph.android.fragment.EvaluacionAFragment;
import com.artica.telesalud.tph.android.fragment.EvaluacionBFragment;
import com.artica.telesalud.tph.android.fragment.EvaluacionCFragment;
import com.artica.telesalud.tph.android.fragment.EvaluacionDFragment;
import com.artica.telesalud.tph.android.fragment.EvaluacionEFragment;
import com.artica.telesalud.tph.android.fragment.IdentificacionPacienteFragment;
import com.artica.telesalud.tph.android.fragment.InsumosFragment;
import com.artica.telesalud.tph.android.fragment.ProcedimientosFragment;
import com.artica.telesalud.tph.android.fragment.RemisionFragment;
import com.artica.telesalud.tph.android.fragment.SignosVitalesFragment;
import com.artica.telesalud.tph.android.fragment.TeleasistenciaFragment;
import com.artica.telesalud.tph.android.fragment.TraumasFragment;
import com.artica.telesalud.tph.android.lightweightmodel.EvaluacionDto;
import com.artica.telesalud.tph.android.lightweightmodel.EventDto;
import com.artica.telesalud.tph.android.lightweightmodel.LesionadoDto;
import com.artica.telesalud.tph.android.lightweightmodel.UserDto;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;

public class EvaluacionPatientActivity extends BaseActivity implements ActionBar.TabListener{



    private TabHost tabs;
    private Integer pagesCount = 8;
    private ViewPager pager;
    private UserDto userInSession;
    private EventDto event;
    private LesionadoDto lesionado;
    private EvaluacionDto evaluacionSelected;
    private DatabaseHelper databaseHelper;
    private String currentEvaluationCode;
    private PagerAdapter pagerAdapter;
    public EvaluacionDto getEvaluacionSelected() {
        return evaluacionSelected;
    }

    public void setEvaluacionSelected(EvaluacionDto evaluacionSelected) {
        this.evaluacionSelected = evaluacionSelected;
    }

    public LesionadoDto getLesionado() {
        return lesionado;
    }

    public void setLesionado(LesionadoDto lesionado) {
        this.lesionado = lesionado;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null) {
            lesionado = bundle.getParcelable(LesionadoDto.class.getCanonicalName());
            if(lesionado != null)
            {
                evaluacionSelected = lesionado.getEvaluaciones().get(0);
                evaluacionSelected.setSelected(Boolean.TRUE);
                lesionado.setIsSynchronized(Boolean.FALSE);
                try {
                    LesionadoService lesionadoService = new LesionadoService(getHelper());
                    lesionadoService.save(lesionado);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            userInSession = bundle.getParcelable(UserDto.class.getCanonicalName());
            event = bundle.getParcelable(EventDto.class.getCanonicalName());
            currentEvaluationCode = bundle.getString(getString(R.string.current_evaluation_code));
        }

        if(getActionBar() != null) {
            ActionBar bar = getActionBar();
            bar.setDisplayHomeAsUpEnabled(true);
            if(lesionado != null && event != null) {
               bar.setTitle("Paciente " + String.valueOf(lesionado.getEventLocalIdentifier()) + "/" + event.getPatientsCount());
            }
            bar.setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar_background));
            bar.setDisplayOptions(
                    ActionBar.DISPLAY_SHOW_CUSTOM
                    | ActionBar.DISPLAY_HOME_AS_UP
                    | ActionBar.DISPLAY_SHOW_HOME
                    | ActionBar.DISPLAY_SHOW_TITLE);
        }
        setContentView(R.layout.activity_evaluacion_patient);

        tabs = (TabHost) findViewById(R.id.tabHost);
        tabs.setup();
        pager = (ViewPager) findViewById(R.id.pagerEvaluations);
        final TabHost.TabSpec specEvaluacion = tabs.newTabSpec(getString(R.string.evaluacion_tab_text));
        specEvaluacion.setContent(new DummyTabContent(this));
        specEvaluacion.setIndicator(getString(R.string.evaluacion_tab_text));
        final TabHost.TabSpec specSignosVitales = tabs.newTabSpec(getString(R.string.signos_vitales_tab_text));
        specSignosVitales.setContent(new DummyTabContent(this));
        specSignosVitales.setIndicator(getString(R.string.signos_vitales_tab_text));

        final TabHost.TabSpec specInformacionPersonal= tabs.newTabSpec(getString(R.string.informacion_personal_tab_text));
        specInformacionPersonal.setContent(new DummyTabContent(this));
        specInformacionPersonal.setIndicator(getString(R.string.informacion_personal_tab_text));

        final TabHost.TabSpec specTeleasistencia= tabs.newTabSpec(getString(R.string.teleasistencia_tab_text));
        specTeleasistencia.setContent(new DummyTabContent(this));
        specTeleasistencia.setIndicator(getString(R.string.teleasistencia_tab_text));


        final TabHost.TabSpec specRegulacion= tabs.newTabSpec(getString(R.string.regulacion_tab_text));
        specRegulacion.setContent(new DummyTabContent(this));
        specRegulacion.setIndicator(getString(R.string.regulacion_tab_text));

        final TabHost.TabSpec specInsumos= tabs.newTabSpec(getString(R.string.insumos_tab_text));
        specInsumos.setContent(new DummyTabContent(this));
        specInsumos.setIndicator(getString(R.string.insumos_tab_text));
        tabs.addTab(specEvaluacion);
        tabs.addTab(specInsumos);
        tabs.addTab(specSignosVitales);
        tabs.addTab(specInformacionPersonal);
        tabs.addTab(specTeleasistencia);
        tabs.addTab(specRegulacion);
        tabs.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if(getString(R.string.evaluacion_tab_text).equals(tabId))
                {
                    setupEvaluacion();
                }
                if(getString(R.string.signos_vitales_tab_text).equals(tabId))
                {
                    setupSignosVitales();
                }
                if(getString(R.string.informacion_personal_tab_text).equals(tabId))
                {
                    setupInformacionPersonal();
                }
                if(getString(R.string.teleasistencia_tab_text).equals(tabId))
                {
                    setupTeleasistencia();
                }
                if(getString(R.string.regulacion_tab_text).equals(tabId))
                {
                    setupRegulacion();
                }
                if(getString(R.string.insumos_tab_text).equals(tabId))
                {
                    setupInsumos();
                }
            }
        });
        setupEvaluacion();
    }
    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in the ViewPager.
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {
    }
    private void setupInsumos()
    {

        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.realtabcontent,new InsumosFragment());
        ft.commit();
    }
    private void setupRegulacion()
    {

        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.realtabcontent,new RemisionFragment());
        ft.commit();
    }
    private void setupTeleasistencia()
    {

        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.realtabcontent,new TeleasistenciaFragment());
        ft.commit();
    }
    private void setupInformacionPersonal()
    {
        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.realtabcontent,new IdentificacionPacienteFragment());
        ft.commit();

    }
    private void setupSignosVitales()
    {
        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.realtabcontent, SignosVitalesFragment.newInstance());
        ft.commit();
    }
    private void setupEvaluacion()
    {
        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();
        if(getString(R.string.evaluation_a).equals(currentEvaluationCode)) {
            ft.replace(R.id.realtabcontent, EvaluacionAFragment.newInstance());
        }
        else if(getString(R.string.evaluation_b).equals(currentEvaluationCode))
        {
            ft.replace(R.id.realtabcontent, EvaluacionBFragment.newInstance());
        }
        else if(getString(R.string.evaluation_c).equals(currentEvaluationCode))
        {
            ft.replace(R.id.realtabcontent, new EvaluacionCFragment());
        }
        else if(getString(R.string.evaluation_d).equals(currentEvaluationCode))
        {
            ft.replace(R.id.realtabcontent, new EvaluacionDFragment());
        }
        else if(getString(R.string.evaluation_e).equals(currentEvaluationCode))
        {
            ft.replace(R.id.realtabcontent, new EvaluacionEFragment());
        }
        else if(getString(R.string.evaluation_emergencia_medica).equals(currentEvaluationCode))
        {
            ft.replace(R.id.realtabcontent, new EmergenciaMedicaFragment());
        }
        else if(getString(R.string.evaluation_trauma).equals(currentEvaluationCode))
        {
            ft.replace(R.id.realtabcontent, new TraumasFragment());
        }
        else if(getString(R.string.evaluation_procedimientos).equals(currentEvaluationCode))
        {
            ft.replace(R.id.realtabcontent, new ProcedimientosFragment());
        }
        else
        {
            ft.replace(R.id.realtabcontent, EvaluacionAFragment.newInstance());
        }
        ft.commit();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.evaluacion_patient, menu);
        MenuItem showPatientItem = menu.findItem(R.id.action_show_user_id);
        showPatientItem.setTitle(userInSession.getPersonName());
        MenuItem showEventItem = menu.findItem(R.id.action_show_event);
        showEventItem.setTitle("Evento "+event.getCaseNumber());
        return super.onCreateOptionsMenu(menu);
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
        if(android.R.id.home == id) {

            Intent intent = new Intent(this, EventActivity.class);
            intent.putExtra(
                    UserDto.class.getCanonicalName(),
                    (Parcelable) userInSession);

            intent.putExtra(EventDto.class.getCanonicalName(), (Parcelable) event);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public DatabaseHelper getHelper() {
        if(databaseHelper == null)
        {
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper                                                                                         ;
    }

    @Override
    public void showSuccessMessage(String message) {

    }

    @Override
    public void showErrorMessage(String message) {

    }
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            switch (position)
            {
                case 0:
                    return new EmergenciaMedicaFragment();
                case 1:
                    return new TraumasFragment();
                case 2:
                    return EvaluacionAFragment.newInstance();
                case 3:
                    return EvaluacionBFragment.newInstance();
                case 4:
                    return new EvaluacionCFragment();
                case 5:
                    return new EvaluacionDFragment();
                case 6:
                    return new EvaluacionEFragment();
                case 7:
                    return new ProcedimientosFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return pagesCount;
        }
    }

    public UserDto getUserInSession() {
        return userInSession;
    }

    public void setUserInSession(UserDto userInSession) {
        this.userInSession = userInSession;
    }

    public EventDto getEvent() {
        return event;
    }

    public void setEvent(EventDto event) {
        this.event = event;
    }
}
