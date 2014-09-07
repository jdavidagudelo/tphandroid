package com.artica.telesalud.tph.android.activity;

import android.location.Geocoder;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.artica.telesalud.tph.android.R;
import com.artica.telesalud.tph.android.dao.DatabaseHelper;
import com.artica.telesalud.tph.android.lightweightmodel.EventDto;
import com.artica.telesalud.tph.android.lightweightmodel.UserDto;

public class EventActivity0 extends BaseActivity {

    private DatabaseHelper databaseHelper = null;
    private Geocoder geocoder;
    private Integer stateId;
    private Integer cityId;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private UserDto userInSession;
    private EventDto event;
    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null) {
            userInSession = bundle.getParcelable(UserDto.class.getCanonicalName());//new UserDto();
//            event = bundle.getParcelable("evento");
        }
//        geocoder = new Geocoder(this, Locale.US);
//        final Spinner spinnerState = (Spinner) findViewById(R.id.spinnerStateEvent);
//        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        Criteria criteria = new Criteria();
//        spinnerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                StateDto selected = (StateDto) spinnerState.getSelectedItem();
//
//                if(selected != null && selected != StateDto.EMPTY_STATE) {
//                    try {
//                        getCitiesByStateFromLocalStorage(selected);
//                        new CitiesService(EventActivity1.this).execute(selected.getStateId());
//
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                }
//                else
//                {
//                    Spinner spinnerCity = (Spinner)findViewById(R.id.spinnerCityEvent);
//                    spinnerCity.setAdapter(new ArrayAdapter<CityDto>(EventActivity1.this,  android.R.layout.simple_spinner_item, new ArrayList<CityDto>()));
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//        Button buttonCancelCreateEvent = (Button)findViewById(R.id.buttonBackList);
//        buttonCancelCreateEvent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(userInSession != null){
//
//                    Intent intent = new Intent(EventActivity1.this,
//                            ListEventsActivity.class);
//                    intent.putExtra(
//                            UserDto.class.getCanonicalName(),
//                            (Parcelable)userInSession);
//                    startActivity(intent);
//                }
//            }
//        });
//        Button buttonCreateEvent = (Button) findViewById(R.id.buttonCreateEvent);
//        buttonCreateEvent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    createEvent();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        locationListener = new LocationListener() {
//            @Override
//            public void onLocationChanged(Location location) {
//                Double lat = (location.getLatitude());
//                Double lng = (location.getLongitude());
//                try {
//                    List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
//                    Dao<StateDto, Integer> daoState = getHelper().getStateDao();
//                    Dao<CityDto, Integer> daoCity = getHelper().getCityDao();
//                    if (addresses.size() > 0) {
//                        Address address = addresses.get(0);
//                        String s = "";
//                        s = s + "Country: " + address.getCountryName() + "\nAdministrative Area: " + address.getAdminArea();
//                        s = s + "\nSub Admin Area: " + address.getSubAdminArea() + "\nLocality: " + address.getLocality();
//                        String stateName = address.getAdminArea();
//                        String cityName = address.getLocality();
//                        String realAddress = address.getAdminArea();
//                        Where<StateDto, Integer> whereState = daoState.queryBuilder().where().eq("name", stateName);
//                        List<StateDto> result = whereState.query();
//                        Spinner spinnerState = (Spinner) findViewById(R.id.spinnerStateEvent);
//                        if (!result.isEmpty()) {
//                            StateDto state = result.get(0);
//                            stateId = state.getStateId();
//                            int selectedIndex = 0;
//                            for (int i = 0; i < spinnerState.getAdapter().getCount(); i++) {
//                                if (state.getStateId().equals(((StateDto) spinnerState.getAdapter().getItem(i)).getStateId())) {
//                                    selectedIndex = i;
//                                }
//                            }
//                            spinnerState.setSelection(selectedIndex);
//                            CityDto city = null;
//                            Log.e("Tag: ", String.valueOf(daoCity.queryForAll().size()), null);
//                            for (CityDto c : daoCity.queryForAll()) {
//                                if (c.getName().equalsIgnoreCase(cityName)) {
//                                    city = c;
//                                }
//                            }
//                            if (city != null) {
//                                cityId = city.getCityId();
//                                selectedIndex = 0;
//                                Spinner spinnerCity = (Spinner) findViewById(R.id.spinnerCityEvent);
//                                for (int i = 0; i < spinnerCity.getAdapter().getCount(); i++) {
//                                    if (city.getCityId().equals(((CityDto) spinnerCity.getAdapter().getItem(i)).getCityId())) {
//                                        selectedIndex = i;
//                                    }
//                                }
//                                spinnerCity.setSelection(selectedIndex);
//                            }
//                        }
//                        for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
//                            if (i == 0) {
//                                EditText addressText = (EditText) findViewById(R.id.editTextAddressEvent);
//                                addressText.setText(address.getAddressLine(i));
//                            }
//                            s += "\nAddressLine " + i + ": " + address.getAddressLine(i);
//                        }
//                        Bundle extras = address.getExtras();
//                        if (extras != null) {
//
//                            for (String key : extras.keySet()) {
//                                String extra = "\nExtra: " + key + " = " + extras.get(key);
//                            }
//                        }
//                        StringBuilder sb;
//
//                        // addressField.setText(s);
//                    }
//
//                } catch (Exception ex) {
//
//                }
//
//
//            }
//
//            @Override
//            public void onStatusChanged(String provider, int status, Bundle extras) {
//                // TODO Auto-generated method stub
//
//            }
//
//            @Override
//            public void onProviderEnabled(String provider) {
//            }
//
//            @Override
//            public void onProviderDisabled(String provider) {
//            }
//        };
//        //locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
    }

   /* public void createEvent() throws IOException, SQLException {
        EditText editTextAddress = (EditText) findViewById(R.id.editTextAddressEvent);
        String address = editTextAddress.getText().toString();
        if (address == null || address.length() == 0) {
            showErrorMessage(getString(R.string.create_event_address_error_text));
            return;
        }
        Spinner spinnerCity = (Spinner) findViewById(R.id.spinnerCityEvent);
        CityDto city = (CityDto) spinnerCity.getSelectedItem();
        if (city == null || city == CityDto.EMPTY_CITY) {
            showErrorMessage(getString(R.string.create_event_city_error_text));
            return;
        }
        RadioGroup group = (RadioGroup) findViewById(R.id.radioZone);
        RadioButton selected = (RadioButton) findViewById(group.getCheckedRadioButtonId());
        if (selected == null) {
            showErrorMessage(getString(R.string.create_event_zone_error_text));
            return;
        }
        String zone = selected.getText().toString();
        Spinner spinnerCause = (Spinner) findViewById(R.id.spinnerCausaAtencion);
        CausaAtencionDto cause = (CausaAtencionDto) spinnerCause.getSelectedItem();
        if (cause == null || cause == CausaAtencionDto.EMPTY_CAUSA_ATENCION) {
            showErrorMessage(getString(R.string.create_event_cause_error_text));
            return;
        }
        Spinner spinnerState = (Spinner) findViewById(R.id.spinnerStateEvent);
        StateDto state = (StateDto) spinnerState.getSelectedItem();
        if (state == null || state == StateDto.EMPTY_STATE) {
            showErrorMessage(getString(R.string.create_event_state_error_text));
            return;
        }
        EditText editTextCaseNumber = (EditText) findViewById(R.id.editTextCaseNumberEvent);
        String caseNumber = editTextCaseNumber.getText().toString();
        if (caseNumber == null || caseNumber.length() == 0) {
            showErrorMessage(getString(R.string.create_event_case_number_error_text));
            return;
        }
        DatePicker pickerDate = (DatePicker) findViewById(R.id.editTextDateEvent);
        TimePicker pickerTime = (TimePicker) findViewById(R.id.editTextTimeEvent);
        DateTime current = new DateTime(new Date());
        DateTime dateTime = new DateTime(pickerDate.getYear(), pickerDate.getMonth() + 1,
                pickerDate.getDayOfMonth(), pickerTime.getCurrentHour(), pickerTime.getCurrentMinute(), current.getZone());

        if (dateTime.compareTo(current) >= 0) {
            showErrorMessage(getString(R.string.create_event_date_greater_error_text));
            return;
        }
        EventDto event = new EventDto();
        event.setDireccion(address);
        event.setCity(city);
        event.setCausaAtencion(cause);
        event.setCallDate(dateTime.toDate());
        event.setCaseNumber(caseNumber);
        event.setDateCreated(new Date());
        event.setIsSynchronized(Boolean.FALSE);
        event.setZone(zone);
        event.setState(EventoSpringDto.ESTADO_ACTIVO);
        event.setCreator(userInSession.getUserId());
        saveToLocalStorage(event);
        Dao<EventDto, Integer> eventDao = getHelper().getEventDao();
        List<EventDto> events = eventDao.queryForEq("isSynchronized", Boolean.FALSE);
        EventDto eventsDto[] = new EventDto[events.size()];
        int i = 0;
        for (EventDto eventDto : events) {
            Dao<CityDto, Integer> cityDao = getHelper().getCityDao();
            Dao<CountryDto, Integer> countryDao = getHelper().getCountryDao();
            Dao<CausaAtencionDto, Integer> causaAtencionDao = getHelper().getCausaAtencionDao();
            Dao<StateDto, Integer> stateDao = getHelper().getStateDao();
            CityDto currentCity = cityDao.queryForId(eventDto.getCity().getCityId());
            eventDto.setCity(currentCity);
            StateDto currentState = stateDao.queryForId(city.getState().getStateId());
            CausaAtencionDto causaAtencion = causaAtencionDao.queryForId(eventDto.getCausaAtencion().getConceptId());
            eventDto.setCausaAtencion(causaAtencion);
            CountryDto currentCountry = countryDao.queryForId(state.getCountry().getCountryId());
            currentState.setCountry(currentCountry);
            currentCity.setState(currentState);
            eventsDto[i] = eventDto;
            i++;
        }
        new CreateEventService(this).execute(eventsDto);
    }

    public void saveToLocalStorage(EventDto event) throws SQLException {
        Dao<EventDto, Integer> eventDao = getHelper().getEventDao();
        Integer nextIndex = event.getEventId();
        if(nextIndex == null) {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                if(!eventDao.idExists(i))
                {
                    nextIndex = i;
                    break;
                }
            }
            event.setEventId(nextIndex);
        }
        eventDao.createOrUpdate(event);
        showSuccessMessage(getString(R.string.create_event_success_text));
    }

    public void getCitiesByStateFromLocalStorage(StateDto state) throws SQLException {
        Dao<CityDto, Integer> cityDao = getHelper().getCityDao();
        List<CityDto> list = new ArrayList<CityDto>();
        list.add(CityDto.EMPTY_CITY);
        Where where = cityDao.queryBuilder().where().eq("state_id", state.getStateId());
        list.addAll(where.query());

        ArrayAdapter<CityDto> stateArrayAdapter = new ArrayAdapter<CityDto>(this,
                android.R.layout.simple_spinner_item, list);
        stateArrayAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinnerCity = (Spinner) findViewById(R.id.spinnerCityEvent);
        Adapter adapter =spinnerCity.getAdapter();
        List<CityDto> currentList = new ArrayList<CityDto>();
        for(int i = 0; adapter != null && i < adapter.getCount(); i++)
        {
            currentList.add((CityDto)adapter.getItem(i));
        }
        Collections.sort(list);
        Collections.sort(currentList);
        if(!currentList.equals(list)) {
            spinnerCity.setAdapter(stateArrayAdapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.event, menu);
        return true;
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
        return super.onOptionsItemSelected(item);
    }
*/
    @Override
    public DatabaseHelper getHelper() {
        return null;
    }

    @Override
    public void showSuccessMessage(String message) {

    }

    @Override
    public void showErrorMessage(String message) {

    }
}
