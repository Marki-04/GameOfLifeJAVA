package org.example;


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        // Créer le tableau
        char[][] tableau = new char[12][12];

        // Ajouter des cellules selectionnées
        tableau[3][3] = 'X';
        tableau[3][4] = 'X';
        tableau[4][4] = 'X';

        // Créer un scanner pour la touche ENTER
        Scanner scanner = new Scanner(System.in);
        do{
            // Afficher le tablea de jeu
            afficherJeu(tableau);

            // Attendre pour une touche
            scanner.nextLine();

            // Calculer la génération suivante


        } while (true);


    }
    // Création du tableau
    private static void afficherJeu(char[][] tableau) {
        for (int i = 1; i < 10; i++) {
            System.out.println(" ");
            for (int j = 1; j < 10; j++) {
                // Ajout de cellules selectionnées :
                if (tableau[i][j] == 'X') {
                    tableau[i][j] = 'X';
                }
                // Reste des cellules vides :
                else {
                    tableau[i][j] = ' ';
                }
                System.out.printf("[" + String.valueOf(tableau[i][j]) + "]");
            }
        }
    }
}