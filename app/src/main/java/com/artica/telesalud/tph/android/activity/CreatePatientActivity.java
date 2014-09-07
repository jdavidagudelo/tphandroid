package com.artica.telesalud.tph.android.activity;

import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;

import com.artica.telesalud.tph.android.R;
import com.artica.telesalud.tph.android.adapter.EvolucionArrayAdapter;
import com.artica.telesalud.tph.android.dao.DatabaseHelper;
import com.artica.telesalud.tph.android.dao.EvaluacionCompletaService;
import com.artica.telesalud.tph.android.dao.EvaluacionService;
import com.artica.telesalud.tph.android.dao.LesionadoService;
import com.artica.telesalud.tph.android.fragment.DummyTabContent;
import com.artica.telesalud.tph.android.lightweightmodel.EvaluacionCompletaDto;
import com.artica.telesalud.tph.android.lightweightmodel.EvaluacionDto;
import com.artica.telesalud.tph.android.lightweightmodel.EventDto;
import com.artica.telesalud.tph.android.lightweightmodel.LesionadoDto;
import com.artica.telesalud.tph.android.lightweightmodel.UserDto;
import com.artica.telesalud.tph.android.view.DraggableImageButton;
import com.artica.telesalud.tph.android.view.TouchImageView;

import java.sql.SQLException;

public class CreatePatientActivity extends BaseActivity implements  View.OnTouchListener{

    private CheckBox buttonSelected;
    private TabHost tabs;

