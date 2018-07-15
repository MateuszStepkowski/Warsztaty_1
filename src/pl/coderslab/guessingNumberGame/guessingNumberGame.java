package pl.coderslab.guessingNumberGame;

import java.util.Scanner;

public class guessingNumberGame {

    public static void main(String[] args){

        System.out.println("Think about a number from 0 to 1000 and then I will guess it in at most ten attempts");
        int min = 0, max = 1000, guess=-500;
        boolean rightClue;
        Scanner scan = new Scanner(System.in);

        while (true) {
            while (min < max) {

                guess = (max - min) / 2 + min;
                rightClue = false;
                while (rightClue == false) {

                    System.out.println(" I guess " + guess + "\n Say if its correct, too little or too much: ");
                    String clue = scan.nextLine().toUpperCase().replaceAll(" ", "");

                    if (clue.equals("CORRECT")) {
                        System.out.println("I Won");
                        System.exit(0);
                    } else if (clue.equals("TOOMUCH")) {
                        max = guess;
                        rightClue = true;
                    } else if (clue.equals("TOOLITTLE")) {
                        min = guess;
                        rightClue = true;
                    } else System.out.println("invalid clue, try again");

                }
            }
            System.out.println("Its " + guess + " or You cheated");
            rightClue = false;
            while (rightClue == false) {
                System.out.println("Say if its correct, too little or too much and dont try to cheat me again! ");
                String clue = scan.nextLine().toUpperCase().replaceAll(" ", "");

                if (clue.equals("CORRECT")) {
                    System.out.println("I Won");
                    System.exit(0);
                } else if (clue.equals("TOOMUCH")) {
                    min = 0;
                    rightClue = true;
                } else if (clue.equals("TOOLITTLE")) {
                    max = 1000;
                    rightClue = true;
                } else System.out.println("invalid clue, try again");
            }
            }
    }
}
