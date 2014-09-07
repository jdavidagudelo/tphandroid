package com.artica.telesalud.tph.android.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.StackView;

import com.artica.telesalud.tph.android.R;

import java.util.ArrayList;

public class SampleActivity extends Activity {

    Button next;
    Button previous;
    StackView sv;
    @SuppressLint("NewApi")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        StackView stk = (StackView)this.findViewById(R.id.stackView1);
        ArrayList<StackItem> items = new ArrayList<StackItem>();
        items.add(new StackItem("", getResources().getDrawable(R.drawable.body)));
        items.add(new StackItem("", getResources().getDrawable(R.drawable.body)));
        items.add(new StackItem("", getResources().getDrawable(R.drawable.body)));
        items.add(new StackItem("", getResources().getDrawable(R.drawable.body)));
        items.add(new StackItem("", getResources().getDrawable(R.drawable.body)));
        StackAdapter adapt = new StackAdapter(this, R.layout.stack_item, items);
        stk.setAdapter(adapt);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sample, menu);
        return true;
    }
    public class StackItem {
        public String itemText;
        public Drawable itemPhoto;
        public StackItem(String text,Drawable photo)
        {
            this.itemPhoto = photo;
            this.itemText = text;
        }
    }
    public class StackAdapter extends ArrayAdapter<StackItem> {
        private ArrayList<StackItem> items;
        private Context ctx;
        public StackAdapter(Context context, int textViewResourceId,
                            ArrayList<StackItem> objects) {
            super(context, textViewResourceId, objects);
            this.items = objects;
            this.ctx = context;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            LayoutInflater vi = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (v == null) {
                v = vi.inflate(R.layout.stack_item, null);
            }
            StackItem m = items.get(position);

            if (m != null) {
                ImageView img = (ImageView)v.findViewById(R.id.imageView1);
                img.setImageDrawable(m.itemPhoto);

            }
            switch (position)
            {
                case 0:
                    return vi.inflate(R.layout.radio_group_triage_red, null);
                case 1:
                    return vi.inflate(R.layout.radio_group_triage_yellow, null);
                case 2:
                    return vi.inflate(R.layout.radio_group_triage_black, null);
                case 3:
                    return vi.inflate(R.layout.radio_group_triage_green, null);
                case 4:
                    return vi.inflate(R.layout.radio_group_triage_white, null);
            }
            return v;
        }
    }
}
