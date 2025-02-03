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
        tableau[3][4] = 'X';
        tableau[4][4] = 'X';
        tableau[5][4] = 'X';

        // Créer un scanner pour la touche ENTER
        Scanner scanner = new Scanner(System.in);
        do{
            // Afficher le tablea de jeu
            afficherJeu(tableau);

            // Attendre pour une touche
            scanner.nextLine();

            // Calculer la génération suivante
            tableau = generationSuivante(tableau);

        } while (true);


    }
    // Fonction qui affiche le tableau
    private static void afficherJeu(char[][] tableau) {
        for (int i = 1; i < 11; i++) {
            System.out.println(" ");
            for (int j = 1; j < 11; j++) {
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
        System.out.println();
    }

    // Fonction qui calcule la génération suivante
    private static char[][] generationSuivante(char[][] tableau) {
        // Créer un nouveau tableau
        char[][] nouveau = new char[12][12];

        // Les conditions de survie
        // Se promener dans chaque case et décider si la cellule vit ou meurt
        for(int i = 1; i < 11; i++) {
            for(int j = 1; j < 11; j++) {
                int voisins = nombreVoisins(tableau, i, j);
                // Cellule vivante doit avoir 2 ou 3 voisins seulement
                //
                if (tableau[i][j] == 'X') {
                    if (voisins == 2 || voisins == 3) {
                        nouveau[i][j] = 'X';
                    }
                    else { // Si on a pas les voisins nécéssaires la cellule meurt
                        nouveau[i][j] = ' ';
                    }
                }
                else if ((tableau[i][j] == ' ')){
                    if (voisins == 3) {
                        nouveau[i][j] = 'X';
                    }
                    else {
                        nouveau[i][j] = ' ';
                    }
                }
            }
        }


        return nouveau;
    }

    // Fonction nombreVoisins qui retourne le nombre de cellules autour de la coordonné passée en paramètre
    private static int nombreVoisins(char[][] tableau, int x, int y) {
        int nombre = 0;

        // Vérifier les 8 directions
        if (tableau[x - 1][y - 1] == 'X') nombre++;
        if (tableau[x][y - 1] == 'X') nombre++;
        if (tableau[x - 1][y] == 'X') nombre++;
        if (tableau[x][y + 1] == 'X') nombre++;
        if (tableau[x + 1][y + 1] == 'X') nombre++;
        if (tableau[x + 1][y] == 'X') nombre++;
        if (tableau[x + 1][y - 1] == 'X') nombre++;
        if (tableau[x - 1][y + 1] == 'X') nombre++;

        // Retourner le total
        return nombre;

    }
}