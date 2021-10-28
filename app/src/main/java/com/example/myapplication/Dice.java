package com.example.myapplication;

/**
 * @file Dice.java
 * @brief Contient la déclaration de la classe \c Dice
 * 
 * La classe \c Dice permet de manipuler un dé. Il est possible de le lancer
 * et de récuperer sa valeur.
 * @author William Lalis
 * @date Septembre 2021
 */

public class Dice {

    private int numberValues;

    /**
     *  Correspond à la valeur actuelle du dé.
     */
    public int diceValue;

    /**
     * @return Instance de l'objet.
     */
    Dice(){
        numberValues = 6; // Default 6 face
        roll();
    }

    

    Dice(int numberValues){
        this.numberValues=numberValues;
        roll();
    }

    /**
     * @fn public void roll(void)
     * @brief Fonction permettant de simuler le lancement du dé.
     * @return void
     */

    public void roll(){
        diceValue=(int)(Math.random()*(numberValues))+1;
    }

    /**
     * @fn public void printDice
     * @brief Fonction permettant l'affichage de la valeur du dé.
     * @return Affiche la valeur.
     */

    public void printDice(){
        System.out.println(diceValue);
    }

}