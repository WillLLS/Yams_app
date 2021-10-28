package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    YamsDices m_Dices;
    SheetPlayer m_Player;
    boolean m_choice[]={false,false,false,false,false};
    String choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m_Dices = new YamsDices();
        m_Player = new SheetPlayer("Player");
        choice="";

    }

    public void setChoice(){
        for(int i=0;i<5;i++){
            if(m_choice[i]==true){
                choice= choice + Integer.toString(i+1);
            }
        }
    }

    public void diceCheck(Integer id){
        if(id==R.id.dice1){
            m_choice[0]=true;
        }
        if(id==R.id.dice2){
            m_choice[1]=true;
        }
        if(id==R.id.dice3){
            m_choice[2]=true;
        }
        if(id==R.id.dice4){
            m_choice[3]=true;
        }
        if(id==R.id.dice5){
            m_choice[4]=true;
        }
    }

    public void diceUncheck(Integer id){
        if(id==R.id.dice1){
            m_choice[0]=false;
        }
        if(id==R.id.dice2){
            m_choice[1]=false;
        }
        if(id==R.id.dice3){
            m_choice[2]=false;
        }
        if(id==R.id.dice4){
            m_choice[3]=false;
        }
        if(id==R.id.dice5){
            m_choice[4]=false;
        }
    }

    public void diceSelected(View v){
        ToggleButton toggle = (ToggleButton) v;
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    toggle.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
                    diceCheck(v.getId());
                } else {
                    toggle.setTextSize(TypedValue.COMPLEX_UNIT_SP,34);
                    diceUncheck(v.getId());
                }
            }
        });
    }

    public void rollSelected(View v){
        setChoice();
        m_Dices.rollYams(choice);
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
        m_Dices.rollYams("12345");
        setTextDices();
    }


}