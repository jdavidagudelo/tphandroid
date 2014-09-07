package com.artica.telesalud.tph.android.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.res.Resources;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.NavUtils;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.artica.telesalud.tph.android.R;
import com.artica.telesalud.tph.android.adapter.ConceptArrayAdapter;
import com.artica.telesalud.tph.android.adapter.LesionadoArrayAdapter;
import com.artica.telesalud.tph.android.dao.CityService;
import com.artica.telesalud.tph.android.dao.ConceptService;
import com.artica.telesalud.tph.android.dao.DatabaseHelper;
import com.artica.telesalud.tph.android.dao.EventService;
import com.artica.telesalud.tph.android.dao.LesionadoService;
import com.artica.telesalud.tph.android.dao.StateService;
import com.artica.telesalud.tph.android.intentservice.Constants;
import com.artica.telesalud.tph.android.intentservice.PatientsIntentService;
import com.artica.telesalud.tph.android.intentservice.SynchronizeIntentService;
import com.artica.telesalud.tph.android.lightweightmodel.CityDto;
import com.artica.telesalud.tph.android.lightweightmodel.ConceptDto;
import com.artica.telesalud.tph.android.lightweightmodel.CountryDto;
import com.artica.telesalud.tph.android.lightweightmodel.EvaluacionDto;
import com.artica.telesalud.tph.android.lightweightmodel.EventDto;
import com.artica.telesalud.tph.android.lightweightmodel.LesionadoDto;
import com.artica.telesalud.tph.android.lightweightmodel.StateDto;
import com.artica.telesalud.tph.android.lightweightmodel.UserDto;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EventActivity extends BaseActivity implements GoogleMap.OnMapLongClickListener,GoogleMap.OnMapClickListener,GoogleMap.OnMarkerDragListener, LocationListener,
        GooglePlayServicesClient.ConnectionCallbacks,
        GooglePlayServicesClient.OnConnectionFailedListener{
    private UserDto userInSession;

    private Menu optionsMenu;
    private EventDto event;
    private TabHost tabs;
    private DatabaseHelper databaseHelper;
    private EditText editTextOtraCausa;
    private GoogleMap googleMap;
    private ConceptArrayAdapter eventCausaAdapter;
    private LocationManager locationManager;
    private String provider;
    private Geocoder geocoder;
    private RadioButton selectedZone;
    private Date callDate;
    private static final int MILLISECONDS_PER_SECOND = 1000;
    private final static int
            CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    // Update frequency in seconds
    public static final int UPDATE_INTERVAL_IN_SECONDS = 5;
    // Update frequency in milliseconds
    private static final long UPDATE_INTERVAL =
            MILLISECONDS_PER_SECOND * UPDATE_INTERVAL_IN_SECONDS;
    // The fastest update frequency, in seconds
    private static final int FASTEST_INTERVAL_IN_SECONDS = 1;
    // A fast frequency ceiling in milliseconds
    private static final long FASTEST_INTERVAL =
            MILLISECONDS_PER_SECOND * FASTEST_INTERVAL_IN_SECONDS;
    private Location eventLocation = null;
    private LocationClient mLocationClient;
    private LocationRequest mLocationRequest;
    private Boolean locationRequested = Boolean.FALSE;
    private EditText editTextPatientsCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        editTextPatientsCount = ((EditText) findViewById(R.id.editTextPatientsQuantity));
        editTextPatientsCount.setEnabled(Boolean.FALSE);
        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        Spinner spinnerTipoVia = (Spinner) findViewById(R.id.spinnerTipoVia);
        Resources resources = getResources();

        spinnerTipoVia.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, resources.getStringArray(R.array.list_tipo_via)));

        Spinner spinnerCuadrante = (Spinner) findViewById(R.id.spinnerCuadrante);
        spinnerCuadrante.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, resources.getStringArray(R.array.list_cuadrante)));
        Spinner spinnerCuadranteSecundario = (Spinner) findViewById(R.id.spinnerCuadranteSecundario);
        spinnerCuadranteSecundario.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, resources.getStringArray(R.array.list_cuadrante)));

        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            userInSession = bundle.getParcelable(UserDto.class.getCanonicalName());
            event = bundle.getParcelable(EventDto.class.getCanonicalName());
            if (event != null) {
                editTextPatientsCount.setText(String.valueOf(event.getPatientsCount()));
            }
        }
        final Spinner spinnerState = (Spinner) findViewById(R.id.spinnerState);
        spinnerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                StateDto selected = (StateDto) spinnerState.getSelectedItem();

                if (selected != null && selected != StateDto.EMPTY_STATE) {
                    try {
                        CityService cityService = new CityService(getHelper());
                        List<CityDto> cities = cityService.getCitiesByState(selected.getStateId());
                        ArrayAdapter<CityDto> cityArrayAdapter = new ArrayAdapter<CityDto>(EventActivity.this,
                                android.R.layout.simple_spinner_item, cities);
                        cityArrayAdapter
                                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        Spinner spinnerCity = (Spinner) findViewById(R.id.spinnerCity);
                        Adapter adapter = spinnerCity.getAdapter();
                        List<CityDto> currentList = new ArrayList<CityDto>();
                        for (int i = 0; adapter != null && i < adapter.getCount(); i++) {
                            currentList.add((CityDto) adapter.getItem(i));
                        }
                        Collections.sort(cities);
                        Collections.sort(currentList);
                        if (!currentList.equals(cities)) {
                            spinnerCity.setAdapter(cityArrayAdapter);
                        }
                    } catch (SQLException e) {
                        Log.e(EventActivity.class.getCanonicalName(), e.getMessage());
                    }

                } else {
                    Spinner spinnerCity = (Spinner) findViewById(R.id.spinnerCity);
                    spinnerCity.setAdapter(new ArrayAdapter<CityDto>(EventActivity.this, android.R.layout.simple_spinner_item, new ArrayList<CityDto>()));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        final TextView textViewFechaHora = (TextView) findViewById(R.id.textViewFechaEvento);
        ImageButton buttonFechaHora = (ImageButton) findViewById(R.id.imageButtonFechaEvento);
        DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/MM/yyyy - HH:mm:ss");
        Date date = new Date();
        DateTime jodatime = new DateTime(date);
        textViewFechaHora.setText(dtf.print(jodatime));
        callDate = date;
        buttonFechaHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date value = callDate;
                final Calendar cal = Calendar.getInstance();
                cal.setTime(value);
                new DatePickerDialog(EventActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view,
                                                  int y, int m, int d) {
                                cal.set(Calendar.YEAR, y);
                                cal.set(Calendar.MONTH, m);
                                cal.set(Calendar.DAY_OF_MONTH, d);

                                // now show the time picker
                                new TimePickerDialog(EventActivity.this,
                                        new TimePickerDialog.OnTimeSetListener() {
                                            @Override
                                            public void onTimeSet(TimePicker view,
                                                                  int h, int min) {
                                                cal.set(Calendar.HOUR_OF_DAY, h);
                                                cal.set(Calendar.MINUTE, min);
                                                Date date = cal.getTime();
                                                DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/MM/yyyy - HH:mm:ss");
                                                DateTime jodatime = new DateTime(date);
                                                textViewFechaHora.setText(dtf.print(jodatime));
                                                callDate = date;
                                            }
                                        }, cal.get(Calendar.HOUR_OF_DAY),
                                        cal.get(Calendar.MINUTE), true
                                ).show();
                            }
                        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)
                ).show();
            }
        });

        tabs = (TabHost) findViewById(R.id.tabHost);
        tabs.setup();
        TabSpec specPacientes = tabs.newTabSpec(getString(R.string.tab_pacientes_text));

        specPacientes.setContent(R.id.pacientes);
        specPacientes.setIndicator(getString(R.string.tab_pacientes_text));

        TabSpec specInformacion = tabs.newTabSpec(getString(R.string.tab_informacion_evento_text));
        specInformacion.setContent(R.id.informacion_evento);
        specInformacion.setIndicator(getString(R.string.tab_informacion_evento_text));

        TabSpec specUbiManual = tabs.newTabSpec(getString(R.string.tab_ubicacion_manual_text));
        specUbiManual.setContent(R.id.ubicacion_manual);
        specUbiManual.setIndicator(getString(R.string.tab_ubicacion_manual_text));

        tabs.addTab(specPacientes);
        tabs.addTab(specInformacion);
        tabs.addTab(specUbiManual);

        geocoder = new Geocoder(EventActivity.this, Locale.US);
        // Get the location manager
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        Location location = locationManager.getLastKnownLocation(provider);
        editTextOtraCausa = (EditText) findViewById(R.id.editTextOtraCausa);
        editTextOtraCausa.setEnabled(false);

        if (event != null) {
            getEventCauses(event.getCausaAtencion());

            if (event.getOtherCause() != null) {
                editTextOtraCausa.setText(event.getOtherCause());
            }
            if (event.getCoordinates() != null) {
                if (getLocation(event.getCoordinates()) != null) {
                    location = getLocation(event.getCoordinates());
                }
            }
            if (event.getCallDate() != null) {
                callDate = event.getCallDate();
                DateTime callDateTime = new DateTime(callDate);
                textViewFechaHora.setText(dtf.print(callDateTime));
            }
            if (event.getCaseNumber() != null && event.getEventId() != null) {
                ((EditText) findViewById(R.id.editTextNumeroCaso)).setText(event.getCaseNumber());
            }
            if (event.getDireccion() != null) {
                EditText editTextOtraDireccion = (EditText) findViewById(R.id.editTextOtraDireccion);
                editTextOtraDireccion.setText(event.getDireccion());
            }

            if (event.getZone() != null) {
                setZona(event.getZone());
            }
        }
        if (location != null) {
            onLocationChanged(location);
            try {
                initilizeMap(location);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        View.OnClickListener listenerZona = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedZone != null) {
                    selectedZone.setChecked(false);
                }
                selectedZone = (RadioButton) v;
                selectedZone.setChecked(true);
            }
        };
        RadioButton radioButtonZonaUrbana = (RadioButton) findViewById(R.id.radioButtonZonaUrbana);
        RadioButton radioButtonZonaRural = (RadioButton) findViewById(R.id.radioButtonZonaRural);
        radioButtonZonaRural.setOnClickListener(listenerZona);
        radioButtonZonaUrbana.setOnClickListener(listenerZona);
        // Create the LocationRequest object
        mLocationRequest = LocationRequest.create();
        // Use high accuracy
        mLocationRequest.setPriority(
                LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        // Set the update interval to 5 seconds
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        // Set the fastest update interval to 1 second
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);

        // Create a new location client, using the enclosing class to
        //handle callbacks.

        mLocationClient = new LocationClient(EventActivity.this, EventActivity.this, EventActivity.this);
        // Start with updates turned off
        final GridView gridViewPacientes = (GridView) findViewById(R.id.listViewPatients);
        gridViewPacientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(EventActivity.this, EvaluacionPatientActivity.class);
                intent.putExtra(EventDto.class.getCanonicalName(), (Parcelable) event);
                intent.putExtra(UserDto.class.getCanonicalName(), (Parcelable) userInSession);
                intent.putExtra(LesionadoDto.class.getCanonicalName(), (Parcelable) gridViewPacientes.getAdapter().getItem(position));
                startActivity(intent);
            }
        });
        Button button = (Button) findViewById(R.id.buttonRefreshGps);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationRequested = Boolean.TRUE;
                if (mLocationClient.isConnected()) {
                    mLocationClient.requestLocationUpdates(mLocationRequest, EventActivity.this);
                } else {
                    mLocationClient.connect();
                }
            }
        });

        IntentFilter mStatusIntentFilter = new IntentFilter(
                Constants.BROADCAST_ACTION_RECEIVE_PATIENTS);
        Receiver receiver = new Receiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, mStatusIntentFilter);

        mStatusIntentFilter = new IntentFilter(
                Constants.BROADCAST_ACTION_RECEIVE_LISTS);
        receiver = new Receiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, mStatusIntentFilter);
        tabs.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if (tabId.equals(getString(R.string.tab_ubicacion_manual_text))) {
                    setRefreshActionButtonState(Boolean.TRUE);
                    SynchronizeIntentService.startActionGetLists(EventActivity.this);
                }
            }
        });
    }
    public void getEventCauses(ConceptDto concept)
    {
        try {
            RadioButton radioButtonOtherCause =(RadioButton)findViewById(R.id.radioButtonOtraCausa);
            ConceptService conceptService = new ConceptService(getHelper());
            List<ConceptDto> concepts = conceptService.getCausasAtencion();
            if(concept != null) {
                for (ConceptDto c : concepts) {
                    if(c!= null && c.getId() != null && concept.getId() != null) {
                        c.setSelected(c.getId().equals(concept.getId()));
                    }
                }
            }
            else
            {
                editTextOtraCausa.setEnabled(Boolean.TRUE);
                radioButtonOtherCause.setChecked(Boolean.TRUE);
            }
            GridView grid =(GridView)findViewById(R.id.gridViewEventCause);
            final ConceptArrayAdapter adapter = new ConceptArrayAdapter(this, 0, concepts);
            radioButtonOtherCause.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    editTextOtraCausa.setEnabled(isChecked);
                }
            });

            grid.setAdapter(adapter);
            adapter.setRadioButtonOther(radioButtonOtherCause);
            eventCausaAdapter = adapter;

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public EventDto getEvent() {
        return event;
    }

    public void setEvent(EventDto event) {
        this.event = event;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.event, menu);
        optionsMenu = menu;
        setRefreshActionButtonState(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_create_patient:
                Intent intent = new Intent(EventActivity.this,
                        CreatePatientTriageActivity.class);
                intent.putExtra(EventDto.class.getCanonicalName(), (Parcelable)event);
                intent.putExtra(UserDto.class.getCanonicalName(), (Parcelable)userInSession);
                intent.putExtra(getString(R.string.current_patients_extra_name), String.valueOf(event.getPatientsCount()+1)+"/"+String.valueOf(event.getPatientsCount()+1));
                startActivity(intent);
                return true;
            case R.id.action_save:
                try {
                    createEvent();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
                return true;
            case R.id.action_accept:
                Toast.makeText(this, "cerrar evento", Toast.LENGTH_LONG).show();

                return true;
            case R.id.action_settings:
                Toast.makeText(this, "configuracion", Toast.LENGTH_LONG).show();
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * function to load map. If map is not created it will create it for you
     * */
    private void initilizeMap(Location location) {
        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.map)).getMap();

            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
        if(googleMap != null) {
            googleMap.setOnMarkerDragListener(this);
            googleMap.setOnMapLongClickListener(this);
            googleMap.setOnMapClickListener(this);
            googleMap.clear();
            CameraPosition INIT =
                    new CameraPosition.Builder()
                            .target(new LatLng(location.getLatitude(), location.getLongitude()))
                            .zoom(17.5F)
                            .bearing(300F) // orientation
                            .tilt(50F) // viewing angle
                            .build();

            // use map to move camera into position
            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(INIT));

            //create initial marker
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(location.getLatitude(), location.getLongitude()))
                    .title("Ubicación del evento")
                    .snippet("").draggable(true)).showInfoWindow();
        }
    }




    public CityDto getCityByName(String stateName, String cityName, String countryName) {
        CountryDto countryDto = new CountryDto();
        countryDto.setName(countryName);
        StateDto state = new StateDto();
        state.setName(stateName);
        CityDto city = new CityDto();
        city.setName(cityName);
        city.setState(state);
        state.setCountry(countryDto);
        return city;
    }
    public void createEvent() throws IOException, SQLException {

        String numeroCaso = ((EditText) findViewById(R.id.editTextNumeroCaso)).getText().toString();
        if(eventLocation != null && isConnected()) {
            String coordinates = "(" + eventLocation.getLatitude() + ", " + eventLocation.getLongitude() + ")";
            Address address = null;
            try {
                address = getAddress(eventLocation);
            } catch (IOException e) {
                Log.e(CreateEventActivity.class.getCanonicalName(), e.getMessage());
            }
            if(address != null) {
                String direccion = address.getAddressLine(0);
                String countryName = address.getCountryName();
                String stateName = address.getAdminArea();
                String cityName = address.getLocality();
                CityDto city = getCityByName(stateName, cityName, countryName);
                event.setCoordinates(coordinates);
                event.setDireccion(direccion);
                event.setCity(city);
            }
        }
        else
        {
            String direccion = getDireccionManual();
            Spinner spinnerCity = (Spinner)findViewById(R.id.spinnerCity);
            CityDto city = (CityDto)spinnerCity.getSelectedItem();
            event.setCity(city);
            event.setDireccion(direccion);
        }

        {
            EditText editTextOtraCausa = (EditText)findViewById(R.id.editTextOtraCausa);
            event.setOtherCause(editTextOtraCausa.getText().toString());
        }
        ConceptDto causaAtencion = getCausaAtencion();
        event.setCausaAtencion(causaAtencion);
        event.setCallDate(callDate);
        event.setCaseNumber(numeroCaso);
        if(userInSession != null) {
            event.setCreator(userInSession.getUserId());
        }
        event.setZone(getZona());
        event.setIsSynchronized(Boolean.FALSE);
        event = saveEventToLocalStorage(event);
        tabs.setCurrentTabByTag(getString(R.string.tab_pacientes_text));
        if(isConnected() && numeroCaso != null && !numeroCaso.isEmpty()) {
            callSynchronizeService();
        }
    }
    public void callSynchronizeService()
    {
        SynchronizeIntentService.startActionSynchronize(this);
    }
    public String getZona()
    {
        if(selectedZone != null)
        {
            return selectedZone.getText().toString();
        }
        return null;
    }
    public String getDireccionManual()
    {
        StringBuilder sb = new StringBuilder();
        Spinner spinnerTipoVia = (Spinner)findViewById(R.id.spinnerTipoVia);
        if(spinnerTipoVia != null && spinnerTipoVia.getSelectedItem() != null) {
            sb.append(spinnerTipoVia.getSelectedItem().toString());
        }
        EditText editTextNumeroTipoVia = (EditText)findViewById(R.id.editTextNumeroComun);
        if(editTextNumeroTipoVia != null && editTextNumeroTipoVia.getText() != null)
        {
            if(sb.length() > 0) {
                sb.append(" ");
            }
            sb.append(editTextNumeroTipoVia.getText().toString());
        }
        Spinner spinnerLetraTipoVia = (Spinner)findViewById(R.id.spinnerLetraTipoVia);
        if(spinnerLetraTipoVia != null && spinnerLetraTipoVia.getSelectedItem() != null)
        {
            if(sb.length() > 0)
            {
                sb.append(" ");
            }
            sb.append(spinnerLetraTipoVia.getSelectedItem().toString());
        }
        Spinner spinnerCuadranteTipoVia = (Spinner)findViewById(R.id.spinnerCuadrante);
        if(spinnerCuadranteTipoVia != null && spinnerCuadranteTipoVia.getSelectedItem() != null)
        {
            if(sb.length() > 0)
            {
                sb.append(" ");
            }
            sb.append(spinnerCuadranteTipoVia.getSelectedItem().toString());
        }
        EditText editTextNumeroSecundario = (EditText)findViewById(R.id.editTextNumeroSecundario);
        if(editTextNumeroSecundario != null && editTextNumeroSecundario.getText() != null)
        {
            if(sb.length() > 0)
            {
                sb.append(" ");
            }
            sb.append(editTextNumeroSecundario.getText().toString());
        }
        Spinner spinnerLetraSecundaria = (Spinner)findViewById(R.id.spinnerLetraSecundaria);
        if(spinnerLetraSecundaria != null && spinnerLetraSecundaria.getSelectedItem() != null)
        {
            if(sb.length() > 0)
            {
                sb.append(" ");
            }
            sb.append(spinnerLetraSecundaria.getSelectedItem().toString());
        }
        Spinner spinnerCuadranteSecundario = (Spinner)findViewById(R.id.spinnerCuadranteSecundario);
        if(spinnerCuadranteSecundario != null && spinnerCuadranteSecundario.getSelectedItem() != null)
        {
            if(sb.length() > 0)
            {
                sb.append(" ");
            }
            sb.append(spinnerCuadranteSecundario.getSelectedItem().toString());
        }
        EditText editTextNumeroAdicional = (EditText)findViewById(R.id.editTextNumeroAdicional);
        if(editTextNumeroAdicional != null && editTextNumeroAdicional.getText() != null)
        {
            if(sb.length() > 0)
            {
                sb.append(" ");
            }
            sb.append(editTextNumeroAdicional.getText().toString());
        }
        Spinner spinnerBloque = (Spinner)findViewById(R.id.spinnerBloque);
        if(spinnerBloque != null && spinnerBloque.getSelectedItem() != null)
        {
            if(sb.length() > 0)
            {
                sb.append(" ");
            }
            sb.append(spinnerBloque.getSelectedItem().toString());
        }
        EditText editTextNumeroBloque = (EditText)findViewById(R.id.editTextNumeroBloque);
        if(editTextNumeroBloque != null && editTextNumeroBloque.getText() != null)
        {
            if(sb.length() > 0)
            {
                sb.append(" ");
            }
            sb.append(editTextNumeroBloque.getText().toString());
        }
        Spinner spinnerApartamento = (Spinner)findViewById(R.id.spinnerApartamento);
        if(spinnerApartamento != null && spinnerApartamento.getSelectedItem() != null)
        {
            if(sb.length() > 0)
            {
                sb.append(" ");
            }
            sb.append(spinnerApartamento.getSelectedItem().toString());
        }
        EditText editTextNumeroApartamento = (EditText)findViewById(R.id.editTextNumeroApartamento);
        if(editTextNumeroApartamento != null && editTextNumeroApartamento.getText() != null)
        {
            if(sb.length() > 0)
            {
                sb.append(" ");
            }
            sb.append(editTextNumeroApartamento.getText().toString());
        }
        if(sb.length() == 0)
        {
            EditText editTextOtraDireccion = (EditText)findViewById(R.id.editTextOtraDireccion);
            if(editTextOtraDireccion != null && editTextOtraDireccion.getText() != null)
            {
                sb.append(editTextOtraDireccion.getText().toString());
            }
        }
        return sb.toString();
    }
    public EventDto saveEventToLocalStorage(EventDto eventDto) throws SQLException {
        EventService eventService = new EventService(getHelper());
        return eventService.save(eventDto);

    }
    public ConceptDto getCausaAtencion() throws SQLException {
        return eventCausaAdapter.getConceptSelected();
    }
    @Override
    public void onMarkerDrag(Marker arg0) {

    }

    @Override
    public void onMarkerDragEnd(Marker arg0) {
        if(eventLocation != null) {
            LatLng pos = arg0.getPosition();
            eventLocation.setLatitude(pos.latitude);
            eventLocation.setLongitude(pos.longitude);
        }
    }

    @Override
    public void onMarkerDragStart(Marker arg0) {

    }

    @Override
    public void onMapClick(LatLng arg0) {
        if(googleMap != null) {
            googleMap.animateCamera(CameraUpdateFactory.newLatLng(arg0));
        }
    }


    @Override
    public void onMapLongClick(LatLng arg0) {

        //create new marker when user long clicks
        // googleMap.addMarker(new MarkerOptions()
        // .position(arg0)
        //.draggable(true));
    }
    public Address getAddress(Location location) throws IOException {
        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
        if(addresses.size() > 0)
        {
            return addresses.get(0);
        }
        return null;
    }
    @Override
    public void onLocationChanged(Location location) {
        if(eventLocation == null || locationRequested) {
            eventLocation = location;
            initilizeMap(location);
            if(mLocationClient != null) {
                // If the client is connected
                if (mLocationClient.isConnected()) {
            /*
             * Remove location updates for a listener.
             * The current Activity is the listener, so
             * the argument is "this".
             */
                    mLocationClient.removeLocationUpdates(this);
                }
        /*
         * After disconnect() is called, the client is
         * considered "dead".
         */
                mLocationClient.disconnect();
            }
        }

    }


    /*
         * Called by Location Services when the request to connect the
         * client finishes successfully. At this point, you can
         * request the current location or start periodic updates
         */
    @Override
    public void onConnected(Bundle dataBundle) {
        // Display the connection status
        if (eventLocation == null || locationRequested) {
            mLocationClient.requestLocationUpdates(mLocationRequest, this);
        }
    }

    /*
     * Called by Location Services if the connection to the
     * location client drops because of an error.
     */
    @Override
    public void onDisconnected() {
        // Display the connection status
        Toast.makeText(this, "Disconnected. Please re-connect.",
                Toast.LENGTH_SHORT).show();
    }

    /*
     * Called by Location Services if the attempt to
     * Location Services fails.
     */
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        /*
         * Google Play services can resolve some errors it detects.
         * If the error has a resolution, try sending an Intent to
         * start a Google Play services activity that can resolve
         * error.
         */
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(
                        this,
                        CONNECTION_FAILURE_RESOLUTION_REQUEST);
                /*
                * Thrown if Google Play services canceled the original
                * PendingIntent
                */
            } catch (IntentSender.SendIntentException e) {
                // Log the error
                Log.e(EventActivity.class.getCanonicalName(), e.getMessage());
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        tabs.setCurrentTabByTag(getString(R.string.tab_pacientes_text));
        if(mLocationClient != null) {
            mLocationClient.connect();
        }
        if(event != null && userInSession != null)
        {
            try {
                loadLists();
                loadPatients();
            } catch (SQLException e) {
                Log.e(EventActivity.class.getCanonicalName(), e.getMessage());
            }
            callPatientsService();
        }


    }

    public void callPatientsService()
    {
        PatientsIntentService.startActionGetPatients(this, userInSession.getUserId(), event.getEventId());
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(event != null && userInSession != null)
        {
            try {
                loadPatients();
            } catch (SQLException e) {
                Log.e(EventActivity.class.getCanonicalName(), e.getMessage());
            }
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        // If the client is connected
        if (mLocationClient.isConnected()) {
            /*
             * Remove location updates for a listener.
             * The current Activity is the listener, so
             * the argument is "this".
             */
            mLocationClient.removeLocationUpdates(this);
        }
        /*
         * After disconnect() is called, the client is
         * considered "dead".
         */
        mLocationClient.disconnect();
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
    public void setZona(String zona)
    {
        RadioButton buttonZonaUrbana = (RadioButton)findViewById(R.id.radioButtonZonaUrbana);
        RadioButton buttonZonaRural = (RadioButton)findViewById(R.id.radioButtonZonaRural);
        if(zona.equalsIgnoreCase(buttonZonaRural.getText().toString()))
        {
            selectedZone = buttonZonaRural;
            buttonZonaRural.setChecked(true);
        }
        else
        {
            selectedZone = buttonZonaUrbana;
            buttonZonaUrbana.setChecked(true);
        }
    }

    public Location getLocation(String coordinates)
    {
        if(coordinates != null && coordinates.length() > 4) {
            String s = coordinates.substring(1, coordinates.length() - 1);
            String[] split = s.split(",");
            Location location = new Location(provider);
            location.setLatitude(Double.parseDouble(split[0]));
            location.setLongitude(Double.parseDouble(split[1]));
            return location;
        }
        if(locationManager != null) {
            return locationManager.getLastKnownLocation(provider);
        }
        return null;

    }


    public void loadPatients() throws SQLException {
        LesionadoService lesionadoService = new LesionadoService(getHelper());
        List<LesionadoDto> lesionadosList = lesionadoService.getLesionadosEventById(event.getId());
        if (event != null) {
            event.setPatientsCount(lesionadosList.size());
            editTextPatientsCount.setText(String.valueOf(event.getPatientsCount()));
        }
        Collections.sort(lesionadosList, new Comparator<LesionadoDto>() {
            @Override
            public int compare(LesionadoDto lhs, LesionadoDto rhs) {
                List<EvaluacionDto> evaluacionesL = lhs.getEvaluaciones();
                List<EvaluacionDto> evaluacionesR = rhs.getEvaluaciones();
                if (evaluacionesL == null || evaluacionesL.isEmpty()) {
                    if (evaluacionesR == null || evaluacionesR.isEmpty()) {
                        if (lhs.getEventLocalIdentifier() != null) {
                            if (rhs.getEventLocalIdentifier() != null) {
                                return lhs.getEventLocalIdentifier().compareTo(rhs.getEventLocalIdentifier());
                            }
                        }
                    } else {
                        return 1;
                    }
                } else {
                    if (evaluacionesR == null || evaluacionesR.isEmpty()) {
                        return -1;
                    } else {
                        EvaluacionDto el = evaluacionesL.get(evaluacionesL.size() - 1);
                        EvaluacionDto er = evaluacionesR.get(evaluacionesR.size() - 1);
                        if (el == null) {
                            if (er == null) {
                                if (lhs.getEventLocalIdentifier() != null) {
                                    if (rhs.getEventLocalIdentifier() != null) {
                                        return lhs.getEventLocalIdentifier().compareTo(rhs.getEventLocalIdentifier());
                                    }
                                }
                            } else {
                                return 1;
                            }
                        } else {
                            if (er == null) {
                                return -1;
                            } else {
                                if (er.getPrioridadTriage() == null) {
                                    if (el.getPrioridadTriage() == null) {
                                        if (lhs.getEventLocalIdentifier() != null) {
                                            if (rhs.getEventLocalIdentifier() != null) {
                                                return lhs.getEventLocalIdentifier().compareTo(rhs.getEventLocalIdentifier());
                                            }
                                        }
                                    } else {
                                        return -1;
                                    }
                                } else {
                                    if (el.getPrioridadTriage() == null) {
                                        return 1;
                                    } else {
                                        return el.getPrioridadTriage().compareTo(er.getPrioridadTriage());
                                    }
                                }
                            }
                        }
                    }
                }
                return 0;
            }

        });
        GridView grid = (GridView) findViewById(R.id.listViewPatients);
        LesionadoArrayAdapter adapter = new LesionadoArrayAdapter(this, lesionadosList);
        grid.setAdapter(adapter);
        setRefreshActionButtonState(false);
    }

    private class Receiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent != null) {
                if(intent.getAction().equals(Constants.BROADCAST_ACTION_RECEIVE_PATIENTS)) {
                    try {
                        loadPatients();
                    } catch (SQLException e) {
                        Log.e(EventActivity.class.getCanonicalName(), e.getMessage());
                    }
                }
                else
                {
                    if(intent.getAction().equals(Constants.BROADCAST_ACTION_RECEIVE_LISTS))
                    {
                        try {
                            loadLists();
                        } catch (SQLException e) {
                            Log.e(EventActivity.class.getCanonicalName(), e.getMessage());
                        }
                    }
                }

            }
        }
    }
    private void loadLists() throws SQLException {
        StateService stateService = new StateService(getHelper());
        List<StateDto> states = stateService.getStatesDefault();
        ArrayAdapter<StateDto> stateArrayAdapter = new ArrayAdapter<StateDto>(this,
                android.R.layout.simple_spinner_item, states);
        stateArrayAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinnerState = (Spinner)findViewById(R.id.spinnerState);
        Adapter adapter =spinnerState.getAdapter();
        List<StateDto> currentList = new ArrayList<StateDto>();
        for(int i = 0; adapter != null && i < adapter.getCount(); i++)
        {
            currentList.add((StateDto)adapter.getItem(i));
        }
        Collections.sort(states);
        Collections.sort(currentList);
        if(!currentList.equals(states)) {
            spinnerState.setAdapter(stateArrayAdapter);
        }
        setRefreshActionButtonState(Boolean.FALSE);

    }
    public void setRefreshActionButtonState(final boolean refreshing) {
        if (optionsMenu != null) {
            final MenuItem refreshItem = optionsMenu
                    .findItem(R.id.action_refresh);
            if (refreshItem != null) {
                if (refreshing) {
                    refreshItem.setActionView(R.layout.actionbar_indeterminate_progress);
                } else {
                    refreshItem.setActionView(null);
                }
            }
        }
    }
}
