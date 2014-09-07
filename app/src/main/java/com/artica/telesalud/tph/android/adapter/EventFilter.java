package com.artica.telesalud.tph.android.adapter;

import android.content.Context;
import android.util.Log;
import android.widget.Filter;

import com.artica.telesalud.tph.android.R;
import com.artica.telesalud.tph.android.activity.ListEventsActivity;
import com.artica.telesalud.tph.android.lightweightmodel.EventDto;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by interoperabilidad on 27/05/14.
 */
public class EventFilter extends Filter {
    private List<EventDto> events = new ArrayList<EventDto>();
    private EventArrayAdapter adapter;
    private Context context;
    private static final HashMap<String, String> replacements = new HashMap<String, String>();
    static {
        replacements.put("á", "a");
        replacements.put("é", "e");
        replacements.put("í", "i");
        replacements.put("ó", "o");
        replacements.put("ú", "u");
        replacements.put("Á", "A");
        replacements.put("É", "E");
        replacements.put("Í", "I");
        replacements.put("Ó", "O");
        replacements.put("Ú", "U");
    }
    public EventFilter(List<EventDto> events, EventArrayAdapter adapter, Context context)
    {
        this.events.addAll(events);
        this.adapter = adapter;
        this.context = context;
    }
    private String getDateFormatted(Date date)
    {
        DateTime dateTime = new DateTime(date);
        DateTimeFormatter dtf = DateTimeFormat.forPattern(context.getString(R.string.date_time_format));
        return dtf.print(dateTime);
    }
    public static String removeAccents(String s)
    {
        StringBuilder sb = new StringBuilder();
        for(char c : s.toCharArray()){
            String x = String.valueOf(c);
            String value = replacements.get(x);
            if(value != null)
            {
                sb.append(value);
            }
            else{
                sb.append(x);
            }
        }
        return sb.toString();
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraints) {
        List<EventDto> result = new ArrayList<EventDto>();
        FilterResults results = new FilterResults();
        Log.e("Tag3: ", String.valueOf(events.size()));
        String[] split = constraints.toString().split(" ");
        for(int i = 0; i < split.length; i++)
        {
            String s = removeAccents(split[i]);
            split[i] = s;
        }
            for (EventDto event : events) {
                Boolean validAll = Boolean.TRUE;
                for(String constraint : split) {
                    Boolean valid = Boolean.FALSE;

                    valid |= removeAccents(event.getState()).toLowerCase().contains(constraint.toLowerCase());
                    valid |= removeAccents(event.getCaseNumber()).toLowerCase().contains(constraint.toLowerCase());
                    valid |= removeAccents(event.getCausaAtencion().getName()).toLowerCase().contains(constraint.toLowerCase());
                    valid |= removeAccents(event.getDireccion()).toLowerCase().contains(constraint.toLowerCase());
                    valid |= removeAccents(getDateFormatted(event.getCallDate())).toLowerCase().contains(constraint.toLowerCase());
                    valid |= removeAccents(event.getCity().getName()).toLowerCase().contains(constraint.toLowerCase());
                    valid |= removeAccents(event.getCity().getState().getName()).toLowerCase().contains(constraint.toLowerCase());
                    validAll &= valid;
                }
                if (validAll) {
                    result.add(event);
                }
            }

        results.count = result.size();
        results.values = result;
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {


        List<EventDto> result = (List<EventDto>)results.values;
        adapter.clear();
        for (EventDto event : result) {
             adapter.add(event);
        }
        adapter.notifyDataSetChanged();

    }
}
