package com.artica.telesalud.tph.android.fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.artica.telesalud.tph.android.R;
import com.artica.telesalud.tph.android.activity.EvaluacionPatientActivity;

/**
 * Created by interoperabilidad on 23/07/14.
 */
public class FragmentSelectorManager {


    public static void initViewInsumos(final EvaluacionPatientActivity activity, final View view, final String fragmentTag)
    {
        if(activity != null && view != null)
        {
            View button;
            if(!activity.getString(R.string.fragment_insumos_tag).equals(fragmentTag))
            {
                button = view.findViewById(R.id.buttonLiquidos);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FragmentManager fm = activity.getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.realtabcontent, new InsumosFragment());
                        ft.commit();
                    }
                });
            }
            if(!activity.getString(R.string.fragment_oxigeno_tag).equals(fragmentTag))
            {
                button = view.findViewById(R.id.buttonOxigeno);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FragmentManager fm = activity.getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.realtabcontent, new OxigenoFragment());
                        ft.commit();
                    }
                });
            }
        }
    }
    public static void initViewRemision(final EvaluacionPatientActivity activity, final View view, final String fragmentTag)
    {
        if(activity != null && view != null)
        {
            View button;
            if(!activity.getString(R.string.fragment_remision_tag).equals(fragmentTag))
            {
                button = view.findViewById(R.id.buttonRemision);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FragmentManager fm = activity.getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.realtabcontent, new RemisionFragment());
                        ft.commit();
                    }
                });
            }
            if(!activity.getString(R.string.fragment_responsables_tag).equals(fragmentTag))
            {
                button = view.findViewById(R.id.buttonResponsables);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FragmentManager fm = activity.getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.realtabcontent, new ResponsablesFragment());
                        ft.commit();
                    }
                });
            }
            if(!activity.getString(R.string.fragment_recepcion_tag).equals(fragmentTag))
            {
                button = view.findViewById(R.id.buttonRecepcion);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FragmentManager fm = activity.getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.realtabcontent, new RecepcionFragment());
                        ft.commit();
                    }
                });
            }
        }
    }

    public static void initViewAntecedentes(final EvaluacionPatientActivity activity, final View view, final String fragmentTag)
    {
        if(activity != null && view != null)
        {
           View button;
            if(!activity.getString(R.string.fragment_antecedentes_patient_tag).equals(fragmentTag))
            {
                button = view.findViewById(R.id.buttonAntecedentes);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FragmentManager fm = activity.getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.realtabcontent,new AntecedentesPatientFragment());
                        ft.commit();
                    }
                });
            }
            if(!activity.getString(R.string.fragment_identification_patient_tag).equals(fragmentTag))
            {
                button = view.findViewById(R.id.buttonIdentificacion);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        FragmentManager fm = activity.getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.realtabcontent,new IdentificacionPacienteFragment());
                        ft.commit();
                    }
                });
            }
            if(!activity.getString(R.string.fragment_domicilio_tag).equals(fragmentTag))
            {

                button = view.findViewById(R.id.buttonDomicilio);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        FragmentManager fm = activity.getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.realtabcontent,new PatientAddressFragment());
                        ft.commit();
                    }
                });
            }
            if(!activity.getString(R.string.fragment_companion_tag).equals(fragmentTag))
            {


                button = view.findViewById(R.id.buttonCompanion);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        FragmentManager fm = activity.getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.realtabcontent,new CompanionFragment());
                        ft.commit();
                    }
                });
            }
            if(!activity.getString(R.string.fragment_aseguramiento_tag).equals(fragmentTag))
            {


                button = view.findViewById(R.id.buttonAseguramiento);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        FragmentManager fm = activity.getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.realtabcontent,new AseguramientoFragment());
                        ft.commit();
                    }
                });
            }
        }
    }
    public static void initViewEvaluacion(final EvaluacionPatientActivity activity, final View view, final String fragmentTag)
    {
        if(activity != null && view != null)
        {
            View button;
            if(!activity.getString(R.string.fragment_evaluacion_a_tag).equals(fragmentTag)) {
                button = view.findViewById(R.id.imageButtonEvaluacionA);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FragmentManager fm = activity.getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        //ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                        ft.replace(R.id.realtabcontent, EvaluacionAFragment.newInstance());
                        ft.commit();
                    }
                });
            }
            if(!activity.getString(R.string.fragment_evaluacion_b_tag).equals(fragmentTag)) {
                button =  view.findViewById(R.id.imageButtonEvaluacionB);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FragmentManager fm = activity.getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        //ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                        ft.replace(R.id.realtabcontent, EvaluacionBFragment.newInstance());
                        ft.commit();
                    }
                });
            }

            if(!activity.getString(R.string.fragment_evaluacion_c_tag).equals(fragmentTag)) {
                button =  view.findViewById(R.id.imageButtonEvaluacionC);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FragmentManager fm = activity.getSupportFragmentManager();

                        FragmentTransaction ft = fm.beginTransaction();
                        //ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                        ft.replace(R.id.realtabcontent, new EvaluacionCFragment());
                        ft.commit();
                    }
                });
            }
            if(!activity.getString(R.string.fragment_evaluacion_d_tag).equals(fragmentTag)) {
                button =  view.findViewById(R.id.imageButtonEvaluacionD);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FragmentManager fm = activity.getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        //ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                        ft.replace(R.id.realtabcontent, new EvaluacionDFragment());
                        ft.commit();
                    }
                });
            }
            if(!activity.getString(R.string.fragment_evaluacion_e_tag).equals(fragmentTag)) {
                button =  view.findViewById(R.id.imageButtonEvaluacionE);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FragmentManager fm = activity.getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        //ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);

                        ft.replace(R.id.realtabcontent, new EvaluacionEFragment());
                        ft.commit();
                    }
                });
            }
            if(!activity.getString(R.string.fragment_emergencia_medica_tag).equals(fragmentTag)) {
                button =  view.findViewById(R.id.imageButtonEmergenciaMedica);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FragmentManager fm = activity.getSupportFragmentManager();

                        FragmentTransaction ft = fm.beginTransaction();
                        //ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                        ft.replace(R.id.realtabcontent,  new EmergenciaMedicaFragment());

                        ft.commit();
                    }
                });
            }
            if(!activity.getString(R.string.fragment_procedimientos_tag).equals(fragmentTag)) {
                button =  view.findViewById(R.id.imageButtonProcedimientos);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FragmentManager fm = activity.getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        //ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                        ft.replace(R.id.realtabcontent, new ProcedimientosFragment());
                        ft.commit();
                    }
                });
            }
            if(!activity.getString(R.string.fragment_triage_tag).equals(fragmentTag)) {
                button =  view.findViewById(R.id.imageButtonTrauma);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FragmentManager fm = activity.getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        //ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                        ft.replace(R.id.realtabcontent, new TriageFragment());
                        ft.commit();
                    }
                });
            }
        }
    }
}
