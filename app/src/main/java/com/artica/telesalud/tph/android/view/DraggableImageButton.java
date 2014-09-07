package com.artica.telesalud.tph.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;

import com.artica.telesalud.tph.android.lightweightmodel.HallazgoDto;

/**
 * Created by interoperabilidad on 25/07/14.
 */
public class DraggableImageButton extends TextView{

    private ViewGroup layout=null;
    private Double imageX = 0.0;
    private Double imageY = 0.0;
    private Integer xDelta;
    private Integer yDelta;
    private HallazgoDto hallazgo;
    private TouchImageView touchImageView;
    public HallazgoDto getHallazgo() {
        return hallazgo;
    }

    public void setHallazgo(HallazgoDto hallazgo) {
        this.hallazgo = hallazgo;
    }

    public DraggableImageButton(Context context) {
        super(context);
    }

    public DraggableImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DraggableImageButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    public TouchImageView getTouchImageView() {
        return touchImageView;
    }

    public void setTouchImageView(TouchImageView touchImageView) {
        this.touchImageView = touchImageView;
    }

    public Integer getxDelta() {
        return xDelta;
    }

    public void setxDelta(Integer xDelta) {
        this.xDelta = xDelta;
    }

    public Integer getyDelta() {
        return yDelta;
    }

    public void setyDelta(Integer yDelta) {
        this.yDelta = yDelta;
    }

    public Double getImageX() {
        return imageX;
    }

    public void setImageX(Double imageX) {
        this.imageX = imageX;
        if(hallazgo != null)
        {
            hallazgo.setX(imageX);
        }
    }

    public Double getImageY() {
        return imageY;
    }

    public void setImageY(Double imageY) {
        this.imageY = imageY;
        if(hallazgo != null)
        {
            hallazgo.setY(imageY);
        }
    }

    public ViewGroup getLayoutGroup() {
        return layout;
    }

    public void setLayout(ViewGroup layout) {
        this.layout = layout;
    }








}
