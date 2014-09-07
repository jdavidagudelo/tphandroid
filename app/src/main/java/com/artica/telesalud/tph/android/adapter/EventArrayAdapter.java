package com.artica.telesalud.tph.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.artica.telesalud.tph.android.R;
import com.artica.telesalud.tph.android.lightweightmodel.EventDto;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by interoperabilidad on 27/05/14.
 */
public class EventArrayAdapter extends ArrayAdapter<EventDto> {

    private EventFilter eventFilter;
    @Override
    public Filter getFilter() {

        if(eventFilter == null)
        {
            eventFilter = new EventFilter(events, this, context);
        }
        return eventFilter;
    }
    private static class ViewHolder
    {
        TextView textViewCaseNumber;
        TextView textViewAddress;
        TextView textViewCity;
        TextView textViewCause;
        TextView textViewDate;
    }
    private List<EventDto> events = new ArrayList<EventDto>();
    private final Context context;
    public EventArrayAdapter(Context context, List<EventDto> events) {
        super(context, R.layout.event_item, events);
        this.events.addAll(events);
        this.context = context;
    }
    private String getDateFormatted(Date date)
    {
        DateTime dateTime = new DateTime(date);
        DateTimeFormatter dtf = DateTimeFormat.forPattern(context.getString(R.string.date_time_format));
        return dtf.print(dateTime);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if(convertView == null) {
               convertView = inflater.inflate(R.layout.event_item, parent, false);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.textViewCaseNumber = (TextView) convertView.findViewById(R.id.textViewCaseNumberEventList);
                viewHolder.textViewAddress = (TextView) convertView.findViewById(R.id.textViewAddressEventList);
                viewHolder.textViewCity = (TextView) convertView.findViewById(R.id.textViewCityEventList);
                viewHolder.textViewCause = (TextView) convertView.findViewById(R.id.textViewCauseEventList);
                viewHolder.textViewDate = (TextView) convertView.findViewById(R.id.textViewDateEventList);
                convertView.setTag(viewHolder);
            }
            ViewHolder viewHolder = (ViewHolder)convertView.getTag();
            TextView textViewCaseNumber = viewHolder.textViewCaseNumber;
            TextView textViewAddress = viewHolder.textViewAddress;
            TextView textViewCity = viewHolder.textViewCity;
            TextView textViewCause = viewHolder.textViewCause;
            TextView textViewDate = viewHolder.textViewDate;
            EventDto event = getItem(position);
            textViewCaseNumber.setText(event.getCaseNumber());
            if(event.getCity() != null && event.getCity().getState() != null) {
                textViewCity.setText(event.getCity().getName() + " (" + event.getCity().getState().getName() + ")");
            }
            else
            {
                if(event.getCityName() != null && event.getStateName() != null)
                {
                    textViewCity.setText(event.getCityName() + " (" + event.getStateName() + ")");
                }
            }
            textViewAddress.setText(event.getDireccion());
            if(event.getCausaAtencion() != null) {
                textViewCause.setText(event.getCausaAtencion().getName());
            }
            else
            {
                textViewCause.setText(getContext().getString(R.string.other_event_cause));
            }
            textViewDate.setText(getDateFormatted(event.getCallDate()));
            return convertView;
    }


}
