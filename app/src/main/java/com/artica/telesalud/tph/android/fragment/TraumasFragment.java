package com.artica.telesalud.tph.android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.artica.telesalud.tph.android.R;
import com.artica.telesalud.tph.android.lightweightmodel.EvaluacionDto;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TraumasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TraumasFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class TraumasFragment extends AbstractEvaluacionFragment {

    public TraumasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    @Override
    public int getViewId() {
        return R.layout.fragment_traumas;
    }
    @Override
    public int getFragmentTagId() {
        return R.string.fragment_trauma_tag;
    }

    public void updateEvaluacion(final EvaluacionDto evaluacion){}
    @Override
    public void updateButtonSelected() {
        ImageButton button = (ImageButton)view.findViewById(R.id.imageButtonTrauma);
        button.setActivated(Boolean.TRUE);
    }
}
