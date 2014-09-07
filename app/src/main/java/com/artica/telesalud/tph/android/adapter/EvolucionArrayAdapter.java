package com.artica.telesalud.tph.android.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.artica.telesalud.tph.android.R;
import com.artica.telesalud.tph.android.lightweightmodel.EvaluacionDto;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by interoperabilidad on 23/07/14.
 */
public class EvolucionArrayAdapter extends ArrayAdapter<EvaluacionDto> {
    private List<EvaluacionDto> evaluaciones= new ArrayList<EvaluacionDto>();
    private final Context context;
    private View selectedView;

    public View getSelectedView() {
        return selectedView;
    }

    public void setSelectedView(View selectedView) {
        this.selectedView = selectedView;
    }

    private static class ViewHolder
    {
        public TextView textViewIdEvaluacion;
        public TextView textViewFechaEvaluacion;
        public View layoutEvolucion;

    }
    public EvolucionArrayAdapter(Context context, List<EvaluacionDto> evaluaciones) {
        super(context, R.layout.evolucion_item, evaluaciones);
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.evolucion_item, parent, false);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.textViewIdEvaluacion = (TextView)convertView.findViewById(R.id.textViewIdEvaluacion);
            viewHolder.textViewFechaEvaluacion = (TextView)convertView.findViewById(R.id.textViewFechaEvaluacion);
            viewHolder.layoutEvolucion = convertView.findViewById(R.id.layoutItemEvolucion);
            convertView.setTag(viewHolder);
        }
        ViewHolder viewHolder = (ViewHolder)convertView.getTag();
        EvaluacionDto evaluacion = getItem(position);
        TextView textViewIdEvaluacion = viewHolder.textViewIdEvaluacion;
        textViewIdEvaluacion.setText(String.valueOf(position+1));
        TextView textViewFechaEvaluacion = viewHolder.textViewFechaEvaluacion;
        if(evaluacion.getSelected()) {
            convertView.setBackgroundColor(Color.WHITE);
        }
        else{
           convertView.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.evolucion_item));
        }
        DateTime dateTime = new DateTime(evaluacion.getEvaluacion().getDateCreated());
        DateTimeFormatter dtf = DateTimeFormat.forPattern("hh:mm:ss a");
        textViewFechaEvaluacion.setText(String.valueOf(dtf.print(dateTime)));
        return convertView;
    }
}
