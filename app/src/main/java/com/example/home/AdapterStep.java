package com.example.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AdapterStep extends ArrayAdapter<com.example.home.ModelStep> {
    private Context context;
    int resource;

    public AdapterStep(Context context, int resource, ArrayList<com.example.home.ModelStep> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        String steps=getItem(position).getSteps();
        String type =getItem(position).getType();
        String date= getItem(position).getDate();


        com.example.home.ModelStep modelStep = new com.example.home.ModelStep(steps,type,date);
        LayoutInflater inflater =LayoutInflater.from(context);
        convertView = inflater.inflate(resource,parent,false);

        TextView tvSteps=convertView.findViewById(R.id.Retrieve_steps);
        TextView tvType=convertView.findViewById(R.id.Retrieve_type);
        TextView tvDate=convertView.findViewById(R.id.Retrieve_date);

        /*  tvSteps.setText(steps);
            tvType.setText(type);
            tvDate.setText(date);
*/
        return convertView;
    }
}
