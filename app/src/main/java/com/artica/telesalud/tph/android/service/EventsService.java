package com.artica.telesalud.tph.android.service;

import android.util.Log;
import android.widget.GridView;

import com.artica.telesalud.tph.android.R;
import com.artica.telesalud.tph.android.activity.BaseActivity;
import com.artica.telesalud.tph.android.adapter.EventArrayAdapter;
import com.artica.telesalud.tph.android.dao.EventService;
import com.artica.telesalud.tph.android.lightweightmodel.EventDto;
import com.artica.telesalud.tph.android.lightweightmodel.ListEvents;

import java.util.List;

/**
 * Created by interoperabilidad on 26/05/14.
 */
public class EventsService extends GetListAsyncService<Integer, Void, ListEvents> {
    public EventsService(BaseActivity activity) {

        super(activity);
    }

    @Override
    protected ListEvents doInBackground(Integer... params) {

        try {
            EventService eventService = new EventService(activity.getHelper());
            List<EventDto> listLocalEvents = eventService.getEventsNotSaved();
            List<EventDto> list = eventService.getEventsActive();
            if (!list.isEmpty() || !listLocalEvents.isEmpty()) {
                GridView grid = (GridView) activity.findViewById(R.id.listViewEventsActive);
                EventArrayAdapter adapter = new EventArrayAdapter(activity, list);
                grid.setAdapter(adapter);
                grid = (GridView) activity.findViewById(R.id.listViewEventsNoCode);
                EventArrayAdapter localAdapter =(EventArrayAdapter)grid.getAdapter();
                localAdapter = new EventArrayAdapter(activity, listLocalEvents);
                grid.setAdapter(localAdapter);
            }
        }
        catch(Exception e)
        {
            Log.e("TAG", e.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(ListEvents events) {


    }

}
