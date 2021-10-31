package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;

public class MyAdapter extends ArrayAdapter<figure> {

    private Context mContext;
    private int mResource;

    public MyAdapter(@NonNull Context context, int resource, @NonNull List<figure> objects) {
        super(context, resource, objects);

        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);

        TextView figureView = convertView.findViewById(R.id.figure);
        TextView scoreView = convertView.findViewById(R.id.score);

        figureView.setText(getItem(position).getName());
        scoreView.setText(getItem(position).getScore());


        return convertView;
    }
}
