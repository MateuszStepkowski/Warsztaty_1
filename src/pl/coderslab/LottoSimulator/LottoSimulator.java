package pl.coderslab.LottoSimulator;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class LottoSimulator {

    public static void main(String[] args){

        int[] chosenNumbs = new int[6];
        boolean alreadyChosen = false;
        Scanner scan = new Scanner(System.in);

        //Choosing numbers by user
        for (int i = 0; i < 6; i++) {

            while (chosenNumbs[i] < 1 || chosenNumbs[i] > 49 || alreadyChosen == true) {

                alreadyChosen = false;
                chosenNumbs[i] = 0;
                System.out.println("Choose "+(i+1)+" number from 1 to 49: ");

                try {
                    chosenNumbs[i] = Integer.parseInt(scan.nextLine().replaceAll(" ", ""));
                }catch (NumberFormatException e) {
                    System.out.println("Wrong format, try again");
                    continue;
                }

                for (int j = 0; j < i; j++) {
                    if (chosenNumbs[i] == chosenNumbs[j]){
                        alreadyChosen = true;
                        break;
                    }
                }
                if (alreadyChosen == true) {
                    System.out.println("You have already chosen this number, try again");
                    continue;
                }
                if (chosenNumbs[i] < 1 || chosenNumbs[i] > 49) System.out.println("Chosen number is out of range, try again");
            }
        }
        Arrays.sort(chosenNumbs);
        System.out.println("Chosen numbers:");
        System.out.println(Arrays.toString(chosenNumbs));

        //winning numbers draw
        int[] arrOfwinning = new int[49];
        for (int i = 0; i < arrOfwinning.length; i++) {
            arrOfwinning[i] = i+1;
        }
        shuffleArray(arrOfwinning);
        arrOfwinning = Arrays.copyOf(arrOfwinning, 6);
        Arrays.sort(arrOfwinning);

        System.out.println("Winning numbers:");
        System.out.println(Arrays.toString(arrOfwinning)+"\n");
        System.out.println(" 1st prize: six matching numbers\n 2nd prize: five matching numbers\n 3rd prize: four matching numbers 4th prize: six matching numbers\n\n");
        int counter = 0;
        for (int i : chosenNumbs) {
            for (int j : arrOfwinning) {
                if (i == j){
                    counter ++;
                    break;
                }
            }
        }
        if (counter > 2) System.out.println("Congrats ! You matched "+counter+" numbers, check Your prize :D");
        else System.out.println("I'm sorry, You didn't win\nMaybe next time...");
    }
    //method for array shuffling
    static void shuffleArray(int[] arr) {

        Random rand = new Random();

        for (int i = arr.length - 1; i > 0; i--)
        {
            int index = rand.nextInt(i + 1);

            int a = arr[index];
            arr[index] = arr[i];
            arr[i] = a;
        }
    }
}
