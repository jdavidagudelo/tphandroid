package com.artica.telesalud.tph.android.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TabHost;
import android.widget.Toast;

import com.artica.telesalud.tph.android.R;
import com.artica.telesalud.tph.android.adapter.EventArrayAdapter;
import com.artica.telesalud.tph.android.dao.DatabaseHelper;
import com.artica.telesalud.tph.android.dao.EventService;
import com.artica.telesalud.tph.android.intentservice.Constants;
import com.artica.telesalud.tph.android.intentservice.EventsIntentService;
import com.artica.telesalud.tph.android.intentservice.SynchronizeIntentService;
import com.artica.telesalud.tph.android.lightweightmodel.EventDto;
import com.artica.telesalud.tph.android.lightweightmodel.UserDto;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class ListEventsActivity extends BaseActivity  {

    private UserDto userInSession;
    private DatabaseHelper databaseHelper = null;
    private EventService eventService;
    private Menu optionsMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_list_events);
        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null) {
            userInSession = bundle.getParcelable(UserDto.class.getCanonicalName());//new UserDto();
        }
        if(userInSession == null) {
            userInSession = new UserDto();
            userInSession.setUserId(35);
            userInSession.setUserName("eintervencion");
            userInSession.setRole(7);
            userInSession.setPersonName("Juan David Agudelo Alvarez");
        }

        TabHost tabs = (TabHost) findViewById(R.id.tabHostEvents);
        tabs.setup();
        TabHost.TabSpec specEventsActive = tabs.newTabSpec("Eventos activos");
        TabHost.TabSpec specEventsNoCode = tabs.newTabSpec("Eventos sin código");
        specEventsActive.setContent(R.id.eventos_activos);
        specEventsActive.setIndicator("Eventos activos");
        specEventsNoCode.setContent(R.id.eventos_sin_codigo);
        specEventsNoCode.setIndicator("Eventos sin código");
        tabs.addTab(specEventsNoCode);
        tabs.addTab(specEventsActive);
        final GridView gridView = (GridView)findViewById(R.id.listViewEventsActive);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(userInSession != null)
                {
                    Intent intent = new Intent(ListEventsActivity.this, EventActivity.class);
                    intent.putExtra(
                            UserDto.class.getCanonicalName(),
                            (Parcelable)userInSession);
                    intent.putExtra(EventDto.class.getCanonicalName(), (Parcelable) gridView.getAdapter().getItem(position));
                    startActivity(intent);
                }
                else
                {
                    showErrorMessage("No fue posible acceder al evento, intentelo nuevamente!");
                }
            }
        });
       final GridView gridViewOther = (GridView)findViewById(R.id.listViewEventsNoCode);

        gridViewOther.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (userInSession != null) {
                    Intent intent = new Intent(ListEventsActivity.this, EventActivity.class);
                    intent.putExtra(
                            UserDto.class.getCanonicalName(),
                            (Parcelable) userInSession);
                    EventDto event = (EventDto) gridViewOther.getAdapter().getItem(position);
                    intent.putExtra(EventDto.class.getCanonicalName(), (Parcelable) event);
                    startActivity(intent);
                } else {
                    showErrorMessage("No fue posible acceder al evento, intentelo nuevamente!");
                }
            }
        });

        IntentFilter mStatusIntentFilter = new IntentFilter(
                Constants.BROADCAST_ACTION_RECEIVE_EVENTS);
        Receiver receiver = new Receiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, mStatusIntentFilter);
    }
    public void callEventsService()
    {
        EventsIntentService.startActionGetEvents(this, userInSession.getUserId());
    }
    public void callSynchronizeService()
    {
        SynchronizeIntentService.startActionSynchronize(this);
    }
    private String getDateFormatted(Date date)
    {
        DateTime dateTime = new DateTime(date);
        DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss");
        return dtf.print(dateTime);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list_events, menu);
        optionsMenu = menu;
        setRefreshActionButtonState(true);
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
        if(id == R.id.action_create_event)
        {

            Intent intent = new Intent(ListEventsActivity.this, CreateEventActivity.class);
            intent.putExtra(
                    UserDto.class.getCanonicalName(),
                    (Parcelable)userInSession);
            startActivity(intent);
        }
        return id == R.id.action_refresh || super.onOptionsItemSelected(item);
    }
    @Override
    public void onStart()
    {
        super.onStart();
        setRefreshActionButtonState(true);
        callEventsService();
        loadEvents();
        callSynchronizeService();
    }
    @Override
    public void onResume()
    {
        super.onResume();
        loadEvents();
    }
    @Override
    public void showErrorMessage(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSuccessMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper;
    }
    public void loadEvents()
    {
        try {
            Comparator<EventDto> comparator = new Comparator<EventDto>() {
                @Override
                public int compare(EventDto lhs, EventDto rhs) {
                    return rhs.getCallDate().compareTo(lhs.getCallDate());
                }
            };
            List<EventDto> listLocalEvents = getEventService().getEventsNotSaved();
            List<EventDto> list = getEventService().getEventsActive();
            Collections.sort(list, comparator);
            Collections.sort(listLocalEvents, comparator);
            if (!list.isEmpty() || !listLocalEvents.isEmpty()) {
                ListEventsActivity activity = ListEventsActivity.this;
                GridView grid = (GridView) activity.findViewById(R.id.listViewEventsActive);
                EventArrayAdapter adapter = new EventArrayAdapter(activity, list);
                grid.setAdapter(adapter);
                grid = (GridView) activity.findViewById(R.id.listViewEventsNoCode);
                EventArrayAdapter localAdapter = new EventArrayAdapter(activity, listLocalEvents);
                grid.setAdapter(localAdapter);
            }
        }
        catch(Exception e)
        {

            Log.e(ListEventsActivity.class.getCanonicalName(), e.getMessage());
        }
        finally
        {
            setRefreshActionButtonState(false);
        }
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
    private class Receiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {
            loadEvents();
        }
    }
    public EventService getEventService() throws SQLException {
        if(eventService == null)
        {
            eventService = new EventService(getHelper());
        }
        return eventService;
    }
}
