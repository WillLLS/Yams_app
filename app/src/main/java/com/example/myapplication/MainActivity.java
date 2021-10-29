package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.translation.UiTranslationManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private YamsDices m_Dices;
    private SheetPlayer m_Player;
    private String m_choice;

    private RecyclerView m_RecycleView;
    private List<figure> m_ListFigure;
    private myAdapter m_Adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m_Dices = new YamsDices();
        m_Player = new SheetPlayer("Player");
        m_choice="";

        m_RecycleView = (RecyclerView)findViewById(R.id.recyclerView);

        m_ListFigure = new ArrayList<>();
        m_ListFigure.add(new figure("Sum of 1","-"));
        m_ListFigure.add(new figure("Sum of 2","-"));
        m_ListFigure.add(new figure("Sum of 3","-"));
        m_ListFigure.add(new figure("Sum of 4","-"));
        m_ListFigure.add(new figure("Sum of 5","-"));
        m_ListFigure.add(new figure("Three of a Kind","-"));
        m_ListFigure.add(new figure("Full House","-"));
        m_ListFigure.add(new figure("Four of a Kind","-"));
        m_ListFigure.add(new figure("Small Straight","-"));
        m_ListFigure.add(new figure("Large Straight","-"));
        m_ListFigure.add(new figure("Yahtzee","-"));
        m_ListFigure.add(new figure("Chance","-"));


        m_Adapter = new myAdapter(m_ListFigure);
        m_RecycleView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        m_RecycleView.setAdapter(m_Adapter);

        ((Button)findViewById(R.id.rollSelected)).setEnabled(false);

        ((ToggleButton) findViewById(R.id.dice1)).toggle();
        ((ToggleButton) findViewById(R.id.dice2)).toggle();
        ((ToggleButton) findViewById(R.id.dice3)).toggle();
        ((ToggleButton) findViewById(R.id.dice4)).toggle();
        ((ToggleButton) findViewById(R.id.dice5)).toggle();

    }

    /**
     * fonction permettant de gérer les dés
     * @param v ToggleButton (dés)
     */
    public void diceSelected(View v){
        ToggleButton button = (ToggleButton)v;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                boolean isChecked = button.isChecked();
                if(isChecked){
                    button.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);

                    switch(button.getId()){
                        case R.id.dice1:
                            m_choice += "1";
                            break;
                        case R.id.dice2:
                            m_choice += "2";
                            break;
                        case R.id.dice3:
                            m_choice += "3";
                            break;
                        case R.id.dice4:
                            m_choice += "4";
                            break;
                        case R.id.dice5:
                            m_choice += "5";
                            break;
                    }
                }
                else{
                    button.setTextSize(TypedValue.COMPLEX_UNIT_SP,34);

                    switch(button.getId()){
                        case R.id.dice1:
                            m_choice = m_choice.replace("1","");
                            break;
                        case R.id.dice2:
                            m_choice = m_choice.replace("2","");
                            break;
                        case R.id.dice3:
                            m_choice = m_choice.replace("3","");
                            break;
                        case R.id.dice4:
                            m_choice = m_choice.replace("4","");
                            break;
                        case R.id.dice5:
                            m_choice = m_choice.replace("5","");
                            break;
                    }
                }
            }
        });
    }

    /**
     * fonction permettant de simuler un lancer de dés préalablement séléctionnés.
     * @param v Boutton rollSelected
     */
    public void rollSelected(View v){
        m_Dices.rollYams(m_choice);
        setTextDices();
    }

    /**
     * Fonction permettant de mettre à jour le texte des dés
     */
    public void setTextDices(){
        ToggleButton dice1 = ((ToggleButton)findViewById(R.id.dice1));
        ToggleButton dice2 = ((ToggleButton)findViewById(R.id.dice2));
        ToggleButton dice3 = ((ToggleButton)findViewById(R.id.dice3));
        ToggleButton dice4 = ((ToggleButton)findViewById(R.id.dice4));
        ToggleButton dice5 = ((ToggleButton)findViewById(R.id.dice5));


        dice1.setText(Integer.toString(m_Dices.dice5[0].diceValue));
        dice1.setTextOn(Integer.toString(m_Dices.dice5[0].diceValue));
        dice1.setTextOff(Integer.toString(m_Dices.dice5[0].diceValue));

        dice2.setText(Integer.toString(m_Dices.dice5[1].diceValue));
        dice2.setTextOn(Integer.toString(m_Dices.dice5[1].diceValue));
        dice2.setTextOff(Integer.toString(m_Dices.dice5[1].diceValue));

        dice3.setText(Integer.toString(m_Dices.dice5[2].diceValue));
        dice3.setTextOn(Integer.toString(m_Dices.dice5[2].diceValue));
        dice3.setTextOff(Integer.toString(m_Dices.dice5[2].diceValue));

        dice4.setText(Integer.toString(m_Dices.dice5[3].diceValue));
        dice4.setTextOn(Integer.toString(m_Dices.dice5[3].diceValue));
        dice4.setTextOff(Integer.toString(m_Dices.dice5[3].diceValue));

        dice5.setText(Integer.toString(m_Dices.dice5[4].diceValue));
        dice5.setTextOn(Integer.toString(m_Dices.dice5[4].diceValue));
        dice5.setTextOff(Integer.toString(m_Dices.dice5[4].diceValue));

    }

    /**
     * Fonction permettant de lancer tous les dés
     * @param v Button rollAll
     */
    public void rollAll(View v){
        ((Button)findViewById(R.id.rollSelected)).setEnabled(true);

        m_Dices.rollYams("12345");
        setTextDices();
    }
}