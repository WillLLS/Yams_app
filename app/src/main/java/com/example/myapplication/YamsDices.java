package com.example.myapplication; /**
 * @file YamsDices.java
 * @brief Contient la déclaration de la classe \c YamsDice
 * 
 * Cette classe permet de manipuler plusieurs dés provenant de la classe \c Dice.
 * @author William Lalis
 * @date Septembre 2021
 */

import java.util.Scanner;

public class YamsDices {

    public Dice[] dice5 = new Dice[5]; /** Liste contenant 5 dés pour le jeu. */

    YamsDices(){
        for(int idxDice=0;idxDice<5;idxDice++){
            dice5[idxDice]=new Dice();
            dice5[idxDice].roll();
        }
    }


    /**
     * @fn public void printYams(void)
     * @brief Affiche la valeur de tous les dés.
     * 
     * Cette fonction permet d'afficher dans la console la valeur des dés à partir de leurs indices.
     * @return void
     */
    public void printYams(){
        System.out.println("Dice n° 1 2 3 4 5");
        System.out.print("        ");
        for(int idxDice=0;idxDice<5;idxDice++){
            System.out.print(dice5[idxDice].diceValue+" ");
        }
        System.out.println("");
    }

    /**
     * @fn public String askDice(Scanner sc)
     * @brief Demande à l'utilisateur de faire un choix
     * 
     * Ce choix concerne les dés que le joueur souhaite relancer. Il les choisis à partir de leurs indices.
     * @param sc Permet de récuperer le choix de l'utilisateur
     * @return (String) Choix de l'utilisateur.
     */
    public String askDice(Scanner sc){
        System.out.println("Indicates the dices you want to roll (12345):");
        return sc.nextLine();
    }

    /**
     * @fn public void rollYams(String position)
     * @brief Simule le lancer de certains dés.
     * 
     * rollYams permet de lancer les dés dont l'indice est en paramètre.
     * @param position Il s'agit des indices des dés à relancer.
     * @return void
     */
    public void rollYams(String position){
        String[] pos = position.split("");

        for(String idx : pos){
            switch(idx){
                case "1":
                    dice5[0].roll();
                    break;
                case "2":
                    dice5[1].roll();
                    break;
                case "3":
                    dice5[2].roll();
                    break;
                case "4":
                    dice5[3].roll();
                    break;
                case "5":
                    dice5[4].roll();
                    break;
                /*
                default:
                    System.out.println("error - value of index incorrect");
                    break;*/
            }
        }
    }

    /**
     * @brief Fonction de test
     * 
     * Cette fonction permet d'attribuer à chaque dé une valeur pour pouvoir tester le programme.
     * @param dice1 Valeur du dé 1
     * @param dice2 Valeur du dé 2
     * @param dice3 Valeur du dé 3
     * @param dice4 Valeur du dé 4
     * @param dice5 Valeur du dé 5
     * @return void
     */
    public void diceValue(int dice1,int dice2,int dice3,int dice4,int dice5){ // Fonction pour tester le 
        this.dice5[0].diceValue=dice1;
        this.dice5[1].diceValue=dice2;
        this.dice5[2].diceValue=dice3;
        this.dice5[3].diceValue=dice4;
        this.dice5[4].diceValue=dice5;
    }

    /**
     * @fn public int getSum(int indexFigure)
     * @brief Determine le score pour les figures de la première partie
     * 
     * Il s'agit d'une sous fonction utilisée dans la fonction getScore.
     * @param indexFigure indice de la figure choisie par le joueur.
     * @return (int) Score obtenu.
     */
    public int getSum(int indexFigure){
        int sum=0;
        for(int index=0;index<5;index++){
            if(dice5[index].diceValue==(indexFigure+1)){
                sum+=dice5[index].diceValue;
            }
        }
        return sum;
    }

