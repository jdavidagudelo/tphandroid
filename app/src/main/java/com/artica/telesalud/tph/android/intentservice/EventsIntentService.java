package com.artica.telesalud.tph.android.intentservice;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.artica.telesalud.tph.android.R;
import com.artica.telesalud.tph.android.dao.CityService;
import com.artica.telesalud.tph.android.dao.DatabaseHelper;
import com.artica.telesalud.tph.android.dao.EventService;
import com.artica.telesalud.tph.android.lightweightmodel.CityDto;
import com.artica.telesalud.tph.android.lightweightmodel.CountryDto;
import com.artica.telesalud.tph.android.lightweightmodel.EventDto;
import com.artica.telesalud.tph.android.lightweightmodel.ListEvents;
import com.artica.telesalud.tph.android.lightweightmodel.StateDto;
import com.artica.telesalud.tph.android.lightweightmodel.UserInSession;
import com.artica.telesalud.tph.android.mapper.DateObjectMapper;
import com.artica.telesalud.tph.android.model.EventoSpringDto;
import com.artica.telesalud.tph.android.service.NetworkUtil;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class EventsIntentService extends IntentService {
    // Defines a custom Intent action

    // Defines the key for the status "extra" in an Intent
    public static final String EXTENDED_DATA_STATUS =
            "com.example.android.threadsample.STATUS";
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    public static final String ACTION_GET_EVENTS = "com.artica.telesalud.tph.android.intentservice.action.GET_EVENTS";
    // TODO: Rename parameters
    public static final String EXTRA_PARAM_USER_ID = "com.artica.telesalud.tph.android.intentservice.extra.USER_ID";

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionGetEvents(Context context, Integer userId) {
        Intent intent = new Intent(context, EventsIntentService.class);
        intent.setAction(ACTION_GET_EVENTS);
        intent.putExtra(EXTRA_PARAM_USER_ID, userId);
        context.startService(intent);
    }



    public EventsIntentService() {
        super("EventsIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_GET_EVENTS.equals(action)) {
                final Integer param1 = intent.getIntExtra(EXTRA_PARAM_USER_ID, 0);
                try {
                    handleActionGetEvents(param1);
                } catch (Exception e) {
                    Log.e("TAG", e.getCause()+"");
                }
            }
        }
    }
    public Boolean isConnected()
    {
        return !NetworkUtil.getConnectivityStatus(this).equals(NetworkUtil.NetworkType.TYPE_NOT_CONNECTED);
    }
    /**
     * Handle action getEvents  in the provided background thread with the provided
     * parameters.
     */
    private void handleActionGetEvents(Integer userId) throws SQLException {
        if(isConnected()) {
            StringBuilder sb = new StringBuilder();
            sb.append(getString(R.string.uri_base_rest_services));
            sb.append(getString(R.string.uri_get_events));
            final String url = sb.toString();
            Map<String, String> map = new HashMap<String, String>();
            map.put("userId", userId.toString());
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<String> requestEntity = new HttpEntity<String>(
                    UserInSession.getDefaultHeaders());
            restTemplate.getMessageConverters().add(DateObjectMapper.getConverter());
            ResponseEntity<ListEvents> entity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, ListEvents.class, map);
            ListEvents events = entity.getBody();
            EventService eventService = new EventService(getHelper());
            List<EventDto> list = new ArrayList<EventDto>();
            if (events != null) {
                for (EventoSpringDto evento : events) {
                    EventDto e = new EventDto(evento);
                    if (e.getCity() == null) {
                        if (e.getCoordinates() != null && isConnected()) {
                            Geocoder geocoder = new Geocoder(getBaseContext(), Locale.US);
                            String coordinates = e.getCoordinates();
                            LocationManager locationManager = (LocationManager) getBaseContext().getSystemService(Context.LOCATION_SERVICE);
                            Criteria criteria = new Criteria();
                            String provider = locationManager.getBestProvider(criteria, false);
                            if (coordinates != null && coordinates.length() > 4) {
                                String s = coordinates.substring(1, coordinates.length() - 1);
                                String[] split = s.split(",");
                                Location location = new Location(provider);
                                location.setLatitude(Double.parseDouble(split[0]));
                                location.setLongitude(Double.parseDouble(split[1]));
                                try {
                                    List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                    if (addresses.size() > 0) {
                                        CityService cityService = new CityService(getHelper());
                                        Address address = addresses.get(0);
                                        String countryName = address.getCountryName();
                                        String stateName = address.getAdminArea();
                                        String cityName = address.getLocality();
                                        CountryDto countryDto = new CountryDto();
                                        countryDto.setName(countryName);
                                        StateDto state = new StateDto();
                                        state.setName(stateName);
                                        CityDto city = new CityDto();
                                        city.setName(cityName);
                                        state.setCountry(countryDto);
                                        city.setState(state);
                                        city = cityService.save(city);
                                        e.setCity(city);
                                        e.setStateName(stateName);
                                        e.setCityName(cityName);
                                    }
                                } catch (IOException ex) {
                                    Log.e("TAG", ex.getMessage());
                                }
                            }
                        }
                    }
                    list.add(e);
                }
                for(EventDto event : list)
                {
                    if(event.getEventId() != null)
                    {
                        EventDto current = eventService.getEvent(event.getEventId());
                        if(current == null || current.getIsSynchronized())
                        {
                            eventService.save(event);
                        }
                    }
                }
                //eventService.saveBatch(list);

            }
        }
        Intent localIntent =
                new Intent(Constants.BROADCAST_ACTION_RECEIVE_EVENTS)
                        // Puts the status into the Intent
                        .putExtra(EXTENDED_DATA_STATUS, "This is my status");
        // Broadcasts the Intent to receivers in this app.
        LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);

    }
    private DatabaseHelper databaseHelper;
    public DatabaseHelper getHelper() {
        if(databaseHelper == null)
        {
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper                                                                                         ;
    }

}
