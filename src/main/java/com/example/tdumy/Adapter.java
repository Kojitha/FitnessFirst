package com.example.tdumy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter<Model> {

    private Context context;
    int resource;

    public Adapter(Context context,int resource, ArrayList<Model> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String water = getItem(position).getWater();
        String wakeUp = getItem(position).getWakeup();
        String goToBed = getItem(position).getGotup();

        Model model = new Model(water, wakeUp, goToBed);
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);

        TextView tvWater = convertView.findViewById(R.id.water);
        TextView tvWakeUp = convertView.findViewById(R.id.wakeup);
        TextView tvGoToBed = convertView.findViewById(R.id.gotobed);

        tvWater.setText(water);
        tvWakeUp.setText(wakeUp);
        tvGoToBed.setText(goToBed);

        return convertView;
    }


}
