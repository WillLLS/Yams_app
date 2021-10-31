package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.translation.UiTranslationManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private boolean start;
    private int set;

    YamsDices m_Dices;
    SheetPlayer m_Player;
    String m_choice;

    int m_memoryIndexFigure;
    int m_indexFigure;

    Button rollAll;
    Button rollSelected;

    ArrayList<figure> m_ListFigure;
    MyAdapter figureAdapter;
    ListView m_listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start=true;
        set=0;

        m_memoryIndexFigure = -1;

        rollAll = (Button)findViewById(R.id.rollAll);
        rollSelected = (Button)findViewById(R.id.rollSelected);

        m_Dices = new YamsDices();
        m_Player = new SheetPlayer("Player");
        m_choice="";

        m_ListFigure = new ArrayList<>();
        m_ListFigure.add(new figure("Sum of 1","-"));
        m_ListFigure.add(new figure("Sum of 2","-"));
        m_ListFigure.add(new figure("Sum of 3","-"));
        m_ListFigure.add(new figure("Sum of 4","-"));
        m_ListFigure.add(new figure("Sum of 5","-"));
        m_ListFigure.add(new figure("Sum of 6","-"));
        m_ListFigure.add(new figure("Three of a King","-"));
        m_ListFigure.add(new figure("Full House","-"));
        m_ListFigure.add(new figure("Four of a Kind","-"));
        m_ListFigure.add(new figure("Small Straight","-"));
        m_ListFigure.add(new figure("Large Straight","-"));
        m_ListFigure.add(new figure("Yahtzee","-"));
        m_ListFigure.add(new figure("Chance","-"));

        m_listView = (ListView)findViewById(R.id.ListView);

        figureAdapter = new MyAdapter(this, R.layout.item, m_ListFigure);

        m_listView.setAdapter(figureAdapter);


        m_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if(m_memoryIndexFigure==-1){
                     m_ListFigure.get(i).setScore("Clicked");
                     m_indexFigure=i;
                     m_memoryIndexFigure = i;
                }
                else{
                    if(m_Player.getAvailable(i)==true){

                        if(m_Player.getAvailable(m_memoryIndexFigure)==true){
                            m_ListFigure.get(m_memoryIndexFigure).setScore("-");
                        }
                        else{
                            m_ListFigure.get(m_memoryIndexFigure).setScore(String.valueOf(m_Player.getScore(m_memoryIndexFigure)));
                        }
                        m_ListFigure.get(i).setScore("Clicked");
                        m_indexFigure=i;
                        m_memoryIndexFigure = i;
                    }
                    else{
                        m_ListFigure.get(i).setScore("Clicked - " + String.valueOf(m_Player.getScore(i)));
                        if(m_Player.getAvailable(m_memoryIndexFigure)==true){
                            m_ListFigure.get(m_memoryIndexFigure).setScore("-");
                        }
                        else{
                            m_ListFigure.get(m_memoryIndexFigure).setScore(String.valueOf(m_Player.getScore(m_memoryIndexFigure)));
                        }
                        m_memoryIndexFigure = i;
                    }
                }
                figureAdapter.notifyDataSetChanged();

                Log.d("Item Clicked ", String.valueOf(i));
                Log.d("IndexSave",String.valueOf(m_indexFigure));
            }
        });

        rollSelected.setEnabled(false);
        rollSelected.setTextColor(Color.BLACK);

        ((ToggleButton) findViewById(R.id.dice1)).toggle();
        ((ToggleButton) findViewById(R.id.dice2)).toggle();
        ((ToggleButton) findViewById(R.id.dice3)).toggle();
        ((ToggleButton) findViewById(R.id.dice4)).toggle();
        ((ToggleButton) findViewById(R.id.dice5)).toggle();


    }

    /**
     * Gestion affichage bonus
     */
    public void Bonus(){
        if(m_Player.getBonus()){
            ((TextView)findViewById(R.id.Bonus)).setText("35");
        }
    }

    /**
     * Fonction permettant de gérer le déroulement de la partie
     */
    public void nextRound(){
        if(set<12){
            m_Player.newRound();
            ((TextView)findViewById(R.id.numRound)).setText(String.valueOf(m_Player.getRound()));

            if(m_Player.getRound()==3){

                int score = m_Dices.getScore(m_indexFigure); // Récupération du score pour la figure choisie

                m_Player.updateScoring(m_indexFigure,score); // MAJ Score
                ((TextView)findViewById(R.id.TVScore)).setText(String.valueOf(m_Player.getTotalScore())); // Ecriture du score

                m_ListFigure.get(m_indexFigure).setScore(String.valueOf(score)); // Actualisation item figure
                figureAdapter.notifyDataSetChanged(); // Information pour l'adapter d'un changement

                set += 1;
                m_Player.setRound(0);

                rollSelected.setEnabled(false);
                rollSelected.setTextColor(Color.BLACK);
            }

            Bonus();

        }

        else{       // Fin de partie
            m_Player.totalScore();

            ((TextView)findViewById(R.id.info)).setText("Game Over - Score : "+String.valueOf(m_Player.getTotalScore()));

            rollAll.setText("RESTART");
            rollSelected.setEnabled(false);
            rollSelected.setTextColor(Color.BLACK);

        }


        Log.d("Round",String.valueOf(m_Player.getRound()));
        Log.d("Set",String.valueOf(set));
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

        nextRound();
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
     * Fonction permettant de rejouer une partie
     */
    public void reset(){
        m_Player.reset();
        rollAll.setText("ROLL\nALL");

        start=true;
        set=0;

        ((TextView)findViewById(R.id.info)).setText("Press 'ROLL ALL' to start a game");

        m_Dices.diceValue(1,2,3,4,5);
        setTextDices();

        ((TextView)findViewById(R.id.TVScore)).setText("0");
        ((TextView)findViewById(R.id.Bonus)).setText("0");
        for(int i=0;i<13;i++){
            m_ListFigure.get(i).setScore("-");
        }
        figureAdapter.notifyDataSetChanged();
    }

    /**
     * Fonction permettant de lancer tous les dés
     * @param v Button rollAll
     */
    public void rollAll(View v){

        if(rollAll.getText()=="RESTART"){
            reset();
        }
        else {

            m_Dices.rollYams("12345");
            setTextDices();

            if (start) {
                rollSelected.setEnabled(true);
                rollSelected.setTextColor(Color.WHITE);
                ((TextView) findViewById(R.id.info)).setText("");
                start = false;
            } else {
                if (!rollSelected.isEnabled()) {
                    rollSelected.setEnabled(true);
                    rollSelected.setTextColor(Color.WHITE);
                }
                nextRound();
            }
        }
    }
}