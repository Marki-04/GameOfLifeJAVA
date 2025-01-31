package org.example;


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        char[][] tableau = new char[12][12];

        // Création du tableau
        for (int i = 1; i < 10; i++) {
            System.out.println(" ");
            for (int j = 1; j < 10; j++) {
                // Ajout de cellules selectionnées :
                if(i == 3 && j == 3) {tableau[i][j] = 'X';}
                else if(i == 5 && j == 4) {tableau[i][j] = 'X';}
                else if(i == 7 && j == 5) {tableau[i][j] = 'X';}
                else if(i == 8 && j == 6) {tableau[i][j] = 'X';}
                else if(i == 9 && j == 7) {tableau[i][j] = 'X';}
                else if(i == 8 && j == 8) {tableau[i][j] = 'X';}
                // Reste des cellules vides :
                else {tableau[i][j] = ' ';}
                System.out.printf("[" + String.valueOf(tableau[i][j]) + "]");
            }
        }

        // LES CONDITIONS :



    }


}