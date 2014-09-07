package com.artica.telesalud.tph.android.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.artica.telesalud.tph.android.R;
import com.artica.telesalud.tph.android.activity.EvaluacionPatientActivity;
import com.artica.telesalud.tph.android.adapter.EvolucionArrayAdapter;
import com.artica.telesalud.tph.android.dao.EvaluacionCompletaService;
import com.artica.telesalud.tph.android.dao.EvaluacionService;
import com.artica.telesalud.tph.android.dao.LesionadoService;
import com.artica.telesalud.tph.android.dao.ProcedimientosService;
import com.artica.telesalud.tph.android.lightweightmodel.EvaluacionCompletaDto;
import com.artica.telesalud.tph.android.lightweightmodel.EvaluacionDto;
import com.artica.telesalud.tph.android.lightweightmodel.EventDto;
import com.artica.telesalud.tph.android.lightweightmodel.LesionadoDto;
import com.artica.telesalud.tph.android.lightweightmodel.UserDto;

import java.sql.SQLException;

/**
 * Created by interoperabilidad on 29/07/14.
 */
public abstract class AbstractEvaluacionFragment extends Fragment{
    protected LesionadoDto lesionado;
    protected View view;
    protected EventDto event;
    protected UserDto userInSession;
    protected EvaluacionDto evaluacion;
    protected EvaluacionService evaluacionService;
    protected EvaluacionCompletaService evaluacionCompletaService;
    protected LesionadoService lesionadoService;
    protected ProcedimientosService procedimientosService;
    protected EvaluacionPatientActivity activity;
    public LesionadoDto getLesionado() {
        return lesionado;
    }

    public void setLesionado(LesionadoDto lesionado) {
        this.lesionado = lesionado;
    }

    public EventDto getEvent() {
        return event;
    }

    public void setEvent(EventDto event) {
        this.event = event;
    }

    public UserDto getUserInSession() {
        return userInSession;
    }

    public void setUserInSession(UserDto userInSession) {
        this.userInSession = userInSession;
    }

    public EvaluacionDto getEvaluacion() {
        return evaluacion;
    }
    public abstract int getViewId();
    public abstract int getFragmentTagId();
    public abstract void updateButtonSelected();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(getViewId(), container, false);
        activity = ((EvaluacionPatientActivity)getActivity());
        FragmentSelectorManager.initViewEvaluacion(activity, view, activity.getString(getFragmentTagId()));
        lesionado = activity.getLesionado();
        userInSession = activity.getUserInSession();
        evaluacion = activity.getEvaluacionSelected();
        try {
            evaluacionService = new EvaluacionService(activity.getHelper());
            lesionadoService = new LesionadoService(activity.getHelper());
            evaluacionCompletaService = new EvaluacionCompletaService(activity.getHelper());
            procedimientosService = new ProcedimientosService(activity.getHelper());
        } catch (SQLException e) {
            Log.e(AbstractEvaluacionFragment.class.getCanonicalName(), e.getMessage());
        }
        ImageButton button = (ImageButton)view.findViewById(R.id.imageButtonAddEvolucion);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    EvaluacionCompletaDto evaluacion = evaluacionCompletaService.createNew(lesionado.getEncuentro(), userInSession.getUserId());
                    EvaluacionDto evaluacionDto = new EvaluacionDto();
                    evaluacionDto.setEvaluacion(evaluacion);
                    evaluacionService.save(evaluacionDto);
                    if(lesionado.getId() != null) {
                        lesionado = lesionadoService.getLesionadoById(lesionado.getId());
                    }
                    else
                    {
                        lesionado = lesionadoService.getLesionado(lesionado.getLesionadoId());
                    }
                    activity.setLesionado(lesionado);
                    ListView listViewEvaluaciones = (ListView)view.findViewById(R.id.listViewEvoluciones);
                    for(EvaluacionDto evaluation : lesionado.getEvaluaciones())
                    {
                        if(evaluation.getId().equals(AbstractEvaluacionFragment.this.evaluacion.getId()))
                        {
                            evaluation.setSelected(Boolean.TRUE);
                            activity.setEvaluacionSelected(evaluation);
                            setEvaluacion(evaluation);
                            updateEvaluacion(evaluation);
                        }
                    }
                    EvolucionArrayAdapter adapter = new EvolucionArrayAdapter(activity, lesionado.getEvaluaciones());
                    listViewEvaluaciones.setAdapter(adapter);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        if(lesionado != null)
        {
            final ListView listViewEvaluaciones = (ListView)view.findViewById(R.id.listViewEvoluciones);
            for(EvaluacionDto evaluation : lesionado.getEvaluaciones())
            {
                if(evaluation.getId().equals(AbstractEvaluacionFragment.this.evaluacion.getId()))
                {
                    evaluation.setSelected(Boolean.TRUE);
                    activity.setEvaluacionSelected(evaluation);
                    setEvaluacion(evaluation);
                    updateEvaluacion(evaluation);
                }
            }
            EvolucionArrayAdapter adapter = new EvolucionArrayAdapter(activity, lesionado.getEvaluaciones());
            listViewEvaluaciones.setAdapter(adapter);

            listViewEvaluaciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    savePreviousState();
                    EvolucionArrayAdapter adapter = (EvolucionArrayAdapter)parent.getAdapter();
                    EvaluacionDto evaluacion = adapter.getItem(position);
                    if(AbstractEvaluacionFragment.this.evaluacion != null) {
                        AbstractEvaluacionFragment.this.evaluacion.setSelected(Boolean.FALSE);
                    }
                    View selectedView = adapter.getSelectedView();
                    if(selectedView != null)
                    {
                        //noinspection deprecation
                        selectedView.setBackgroundDrawable(getResources().getDrawable(R.drawable.evolucion_item));
                    }
                    evaluacion.setSelected(true);
                    view.setBackgroundColor(Color.WHITE);
                    activity.setEvaluacionSelected(evaluacion);
                    AbstractEvaluacionFragment.this.evaluacion = evaluacion;
                    adapter.setSelectedView(view);
                    saveEvaluacion(evaluacion);
                    adapter.notifyDataSetChanged();
                    updateEvaluacion(evaluacion);
                }
            });
        }
        updateButtonSelected();
        return view;
    }
    public void saveEvaluacion(final EvaluacionDto evaluacion)
    {
        try {

           evaluacionService.save(evaluacion);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void setEvaluacion(EvaluacionDto evaluacion)
    {
        this.evaluacion = evaluacion;
    }


    @Override
    public void onDetach ()
    {
        super.onDetach();
        Log.e("TAG", "Detach");
    }
    public abstract void updateEvaluacion(final EvaluacionDto evaluacion);
    public void savePreviousState(){}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


}
