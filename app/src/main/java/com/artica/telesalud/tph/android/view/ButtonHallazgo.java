package com.artica.telesalud.tph.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import com.artica.telesalud.tph.android.lightweightmodel.HallazgoDto;

/**
 * Created by interoperabilidad on 22/08/14.
 */
public class ButtonHallazgo extends Button {
    private HallazgoDto hallazgo;
    public ButtonHallazgo(Context context) {
        super(context);
    }

    public ButtonHallazgo(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ButtonHallazgo(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public HallazgoDto getHallazgo() {
        return hallazgo;
    }

    public void setHallazgo(HallazgoDto hallazgo) {
        this.hallazgo = hallazgo;
        if(hallazgo != null) {
            setText(String.valueOf(hallazgo.getOrden()));
        }
    }
}
