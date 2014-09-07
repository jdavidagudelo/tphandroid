package com.artica.telesalud.tph.android.fragment;

import android.graphics.PointF;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.artica.telesalud.tph.android.R;
import com.artica.telesalud.tph.android.dao.HallazgoService;
import com.artica.telesalud.tph.android.lightweightmodel.EvaluacionDto;
import com.artica.telesalud.tph.android.lightweightmodel.HallazgoDto;
import com.artica.telesalud.tph.android.view.ButtonHallazgo;
import com.artica.telesalud.tph.android.view.DraggableImageButton;
import com.artica.telesalud.tph.android.view.TouchImageView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EvaluacionEFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EvaluacionEFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class EvaluacionEFragment extends AbstractEvaluacionFragment implements View.OnTouchListener{

    public EvaluacionEFragment() {
        // Required empty public constructor
    }
    private HallazgoService hallazgoService;
    private LayoutInflater inflater;
    private TouchImageView touchImageView;
    private ButtonHallazgo buttonHallazgoSelected;
    private SparseArray<CheckBox> listLesiones = new SparseArray<CheckBox>();
    private SparseArray<ButtonHallazgo> buttonHallazgoSparseArray = new SparseArray<ButtonHallazgo>();
    private SparseArray<DraggableImageButton> draggableImageButtons = new SparseArray<DraggableImageButton>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.inflater = inflater;
        view = super.onCreateView(inflater, container, savedInstanceState);
        touchImageView = (TouchImageView) view.findViewById(R.id.imageViewBody);
        touchImageView.setMaxZoom(5.0f);
        touchImageView.setMinZoom(1.0f);

        Button buttonDeleteHallazgo = (Button)view.findViewById(R.id.buttonDeleteHallazgo);
        buttonDeleteHallazgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    deleteCurrentHallazgo();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        Button buttonAddHallazgo = (Button)view.findViewById(R.id.buttonAddHallazgo);
        CheckBox localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonAbrasion);
        listLesiones.append(R.id.radioButtonAbrasion, localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonHallazgoSelected != null) {
                    HallazgoDto hallazgo = buttonHallazgoSelected.getHallazgo();
                    if (hallazgo != null) {
                        hallazgo.setAbrasion(isChecked);
                        saveHallazgo(hallazgo);
                    }
                }
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonAmputacion);
        listLesiones.append(R.id.radioButtonAmputacion, localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonHallazgoSelected != null) {
                    HallazgoDto hallazgo = buttonHallazgoSelected.getHallazgo();
                    if (hallazgo != null) {
                        hallazgo.setAmputacion(isChecked);
                        saveHallazgo(hallazgo);
                    }
                }
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonAplastamiento);
        listLesiones.append(R.id.radioButtonAplastamiento, localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonHallazgoSelected != null) {
                    HallazgoDto hallazgo = buttonHallazgoSelected.getHallazgo();
                    if (hallazgo != null) {
                        hallazgo.setAplastamiento(isChecked);
                        saveHallazgo(hallazgo);
                    }
                }
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonAvulsion);
        listLesiones.append(R.id.radioButtonAvulsion, localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonHallazgoSelected != null) {
                    HallazgoDto hallazgo = buttonHallazgoSelected.getHallazgo();
                    if (hallazgo != null) {
                        hallazgo.setAvulsion(isChecked);
                        saveHallazgo(hallazgo);
                    }
                }
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonContusion);
        listLesiones.append(R.id.radioButtonContusion, localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonHallazgoSelected != null) {
                    HallazgoDto hallazgo = buttonHallazgoSelected.getHallazgo();
                    if (hallazgo != null) {
                        hallazgo.setContusion(isChecked);
                        saveHallazgo(hallazgo);
                    }
                }
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonDolor);
        listLesiones.append(R.id.radioButtonDolor, localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonHallazgoSelected != null) {
                    HallazgoDto hallazgo = buttonHallazgoSelected.getHallazgo();
                    if (hallazgo != null) {
                        hallazgo.setDolor(isChecked);
                        saveHallazgo(hallazgo);
                    }
                }
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonEsquince);
        listLesiones.append(R.id.radioButtonEsquince, localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonHallazgoSelected != null) {
                    HallazgoDto hallazgo = buttonHallazgoSelected.getHallazgo();
                    if (hallazgo != null) {
                        hallazgo.setEsguince(isChecked);
                        saveHallazgo(hallazgo);
                    }
                }
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonFracturaAbierta);
        listLesiones.append(R.id.radioButtonFracturaAbierta, localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonHallazgoSelected != null) {
                    HallazgoDto hallazgo = buttonHallazgoSelected.getHallazgo();
                    if (hallazgo != null) {
                        hallazgo.setFracturaAbierta(isChecked);
                        saveHallazgo(hallazgo);
                    }
                }
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonQuemadura);
        listLesiones.append(R.id.radioButtonQuemadura, localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonHallazgoSelected != null) {
                    HallazgoDto hallazgo = buttonHallazgoSelected.getHallazgo();
                    if (hallazgo != null) {
                        hallazgo.setQuemadura(isChecked);
                        saveHallazgo(hallazgo);
                    }
                }
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonHerida);
        listLesiones.append(R.id.radioButtonHerida, localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonHallazgoSelected != null) {
                    HallazgoDto hallazgo = buttonHallazgoSelected.getHallazgo();
                    if (hallazgo != null) {
                        hallazgo.setHerida(isChecked);
                        saveHallazgo(hallazgo);
                    }
                }
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonFracturaCerrada);
        listLesiones.append(R.id.radioButtonFracturaCerrada, localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonHallazgoSelected != null) {
                    HallazgoDto hallazgo = buttonHallazgoSelected.getHallazgo();
                    if (hallazgo != null) {
                        hallazgo.setFracturaCerrada(isChecked);
                        saveHallazgo(hallazgo);
                    }
                }
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonHeridaArmaFuego);
        listLesiones.append(R.id.radioButtonHeridaArmaFuego, localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonHallazgoSelected != null) {
                    HallazgoDto hallazgo = buttonHallazgoSelected.getHallazgo();
                    if (hallazgo != null) {
                        hallazgo.setHeridaArmaDeFuego(isChecked);
                        saveHallazgo(hallazgo);
                    }
                }
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonHeridaArmaBlanca);
        listLesiones.append(R.id.radioButtonHeridaArmaBlanca, localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonHallazgoSelected != null) {
                    HallazgoDto hallazgo = buttonHallazgoSelected.getHallazgo();
                    if (hallazgo != null) {
                        hallazgo.setHeridaArmaBlanca(isChecked);
                        saveHallazgo(hallazgo);
                    }
                }
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonHemorragia);
        listLesiones.append(R.id.radioButtonHemorragia, localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonHallazgoSelected != null) {
                    HallazgoDto hallazgo = buttonHallazgoSelected.getHallazgo();
                    if (hallazgo != null) {
                        hallazgo.setHemorragia(isChecked);
                        saveHallazgo(hallazgo);
                    }
                }
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonLaceracion);
        listLesiones.append(R.id.radioButtonLaceracion, localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonHallazgoSelected != null) {
                    HallazgoDto hallazgo = buttonHallazgoSelected.getHallazgo();
                    if (hallazgo != null) {
                        hallazgo.setLaceracion(isChecked);
                        saveHallazgo(hallazgo);
                    }
                }
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonMordida);
        listLesiones.append(R.id.radioButtonMordida, localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonHallazgoSelected != null) {
                    HallazgoDto hallazgo = buttonHallazgoSelected.getHallazgo();
                    if (hallazgo != null) {
                        hallazgo.setMordida(isChecked);
                        saveHallazgo(hallazgo);
                    }
                }
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonPicadura);
        listLesiones.append(R.id.radioButtonPicadura, localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonHallazgoSelected != null) {
                    HallazgoDto hallazgo = buttonHallazgoSelected.getHallazgo();
                    if (hallazgo != null) {
                        hallazgo.setPicadura(isChecked);
                        saveHallazgo(hallazgo);
                    }
                }
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonPuncion);
        listLesiones.append(R.id.radioButtonPuncion, localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonHallazgoSelected != null) {
                    HallazgoDto hallazgo = buttonHallazgoSelected.getHallazgo();
                    if (hallazgo != null) {
                        hallazgo.setPuncion(isChecked);
                        saveHallazgo(hallazgo);
                    }
                }
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonTraumaCerrado);
        listLesiones.append(R.id.radioButtonTraumaCerrado, localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonHallazgoSelected != null) {
                    HallazgoDto hallazgo = buttonHallazgoSelected.getHallazgo();
                    if (hallazgo != null) {
                        hallazgo.setTraumaCerrado(isChecked);
                        saveHallazgo(hallazgo);
                    }
                }
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonTraumaPenetrante);
        listLesiones.append(R.id.radioButtonTraumaPenetrante, localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonHallazgoSelected != null) {
                    HallazgoDto hallazgo = buttonHallazgoSelected.getHallazgo();
                    if (hallazgo != null) {
                        hallazgo.setTraumaPenetrante(isChecked);
                        saveHallazgo(hallazgo);
                    }
                }
            }
        });
        localCheckBox = (CheckBox)view.findViewById(R.id.radioButtonHematoma);
        listLesiones.append(R.id.radioButtonHematoma, localCheckBox);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonHallazgoSelected != null) {
                    HallazgoDto hallazgo = buttonHallazgoSelected.getHallazgo();
                    if (hallazgo != null) {
                        hallazgo.setHematoma(isChecked);
                        saveHallazgo(hallazgo);
                    }
                }
            }
        });


        buttonAddHallazgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    createHallazgo();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        if(activity != null)
        {
            try {
                hallazgoService = new HallazgoService(activity.getHelper());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        updateEvaluacion(evaluacion);
        return view;
    }
    public void deleteCurrentHallazgo() throws SQLException {
        if(buttonHallazgoSelected != null) {
            HallazgoDto hallazgo = buttonHallazgoSelected.getHallazgo();
            ButtonHallazgo button = buttonHallazgoSparseArray.get(hallazgo.getId());
            LinearLayout linearLayoutLesiones = (LinearLayout) view.findViewById(R.id.linearLayoutLesiones);
            linearLayoutLesiones.removeView(button);
            button.setVisibility(View.GONE);
            DraggableImageButton imageButton = draggableImageButtons.get(hallazgo.getId());
            imageButton.setVisibility(View.GONE);
            touchImageView.getMarkers().remove(imageButton);
            hallazgoService.deleteHallazgoById(hallazgo.getId());
            evaluacion.setHallazgos(hallazgoService.getHallazgosByEvaluacion(evaluacion.getEvaluacionId()));
            activity.setEvaluacionSelected(evaluacion);
            draggableImageButtons.remove(hallazgo.getId());
        }
    }
    public void initHallazgos()
    {
        if(evaluacion != null && evaluacion.getEvaluacion() != null && hallazgoService != null)
        {
            try {
                List<HallazgoDto> hallazgos = hallazgoService.getHallazgosByEvaluacion(evaluacion.getEvaluacion().getId());
                evaluacion.setHallazgos(hallazgos);
                for(HallazgoDto hallazgo : hallazgos)
                {
                    createDraggableButton(hallazgo);
                    createButtonHallazgo(hallazgo);
                }
                if(buttonHallazgoSelected == null && hallazgos.size() > 0)
                {
                    setButtonHallazgoSelected(buttonHallazgoSparseArray.get(hallazgos.get(0).getId()));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void createHallazgo() throws SQLException {
        if(evaluacion != null)
        {
            HallazgoDto hallazgo = hallazgoService.createNew(evaluacion.getEvaluacion());
            hallazgo.setOrden(hallazgoService.getNextOrden(evaluacion.getEvaluacion().getId()));
            evaluacion.getHallazgos().add(hallazgo);
            evaluacionService.saveHallazgos(evaluacion);
            createDraggableButton(hallazgo);
            createButtonHallazgo(hallazgo);
       }
    }
    private void setupButton(DraggableImageButton view)
    {
        double X =view.getImageX();
        double Y = view.getImageY();
        touchImageView.fitImageToView();
        //gets the position in the touch of the image point
        PointF pointTouch = touchImageView.transformCoordBitmapToTouch((float) X, (float) Y);
        Integer offsetX = 35;
        Integer offsetY = 98;
        X = pointTouch.x;
        Y = pointTouch.y;
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
                .getLayoutParams();
        //used to get location on screen of imageView and marker
        int[] values = new int[2];
        touchImageView.getLocationOnScreen(values);
        int[] values1 = new int[2];
        view.getLayoutGroup().getLocationOnScreen(values1);
        layoutParams.leftMargin = (int)(X - view.getWidth()/2+values[0]-values1[0]) - offsetX;
        layoutParams.topMargin = (int) (Y-view.getHeight()+values[1]-values1[1]) - offsetY;
        layoutParams.rightMargin = -250;
        layoutParams.bottomMargin = -250;
        view.setLayoutParams(layoutParams);
        ViewGroup mRrootLayout = view.getLayoutGroup();
        mRrootLayout.invalidate();
    }

    public void initTableLesiones()
    {
        for(int i = 0; i < listLesiones.size(); i++)
        {
            CheckBox checkBox = listLesiones.valueAt(i);
            checkBox.setChecked(Boolean.FALSE);
        }
    }
    public void setButtonHallazgoSelected(ButtonHallazgo button)
    {
        if(button != null) {
            if (buttonHallazgoSelected != null) {
                buttonHallazgoSelected.setActivated(Boolean.FALSE);
                HallazgoDto hallazgo = buttonHallazgoSelected.getHallazgo();
                saveHallazgo(hallazgo);
            }
            buttonHallazgoSelected = button;
            setLesionesHallazgo(buttonHallazgoSelected.getHallazgo());
            buttonHallazgoSelected.setActivated(Boolean.TRUE);
        }
    }
    public void setLesionesHallazgo(HallazgoDto hallazgo)
    {
        if(hallazgo != null) {
            if(hallazgo.getAbrasion() != null) {
                listLesiones.get(R.id.radioButtonAbrasion).setChecked(hallazgo.getAbrasion());
            }
            else
            {
                listLesiones.get(R.id.radioButtonAbrasion).setChecked(Boolean.FALSE);
            }
            if(hallazgo.getAplastamiento() != null) {
                listLesiones.get(R.id.radioButtonAplastamiento).setChecked(hallazgo.getAplastamiento());
            }
            else
            {
                listLesiones.get(R.id.radioButtonAplastamiento).setChecked(Boolean.FALSE);
            }
            if(hallazgo.getAmputacion() != null) {
                listLesiones.get(R.id.radioButtonAmputacion).setChecked(hallazgo.getAmputacion());
            }
            else
            {
                listLesiones.get(R.id.radioButtonAmputacion).setChecked(Boolean.FALSE);
            }
            if(hallazgo.getAvulsion() != null) {
                listLesiones.get(R.id.radioButtonAvulsion).setChecked(hallazgo.getAvulsion());
            }
            else
            {
                listLesiones.get(R.id.radioButtonAvulsion).setChecked(Boolean.FALSE);
            }
            if(hallazgo.getContusion() != null)
            {
                listLesiones.get(R.id.radioButtonContusion).setChecked(hallazgo.getContusion());
            }
            else
            {
                listLesiones.get(R.id.radioButtonContusion).setChecked(Boolean.FALSE);
            }
            if(hallazgo.getDolor() != null)
            {
                listLesiones.get(R.id.radioButtonDolor).setChecked(hallazgo.getDolor());
            }
            else
            {
                listLesiones.get(R.id.radioButtonDolor).setChecked(Boolean.FALSE);
            }
            if(hallazgo.getEsguince() != null)
            {
                listLesiones.get(R.id.radioButtonEsquince).setChecked(hallazgo.getEsguince());
            }
            else
            {
                listLesiones.get(R.id.radioButtonEsquince).setChecked(Boolean.FALSE);
            }
            if(hallazgo.getFracturaAbierta() != null)
            {
                listLesiones.get(R.id.radioButtonFracturaAbierta).setChecked(hallazgo.getFracturaAbierta());
            }
            else
            {
                listLesiones.get(R.id.radioButtonFracturaAbierta).setChecked(Boolean.FALSE);
            }
            if(hallazgo.getQuemadura() != null)
            {
                listLesiones.get(R.id.radioButtonQuemadura).setChecked(hallazgo.getQuemadura());
            }
            else
            {
                listLesiones.get(R.id.radioButtonQuemadura).setChecked(Boolean.FALSE);
            }
            if(hallazgo.getHerida() != null)
            {
                listLesiones.get(R.id.radioButtonHerida).setChecked(hallazgo.getHerida());
            }
            else
            {
                listLesiones.get(R.id.radioButtonHerida).setChecked(Boolean.FALSE);
            }
            if(hallazgo.getFracturaCerrada() != null)
            {
                listLesiones.get(R.id.radioButtonFracturaCerrada).setChecked(hallazgo.getFracturaCerrada());
            }
            else
            {
                listLesiones.get(R.id.radioButtonFracturaCerrada).setChecked(Boolean.FALSE);
            }
            if(hallazgo.getHeridaArmaDeFuego() != null)
            {
                listLesiones.get(R.id.radioButtonHeridaArmaFuego).setChecked(hallazgo.getHeridaArmaDeFuego());
            }
            else
            {
                listLesiones.get(R.id.radioButtonHeridaArmaFuego).setChecked(Boolean.FALSE);
            }
            if(hallazgo.getHeridaArmaBlanca() != null)
            {
                listLesiones.get(R.id.radioButtonHeridaArmaBlanca).setChecked(hallazgo.getHeridaArmaBlanca());
            }
            else
            {
                listLesiones.get(R.id.radioButtonHeridaArmaBlanca).setChecked(Boolean.FALSE);
            }
            if(hallazgo.getHemorragia() != null)
            {
                listLesiones.get(R.id.radioButtonHemorragia).setChecked(hallazgo.getHemorragia());
            }
            else
            {
                listLesiones.get(R.id.radioButtonHemorragia).setChecked(Boolean.FALSE);
            }
            if(hallazgo.getLaceracion() != null)
            {
                listLesiones.get(R.id.radioButtonLaceracion).setChecked(hallazgo.getLaceracion());
            }
            else
            {
                listLesiones.get(R.id.radioButtonLaceracion).setChecked(Boolean.FALSE);
            }
            if(hallazgo.getMordida() != null)
            {
                listLesiones.get(R.id.radioButtonMordida).setChecked(hallazgo.getMordida());
            }
            else
            {
                listLesiones.get(R.id.radioButtonMordida).setChecked(Boolean.FALSE);
            }
            if(hallazgo.getPicadura() != null)
            {
                listLesiones.get(R.id.radioButtonPicadura).setChecked(hallazgo.getPicadura());
            }
            else
            {
                listLesiones.get(R.id.radioButtonPicadura).setChecked(Boolean.FALSE);
            }
            if(hallazgo.getPuncion() != null)
            {
                listLesiones.get(R.id.radioButtonPuncion).setChecked(hallazgo.getPuncion());
            }
            else
            {
                listLesiones.get(R.id.radioButtonPuncion).setChecked(Boolean.FALSE);
            }
            if(hallazgo.getTraumaCerrado() != null)
            {
                listLesiones.get(R.id.radioButtonTraumaCerrado).setChecked(hallazgo.getTraumaCerrado());
            }
            else
            {
                listLesiones.get(R.id.radioButtonTraumaCerrado).setChecked(Boolean.FALSE);
            }
            if(hallazgo.getTraumaPenetrante() != null)
            {
                listLesiones.get(R.id.radioButtonTraumaPenetrante).setChecked(hallazgo.getTraumaPenetrante());
            }
            else
            {
                listLesiones.get(R.id.radioButtonTraumaPenetrante).setChecked(Boolean.FALSE);
            }
            if(hallazgo.getHematoma() != null)
            {
                listLesiones.get(R.id.radioButtonHematoma).setChecked(hallazgo.getHematoma());
            }
            else
            {
                listLesiones.get(R.id.radioButtonHematoma).setChecked(Boolean.FALSE);
            }
        }

    }
    public void createButtonHallazgo(HallazgoDto hallazgo)
    {
        if(hallazgo != null && hallazgo.getId() != null) {
            LinearLayout linearLayoutLesiones = (LinearLayout) view.findViewById(R.id.linearLayoutLesiones);
            ButtonHallazgo button = new ButtonHallazgo(activity);
            button.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.button_tab_lesiones));
            button.setHallazgo(hallazgo);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(5, 0, 5, 0);
            button.setLayoutParams(params);
            linearLayoutLesiones.addView(button, linearLayoutLesiones.getChildCount() - 1);
            buttonHallazgoSparseArray.put(hallazgo.getId(), button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v instanceof ButtonHallazgo) {
                        setButtonHallazgoSelected((ButtonHallazgo)v);
                    }
                }
            });
        }
    }
    public void clear()
    {
        for(int i = 0; i < draggableImageButtons.size(); i++)
        {
            DraggableImageButton d = draggableImageButtons.valueAt(i);
            d.setVisibility(View.GONE);
        }
        draggableImageButtons.clear();
        RelativeLayout layout = (RelativeLayout) view.findViewById(R.id.evaluationPatient);
        List<View> views = new ArrayList<View>();
        for(int i = 0; i < layout.getChildCount(); i++)
        {
            View view = layout.getChildAt(i);
            if(view instanceof DraggableImageButton) {
                views.add(view);
            }

        }
        for(View view : views)
        {
            layout.removeView(view);
            view.setVisibility(View.GONE);

        }
        LinearLayout linearLayoutLesiones = (LinearLayout) view.findViewById(R.id.linearLayoutLesiones);
        views = new ArrayList<View>();
        for(int i = 0; i < layout.getChildCount(); i++)
        {
            View view = linearLayoutLesiones.getChildAt(i);
            if(view instanceof ButtonHallazgo) {
                views.add(view);
            }

        }
        for(View view : views)
        {
            linearLayoutLesiones.removeView(view);
            view.setVisibility(View.GONE);
        }
        if(touchImageView != null) {
            if(touchImageView.getMarkers() != null) {
                touchImageView.getMarkers().clear();
            }
        }
        buttonHallazgoSelected = null;
        if(buttonHallazgoSparseArray != null) {
            buttonHallazgoSparseArray.clear();
        }
    }
    public void createDraggableButton(HallazgoDto hallazgo) throws SQLException {

        if(view != null) {
            View viewButton = inflater.inflate(R.layout.draggable_button, null);
            ViewGroup mRrootLayout = (ViewGroup) viewButton.findViewById(R.id.root);
            DraggableImageButton draggableButton = (DraggableImageButton) mRrootLayout.findViewById(R.id.imageView);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int)(100*0.7), (int)(140*0.7));
            layoutParams.setMargins(0,0,0,0);
            draggableButton.setLayoutParams(layoutParams);
            draggableButton.setOnTouchListener(this);
            draggableButton.setLayout(mRrootLayout);
            touchImageView.addMarker(draggableButton);
            RelativeLayout layout = (RelativeLayout) view.findViewById(R.id.evaluationPatient);
            layout.addView(viewButton);
            draggableButton.setTouchImageView(touchImageView);
            if(hallazgo != null) {
                if(hallazgo.getOrden() != null) {
                    draggableButton.setText(String.valueOf(hallazgo.getOrden()));
                }
                if(hallazgo.getX() != null)
                {
                    draggableButton.setImageX(hallazgo.getX());
                }
                if(hallazgo.getY() != null)
                {
                    draggableButton.setImageY(hallazgo.getY());
                }
                draggableButton.setHallazgo(hallazgo);
                setupButton(draggableButton);
                draggableImageButtons.put(hallazgo.getId(), draggableButton);
            }
        }

    }
    public void setButtonSelectedFromDraggableButton(DraggableImageButton button)
    {
        HallazgoDto hallazgo = button.getHallazgo();
        if(hallazgo != null && hallazgo.getId() != null)
        {
            ButtonHallazgo buttonSelected = buttonHallazgoSparseArray.get(hallazgo.getId());
            if(buttonHallazgoSelected != null) {
                HallazgoDto current = buttonHallazgoSelected.getHallazgo();
                if (current != null && hallazgo.getId().equals(current.getId())) {
                    return;
                }
            }
            if(buttonSelected != null) {
                setButtonHallazgoSelected(buttonSelected);
            }
        }
    }
    public boolean onTouch(View view, MotionEvent event) {
        if(view instanceof DraggableImageButton)
        {
            DraggableImageButton draggableImageButton = (DraggableImageButton)view;
            setButtonSelectedFromDraggableButton(draggableImageButton);
            final int X = (int) event.getRawX();
            final int Y = (int) event.getRawY();
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    draggableImageButton.setxDelta(X - lParams.leftMargin);
                    draggableImageButton.setyDelta(Y - lParams.topMargin);
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    break;
                case MotionEvent.ACTION_MOVE:
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
                            .getLayoutParams();
                    layoutParams.leftMargin = X - draggableImageButton.getxDelta();
                    layoutParams.topMargin = Y - draggableImageButton.getyDelta();
                    layoutParams.rightMargin = -250;
                    layoutParams.bottomMargin = -250;
                    view.setLayoutParams(layoutParams);
                    break;

            }
            ((RelativeLayout)view.getParent()).invalidate();
            int[] values = new int[2];
            TouchImageView touchImageView = draggableImageButton.getTouchImageView();
            touchImageView.getLocationOnScreen(values);
            int[] values1 = new int[2];
            ((DraggableImageButton)view).getLayoutGroup().getLocationOnScreen(values1);
            PointF point = touchImageView.transformCoordTouchToBitmap(view.getX()+view.getWidth()/2-values[0]+values1[0] ,view.getY()+view.getHeight() -values[1]+values1[1], false);
            draggableImageButton.setImageX((double)point.x);
            draggableImageButton.setImageY((double)point.y);
            if(draggableImageButton.getHallazgo() != null)
            {
                saveHallazgo(draggableImageButton.getHallazgo());
            }
        }
        else
        {
            return false;
        }

        return true;
    }
    @Override
    public int getViewId() {
        return R.layout.fragment_evaluacion_e;
    }

    @Override
    public int getFragmentTagId() {
        return R.string.fragment_evaluacion_e_tag;
    }
    public static class AppSectionsPagerAdapter extends FragmentPagerAdapter {

        public AppSectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new LesionFragment();
        }
        @Override
        public String getPageTitle(int position)
        {
            return "Page title "+position;
        }
        @Override
        public int getCount() {
            return 2;
        }
    }
    public void onDestroyView () {
        super.onDestroyView();
        clear();
    }
    public void saveHallazgo(HallazgoDto hallazgo){

        new SaveHallazgosTask().execute(hallazgo);
    }
    public void saveHallazgos()
    {
        List<HallazgoDto> hallazgos = new ArrayList<HallazgoDto>();
        for(int i = 0; i < draggableImageButtons.size(); i++)
        {
            DraggableImageButton button = draggableImageButtons.valueAt(i);
            if(button.getHallazgo() != null)
            {
                try {
                    hallazgos.add(hallazgoService.save(button.getHallazgo()));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        evaluacion.setHallazgos(hallazgos);
        try {
            evaluacionService.saveHallazgos(evaluacion);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void updateEvaluacion(final EvaluacionDto evaluacion){
        clear();
        initHallazgos();
    }
    @Override
    public void savePreviousState()
    {
    }



    @Override
    public void updateButtonSelected() {

        ImageButton button = (ImageButton)view.findViewById(R.id.imageButtonEvaluacionE);
        button.setActivated(Boolean.TRUE);
    }
    private class SaveHallazgosTask extends AsyncTask<HallazgoDto, Void, Void>
    {

        @Override
        protected Void doInBackground(HallazgoDto... params) {
            try {
                hallazgoService.save(params[0]);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