    private UserDto userInSession;
    private EventDto event;
    private LesionadoDto lesionado;
    private EvaluacionDto evaluacionSelected;
    private DraggableImageButton mImageView;
    private ViewGroup mRrootLayout;
    private TouchImageView touchImageView;
    private int _xDelta;
    private int _yDelta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_patient);

        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null) {
            lesionado = bundle.getParcelable(LesionadoDto.class.getCanonicalName());
            if(lesionado != null)
            {
                evaluacionSelected = lesionado.getEvaluaciones().get(0);
            }
            userInSession = bundle.getParcelable(UserDto.class.getCanonicalName());
            event = bundle.getParcelable(EventDto.class.getCanonicalName());
            evaluacionSelected = bundle.getParcelable(EvaluacionDto.class.getCanonicalName());
        }
        tabs = (TabHost) findViewById(R.id.tabHost);
        tabs.setup();
        TabHost.TabSpec specCrear = tabs.newTabSpec("Crear Paciente");
        specCrear.setContent(new DummyTabContent(this));
        specCrear.setIndicator("Crear Paciente");
        tabs.addTab(specCrear);
        mRrootLayout = (ViewGroup) findViewById(R.id.root);
        mImageView = (DraggableImageButton) mRrootLayout.findViewById(R.id.imageView);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(100, 100);
        mImageView.setLayoutParams(layoutParams);
        mImageView.setOnTouchListener(this);
        mImageView.setLayout(mRrootLayout);
        touchImageView = (TouchImageView)findViewById(R.id.imageViewBody);
        touchImageView.setMaxZoom(5.0f);
        touchImageView.setMinZoom(1.0f);
        touchImageView.addMarker(mImageView);
        ImageButton button = (ImageButton) findViewById(R.id.imageButtonEvaluacionA);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CreatePatientActivity.this, EvaluacionPatientActivity.class);
                intent.putExtra(EventDto.class.getCanonicalName(), (Parcelable)CreatePatientActivity.this.getEvent());
                intent.putExtra(UserDto.class.getCanonicalName(), (Parcelable)CreatePatientActivity.this.getUserInSession());
                intent.putExtra(LesionadoDto.class.getCanonicalName(),  (Parcelable)CreatePatientActivity.this.getLesionado());
                intent.putExtra(EvaluacionDto.class.getCanonicalName(),  (Parcelable)CreatePatientActivity.this.getEvaluacionSelected());
                intent.putExtra(getString(R.string.current_evaluation_code), getString(R.string.evaluation_a));
                startActivity(intent);
            }
        });
        button = (ImageButton) findViewById(R.id.imageButtonEvaluacionB);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CreatePatientActivity.this, EvaluacionPatientActivity.class);
                intent.putExtra(EventDto.class.getCanonicalName(), (Parcelable)CreatePatientActivity.this.getEvent());
                intent.putExtra(UserDto.class.getCanonicalName(), (Parcelable)CreatePatientActivity.this.getUserInSession());
                intent.putExtra(LesionadoDto.class.getCanonicalName(),  (Parcelable)CreatePatientActivity.this.getLesionado());
                intent.putExtra(EvaluacionDto.class.getCanonicalName(),  (Parcelable)CreatePatientActivity.this.getEvaluacionSelected());
                intent.putExtra(getString(R.string.current_evaluation_code), getString(R.string.evaluation_b));
                startActivity(intent);
            }
        });
        button = (ImageButton) findViewById(R.id.imageButtonEvaluacionC);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CreatePatientActivity.this, EvaluacionPatientActivity.class);
                intent.putExtra(EventDto.class.getCanonicalName(), (Parcelable)CreatePatientActivity.this.getEvent());
                intent.putExtra(UserDto.class.getCanonicalName(), (Parcelable)CreatePatientActivity.this.getUserInSession());
                intent.putExtra(LesionadoDto.class.getCanonicalName(),  (Parcelable)CreatePatientActivity.this.getLesionado());
                intent.putExtra(EvaluacionDto.class.getCanonicalName(),  (Parcelable)CreatePatientActivity.this.getEvaluacionSelected());
                intent.putExtra(getString(R.string.current_evaluation_code), getString(R.string.evaluation_c));
                startActivity(intent);
            }
        });
        button = (ImageButton) findViewById(R.id.imageButtonEvaluacionD);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CreatePatientActivity.this, EvaluacionPatientActivity.class);
                intent.putExtra(EventDto.class.getCanonicalName(), (Parcelable)CreatePatientActivity.this.getEvent());
                intent.putExtra(UserDto.class.getCanonicalName(), (Parcelable)CreatePatientActivity.this.getUserInSession());
                intent.putExtra(LesionadoDto.class.getCanonicalName(),  (Parcelable)CreatePatientActivity.this.getLesionado());
                intent.putExtra(EvaluacionDto.class.getCanonicalName(),  (Parcelable)CreatePatientActivity.this.getEvaluacionSelected());
                intent.putExtra(getString(R.string.current_evaluation_code), getString(R.string.evaluation_d));
                startActivity(intent);
            }
        });
        button = (ImageButton) findViewById(R.id.imageButtonEvaluacionE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CreatePatientActivity.this, EvaluacionPatientActivity.class);
                intent.putExtra(EventDto.class.getCanonicalName(), (Parcelable)CreatePatientActivity.this.getEvent());
                intent.putExtra(UserDto.class.getCanonicalName(), (Parcelable)CreatePatientActivity.this.getUserInSession());
                intent.putExtra(LesionadoDto.class.getCanonicalName(),  (Parcelable)CreatePatientActivity.this.getLesionado());
                intent.putExtra(EvaluacionDto.class.getCanonicalName(),  (Parcelable)CreatePatientActivity.this.getEvaluacionSelected());
                intent.putExtra(getString(R.string.current_evaluation_code), getString(R.string.evaluation_e));
                startActivity(intent);
            }
        });
        button = (ImageButton) findViewById(R.id.imageButtonTrauma);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CreatePatientActivity.this, EvaluacionPatientActivity.class);
                intent.putExtra(EventDto.class.getCanonicalName(), (Parcelable)CreatePatientActivity.this.getEvent());
                intent.putExtra(UserDto.class.getCanonicalName(), (Parcelable)CreatePatientActivity.this.getUserInSession());
                intent.putExtra(LesionadoDto.class.getCanonicalName(),  (Parcelable)CreatePatientActivity.this.getLesionado());
                intent.putExtra(EvaluacionDto.class.getCanonicalName(),  (Parcelable)CreatePatientActivity.this.getEvaluacionSelected());
                intent.putExtra(getString(R.string.current_evaluation_code), getString(R.string.evaluation_trauma));
                startActivity(intent);
            }
        });
        button = (ImageButton) findViewById(R.id.imageButtonEmergenciaMedica);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CreatePatientActivity.this, EvaluacionPatientActivity.class);
                intent.putExtra(EventDto.class.getCanonicalName(), (Parcelable)CreatePatientActivity.this.getEvent());
                intent.putExtra(UserDto.class.getCanonicalName(), (Parcelable)CreatePatientActivity.this.getUserInSession());
                intent.putExtra(LesionadoDto.class.getCanonicalName(),  (Parcelable)CreatePatientActivity.this.getLesionado());
                intent.putExtra(EvaluacionDto.class.getCanonicalName(),  (Parcelable)CreatePatientActivity.this.getEvaluacionSelected());
                intent.putExtra(getString(R.string.current_evaluation_code), getString(R.string.evaluation_emergencia_medica));
                startActivity(intent);
            }
        });
        button = (ImageButton) findViewById(R.id.imageButtonProcedimientos);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CreatePatientActivity.this, EvaluacionPatientActivity.class);
                intent.putExtra(EventDto.class.getCanonicalName(), (Parcelable)CreatePatientActivity.this.getEvent());
                intent.putExtra(UserDto.class.getCanonicalName(), (Parcelable)CreatePatientActivity.this.getUserInSession());
                intent.putExtra(LesionadoDto.class.getCanonicalName(),  (Parcelable)CreatePatientActivity.this.getLesionado());
                intent.putExtra(EvaluacionDto.class.getCanonicalName(),  (Parcelable)CreatePatientActivity.this.getEvaluacionSelected());
                intent.putExtra(getString(R.string.current_evaluation_code), getString(R.string.evaluation_procedimientos));
                startActivity(intent);
            }
        });
        button = (ImageButton)findViewById(R.id.imageButtonAddEvolucion);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EvaluacionCompletaService evaluacionService = null;
                try {
                    evaluacionService = new EvaluacionCompletaService(getHelper());
                    EvaluacionCompletaDto evaluacion = evaluacionService.createNew(lesionado.getEncuentro(), userInSession.getUserId());
                    EvaluacionDto evaluacionDto = new EvaluacionDto();
                    evaluacionDto.setEvaluacion(evaluacion);
                    EvaluacionService evaluacionDtoService = new EvaluacionService(getHelper());
                    evaluacionDtoService.save(evaluacionDto);
                    LesionadoService lesionadoService = new LesionadoService(getHelper());
                    if(lesionado.getId() != null) {
                        lesionado = lesionadoService.getLesionadoById(lesionado.getId());
                    }
                    else
                    {
                        lesionado = lesionadoService.getLesionado(lesionado.getLesionadoId());
                    }
                    setLesionado(lesionado);
                    ListView listViewEvaluaciones = (ListView)findViewById(R.id.listViewEvoluciones);
                    EvolucionArrayAdapter adapter = new EvolucionArrayAdapter(CreatePatientActivity.this, lesionado.getEvaluaciones());
                    listViewEvaluaciones.setAdapter(adapter);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        if(lesionado != null)
        {
            ListView listViewEvaluaciones = (ListView)findViewById(R.id.listViewEvoluciones);
            EvolucionArrayAdapter adapter = new EvolucionArrayAdapter(this, lesionado.getEvaluaciones());
            listViewEvaluaciones.setAdapter(adapter);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.create_patient, menu);
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
        if(id == R.id.action_save_patient)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public DatabaseHelper getHelper() {
        return null;
    }

    @Override
    public void showSuccessMessage(String message) {

    }

    @Override
    public void showErrorMessage(String message) {

    }


    public boolean onTouch(View view, MotionEvent event) {
        if(view instanceof DraggableImageButton)
        {
        final int X = (int) event.getRawX();
        final int Y = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                _xDelta = X - lParams.leftMargin;
                _yDelta = Y - lParams.topMargin;
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
                layoutParams.leftMargin = X - _xDelta;
                layoutParams.topMargin = Y - _yDelta;
                layoutParams.rightMargin = -250;
                layoutParams.bottomMargin = -250;
                view.setLayoutParams(layoutParams);
                break;

        }
        mRrootLayout.invalidate();

            DraggableImageButton draggableImageButton = (DraggableImageButton)view;
            int[] values = new int[2];
            touchImageView.getLocationOnScreen(values);
             int[] values1 = new int[2];
            ((DraggableImageButton)view).getLayoutGroup().getLocationOnScreen(values1);
            PointF point = touchImageView.transformCoordTouchToBitmap(view.getX()+view.getWidth()/2-values[0]+values1[0] ,view.getY()+view.getHeight() -values[1]+values1[1], false);
            draggableImageButton.setImageX((double)point.x);
            draggableImageButton.setImageY((double)point.y);
        }
        else
        {
            return false;
        }

        return true;
    }

    public UserDto getUserInSession() {
        return userInSession;
    }

    public void setUserInSession(UserDto userInSession) {
        this.userInSession = userInSession;
    }

    public EventDto getEvent() {
        return event;
    }

    public void setEvent(EventDto event) {
        this.event = event;
    }

    public LesionadoDto getLesionado() {
        return lesionado;
    }

    public void setLesionado(LesionadoDto lesionado) {
        this.lesionado = lesionado;
    }

    public EvaluacionDto getEvaluacionSelected() {
        return evaluacionSelected;
    }

    public void setEvaluacionSelected(EvaluacionDto evaluacionSelected) {
        this.evaluacionSelected = evaluacionSelected;
    }
}
