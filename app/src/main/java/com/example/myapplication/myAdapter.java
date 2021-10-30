package com.example.myapplication;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myAdapter.myViewHolder> {

    public class myViewHolder extends RecyclerView.ViewHolder{

        private TextView m_TVfigure;
        private TextView m_TVclicked;
        private Integer m_IndexFigure;

        private View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_IndexFigure = getLayoutPosition();


                //m_figure.get(m_Position).setClicked();
                if(m_TVclicked.getText()=="CLICKED"){
                    m_TVclicked.setText(Integer.toString(m_IndexFigure));
                }
                else{
                    m_TVclicked.setText("CLICKED");
                }

                Log.d("position",m_IndexFigure.toString());

            }
        };

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(onClickListener);

            m_TVfigure = (TextView)itemView.findViewById(R.id.figure);
            m_TVclicked = (TextView)itemView.findViewById(R.id.clicked);
        }

        void display(figure figure){
            m_TVfigure.setText(figure.getName());
            m_TVclicked.setText(figure.getClicked());
        }

    }

    List<figure> m_figure;
    int indexfigure;

    public myAdapter(List<figure> figure) {
        this.m_figure = figure;
    }

    public void setAdapter(List<figure> figure){
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
        //Log.d("position",m_figure.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return m_figure.size();
    }

}
