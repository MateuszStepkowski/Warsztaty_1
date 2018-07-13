package pl.coderslab.guessNumberGame;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class guessNumberGame {

    public static void main(String[] args) {

        int randNum = new Random().nextInt(100) + 1, guessNum = 0;
        Scanner scan = new Scanner(System.in);

        while (guessNum != randNum) {

            System.out.println("Zgadnij liczbę: ");
            try {
                guessNum = Integer.parseInt(scan.nextLine().replaceAll(" ", ""));
            } catch (NumberFormatException e) {
                System.out.println("Wprowadzono błędne dane. Podaj liczbę naturalną z zakresu 1-100: ");
                continue;
            }
            if (guessNum > randNum){
                if (guessNum > 100) {
                    System.out.println("Za dużo!");
                    System.out.println("Podano liczbę spoza zakresu 1-100");
                }else System.out.println("Za dużo!");
            }
            else if (guessNum < randNum){
                if (guessNum < 1){
                    System.out.println("Za mało!");
                    System.out.println("Podano liczbę spoza zakresu 1-100");
                }else  System.out.println("Za mało!");
            }
        }

        System.out.println("Zgadłeś !");
    }
}
