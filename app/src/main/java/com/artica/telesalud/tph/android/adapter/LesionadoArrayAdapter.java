package com.artica.telesalud.tph.android.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.artica.telesalud.tph.android.R;
import com.artica.telesalud.tph.android.lightweightmodel.EvaluacionDto;
import com.artica.telesalud.tph.android.lightweightmodel.LesionadoDto;

import java.util.List;

public class LesionadoArrayAdapter extends ArrayAdapter<LesionadoDto> {

    private final Context context;
    
    public LesionadoArrayAdapter(Context context, List<LesionadoDto> lesionados) {
        super(context, R.layout.lesionado_item, lesionados);

        this.context = context;
    }
    private static class ViewHolder
    {

        public TextView textViewConsecutivo ;
        public TextView textViewGender;
        public TextView textViewNombre;
        public TextView textViewIdentificacion;
        public LinearLayout linearLayoutTriage;
        public TextView textViewTriage;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Integer count = getCount();
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.lesionado_item, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.textViewConsecutivo = (TextView) convertView.findViewById(R.id.textViewConsecutivoLesionadoList);
            viewHolder.textViewGender = (TextView) convertView.findViewById(R.id.textViewGenderLesionadotList);
            viewHolder.textViewNombre = (TextView) convertView.findViewById(R.id.textViewNameLesionadoList);
            viewHolder.textViewIdentificacion = (TextView) convertView.findViewById(R.id.textViewIdentificacionLesionadoList);
            viewHolder.linearLayoutTriage = (LinearLayout)convertView.findViewById(R.id.linearLayoutTriage);
            viewHolder.textViewTriage =(TextView)convertView.findViewById(R.id.textViewTriageLesionadoList);
            convertView.setTag(viewHolder);
        }
        ViewHolder viewHolder = (ViewHolder)convertView.getTag();
        LesionadoDto lesionado = getItem(position);
        StringBuilder id = new StringBuilder();
        if(lesionado.getEventLocalIdentifier() != null) {
            id.append(lesionado.getEventLocalIdentifier()).append("/").append(count);
        }
        else
        {
            id.append(position + 1).append("/").append(count);
        }
        viewHolder.textViewConsecutivo.setText(id.toString());
        if(lesionado.getEncuentro() != null) {
            if(lesionado.getEncuentro().getPatient() != null) {
                if(lesionado.getEncuentro().getPatient().getPerson() != null) {
                    if(lesionado.getEncuentro().getPatient().getPerson().getGender() != null)
                        viewHolder.textViewGender.setText(lesionado.getEncuentro().getPatient().getPerson().getGender());
                }
            }
        }
        if(lesionado.getEncuentro() != null) {
            if(lesionado.getEncuentro().getPatient() != null) {
                if(lesionado.getEncuentro().getPatient().getPreferredIdentifier() != null)
                {
                    if(lesionado.getEncuentro().getPatient().getPreferredIdentifier().getIdentifier() != null) {
                        viewHolder.textViewIdentificacion.setText(String.valueOf(lesionado.getEncuentro().getPatient().getPreferredIdentifier().getIdentifier()));
                    }
                }
                if(lesionado.getEncuentro().getPatient().getPerson() != null) {
                    if(lesionado.getEncuentro().getPatient().getPerson().getPreferredName() != null) {
                        viewHolder.textViewNombre.setText((lesionado.getEncuentro().getPatient().getPerson().getPreferredName().getGivenName() == null ? "" : lesionado.getEncuentro().getPatient().getPerson().getPreferredName().getGivenName())
                                +" "+(lesionado.getEncuentro().getPatient().getPerson().getPreferredName().getMiddleName() == null ? "" : lesionado.getEncuentro().getPatient().getPerson().getPreferredName().getMiddleName())
                                + " " + (lesionado.getEncuentro().getPatient().getPerson().getPreferredName().getFamilyName() == null ? "" : lesionado.getEncuentro().getPatient().getPerson().getPreferredName().getFamilyName())
                                + " " + (lesionado.getEncuentro().getPatient().getPerson().getPreferredName().getFamilyName2() == null ? "" : lesionado.getEncuentro().getPatient().getPerson().getPreferredName().getFamilyName2()));

                    }
                }
            }
        }
        if(lesionado.getEvaluaciones() != null)
        {
            if(!lesionado.getEvaluaciones().isEmpty()) {
                EvaluacionDto evaluacion = lesionado.getEvaluaciones().get(lesionado.getEvaluaciones().size()-1);
                if (evaluacion != null && evaluacion.getPrioridadTriage() != null) {
                    if (EvaluacionDto.PRIORIDAD_TRIAGE_AMARILLO.equals(evaluacion.getPrioridadTriage())) {
                        viewHolder.linearLayoutTriage.setBackgroundColor(Color.YELLOW);
                        viewHolder.textViewConsecutivo.setTextColor(Color.BLACK);
                    }
                    else if (EvaluacionDto.PRIORIDAD_TRIAGE_BLANCO.equals(evaluacion.getPrioridadTriage())) {
                        viewHolder.linearLayoutTriage.setBackgroundColor(Color.WHITE);
                        viewHolder.textViewConsecutivo.setTextColor(Color.BLACK);
                    }
                    else if (EvaluacionDto.PRIORIDAD_TRIAGE_NEGRO.equals(evaluacion.getPrioridadTriage())) {
                        viewHolder.linearLayoutTriage.setBackgroundColor(Color.BLACK);
                        viewHolder.textViewConsecutivo.setTextColor(Color.WHITE);
                    }
                    else if (EvaluacionDto.PRIORIDAD_TRIAGE_ROJO.equals(evaluacion.getPrioridadTriage())) {
                        viewHolder.linearLayoutTriage.setBackgroundColor(Color.RED);
                        viewHolder.textViewConsecutivo.setTextColor(Color.WHITE);
                    }
                    else if (EvaluacionDto.PRIORIDAD_TRIAGE_VERDE.equals(evaluacion.getPrioridadTriage())) {
                        viewHolder.linearLayoutTriage.setBackgroundColor(Color.GREEN);
                        viewHolder.textViewConsecutivo.setTextColor(Color.WHITE);
                    }
                    else
                    {
                        viewHolder.linearLayoutTriage.setBackgroundColor(Color.BLUE);
                        viewHolder.textViewConsecutivo.setTextColor(Color.WHITE);
                    }
                }
                else
                {
                    viewHolder.linearLayoutTriage.setBackgroundColor(Color.BLUE);
                    viewHolder.textViewConsecutivo.setTextColor(Color.WHITE);
                }
                StringBuilder sb = new StringBuilder();
                if(evaluacion.getTriageAbdomenAgudo())
                {

                    sb.append(getContext().getString(R.string.abdomen_agudo_text).replaceAll("\n", "")).append("\n");
                }
                if(evaluacion.getTriageAfectados())
                {
                    sb.append(getContext().getString(R.string.afectados_text).replaceAll("\n", "")).append("\n");
                }
                if(evaluacion.getTriageAlteracionEstadoConciencia())
                {
                    sb.append(getContext().getString(R.string.alteracion_estado_conciencia_text).replaceAll("\n", "")).append("\n");
                }
                if(evaluacion.getTriageArritmias())
                {
                    sb.append(getContext().getString(R.string.arritmias_text).replaceAll("\n", "")).append("\n");
                }
                if(evaluacion.getTriageDolorToracico())
                {
                    sb.append(getContext().getString(R.string.dolor_toracico_text).replaceAll("\n", "")).append("\n");
                }
                if(evaluacion.getTriageEvisceracion())
                {
                    sb.append(getContext().getString(R.string.evisceracion_text).replaceAll("\n", "")).append("\n");
                }
                if(evaluacion.getTriageExcitacionPsicomotora())
                {
                    sb.append(getContext().getString(R.string.excitacion_psicomotora_text).replaceAll("\n", "")).append("\n");
                }
                if(evaluacion.getTriageExposicionMasaEncefalica())
                {
                    sb.append(getContext().getString(R.string.exposicion_masa_encefalica_text).replaceAll("\n", "")).append("\n");
                }
                if(evaluacion.getTriageFracturasMayores())
                {
                    sb.append(getContext().getString(R.string.fracturas_mayores_text).replaceAll("\n", "")).append("\n");
                }
                if(evaluacion.getTriageFracturasNoProximales())
                {
                    sb.append(getContext().getString(R.string.fracturas_no_proximales_text).replaceAll("\n", "")).append("\n");
                }
                if(evaluacion.getTriageGlasgow14o15())
                {
                    sb.append(getContext().getString(R.string.glasgow1415_text).replaceAll("\n", "")).append("\n");
                }
                if(evaluacion.getTriageGlasgow3())
                {
                    sb.append(getContext().getString(R.string.glasgow3_text).replaceAll("\n", "")).append("\n");
                }
                if(evaluacion.getTriageGlasgow4a8())
                {
                    sb.append(getContext().getString(R.string.glasgow48_text).replaceAll("\n", "")).append("\n");
                }
                if(evaluacion.getTriageGlasgow9a13())
                {
                    sb.append(getContext().getString(R.string.glasgow913_text).replaceAll("\n", "")).append("\n");
                }
                if(evaluacion.getTriageHemorragiasControladas())
                {
                    sb.append(getContext().getString(R.string.hemorragias_controladas_text).replaceAll("\n", "")).append("\n");
                }
                if(evaluacion.getTriageHemorragiasNoControladas())
                {
                    sb.append(getContext().getString(R.string.hemorragias_no_controladas_text).replaceAll("\n", "")).append("\n");
                }
                if(evaluacion.getTriageInsuficienciaRespiratoria())
                {
                    sb.append(getContext().getString(R.string.insuficiencia_respiratoria_text).replaceAll("\n", "")).append("\n");
                }
                if(evaluacion.getTriageIntoxicacion())
                {
                    sb.append(getContext().getString(R.string.intoxicacion_triage_text).replaceAll("\n", "")).append("\n");
                }
                if(evaluacion.getTriageLesionCervicalCompleta())
                {
                    sb.append(getContext().getString(R.string.lesion_cervical_completa_text).replaceAll("\n", "")).append("\n");
                }
                if(evaluacion.getTriageLesionCervicalIncompleta())
                {
                    sb.append(getContext().getString(R.string.lesion_cervical_incompleta_text).replaceAll("\n", "")).append("\n");
                }
                if(evaluacion.getTriageLesionesImpidenRcp())
                {
                    sb.append(getContext().getString(R.string.lesiones_impiden_rcp_text).replaceAll("\n", "")).append("\n");
                }
                if(evaluacion.getTriageLesionesSuperficiales())
                {
                    sb.append(getContext().getString(R.string.lesiones_superficiales_text).replaceAll("\n", "")).append("\n");
                }
                if(evaluacion.getTriageLesionMedularDorsal())
                {
                    sb.append(getContext().getString(R.string.lesion_medular_dorsal_text).replaceAll("\n", "")).append("\n");
                }
                if(evaluacion.getTriageLesionMedularLumbar())
                {
                    sb.append(getContext().getString(R.string.lesion_medular_lumbar_text).replaceAll("\n", "")).append("\n");
                }
                if(evaluacion.getTriageMultiplesHeridas())
                {
                    sb.append(getContext().getString(R.string.multiplesHeridas_text).replaceAll("\n", "")).append("\n");
                }
                if(evaluacion.getTriagePa90())
                {
                    sb.append(getContext().getString(R.string.pa90_text).replaceAll("\n", "")).append("\n");
                }
                if(evaluacion.getTriageParoProlongado())
                {
                    sb.append(getContext().getString(R.string.paro_prolongado_text).replaceAll("\n", "")).append("\n");
                }
                if(evaluacion.getTriageQuemadurasGraves())
                {
                    sb.append(getContext().getString(R.string.quemaduras_graves_text).replaceAll("\n", "")).append("\n");
                }
                if(evaluacion.getTriageQuemadurasModeradas())
                {
                    sb.append(getContext().getString(R.string.quemaduras_moderadas_text).replaceAll("\n", "")).append("\n");
                }
                if(evaluacion.getTriageQuemadurasPrimerGrado())
                {
                    sb.append(getContext().getString(R.string.quemaduras_grado_i_text).replaceAll("\n", "")).append("\n");
                }
                if(evaluacion.getTriageTrabajoPartoSangrado())
                {
                    sb.append(getContext().getString(R.string.trabajo_parto_sangrado_text).replaceAll("\n", "")).append("\n");
                }
                if(evaluacion.getTriageViaAreaObstruida())
                {
                    sb.append(getContext().getString(R.string.via_aerea_riesgo_text).replaceAll("\n", "")).append("\n");
                }
                if(evaluacion.getTriageOtro() != null)
                {
                    sb.append(evaluacion.getTriageOtro());
                }
                viewHolder.textViewTriage.setText(sb.toString());
            }
            else
            {
                viewHolder.linearLayoutTriage.setBackgroundColor(Color.BLUE);
                viewHolder.textViewConsecutivo.setTextColor(Color.WHITE);
            }
        }
        else
        {
            viewHolder.linearLayoutTriage.setBackgroundColor(Color.BLUE);
            viewHolder.textViewConsecutivo.setTextColor(Color.WHITE);
        }
        return convertView;
    }


}
