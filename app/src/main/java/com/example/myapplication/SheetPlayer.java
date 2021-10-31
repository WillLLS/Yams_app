package com.example.myapplication;
/**
 * @file SheetPlayer.java
 * @brief Contient la déclaration de la classe \c SheetPlayer
 * 
 * La classe \c SheetPlayer permet de gérer le score du joueur.
 * @author William Lalis
 * @date Septembre 2021
 */
import android.util.Log;

import java.util.Scanner;


public class SheetPlayer {

    /** Nom du joueur */
    public String m_name;  
    private int m_totalScore;   /** Score total de l'utilisateur */
    private int[] m_pScore; /** Score de chaque figure */
    private int m_round=1;  /** Indice du round actuel */
    private Boolean m_bonus = false;    /** Active le bonus de point */
    private boolean[] m_available = {true,true,true,true,true,true,true,true,true,true,true,true,true}; /** Determine si la figure est disponible */

    /**
     * @fn SheetPlayer(String name)
     * @brief Permet d'instancier un joueur.
     * @param name Nom du joueur.
     * @return Une instance SheetPlayer.
     */
    SheetPlayer(String name){
        m_name=name;
        m_totalScore=0;
        m_pScore=new int[13];
    }

    public void reset(){
        m_totalScore=0;
        m_pScore=new int[13];
        m_round=1;
        m_bonus=false;

        for(int i=0;i<12;i++){
            m_available[i]=true;
        }
    }

    public boolean getAvailable(int position){
        return m_available[position];
    }

    public void newRound(){
        m_round+=1;
    }

    public int getScore(int indexFigure){
        return m_pScore[indexFigure];
    }

    public int getTotalScore(){
        return m_totalScore;
    }

    public void setRound(int round){
        m_round=round;
    }

    public int getRound(){
        return m_round;
    }

    public void verifBonus(){
        int bonus=0;
        for(int i=0;i<6;i++){
            if(m_available[i]==false){
                bonus+= m_pScore[i];
            }
        }
        if(bonus>=63){ ///////////////////////////
            m_bonus=true;
        }

        Log.d("Bonus",String.valueOf(m_bonus));
    }

    public boolean getBonus(){
        return m_bonus;
    }

    /**
     * @fn public void printScore(void)
     * @brief Permet d'afficher la console de jeu.
     * 
     * Le joueur peut alors visualiser ses dés, les différents choix qu'il a fait ainsi que son score.
     * 
     * @return Affiche le score du joueur.
     */

    public void printScore(){

        String[] choice={"a","b","c","d","e","f","g","h","i","j","k","l","m"};
        String[] nameFigure = {"Brelan","Full","Carré","Petite Suite","Grande Suite","Yams","Chance"};
        int subTotal=0;

        System.out.println("\n --------------------\n Player: Player1 round: " + m_round +"\n --------------------");

        for(int indice=0;indice<6;indice++){
            
            if(m_available[indice]==true){
                System.out.println("[" + choice[indice] + "] sum of " + (indice+1) + " : -" );
            }
            else{
                System.out.println("[x] sum of " + (indice+1) + " : " + m_pScore[indice]);
                subTotal+=m_pScore[indice];
            }
        } 
        System.out.println("----subTotal: " + subTotal + " => bonus : "+ ((subTotal>63) ? 35 : 0));

        for(int indice=6;indice<13;indice++){
            if(m_available[indice]==true){
                System.out.println("[" + choice[indice] + "] " + nameFigure[indice-6] + " : -");
            }
            else{
                System.out.println("[x] " + nameFigure[indice-6] + " : " + m_pScore[indice]);
            }
        }

        System.out.println("Total: " + (m_bonus ? m_totalScore+35 : m_totalScore));
        System.out.println("--------------------");

        m_round=(m_round+1)%3 + 1;
    }

    /**
     * @fn public int askFigure(Scanner sc)
     * @brief Demande au joueur de selectionner l'indice de la figure désirée.
     * 
     * Cet indice est sous forme de lettre, puis transformer en entier grâce à cette fonction.
     * 
     * @param sc Permet de récuperer le choix de l'utilisateur.
     * @return (int) l'index du de la figure choisie par le joueur.
     */

    public int askFigure(Scanner sc){ //Choix de la FIGURE (sum of 1,...)

        int indexFigure=0;
        System.out.println("Indicates the figure you want to play (a,b ...m):");
        String figure = sc.nextLine();

        switch(figure){
            case "a":
                indexFigure=0;
                break;
            case "b":                    
                indexFigure=1;
                break;
            case "c":
                indexFigure=2;
                break;
            case "d":
                indexFigure=3;
                break;
            case "e":
                indexFigure=4;
                break;
            case "f":
                indexFigure=5;
                break;
            case "g":
                indexFigure=6;
                break;
            case "h":
                indexFigure=7;
                break;
            case "i":
                indexFigure=8;
                break;
            case "j":
                indexFigure=9;
                break;
            case "k":
                indexFigure=10;
                break;
            case "l":
                indexFigure=11;
                break;
            case "m":
                indexFigure=12;
                break;
            default:
                m_pScore[0]=0;
                System.out.println("\nThe default choice is a.");
                break;
            }
        return indexFigure;
    }

    /**
     * @fn public void updateScoring(int figureIndex,int score)
     * @brief Met à jour le score du joueur.
     * 
     * Cette fonction permet de mettre à jour le score du joueur. Elle permet également de modifier la disponibilité de certaine figure et d'ajouter ou non un bonus de point.
     * 
     * @param figureIndex Indice de la figure choisie par le joueur.
     * @param score Score obtenue par le joueur lors de ce round. Provient de la classe YamsDice.
     * @return void
     */
    public void updateScoring(int figureIndex,int score){
        m_available[figureIndex]=false;
        m_pScore[figureIndex]=score;
        m_totalScore+=score;
        verifBonus();
    }

    /**
     * @fn public int totalScore(void)
     * @brief Cette fonction est appelé à la fin de la partie.
     * 
     * Elle permet d'afficher le score total du joueur en prenant compte des bonus et du résultat de chaque tour.
     * @return le score total du joueur.
     */
    public int totalScore(){
        if(m_bonus){
            m_totalScore+=35;
        }
        return m_totalScore;
    }
}
