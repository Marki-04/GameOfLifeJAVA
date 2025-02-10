package org.example;


import com.google.gson.Gson;

import java.io.*;
import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        // Créer le tableau
        char[][] tableau = new char[12][12];

        // Boucle s'il y a une erreur d'entrée
        while (true) {
            // Menu utilisateur
            System.out.print("Bonjour et bienvenue à GameOfLife!\n" +
                    "Choississez une des options suivantes : \n" +
                    "1 - Poursuivre le pattern précédent.\n" +
                    "2 - Commencer une nouvelle partie avec une forme GLIDER\n" +
                    "3 - Commencer une nouvelle partie avec une forme de BARRES\n");
            // Scanneur qui attends pour un choix de l'utilisateur
            Scanner scan = new Scanner(System.in);
            int numChoisi = scan.nextInt();
            scan.nextLine();

            // Selon le choix de l'utilisateur, on affiche le tableau spécifique
            if (numChoisi == 1) {
                tableau = deserialiserTableau("tableau.json");
                break;
            }else if (numChoisi == 2) {
                tableau = deserialiserTableau("glider.json");
                break;
            }else if (numChoisi == 3) {
                tableau = deserialiserTableau("barre.json");
                break;
            }else {
                System.out.println("Le choix n'existe pas, réessayez!");
            }
        }



        // Créer un scanner pour la touche ENTER
        Scanner scanner = new Scanner(System.in);
        do{
            // Afficher le tablea de jeu
            afficherJeu(tableau);

            // Attendre pour une touche
            scanner.nextLine();

            // Enregistrer le tableau dans le fichier tableau.json
            enregistrerTableau(tableau);

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

    // Fonction qui enregistre l'état dans un fichier
    private static void enregistrerTableau(char[][] tableau) {
        // Créer la variable Gson
        Gson gson = new Gson();
        // Ajouter le tableau dans un string Json
        String json = gson.toJson(tableau, char[][].class);
        // Sérialiser le tableau
        try {
            FileWriter fw = new FileWriter("tableau.json");
            fw.write(json);
            fw.close();
        }catch (IOException e){
            System.out.println("Erreur à écrire dans le fichier");
        }
    }

    // Fonction qui désérialise le fichier .json selon le nom recu en paramètres
    private static char[][] deserialiserTableau(String fichier) {
        char[][] tableauDeserialise;
        try {
            Gson gson = new Gson();
            BufferedReader br = new BufferedReader(new FileReader(fichier));
            tableauDeserialise = gson.fromJson(br, char[][].class);
        } catch (IOException e) {
        throw new RuntimeException(e);
    }
        return tableauDeserialise;
    }

}