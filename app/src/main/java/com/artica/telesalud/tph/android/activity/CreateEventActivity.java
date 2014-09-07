package com.artica.telesalud.tph.android.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
import com.artica.telesalud.tph.android.adapter.EventFilter;
import com.artica.telesalud.tph.android.dao.ConceptService;
import com.artica.telesalud.tph.android.dao.DatabaseHelper;
import com.artica.telesalud.tph.android.dao.EventService;
import com.artica.telesalud.tph.android.intentservice.SynchronizeIntentService;
import com.artica.telesalud.tph.android.lightweightmodel.CityDto;
import com.artica.telesalud.tph.android.lightweightmodel.ConceptDto;
import com.artica.telesalud.tph.android.lightweightmodel.CountryDto;
import com.artica.telesalud.tph.android.lightweightmodel.EventDto;
import com.artica.telesalud.tph.android.lightweightmodel.StateDto;
import com.artica.telesalud.tph.android.lightweightmodel.UserDto;
import com.artica.telesalud.tph.android.service.CausasAtencionService;
import com.artica.telesalud.tph.android.service.CitiesService;
import com.artica.telesalud.tph.android.service.PatientsService;
import com.artica.telesalud.tph.android.service.StatesService;
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
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CreateEventActivity extends BaseActivity implements GoogleMap.OnMapLongClickListener,GoogleMap.OnMapClickListener,GoogleMap.OnMarkerDragListener, LocationListener,
        GooglePlayServicesClient.ConnectionCallbacks,
        GooglePlayServicesClient.OnConnectionFailedListener{
    private UserDto userInSession;
    private EventDto event;
    private TabHost tabs;
    private DatabaseHelper databaseHelper;
    private GoogleMap googleMap;
    private ConceptArrayAdapter eventCausaAdapter;
    private EditText editTextOtraCausa;
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
    private SharedPreferences mPrefs;
    private LocationRequest mLocationRequest;
    private Boolean locationRequested = Boolean.FALSE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        Spinner spinnerTipoVia = (Spinner)findViewById(R.id.spinnerTipoVia);
        Resources resources = getResources();

        spinnerTipoVia.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, resources.getStringArray(R.array.list_tipo_via)));

        Spinner spinnerCuadrante = (Spinner)findViewById(R.id.spinnerCuadrante);
        spinnerCuadrante.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, resources.getStringArray(R.array.list_cuadrante)));
        Spinner spinnerCuadranteSecundario = (Spinner)findViewById(R.id.spinnerCuadranteSecundario);
        spinnerCuadranteSecundario.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, resources.getStringArray(R.array.list_cuadrante)));

        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null) {
            userInSession = bundle.getParcelable(UserDto.class.getCanonicalName());
        }
        final Spinner spinnerState = (Spinner)findViewById(R.id.spinnerState);
        spinnerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                StateDto selected = (StateDto) spinnerState.getSelectedItem();

                if (selected != null && selected != StateDto.EMPTY_STATE) {

                    new CitiesService(CreateEventActivity.this).execute(selected.getStateId());
                } else {
                    Spinner spinnerCity = (Spinner) findViewById(R.id.spinnerCity);
                    spinnerCity.setAdapter(new ArrayAdapter<CityDto>(CreateEventActivity.this, android.R.layout.simple_spinner_item, new ArrayList<CityDto>()));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        final TextView textViewFechaHora = (TextView)findViewById(R.id.textViewFechaEvento);
        ImageButton buttonFechaHora = (ImageButton)findViewById(R.id.imageButtonFechaEvento);
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
                new DatePickerDialog(CreateEventActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override public void onDateSet(DatePicker view,
                                                            int y, int m, int d) {
                                cal.set(Calendar.YEAR, y);
                                cal.set(Calendar.MONTH, m);
                                cal.set(Calendar.DAY_OF_MONTH, d);

                                // now show the time picker
                                new TimePickerDialog(CreateEventActivity.this,
                                        new TimePickerDialog.OnTimeSetListener() {
                                            @Override public void onTimeSet(TimePicker view,
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
                                        cal.get(Calendar.MINUTE), true).show();
                            }
                        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        tabs = (TabHost) findViewById(R.id.tabHost);
        tabs.setup();

        TabSpec specInformacion = tabs.newTabSpec(getString(R.string.tab_informacion_evento_text));
        specInformacion.setContent(R.id.informacion_evento);
        specInformacion.setIndicator(getString(R.string.tab_informacion_evento_text));

        TabSpec specUbiManual = tabs.newTabSpec(getString(R.string.tab_ubicacion_manual_text));
        specUbiManual.setContent(R.id.ubicacion_manual);
        specUbiManual.setIndicator(getString(R.string.tab_ubicacion_manual_text));

        tabs.addTab(specInformacion);
        tabs.addTab(specUbiManual);
        geocoder = new Geocoder(CreateEventActivity.this, Locale.US);
        // Get the location manager
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, false);
        Location location = locationManager.getLastKnownLocation(provider);

        if(location != null) {
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
                if(selectedZone != null)
                {
                    selectedZone.setChecked(false);
                }
                selectedZone = (RadioButton)v;
                selectedZone.setChecked(true);
            }
        };

        editTextOtraCausa = (EditText)findViewById(R.id.editTextOtraCausa);
        editTextOtraCausa.setEnabled(false);
        RadioButton radioButtonZonaUrbana = (RadioButton)findViewById(R.id.radioButtonZonaUrbana);
        RadioButton radioButtonZonaRural = (RadioButton)findViewById(R.id.radioButtonZonaRural);
        radioButtonZonaRural.setOnClickListener(listenerZona);
        radioButtonZonaUrbana.setOnClickListener(listenerZona);
        selectedZone = radioButtonZonaUrbana;
        radioButtonZonaUrbana.setChecked(Boolean.TRUE);
        // Create the LocationRequest object
        mLocationRequest = LocationRequest.create();
        // Use high accuracy
        mLocationRequest.setPriority(
                LocationRequest.PRIORITY_HIGH_ACCURACY);
        // Set the update interval to 5 seconds
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        // Set the fastest update interval to 1 second
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);

        // Create a new location client, using the enclosing class to
        //handle callbacks.

        mLocationClient = new LocationClient(CreateEventActivity.this, CreateEventActivity.this, CreateEventActivity.this);
        // Start with updates turned off
        Button button = (Button)findViewById(R.id.buttonRefreshGps);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationRequested = Boolean.TRUE;
                if (mLocationClient.isConnected()) {
                    mLocationClient.requestLocationUpdates(mLocationRequest, CreateEventActivity.this);
                }
                else
                {
                    mLocationClient.connect();
                }
            }
        });
        getEventCauses();
    }
    public void getEventCauses()
    {
        try {
            ConceptService conceptService = new ConceptService(getHelper());
            List<ConceptDto> concepts = conceptService.getCausasAtencion();
            GridView grid =(GridView)findViewById(R.id.gridViewEventCause);

            final ConceptArrayAdapter adapter = new ConceptArrayAdapter(this, 0, concepts);
            grid.setAdapter(adapter);
            RadioButton radioButtonOtherCause =(RadioButton)findViewById(R.id.radioButtonOtraCausa);
            radioButtonOtherCause.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    editTextOtraCausa.setEnabled(isChecked);
                }
            });
            adapter.setRadioButtonOther(radioButtonOtherCause);
            eventCausaAdapter = adapter;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void callSynchronizeService()
    {
        SynchronizeIntentService.startActionSynchronize(this);
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
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_create_patient:
                Intent intent = new Intent(CreateEventActivity.this,
                        SampleActivity.class);
                intent.putExtra(EventDto.class.getCanonicalName(), (Parcelable)event);
                intent.putExtra(UserDto.class.getCanonicalName(), (Parcelable)userInSession);
                startActivity(intent);
                return true;
            case R.id.action_save:
                try {
                    createEvent();
                } catch (SQLException e) {
                    Log.e(CreateEventActivity.class.getCanonicalName(), e.getMessage());
                }
                return true;
            case R.id.action_accept:

                return true;
            case R.id.action_settings:
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
        googleMap.setOnMarkerDragListener(this);
        googleMap.setOnMapLongClickListener(this);
        googleMap.setOnMapClickListener(this);
        googleMap.clear();
        CameraPosition INIT =
                new CameraPosition.Builder()
                        .target(new LatLng(location.getLatitude(), location.getLongitude()))
                        .zoom(17.5F)
                        .bearing(300F) // orientation
                        .tilt( 50F) // viewing angle
                        .build();

        // use map to move camera into position
        googleMap.moveCamera( CameraUpdateFactory.newCameraPosition(INIT) );

        //create initial marker
        googleMap.addMarker( new MarkerOptions()
                .position( new LatLng(location.getLatitude(), location.getLongitude()) )
                .title("Ubicación del evento")
                .snippet("").draggable(true)).showInfoWindow();
    }




    public CityDto getCityByName(String stateName, String cityName, String countryName) {
        CountryDto countryDto = new CountryDto();
        countryDto.setName(EventFilter.removeAccents(countryName));
        StateDto state = new StateDto();
        state.setName(EventFilter.removeAccents(stateName));
        CityDto city = new CityDto();
        city.setName(EventFilter.removeAccents(cityName));
        city.setState(state);
        state.setCountry(countryDto);
        return city;
    }
    public void createEvent() throws SQLException {
        EventDto event = new EventDto();
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
                event.setStateName(stateName);
                event.setCityName(cityName);
            }
        }
        else
        {
            String direccion = getDireccionManual();
            Spinner spinnerCity = (Spinner)findViewById(R.id.spinnerCity);
            CityDto city = (CityDto)spinnerCity.getSelectedItem();
            event.setCity(city);
            event.setDireccion(direccion);
            if(city != null) {
                event.setStateName(city.getName());
                if (city.getState() != null) {
                    event.setCityName(city.getState().getName());
                }
            }
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
        else
        {
            event.setCreator(35);
        }
        event.setZone(getZona());
        event = saveEventToLocalStorage(event);
        if(isConnected() && numeroCaso != null && !numeroCaso.isEmpty()) {
            callSynchronizeService();
        }
        EditText editText = (EditText)findViewById(R.id.editTextPatientsQuantity);
        Intent intent = new Intent(this, CreatePatientTriageActivity.class);
        intent.putExtra(EventDto.class.getCanonicalName(), (Parcelable)event);
        intent.putExtra(UserDto.class.getCanonicalName(), (Parcelable)userInSession);
        intent.putExtra(getString(R.string.current_patients_extra_name), (1)+"/"+(editText.getText().toString()));
        startActivity(intent);
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
        // TODO Auto-generated method stub

    }

    @Override
    public void onMarkerDragEnd(Marker arg0) {
        // TODO Auto-generated method stub
        if(eventLocation != null) {
            LatLng pos = arg0.getPosition();
            eventLocation.setLatitude(pos.latitude);
            eventLocation.setLongitude(pos.longitude);
        }
    }

    @Override
    public void onMarkerDragStart(Marker arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onMapClick(LatLng arg0) {
        // TODO Auto-generated method stub
        if(googleMap != null) {
            googleMap.animateCamera(CameraUpdateFactory.newLatLng(arg0));
        }
    }


    @Override
    public void onMapLongClick(LatLng arg0) {
        // TODO Auto-generated method stub

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
                locationRequested = Boolean.FALSE;
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
                Log.e(CreateEventActivity.class.getCanonicalName(), e.getMessage());
            }
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        if(mLocationClient != null) {
            mLocationClient.connect();
        }
        new StatesService(CreateEventActivity.this).execute();
        new CausasAtencionService(CreateEventActivity.this).execute();

        if(event != null)
        {
            new PatientsService(this).execute(event.getEventId(), 35);
        }

    }
    @Override
    protected void onResume() {
        super.onResume();

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






}
