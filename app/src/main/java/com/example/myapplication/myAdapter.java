package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myAdapter.myViewHolder> {

    public class myViewHolder extends RecyclerView.ViewHolder{

        private TextView m_figure;
        private TextView m_clicked;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            m_figure = (TextView)itemView.findViewById(R.id.figure);
            m_clicked = (TextView)itemView.findViewById(R.id.clicked);
        }

        void display(figure figure){
            m_figure.setText(figure.getName());
            m_clicked.setText(figure.getClicked());
        }
    }

    List<figure> m_figure;

    public myAdapter(List<figure> figure) {
        this.m_figure = figure;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cell,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.display(m_figure.get(position));
    }

    @Override
    public int getItemCount() {
        return m_figure.size();
    }

}
