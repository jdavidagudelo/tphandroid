package com.artica.telesalud.tph.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import com.artica.telesalud.tph.android.R;

import java.util.List;

/**
 * Created by interoperabilidad on 15/08/14.
 */
public class RadioGroupArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private static class ViewHolder
    {
        public CheckBox checkBox;
    }
    public RadioGroupArrayAdapter(Context context, List<String> objects) {
        super(context, R.layout.radio_button_item, objects);

        this.context = context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.radio_button_item, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.checkBox = (CheckBox)convertView.findViewById(R.id.checkBoxItem);
            convertView.setTag(viewHolder);
        }
        ViewHolder viewHolder = (ViewHolder)convertView.getTag();
        CheckBox checkBox = viewHolder.checkBox;
        checkBox.setText(getItem(position));
        return convertView;
    }
}