    /**
     * @fn public int getSum2(int indexFigure)
     * @brief Determine le score pour les figures de la seconde partie.
     * 
     * Il s'agit d'une sous fonction utilisée dans la fonction getScore. Elle calcul le score pour les figures suivante :
     * Brelan, Full, Carre, Petite et Grande Suite, Yams et Chance.
     * @param indexFigure indice de la figure choisie par le joueur.
     * @return (int) Score obtenu.
     */
    public int getSum2(int indexFigure){
        int sum=0;
        switch(indexFigure){
            case 6: //Brelan
                int brelan=0;

                for(int value=1;value<7;value++){
                    for(int indice=0;indice<5;indice++){
                        if(value==dice5[indice].diceValue){
                            brelan+=1;
                        }
                    }
                    if(brelan==3){
                            sum = 3*value;
                            break;
                        }
                    brelan=0;
                }
                break;

            case 7: //Full
                int brelanFull=0;
                int valueBrelan=0;
                int paire=0;

                for(int value=1;value<7;value++){   // Recherche Brelan
                    for(int indice=0;indice<5;indice++){
                        if(value==dice5[indice].diceValue){
                            brelanFull+=1;
                        }
                    }
                    if(brelanFull==3){
                        valueBrelan=value;
                        break;
                    }
                    brelanFull=0;
                }

                for(int value=1;value<7;value++){   // Recherche paire en éliminant le Brelan
                    if(value!=valueBrelan){
                        for(int indice=0;indice<5;indice++){
                            if(value==dice5[indice].diceValue){
                                paire+=1;
                            }
                        }
                        if(paire==2){
                            break;
                        }
                        paire=0;
                    }
                }
                if(brelanFull==3 & paire==2){
                    sum=25;
                }
                break;

            case 8: //Carre
                int carre=0;

                for(int value=1;value<7;value++){
                    for(int indice=0;indice<5;indice++){
                        if(value==dice5[indice].diceValue){
                            carre+=1;
                        }
                        if(carre==4){
                            sum = 4*value;
                            break;
                        }
                    }
                    carre=0;
                }
                break;

            case 9: //Petite Suite 
                if(suite(4)>=3){
                    sum = 30;
                }
                break;

            case 10: //Grande Suite
                if(suite(4)>=4){
                    sum = 40;
                }
                break;

            case 11: //Yams
                for(int index=1;index<5;index++){
                    if(dice5[0].diceValue!=dice5[index].diceValue){
                        sum=0;
                        break;
                    }
                    else{
                        sum=50; // Points gagnés
                    }
                }
                break;

            case 12: //Chance
                for(int indice=0;indice<5;indice++){
                    sum+=dice5[indice].diceValue;
                }
                break;
        }
        return sum;
    }

    /**
     * @fn public static void Tri(int[] diceValue)
     * @brief Fonction permettant de trier une liste d'entier' dans l'ordre croissant.
     * 
     * Cette fonction est utiliser pour determiner si une figure (suite) est obtenue.
     * @param diceValue Liste contenant les valeurs des dés.
     */
    public static void Tri(int[] diceValue){
        for(int i=0;i<5;i++){
            for(int j=i;j<5;j++){
                if(diceValue[i]>diceValue[j]){
                    int data_int = diceValue[i];
                    diceValue[i]=diceValue[j];
                    diceValue[j]=data_int;
                }
            }
        }
    }

    /**
     * @fn public int suite(int typeSuite)
     * @brief Determine si les 5 dés forment une suite.
     * @param typeSuite Choix d'une petite Suite (int typeSuite = 4) ou d'une grande Suite (int typeSuite = 5)
     * @return (int) Suite, permet de savoir s'il y a ou non une suite
     */
    public int suite(int typeSuite){
        int[] diceValue = {dice5[0].diceValue,dice5[1].diceValue,dice5[2].diceValue,dice5[3].diceValue,dice5[4].diceValue};
        Tri(diceValue);
        byte suite=0;
        for(int indice=0;indice<4;indice++){
            if((diceValue[indice]+1)==diceValue[indice+1]){ ////////////////////////
                suite+=1;
            }
        }
        return suite;  
    }

    /**
     * @fn public int getScore(int indexFigure)
     * @brief Permet d'obtenir le score du joueur
     * 
     * Suivant le choix de la figure par le joueur ainsi que la valeurs des dés, cette fonction retournera le score obtenue. 
     * @param indexFigure Choix de la figure fait par le joueur.
     * @return (int) Score du joueur.
     */
    public int getScore(int indexFigure){
        int score=0;
        if(indexFigure<6){
            score=getSum(indexFigure); // Récupération du score pour les permières figures
        }
        else if(5<indexFigure && indexFigure<13){
            score=getSum2(indexFigure); // Récupération du score pour les dernières figures
        }

        return score;
    }
}
